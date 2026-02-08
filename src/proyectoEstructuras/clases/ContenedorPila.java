package proyectoEstructuras.clases;
import listas.ListaSimple;
import pilas.Pila;

public class ContenedorPila implements Pila{

    protected ListaSimple lista;
    private final int maximo;
    private int peso;
    private int cont;


    public ContenedorPila(int maximo){
        this.maximo = maximo;
        lista = new ListaSimple();
        peso = 0;
        cont = 0;
    }

    @Override
    public void push(Object contenedor) {
        if(!lleno()){
            Contenedor temp = (Contenedor) contenedor;
            lista.insertaInicio(contenedor);
            peso += temp.peso();
            cont ++;
        }else
            System.out.println("[!] Pila de contenedores llena");
    }

    @Override
    public Object pop(){
        Contenedor contenedorEliminado = null;
        if(isEmpty()){
            System.out.println("[!] La pila de contenedores esta vacia");
        }else{
            contenedorEliminado = (Contenedor)lista.eliminaInicio();
            peso -= contenedorEliminado.peso();
            cont --;
        }

        return contenedorEliminado;
    }

    @Override
    public Object top() {
        Contenedor contenedorTope = null;
        if(isEmpty()){
            System.out.println("[!] La pila de contenedores esta vacia");
        }else{
            contenedorTope = (Contenedor)lista.getInicio().getDato();
        }
        return contenedorTope;
    }

    @Override
    public int size() {
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return cont == 0;
    }

    public boolean lleno(){
        return cont == maximo;
    }

    public int getMaximo() {
        return maximo;
    }

    public int getPeso() {
        return peso;
    }
}
