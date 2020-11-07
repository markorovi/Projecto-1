package com.gmail.markorovi24.Cartas;

import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;

public class Esbirros extends Cartas {

    /**
     * Constructor para las cartas de tipo esbirros, los parametros son auto-explicatorios
     * @param id
     * @param nombre
     * @param descripcion
     * @param frase
     * @param dano
     * @param mana
     */
    public Esbirros(String id, String nombre, String descripcion, String frase, int dano, int mana) {
        super(id, nombre, descripcion, frase, dano, mana);
    }

    /**
     * Método que se encarga de realizar el daño necesario cuando se juega una carta de tipo esbirro
     */
    public void Ataque(){
        MediadorVidaMana.obtenerInstancia().recibirDano(this.dano);
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
    }

}
