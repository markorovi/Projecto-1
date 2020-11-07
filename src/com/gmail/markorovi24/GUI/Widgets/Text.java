package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que se encarga de crear los textos que se ven en la pantalla
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.2
 */
public class Text {
    private JTextArea Texto = new JTextArea();
    private JScrollPane Scroll = new JScrollPane(Texto);

    /**
     * Get de la instancia que necesita el JFrame para funcionor
     * @return El scroll
     */
    public JScrollPane getText(){
        return Scroll;
    }

    /**
     * Contructor para el textarea, los parámetros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void builder(int x, int y, int width, int height, Color color) {
        Scroll.setBounds(x, y, width, height);
        Scroll.setOpaque(true);
        Scroll.setBackground(color);
        Texto.setEditable(false);
    }

    /**
     * Set para el texto que contiene el jtextarea
     * @param texto Texto a contener
     */
    public void setText(String texto){
        Texto.setText(texto);
    }

}
