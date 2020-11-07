package com.gmail.markorovi24.Cartas;

public class Cartas {
    protected final String tipo;
    protected final String id;
    protected String nombre;
    protected String descripcion;
    protected String frase;
    protected int dano = 0;
    protected int mana = 0;

    /**
     * Constructor para las cartas de tipo esbirros, los parametros son auto-explicatorios
     * @param id
     * @param nombre
     * @param descripcion
     * @param frase
     * @param dano
     * @param mana
     */
    public Cartas(String id, String nombre, String descripcion, String frase, int dano, int mana) {
        this.tipo = "esbirros";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frase = frase;
        this.dano = dano;
        this.mana = mana;
    }

    /**
     * Constructor para las cartas de tipo hechizos, los parametros son auto-explicatorios
     * @param id
     * @param nombre
     * @param descripcion
     * @param mana
     */
    public Cartas(String id, String nombre, String descripcion, int mana) {
        this.tipo = "hechizos";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mana = mana;
    }

    /**
     * Constructor para las cartas de tipo secretos, los parametros son auto-explicatorios
     * @param id
     * @param nombre
     * @param descripcion
     * @param frase
     * @param mana
     */
    public Cartas(String id, String nombre, String descripcion, String frase, int mana) {
        this.tipo = "secretos";
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frase = frase;
        this.mana = mana;
    }

    /**
     * Get para el tipo de la carta
     * @return Su tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Get para la id de la carta
     * @return Su ide
     */
    public String getId() {
        return id;
    }

    /**
     * Get para el nombre de la carta
     * @return Su nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set para el nombre de la carta
     * @param nombre El nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get para la descripción de la carta
     * @return Su descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set para la descripción de la carta
     * @param descripcion El valor a asignar a la descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get para la frase de la carta
     * @return Su frase
     */
    public String getFrase() {
        return frase;
    }

    /**
     * Set para la frase de la carta
     * @param frase Valor a asignar a la frase
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }

    /**
     * Get para el daño de la carta
     * @return Su daño
     */
    public int getDano() {
        return dano;
    }

    /**
     * Set para el daño de la carta
     * @param dano Su daño
     */
    public void setDano(int dano) {
        this.dano = dano;
    }

    /**
     * Get para el mana de la carta
     * @return Su maná
     */
    public int getMana() {
        return mana;
    }

    /**
     * Set para el maná de la carta
     * @param mana Valor a asignar al maná de la carta
     */
    public void setMana(int mana) {
        this.mana = mana;
    }
}
