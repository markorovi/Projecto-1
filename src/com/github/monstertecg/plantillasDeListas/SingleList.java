package com.github.monstertecg.plantillasDeListas;

public class SingleList<T> {
    private ListaSimple<T> first;

    public SingleList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * Adds an element to the end
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (this.isEmpty()) {
            this.first = new ListaSimple(element);
        } else {
            ListaSimple<T> ref = this.first;
            while (ref.getNext() != null) {
                ref = ref.getNext();
            }
            ref.setNext(new ListaSimple(element));
        }

    }
}
