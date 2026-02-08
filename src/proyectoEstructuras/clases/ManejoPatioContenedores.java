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
        System.out.println("Seleccione opcion: ");
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
        Contenedor topeA = null;
        Contenedor topeB = null;
        Contenedor topeC = null;

        if (!patio.vacioA())
            topeA = (Contenedor)patio.verTopeA();
        if(!patio.vacioB())
            topeB = (Contenedor)patio.verTopeB();
        if(!patio.vacioC())
            topeC = (Contenedor)patio.verTopeC();

        System.out.println("Tope de A: " + topeA);
        System.out.println("Tope de B: " + topeB);
        System.out.println("Tope de C: " + topeC);
    }

    public void agregarProducto(String idContenedor, Producto producto) {
        Contenedor contenedor = buscarContenedorEnA(idContenedor);

        if (contenedor == null) {
            contenedor = buscarContenedorEnB(idContenedor);
        }

        if (contenedor == null) {
            contenedor = buscarContenedorEnC(idContenedor);
        }

        if (contenedor == null) {
            System.out.println("[!] No se ha encontrado el contenedor");
            return;
        }

        System.out.println("[+] Contenedor encontrado, agregando producto");
        contenedor.agregarProducto(producto);
    }

    public int calcularPeso(String idContenedor) {
        Contenedor contenedor = buscarContenedorEnA(idContenedor);

        if (contenedor == null) {
            contenedor = buscarContenedorEnB(idContenedor);
        }

        if (contenedor == null) {
            contenedor = buscarContenedorEnC(idContenedor);
        }

        if (contenedor == null) {
            System.out.println("[!] No se ha encontrado el contenedor");
            return 0;
        }

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

    public Contenedor buscarContenedorEnA(String id) {
        if (patio.vacioA()) {
            return null;
        }
        while (!patio.vacioA()) {
            Contenedor tope = patio.verTopeA();

            if (Objects.equals(tope.getId(), id)) {
                return tope; // encontrado
            }
            Contenedor temp = patio.retirarDePilaA();
            if (!patio.llenoB()) {
                patio.agregarAPilaB(temp);
            }
            else if (!patio.llenoC()) {
                patio.agregarAPilaC(temp);
            }
            else {
                // no hay espacio para maniobrar
                patio.agregarAPilaA(temp); // lo devuelves
                return null;
            }
        }
        return null; // no encontrado
    }
    public Contenedor buscarContenedorEnB(String id) {
        if (patio.vacioB()) {
            return null;    // vacio nada que buscar
        }
        while (!patio.vacioB()) {
            Contenedor tope = patio.verTopeB();

            if (Objects.equals(tope.getId(), id)) {
                return tope; // encontrado
            }
            Contenedor temp = patio.retirarDePilaB();  // no encontre desapilo
            if (!patio.llenoC()) {
                patio.agregarAPilaC(temp);    // si C no esta lleno apilo en C
            }
            else if (!patio.llenoA()) {      // si C esta lleno y A no apilo en A
                patio.agregarAPilaA(temp);
            }
            else {                              // C y A llenos no hay espacio para maniobrar
                patio.agregarAPilaB(temp);      // lo devuelves a la pila B
                return null;
            }
        }
        return null; // no encontrado
    }
    public Contenedor buscarContenedorEnC(String id) {
        if (patio.vacioC()) {
            return null;   //vacio C regreso
        }
        while (!patio.vacioC()) {     // mientras C no este vacio
            Contenedor tope = patio.verTopeC();

            if (Objects.equals(tope.getId(), id)) {
                return tope; // encontrado
            }
            Contenedor temp = patio.retirarDePilaC(); //desapilo C
            if (!patio.llenoA()) {
                patio.agregarAPilaA(temp);   // meto en A si hay espacio
            }
            else if (!patio.llenoB()) {
                patio.agregarAPilaB(temp);  // meto en B si no hay en A
            }
            else {                          // no hay espacio para maniobrar
                patio.agregarAPilaA(temp); // lo devuelves a C
                return null;
            }
        }
        return null; // no encontrado
    }

    public static void main(String[] args) {

        ManejoPatioContenedores sistema = new ManejoPatioContenedores();

        //productos de prueba
        Producto p1 = new Producto(11,"leche", 1);
        Producto p2 = new Producto(12,"huevos",1);
        Producto p3 = new Producto(13,"tablet", 1);
        Producto p4 = new Producto(14,"television",10);
        Producto p5 = new Producto(15,"galletas",1);

        // Contenedores de prueba
        Contenedor a1 = new Contenedor("A1");
        Contenedor a2 = new Contenedor("A2");
        Contenedor a3 = new Contenedor("A3");
        Contenedor a4 = new Contenedor("A4");
        Contenedor a5 = new Contenedor("A5");
        Contenedor b1 = new Contenedor("B1");
        Contenedor b2 = new Contenedor("B2");
        Contenedor c1 = new Contenedor("C1");
        Contenedor c2 = new Contenedor("C2");

        sistema.patio.agregarAPilaA(a1);
        sistema.patio.agregarAPilaA(a2);
        sistema.patio.agregarAPilaA(a3);
        sistema.patio.agregarAPilaA(a4);
        sistema.patio.agregarAPilaB(b1);
        sistema.patio.agregarAPilaB(b2);
        sistema.patio.agregarAPilaC(c1);
        sistema.patio.agregarAPilaA(a5);
        sistema.patio.agregarAPilaC(c2);

        sistema.verTopeDeLasPilas();
        Contenedor c = sistema.patio.retirarDePilaC();
        System.out.println(c);

        sistema.verTopeDeLasPilas();

        sistema.agregarProducto("C1", p1);
        sistema.verTopeDeLasPilas();
        sistema.agregarProducto("C8", p4);
        sistema.verTopeDeLasPilas();
        sistema.agregarProducto("C1", p5);
        sistema.verTopeDeLasPilas();
        System.out.println(sistema.calcularPeso("h1"));
        System.out.println(sistema.calcularPeso("A1"));
        sistema.ingresarContenedor(c1);
        sistema.verTopeDeLasPilas();

    }



}
