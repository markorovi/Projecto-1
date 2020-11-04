package com.github.monstertecg.listasEnlazadas;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.6.0
 */
public class ListaCircularDoble<T> {

    private T valor = null;
    private ListaCircularDoble<T> siguiente = null;
    private ListaCircularDoble<T> anterior = null;
    private ListaCircularDoble<T> primero = null;

    public ListaCircularDoble() { }

    public ListaCircularDoble(T value) {
        this.valor = value;
    }

    /**
     * Agrega un elemento al final de la lista
     *
     * @param elemento
     */
    public void Agregar(T elemento) {
        if (this.primero == null) {
            this.primero = new ListaCircularDoble<T>(elemento);
            this.primero.EstablecerObjetoSiguiente(this.primero);
            this.primero.EstablecerObjetoAnterior(this.primero);
        } else {
            ListaCircularDoble<T> temporal = new ListaCircularDoble<T>(elemento);
            ListaCircularDoble<T> ultimo = this.primero.BuscaUltimo(this.primero);
            temporal.EstablecerObjetoAnterior(ultimo);
            ultimo.EstablecerObjetoSiguiente(temporal);
            temporal.EstablecerObjetoSiguiente(this.primero);
            this.primero.EstablecerObjetoAnterior(temporal);
        }
    }

    public void AgregarEn(int indice, T elemento) {
        if (this.primero == null && indice != 0) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        }else if(this.primero == null){
            this.primero = new ListaCircularDoble<T>(elemento);
        } else {
            ListaCircularDoble<T> temporal = new ListaCircularDoble<T>(elemento);
            ListaCircularDoble<T> anteriorPosicion = this.ObtenerObjeto(indice);
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
    private ListaCircularDoble<T> BuscaUltimo(ListaCircularDoble<T> primero){
        if (this.siguiente == primero){
            return this;
        } else {
            return this.siguiente.BuscaUltimo(primero);
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
                ListaCircularDoble<T> temporal = this.primero.ObtenerObjetoSiguiente();
                temporal.EstablecerObjetoAnterior(this.primero.BuscaUltimo(this.primero));
                if (this.primero == temporal.ObtenerObjetoSiguiente()){
                    temporal.EstablecerObjetoSiguiente(temporal);
                }
                this.primero.BuscaUltimo(this.primero).EstablecerObjetoSiguiente(temporal);
                this.primero = temporal;
            }
        } else {
            this.primero.EliminarAux(indice, this.primero);
        }
    }

    private void EliminarAux (int indice, ListaCircularDoble<T> primero) {
        if (indice -1 == 0) {
            if (this.siguiente == primero){
                throw new IndexOutOfBoundsException("El índice está fuera de los límites de la lista.");
            }
            this.siguiente = this.siguiente.ObtenerObjetoSiguiente();
            if (this.siguiente != primero) {
                this.siguiente.EstablecerObjetoAnterior(this);
            }
        } else {
            this.siguiente.EliminarAux(indice -1, primero);
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
            return this.primero.LargoAux(this.primero);
        }
    }

    private int LargoAux(ListaCircularDoble<T> primero){
        if (this.siguiente == primero){
            return 1;
        } else {
            return this.siguiente.LargoAux(primero) + 1;
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
        return this.primero.ObtenerAux(indice, this.primero);
    }

    /**
     * función recursiva de Obtener valor
     * @param indice
     * @return valor
     */
    private T ObtenerAux(int indice, ListaCircularDoble<T> primero) {
        if (indice == 0) {
            return this.valor;
        } else if (this.siguiente == primero) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerAux(indice -1, primero);
        }
    }

    private ListaCircularDoble<T> ObtenerObjeto(int indice) {
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        }
        return this.primero.ObtenerObjetoAux(indice, this.primero);
    }

    private ListaCircularDoble<T> ObtenerObjetoAux(int indice, ListaCircularDoble<T> primero) {
        if (indice == 0) {
            return this;
        } else if (this.siguiente == primero) {
            throw new IndexOutOfBoundsException("El índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerObjetoAux(indice -1, primero);
        }
    }








    public Object asd(){
        return this.primero.siguiente.ObtenerElemento();
    }

    public T ObtenerAnteriorDe(int indice){
        if (this.primero == null) {
                throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoAnterior().ObtenerElemento();
    }

    public T ObtenerSiguienteDe(int indice){
        if (this.primero == null) {
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

    private ListaCircularDoble<T> ObtenerObjetoSiguiente() {
        return this.siguiente;
    }

    private ListaCircularDoble<T> ObtenerObjetoAnterior() {
        return this.anterior;
    }

    private T ObtenerElemento(){
        return this.valor;
    }

    private void EstablecerObjetoSiguiente(ListaCircularDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    private void EstablecerObjetoAnterior(ListaCircularDoble<T> anterior){
        this.anterior = anterior;
    }

}
