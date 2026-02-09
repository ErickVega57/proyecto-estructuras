package proyectoEstructuras.manifiesto;

import listas.ListaDoble;
import listas.Nodo;
import listas.NodoDoble;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Rutas extends ListaDoble {
    Scanner scanner = new Scanner(System.in);
    String nombreRuta; //equivalente a Dato de ListaDoble

    //Nodo inicio, final, siguiente, anterior
    //Ruta es un tipo de ListaDoble


     public void agregarParadaAlFinal(){
        System.out.println("Ingrese el nombre de la nueva parada: ");
        setNombreRuta(scanner.nextLine());
        insertaFinal(nombreRuta); //se utiliza la función ya creada y se le pasa el nombre del dato.
    }

    public void agregarParadaEntreRutas(){

        System.out.println("[!] Inserte nombre de la nueva parada: ");
        NodoDoble paradaNueva = new NodoDoble(scanner.nextLine());

        if(inicio == null){ //lista vacía
            System.out.println("[!] La lista está vacía. Se añadirá parada al inicio de la ruta.");
            inicio = ultimo = paradaNueva;
            return;
        }

        System.out.println("[?] ¿Después de qué parada quiere añadir una nueva?");
        String datoParadaAnterior = scanner.nextLine();

        if(paradaExiste(datoParadaAnterior)){

            if(inicio == ultimo){

                System.out.println("[!] Solo hay una parada programada");
                System.out.println("[1] Insertar después");
                System.out.println("[2] Insertar antes");

                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op){
                    case 1:
                        insertaFinal(paradaNueva.getDato());
                        return;

                    case 2:
                        insertaInicio(paradaNueva.getDato());
                        return;
                    default:
                        System.out.println("Opción inválida");
                        return;

                }
            }else {

                NodoDoble paradaActual = inicio;

                while (!datoParadaAnterior.equals(paradaActual.getDato())) { //se recorre la Lista
                    System.out.println("Estoy en: " + paradaActual.getDato());
                    paradaActual = paradaActual.getSiguiente();
                }

                //Se reasignan valores
                paradaNueva.setSiguiente(paradaActual.getSiguiente());
                paradaNueva.setAnterior(paradaActual);

                if (paradaActual.getSiguiente() != null) {
                    paradaActual.getSiguiente().setAnterior(paradaNueva);
                }

                paradaActual.setSiguiente(paradaNueva);

                if (paradaActual == ultimo) {
                    ultimo = paradaNueva;
                }
            }
        }else {
            System.out.println("[!] Ingrese una parada que exista.");
            return;
        }
    }

    public void eliminarParada(){
        int opcion;
        do{
            System.out.println("\n\t[3] Eliminar Parada");
            System.out.println("=================================================================");
            System.out.println("[1] Eliminar primera parada");
            System.out.println("[2] Eliminar última parada");
            System.out.println("[3] Otra");
            System.out.println("[4] Volver");
            System.out.println("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion){
                case 1:
                    System.out.println("[1] Eliminar primera parada");
                    System.out.println("[!] Eliminando primera parada...");
                    eliminaInicio();
                    imprimir();
                    break;
                case 2:
                    System.out.println("[1] Eliminar última parada");
                    System.out.println("[!] Eliminando última parada...");
                    eliminaFinal();
                    imprimir();
                    break;
                case 3:
                    System.out.println("[3] Otra");
                    System.out.println("[!] Introduzca el nombre de la parada que desea eliminar:");
                    System.out.println("[!] Presione Enter...");
                    scanner.nextLine();
                    String nombreParadaAEliminar = scanner.nextLine();

                    eliminaOtra(nombreParadaAEliminar);
                    imprimir();
                    break;
            }
        }while(opcion != 4);

    }

    public boolean paradaExiste(String dato){
         NodoDoble nodoActual = inicio;
         //recorrer arreglo
        //si la lista está vacía
        if(nodoActual == null){
            System.out.println("[!] La lista está vacía");
            return false;
        }
        while(nodoActual!= null){
            //comparamos
            if(dato.equals(nodoActual.getDato())){
                System.out.println("[!] La parada sí existe");
                return true;
            }
            //cambiamos de nodo
            nodoActual = nodoActual.getSiguiente();
        }
        System.out.println("[!] La parada no existe");
        return false;
    }

    public void eliminaOtra(String datoAEliminar){

        if(inicio == null){
            System.out.println("[!] Lista vacía...");
            return;
        }
         NodoDoble paradaActual = inicio; //Auxiliar
         //recorrer nodo
        if(paradaExiste(datoAEliminar)){

            //Recorremos lista
            while(!datoAEliminar.equals(paradaActual.getDato())){
                paradaActual = paradaActual.getSiguiente();
            } //parada Actual es el nodo que quiero eliminar

            //actualizamos
            paradaActual.getAnterior().setSiguiente(paradaActual.getSiguiente());
            paradaActual.getSiguiente().setAnterior(paradaActual.getAnterior());

        }else{
            System.out.println("[!] El dato no existe...");
        }
    }



    private int leerOpcion(){
        int opcion;
        try {
            opcion = scanner.nextInt();
            return opcion;
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }


    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    //OVERRIDE FUNCTIONS
    public static void main(String[] args) {
        Rutas ruta = new Rutas();
        Scanner scanner1 = new Scanner(System.in);

        ruta.agregarParadaAlFinal();
        ruta.agregarParadaAlFinal();
        ruta.agregarParadaAlFinal();

        ruta.agregarParadaEntreRutas();
        ruta.eliminarParada();
        ruta.imprimir();
    }

}
