package com.gmail.markorovi24.GUI;
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
            Juego.configurarMenu();
        });
        b2.addActionListener(e -> {
            //Codigo para el boton 2
        });
        Ventana.add(b1);
        Ventana.add(b2);
        habilitarVentana();
    }
}