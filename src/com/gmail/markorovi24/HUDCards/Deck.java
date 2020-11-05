package com.gmail.markorovi24.HUDCards;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Mediator.MediadorMyCards;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.util.Random;

public class Deck {
    JLabel Deck = new JLabel();

    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Deck.setIcon(new ImageIcon(path + name));
    }

    public JLabel getDeck(){
        return Deck;
    }

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

    public void builderWithOutActions(int x, int y, int width, int height) {
        Deck.setOpaque(true);
        Deck.setBounds(x, y, width, height);
        setImage("Interrogacion.jpg");
    }
}