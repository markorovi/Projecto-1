package com.gmail.markorovi24.HUDCards;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

/**
 * Clase que hace referencia a todas los label que funcionan como la mano de cartas del oponente
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.5
 */
public class RivalCards {
    JLabel Card = new JLabel();

    /**
     * Constructor para el JLabel, los parametros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void builder(int x, int y, int width, int height){
        Card.setOpaque(true);
        setImage("Interrogacion.jpg");
        Card.setBounds(x, y, width, height);
    }

    /**
     * Se encarga de agregar la imagen al Jlabel
     * @param name Nombre de la imagen a agregar
     */
    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Card.setIcon(new ImageIcon(path + name));

    }

    /**
     * Get para el JLabel que el JFrame necesita para agregarlo.
     * @return
     */
    public JLabel getCard(){
        return Card;
    }
}
