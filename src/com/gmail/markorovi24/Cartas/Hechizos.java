package com.gmail.markorovi24.Cartas;

public class Hechizos extends Cartas {

    public Hechizos(String nombre, String descripcion, int mana) {
        super(nombre, descripcion, null, 0, mana);
    }

    @Override
    public void Mostrar() {
        System.out.println(nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Maná: " + mana);
    }
}
