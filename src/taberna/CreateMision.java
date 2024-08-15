package taberna;

import java.util.ArrayList;

public class CreateMision {
    public static ArrayList<Mision>listadoMisiones(){

        ArrayList<Mision>misions=new ArrayList<>();

        misions.add(new Mision("PRADO","C", "Buscando provisiones", "Consigue carne de Jabali salvaje", 6, "Carne de jabali",10));
        misions.add(new Mision("PRADO","B", "Control de paglas", "Consigue cabeza de Halconcillo", 20, "Plumas de halconcillo", 15));
        misions.add(new Mision("PRADO","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado", 1, "Cabeza de Grifo Dorado", 25));
        misions.add(new Mision("PRADO","S", "Premio de la corona", "Matar al Halcon Real para la corona", 1, "Cabeza de Halcon real",50));

        misions.add(new Mision("BOSQUE","C", "Buscando provisiones", "Consigue carne de Libre del padro", 10, "Carne de conejo", 10));
        misions.add(new Mision("BOSQUE","B", "Control de paglas", "Consigue cabeza de Halconcillo", 20, "Plumas de halconcillo", 15));
        misions.add(new Mision("BOSQUE","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado", 1, "Cabeza de Grifo Dorado", 25));
        misions.add(new Mision("BOSQUE","S", "Premio de la corona", "Matar al Halcon Real para la corona", 1, "Cabeza de Halcon real",50));

        misions.add(new Mision("CUEVA","C", "Buscando provisiones", "Consigue carne de Libre del padro", 10, "Carne de conejo", 10));
        misions.add(new Mision("CUEVA","B", "Control de paglas", "Consigue cabeza de Halconcillo", 20, "Plumas de halconcillo", 15));
        misions.add(new Mision("CUEVA","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado", 1, "Cabeza de Grifo Dorado", 25));
        misions.add(new Mision("CUEVA","S", "Premio de la corona", "Matar al Halcon Real para la corona", 1, "Cabeza de Halcon real",50));


        return misions;
    }

    public static ArrayList<Mision> listaMisiones(){
        ArrayList<Mision> listaMisiones = new ArrayList<>();
        return listaMisiones;
    }

}
