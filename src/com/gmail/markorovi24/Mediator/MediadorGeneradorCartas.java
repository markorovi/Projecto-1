package com.gmail.markorovi24.Mediator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;

import java.io.IOException;
import java.util.Random;

public class MediadorGeneradorCartas {

    static MediadorGeneradorCartas Mediador;
    Random rand = new Random();
    JsonNode Diccionario;

    {
        try {
            Diccionario = Json.getFromFile("card.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MediadorGeneradorCartas obtenerInstancia() {
        if (Mediador == null) {
            Mediador = new MediadorGeneradorCartas();
        }
        return Mediador;
    }

    private Cartas createCard(String type, String card, JsonNode canvas) {
        switch (type) {
            case "esbirros":
                return new Esbirros(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("frase").asText(), canvas.get("daño").asInt(), canvas.get("mana").asInt());
            case "hechizos":
                return new Hechizos(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("mana").asInt());
            case "secretos":
                return new Secretos(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("frase").asText(), canvas.get("mana").asInt());
            default:
                return null;
        }
    }

    public ListaStack<Cartas> ramdomizadorDeck() {
        ListaStack<Cartas> Pila = new ListaStack<>();

        for (int i = 0; i < 16; i++) {
            int type = rand.nextInt(3);
            if (type == 0) {
                int card = rand.nextInt(20);
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                Pila.Agregar(createCard("esbirros", String.valueOf(card), attr));

            } else if (type == 1) {
                int card = rand.nextInt(10);
                Diccionario.get("hechizos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("hechizos").get(String.valueOf(card));
                Pila.Agregar(createCard("hechizos", String.valueOf(card), attr));

            } else {
                int card = rand.nextInt(10);
                Diccionario.get("secretos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("secretos").get(String.valueOf(card));
                Pila.Agregar(createCard("secretos", String.valueOf(card), attr));
            }
        }
        return Pila;
    }

    public ListaCircularDoble<Cartas> ramdomizadorHand() {
        ListaCircularDoble<Cartas> Lista = new ListaCircularDoble<>();

        for (int i = 0; i < 4; i++) {
            int type = rand.nextInt(3);
            if (type == 1) {
                int card = rand.nextInt(20);
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                Lista.Agregar(createCard("esbirros", String.valueOf(card), attr));

            } else if (type == 2) {
                int card = rand.nextInt(10);
                Diccionario.get("hechizos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("hechizos").get(String.valueOf(card));
                Lista.Agregar(createCard("hechizos", String.valueOf(card), attr));

            } else {
                int card = rand.nextInt(10);
                Diccionario.get("secretos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("secretos").get(String.valueOf(card));
                Lista.Agregar(createCard("secretos", String.valueOf(card), attr));
            }
        }

        return Lista;
    }

}
