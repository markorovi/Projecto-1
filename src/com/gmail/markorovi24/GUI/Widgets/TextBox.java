package com.gmail.markorovi24.GUI.Widgets;

import javax.swing.*;

public class TextBox {
    JTextField Box = new JTextField();

    public JTextField getBox(){
        return Box;
    }

    public void builder(int x, int y, int width, int height){
        Box.setBounds(x, y, width, height);
    }

    public String getText(){
        return Box.getText();
    }
}
