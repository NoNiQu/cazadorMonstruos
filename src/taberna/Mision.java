package taberna;

public class Mision {
    private String zona;
    private String rango;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String nombreItem;
    private int recompensa;

    public Mision(String zona, String rango, String nombre, String descripcion, int cantidad, String nombreItem, int recompensa) {
        this.zona = zona;
        this.rango = rango;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombreItem = nombreItem;
        this.recompensa = recompensa;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "zona='" + zona + '\'' +
                ", rango='" + rango + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", nombreItem='" + nombreItem + '\'' +
                ", recompensa=" + recompensa +
                '}';
    }
}
