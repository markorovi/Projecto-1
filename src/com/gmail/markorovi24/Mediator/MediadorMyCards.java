package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.gmail.markorovi24.Cartas.Cartas;

public class MediadorMyCards {
    ListaStack<Cartas> MyDeck;
    ListaDoble<Cartas> Historial = new ListaDoble<Cartas>();
    ListaCircularDoble<Cartas> Hand;
    int Contador = 16;
    int HandCards = 4;

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

    public Cartas takeCard(){
        if (Contador != 0) {
            Cartas Carta = MyDeck.ObtenerValor();
            MyDeck.EliminarUltimoElemento();
            Contador--;
            return Carta;
        } else {
            return null;
        }
    }
}
