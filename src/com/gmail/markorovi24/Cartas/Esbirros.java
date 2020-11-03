package com.gmail.markorovi24.Cartas;

public class Esbirros extends Cartas {

    public Esbirros(String nombre, String descripcion, String frase, int dano, int mana) {
        super(nombre, descripcion, frase, dano, mana);
    }

    @Override
    public void Mostrar() {
        System.out.println("Hola, soy " + nombre + ". " + frase);
        System.out.println("\n" + descripcion);
        System.out.println("daño: " + dano + ", mana: " + mana);
    }

    public int Ataque(){
        return this.dano;
    }

}
