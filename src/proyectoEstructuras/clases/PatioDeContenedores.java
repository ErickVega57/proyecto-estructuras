package proyectoEstructuras.clases;


import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class PatioDeContenedores {

    private static final int MAXIMO = 5;
    private ContenedorPila pilaA;
    private ContenedorPila pilaB;
    private ContenedorPila pilaC;

    public PatioDeContenedores(){
        pilaA = new ContenedorPila(MAXIMO);
        pilaB = new ContenedorPila(MAXIMO);
        pilaC = new ContenedorPila(MAXIMO);
    }

    public void verTopeDeLasPilas(){
        Contenedor topeA = null;
        Contenedor topeB = null;
        Contenedor topeC = null;

        if (!pilaA.isEmpty())
            topeA = (Contenedor)pilaA.top();
        if(!pilaB.isEmpty())
            topeB = (Contenedor)pilaB.top();
        if(!pilaC.isEmpty())
            topeC = (Contenedor)pilaC.top();

        System.out.println("Tope de A: " + topeA);
        System.out.println("Tope de B: " + topeB);
        System.out.println("Tope de C: " + topeC);
    }

    private Contenedor crearContenedor(){
        String id = leerString("id del contenedor:");
        return new Contenedor(id);
    }

    public void ingresarContenedor(){
        Contenedor contenedor = crearContenedor();
        if (contenedor == null){
            System.out.println("[!] Error al crear contenedor");
            return;
        }
        System.out.println("[+] Seleccione la pila a la que le desea agregar el contenedor");
        System.out.println("[1] Pila A");
        System.out.println("[2] Pila B");
        System.out.println("[3] Pila C");
        System.out.println("Seleccione opcion: ");
        switch (leerOpcion()){
            case 1 :
                System.out.println("[PILA A]");
                pilaA.push(contenedor);
                System.out.println("[+] Contenedor agregado correctamente");
                break;
            case 2 :
                System.out.println("[PILA B]");
                pilaB.push(contenedor);
                System.out.println("[+] Contenedor agregado correctamente");
                break;
            case 3 :
                System.out.println("[PILA C]");
                pilaC.push(contenedor);
                System.out.println("[+] Contenedor agregado correctamente");
                break;
            default:
                System.out.println("[!] Opcion no válida");
        }
    }

    public Contenedor buscarContenedorEnPila(char pila, String id) {
        ContenedorPila pilaX = elegirPila(pila);
        if (pilaX.isEmpty()) {
            return null;
        }
        ContenedorPila aux = new ContenedorPila(5);
        Contenedor encontrado = null;

        while (!pilaX.isEmpty()){
            Contenedor tope = (Contenedor) pilaX.pop();  // saco el tope
            if(Objects.equals(tope.getId(), id)){ // coincide el tope
                encontrado = tope;  // encontrado = tope y rompo el ciclo
                break;
            }else{   // no coincide el tope
                aux.push(tope);  // lo meto a aux
            }
        }
        while (!aux.isEmpty()){      //desapilo
            pilaX.push((Contenedor)aux.pop());      // saco de aux y meto a pila A
        }
        if(encontrado != null){  // si lo encontro
            pilaX.push(encontrado);
        }
        return encontrado;
    }

    public Contenedor buscarContenedor(String id){
        char letraPila = 'A';
        for (char i = 'A'; i <= 'C'; i ++){
            Contenedor contenedor = buscarContenedorEnPila(i, id);
            if(contenedor != null) {
                return contenedor;
            }
        }
        return null;
    }

    private Producto crearProducto(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id del producto:");
        int id = leerOpcion();
        String nombre = leerString("nombre del producto: ");
        System.out.println("Ingrese peso del producto:");
        int peso = leerOpcion();

        return  new Producto(id, nombre, peso);
    }

    public void agregarProducto(){
        String idContenedor = leerString("id del contenedor: ");

        Producto producto = crearProducto();
        if(producto == null){
            System.out.println("[!] Error al crear producto");
            return;
        }
        Contenedor contenedor = buscarContenedor(idContenedor);
        if(contenedor == null){
            System.out.println("[!] No se encontro contenedor");
            return;
        }
        contenedor.agregarProducto(producto);
        System.out.println("[+] Producto agregado al contenedor " + idContenedor + " correctamente");
    }

    public int calcularPeso(){
        String idContenedor = leerString("id del contenedor: ");

        Contenedor contenedor = buscarContenedor(idContenedor);
        if(contenedor == null){
            System.out.println("[!] No se encontro contenedor");
            return 0;
        }

        return contenedor.peso();
    }

    public void retirarContenedor(){

        System.out.println("[+] Seleccione la pila de la que desea retirar el contenedor");
        System.out.println("[1] Pila A");
        System.out.println("[2] Pila B");
        System.out.println("[3] Pila C");
        System.out.println("Seleccione opcion: ");
        switch (leerOpcion()){
            case 1 :
                System.out.println("[PILA A]");
                System.out.println("[+] Contenedor "+ pilaA.pop() + " retirado correctamente");
                break;
            case 2 :
                System.out.println("[PILA B]");
                System.out.println("[+] Contenedor "+ pilaB.pop() + " retirado correctamente");
                break;
            case 3 :
                System.out.println("[PILA C]");
                System.out.println("[+] Contenedor "+ pilaC.pop() + " retirado correctamente");
                break;
            default:
                System.out.println("[!] Opcion no válida");
        }
    }

    private String leerString(String mensaje){
        Scanner scanner = new Scanner(System.in);
        String string;
        do {
            System.out.println("[+] Ingrese " + mensaje );
            string = scanner.nextLine();
        }while (string == null || string.isEmpty() );
        return string;
    }

    private int leerOpcion(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
            opcion = scanner.nextInt();
            return opcion;
        } catch (InputMismatchException e) {
            opcion = 0;
        }
        return opcion;
    }

    private ContenedorPila elegirPila(char letraPila){
        return switch (letraPila) {
            case 'A' -> {
                yield pilaA;
            }
            case 'B' -> {
                yield pilaB;
            }
            case 'C' -> {
                yield pilaC;
            }
            default -> {
                System.out.println("[!] Opcion no válida");
                yield null;
            }
        };
    }

    public static void main(String[] args) {


        PatioDeContenedores patio = new PatioDeContenedores();
        // Contenedores de prueba
        patio.ingresarContenedor();
        patio.agregarProducto();
        patio.agregarProducto();
        patio.agregarProducto();
        int peso = patio.calcularPeso();
        System.out.println(peso);
        patio.verTopeDeLasPilas();

        for (int i = 0; i < 5; i++) {
            patio.ingresarContenedor();
        }
        patio.verTopeDeLasPilas();
        System.out.println("Buscar");
        System.out.println(patio.buscarContenedorEnPila('A', "A1"));
        System.out.println(patio.buscarContenedorEnPila('B', "A1"));
        System.out.println(patio.buscarContenedorEnPila('C', "A1"));
        System.out.println("Fin");
        patio.verTopeDeLasPilas();
        patio.agregarProducto();
        patio.verTopeDeLasPilas();

    }


}
