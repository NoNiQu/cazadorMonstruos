package enemy;

import java.util.ArrayList;

public class CreateEnemy {
        public static ArrayList<Enemy> Bestiary(){
            ArrayList<Enemy> enemies = new ArrayList<>();

            //Prado

            enemies.add(new Enemy(1,"C", 10, 10,"Jabali Salvaje", 4, 0,15, 10,"Carne de Jabali","PRADO", false));
            enemies.add(new Enemy(2,"B",10,10,"Halconcillo", 4, 0,15, 15,"Plumas de Halcon","PRADO", false));
            enemies.add(new Enemy(3,"A",10,10,"Grifo Dorado", 4, 0,15, 20,"Alas de Grifo Dorado","PRADO", false));
            enemies.add(new Enemy(10,"A", 10, 10,"Halcon real", 4, 0,15,50,"Cabeza de Halcon Real","PRADO", true));

            //Bosque

            enemies.add(new Enemy(4,"C",10,10,"Ciervo Fantasma", 4, 0,15,10,"Carne de Jabali","BOSQUE",false));
            enemies.add(new Enemy(5,"B",10,10,"Lobosombra", 4, 0,15, 15,"Plumas de Halcon","BOSQUE",false));
            enemies.add(new Enemy(6,"A",10,10,"Driade corrupta", 4, 0,15,20,"Alas de Grifo Dorado","BOSQUE",false));
            enemies.add(new Enemy(11,"A", 10, 10,"Hidra de Hojas", 4, 0,15,50,"Cabeza de Halcon Real","BOSQUE", true));

            //Cueva

            enemies.add(new Enemy(7,"C",10,10,"Salamandra Oscura", 4, 0,15, 10,"Carne Jabali","CUEVA",false));
            enemies.add(new Enemy(8,"B",10,10,"Murcielago Gigante", 4, 0,15, 15,"Plumas de Halcon","CUEVA",false));
            enemies.add(new Enemy(9,"A",10,10,"Escarabajo Luminoso", 4, 0,15,20,"Alas de Grifo Dorado", "CUEVA",false));
            enemies.add(new Enemy(12,"A", 10,10, "Golem de Cristal", 4, 0,15,50,"Cabeza de Halcon Real","CUEVA", true));

            return enemies;
        }
}
