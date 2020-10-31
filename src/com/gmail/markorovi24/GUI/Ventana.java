package com.gmail.markorovi24.GUI;
import javax.swing.*;

public class Ventana {
    JFrame Ventana = new JFrame();

    public void crearVentana(String name, int width, int height) {
        Ventana.setTitle(name);
        Ventana.setSize(width, height)
        Ventana.setLayout(null);
        Ventana.setResizable(false);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void habilitarVentana() {
        Ventana.setVisible(true);
    }
}
