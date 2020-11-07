package com.gmail.markorovi24.Mediator;

import com.gmail.markorovi24.GUI.Ventana;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class MediadorVidaMana {
    static MediadorVidaMana Mediador;
    int MyHP = 1000;
    int MyMana = 200;
    int RivalHP = 1000;
    int RivalMana = 200;

    public static synchronized MediadorVidaMana obtenerInstancia() {
        if (Mediador == null) {
            Mediador = new MediadorVidaMana();
        }
        return Mediador;
    }

    public int getMyHP() {
        return MyHP;
    }

    public void setMyHP(int myHP) {
        MyHP = myHP;
    }

    public int getMyMana() {
        return MyMana;
    }

    public void setMyMana(int myMana) {
        MyMana = myMana;
        if (MyMana > 1000) {
            MyMana = 1000;
        }
    }

    public int getRivalHP() {
        return RivalHP;
    }

    public void setRivalHP(int rivalHP) {
        RivalHP = rivalHP;
    }

    public int getRivalMana() {
        return RivalMana;
    }

    public void setRivalMana(int rivalMana) {
        RivalMana = rivalMana;
    }

    public void checkWinCondition() {
        if (this.RivalHP <= 0) {
            System.out.println("ENTRAAAAAA AL FILTROOOOO");
            JFrame frame = MediadorCartasHUD.obtenerInstancia().getVentana().getFrame();
            JOptionPane.showMessageDialog(frame, "Ganaste, grande, campeón, máquina, fiera. Ahora fuera de aquí.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        } else if (this.MyHP <= 0) {
            System.out.println("ENTRAAAAAA AL FILTROOOOO");
            JFrame frame = MediadorCartasHUD.obtenerInstancia().getVentana().getFrame();
            JOptionPane.showMessageDialog(frame, "Perdiste, momento sad, que pena, quizás en otra ocasión. Ahora fuera de aquí.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    public void recibirDano(int dano) {
        this.MyHP -= dano;
    }

    public void actualizarMana(int mana) {
        this.MyMana -= mana;
    }

    public void producirDano(int dano) {
        this.RivalHP -= dano;
    }
}
