package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.json.Json;
import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;

/**
 * Singleton y mediador para t0do lo al envio de datos
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.5.0
 */
public class MediadorServidor {
    static MediadorVidaMana ControlVidaMana = MediadorVidaMana.obtenerInstancia();
    private boolean MyTurn;
    private int contadorBloqueos = 0;

    /**
     * Get para el contador de los bloqueos
     * @return Entero de bloqueos restantes
     */
    public int getContadorBloqueos() {
        return contadorBloqueos;
    }

    /**
     * Set para el contador de los bloqueos
     * @param contadorBloqueos Valor a asignar
     */
    public void setContadorBloqueos(int contadorBloqueos) {
        this.contadorBloqueos = contadorBloqueos;
    }



    static MediadorServidor Mediador;

    /**
     * Singleton
     * @return Mediador
     */
    public static synchronized MediadorServidor obtenerInstancia(){
        if(Mediador == null){
            Mediador = new MediadorServidor();
        } return Mediador;
    }

    /**
     * Set para el booleano que verifica si es el turno del usuario de jugar o no
     * @param value Valor a asignar
     */
    public void setMyTurn(boolean value){
        this.MyTurn = value;
    }

    /**
     * Get para el booleano que verifica si es el turno del usuario de jugar o no
     * @return Booleano correspondiente a si es el turno del usuario de jugar o no
     */
    public boolean getMyTurn(){
        return MyTurn;
    }

    /**
     * Enviar la carta que se juega
     * @param card Carta a enviar
     */
    public void enviarCarta(Cartas card){
        String tipo = card.getTipo();
        String paquete = Json.VarToString(tipo, card.getId(), ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), false, "jugar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    /**
     * El envio de datos cuando se da al botón de saltar
     */
    public void noJugar(){
        MediadorEfectos.obtenerInstancia().verificarEfectos();
        String paquete = Json.VarToString("", "", ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), true, "jugar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }


    /**
     * El envio de datos cuando se es congelado
     */
    public void congelado(){
        String paquete = Json.VarToString("", "", ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), true, "saltar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    /**
     * Método que genera las cartas recibidas y las agrega al historial
     * @param card Carta recibida
     */
    public void recibido(Cartas card){
        MediadorVidaMana temporal = MediadorVidaMana.obtenerInstancia();
        String tipo = card.getTipo();

        if(tipo.equals("esbirros")){
            Esbirros esbirro = (Esbirros) card;
            MediadorMyCards.obtenerInstancia().agregarHistorial(esbirro);
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarHistorial();
            esbirro.Ataque();
        } else if (tipo.equals("hechizos")){
            Hechizos hechizo = (Hechizos) card;
            MediadorMyCards.obtenerInstancia().agregarHistorial(hechizo);
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarHistorial();
        } else if (tipo.equals("secretos")){
            Secretos secreto = (Secretos) card;
            MediadorMyCards.obtenerInstancia().agregarHistorial(secreto);
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarHistorial();
        }
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarCartaHistorial();
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarEfectos();
        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 1){
            temporal.setMyMana(temporal.getMyMana() + (int) (temporal.getMyMana()*0.25));
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
        }

        MediadorEfectos.obtenerInstancia().verificarEfectos();
    }

    /**
     * Cuando se reciben datos que le permiten al usuario volver a jugar
     */
    public void saltado(){
        MediadorVidaMana temporal = MediadorVidaMana.obtenerInstancia();
        MediadorServidor.obtenerInstancia().setMyTurn(true);
        MediadorMyCards.obtenerInstancia().setRemainingCards(1);
        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 1){
            temporal.setMyMana(temporal.getMyMana() + (int) (temporal.getMyMana()*0.25));
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
        }
    }

}
