package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que se encarga de crear los labels que se ven en la pantalla
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.2
 */
public class Label {
    private JLabel Label = new JLabel();
    private String string;

    /**
     * Get de la instancia que necesita el JFrame para funcionor
     * @return El label
     */
    public JLabel getLabel(){
        return Label;
    }

    /**
     * Contructor para el label, los parámetros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param texto
     */
    public void builder(int x, int y, int width, int height, Color color, String texto) {
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setVerticalAlignment(SwingConstants.CENTER);
        Label.setBounds(x, y, width, height);
        Label.setOpaque(true);
        Label.setBackground(color);
        Label.setText(texto);
        this.string = texto;
    }

    /**
     * Set para el texto que el label contiene
     * @param texto Texto a contener
     */
    public void setText(String texto){
        Label.setText(texto);
    }

    /**
     * Agrega valores al texto ya existente en el label
     * @param value Texto a agregar
     */
    public void setValue(int value){
        setText(this.string + String.valueOf(value));
    }

}
