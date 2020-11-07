package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;
import com.gmail.markorovi24.GUI.Ventana;
import com.gmail.markorovi24.GUI.VentanaJuego;

/**
 * Singleton y mediador para t0do lo relacionado con la logica de las cartas
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.5
 */

public class MediadorMyCards {
    private VentanaJuego Ventana;
    private ListaStack<Cartas> MyDeck;
    private ListaDoble<Cartas> Historial = new ListaDoble<Cartas>();
    private ListaCircularDoble<Cartas> Hand;
    private int Contador = 16;
    private int HandCards = 4;
    private int ContadorHistorial = 0;
    private int Index = 0;
    private int remainingCards = 1;


    static MediadorMyCards Mediador;

    /**
     * Singleton
     * @return Mediador
     */
    public static synchronized MediadorMyCards obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorMyCards();
        } return Mediador;
    }

    /**
     * Get para la ventana del juego
     * @return VentanaJUego
     */
    public VentanaJuego getVentana() {
        return Ventana;
    }

    /**
     * Get para verificar cuantas cartas le quedan por tomar al usuario
     * @return Un entero que referencia a las cartas que se pueden tomar
     */
    public int getRemainingCards() {
        return remainingCards;
    }


    /**
     * Set para el valor de remainingCards
     * @param remainingCards El valor que asignarle al atributto
     */
    public void setRemainingCards(int remainingCards) {
        this.remainingCards = remainingCards;
    }

    /**
     * Set para la ventana que se almacena
     * @param ventana Valor a asignar
     */
    public void setVentana(VentanaJuego ventana) {
        Ventana = ventana;
    }

    /**
     * Get para el entero que almacena el indice en que se encuentra el historial
     * @return Entero del indice correspondiente
     */
    public int getIndex() {
        return Index;
    }

    /**
     * Set para el entero que almacena el indice en que se encuentra el historial
     * @param index Valor que asignar
     */
    public void setIndex(int index) {
        Index = index;
    }

    /**
     * Get para el valor que almacena cuantas cartas hay en el historial
     * @return Número de cartas en el historial
     */
    public int getContadorHistorial() {
        return ContadorHistorial;
    }

    /**
     * Set para el valor que almacena cuantas cartas hay en el hisroail
     * @param contadorHistorial Valor el cual asignar
     */
    public void setContadorHistorial(int contadorHistorial) {
        ContadorHistorial = contadorHistorial;
    }

    /**
     * Get de las cartas que el usuario tiene en mano
     * @return Entero de cuantas cartas hay en mano
     */
    public int getHandCards() {
        return HandCards;
    }

    /**
     * Set para el número de cartas que el usuario tiene en mano
     * @param handCards Valor a asignar
     */
    public void setHandCards(int handCards) {
        HandCards = handCards;
    }

    /**
     * Get para obtener la lista de cartas en mano
     * @return La lista correspondiente a la mano
     */
    public ListaCircularDoble<Cartas> getHand() {
        return Hand;
    }

    /**
     * Set para la lista que corresponde a la mano del usuario
     * @param hand Valor a asignarle a la lista
     */
    public void setHand(ListaCircularDoble<Cartas> hand) {
        Hand = hand;
    }

    /**
     * Agregar una carta a la mano del usuario
     * @param card Carta a agregar
     */
    public void agregarHand(Cartas card){
        Hand.Agregar(card);
    }

    /**
     * Get para obtener la lista que referencia al historial
     * @return La lista de cartas del historial
     */
    public ListaDoble<Cartas> getHistorial() {
        return Historial;
    }

    /**
     * Set para la lista que hace referencia al historial
     * @param historial Valor que asignar
     */
    public void setHistorial(ListaDoble<Cartas> historial) {
        Historial = historial;
    }

    /**
     * Agregar una carta al historial
     * @param card Carta a agregar
     */
    public void agregarHistorial(Cartas card){
        this.Historial.Agregar(card);
    }

    /**
     * Elminiar una carta de la mano del usuario
     * @param i Indice de la carta a eliminar
     */
    public void eliminarHandCard(int i){
        this.Hand.Eliminar(i);
        HandCards--;
    }

    /**
     * Get para el deck de cartas del usuario
     * @return Valor a obtener de la lista del deck
     */
    public ListaStack<Cartas> getMyDeck() {
        return MyDeck;
    }

    /**
     * Set para la lista de cartas del deck del usuario
     * @param myDeck Valor a asignar a la lista del deck
     */
    public void setMyDeck(ListaStack<Cartas> myDeck) {
        MyDeck = myDeck;
    }

    /**
     * Tomar una carta de la pila del deck
     */
    public void takeCard(){
        if (Contador != 0 && getHandCards() < 10 && remainingCards != 0) {
            Cartas Carta = MyDeck.ObtenerValor();
            MyDeck.EliminarUltimoElemento();
            agregarHand(Carta);
            setHandCards(getHandCards() + 1);
            Ventana.actualizarHand();
            Contador--;
            remainingCards--;
        }
    }

    /**
     * Actualizar el daño cuando se juega una carta o actualizar la lista de efectos de las cartas
     * @param card
     */
    public void jugarCarta(Cartas card){
        String type = card.getTipo();

        if(type.equals("esbirros")){
            Esbirros esbirro = (Esbirros) card;
            MediadorVidaMana.obtenerInstancia().producirDano(card.getDano());
            Ventana.actualizarDano();

        } else if (type.equals("hechizos")){
            Hechizos hechizo = (Hechizos) card;
            MediadorVidaMana.obtenerInstancia().producirDano(card.getDano());
            Ventana.actualizarDano();

        } else if (type.equals("secretos")){
            Secretos secreto = (Secretos) card;
            MediadorVidaMana.obtenerInstancia().producirDano(card.getDano());
            Ventana.actualizarDano();
            for(int i = 0; i < 10; i++){
                if(Integer.parseInt(card.getId()) == i){
                    MediadorEfectos.obtenerInstancia().setEfectosEn(i, true);
                }
            }

        }
    }
}
