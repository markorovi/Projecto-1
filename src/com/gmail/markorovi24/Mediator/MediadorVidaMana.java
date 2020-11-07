package com.gmail.markorovi24.Mediator;

import com.gmail.markorovi24.GUI.Ventana;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Singleton y mediador para t0do lo relacionado con la vida y el maná
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.5.0
 */

public class MediadorVidaMana {
    static MediadorVidaMana Mediador;
    private int MyHP = 1000;
    private int MyMana = 200;
    private int RivalHP = 1000;

    /**
     * Singleton
     * @return Mediador
     */
    public static synchronized MediadorVidaMana obtenerInstancia() {
        if (Mediador == null) {
            Mediador = new MediadorVidaMana();
        }
        return Mediador;
    }

    /**
     * Get para la vida del usuario
     * @return Entero de vida
     */
    public int getMyHP() {
        return MyHP;
    }

    /**
     * Set para la vida del usuario
     * @param myHP Entero a asignar
     */
    public void setMyHP(int myHP) {
        MyHP = myHP;
    }

    /**
     * Get para el maná del usuario
     * @return Entero de maná
     */
    public int getMyMana() {
        return MyMana;
    }

    /***
     * Set para el maná del usuario, no puede ser mayor a 1000
     * @param myMana Valor a asignar al maná del usuario
     */
    public void setMyMana(int myMana) {
        MyMana = myMana;
        if (MyMana > 1000) {
            MyMana = 1000;
        }
    }

    /**
     * Get para el entero de la vida del rival
     * @return Entero de la vida del rival
     */
    public int getRivalHP() {
        return RivalHP;
    }

    /**
     * Set para la vida del rival
     * @param rivalHP Entero de la vida del rival
     */
    public void setRivalHP(int rivalHP) {
        RivalHP = rivalHP;
    }

    /**
     * Método que se dispara cada vez para verificar si alguno de los jugadores ha ganado
     */
    public void checkWinCondition() {
        if (this.RivalHP <= 0) {
            JFrame frame = MediadorCartasHUD.obtenerInstancia().getVentana().getFrame();
            JOptionPane.showMessageDialog(frame, "Ganaste, grande, campeón, máquina, fiera. Ahora fuera de aquí.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        } else if (this.MyHP <= 0) {
            JFrame frame = MediadorCartasHUD.obtenerInstancia().getVentana().getFrame();
            JOptionPane.showMessageDialog(frame, "Perdiste, momento sad, que pena, quizás en otra ocasión. Ahora fuera de aquí.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Se encarga de mantener el daño actualizado con todos los eventos
     * @param dano Entero de daño recibido
     */
    public void recibirDano(int dano) {
        this.MyHP -= dano;
    }

    /**
     * Se encarga de mantener el maná actualizado con todos los eventos
     * @param mana Entero de maná usado
     */
    public void actualizarMana(int mana) {
        this.MyMana -= mana;
    }

    /**
     * Se encarga de mantener la vida del rival actualizada con todos los eventos
     * @param dano Entero de la vida del rival
     */
    public void producirDano(int dano) {
        this.RivalHP -= dano;
    }
}
