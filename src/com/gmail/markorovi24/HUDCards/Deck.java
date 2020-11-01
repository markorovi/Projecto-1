package com.gmail.markorovi24.HUDCards;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Deck {
    JLabel Deck = new JLabel();
    Random azar = new Random();

    //Esto esta intentando la interaccion con el click
    private void randomTest(){
        Deck.setBackground(new Color(azar.nextInt(256), azar.nextInt(256), azar.nextInt(256), azar.nextInt(256)));
    }

    public JLabel getDeck(){
        return Deck;
    }

    public void builder(int x, int y, int width, int height){
        Deck.setOpaque(true);;
        Deck.setBounds(x, y, width, height);
        randomTest();
        Deck.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Deck.setBackground(new Color(azar.nextInt(256), azar.nextInt(256), azar.nextInt(256), azar.nextInt(256)));
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
}