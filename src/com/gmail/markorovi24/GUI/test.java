package com.gmail.markorovi24.GUI;

import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.gmail.markorovi24.Mediator.MediadorGeneradorCartas;

public class test {
    public static void main(String[] args){
        VentanaMenu test = new VentanaMenu();
        test.crearVentana("hola", 640,480);
        test.configurarMenu();

        ListaCircularDoble<Integer> prueba = new ListaCircularDoble<Integer>();
    }
}
