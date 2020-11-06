package com.github.monstertecg.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.Cartas.Hechizos;
import com.gmail.markorovi24.Cartas.Secretos;
import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.Mediator.MediadorMyCards;
import com.gmail.markorovi24.Mediator.MediadorServidor;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;

import java.io.IOException;
import java.util.Random;

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

            case "carta robada":
                JsonNode nodoCarta = mensajeCodificado.get("mensaje");
                switch (nodoCarta.get("tipo").asText()) {

                    case "esbirros":

                        carta = Decodificador.DecodificarEsbirro(nodoCarta);
                        break;

                    case "hechizos":
                        carta = Decodificador.DecodificadorHechizos(nodoCarta);
                        break;

                    case "secretos":
                        carta = Decodificador.DecodificadorSecretos(nodoCarta);
                        break;
                }
                MediadorMyCards.obtenerInstancia().agregarHand(carta);
                MediadorMyCards.obtenerInstancia().setHandCards(MediadorMyCards.obtenerInstancia().getHandCards() + 1);
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarHand();
                return;

            default:
                System.out.println("sin carta");
                return;
        }
        if (carta instanceof Hechizos) {
            if (carta.getId().equals("0")){
                MediadorServidor.obtenerInstancia().setMyTurn(false);
                Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
            }
        }
        MediadorServidor.obtenerInstancia().recibido(carta);
    }

    public static void DecodificarMiscelaneos(JsonNode mensajeCodificado) {
        int vida = mensajeCodificado.get("vida").asInt();
        int mana = mensajeCodificado.get("mana").asInt();
        boolean saltar = mensajeCodificado.get("saltar").asBoolean();


        // llama al mediador para actualizar datos

        try {
            JsonNode infoInvitado = Json.parse(mensajeCodificado.get("mensaje").asText());

            Conectividad.obtenerInstancia().EstablecerDestino(infoInvitado.get("puerto").asText(), infoInvitado.get("ip").asText());

        } catch (JsonProcessingException jsonProcessingException) { // ya no es el primer mensaje }

            try {
                String mensaje = mensajeCodificado.get("mensaje").asText();

                System.out.println("MENSAJE " + mensaje);
                if (mensaje.equals("robar")) {
                    ListaCircularDoble<Cartas> cartas = MediadorMyCards.obtenerInstancia().getHand();
                    Cartas carta = cartas.Obtener(new Random().nextInt(cartas.Largo()));
                    Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", 0, 0, false, Json.CartaToString(carta)));
                } else if (mensaje.equals("congelar")) {
                    //MediadorServidor.obtenerInstancia().setMyTurn(false);
                    //Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
                } else if (mensaje.equals("jugar")) {
                    MediadorServidor.obtenerInstancia().saltado();
                } else if (mensaje.equals("saltar")){
                    MediadorServidor.obtenerInstancia().saltado();
                }

            } catch (Exception e) {
                System.out.println("no había mensaje secreto");
            }
        }
    }
}
