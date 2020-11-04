package com.gmail.markorovi24.GUI;
import com.gmail.markorovi24.GUI.Widgets.Button;
import com.gmail.markorovi24.GUI.Widgets.Label;
import com.gmail.markorovi24.HUDCards.Deck;
import com.gmail.markorovi24.HUDCards.MyCards;
import com.gmail.markorovi24.HUDCards.RivalCards;
import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.GUI.Widgets.*;
import com.gmail.markorovi24.Mediator.MediadorMyCards;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaJuego extends Ventana{
    public void configurarMenu(MediadorCartasHUD ControlCartas, MediadorVidaMana ControlVidaMana, MediadorMyCards ControlDecks){
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

        MyCard1.builder(180, 490, 120, 160, ControlCartas);
        MyCard2.builder(330, 490, 120, 160, ControlCartas);
        MyCard3.builder(480, 490, 120, 160, ControlCartas);
        MyCard4.builder(630, 490, 120, 160, ControlCartas);
        MyCard5.builder(780, 490, 120, 160, ControlCartas);
        MyCard6.builder(180, 700, 120, 160, ControlCartas);
        MyCard7.builder(330, 700, 120, 160, ControlCartas);
        MyCard8.builder(480, 700, 120, 160, ControlCartas);
        MyCard9.builder(630, 700, 120, 160, ControlCartas);
        MyCard10.builder(780, 700, 120, 160, ControlCartas);

        Deck GameDeck = new Deck();
        Deck CardsPlayed = new Deck();

        RivalCards RivalCard1 = new RivalCards();
        RivalCards RivalCard2 = new RivalCards();
        RivalCards RivalCard3 = new RivalCards();
        RivalCards RivalCard4 = new RivalCards();
        RivalCards RivalCard5 = new RivalCards();

        RivalCard1.builder(180, 30, 120, 160);
        RivalCard2.builder(330, 30, 120, 160);
        RivalCard3.builder(480, 30, 120, 160);
        RivalCard4.builder(630, 30, 120, 160);
        RivalCard5.builder(780, 30, 120, 160);

        GameDeck.builderWithActions(330, 250, 120, 160, ControlDecks);
        CardsPlayed.builderWithOutActions(630, 250, 120, 160);

        Text ActiveCards = new Text();
        ActiveCards.builder(15, 750, 150, 100, Color.white);

        Text SelectedCardEffects = new Text();
        SelectedCardEffects.builder(15, 600, 150, 100, Color.white);

        Text ListCardEffect = new Text();
        ListCardEffect.builder(925, 600, 150, 100, Color.white);

        Label Texto1 = new Label();
        Texto1.builder(15, 710, 150, 30, Color.white, "Efectos activos");

        Label Texto2 = new Label();
        Texto2.builder(15, 560, 150, 30, Color.white, "Carta Seleccionada");

        Label Texto3 = new Label();
        Texto3.builder(925, 560, 150, 30, Color.white, "Carta del Deck");

        Label Texto4 = new Label();
        Texto4.builder(480, 250, 120, 30, Color.white, "Vida rival: ");
        Texto4.setValue(ControlVidaMana.getGuestHP());

        Label Texto5 = new Label();
        Texto5.builder(15, 500, 150, 30, Color.white, "Vida: ");
        Texto5.setValue(ControlVidaMana.getHostHP());

        Label Texto6 = new Label();
        Texto6.builder(925, 500, 150, 30, Color.white, "Maná: ");
        Texto6.setValue(ControlVidaMana.getHostMana());

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
        Ventana.add(CardsPlayed.getDeck());
        Ventana.add(Boton1.getBoton());
        Ventana.add(Boton2.getBoton());
        Ventana.add(Boton3.getBoton());
        Ventana.add(Boton4.getBoton());
        Ventana.add(ActiveCards.getText());
        Ventana.add(SelectedCardEffects.getText());
        Ventana.add(ListCardEffect.getText());
        Ventana.add(Texto1.getLabel());
        Ventana.add(Texto2.getLabel());
        Ventana.add(Texto3.getLabel());
        Ventana.add(Texto4.getLabel());
        Ventana.add(Texto5.getLabel());
        Ventana.add(Texto6.getLabel());

        crearVentana("test", 1110,910);
        habilitarVentana();
    }


}
