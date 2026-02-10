package proyectoEstructuras.manifiesto;

import listas.ListaDoble;
import listas.Nodo;
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
        String nombreParada = leerDato();
        recorrido.insertaFinal(nombreParada); //se utiliza la función ya creada y se le pasa el nombre del dato.
    }

    public void agregarParadaEntreRutas(){
         //recibir nombre de la nueva Parada
        NodoDoble nuevaParada = new NodoDoble(); //Se le asigna un nuevo dato a la nueva parada

         //Tres casos 1. Lista vacía 2. Solo un elemento 3. Encontrar Nodo.
        //caso 1.
        if(recorrido.vacio()){
            System.out.println("[!] La lista está vacía: ");
            nuevaParada.setDato(setNombreParada());
            recorrido.insertaFinal(nuevaParada.getDato());
            return;
        } else {
            System.out.println("[!] La tiene uno o más elementos: ");
            nuevaParada.setDato(setNombreParada());
            System.out.println("Parada de referencia: ");

            NodoDoble referencia = new NodoDoble(leerDato()); //se lee el dato SOLO UNA VEZ


        //caso 2 hay más elementos
            /*if (recorrido.getInicio() == recorrido.getUltimo()) { //Caso 2. solo un elemento en la lista
                menuParadas(nuevaParada, referencia); //Decidir si se agrega antes o después de esa parada
                System.out.println("ESTOY EN CASO SOLO UN ELEMENTO");
                return;
            }*/

            if(buscar(referencia.getDato().toString()) != null){ //si el elemento está en la lista
                menuParadas(nuevaParada, referencia);
            }else{
                System.out.println("[!] La parada no existe");
            }

        }
    }

    public void verRecorrido(){
         recorrido.imprimir();
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
                eliminarParadaPorNombre(
                        recorrido.getInicio().getDato().toString()
                );
                break;

            case 2:
                eliminarParadaPorNombre(
                        recorrido.getUltimo().getDato().toString()
                );
                break;

            case 3:
                System.out.println("Ingrese parada a eliminar:");
                eliminarParadaPorNombre(leerDato());
                break;

            default:
                System.out.println("[!] Opción inválida");
        }
    }

    public void eliminarParadaPorNombre(String nombre){

        if(recorrido.vacio()){
            System.out.println("[!] No hay paradas en la ruta.");
            return;
        }

        NodoDoble nodoEliminar = buscar(nombre);

        if(nodoEliminar == null){
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

        System.out.println("[!] Parada eliminada correctamente.");
    }

    //***
    public void simularRecorrido(){
        NodoDoble aux = recorrido.getInicio();

        while(aux != null){

        }
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


    //OVERRIDE FUNCTIONS
    public static void main(String[] args) {
        Rutas ruta = new Rutas();
        Scanner scanner1 = new Scanner(System.in);
        ruta.agregarParadaAlFinal();
        ruta.agregarParadaEntreRutas();
        ruta.recorrido.imprimir();

    }

}
