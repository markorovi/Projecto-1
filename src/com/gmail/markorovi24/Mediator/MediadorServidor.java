package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.json.Json;
import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.Cartas.Cartas;

public class MediadorServidor {
    MediadorVidaMana ControlVidaMana;


    //Contructor singleton
    static MediadorServidor Mediador;
    public static synchronized MediadorServidor obtenerInstancia(){
        if(Mediador == null){
            Mediador = new MediadorServidor();
        } return Mediador;
    }

    public void setControlVidaMana(MediadorVidaMana Control){
        ControlVidaMana = Control;
    }

    public void enviarCarta(Cartas card){
        String tipo = card.getTipo();
        String paquete = Json.VarToString(tipo, card.getId(), ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), false, "funca");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    public void noJugar(){
        String paquete = Json.VarToString("", "", ControlVidaMana.getMyHP(),ControlVidaMana.getMyMana(), true, "funca2");
        Conectividad.obtenerInstancia().EnviarMensaje(paquete);
    }

    public void recibido(Cartas card){
        System.out.println(card.getNombre());
    }
}
