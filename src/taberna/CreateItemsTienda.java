package taberna;

import java.util.ArrayList;

public class CreateItemsTienda {
    public static ArrayList<Tienda> tienda(){
        ArrayList<Tienda>objetoComprar=new ArrayList<>();

        objetoComprar.add(new Tienda(1, "Pocion de vida pequeña", "Recupera 25 puntos de vida", 3));
        objetoComprar.add(new Tienda(2, "Frasco incendario debil", "Hace 50 de daño de fuego a los enemigos", 5));
        objetoComprar.add(new Tienda(3, "Frasco nitrogeno debil", "Hace 50 de daño de hielo a los enemigos", 5));
        objetoComprar.add(new Tienda(4, "Pocion de vida", "Recupera 50 puntos de vida", 8));
        objetoComprar.add(new Tienda(5, "Frasco incendario", "Hace 50 de daño de fuego a los enemigos", 10));
        objetoComprar.add(new Tienda(6, "Frasco nitrogeno", "Hace 50 de daño de hielo a los enemigos", 10));
        objetoComprar.add(new Tienda(7, "Pocion grande de vida", "Recupera 100 puntos de vida", 18));
        objetoComprar.add(new Tienda(8, "Gran frasco incendario", "Hace 50 de daño de fuego a los enemigos", 20));
        objetoComprar.add(new Tienda(9, "Gran frasco nitrogeno", "Hace 50 de daño de hielo a los enemigos", 20));

        return objetoComprar;
    }
}
