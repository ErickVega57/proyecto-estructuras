package proyectoEstructuras.main.menus;
import proyectoEstructuras.manifiesto.Rutas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu(){
        scanner = new Scanner(System.in);
        // aqui puden agregar las listas, colas y las variables del menu
    }

    public void iniciar(){
        menuPrincipal();
    }

    private void menuPrincipal(){
        int opcion;
        do {
            System.out.println("\n\tPUERTO PROGRESO LOGIS SYSTEM v1.0 - GESTIÓN PORTUARIA");
            System.out.println("=================================================================");
            System.out.println("[1] ZONA DE RECEPCIÓN"); //Colas
            System.out.println("[2] PATIO DE CONTENEDORES"); //Pilas
            System.out.println("[3] LOGÍSTICA Y RUTAS"); //Lista Simplemente Ligadas
            System.out.println("[4] REPORTE GENERAL");
            System.out.println("[5] SALIR");

            System.out.println("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion){
                case 1 :
                    System.out.println("[-] Opcion[1]");
                    subMenuZonaDeRecepcion();
                    break;
                case 2:
                    System.out.println("[-] Opcion[2]");
                    subMenuPatioDeContenedores();
                    break;
                case 3:
                    System.out.println("[-] Opcion[3]");
                    subMenuRutas();
                    break;
                case 4:
                    System.out.println("[-] Opcion[4]");

                    subMenuReportePuerto();
                    break;
                case 5:
                    System.out.println("[!] Saliendo del programa...");
                    break;
                default:
                    System.out.println("[!] Opción no válida");
                    presionaEnter();
            }

        }while (opcion != 5);
        System.out.println("[!] Programa finalizado.");
    }

    private void subMenuZonaDeRecepcion(){ //Submenu 1 (Pilas)
        int opcion;
        do{
            System.out.println("\n\tZONA DE RECEPCIÓN");
            System.out.println("=================================================================");
            System.out.println("[1] Registrar llegada de camión");
            System.out.println("[2] Dar ingreso a patio");
            System.out.println("[3] Ver próximo camión a atender");
            System.out.println("[4] Listar todos los camiones en espera");
            System.out.println("[5] Volver al menú principal");

            System.out.println("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion){
                case 1 :
                    System.out.println("[-] Opcion[1]");
                    break;
                case 2:
                    System.out.println("[-] Opcion[2]");
                    break;
                case 3:
                    System.out.println("[-] Opcion[3]");
                    break;
                case 4:
                    System.out.println("[-] Opcion[4]");
                    break;
                case 5:
                    System.out.println("[!] Volviendo al menu...");
                    break;
                default:
                    System.out.println("[!] Opción no válida");
                    presionaEnter();
            }
        }while (opcion != 5);
    }


    private void subMenuPatioDeContenedores(){ //Submenú 1
        int opcion;
        do{
            System.out.println("\n\tZONA DE RECEPCIÓN");
            System.out.println("=========================================");
            System.out.println("[1] Ingresar contenedor desde Recepción");
            System.out.println("[2] Retirar contenedor para Ruta");
            System.out.println("[3] Ver tope de las pilas");
            System.out.println("[4] INSPECCIONAR CONTENEDOR");
            System.out.println("[5] Volver al menú principal");

            System.out.println("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion){
                case 1 :
                    System.out.println("[-] Opcion[1]");
                    break;
                case 2:
                    System.out.println("[-] Opcion[2]");
                    break;
                case 3:
                    System.out.println("[-] Opcion[3]");
                    break;
                case 4:
                    System.out.println("[-] INSPECCIONAR CONTENEDOR");
                    inspeccionarContenedorOpciones();
                    break;
                case 5:
                    System.out.println("[!] Volviendo al menu...");
                    break;
                default:
                    System.out.println("[!] Opción no válida");
                    presionaEnter();
            }
        }while (opcion != 5);
    }

    private void inspeccionarContenedorOpciones(){ //Submenú 2
        boolean salir = false;
        do{
            System.out.println(">>[1] Agregar producto");
            System.out.println(">>[2] Calcular peso total");
            int opcion = leerOpcion();
            switch (opcion){
                case 1:
                    System.out.println("[-] Opcion 1");
                    break;
                case 2:
                    System.out.println("[!] Volviendo al menu...");
                    break;
                default:
                    salir = true;
            }

        }while (!salir);
    }

    private void subMenuRutas(){ //Submenú 3
        int opcion;
        Rutas ruta = new Rutas();

        do{
            System.out.println("\n\tLOGÍSTICA Y RUTAS");
            System.out.println("=========================================");
            System.out.println("[1] Agregar nueva parada al final");
            System.out.println("[2] Insertar parada entre destinos");
            System.out.println("[3] Cancelar parada");
            System.out.println("[4] SIMULAR RECORRDIDO");
            System.out.println("[5] Volver al menú principal");

            System.out.println("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion){
                case 1 :
                    System.out.println("[1] Agregar nueva parada al final");
                    ruta.agregarParadaAlFinal();
                    break;
                case 2:
                    System.out.println("[2] Insertar parada entre destinos");
                    ruta.agregarParadaEntreRutas();
                    break;
                case 3:
                    System.out.println("[3] Cancelar parada");
                    ruta.eliminarParada();
                    break;
                case 4:
                    System.out.println("[4] Simular recorrido");
                    ruta.recorrerParadas();
                    break;
                case 5:
                    System.out.println("[!] Volviendo al menu...");
                    break;
                default:
                    System.out.println("[!] Opción no válida");
                    presionaEnter();
            }
        }while (opcion != 5);
    }

    private void subMenuReportePuerto(){ //Submenú 4
        System.out.println("\n\tREPORTE GENERAL");
        System.out.println("=========================================");
        presionaEnter();
    }

    private void presionaEnter(){
        System.out.println("\n[+] Presione [enter] para continuar... ");
        scanner.nextLine(); //buffer
        scanner.nextLine();
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


}
