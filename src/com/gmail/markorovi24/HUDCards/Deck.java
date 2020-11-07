package com.gmail.markorovi24.HUDCards;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Mediator.MediadorMyCards;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Clase que hace referencia a los 2 decks que se encuentran en el centro de la pantalla del juego
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.5
 */
public class Deck {
    private JLabel Deck = new JLabel();

    /**
     * Se encarga de actualizar la imagen del label
     * @param name Nombre de la imagen a utilizar
     */
    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Deck.setIcon(new ImageIcon(path + name));
    }

    /**
     * Get del jlabel que necesita el JFrame para agregarlo
     * @return el JLabel
     */
    public JLabel getDeck(){
        return Deck;
    }

    /**
     * Contructor con eventos para el deck de donde el usuario puede tomar las cartas, los parametros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     * @param Control
     */
    public void builderWithActions(int x, int y, int width, int height, MediadorMyCards Control){
        Deck.setOpaque(true);;
        Deck.setBounds(x, y, width, height);
        setImage("Interrogacion.jpg");
        Deck.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Control.takeCard();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * Constructor para el deck que funciona solo para la visualización del historial, los parametros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void builderWithOutActions(int x, int y, int width, int height) {
        Deck.setOpaque(true);
        Deck.setBounds(x, y, width, height);
        setImage("Interrogacion.jpg");
    }
}