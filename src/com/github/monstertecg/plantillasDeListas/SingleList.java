package com.github.monstertecg.plantillasDeListas;

public class SingleList<T> {
    private ListaSimple<T> primero;

    public SingleList() {
        this.primero = null;
    }

    public boolean esNulo() {
        return this.primero == null;
    }

    /**
     * Adds an element to the end
     *
     * @param elemento the element to add
     */
    public void Añadir(T elemento) {
        if (this.esNulo()) {
            this.primero = new ListaSimple(elemento);
        } else {
            ListaSimple<T> temporal = this.primero;
            while (temporal.getSiguiente() != null) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(new ListaSimple(elemento));
        }

    }
}
