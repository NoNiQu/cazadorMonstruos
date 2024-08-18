package saveCharge;

import base.Items;
import hero.Calidad;
import hero.Hero;
import taberna.Mision;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SaveChargeFile {

    public static void guardarDatos(String rutaArchivo, Hero hero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("name=" + hero.getName() + "\n");
            writer.write("level=" + hero.getLevel() + "\n");
            writer.write("levelMax=" + hero.getLevelMax() + "\n");
            writer.write("exp=" + hero.getExp() + "\n");
            writer.write("expNext=" + hero.getExpNext() + "\n");
            writer.write("life=" + hero.getLife() + "\n");
            writer.write("lifeMax=" + hero.getLifeMax() + "\n");
            writer.write("attack=" + hero.getAttack() + "\n");
            writer.write("defense=" + hero.getDefense() + "\n");
            writer.write("speed=" + hero.getSpeed() + "\n");
            writer.write("money=" + hero.getMoney() + "\n");
            writer.write("tipoCalidad=" + hero.getTipoCalidad().toString() + "\n");

            writer.write("inventorySize=" + Hero.getInventory().size() + "\n");
            for (Items item : Hero.getInventory().values()) {
                writer.write("item=" + item.getNombre() + "," + item.getCantidadCoste() + "\n");
            }

            if (hero.getMisionActual() != null) {
                writer.write("misionActual=" + hero.getMisionActual().toString() + "\n");
            } else {
                writer.write("misionActual=null\n");
            }

            writer.write("misionesCompletadasSize=" + hero.getMisionesCompletadas().size() + "\n");
            for (Mision mision : hero.getMisionesCompletadas()) {
                writer.write("misionCompletada=" + mision.toString() + "\n");
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Hero cargarDatos(String rutaArchivo) {
        Hero hero = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            HashMap<String, Items> inventory = new HashMap<>();
            String name = "";
            int level = 0, levelMax = 0, exp = 0, expNext = 0, life = 0, lifeMax = 0, attack = 0, defense = 0, speed = 0, money = 0;
            Calidad tipoCalidad = Calidad.STANDARD;
            Mision misionActual = null;
            ArrayList<Mision> misionesCompletadas = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length < 2) continue;

                switch (parts[0]) {
                    case "name":
                        name = parts[1];
                        break;
                    case "level":
                        level = Integer.parseInt(parts[1]);
                        break;
                    case "levelMax":
                        levelMax = Integer.parseInt(parts[1]);
                        break;
                    case "exp":
                        exp = Integer.parseInt(parts[1]);
                        break;
                    case "expNext":
                        expNext = Integer.parseInt(parts[1]);
                        break;
                    case "life":
                        life = Integer.parseInt(parts[1]);
                        break;
                    case "lifeMax":
                        lifeMax = Integer.parseInt(parts[1]);
                        break;
                    case "attack":
                        attack = Integer.parseInt(parts[1]);
                        break;
                    case "defense":
                        defense = Integer.parseInt(parts[1]);
                        break;
                    case "speed":
                        speed = Integer.parseInt(parts[1]);
                        break;
                    case "money":
                        money = Integer.parseInt(parts[1]);
                        break;
                    case "tipoCalidad":
                        tipoCalidad = Calidad.valueOf(parts[1]);
                        break;
                    case "inventorySize":
                        int inventorySize = Integer.parseInt(parts[1]);
                        for (int i = 0; i < inventorySize; i++) {
                            String itemLine = reader.readLine();
                            Items item = new Items(itemLine);
                            inventory.put(item.getNombre(), item);
                        }
                        break;
                    case "misionActual":
                        String misionLine = parts[1]; // Se cambia la asignación de la línea directamente desde parts[1]
                        if (!misionLine.equals("null")) {
                            misionActual = new Mision(misionLine);
                        }
                        break;
                    case "misionesCompletadasSize":
                        int misionesSize = Integer.parseInt(parts[1]);
                        for (int i = 0; i < misionesSize; i++) {
                            String misionCompletadaLine = reader.readLine().split("=")[1]; // Asegura que solo se asigna el valor de la misión
                            Mision misionCompletada = new Mision(misionCompletadaLine);
                            misionesCompletadas.add(misionCompletada);
                        }
                        break;
                }
            }

            hero = new Hero(name, level, levelMax, exp, expNext, life, lifeMax, attack, defense, speed, money, tipoCalidad, misionActual);
            hero.setInventory(inventory);
            hero.setMisionesCompletadas(misionesCompletadas);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero;
    }


}
