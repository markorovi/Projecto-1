package com.github.monstertecg.listasEnlazadas;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2.0
 */
public class ListaStack<T> {
    private ListaStack<T> ultimo;
    private ListaStack<T> anterior = null;
    private ListaStack<T> referencia;
    private T valor = null;

    public ListaStack(){
        this.ultimo = null;
        this.referencia = this;
    }

    /**
     * Constructor
     *
     * @param valor
     */
    public ListaStack(T valor, ListaStack<T> referencia){
        this.valor = valor;
        this.ultimo = this;
        this.referencia = referencia;
    }

    /**
     * Agrega un nuevo último elemento
     *
     * @param valor
     */
    public void Agregar(T valor){
        ListaStack siguienteTemporal = new ListaStack(valor, this.referencia);
        siguienteTemporal.EstablecerAnterior(this.ultimo);
        ActualizarUltimo(siguienteTemporal);
    }

    /**
     * cambia el valor del último elemento
     *
     * @param valor
     */
    public void CambiarValor(T valor) {
        this.ultimo.valor = valor;
    }

    /**
     * Elimina el último elemento de la pila
     *
     */
    public void EliminarUltimoElemento(){
        if (this.ultimo == null) {
            throw new IndexOutOfBoundsException("La pila está vacía.");
        } else if (ultimo.anterior == null) {
            this.referencia.ultimo = null;
            return;
        }
        ActualizarUltimo(this.ultimo.anterior);
    }

    /**
     * Establece el último para que todos tengan la misma referencia al mismo último.
     */
    private void ActualizarUltimo (ListaStack<T> ultimo){
        if (this.anterior == null){
            this.ultimo = ultimo;
            return;
        } else {
            this.ultimo = ultimo;
            this.anterior.ActualizarUltimo(ultimo);
        }
    }

    /**
     * Obtiene el largo de la pila
     *
     * @return largo
     */
    public int Largo(){
        if (ultimo == null){
            return 0;
        }
        return ultimo.ConteoDeLargo();
    }

    /**
     * Método recursivo perteneciente a Largo() para conteo del largo de la pila
     * @return largo
     */
    private int ConteoDeLargo(){
        if (this.anterior == null){
            return 1;
        } else {
            return this.anterior.ConteoDeLargo() + 1;
        }
    }

    /**
     * Devuelve el valor del último elemento
     *
     * @return ultimo valor
     */
    public T ObtenerValor(){
        return (T) this.ultimo.valor;
    }

    /**
     * Establece el elemento anterior
     *
     * @param anterior
     */
    private void EstablecerAnterior(ListaStack<T> anterior){
        this.anterior = anterior;
    }

}
