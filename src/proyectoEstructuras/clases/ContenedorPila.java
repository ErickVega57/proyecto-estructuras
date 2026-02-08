package proyectoEstructuras.clases;
import pilas.ListaSPila;
import pilas.Pila;

public class ContenedorPila extends ListaSPila {

    private final int maximo;
    private int peso;

    public ContenedorPila(int maximo){
        this.maximo = maximo;
        peso = 0;
    }

    @Override
    public void push(Object o) {
        if (lleno())
            System.out.println("[!] La pila de contenedores esta llena");
        else {
            Contenedor contenedorTemp = (Contenedor) o;
            peso += contenedorTemp.peso();
            super.push(o);
        }
    }

    @Override
    public Object pop(){
        Contenedor contenedorEliminado = null;
        if(lista.vacio()){
            System.out.println("[!] La pila de contenedores esta vacia");
        }else{
            contenedorEliminado =(Contenedor) super.pop();
            peso -= contenedorEliminado.peso();
        }
        return contenedorEliminado;
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
