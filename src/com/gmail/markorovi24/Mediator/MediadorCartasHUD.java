package com.gmail.markorovi24.Mediator;

import com.gmail.markorovi24.GUI.VentanaJuego;

public class MediadorCartasHUD {
    private boolean isACardUp = false;
    VentanaJuego Ventana;

    static MediadorCartasHUD Mediador;
    public static synchronized MediadorCartasHUD obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorCartasHUD();
        } return Mediador;
    }

    public VentanaJuego getVentana() {
        return Ventana;
    }

    public void setVentana(VentanaJuego ventana) {
        Ventana = ventana;
    }



    public void setCardUp(boolean ACardUp) {
        Ventana.actualizarCartaSeleccionada();
        isACardUp = ACardUp;
    }

    public boolean getCardsState() {
        return isACardUp;
    }

}
