package proyectoEstructuras.rutas;

import listas.ListaDoble;
import listas.NodoDoble;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Rutas{

    private Scanner scanner = new Scanner(System.in);
    private ListaDoble recorrido = new ListaDoble();

    //Nodo inicio, final, siguiente, anterior
    //Ruta es un tipo de ListaDoble
    public String setNombreParada(){
        System.out.println("Ingrese el nombre de la nueva parada: ");
        return leerDato();
    }

     public void agregarParadaAlFinal(){
        recorrido.insertaFinal(setNombreParada()); //se utiliza la función ya creada y se le pasa el nombre del dato.
         System.out.println("[!] La parada se agregó correctamente...");
    }

    public void agregarParadaEntreRutas(){
         //recibir nombre de la nueva Parada
        NodoDoble nuevaParada = new NodoDoble(); //Se le asigna un nuevo dato a la nueva parada

         //Tres casos 1. Lista vacía 2. Solo un elemento 3. Encontrar Nodo.
        //caso 1.
        if(recorrido.vacio()){
            saltoDeLinea();
            System.out.println("[!] La lista está vacía: ");
            nuevaParada.setDato(setNombreParada());
            recorrido.insertaFinal(nuevaParada.getDato());
            return;
        } else {
            saltoDeLinea();
            System.out.println("[!] El recorrido tiene una o más paradas...");
            nuevaParada.setDato(setNombreParada());
            System.out.println("Parada de referencia: ");

            NodoDoble referencia = new NodoDoble(leerDato()); //se lee el dato SOLO UNA VEZ

            if(buscar(referencia.getDato().toString()) != null){ //si el elemento está en la lista
                menuParadas(nuevaParada, referencia);
            }else{
                saltoDeLinea();
                System.out.println("[!] La parada no existe");
            }

        }
    }

    public void menuParadas(NodoDoble nuevaParada, NodoDoble paradaBuscada){
        System.out.println("[1] Agregar nueva parada antes de " + paradaBuscada.getDato());
        System.out.println("[2] Agregar nueva parada después de " + paradaBuscada.getDato());
        int op;
        op = leerOpcion();

        NodoDoble referencia = buscar(paradaBuscada.getDato().toString());

        switch (op){
            case 1:
                agregarAntes(referencia, nuevaParada);
                break;
            case 2:
                agregarDespues(referencia, nuevaParada);
                break;
            default:
                saltoDeLinea();
                System.out.println("[!] Opción inválida");
        }
    }

    public void agregarAntes(NodoDoble referencia, NodoDoble nueva){

            if(referencia != null){ //si la referencia existe


               nueva.setAnterior(referencia.getAnterior());
               nueva.setSiguiente(referencia);

               if(referencia.getAnterior() != null){ //si hay algo antes de la referencia
                   referencia.getAnterior().setSiguiente(nueva); //actualizamos el anterior de la referencia
               }else if (referencia.getAnterior() == null){ //si la referencia era el primer nodo, SE DEBE ACTUALIZAR INICIO
                   recorrido.setInicio(nueva);
               }
                referencia.setAnterior(nueva);
                recorrido.imprimir();
            }
    }

    public void agregarDespues(NodoDoble referencia, NodoDoble nueva){
        if(referencia != null){ //si la referencia existe
            nueva.setSiguiente(referencia.getSiguiente());
            nueva.setAnterior(referencia);
            if(referencia.getSiguiente() != null){
                referencia.getSiguiente().setAnterior(nueva);
            }else{//si referencia era el último, se actualiza ULTIMO
                recorrido.setUltimo(nueva);
            }
            referencia.setSiguiente(nueva);
        }
    }

    public NodoDoble buscar(String paradaBuscada){
        //Buscará el dato dentro del recorrido y devolverá el nodo que busco, con sus apuntadores
        //Nodo auxiliar

        NodoDoble nodoAuxiliar = (NodoDoble) recorrido.getInicio();
        while(nodoAuxiliar != null){
            if(paradaBuscada.equals(nodoAuxiliar.getDato())){ //si se cumple la igualdad, entonces devolvemos el nodo
                return nodoAuxiliar;
            }
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        return null;
    }

    public void menuEliminarParada(){

        System.out.println("[1] Eliminar primera parada");
        System.out.println("[2] Eliminar última parada");
        System.out.println("[3] Eliminar por nombre");

        int op = leerOpcion();

        switch(op){

            case 1:
                if(recorrido.getInicio() == null){
                    saltoDeLinea();
                    System.out.println("[!] No hay paradas en la ruta.");
                }else
                    eliminarParadaPorNombre(recorrido.getInicio().getDato().toString());
                break;

            case 2:
                if(recorrido.getUltimo() == null){
                    saltoDeLinea();
                    System.out.println("[!] No hay paradas en la ruta.");
                }else
                    eliminarParadaPorNombre(recorrido.getUltimo().getDato().toString());
                break;

            case 3:
                if(recorrido.vacio()){
                    saltoDeLinea();
                    System.out.println("[!] No hay paradas en la ruta.");
                }else {
                    System.out.println("Ingrese parada a eliminar:");
                    eliminarParadaPorNombre(leerDato());
                }
                break;

            default:
                System.out.println("[!] Opción inválida");
        }
    }

    public void eliminarParadaPorNombre(String nombre){

        if(recorrido.vacio()){
            saltoDeLinea();
            System.out.println("[!] No hay paradas en la ruta.");
            return;
        }

        NodoDoble nodoEliminar = buscar(nombre);

        if(nodoEliminar == null){
            saltoDeLinea();
            System.out.println("[!] La parada no existe.");
            return;
        }

        if(recorrido.getInicio() == recorrido.getUltimo()){
            recorrido.setInicio(null);
            recorrido.setUltimo(null);
        }

        else if(nodoEliminar == recorrido.getInicio()){
            recorrido.setInicio(nodoEliminar.getSiguiente());
            ((NodoDoble) recorrido.getInicio()).setAnterior(null); //se debe castear para tener la funcion setAnterior
        }

        else if(nodoEliminar == recorrido.getUltimo()){
            recorrido.setUltimo(nodoEliminar.getAnterior());
            recorrido.getUltimo().setSiguiente(null);
        }

        else{
            nodoEliminar.getAnterior().setSiguiente(nodoEliminar.getSiguiente());
            nodoEliminar.getSiguiente().setAnterior(nodoEliminar.getAnterior());
        }
        saltoDeLinea();
        System.out.println("[!] Parada eliminada correctamente.");
    }

    public void recorrerParadas() {

        if (recorrido.getInicio() == null) {
            saltoDeLinea();
            System.out.println("[!] No hay paradas en la ruta.");
            return;
        }

        NodoDoble actual = recorrido.getInicio();
        int opcion;

        do {
            saltoDeLinea();
            System.out.println("\n=== Navegación de Paradas ===");
            System.out.println("Parada actual: " + actual.getDato());
            System.out.println("[1] Ir a la siguiente parada");
            System.out.println("[2] Ir a la parada anterior");
            System.out.println("[3] Salir");
            System.out.print("Seleccione opción: ");

            opcion = leerOpcion();

            switch (opcion) {

                case 1:
                    if (actual.getSiguiente() != null) {
                        actual = actual.getSiguiente();
                    } else {
                        saltoDeLinea();
                        System.out.println("[!] Ya estás en la última parada.");
                    }
                    break;

                case 2:
                    if (actual.getAnterior() != null) {
                        actual = actual.getAnterior();
                    } else {
                        saltoDeLinea();
                        System.out.println("[!] Ya estás en la primera parada.");
                    }
                    break;

                case 3:
                    saltoDeLinea();
                    System.out.println("[!] Saliendo simulación de recorrido...");
                    break;

                default:
                    saltoDeLinea();
                    System.out.println("[!] Opción inválida.");
            }

        } while (opcion != 3);
    }

    private int leerOpcion(){
        int opcion;
        try {
            opcion = scanner.nextInt();
            System.out.println("[!] Presione enter...");
            scanner.nextLine(); //limpiar buffer
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            opcion = -1;
        }
        return opcion;
    }

    private String leerDato(){
        String dato;

        do{
            dato = scanner.nextLine().trim();
            if(dato.isEmpty()){
                System.out.println("[!] No puedes dejar el campo vacío. Intenta otra vez:");
            }

        }while(dato.isEmpty());

        return dato;
    }

    public void saltoDeLinea(){
        for( int i = 0; i<4; i++){
            System.out.println();
        }
    }


    //OVERRIDE FUNCTIONS
    public static void main(String[] args) {
        Rutas ruta = new Rutas();
        Scanner scanner1 = new Scanner(System.in);
        ruta.agregarParadaAlFinal();
        ruta.agregarParadaEntreRutas();
        ruta.recorrido.imprimir();

        ruta.recorrerParadas();

    }

}
