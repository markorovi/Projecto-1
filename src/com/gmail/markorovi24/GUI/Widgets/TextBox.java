package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;

/**
 * Clase que se encarga de crear las cajas de texto que se ven en la pantalla
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.2
 */
public class TextBox {
    private JTextField Box = new JTextField();

    /**
     * Get de la instancia que necesita el JFrame para funcionor
     * @return El textfield
     */
    public JTextField getBox(){
        return Box;
    }

    /**
     * Contructor para el textfield, los parámetros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void builder(int x, int y, int width, int height){
        Box.setBounds(x, y, width, height);
    }

    /**
     * Get para el texto que se encuentra en la caja de texto
     * @return El string correspondiente
     */
    public String getText(){
        return Box.getText();
    }
}
