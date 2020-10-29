package com.github.monstertecg.plantillasDeListas;

public class ListaSimple<T> {
    private T value;
    private ListaSimple next;

    public ListaSimple() {
        this.next = null;
    }

    public ListaSimple(T value) {
        this();
        this.value = value;
    }

    public ListaSimple(T value, ListaSimple next) {
        this(value);
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListaSimple<T> getNext() {
        return next;
    }

    public void setNext(ListaSimple<T> next) {
        this.next = next;
    }
}
