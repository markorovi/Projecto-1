package com.gmail.markorovi24.Mediator;

import com.gmail.markorovi24.GUI.VentanaJuego;

/**
 * Singleton y mediador para t0do lo relacionado con lo visual de las cartas en la ventana de juego
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.5.0
 */
public class MediadorCartasHUD {
    private boolean isACardUp = false;
    private VentanaJuego Ventana;
    static MediadorCartasHUD Mediador;

    /**
     * Singleton
     * @return Mediador
     */
    public static synchronized MediadorCartasHUD obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorCartasHUD();
        } return Mediador;
    }

    /**
     * Get para la ventana del juego
     * @return VentanJuego
     */
    public VentanaJuego getVentana() {
        return Ventana;
    }

    /**
     * Set para la ventana que hace referencia a la ventana del juego
     * @param ventana Ventana a settear
     */
    public void setVentana(VentanaJuego ventana) {
        Ventana = ventana;
    }

    /**
     * Set para el valor que se refiere a si hay una carta seleccionada
     * @param ACardUp
     */
    public void setCardUp(boolean ACardUp) {
        Ventana.actualizarCartaSeleccionada();
        isACardUp = ACardUp;
    }

    /**
     * Get para saber si una carta se encuentra seleccionada.
     * @return boolean value
     */
    public boolean getCardsState() {
        return isACardUp;
    }

}
