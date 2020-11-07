package com.gmail.markorovi24.GUI;
import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.Mediator.*;

import javax.swing.*;

public class VentanaMenu extends Ventana{
    public void configurarMenu(){
        JButton b1 = new JButton("Hostear");
        JButton b2 = new JButton("Conectarse");
        b1.setBounds(120,220, 100, 100);
        b2.setBounds(400,220, 100, 100);
        b1.addActionListener(e -> {
            Conectividad Host = Conectividad.SerAnfitrion();
            (new Thread(Host::BucleDeConexion)).start();

            Ventana.setVisible(false);
            VentanaJuego Juego = new VentanaJuego();
            MediadorCartasHUD Control1 = MediadorCartasHUD.obtenerInstancia();
            //MediadorVidaMana Control2 = MediadorVidaMana.obtenerInstancia();
            MediadorGeneradorCartas Control3 = MediadorGeneradorCartas.obtenerInstancia();
            MediadorMyCards Control4 = MediadorMyCards.obtenerInstancia();
            MediadorServidor Control5 = MediadorServidor.obtenerInstancia();
            MediadorEfectos Control6 = MediadorEfectos.obtenerInstancia();


            Control1.setVentana(Juego);
            Control4.setMyDeck(Control3.ramdomizadorDeck());
            Control4.setHand(Control3.ramdomizadorHand());
            Control4.setVentana(Juego);
            Control5.setMyTurn(true);
            Control6.generarLista();

            new Thread() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Tu ip es la " + Conectividad.obtenerInstancia().ObtenerOrigen()[1] + " con puerto " + Conectividad.obtenerInstancia().ObtenerOrigen()[0]);
                    while (!Host.isConnected()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }

                    Juego.configurarMenu();

                }
            }.start();


        });
        b2.addActionListener(e -> {

            Ventana.setVisible(false);
            VentanaIP IP = new VentanaIP();

            IP.configurarMenu();
        });
        Ventana.add(b1);
        Ventana.add(b2);
        habilitarVentana();
    }
}
