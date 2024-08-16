package hero;

import base.Items;
import taberna.Mision;
import java.util.ArrayList;
import java.util.HashMap;

public class Hero {
    private String name;
    private int level;
    private int levelMax;
    private int exp;
    private int expNext;
    private int life;
    private int lifeMax;
    private int attack;
    private int defense;
    private int speed;
    private int money;
    private Calidad tipoCalidad;
    private static HashMap<String, Items> inventory;
    private Mision misionActual;
    private ArrayList<String> itemMision;
    private ArrayList<Mision> misionesCompletadas;

    public Hero(String name, int level, int levelMax, int exp, int expNext, int life, int lifeMax, int attack, int defense, int speed, int money, Calidad tipoCalidad, Mision misionActual) {
        this.name = name;
        this.level = level;
        this.levelMax = levelMax;
        this.exp = exp;
        this.expNext = expNext;
        this.life = life;
        this.lifeMax = lifeMax;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.money = money;
        this.tipoCalidad = tipoCalidad;
        inventory = new HashMap<>();
        this.misionActual = misionActual;
        this.itemMision = new ArrayList<>();
        this.misionesCompletadas =new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpNext() {
        return expNext;
    }

    public void setExpNext(int expNext) {
        this.expNext = expNext;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLifeMax() {
        return lifeMax;
    }

    public void setLifeMax(int lifeMax) {
        this.lifeMax = lifeMax;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Calidad getTipoCalidad() {
        return tipoCalidad;
    }

    public void setTipoCalidad(Calidad tipoCalidad) {
        this.tipoCalidad = tipoCalidad;
    }

    public static HashMap<String, Items> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Items> inventory) {
        Hero.inventory = inventory;
    }

    public Mision getMisionActual() {
        return misionActual;
    }

    public void setMisionActual(Mision misionActual) {
        this.misionActual = misionActual;
    }

    public ArrayList<String> getItemMision() {
        return itemMision;
    }

    public void setItemMision(ArrayList<String> itemMision) {
        this.itemMision = itemMision;
    }

    public ArrayList<Mision> getMisionesCompletadas() {
        return misionesCompletadas;
    }

    public void setMisionesCompletadas(ArrayList<Mision> misionesCompletadas) {
        this.misionesCompletadas = misionesCompletadas;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", levelMax=" + levelMax +
                ", exp=" + exp +
                ", expNext=" + expNext +
                ", life=" + life +
                ", lifeMax=" + lifeMax +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ", money=" + money +
                ", tipoCalidad=" + tipoCalidad +
                ", inventory=" + inventory +
                ", misionActual=" + misionActual +
                ", itemMision=" + itemMision +
                ", misionesCompletadas=" + misionesCompletadas +
                '}';
    }


    //OTRAS FUNCIONES

    public void addMision(Mision mision) {
        misionesCompletadas.add(mision);
    }

    public static boolean buscarObjetoMision(ArrayList<String> buscador, String item) {
        return buscador.contains(item);
    }

    public void addItemMision(String item) {
        inventory.get(item);
    }

    public void removeItemMision(String item) {
        inventory.remove(item);
    }

    public static Items buscarObjeto(String itemName) {
        return inventory.get(itemName);
    }

    public static boolean buscarCurabool(boolean combat) {
        for (Items item : inventory.values()) {
            if (item.getTipoObjeto() == TipoObjeto.CURE) {
                return true;
            }
        }
        return false;
    }


    public static Items buscarCura(boolean combat) {
        for (Items item : inventory.values()) {
            if (item.getTipoObjeto() == TipoObjeto.CURE) {
                return item;
            }
        }
        return null;
    }

    public static boolean buscarObjetoBool(String item) {
        return inventory.containsKey(item); // Busca en el HashMap
    }

    public void addInventory(Items item) {
        if (inventory.containsKey(item.getNombre())) {
            Items existingItem = inventory.get(item.getNombre());
            existingItem.setCantidadCoste(existingItem.getCantidadCoste() + item.getCantidadCoste());
        } else {
            inventory.put(item.getNombre(), item);
        }
    }

    public static void usarCura(Hero hero, Items itemCura) {
        if (itemCura.getTipoObjeto() == TipoObjeto.CURE) {
            hero.life = Math.min(hero.getLife() + itemCura.getEfecto(), hero.getLifeMax());
            menosCantidad(itemCura);
        }
    }

    public static void menosCantidad(Items item) {
        Items existingItem = inventory.get(item.getNombre());
        if (existingItem != null) {
            int newQuantity = existingItem.getCantidadCoste() - 1;
            if (newQuantity <= 0) {
                removeInventory(item);
            } else {
                existingItem.setCantidadCoste(newQuantity);
            }
        }
    }

    public static void removeInventory(Items item) {
        inventory.remove(item.getNombre());
    }

    public void calidad(Calidad objeto) {
        switch (objeto) {
            case STANDARD:
                attack += 5;
                defense += 5;
                lifeMax += 10;
                break;
            case RARE:
                attack += 10;
                defense += 10;
                lifeMax += 20;
                break;
            case EPIC:
                attack += 20;
                defense += 20;
                lifeMax += 40;
                break;
            case LEGEND:
                attack += 40;
                defense += 40;
                lifeMax += 80;
                break;
        }
    }

    public void subirNivel() {
        this.level++;
        this.exp = 0;
        this.expNext += 100;
        this.lifeMax += 10;
        this.attack += 10;
        this.defense += 10;
        this.speed += 10;
        this.life = this.lifeMax;
    }
}
