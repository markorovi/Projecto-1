package com.gmail.markorovi24.HUDCards;

import javax.swing.*;
import java.awt.*;

public class RivalCards {
    JLabel Card = new JLabel();

    public void builder(Color color, int x, int y, int width, int height){
        Card.setOpaque(true);
        Card.setBackground(color);
        Card.setBounds(x, y, width, height);
    }

    public JLabel getCard(){
        return Card;
    }
}
