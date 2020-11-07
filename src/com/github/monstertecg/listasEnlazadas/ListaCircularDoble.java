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

    private ListaCircularDoble(T value) {
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

    /**
     * Se encarga de agregar un elemento en un indice específico
     * @param indice Indice en el cual se desea agregar el elemento
     * @param elemento Elemento a agregar
     */
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
     * @param indice Indice de la lista a eliminar
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

    /**
     * Elimina el elemento en la lista en el indice indicado
     * @param indice Indice en el cual se eliminará el elemento
     * @param primero La cabeza de la lista
     */

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

    /**
     * Obtiene el objeto en el indice indicado
     * @param indice Indice que hace referencia al objeto a devolver
     * @return Objeto de la lista
     */
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

    /**
     * Busca el objeto siguiente al del indice ingresado
     * @param indice Indice que hace referencia al objeto que se le buca el siguiente
     * @return
     */
    public T ObtenerSiguienteDe(int indice){
        if (this.primero == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        }
        return this.ObtenerObjeto(indice).ObtenerObjetoSiguiente().ObtenerElemento();
    }

    /**
     * Sustituye un objeto de la lista por el que se desee
     * @param indice Objeto a reemplazar
     * @param valor Objeto que reemplazará
     */
    public void CambiarValor(int indice, T valor){
        if (indice == 0 && this.primero == null) {
            throw new IndexOutOfBoundsException("El elemento no existe todavía");
        }
        this.primero.EstablecerValorAObjeto(indice, valor);
    }

    /**
     * Establece el valor a un objeto de la lista
     * @param indice Objeto a modificar
     * @param valor Valor por el cual se desea modifica
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
     * Obtiene el objeto siguiente al actual
     * @return Siguiente en la lista
     */
    private ListaCircularDoble<T> ObtenerObjetoSiguiente() {
        return this.siguiente;
    }

    /**
     * Obtiene el objeto anterior del actual
     * @return Anterior en la lista
     */
    private ListaCircularDoble<T> ObtenerObjetoAnterior() {
        return this.anterior;
    }

    /**
     * Obtiene el valor correspondiente al objeto actual
     * @return Valor del objeto actual
     */
    private T ObtenerElemento(){
        return this.valor;
    }

    /**
     * Asigna que objeto será el siguiente para el objeto actual
     * @param siguiente Objeto a asignar
     */
    private void EstablecerObjetoSiguiente(ListaCircularDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Asigna que objeto será el anterior para el objeto actual
     * @param anterior Objeto a asignar
     */
    private void EstablecerObjetoAnterior(ListaCircularDoble<T> anterior){
        this.anterior = anterior;
    }

}
