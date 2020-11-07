package com.github.monstertecg.listasEnlazadas;

import java.util.logging.Logger;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.5.5
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

    /**
     * Se encarga de agregar un elemento en la lista en un indice específico
     * @param indice Indice en el cual se desea agregar el elemento
     * @param elemento Elemento a agregar
     */

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

    /**
     * Funcion recursiva para eliminar
     * @param indice Elemento a eliminar
     */

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

    /**
     * Función recursiva para encontrar el largo de la lista
     * @return El largo de la lista
     */
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

    /**
     * Obtiene el objeto del indice solicitado de la lista
     * @param indice Objeto a devolver
     * @return El objeto que se encontraba en el indice brindado
     */
    private ListaDoble<T> ObtenerObjeto(int indice) {
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        }
        return this.primero.ObtenerObjetoAux(indice);
    }

    /**
     * Funcion recursiva para obtener un objeto dentro de la lista
     * @param indice Indice que hace referencia al objeto a devolver
     * @return Objeto solicitado
     */
    private ListaDoble<T> ObtenerObjetoAux(int indice) {
        if (indice == 0) {
            return this;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            return this.siguiente.ObtenerObjetoAux(indice -1);
        }
    }

    /**
     * Obtiene el objeto anterior al objeto actual
     * @param indice Objeto del que se desea obtener el anterior
     * @return El objeto anterior
     */
    public T ObtenerAnteriorDe(int indice){
        if (indice == 0) {
            throw new IndexOutOfBoundsException("No se puede obtener un elemento posterior al último elemento.");
        } else if (this.primero == null) {
                throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoAnterior().ObtenerElemento();
    }

    /**
     * Obtiene el objeto siguiente al objeto actual
     * @param indice Objeto del que se desea obtener el siguiente
     * @return El objeto siguiente
     */
    public T ObtenerSiguienteDe(int indice){
        if (indice == this.Largo()-1) {
            throw new IndexOutOfBoundsException("No se puede obtener un elemento siguiente al último elemento.");
        } else if (this.primero == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoSiguiente().ObtenerElemento();
    }

    /**
     * Obtiene el objeto anterior de un objeto específico
     * @param elemento Objeto que hace referencia al que se desea obtener el anterior
     * @return Objeto anterior al brindado
     */
    public T ObtenerAnteriorDeElemento(T elemento){
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        } else
            return this.primero.ObtenerAnteriorDeElementoAux(elemento);
    }

    /**
     * Funcion recursiva para obtener el objeto anterior de otro
     * @param elemento Objeto que hace referencia al que se desea obtener el anterio
     * @return Objeto anterior al brindado
     */
    private T ObtenerAnteriorDeElementoAux(T elemento){
        if (this.primero == elemento) {
            throw new IndexOutOfBoundsException("El primer elemento no tiene anterior.");
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El elemento -> "+ elemento +" <- no pertenece a la lista.");
        } else if (this.siguiente == elemento) {
            return this.valor;
        } else {
            return this.siguiente.ObtenerSiguienteDeElementoAux(elemento);
        }
    }


    /**
     * Obtiene el objeto siguiente de un objeto específico
     * @param elemento Objeto que hace referencia al que se desea obtener el siguiente
     * @return El objeto siguiente al brindado
     */
    public T ObtenerSiguienteDeElemento(T elemento){
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        } else
        return this.primero.ObtenerSiguienteDeElementoAux(elemento);
    }

    /**
     * Funcion recursiva para obtener el objeto siguiente de otro
     * @param elemento Objeto que hace referencia al que se desea obtener el siguiente
     * @return Objeto siguiente al brindado
     */
    private T ObtenerSiguienteDeElementoAux(T elemento){
        if (this.primero == elemento){
            return this.primero.ObtenerObjetoSiguiente().ObtenerElemento();
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El elemento es el último de la lista, no tiene siguiente.");
        } else {
            return this.primero.ObtenerSiguienteDeElementoAux(elemento);
        }
    }

    /**
     * Cambia el valor en un indice específico de la lista
     * @param indice Indice de la lista a cambiar
     * @param valor Valor por el cual cambiar el objeto en el indice brindado
     */
    public void CambiarValor(int indice, T valor){
        if (indice == 0 && this.primero == null) {
            throw new IndexOutOfBoundsException("El elemento no existe todavía");
        }
        this.primero.EstablecerValorAObjeto(indice, valor);
    }

    /**
     * Establece el valor a un objeto en un indice especifico en la lista
     * @param indice Objeto a modificar
     * @param valor Valor por el cual modificar al objeto
     */
    private void EstablecerValorAObjeto(int indice, T valor) {
        if (indice == 0) {
            this.valor = valor;
        } else if (this.siguiente == null) {
            throw new IndexOutOfBoundsException("El el índice solicitado sobrepasa el largo de la lista.");
        } else {
            this.siguiente.EstablecerValorAObjeto(indice -1, valor);
        }
    }

    /**
     * Get para el objeto siguiete al actual
     * @return El objeto siguiete
     */
    private ListaDoble<T> ObtenerObjetoSiguiente() {
        return this.siguiente;
    }

    /**
     * Get para el objeto anterior al actual
     * @return El objeto anterior
     */
    private ListaDoble<T> ObtenerObjetoAnterior() {
        return this.anterior;
    }

    /**
     * Get para el objeto actual
     * @return El objeto actual
     */
    private T ObtenerElemento(){
        return this.valor;
    }

    /**
     * Set para el objeto siguiente al actual
     * @param siguiente Valor a asiganrle a siguiente
     */
    private void EstablecerObjetoSiguiente(ListaDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Set para el objeto anterior al actual
     * @param anterior Valor a asignarle a anterior
     */
    private void EstablecerObjetoAnterior(ListaDoble<T> anterior){
        this.anterior = anterior;
    }

}
