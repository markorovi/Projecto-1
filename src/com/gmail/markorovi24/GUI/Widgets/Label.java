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
public class Label {
    JLabel Label = new JLabel();
    String string;

    /**
     * Get de la instancia
     * @return
     */
    public JLabel getLabel(){
        return Label;
    }

    public void builder(int x, int y, int width, int height, Color color, String texto) {
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setVerticalAlignment(SwingConstants.CENTER);
        Label.setBounds(x, y, width, height);
        Label.setOpaque(true);
        Label.setBackground(color);
        Label.setText(texto);
        this.string = texto;
    }

    public void setText(String texto){
        Label.setText(texto);
    }

    public void setValue(int value){
        setText(this.string + String.valueOf(value));
    }

}
