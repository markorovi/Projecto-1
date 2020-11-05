package com.gmail.markorovi24.Cartas;

import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;

public class Esbirros extends Cartas {


    public Esbirros(String id, String nombre, String descripcion, String frase, int dano, int mana) {
        super(id, nombre, descripcion, frase, dano, mana);
    }

    @Override
    public void Mostrar() {
        System.out.println("Hola, soy " + nombre + ". " + frase);
        System.out.println("\n" + descripcion);
        System.out.println("daño: " + dano + ", mana: " + mana);
    }

    public void Ataque(){
        MediadorVidaMana.obtenerInstancia().recibirDano(this.dano);
        MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
    }

}
