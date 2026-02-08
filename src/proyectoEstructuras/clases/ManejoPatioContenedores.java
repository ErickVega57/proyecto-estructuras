package proyectoEstructuras.clases;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class ManejoPatioContenedores {

    public final PatioDeContenedores patio;

    public ManejoPatioContenedores (){
        patio = new PatioDeContenedores();
    }

    public void ingresarContenedor(Contenedor contenedor){
        System.out.println("[+] Seleccione la pila a la que le desea agregar el contenedor");
        System.out.println("[1] Pila A");
        System.out.println("[2] Pila B");
        System.out.println("[3] Pila C");
        switch (leerOpcion()){
            case 1 :
                System.out.println("[PILA A]");
                patio.agregarAPilaA(contenedor);
                break;
            case 2 :
                System.out.println("[PILA B]");
                patio.agregarAPilaB(contenedor);
                break;
            case 3 :
                System.out.println("[PILA C]");
                patio.agregarAPilaC(contenedor);
                break;
            default:
                System.out.println("[!] Opcion no válida");
        }
    }
    public void verTopeDeLasPilas(){
        Contenedor topeA = (Contenedor)patio.verTopeA();
        Contenedor topeB = (Contenedor)patio.verTopeB();
        Contenedor topeC = (Contenedor)patio.verTopeC();


        System.out.println("Tope de A: " + topeA);
        System.out.println("Tope de B: " + topeB);
        System.out.println("Tope de C: " + topeC);

    }

    public void agregarProducto(Contenedor contenedor, Producto producto){
        contenedor.agregarProducto(producto);
    }
    public int CalcularPesoTotal(Contenedor contenedor){
        return contenedor.peso();
    }

    private int leerOpcion(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
            opcion = scanner.nextInt();
            return opcion;
        } catch (InputMismatchException e) {
            opcion = -1;
        }
        return opcion;
    }

    public Contenedor buscarContenedorEnA (String id){
        if(patio.vacioA()){  // si la pila esta vacia no hay que buscar
            return null;
        }
        Contenedor encontrado = null;
        Contenedor tope = patio.verTopeA();
        while (!patio.vacioA() && !Objects.equals(tope.getId(), id)){  // pila no vacia y que el id del contenedor tope sea distinto
            Contenedor temp = patio.retirarDePilaA();
            if (!patio.llenoB()){
                patio.agregarAPilaB(temp);  // si hay espacio en B agrego B
            }else{
                if (!patio.llenoC()){  // si no hay en B pero si en C agrego C
                    patio.agregarAPilaC(temp);
                }else{
                    patio.agregarAPilaA(temp);  // no hay espacio ni en B ni en C lo regreso a A
                    System.out.println("[!] No hay espacio para maniobrar");
                    return null;
                }
            }
            tope = patio.verTopeA();
        }
        if(patio.vacioA()){
            System.out.println("[!] Contenedor no encontrado en A");
            return null;
        }
        if(Objects.equals(tope.getId(),id)){
            encontrado = patio.retirarDePilaA();
        }
        return encontrado;
    }

    public Contenedor buscarContenedorEnB(String id){
        if(patio.vacioB()){
            return null;
        }
        Contenedor encontrado = null;
        Contenedor tope = patio.verTopeB();
        while (!patio.vacioB() && !Objects.equals(tope.getId(), id)){
            Contenedor temp = patio.retirarDePilaB();
            if (!patio.llenoA()){
                patio.agregarAPilaA(temp);
            }else{
                if (!patio.llenoC()){
                    patio.agregarAPilaC(temp);
                }else{
                    patio.agregarAPilaB(temp);
                    System.out.println("[!] No hay espacio para maniobrar");
                    return null;
                }
            }
            tope = patio.verTopeB();
        }
        if(patio.vacioB()){
            System.out.println("[!] Contenedor no encontrado en B");
            return null;
        }
        if(Objects.equals(tope.getId(),id)){
            encontrado = patio.retirarDePilaB();
        }
        return encontrado;
    }


    public Contenedor buscarContenedorEnC(String id){
        if(patio.vacioC()){
            return null;
        }
        Contenedor encontrado = null;
        Contenedor tope = patio.verTopeC();
        while (!patio.vacioC() && !Objects.equals(tope.getId(), id)){
            Contenedor temp = patio.retirarDePilaC();
            if (!patio.llenoB()){
                patio.agregarAPilaB(temp);
            }else{
                if (!patio.llenoA()){
                    patio.agregarAPilaA(temp);
                }else{
                    patio.agregarAPilaC(temp);
                    System.out.println("[!] No hay espacio para maniobrar");
                    return null;
                }
            }
            tope = patio.verTopeC();
        }
        if(patio.vacioC()){
            System.out.println("[!] Contenedor no encontrado en C");
            return null;
        }
        if(Objects.equals(tope.getId(),id)){
            encontrado = patio.retirarDePilaC();
        }
        return encontrado;
    }

    public static void main(String[] args) {

        ManejoPatioContenedores sistema = new ManejoPatioContenedores();

        // Contenedores de prueba
        Contenedor a1 = new Contenedor("A1");
        Contenedor a2 = new Contenedor("A2");
        Contenedor a3 = new Contenedor("A3");
        Contenedor a4 = new Contenedor("A4");

        Contenedor b1 = new Contenedor("B1");
        Contenedor b2 = new Contenedor("B2");

        Contenedor c1 = new Contenedor("C1");

        sistema.patio.agregarAPilaA(a1);
        sistema.patio.agregarAPilaA(a2);
        sistema.patio.agregarAPilaA(a3);
        sistema.patio.agregarAPilaA(a4);
        sistema.patio.agregarAPilaB(b1);
        sistema.patio.agregarAPilaB(b2);
        sistema.patio.agregarAPilaC(c1);


        System.out.println("\n🔎 Buscando A2 en pila A");
        Contenedor encontrado = sistema.buscarContenedorEnA("A2");
        System.out.println("Resultado: " + (encontrado != null ? encontrado.getId() : "null"));

        System.out.println("\n=== DESPUÉS DE BUSCAR EN A ===");

        System.out.println("\n🔎 Buscando B1 en pila B");
        encontrado = sistema.buscarContenedorEnB("B1");
        System.out.println("Resultado: " + (encontrado != null ? encontrado.getId() : "null"));

        System.out.println("\n=== DESPUÉS DE BUSCAR EN B ===");


        System.out.println("\n🔎 Buscando C1 en pila C");
        encontrado = sistema.buscarContenedorEnC("C1");
        System.out.println("Resultado: " + (encontrado != null ? encontrado.getId() : "null"));

        System.out.println("\n=== ESTADO FINAL ===");
    }



}
