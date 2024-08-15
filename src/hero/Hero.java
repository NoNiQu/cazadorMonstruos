package hero;

import taberna.Mision;
import taberna.Tienda;
import java.util.ArrayList;

public class Hero {
    private String name;
    private int level;
    private int nextlvl;
    private int lvlMax;
    private int experencie;
    private int lifeAcual;
    private int lifeMax;
    private int dmg;
    private int def;
    private int speed;
    private int money;
    private Calidad equipamiento;
    private ArrayList<ItemsHero>inventory;
    private Mision misionActual;
    private ArrayList<Mision>misionesCompletas;

    public Hero(String name, int level, int nextlvl, int lvlMax, int experencie, int lifeAcual, int lifeMax, int dmg, int def, int speed, int money, Calidad equipamiento, ArrayList<ItemsHero> inventory, Mision misionActual, ArrayList<Mision> misionesCompletas) {
        this.name = name;
        this.level = level;
        this.nextlvl = nextlvl;
        this.lvlMax = lvlMax;
        this.experencie = experencie;
        this.lifeAcual = lifeAcual;
        this.lifeMax = lifeMax;
        this.dmg = dmg;
        this.def = def;
        this.speed = speed;
        this.money = money;
        this.equipamiento = equipamiento;
        this.inventory = inventory;
        this.misionActual = misionActual;
        this.misionesCompletas = misionesCompletas;
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

    public int getNextlvl() {
        return nextlvl;
    }

    public void setNextlvl(int nextlvl) {
        this.nextlvl = nextlvl;
    }

    public int getLvlMax() {
        return lvlMax;
    }

    public void setLvlMax(int lvlMax) {
        this.lvlMax = lvlMax;
    }

    public int getExperencie() {
        return experencie;
    }

    public void setExperencie(int experencie) {
        this.experencie = experencie;
    }

    public int getLifeAcual() {
        return lifeAcual;
    }

    public void setLifeAcual(int lifeAcual) {
        this.lifeAcual = lifeAcual;
    }

    public int getLifeMax() {
        return lifeMax;
    }

    public void setLifeMax(int lifeMax) {
        this.lifeMax = lifeMax;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
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

    public Calidad getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(Calidad equipamiento) {
        this.equipamiento = equipamiento;
    }

    public ArrayList<ItemsHero> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<ItemsHero> inventory) {
        this.inventory = inventory;
    }

    public Mision getMisionActual() {
        return misionActual;
    }

    public void setMisionActual(Mision misionActual) {
        this.misionActual = misionActual;
    }

    public ArrayList<Mision> getMisionesCompletas() {
        return misionesCompletas;
    }

    public void setMisionesCompletas(ArrayList<Mision> misionesCompletas) {
        this.misionesCompletas = misionesCompletas;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", nextlvl=" + nextlvl +
                ", lvlMax=" + lvlMax +
                ", experencie=" + experencie +
                ", lifeAcual=" + lifeAcual +
                ", lifeMax=" + lifeMax +
                ", dmg=" + dmg +
                ", def=" + def +
                ", speed=" + speed +
                ", money=" + money +
                ", equipamiento=" + equipamiento +
                ", inventory=" + inventory +
                ", misionActual=" + misionActual +
                ", misionesCompletas=" + misionesCompletas +
                '}';
    }

    //OTRAS FUNCIONES

    public void addMision(Mision mision){
        misionesCompletas.add(mision);
    }

    public static ItemsHero buscarObjeto(Hero hero, String item) {

        for(ItemsHero itemsHero : hero.getInventory()){
            if(itemsHero.getNombre().equals(item)){
                return itemsHero;
            }
        }

        return null;
    }

    public static boolean buscarObjetoMision(Hero hero, String item, int cantidad) {

        boolean completada = false;

        for(ItemsHero itemsHero : hero.getInventory()){
            if(itemsHero.getNombre().equals(item)){
                if (itemsHero.getCantidad()==cantidad){
                    hero.menosCantidad(itemsHero,cantidad);
                    completada = true;
                }
            }
        }
        return completada;
    }

    public static boolean buscarObjetoBool(Hero hero, String item) {
        boolean completada = false;

        for(ItemsHero itemsHero : hero.getInventory()){
            if (itemsHero.getNombre().equals(item)) {
                completada = true;
                break;
            }
        }
        return completada;
    }

    public void addInventory(ItemsHero taberna, int cantidad){
        for(ItemsHero itemsHero: inventory){
            if(itemsHero.getNombre().equals(taberna.getNombre())){
                itemsHero.setCantidad(itemsHero.getCantidad()+cantidad);
            }else
                inventory.add(new ItemsHero(inventory.size()+1, taberna.getNombre(), taberna.getDescripcion(), cantidad));
        }
    }

    public void addInventory(Tienda tienda, int cantidad){
        for(ItemsHero itemsHero: inventory){
            if(itemsHero.getNombre().equals(tienda.getNombre())){
                itemsHero.setCantidad(itemsHero.getCantidad()+cantidad);
            }else
                inventory.add(new ItemsHero(inventory.size()+1, tienda.getNombre(), tienda.getDescripcion(), cantidad));
        }
    }
    public void menosCantidad(ItemsHero itemsHero){
        itemsHero.setCantidad(itemsHero.getCantidad()-1);
        if(itemsHero.getCantidad()==0){
            removeInventory(itemsHero);
        }
    }
    public void menosCantidad(ItemsHero itemsHero, int cantidad){
        itemsHero.setCantidad(itemsHero.getCantidad()-cantidad);
        if(itemsHero.getCantidad()==0){
            removeInventory(itemsHero);
        }
    }
    public void removeInventory(ItemsHero itemsHero){
        inventory.remove(itemsHero);
    }

    public void calidad(Calidad objeto){
        if (objeto == Calidad.LEGEND){
            dmg += 100;
            def += 100;
        }
        else if (objeto == Calidad.EPIC){
            dmg += 50;
            def += 50;
        }
        else if (objeto == Calidad.RARE){
            dmg += 25;
            def += 25;
        }
        else{
            dmg += 10;
            def += 10;
        }
    }

    public void subirNivel() {
        int subidaEstadistica = 10;
        int subidaNivel = 100;

        experencie -= nextlvl;
        nextlvl += subidaNivel;
        level++;

        lifeMax += subidaEstadistica;
        dmg += subidaEstadistica;
        def += subidaEstadistica;
        speed += subidaEstadistica;

    }
}
