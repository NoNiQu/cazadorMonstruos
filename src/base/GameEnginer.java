package base;

import enemy.Enemy;
import hero.Calidad;
import hero.Hero;
import hero.ItemsHero;
import map.Overworld;
import taberna.CreateItemsTienda;
import taberna.Mision;
import taberna.CreateMision;
import map.CreateZoneMap;
import map.ZoneMap;
import taberna.Tienda;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEnginer {

    static boolean herreroLoco = false;

    public static void Posada(Hero hero, Scanner sc){

        int counter = 0;
        boolean salir = false;
        int dormir = 5;

        System.out.println("Hola heroe " +hero.getName() + " bienvenido a la posada del pueblo");
        System.out.println("Para estar en esta posada tienes que hacer misiones de diferentes rangos: C, B, A. Estan restringidos por tu nivel.");
        System.out.println("Rango C: nivel 1-5, Rango B: nivel 5-10 y Rango A: nivel 10-15");
        System.out.println("Escoge un contrato, completalo y reclama tu recompensa. Sino traes el contrato completado no vuelvas.");
        System.out.println("Si mueres en el intento nadie te echara de menos! :)");
        System.out.println();

        while(!salir){
            System.out.println("Que hacemos hoy?");
            System.out.println("1. Escoger contrato");
            System.out.println("2. Entregar recompensa");
            System.out.println("3. Mirar la tienda");
            System.out.println("4. Mejorar equipamiento");
            System.out.println("5. Descansar");
            System.out.println("6. Guardar Partida");
            System.out.println("7. Llamar a la puerta");
            System.out.println("8. Huir del pueblo");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    System.out.println("Que zona quieres ir");
                    misionesDisponibles(hero, zonaSeleccionada(sc), sc);
                    break;
                case 2:
                    System.out.println("Que recompensa quieres entregar");
                    entregaMision(hero);
                    break;
                case 3:
                    abrirTienda(hero, sc);
                    break;
                case 4:
                    if (!herreroLoco)
                        abrirHerrero(hero, sc);
                    else
                        System.out.println("El herrero ha huido");
                    break;
                case 5:
                    System.out.println("Son " + dormir + " para dormir en la posada");
                    dormir(hero, dormir);
                    break;
                case 6:
                    System.out.println("GUARDA PARTIDA");
                    break;
                case 7:
                    if (herreroLoco){
                        Enemy enemy = new Enemy(13,"S", 10, 10,"Ormr, el Dragon regente", 4, 0,15,100,"Cabeza de Halcon Real","BossFinal", true);
                        eventoBossSecreto(hero, enemy, sc);
                    }else
                        System.out.println("No parece ocurrir nada.");
                    break;
                case 8:
                    System.out.println("Buena suerte amigo...");
                    System.out.println("Huyes del pueblo y te mueres por frio fin de la partida gilipollas");
                    System.out.println("GAME OVER");
                    salir = true;
                    break;
                default:
                    if (counter>0) {
                        System.out.println("PUTO NIÑO RATA");
                        System.out.println("Eres una desgracia para ti y para toda tu familia");
                        System.out.println("Largo de mi puta posada y con ello expulsado de la ciudad. Muerete subnormal.");
                        System.out.println("GAME OVER");
                        System.out.println("Consejo: Nunca cabrees a tu posadero de confianza :)");
                        salir = true;
                        break;
                    }
                    else
                        System.out.println("jajaja, muy gracioso, muy gracioso... volvere a repetirlo");

                    counter++;
            }
        }

    }

    private static String zonaSeleccionada(Scanner sc) {
        ArrayList<ZoneMap> zonas = CreateZoneMap.zonas();
        for (int i = 0; i < zonas.size(); i++) {
            System.out.println((i + 1) + ". " + zonas.get(i).getNombre());
        }
        int zonaElegida = sc.nextInt();
        sc.nextLine();
        return zonas.get(zonaElegida - 1).getNombre();
    }

    private static void misionesDisponibles(Hero hero, String zona, Scanner sc) {
        System.out.println("Estas son las misiones disponibles:");
        ArrayList<Mision> misionDisponible = listaMisionesActuales(hero, zona);

        for (Mision mision : misionDisponible) {
            System.out.println(mision.getNombre() + ": " + mision.getDescripcion() + " | Rango: " + mision.getRango() + " | Recompensa: " + mision.getRecompensa() + " de oro/s");
        }

        System.out.println("Escoge una mision segun su rango");

        String opcion = sc.next().toUpperCase();
        sc.nextLine();

        activarMision(hero, misionDisponible, zona, opcion);

        System.out.println("Viajando al mapa: " + zona);

        Overworld.explorarZona(hero, zona, sc);
    }

    private static ArrayList<Mision> listaMisionesActuales(Hero hero, String zona) {
        ArrayList<Mision> misions = CreateMision.listadoMisiones();
        ArrayList<Mision> listaMisionesActuales = new ArrayList<>();

        for (Mision mision : misions) {
            if (mision.getZona().equals(zona)) {
                if (hero.getLevel() <= 5 && mision.getRango().equals("C")) {
                    listaMisionesActuales.add(mision);
                } else if (hero.getLevel() > 5 && hero.getLevel() <= 10 && (mision.getRango().equals("B") || mision.getRango().equals("C"))) {
                    listaMisionesActuales.add(mision);
                } else if (hero.getLevel() > 10 && (mision.getRango().equals("A") || mision.getRango().equals("B") || mision.getRango().equals("C"))) {
                    listaMisionesActuales.add(mision);
                }
            }
        }

        return listaMisionesActuales;

    }

    private static void activarMision(Hero hero, ArrayList<Mision>lista, String zona, String opcion) {
        for(Mision mision: lista){
            if(mision.getZona().equals(zona) && mision.getRango().equals(opcion)){
                hero.setMisionActual(mision);
            }
        }

        entregaPociones(hero);

    }

    private static void entregaPociones(Hero hero) {
        ItemsHero ayuda = new ItemsHero(1, "Pocion de vida", "Recupera 50 puntos de vida", 0);

        System.out.println("Toma esto... te ayudara");

        if (hero.getMisionActual().getRango().equals("C"))
            hero.addInventory(ayuda,2);
        else if (hero.getMisionActual().getRango().equals("B"))
            hero.addInventory(ayuda,4);
        else
            hero.addInventory(ayuda,6);

        System.out.println("Se han añadido " +ayuda.getNombre() + " a tu inventario.");
    }

    private static void entregaMision(Hero hero) {
        System.out.println("Vamos a ver que tenemos");
        String item = hero.getMisionActual().getNombreItem();
        int cantidad = hero.getMisionActual().getCantidad();

        boolean misionCompletada= (Hero.buscarObjetoMision(hero, item, cantidad));
        if (misionCompletada){
            hero.addMision(hero.getMisionActual());
            hero.setMisionActual(null);
        }

    }

    private static void abrirTienda(Hero hero, Scanner sc) {
        ArrayList<Tienda>tiendas = CreateItemsTienda.tienda();

        for(Tienda tienda: tiendas){
            System.out.println(tienda);
        }

        System.out.println("Saldo: "+ hero.getMoney());
        System.out.println("Deseas comprar algo?\n1. Si\n2. No");

        int opcion = sc.nextInt();
        sc.nextLine();

        if(opcion==1){
            System.out.println("Que deseas comprar? Introduce el numero del item");
            int itemNombre = sc.nextInt();
            System.out.println("Cuantos quieres?");
            int cantidad = sc.nextInt();
            sc.nextLine();
            comprar(itemNombre, cantidad, tiendas, hero);
        }else
            System.out.println("Hasta la proxima");

    }

    private static void comprar(int item, int cantidad, ArrayList<Tienda>tiendas, Hero hero) {
        for (Tienda tienda: tiendas){
            if(item == tienda.getId()){
                int suma = tienda.getCoste()*cantidad;
                if (suma <= hero.getMoney()){
                    hero.addInventory(tienda,cantidad);
                    hero.setMoney(hero.getMoney()-suma);
                }else{
                    System.out.println("No tienes suficiente dinero");
                    System.out.println("Coste de compra: "+suma);
                    System.out.println("Saldo actual: "+ hero.getMoney());
                }
            }else
                System.out.println("Codigo introducido incorrecto");
        }
    }

    private static void abrirHerrero(Hero hero, Scanner sc) {
        String secretItem = "SHINE ROCKS";

        if(Hero.buscarObjetoBool(hero, secretItem)){
            eventoHerreroEspecial(secretItem, hero);
        }
        else if(hero.getEquipamiento()==Calidad.EPIC){
            System.out.println("Tu equipamiento esta al tope.");
        }else {
            System.out.println("Vamos a mejorar tu equipamiento");
            System.out.println("Tu saldo es: "+hero.getMoney() + " oros");
            System.out.println("Tu equipamiento actualmente esta en calidad: "+ hero.getEquipamiento());

            mejorarEquipamiento(hero,sc);
        }

    }

    private static void mejorarEquipamiento(Hero hero, Scanner sc) {
        int precio;
        if(hero.getEquipamiento()==Calidad.RARE){
            precio = 50;
            System.out.println("Para mejorar tu armadura a: " +Calidad.EPIC + " son " +precio+ " oros");
            System.out.println("Deseas deseas mejorarlo?\n1. Si\n2. No");

            int opcion = sc.nextInt();
            sc.nextLine();

            if(opcion==1){
                if(hero.getMoney()>=precio){
                    hero.setEquipamiento(Calidad.EPIC);
                }else
                    System.out.println("No tienes suficiente dinero");
            }else
                System.out.println("Hasta la proxima");

        }else{
            precio = 25;
            System.out.println("Para mejorar tu armadura a: " +Calidad.RARE + " son " +precio+ " oros");
            System.out.println("Deseas deseas mejorarlo?\n1. Si\n2. No");

            int opcion = sc.nextInt();
            sc.nextLine();

            if(opcion==1){
                if(hero.getMoney()>=precio){
                    hero.setEquipamiento(Calidad.RARE);
                }else
                    System.out.println("No tienes suficiente dinero");
            }else
                System.out.println("Hasta la proxima");
        }
    }

    private static void eventoHerreroEspecial(String secretItem, Hero hero) {
        System.out.println("Shiny Shiny... Give me that BIIIIIIIIIIITCH");
        hero.removeInventory(Hero.buscarObjeto(hero, secretItem));
        hero.setEquipamiento(Calidad.LEGEND);
        herreroLoco = true;
        System.out.println("Tu equipamiento ha sido mejorado a calidad: LEGENDARIA");
        System.out.println("El herrero te expulsa de una patada");
    }

    private static void dormir(Hero hero, int coste) {
        if(hero.getMoney() >= coste){
            System.out.println("Que pases buena noche");
            hero.setLifeAcual(hero.getLifeMax());
            System.out.println("Tu vida se ha restaurado");
        }
        else
            System.out.println("No tienes suficiente dinero");
    }

    private static void eventoBossSecreto(Hero hero, Enemy enemy, Scanner sc) {
        System.out.println("MIUA MIUA ****");
        Overworld.Boss(hero, enemy, sc);
        System.out.println("GG has terminado el juego");
    }


}
