package com.gmail.markorovi24.Mediator;

import com.gmail.markorovi24.GUI.VentanaJuego;

public class MediadorCartasHUD {
    private boolean isACardUp = false;
    VentanaJuego Ventana;

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
