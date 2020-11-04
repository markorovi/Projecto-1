package com.gmail.markorovi24.GUI;

import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.gmail.markorovi24.Mediator.MediadorGeneradorCartas;

public class test {
    public static void main(String[] args){
        VentanaMenu test = new VentanaMenu();
        test.crearVentana("hola", 640,480);
        test.configurarMenu();

        ListaCircularDoble<Integer> prueba = new ListaCircularDoble<Integer>();

        prueba.Agregar(1);
        prueba.Agregar(2);
        prueba.Agregar(3);
        prueba.Agregar(4);

        System.out.println(prueba.Obtener(0));
        System.out.println(prueba.Obtener(1));
        System.out.println(prueba.Obtener(2));
        System.out.println(prueba.Obtener(3));


        prueba.Eliminar(0);


    }
}
