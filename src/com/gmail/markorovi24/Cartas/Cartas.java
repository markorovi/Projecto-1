package com.gmail.markorovi24.Cartas;

public class Cartas {
    protected final String tipo;
    protected final String id;
    protected String nombre;
    protected String descripcion;
    protected String frase;
    protected int dano;
    protected int mana;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Cartas(String id, String nombre, String descripcion, String frase, int dano, int mana) {
        // Constructor de esbirros

        this.tipo = "esbirros";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frase = frase;
        this.dano = dano;
        this.mana = mana;
    }

    public Cartas(String id, String nombre, String descripcion, int mana) {
        // Constructor de hechizos

        this.tipo = "hechizos";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mana = mana;
    }

    public Cartas(String id, String nombre, String descripcion, String frase, int mana) {
        // Constructor de secretos

        this.tipo = "secretos";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frase = frase;
        this.mana = mana;
    }

    public void Mostrar(){
        System.out.println("Hola, soy " + nombre + ", " + frase);
        System.out.println("\n" + descripcion);
        System.out.println("daño: " + dano + ", mana: " + mana);
    }

    public String getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }
}
