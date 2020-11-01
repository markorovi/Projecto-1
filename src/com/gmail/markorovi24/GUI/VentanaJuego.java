package com.gmail.markorovi24.GUI;
import java.awt.*;


public class VentanaJuego extends Ventana{
    public void configurarMenu(){
        MyCards MyCard1 = new MyCards();
        MyCards MyCard2 = new MyCards();
        MyCards MyCard3 = new MyCards();
        MyCards MyCard4 = new MyCards();

        MyCard1.builder(Color.black, 180, 500, 120, 160);
        MyCard2.builder(Color.black, 330, 500, 120, 160);
        MyCard3.builder(Color.black, 480, 500, 120, 160);
        MyCard4.builder(Color.black, 630, 500, 120, 160);

        Ventana.add(MyCard1.getCard());
        Ventana.add(MyCard2.getCard());
        Ventana.add(MyCard3.getCard());
        Ventana.add(MyCard4.getCard());

        crearVentana("test", 960,720);
        habilitarVentana();
    }


}
