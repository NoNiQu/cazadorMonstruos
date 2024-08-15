package enemy;

public class Enemy {
    private int id;
    private String rank;
    private int life;
    private int maxLife;
    private String name;
    private int dmg;
    private int def;
    private int speed;
    private int dropExperience;
    private String dropItem;
    private String zone;
    private boolean boss;

    public Enemy(int id, String rank, int life, int maxLife, String name, int dmg, int def, int speed, int dropExperience, String dropItem, String zone, boolean boss) {
        this.id = id;
        this.rank = rank;
        this.life = life;
        this.maxLife = maxLife;
        this.name = name;
        this.dmg = dmg;
        this.def = def;
        this.speed = speed;
        this.dropExperience = dropExperience;
        this.dropItem = dropItem;
        this.zone = zone;
        this.boss = boss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDropExperience() {
        return dropExperience;
    }

    public void setDropExperience(int dropExperience) {
        this.dropExperience = dropExperience;
    }

    public String getDropItem() {
        return dropItem;
    }

    public void setDropItem(String dropItem) {
        this.dropItem = dropItem;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                ", life=" + life +
                ", maxLife=" + maxLife +
                ", name='" + name + '\'' +
                ", dmg=" + dmg +
                ", def=" + def +
                ", speed=" + speed +
                ", dropExperience=" + dropExperience +
                ", dropItem='" + dropItem + '\'' +
                ", zone='" + zone + '\'' +
                ", boss=" + boss +
                '}';
    }
}
