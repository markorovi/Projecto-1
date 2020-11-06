package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.json.Json;
import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;

public class MediadorServidor {
    MediadorVidaMana ControlVidaMana = MediadorVidaMana.obtenerInstancia();
    private boolean MyTurn;


    //Contructor singleton
    static MediadorServidor Mediador;
    public static synchronized MediadorServidor obtenerInstancia(){
        if(Mediador == null){
            Mediador = new MediadorServidor();
        } return Mediador;
    }

    public void setMyTurn(boolean value){
        this.MyTurn = value;
    }

    public boolean getMyTurn(){
        return MyTurn;
    }


    public void enviarCarta(Cartas card){
        String tipo = card.getTipo();
        String paquete = Json.VarToString(tipo, card.getId(), ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), false, "jugar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    public void noJugar(){
        String paquete = Json.VarToString("", "", ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), true, "jugar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }


    public void congelado(){
        String paquete = Json.VarToString("", "", ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), true, "saltar");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    public void recibido(Cartas card){
        MediadorEfectos.obtenerInstancia().verificarEfectos();
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
            for(int i = 0; i < 10; i++){
                if(Integer.parseInt(card.getId()) == i){
                    MediadorEfectos.obtenerInstancia().setEfectosEn(i, true);
                }
            }
        }
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarCartaHistorial();
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarEfectos();
        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 1){
            temporal.setMyMana(temporal.getMyMana() + (int) (temporal.getMyMana()*0.25));
            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
        }
    }

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
