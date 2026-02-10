package proyectoEstructuras.recepcion;

public class Camion {
    private final String nombre;
    private final String placa;

    // Constructor para crear un nuevo camion con su nombre y placa
    public Camion(String nombre, String placa) {
        this.nombre = nombre;
        this.placa = placa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlaca() {
        return placa;
    }

    // Método para mostrar la información del camion de manera legible
    @Override
    public String toString() {
        return "Camion: Conductor = " + nombre + ", placa = " + placa;
    }
}
