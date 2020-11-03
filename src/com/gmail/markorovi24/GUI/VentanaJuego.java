package com.gmail.markorovi24.GUI;
import com.gmail.markorovi24.GUI.Widgets.Button;
import com.gmail.markorovi24.GUI.Widgets.Label;
import com.gmail.markorovi24.HUDCards.Deck;
import com.gmail.markorovi24.HUDCards.MyCards;
import com.gmail.markorovi24.HUDCards.PlayedDeck;
import com.gmail.markorovi24.HUDCards.RivalCards;
import com.gmail.markorovi24.Mediator.MediadorCartas;
import com.gmail.markorovi24.GUI.Widgets.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaJuego extends Ventana{
    MediadorCartas Control = new MediadorCartas();
    public void configurarMenu(){
        MyCards MyCard1 = new MyCards();
        MyCards MyCard2 = new MyCards();
        MyCards MyCard3 = new MyCards();
        MyCards MyCard4 = new MyCards();
        MyCards MyCard5 = new MyCards();
        MyCards MyCard6 = new MyCards();
        MyCards MyCard7 = new MyCards();
        MyCards MyCard8 = new MyCards();
        MyCards MyCard9 = new MyCards();
        MyCards MyCard10 = new MyCards();

        MyCard1.builder(Color.black, 180, 490, 120, 160, Control);
        MyCard2.builder(Color.black, 330, 490, 120, 160, Control);
        MyCard3.builder(Color.black, 480, 490, 120, 160, Control);
        MyCard4.builder(Color.black, 630, 490, 120, 160, Control);
        MyCard5.builder(Color.black, 780, 490, 120, 160, Control);
        MyCard6.builder(Color.black, 180, 700, 120, 160, Control);
        MyCard7.builder(Color.black, 330, 700, 120, 160, Control);
        MyCard8.builder(Color.black, 480, 700, 120, 160, Control);
        MyCard9.builder(Color.black, 630, 700, 120, 160, Control);
        MyCard10.builder(Color.black, 780, 700, 120, 160, Control);

        Deck GameDeck = new Deck();
        PlayedDeck CardsPlayed = new PlayedDeck();

        RivalCards RivalCard1 = new RivalCards();
        RivalCards RivalCard2 = new RivalCards();
        RivalCards RivalCard3 = new RivalCards();
        RivalCards RivalCard4 = new RivalCards();
        RivalCards RivalCard5 = new RivalCards();

        RivalCard1.builder(Color.black, 180, 30, 120, 160);
        RivalCard2.builder(Color.black, 330, 30, 120, 160);
        RivalCard3.builder(Color.black, 480, 30, 120, 160);
        RivalCard4.builder(Color.black, 630, 30, 120, 160);
        RivalCard5.builder(Color.black, 780, 30, 120, 160);

        GameDeck.builder(330, 250, 120, 160);
        CardsPlayed.builder(630, 250, 120, 160);

        Text ActiveCards = new Text();
        ActiveCards.builder(15, 750, 150, 100, Color.white);

        Label Texto1 = new Label();
        Texto1.builder(15, 710, 150, 30, Color.white, "Efectos activos");

        Button Boton1 = new Button();
        Boton1.builder(950, 800, 100,50, "Lanzar", Color.white);
        Boton1.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        Button Boton2 = new Button();
        Boton2.builder(630, 412, 50,35, "←", Color.white);
        Boton2.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Button Boton3 = new Button();
        Boton3.builder(700, 412, 50,35, "→", Color.white);
        Boton3.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Button Boton4 = new Button();
        Boton4.builder(950, 730, 100,50, "Saltar", Color.white);
        Boton4.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        Ventana.add(MyCard1.getCard());
        Ventana.add(MyCard2.getCard());
        Ventana.add(MyCard3.getCard());
        Ventana.add(MyCard4.getCard());
        Ventana.add(MyCard5.getCard());
        Ventana.add(MyCard6.getCard());
        Ventana.add(MyCard7.getCard());
        Ventana.add(MyCard8.getCard());
        Ventana.add(MyCard9.getCard());
        Ventana.add(MyCard10.getCard());
        Ventana.add(RivalCard1.getCard());
        Ventana.add(RivalCard2.getCard());
        Ventana.add(RivalCard3.getCard());
        Ventana.add(RivalCard4.getCard());
        Ventana.add(RivalCard5.getCard());
        Ventana.add(GameDeck.getDeck());
        Ventana.add(CardsPlayed.getPlayedDeck());
        Ventana.add(Boton1.getBoton());
        Ventana.add(Boton2.getBoton());
        Ventana.add(Boton3.getBoton());
        Ventana.add(Boton4.getBoton());
        Ventana.add(ActiveCards.getText());
        Ventana.add(Texto1.getLabel());

        crearVentana("test", 1110,910);
        habilitarVentana();
    }


}
