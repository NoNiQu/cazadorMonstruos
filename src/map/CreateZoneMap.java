package map;

import java.util.ArrayList;

public class CreateZoneMap {
    public static ArrayList<ZoneMap>zonas(){
        ArrayList<ZoneMap>zoneMaps=new ArrayList<>();

        zoneMaps.add(new ZoneMap(1,"PRADO"));
        zoneMaps.add(new ZoneMap(2,"BOSQUE"));
        zoneMaps.add(new ZoneMap(3,"CUEVA"));

        return zoneMaps;
    }
}
