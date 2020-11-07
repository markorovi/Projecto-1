package com.gmail.markorovi24.GUI;

import javax.swing.*;
/**
 * Clase que se encarga de ser la base para las demás ventanas.
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.0
 */
public class Ventana {
    protected JFrame Ventana = new JFrame();

    /**
     * Método que se encarga de generar las bases de la ventana una vez lista
     * @param name
     * @param width
     * @param height
     */
    public void crearVentana(String name, int width, int height) {
        Ventana.setTitle(name);
        Ventana.setSize(width, height);
        Ventana.setLayout(null);
        Ventana.setResizable(false);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Hace que la ventana sea visible
     */
    public void habilitarVentana() {
        Ventana.setVisible(true);
    }

    /**
     *  Get para el frame de la ventana
     * @return El JFrame
     */
    public JFrame getFrame(){
        return Ventana;
    }
}
