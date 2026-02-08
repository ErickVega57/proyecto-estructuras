package proyectoEstructuras.clases;

import listas.ListaSimple;
import listas.Nodo;

public class Contenedor {
    private ListaSimple productos;
    private final String id;

    public Contenedor(String id){
        productos = new ListaSimple();
        this.id = id;
    }

    public boolean contenedorVacio(){
        return productos.vacio();
    }

    public void agregarProducto(Producto producto){
        productos.insertaInicio(producto);
    }

    public boolean productoExiste (Producto producto){
        if (productos.vacio()){
            return false;
        }else{
            if(productos.getInicio() == productos.getUltimo()){ //unico elemento
                Producto productoContenedor = (Producto) productos.getInicio().getDato();
                return producto == productoContenedor;
            }else {
                Nodo actual = productos.getInicio();
                while (actual != null && (Producto)actual.getDato() != producto){
                    actual = actual.getSiguiente();
                }
                return actual != null;
            }
        }
    }

    public int peso (){
        int peso = 0;
        if (productos.vacio()){  //no hay productos en el contenedor
            return 0;
        }else{
            if (productos.getInicio() == productos.getUltimo()){ // solo un producto
                Producto producto =(Producto) productos.getInicio().getDato();
                peso += producto.getPeso();
            }else{   // mas de un producto
                Nodo ultimo = productos.getUltimo();
                Nodo actual = productos.getInicio();
                while (actual != null){
                    Producto producto =(Producto) actual.getDato();
                    peso += producto.getPeso();
                    actual = actual.getSiguiente();
                }
            }
        }
        return peso;
    }

    public void verProductos(){
        productos.imprimir();
    }

    public String toString(){
        return String.format("Contenedor: "  + id );
    }

    public String getId() {
        return id;
    }

}
