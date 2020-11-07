package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que se encarga de crear los botones que se ven en la pantalla
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.2
 */

public class Button {
    private JButton Boton = new JButton();

    /**
     * Get para la instancia que necesita el JFrame para funcionar
     * @return El boton
     */
    public JButton getBoton(){
        return Boton;
    }

    /**
     * Contructor para el botón, los parámetros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param color
     */
    public void builder(int x, int y, int width, int height, String text, Color color){
        Boton.setBounds(x, y, width, height);
        Boton.setText(text);
        Boton.setOpaque(true);
        Boton.setBackground(color);
    }
}
