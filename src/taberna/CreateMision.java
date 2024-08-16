package taberna;

import java.util.ArrayList;

public class CreateMision {
    public static ArrayList<Mision>listadoMisiones(){

        ArrayList<Mision>misions=new ArrayList<>();

        misions.add(new Mision("PRADO","C", "Buscando provisiones", "Consigue carne de Jabali salvaje", "Carne de Jabali",10));
        misions.add(new Mision("PRADO","B", "Control de paglas", "Consigue cabeza de Halconcillo",  "Plumas de Halcon", 15));
        misions.add(new Mision("PRADO","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado",  "Alas de Grifo Dorado", 25));
        misions.add(new Mision("PRADO","A", "Premio de la corona", "Matar al Halcon Real para la corona",  "Cabeza de Halcon Real",50));

        misions.add(new Mision("BOSQUE","C", "Buscando provisiones", "Consigue carne de Libre del padro",  "Cornamenta espectral", 10));
        misions.add(new Mision("BOSQUE","B", "Control de paglas", "Consigue cabeza de Halconcillo", "Piel de lobo", 15));
        misions.add(new Mision("BOSQUE","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado",  "Sangre corrupta", 25));
        misions.add(new Mision("BOSQUE","A", "Premio de la corona", "Matar al Halcon Real para la corona",  "Hojas de Hidra",50));

        misions.add(new Mision("CUEVA","C", "Buscando provisiones", "Consigue carne de Libre del padro",  "Cola de Salamandra", 10));
        misions.add(new Mision("CUEVA","B", "Control de paglas", "Consigue cabeza de Halconcillo",  "Alas de murcielago", 15));
        misions.add(new Mision("CUEVA","A", "Acaba con el Grifo Dorado", "Acaba con el depredador del prado",  "Shiny rocks", 25));
        misions.add(new Mision("CUEVA","A", "Premio de la corona", "Matar al Halcon Real para la corona",  "Corazon de cristal",50));

        return misions;
    }

}
