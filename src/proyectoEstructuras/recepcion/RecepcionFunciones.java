package proyectoEstructuras.recepcion;

public class RecepcionFunciones implements Queue {
    private Node head;
    private Node tail;
    private int size;

    // Nodo interno para almacenar datos y enlace al siguiente
    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    // Dice si la cola esta vacia o no y regresa un booleano
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // Regresa un mensaje diciendo si la cola esta vacia o no (para evitar usar if en el main)
    public String esVacio() {
        if (isEmpty() == true) {
            return "La cola está vacía";
        }
        return "La cola no está vacía";
    }

    // Agrega un elemento al final de la cola
    @Override
    public void enqueue(Object x) {
        Node nuevo = new Node(x);
        if (tail != null) {
            tail.next = nuevo;
        }
        tail = nuevo;
        if (head == null) {
            head = nuevo;
        }
        size++;
    }
    
    // Elimina y regresa el elemento al frente de la cola
    @Override
    public Object dequeue() {
        if (isEmpty() == true) {
            System.out.println("La cola está vacía");
            return null;
        }
        Object dato = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return dato;
    }

    // Regresa el elemento al frente de la cola sin eliminarlo
    @Override
    public Object front() {
        if (isEmpty() == true) {
            System.out.println("La cola está vacía");
            return null;
        }
        return head.data;
    }

    // Regresa el número de elementos en la cola (es un simple contador)
    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        RecepcionFunciones q = new RecepcionFunciones();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("Front: " + q.front()); // Output: Front: 1
        System.out.println("Size: " + q.size());   // Output: Size: 3
        System.out.println("Dequeue: " + q.dequeue()); // Output: Dequeue: 1
        System.out.println("Front after dequeue: " + q.front()); // Output: Front after dequeue: 2
        System.out.println("Size after dequeue: " + q.size());   // Output: Size after dequeue: 2
        System.out.println(q.esVacio()); // Output: La cola no está vacía
        q.dequeue();
        q.dequeue();
        System.out.println(q.esVacio()); // Output: La cola está vacía
        System.out.println("Front: " + q.front());
        System.out.println("Size: " + q.size());
        q.dequeue();
        }

}
