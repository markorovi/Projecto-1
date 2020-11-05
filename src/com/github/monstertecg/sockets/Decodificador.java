package com.github.monstertecg.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;
import com.gmail.markorovi24.Mediator.MediadorServidor;

import java.io.IOException;

/**
 * Decodifica los mensajes referentes a las cartas lanzadas.
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.4.2
 */
public class Decodificador {

    private static JsonNode archivo;

    static {
        try {
            archivo = Json.getFromFile("card.json");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private static Esbirros DecodificarEsbirro(JsonNode mensajeCodificado) {

        String idCarta = mensajeCodificado.get("id").asText();

        JsonNode archivoTempo = archivo.get("esbirros");

        archivoTempo = archivoTempo.get(idCarta);

        String nombre = archivoTempo.get("nombre").asText();
        String descripcion = archivoTempo.get("descripcion").asText();
        String frase = archivoTempo.get("frase").asText();
        int dano = archivoTempo.get("daño").asInt();
        int mana = archivoTempo.get("mana").asInt();

        return new Esbirros(idCarta, nombre, descripcion, frase, dano, mana);
    }

    private static Hechizos DecodificadorHechizos(JsonNode mensajeCodificado) {

        String idCarta = mensajeCodificado.get("id").asText();

        JsonNode archivoTempo = archivo.get("hechizos");

        archivoTempo = archivoTempo.get(idCarta);

        String nombre = archivoTempo.get("nombre").asText();
        String descripcion = archivoTempo.get("descripcion").asText();
        int mana = archivoTempo.get("mana").asInt();

        return new Hechizos(idCarta, nombre, descripcion, mana);
    }

    private static Secretos DecodificadorSecretos(JsonNode mensajeCodificado) {

        String idCarta = mensajeCodificado.get("id").asText();

        JsonNode archivoTempo = archivo.get("secretos");

        archivoTempo = archivoTempo.get(idCarta);

        String nombre = archivoTempo.get("nombre").asText();
        String descripcion = archivoTempo.get("descripcion").asText();
        String frase = archivoTempo.get("frase").asText();
        int mana = archivoTempo.get("mana").asInt();

        return new Secretos(idCarta, nombre, descripcion, frase, mana);
    }

    public static void DecodificarCartas(JsonNode mensajeCodificado) {
        Cartas carta = null;

        switch (mensajeCodificado.get("carta seleccionada").asText()){

            case "esbirros":

                carta = Decodificador.DecodificarEsbirro(mensajeCodificado);
                break;

            case "hechizos":
                carta = Decodificador.DecodificadorHechizos(mensajeCodificado);
                break;

            case "secretos":
                carta = Decodificador.DecodificadorSecretos(mensajeCodificado);
                break;

            case "saltar":
                System.out.println("se ha saltado el turno");

            default:
                System.out.println("sin carta");
        }
        if (carta == null){
            return;
        }
        MediadorServidor.obtenerInstancia().recibido(carta);
    }

    public static void DecodificarMiscelaneos(JsonNode mensajeCodificado) {
        int vida = mensajeCodificado.get("vida").asInt();
        int mana = mensajeCodificado.get("mana").asInt();
        boolean abandnar = mensajeCodificado.get("abandonar").asBoolean();


        // llama al mediador para actualizar datos

        try {
            JsonNode infoInvitado = Json.parse(mensajeCodificado.get("mensaje").asText());

            Conectividad.obtenerInstancia().EstablecerDestino(infoInvitado.get("puerto").asText(), infoInvitado.get("ip").asText());

        } catch (JsonProcessingException jsonProcessingException) { // ya no es el primer mensaje}

            try {
                String mensaje = mensajeCodificado.get("mensaje").asText();

                System.out.println("MENSAJE " + mensaje);

            } catch (Exception e) {
                System.out.println("no había mensaje secreto");
            }
        }
        MediadorServidor.obtenerInstancia().saltado();
    }
}
