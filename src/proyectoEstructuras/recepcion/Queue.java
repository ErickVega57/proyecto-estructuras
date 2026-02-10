package proyectoEstructuras.recepcion;

public interface Queue {

    public void enqueue(Object dato);
    public Object dequeue();
    public Object front();
    public int size();
    public boolean isEmpty();
    
}
