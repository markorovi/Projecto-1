package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.gmail.markorovi24.Cartas.Cartas;

public class MediadorMyCards {
    ListaStack<Cartas> MyDeck;
    ListaDoble<Cartas> Historial;
    int Contador = 16;

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
