package com.gmail.markorovi24.Mediator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;

import java.io.IOException;
import java.util.Random;

public class MediadorGeneradorCartas {

    Random rand = new Random();
    JsonNode Diccionario;
    {
        try {
            Diccionario = Json.getFromFile("card.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Cartas createCard(String type, String card, JsonNode canvas){
        if (type.equals("esbirros")){
            Cartas Card = new Esbirros(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("frase").asText(), canvas.get("daño").asInt(), canvas.get("mana").asInt());
            return Card;
        } else if (type.equals("hechizos")){
            Cartas Card = new Hechizos(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("mana").asInt());
            return Card;
        } else {
            Cartas Card = new Secretos(card, canvas.get("nombre").asText(), canvas.get("descripcion").asText(), canvas.get("frase").asText(), canvas.get("mana").asInt());
            return Card;
        }
    }

    public ListaStack<Cartas> ramdomizadorDeck(){
        ListaStack<Cartas> Pila = new ListaStack<Cartas>();

        for (int i = 0; i < 16; i++) {
            int type = rand.nextInt(3);
            if (type == 1) {
                int card = rand.nextInt(20);
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                Pila.Agregar(createCard("esbirros", String.valueOf(card), attr));

            } else if (type == 2) {
                int card = rand.nextInt(10);
                Diccionario.get("hechizos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                Pila.Agregar(createCard("esbirros", String.valueOf(card), attr));

            } else {
                int card = rand.nextInt(10);
                Diccionario.get("secretos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                Pila.Agregar(createCard("esbirros", String.valueOf(card), attr));
            }
        }
        return Pila;
    }

    public void ramdomizadorHand(){
        //Lista
        for (int i = 0; i < 4; i++) {
            //rand.nextInt(int x) un numero entre 0 y x-1
            int type = rand.nextInt(3);
            if (type == 1) {
                int card = rand.nextInt(20);
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                //Cartas carta = new Esbirros(attr.get());
                System.out.println(attr.get("nombre"));
                //.agregar

            } else if (type == 2) {
                int card = rand.nextInt(10);
                Diccionario.get("hechizos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                System.out.println(attr.get("nombre"));

            } else {
                int card = rand.nextInt(10);
                Diccionario.get("secretos").get(String.valueOf(card));
                JsonNode attr = Diccionario.get("esbirros").get(String.valueOf(card));
                System.out.println(attr.get("nombre"));

            }
        }
    }

}
