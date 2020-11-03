package com.gmail.markorovi24.GUI;
import com.gmail.markorovi24.HUDCards.Deck;
import com.gmail.markorovi24.HUDCards.MyCards;
import com.gmail.markorovi24.HUDCards.PlayedDeck;
import com.gmail.markorovi24.HUDCards.RivalCards;
import com.gmail.markorovi24.Mediator.MediadorCartas;

import java.awt.*;


public class VentanaJuego extends Ventana{
    public void configurarMenu(){
        MediadorCartas Control = new MediadorCartas();

        MyCards MyCard1 = new MyCards();
        MyCards MyCard2 = new MyCards();
        MyCards MyCard3 = new MyCards();
        MyCards MyCard4 = new MyCards();

        MyCard1.builder(Color.black, 180, 500, 120, 160, Control);
        MyCard2.builder(Color.black, 330, 500, 120, 160, Control);
        MyCard3.builder(Color.black, 480, 500, 120, 160, Control);
        MyCard4.builder(Color.black, 630, 500, 120, 160, Control);

        Deck GameDeck = new Deck();
        PlayedDeck CardsPlayed = new PlayedDeck();

        RivalCards RivalCard1 = new RivalCards();
        RivalCards RivalCard2 = new RivalCards();
        RivalCards RivalCard3 = new RivalCards();
        RivalCards RivalCard4 = new RivalCards();

        RivalCard1.builder(Color.black, 180, 30, 120, 160);
        RivalCard2.builder(Color.black, 330, 30, 120, 160);
        RivalCard3.builder(Color.black, 480, 30, 120, 160);
        RivalCard4.builder(Color.black, 630, 30, 120, 160);

        GameDeck.builder(330, 250, 120, 160);
        CardsPlayed.builder(480, 250, 120, 160);

        Ventana.add(MyCard1.getCard());
        Ventana.add(MyCard2.getCard());
        Ventana.add(MyCard3.getCard());
        Ventana.add(MyCard4.getCard());
        Ventana.add(RivalCard1.getCard());
        Ventana.add(RivalCard2.getCard());
        Ventana.add(RivalCard3.getCard());
        Ventana.add(RivalCard4.getCard());
        Ventana.add(GameDeck.getDeck());
        Ventana.add(CardsPlayed.getPlayedDeck());

        crearVentana("test", 1110,910);
        habilitarVentana();
    }


}
