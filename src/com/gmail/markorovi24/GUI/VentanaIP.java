package com.gmail.markorovi24.GUI;

import com.gmail.markorovi24.GUI.Widgets.Button;
import com.gmail.markorovi24.GUI.Widgets.Label;
import com.gmail.markorovi24.GUI.Widgets.TextBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaIP extends Ventana{
    private final Button Boton = new Button();
    private final TextBox IP = new TextBox();
    private final TextBox Port = new TextBox();
    private final Label Texto1 = new Label();
    private final Label Texto2 = new Label();

    public void configurarMenu(){
        this.IP.builder(25,75, 100, 30);
        this.Port.builder(175, 75, 100, 30);
        this.Texto1.builder(25, 30, 100, 30, Color.white, "IP");
        this.Texto2.builder(175, 30, 100, 30, Color.white, "Port");


        this.Boton.builder(100, 150, 100,50, "Conectar", Color.white);
        this.Boton.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Ventana.add(this.Boton.getBoton());
        Ventana.add(this.IP.getBox());
        Ventana.add(this.Port.getBox());
        Ventana.add(this.Texto1.getLabel());
        Ventana.add(this.Texto2.getLabel());
        crearVentana("test", 300,250);
        habilitarVentana();
    }


}
