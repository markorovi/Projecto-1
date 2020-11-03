package com.gmail.markorovi24.HUDCards;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class RivalCards {
    JLabel Card = new JLabel();

    public void builder(int x, int y, int width, int height){
        Card.setOpaque(true);
        setImage("Interrogacion.jpg");
        Card.setBounds(x, y, width, height);
    }

    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Card.setIcon(new ImageIcon(path + name));

    }
    public JLabel getCard(){
        return Card;
    }
}
