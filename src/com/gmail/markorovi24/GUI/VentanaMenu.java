package com.gmail.markorovi24.GUI;
import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.Mediator.MediadorGeneradorCartas;
import com.gmail.markorovi24.Mediator.MediadorMyCards;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;

import javax.swing.*;

public class VentanaMenu extends Ventana{
    public void configurarMenu(){
        JButton b1 = new JButton("Test1");
        JButton b2 = new JButton("Test2");
        b1.setBounds(120,220, 100, 100);
        b2.setBounds(400,220, 100, 100);
        b1.addActionListener(e -> {
            //Codigo para el boton 1
            Ventana.setVisible(false);
            VentanaJuego Juego = new VentanaJuego();
            MediadorCartasHUD Control1 = new MediadorCartasHUD();
            MediadorVidaMana Control2 = new MediadorVidaMana();
            MediadorGeneradorCartas Control3 = new MediadorGeneradorCartas();
            MediadorMyCards Control4 = new MediadorMyCards();

            Control1.setVentana(Juego);
            Control4.setMyDeck(Control3.ramdomizadorDeck());
            Control4.setHand(Control3.ramdomizadorHand());
            Control4.setVentana(Juego);

            Juego.configurarMenu(Control1, Control2, Control4);
        });
        b2.addActionListener(e -> {
            //Codigo para el boton 2
        });
        Ventana.add(b1);
        Ventana.add(b2);
        habilitarVentana();
    }
}
