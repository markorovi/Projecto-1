package com.gmail.markorovi24.Cartas;

public class Cartas {
    protected String nombre;
    protected String descripcion;
    protected String frase;
    protected int dano;
    protected int mana;

    public Cartas(String nombre, String descripcion, String frase, int daño, int mana) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frase = frase;
        this.dano = daño;
        this.mana = mana;
    }

    public void Mostrar(){
        System.out.println("Hola, soy " + nombre + ", " + frase);
        System.out.println("\n" + descripcion);
        System.out.println("daño: " + dano + ", mana: " + mana);
    }
}
