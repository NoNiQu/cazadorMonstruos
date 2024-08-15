package taberna;

import base.Items;

public class Tienda extends Items {
    private int coste;

    public Tienda(int id, String nombre, String descripcion, int coste) {
        super(id, nombre, descripcion);
        this.coste = coste;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "- "+  getId()  + getNombre() + " | " +
                getDescripcion() + " | " +
                 coste + " oros";
    }
}
