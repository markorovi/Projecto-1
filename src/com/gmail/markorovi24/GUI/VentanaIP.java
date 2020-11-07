package com.gmail.markorovi24.GUI;

import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.GUI.Widgets.Button;
import com.gmail.markorovi24.GUI.Widgets.Label;
import com.gmail.markorovi24.GUI.Widgets.TextBox;
import com.gmail.markorovi24.Mediator.*;
import javax.naming.ldap.Control;
import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que se encarga de generar la ventana en donde el usuario ingresa datos para conectarse a un juego
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.1
 */
public class VentanaIP extends Ventana{
    private final Button Boton = new Button();
    private final TextBox IP = new TextBox();
    private final TextBox Port = new TextBox();
    private final Label Texto1 = new Label();
    private final Label Texto2 = new Label();

    /**
     * Se encarga de generar una ventana con todas las caracteristicas necesarias para que el usuario
     * consiga acceder a la ventana de juego
     */
    public void configurarMenu(){
        this.IP.builder(25,75, 100, 30);
        this.Port.builder(175, 75, 100, 30);
        this.Texto1.builder(25, 30, 100, 30, Color.white, "IP");
        this.Texto2.builder(175, 30, 100, 30, Color.white, "Port");


        this.Boton.builder(100, 150, 100,50, "Conectar", Color.white);
        this.Boton.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conectividad Host = Conectividad.SerInvitado();
                Host.EstablecerDestino(Port.getText(), IP.getText());
                (new Thread(Host::BucleDeConexion)).start();


                Ventana.setVisible(false);
                VentanaJuego Juego = new VentanaJuego();
                MediadorCartasHUD Control1 = MediadorCartasHUD.obtenerInstancia();
                MediadorVidaMana Control2 = MediadorVidaMana.obtenerInstancia();
                MediadorGeneradorCartas Control3 = MediadorGeneradorCartas.obtenerInstancia();
                MediadorMyCards Control4 = MediadorMyCards.obtenerInstancia();
                MediadorServidor Control5 = MediadorServidor.obtenerInstancia();
                MediadorEfectos Control6 = MediadorEfectos.obtenerInstancia();

                Control1.setVentana(Juego);
                Control4.setMyDeck(Control3.ramdomizadorDeck());
                Control4.setHand(Control3.ramdomizadorHand());
                Control4.setVentana(Juego);
                Control4.setRemainingCards(0);
                Control5.setMyTurn(false);
                Control6.generarLista();

                Juego.configurarMenu();
            }
        });

        Ventana.add(this.Boton.getBoton());
        Ventana.add(this.IP.getBox());
        Ventana.add(this.Port.getBox());
        Ventana.add(this.Texto1.getLabel());
        Ventana.add(this.Texto2.getLabel());
        crearVentana("Conectarse", 300,250);
        habilitarVentana();
    }


}
