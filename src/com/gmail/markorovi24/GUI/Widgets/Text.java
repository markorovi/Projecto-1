package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;
import java.awt.*;

public class Text {
    JTextArea Texto = new JTextArea();
    JScrollPane Scroll = new JScrollPane(Texto);

    public JScrollPane getText(){
        return Scroll;
    }

    //Necesito las listas enlazadas para asi poder hacer que esto reciba una lista
    public void builder(int x, int y, int width, int height, Color color) {
        Scroll.setBounds(x, y, width, height);
        Scroll.setOpaque(true);
        Scroll.setBackground(color);
        Texto.setEditable(false);
    }

}
