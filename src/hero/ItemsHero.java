package hero;

import base.Items;
import java.util.ArrayList;

public class ItemsHero extends Items {
    private int cantidad;

    public ItemsHero(int id, String nombre, String descripcion, int cantidad) {
        super(id, nombre, descripcion);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  "- "+ getId() + getNombre() + " | " +
                getDescripcion() + " | " +
                cantidad;
    }

    public static ArrayList<ItemsHero> Inventario(){
        ArrayList<ItemsHero> inventory = new ArrayList<>();
        return inventory;
    }

}
