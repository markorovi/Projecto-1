package com.gmail.markorovi24.HUDCards;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PlayedDeck {
    JLabel PlayedDeck = new JLabel();
    Random azar = new Random();

    //Esto esta intentando la interaccion con el click
    private void randomTest(){
        PlayedDeck.setBackground(new Color(azar.nextInt(256), azar.nextInt(256), azar.nextInt(256), azar.nextInt(256)));
    }

    public JLabel getPlayedDeck(){
        return PlayedDeck;
    }

    public void builder(int x, int y, int width, int height){
        PlayedDeck.setOpaque(true);;
        PlayedDeck.setBounds(x, y, width, height);
        randomTest();
        PlayedDeck.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                PlayedDeck.setBackground(new Color(azar.nextInt(256), azar.nextInt(256), azar.nextInt(256), azar.nextInt(256)));
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
