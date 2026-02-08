package proyectoEstructuras.clases;


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

    public void agregarAPilaA(Contenedor contenedor){
        pilaA.push(contenedor);
    }
    public void agregarAPilaB(Contenedor contenedor){
        pilaB.push(contenedor);
    }
    public void agregarAPilaC(Contenedor contenedor){
        pilaC.push(contenedor);
    }
    public Contenedor retirarDePilaA(){
        return (Contenedor) pilaA.pop();
    }
    public Contenedor retirarDePilaB(){
        return (Contenedor) pilaB.pop();
    }
    public Contenedor retirarDePilaC(){
        return (Contenedor) pilaC.pop();
    }
    public Contenedor verTopeA (){
        return(Contenedor) pilaA.top();
    }
    public Contenedor verTopeB (){
        return(Contenedor) pilaB.top();
    }
    public Contenedor verTopeC (){
        return(Contenedor) pilaC.top();
    }
    public boolean llenoA(){
        return pilaA.lleno();
    }
    public boolean llenoB(){
        return pilaB.lleno();
    }
    public boolean llenoC(){
        return pilaC.lleno();
    }
    public boolean vacioA(){
        return pilaA.isEmpty();
    }
    public boolean vacioB(){
        return pilaB.isEmpty();
    }
    public boolean vacioC(){
        return pilaC.isEmpty();
    }


}
