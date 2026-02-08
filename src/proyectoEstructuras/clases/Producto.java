package proyectoEstructuras.clases;

public class Producto {

    private int id;
    private int peso;
    private String nombre;

    public Producto(int id, String nombre, int peso) {
        this.id = id;
        this.peso = peso;
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }

    public void verProducto (){
        System.out.printf("%d %s %d", id, nombre, peso);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
