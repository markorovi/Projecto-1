package com.github.monstertecg.plantillasDeListas;

/**
 * Crea lista tipo pila
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.4
 */
public class ListaStack<T> {
    private ListaStack ultimo = null;
    private ListaStack anterior = null;
    private T valor = null;

    /**
     * Constructor
     *
     * @param valor
     */
    public ListaStack(T valor){
        this.valor = valor;
        if (this.ultimo == null){
            this.ultimo = this;
        }
    }

    /**
     * Agrega un nuevo último elemento
     *
     * @param valor
     */
    public void AgregarElemento(T valor){
        ListaStack siguienteTemporal = new ListaStack(valor);
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
        if (ultimo.anterior == null) {
            throw new IndexOutOfBoundsException("No hay elementos compa, la lista está vacía.");
        }
        ActualizarUltimo(this.ultimo.anterior);
    }

    /**
     * Establece el último para que todos tengan la misma referencia al mismo último.
     */
    private void ActualizarUltimo (ListaStack ultimo){
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
    private void EstablecerAnterior(ListaStack anterior){
        this.anterior = anterior;
    }

}
