package enemy;

import java.util.ArrayList;

public class CreateEnemy {

        public static ArrayList<Enemy> Bestiary(){
            ArrayList<Enemy> enemies = new ArrayList<>();

            //Prado

            enemies.add(new Enemy(1,"C", 30, 30,"Jabali Salvaje", 10, 5,15, 10,"Carne de Jabali","PRADO", false));
            enemies.add(new Enemy(2,"B",50,50,"Halconcillo", 15, 10,20, 15,"Plumas de Halcon","PRADO", false));
            enemies.add(new Enemy(3,"A",80,80,"Grifo Dorado", 20, 15,25, 30,"Alas de Grifo Dorado","PRADO", false));
            enemies.add(new Enemy(10,"A", 150, 150,"Halcon real", 25, 20,30,150,"Cabeza de Halcon Real","PRADO", true));

            //Bosque

            enemies.add(new Enemy(4,"C",30, 30,"Ciervo Fantasma", 10, 5,15, 10,"Cornamenta espectral","BOSQUE",false));
            enemies.add(new Enemy(5,"B",50,50,"Lobosombra", 15, 10,20, 15,"Piel de lobo","BOSQUE",false));
            enemies.add(new Enemy(6,"A",80,80,"Driade corrupta", 20, 15,25, 30,"Sangre corrupta","BOSQUE",false));
            enemies.add(new Enemy(11,"A", 150, 150,"Hidra de Hojas", 25, 20,30,150,"Hojas de Hidra","BOSQUE", true));

            //Cueva

            enemies.add(new Enemy(7,"C",30, 30,"Salamandra Oscura", 10, 5,15, 10,"Cola de Salamandra","CUEVA",false));
            enemies.add(new Enemy(8,"B",50,50,"Murcielago Gigante", 15, 10,20, 15,"Alas de murcielago","CUEVA",false));
            enemies.add(new Enemy(9,"A",80,80,"Escarabajo Luminoso", 20, 15,25, 30,"Shiny rocks", "CUEVA",false));
            enemies.add(new Enemy(12,"A", 150, 150, "Golem de Cristal", 25, 20,30,150,"Corazon de cristal","CUEVA", true));

            return enemies;
        }
}
