package map;

import base.GameEnginer;
import base.Items;
import enemy.CreateEnemy;
import enemy.Enemy;
import hero.Hero;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Overworld {

    static Random random = new Random();
    static int gainExperience = 0;
    static int vidaDanger;
    static boolean combat;

    public static void explorarZona(Hero hero, String zona, Scanner sc) {
        int paso = 1;
        int maxPaso = 1;
        String subZona = "C";
        vidaDanger=hero.getLife()/2;
        combat=false;
        boolean misionCompletada = false;

        while (!misionCompletada) {

            if (paso == maxPaso) {
                if (activarEvento()) {
                    ArrayList<Enemy> combate = CreateCombat(HowMany(), zona, subZona);
                    iniciarCombate(hero, combate, sc);
                    misionCompletada = verificarMisionCompletada(hero);
                } else {
                    System.out.println("No has encontrado ningún combate");
                }

                System.out.println("¿Deseas avanzar a la siguiente subzona? (s/n)");
                String respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    subZona = avanzarZona(subZona);
                }
                paso = 0;

            } else {
                paso++;
            }

            if (paso == 0) {
                misionCompletada = verificarMisionCompletada(hero);
            }

            if(hero.getLife()<vidaDanger){
                if(!Hero.getInventory().isEmpty()){
                    if(Hero.buscarCurabool(combat)){
                        Items itemCura = Hero.buscarCura(combat);
                        assert itemCura != null;
                        Hero.usarCura(hero, itemCura);
                    }
                }
            }

        }

        System.out.println("Misión completada. Volviendo a la posada.");
        GameEnginer.Posada(hero, sc);

    }

    private static boolean verificarMisionCompletada(Hero hero) {
        return Hero.buscarObjetoMision(hero.getItemMision(), hero.getMisionActual().getNombreItem());
    }

    private static boolean activarEvento() {
        int randomBattle = random.nextInt(10) + 1;
        return randomBattle %2 == 0;
    }

    private static int HowMany() {
        return random.nextInt(3)+1;
    }

    private static String avanzarZona(String subZona) {
        return switch (subZona) {
            case "A" -> "B";
            case "B" -> "A";
            default -> subZona;
        };
    }

    private static ArrayList<Enemy> CreateCombat(int howMany, String zona, String subZona) {
        ArrayList<Enemy> bestias = CreateEnemy.Bestiary();
        ArrayList<Enemy> combat = new ArrayList<>();
        ArrayList<Enemy> posiblesEnemigos = new ArrayList<>();

        for (Enemy enemy : bestias) {
            if (enemy.getZone().equals(zona) && enemy.getSubZona().equals(subZona)) {
                posiblesEnemigos.add(enemy);
            }
        }

        for (int i = 0; i < howMany && !posiblesEnemigos.isEmpty(); i++) {
            int index = random.nextInt(posiblesEnemigos.size());
            combat.add(posiblesEnemigos.remove(index));
        }

        return combat;
    }

    private static void iniciarCombate(Hero hero, ArrayList<Enemy> combate, Scanner sc) {
        boolean heroTurn = hero.getSpeed() >= combate.getFirst().getSpeed();
        Enemy currentTarget = combate.get(random.nextInt(combate.size()));
        hero.calidad(hero.getTipoCalidad());
        vidaDanger=hero.getLife()/3;
        combat=true;

        boolean defending = false;

        while (hero.getLife() > 0 && !combate.isEmpty()) {

            System.out.println("Vida del héroe: " + hero.getLife() + "/" + hero.getLifeMax());
            System.out.println("Enemigos:");
            for (Enemy enemy : combate) {
                System.out.println(enemy.getName());
            }

            if (heroTurn) {
                System.out.println("Elige una acción: 1. Atacar 2. Defender 3. Huir");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atacar(hero,combate,currentTarget);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " se defiende.");
                        hero.setDefense(hero.getDefense()*2);
                        defending = true;
                        break;
                    case 3:
                        System.out.println(hero.getName() + " huye del combate.");
                        return;
                }

            } else {

                for (Enemy enemy : combate) {
                    int damage = Math.max(0, enemy.getDmg() - hero.getDefense());
                    hero.setLife(hero.getLife() - damage);
                    System.out.println(enemy.getName() + " ataca a " + hero.getName() + " y causa " + damage + " de daño.");
                    if (hero.getLife() <= 0) {
                        System.out.println(hero.getName() + " ha sido derrotado. Game Over.");
                        return;
                    }
                }

            }

            if (defending) {
                hero.setDefense(hero.getDefense() / 2);
                defending = false;
            }

            if(hero.getLife()<vidaDanger){
               if(!Hero.getInventory().isEmpty()){
                   if(Hero.buscarCurabool(combat)){
                       Items itemCura = Hero.buscarCura(combat);
                       assert itemCura != null;
                       Hero.usarCura(hero, itemCura);
                   }
               }
            }

            heroTurn = !heroTurn;
        }

        if (hero.getLife() > 0) {
            System.out.println(hero.getName() + " ha ganado el combate.");
            if (hero.getLevel() != hero.getLevelMax()) {
                hero.setExp(hero.getExp() + gainExperience);
                if (hero.getExp() >= hero.getExpNext()) {
                    hero.subirNivel();
                }
            }
        }

    }

    public static void Boss(Hero hero, Enemy enemy, Scanner sc) {
        boolean heroTurn = hero.getSpeed() >= enemy.getSpeed();
        hero.calidad(hero.getTipoCalidad());
        vidaDanger=hero.getLife()/3;
        combat=true;

        boolean defending = false;

        while (hero.getLife() > 0 && enemy.getLife()==0) {

            System.out.println("Vida del héroe: " + hero.getLife() + "/" + hero.getLifeMax());
            System.out.println("Enemigos:" + enemy.getName());

            if (heroTurn) {
                System.out.println("Elige una acción: 1. Atacar 2. Defender 3. Huir");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atacar(hero,enemy);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " se defiende.");
                        hero.setDefense(hero.getDefense()*2);
                        defending = true;
                        break;
                    case 3:
                        System.out.println(hero.getName() + " huye del combate.");
                        return;
                }

            } else {

                int damage = Math.max(0, enemy.getDmg() - hero.getDefense());
                hero.setLife(hero.getLife() - damage);
                System.out.println(enemy.getName() + " ataca a " + hero.getName() + " y causa " + damage + " de daño.");
                if (hero.getLife() <= 0) {
                    System.out.println(hero.getName() + " ha sido derrotado. Game Over.");
                    return;
                }

            }

            if (defending) {
                hero.setDefense(hero.getDefense() / 2);
                defending = false;
            }

            if(hero.getLife()<vidaDanger){
                if(!Hero.getInventory().isEmpty()){
                    if(Hero.buscarCurabool(combat)){
                        Items itemCura = Hero.buscarCura(combat);
                        assert itemCura != null;
                        Hero.usarCura(hero, itemCura);
                    }
                }
            }

            heroTurn = !heroTurn;
        }

        if (hero.getLife() > 0) {
            System.out.println(hero.getName() + " ha ganado el combate.");
            if (hero.getLevel() != hero.getLevelMax()) {
                hero.setExp(hero.getExp() + gainExperience);
                if (hero.getExp() >= hero.getExpNext()) {
                    hero.subirNivel();
                }
            }
        }

    }

    private static void atacar(Hero hero, ArrayList<Enemy> combate, Enemy currentTarget) {
        int damage = Math.max(0, hero.getAttack() - currentTarget.getDef());
        currentTarget.setLife(currentTarget.getLife() - damage);
        System.out.println(hero.getName() + " ataca a " + currentTarget.getName() + " y causa " + damage + " de daño.");

        if (currentTarget.getLife() <= 0) {
            System.out.println(currentTarget.getName() + " ha sido derrotado.");
            combate.remove(currentTarget);
            gainExperience += currentTarget.getDropExperience();
            if (!combate.isEmpty()) {
                currentTarget = combate.get(random.nextInt(combate.size()));
            }
            if(activarEvento()){
                System.out.println("¡" + hero.getName() + " ha encontrado " + currentTarget.getDropItem() + "!");
                hero.addItemMision(currentTarget.getDropItem());
            }
        }
    }

    private static void atacar(Hero hero, Enemy currentTarget) {
        int damage = Math.max(0, hero.getAttack() - currentTarget.getDef());
        currentTarget.setLife(currentTarget.getLife() - damage);
        System.out.println(hero.getName() + " ataca a " + currentTarget.getName() + " y causa " + damage + " de daño.");

        if (currentTarget.getLife() <= 0) {
            System.out.println(currentTarget.getName() + " ha sido derrotado.");
            gainExperience+=currentTarget.getDropExperience();
        }
        System.out.println("¡" + hero.getName() + " ha encontrado un " + currentTarget.getDropItem() + "!");
        hero.addItemMision(currentTarget.getDropItem());
    }

}
