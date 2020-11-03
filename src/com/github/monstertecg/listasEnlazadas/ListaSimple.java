package com.github.monstertecg.listasEnlazadas;

import java.util.logging.Logger;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2.2
 */
public class ListaSimple<T> {

    private static Logger LOGGER = Logger.getLogger(ListaSimple.class.getName());

    private T value = null;
    private ListaSimple<T> siguiente = null;
    private ListaSimple<T> primero = null;

    public ListaSimple() { this.primero = null; }

    public ListaSimple(T value) {
        this.value = value;
    }

    /**
     * Agrega un elemento al final de la lista
     *
     * @param elemento
     */
    public void Agregar(T elemento) {
        if (this.primero == null) {
            this.primero = new ListaSimple<T>(elemento);
        } else {
            ListaSimple<T> temporal = new ListaSimple<T>(elemento);
            ListaSimple<T> ultimo = this.primero.BuscaUltimo();
            ultimo.EstablecerSiguiente(temporal);
        }
    }

    /**
     * Busca el último elemento de la lista
     * @return último elemento
     */
    private ListaSimple<T> BuscaUltimo(){
        if (this.siguiente == null){
            return this;
        } else {
            return this.siguiente.BuscaUltimo();
        }
    }

    /**
     * Elimina el elemento número índice
     * @param indice
     */
    public void Eliminar (int indice) {
        if (indice == 0) {
            if (this.primero == null) {
                throw new IndexOutOfBoundsException("La lista está vacía, no se pueden eliminar elementos");
            } else {
                this.primero = this.primero.siguiente;
            }
        } else {
            this.primero.EliminarAux(indice);
        }
    }

    private void EliminarAux (int indice) {
        if (indice -1 == 0) {
            if (this.siguiente == null){
                throw new IndexOutOfBoundsException("El índice está fuera de los límites de la lista.");
            }
            this.siguiente = this.siguiente.ObtenerSiguiente();
        } else {
            this.siguiente.EliminarAux(indice -1);
        }
    }

    /**
     * Recorre la lista para calcular el largo
     * @return largo de la lista
     */
    public int Largo() {
        if (this.primero == null) {
            return 0;
        } else {
            return this.primero.LargoAux();
        }
    }

    private int LargoAux(){
        if (this.siguiente == null){
            return 1;
        } else {
            return this.siguiente.LargoAux() + 1;
        }
    }

    /**
     * Devuelve el elemento en el índice solicitado
     * @param indice
     * @return elemento
     */
    public T ObtenerValor(int indice) {
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        }
        return this.primero.ObtenerValorAux(indice);
    }

    /**
     * función recursiva de Obtener valor
     * @param indice
     * @return valor
     */
    private T ObtenerValorAux(int indice) {
        if (indice == 0) {
            return this.value;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerValorAux(indice -1);
        }
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListaSimple<T> ObtenerSiguiente() {
        return siguiente;
    }

    public void EstablecerSiguiente(ListaSimple<T> siguiente) {
        this.siguiente = siguiente;
    }
}
