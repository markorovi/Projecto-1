package com.github.monstertecg.plantillasDeListas;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.4
 */
public class ListaSimple<T> {
    private T value;
    private ListaSimple siguiente;
    private ListaSimple primero;

    public ListaSimple() {
        this.siguiente = null;
    }

    public ListaSimple(T value) {
        this();
        this.value = value;
    }

    public ListaSimple(T value, ListaSimple next) {
        this(value);
        this.siguiente = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListaSimple<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ListaSimple<T> siguiente) {
        this.siguiente = siguiente;
    }
}
