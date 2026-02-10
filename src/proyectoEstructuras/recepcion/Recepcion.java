package proyectoEstructuras.recepcion;

public class Recepcion extends RecepcionFunciones {

    // Registra un nuevo camion en la cola de recepción
    public Camion registro(String nombre, String placas) {
        Camion c = new Camion(nombre, placas);
        enqueue(c);
        return c;
    }

    public Camion atender() {
        return (Camion) dequeue();
    }

    public Camion verProximo() {
        return (Camion) front();
    }

    public int cantidadEnEspera() {
        return size();
    }

    // Main para pruebas
    public static void main(String[] args) {
        Recepcion r = new Recepcion();
        r.registro("Juan Perez", "ABC-123");
        r.registro("Ana Gomez", "XYZ-789");
        System.err.println("Cantidad en espera: " + r.cantidadEnEspera());
        System.out.println("Próximo: " + r.verProximo());
        System.out.println("Atendiendo: " + r.atender());
        System.out.println("Ahora próximo: " + r.verProximo());
        System.out.println("Cantidad en espera: " + r.cantidadEnEspera());
    }

}

