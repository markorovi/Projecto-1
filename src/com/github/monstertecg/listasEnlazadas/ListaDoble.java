package com.github.monstertecg.listasEnlazadas;

import java.util.logging.Logger;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2.2
 */
public class ListaDoble<T> {

    private T valor = null;
    private ListaDoble<T> siguiente = null;
    private ListaDoble<T> anterior = null;
    private ListaDoble<T> primero = null;

    public ListaDoble() { }

    public ListaDoble(T value) {
        this.valor = value;
    }

    /**
     * Agrega un elemento al final de la lista
     *
     * @param elemento
     */
    public void Agregar(T elemento) {
        if (this.primero == null) {
            this.primero = new ListaDoble<T>(elemento);
        } else {
            ListaDoble<T> temporal = new ListaDoble<T>(elemento);
            ListaDoble<T> ultimo = this.primero.BuscaUltimo();
            temporal.EstablecerObjetoAnterior(ultimo);
            ultimo.EstablecerObjetoSiguiente(temporal);
        }
    }

    public void AgregarEn(int indice, T elemento) {
        if (this.primero == null && indice != 0) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        }else if(this.primero == null){
            this.primero = new ListaDoble<T>(elemento);
        } else {
            ListaDoble<T> temporal = new ListaDoble<T>(elemento);
            ListaDoble<T> anteriorPosicion = this.ObtenerObjeto(indice);
            anteriorPosicion.ObtenerObjetoAnterior().EstablecerObjetoSiguiente(temporal);
            temporal.EstablecerObjetoAnterior(anteriorPosicion.ObtenerObjetoAnterior());
            temporal.EstablecerObjetoSiguiente(anteriorPosicion);
            anteriorPosicion.EstablecerObjetoAnterior(temporal);
        }
    }

    /**
     * Busca el último elemento de la lista
     * @return último elemento
     */
    private ListaDoble<T> BuscaUltimo(){
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
                this.primero.EstablecerObjetoAnterior(null);
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
            this.siguiente = this.siguiente.ObtenerObjetoSiguiente();
            if (this.siguiente != null) {
                this.siguiente.EstablecerObjetoAnterior(this);
            }
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
    public T Obtener (int indice) {
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        }
        return this.primero.ObtenerAux(indice);
    }

    /**
     * función recursiva de Obtener valor
     * @param indice
     * @return valor
     */
    private T ObtenerAux(int indice) {
        if (indice == 0) {
            return this.valor;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerAux(indice -1);
        }
    }

    private ListaDoble<T> ObtenerObjeto(int indice) {
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        }
        return this.primero.ObtenerObjetoAux(indice);
    }

    private ListaDoble<T> ObtenerObjetoAux(int indice) {
        if (indice == 0) {
            return this;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerObjetoAux(indice -1);
        }
    }

    public T ObtenerAnteriorDe(int indice){
        if (indice == 0) {
            throw new IndexOutOfBoundsException("No se puede obtener un elemento posterior al último elemento.");
        } else if (this.primero == null) {
                throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoAnterior().ObtenerElemento();
    }

    public T ObtenerSiguienteDe(int indice){
        if (indice == this.Largo()-1) {
            throw new IndexOutOfBoundsException("No se puede obtener un elemento anterior a primer elemento.");
        } else if (this.primero == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoSiguiente().ObtenerElemento();
    }

    public void CambiarValor(int indice, T valor){
        if (indice == 0 && this.primero == null) {
            throw new IndexOutOfBoundsException("El elemento no existe todavía");
        }
        this.primero.EstablecerValorAObjeto(indice, valor);
    }


    private void EstablecerValorAObjeto(int indice, T valor) {
        if (indice == 0) {
            this.valor = valor;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            this.siguiente.EstablecerValorAObjeto(indice -1, valor);
        }
    }

    private ListaDoble<T> ObtenerObjetoSiguiente() {
        return this.siguiente;
    }

    private ListaDoble<T> ObtenerObjetoAnterior() {
        return this.anterior;
    }

    private T ObtenerElemento(){
        return this.valor;
    }

    private void EstablecerObjetoSiguiente(ListaDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    private void EstablecerObjetoAnterior(ListaDoble<T> anterior){
        this.anterior = anterior;
    }

}
