package map;

import enemy.CreateEnemy;
import enemy.Enemy;
import hero.Hero;
import hero.ItemsHero;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Overworld {

    static Random random = new Random();
    static int paso = 0;
    static int maxPaso = 5;
    static int gainExperience = 0;

    public static void explorarZona(Hero hero, String zona, Scanner sc){
        while (!misionCompletada(hero)){
            if (paso == maxPaso){
                if (activarEvento()){
                    ArrayList<Enemy>combate = CreateCombat(hero, HowMany(), zona);
                    iniciarCombate(hero, combate, sc);
                }
                paso = 0;
            }
            paso++;
        }
    }

    private static boolean misionCompletada(Hero hero) {
        String item = hero.getMisionActual().getNombreItem();
        int cantidad = hero.getMisionActual().getCantidad();
        return (Hero.buscarObjetoMision(hero, item, cantidad));
    }

    private static boolean activarEvento() {
        int randomBattle = random.nextInt(10) + 1;
        return randomBattle %2 == 0;
    }

    private static int HowMany() {
        return random.nextInt(3)+1;
    }


    private static ArrayList<Enemy> CreateCombat(Hero hero, int howMany, String zona) {
        ArrayList<Enemy> bestias = CreateEnemy.Bestiary();
        ArrayList<Enemy> combat = new ArrayList<>();
        int counter = howMany;

        while(counter!=0){
            for (Enemy enemy : bestias){
                if(enemy.getZone().equals(zona) && hero.getMisionActual().getRango().equals(enemy.getRank())){
                    combat.add(enemy);
                    counter--;
                }
            }
        }

        return combat;
    }

    private static void iniciarCombate(Hero hero, ArrayList<Enemy> combate, Scanner sc) {
        boolean heroTurn = hero.getSpeed() >= combate.getFirst().getSpeed();
        Enemy currentTarget = combate.get(random.nextInt(combate.size()));
        hero.calidad(hero.getEquipamiento());

        boolean defending = false;

        while (hero.getLifeAcual() > 0 && !combate.isEmpty()) {

            System.out.println("Vida del héroe: " + hero.getLifeAcual() + "/" + hero.getLifeMax());
            System.out.println("Enemigos:");
            for (Enemy enemy : combate) {
                System.out.println(enemy.getName());
            }

            if (heroTurn) {
                System.out.println("Elige una acción: 1. Atacar 2. Defender 3. Usar objeto 4. Huir");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atacar(hero,combate,currentTarget);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " se defiende.");
                        hero.setDef(hero.getDef()*2);
                        defending = true;
                        break;
                    case 3:
                        usarItem(hero, sc);
                        break;
                    case 4:
                        System.out.println(hero.getName() + " huye del combate.");
                        return;
                }

            } else {

                for (Enemy enemy : combate) {
                    int damage = Math.max(0, enemy.getDmg() - hero.getDef());
                    hero.setLifeAcual(hero.getLifeAcual() - damage);
                    System.out.println(enemy.getName() + " ataca a " + hero.getName() + " y causa " + damage + " de daño.");
                    if (hero.getLifeAcual() <= 0) {
                        System.out.println(hero.getName() + " ha sido derrotado. Game Over.");
                        return;
                    }
                }

            }

            if (defending) {
                hero.setDef(hero.getDef() / 2);
                defending = false;
            }

            heroTurn = !heroTurn;
        }

        if (hero.getLifeAcual() > 0) {
            System.out.println(hero.getName() + " ha ganado el combate.");
            if (hero.getLevel() != hero.getLvlMax()) {
                hero.setExperencie(hero.getExperencie() + gainExperience);
                if (hero.getExperencie() >= hero.getNextlvl()) {
                    hero.subirNivel();
                }
            }
        }

    }

    public static void Boss(Hero hero, Enemy enemy, Scanner sc) {
        boolean heroTurn = hero.getSpeed() >= enemy.getSpeed();
        hero.calidad(hero.getEquipamiento());

        boolean defending = false;

        while (hero.getLifeAcual() > 0 && enemy.getLife()==0) {

            System.out.println("Vida del héroe: " + hero.getLifeAcual() + "/" + hero.getLifeMax());
            System.out.println("Enemigos:" + enemy.getName());

            if (heroTurn) {
                System.out.println("Elige una acción: 1. Atacar 2. Defender 3. Usar objeto 4. Huir");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atacar(hero,enemy);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " se defiende.");
                        hero.setDef(hero.getDef()*2);
                        defending = true;
                        break;
                    case 3:
                        usarItem(hero, sc);
                        break;
                    case 4:
                        System.out.println(hero.getName() + " huye del combate.");
                        return;
                }

            } else {

                int damage = Math.max(0, enemy.getDmg() - hero.getDef());
                hero.setLifeAcual(hero.getLifeAcual() - damage);
                System.out.println(enemy.getName() + " ataca a " + hero.getName() + " y causa " + damage + " de daño.");
                if (hero.getLifeAcual() <= 0) {
                    System.out.println(hero.getName() + " ha sido derrotado. Game Over.");
                    return;
                }

            }

            if (defending) {
                hero.setDef(hero.getDef() / 2);
                defending = false;
            }

            heroTurn = !heroTurn;
        }

        if (hero.getLifeAcual() > 0) {
            System.out.println(hero.getName() + " ha ganado el combate.");
            if (hero.getLevel() != hero.getLvlMax()) {
                hero.setExperencie(hero.getExperencie() + gainExperience);
                if (hero.getExperencie() >= hero.getNextlvl()) {
                    hero.subirNivel();
                }
            }
        }

    }

    private static void atacar(Hero hero, ArrayList<Enemy>combate, Enemy currentTarget) {
        int damage = Math.max(0, hero.getDmg() - currentTarget.getDef());
        currentTarget.setLife(currentTarget.getLife() - damage);
        System.out.println(hero.getName() + " ataca a " + currentTarget.getName() + " y causa " + damage + " de daño.");

        if (currentTarget.getLife() <= 0) {
            System.out.println(currentTarget.getName() + " ha sido derrotado.");
            combate.remove(currentTarget);
            gainExperience+=currentTarget.getDropExperience();
            if (!combate.isEmpty()) {
                currentTarget = combate.get(random.nextInt(combate.size()));
            }
            if (activarEvento()) {
                System.out.println("¡" + hero.getName() + " ha encontrado un " + currentTarget.getDropItem() + "!");
                hero.getInventory().add(new ItemsHero(1, currentTarget.getDropItem(),"Item de Mision", HowMany()));
            }
        }
    }

    private static void atacar(Hero hero, Enemy currentTarget) {
        int damage = Math.max(0, hero.getDmg() - currentTarget.getDef());
        currentTarget.setLife(currentTarget.getLife() - damage);
        System.out.println(hero.getName() + " ataca a " + currentTarget.getName() + " y causa " + damage + " de daño.");

        if (currentTarget.getLife() <= 0) {
            System.out.println(currentTarget.getName() + " ha sido derrotado.");
            gainExperience+=currentTarget.getDropExperience();
        }
        System.out.println("¡" + hero.getName() + " ha encontrado un " + currentTarget.getDropItem() + "!");
        hero.getInventory().add(new ItemsHero(1, currentTarget.getDropItem(),"Item de Mision", 1));
    }

    private static void usarItem(Hero hero, Scanner sc) {
        for (int i = 0; i < hero.getInventory().size(); i++) {
            System.out.println((i + 1) + ". " + hero.getInventory().get(i).getNombre() + " " + hero.getInventory().get(i).getCantidad());
        }
        System.out.println("Elige un objeto para usar:");
        int itemChoice = sc.nextInt() - 1;
        ItemsHero itemUsar = hero.getInventory().get(itemChoice);
        System.out.println(hero.getName() + " usa " + itemUsar.getNombre() + ".");
        hero.menosCantidad(itemUsar);
    }

}
