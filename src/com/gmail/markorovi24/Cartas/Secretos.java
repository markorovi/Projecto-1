package com.gmail.markorovi24.Cartas;

public class Secretos extends Cartas {

    public Secretos(String nombre, String descripcion, String frase, int mana) {
        super(nombre, descripcion, frase, 0, mana);
    }

    @Override
    public void Mostrar(){
        System.out.println(nombre);
        System.out.println(frase);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Coste de maná "+ mana);
    }
}
