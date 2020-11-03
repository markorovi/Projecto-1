package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;
import java.awt.*;

public class Button {
    JButton Boton = new JButton();

    public JButton getBoton(){
        return Boton;
    }

    public void builder(int x, int y, int width, int height, String text, Color color){
        Boton.setBounds(x, y, width, height);
        Boton.setText(text);
        Boton.setOpaque(true);
        Boton.setBackground(color);
    }
}
