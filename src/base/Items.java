package base;

import hero.Hero;
import hero.TipoObjeto;
import java.util.ArrayList;

public class Items {
    private String nombre;
    private TipoObjeto tipoObjeto;
    private int efecto;
    private int cantidadCoste;
    private boolean combat;

    public Items(String nombre, TipoObjeto tipoObjeto, int efecto, int cantidadCoste, boolean combat) {
        this.nombre = nombre;
        this.tipoObjeto = tipoObjeto;
        this.efecto = efecto;
        this.cantidadCoste = cantidadCoste;
        this.combat = combat;
    }

    public Items(String data) {
        String[] parts = data.split(",");
        this.nombre = parts[0];
        this.tipoObjeto = TipoObjeto.valueOf(parts[1]);
        this.efecto = Integer.parseInt(parts[2]);
        this.cantidadCoste = Integer.parseInt(parts[3]);
        this.combat = Boolean.parseBoolean(parts[4]);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoObjeto getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(TipoObjeto tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }

    public int getCantidadCoste() {
        return cantidadCoste;
    }

    public void setCantidadCoste(int cantidadCoste) {
        this.cantidadCoste = cantidadCoste;
    }

    public boolean isCombat() {
        return combat;
    }

    public void setCombat(boolean combat) {
        this.combat = combat;
    }

    public static ArrayList<Items> tienda(Hero hero){
        ArrayList<Items>objetoComprar=new ArrayList<>();

        objetoComprar.add(new Items("Pocion de vida pequeña", TipoObjeto.CURE, 25, 3, true));
        objetoComprar.add(new Items("Pocion de vida", TipoObjeto.CURE, 50,  8, true));
        objetoComprar.add(new Items("Pocion grande de vida", TipoObjeto.CURE, 100,  18,true));
        objetoComprar.add(new Items("Fogata", TipoObjeto.CURE, hero.getLifeMax()/2, 10,false));
        objetoComprar.add(new Items("Campamento", TipoObjeto.CURE, hero.getLifeMax(), 15,false));

        return objetoComprar;
    }

    @Override
    public String toString() {
        return "Items{" +
                "nombre='" + nombre + '\'' +
                ", tipoObjeto=" + tipoObjeto +
                ", efecto=" + efecto +
                ", cantidadCoste=" + cantidadCoste +
                ", combat=" + combat +
                '}';
    }
}
