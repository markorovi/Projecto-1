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

public class MediadorMyCards {
    VentanaJuego Ventana;
    ListaStack<Cartas> MyDeck;
    ListaDoble<Cartas> Historial = new ListaDoble<Cartas>();
    ListaCircularDoble<Cartas> Hand;
    int Contador = 16;
    int HandCards = 4;
    int ContadorHistorial = 0;
    int Index = 0;
    int remainingCards = 1;


    static MediadorMyCards Mediador;
    public static synchronized MediadorMyCards obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorMyCards();
        } return Mediador;
    }

    public VentanaJuego getVentana() {
        return Ventana;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public void setRemainingCards(int remainingCards) {
        this.remainingCards = remainingCards;
    }

    public void setVentana(VentanaJuego ventana) {
        Ventana = ventana;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public int getContadorHistorial() {
        return ContadorHistorial;
    }

    public void setContadorHistorial(int contadorHistorial) {
        ContadorHistorial = contadorHistorial;
    }

    public int getHandCards() {
        return HandCards;
    }

    public void setHandCards(int handCards) {
        HandCards = handCards;
    }

    public ListaCircularDoble<Cartas> getHand() {
        return Hand;
    }

    public void setHand(ListaCircularDoble<Cartas> hand) {
        Hand = hand;
    }

    public void agregarHand(Cartas card){
        Hand.Agregar(card);
    }

    public ListaDoble<Cartas> getHistorial() {
        return Historial;
    }

    public void setHistorial(ListaDoble<Cartas> historial) {
        Historial = historial;
    }

    public void agregarHistorial(Cartas card){
        this.Historial.Agregar(card);
    }

    public void eliminarHandCard(int i){
        this.Hand.Eliminar(i);
        HandCards--;
    }


    public ListaStack<Cartas> getMyDeck() {
        return MyDeck;
    }

    public void setMyDeck(ListaStack<Cartas> myDeck) {
        MyDeck = myDeck;
    }

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

        }
    }
}
