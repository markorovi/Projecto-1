package com.github.monstertecg.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
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

            case "curar":
                MediadorVidaMana.obtenerInstancia().setMyHP(MediadorVidaMana.obtenerInstancia().getMyHP() + Integer.parseInt(mensajeCodificado.asText()));
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
                return;

            case "dañar":
                MediadorVidaMana.obtenerInstancia().setMyHP(MediadorVidaMana.obtenerInstancia().getMyHP() - Integer.parseInt(mensajeCodificado.asText()));
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();

            default:
                System.out.println("sin carta");
                return;
        }
        MediadorServidor.obtenerInstancia().setMyTurn(true);
        MediadorMyCards.obtenerInstancia().setRemainingCards(1);
        if (carta.getTipo().equals("hechizos")) {
            String id = carta.getId();
            if (id.equals("0")){
                Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
                MediadorServidor.obtenerInstancia().setMyTurn(false);
            } else if(id.equals("1")) {
                //tipo 1
                ListaCircularDoble<Cartas> cartas = MediadorMyCards.obtenerInstancia().getHand();
                int randInt = new Random().nextInt(cartas.Largo());
                Cartas cartaADar = cartas.Obtener(randInt);
                cartas.Eliminar(randInt);
                MediadorMyCards.obtenerInstancia().setHandCards(MediadorMyCards.obtenerInstancia().getHandCards());
                MediadorMyCards.obtenerInstancia().setHand(cartas);
                Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", 0, 0, false, Json.CartaToString(carta)));
            } else if(id.equals("2")) {
                //tipo 2
                // añadir contador en la vara esta del servidor, para decirle que se sostenga la picha dos turnos más
                MediadorServidor.obtenerInstancia().setContadorBloqueos(2);
                Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
                MediadorServidor.obtenerInstancia().setMyTurn(false);
            } else if(id.equals("3")) {
                //tipo 3
                Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("curar", "100", 0, 0,false,""));
            } else if(id.equals("4")) {
                //tipo 4
                MediadorVidaMana.obtenerInstancia().setMyHP(MediadorVidaMana.obtenerInstancia().getMyHP()-250);
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
            } else if(id.equals("5")) {
                //tipo 5
                ListaCircularDoble<Cartas> cartasMano = MediadorMyCards.obtenerInstancia().getHand();
                JsonNode diccionario = null;
                try {
                    diccionario = Json.getFromFile("card.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert diccionario != null;
                JsonNode laMosca = diccionario.get("esbirros").get("10");
                cartasMano.CambiarValor(new Random().nextInt(cartasMano.Largo()), new Esbirros("10", laMosca.get("nombre").asText(), laMosca.get("descripcion").asText(), laMosca.get("frase").asText(), laMosca.get("daño").asInt(), laMosca.get("mana").asInt()));
                MediadorMyCards.obtenerInstancia().setHand(cartasMano);
            } else if(id.equals("6")) {
                //tipo 6
                ListaDoble<Cartas> historial = MediadorMyCards.obtenerInstancia().getHistorial();
                int vidaACambiar = 0;
                for (int i = 0; i == historial.Largo(); i++) {
                    if (historial.Obtener(i).getDano()>vidaACambiar){
                        vidaACambiar = historial.Obtener(i).getDano();
                    }
                }
                if (vidaACambiar == 0){
                    vidaACambiar = 500;
                }
                MediadorVidaMana.obtenerInstancia().setMyHP(MediadorVidaMana.obtenerInstancia().getMyHP()-vidaACambiar);
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
            } else if(id.equals("7")) {
                //tipo 7
                // programar que esta vaina cambie las cartas por ??? durante un turno
            } else if(id.equals("8")) {
                //tipo 8
                MediadorVidaMana.obtenerInstancia().setMyHP(MediadorVidaMana.obtenerInstancia().getMyHP()-150);
                MediadorCartasHUD.obtenerInstancia().getVentana().actualizarVida();
            } else if(id.equals("9")) {
                //tipo 9
                // no hase nada equis de
            }
        } else if (MediadorServidor.obtenerInstancia().getContadorBloqueos() != 0) {
            Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
            MediadorServidor.obtenerInstancia().setMyTurn(false);
        }
        MediadorServidor.obtenerInstancia().recibido(carta);
    }

    public static void DecodificarMiscelaneos(JsonNode mensajeCodificado) {

        // llama al mediador para actualizar datos

        try {
            JsonNode infoInvitado = Json.parse(mensajeCodificado.get("mensaje").asText());

            Conectividad.obtenerInstancia().EstablecerDestino(infoInvitado.get("puerto").asText(), infoInvitado.get("ip").asText());

        } catch (JsonProcessingException jsonProcessingException) { // ya no es el primer mensaje }

            try {
                String mensaje = mensajeCodificado.get("mensaje").asText();

                System.out.println("MENSAJE " + mensaje);
                switch (mensaje) {
                    case "robar":
                        // ListaCircularDoble<Cartas> cartas = MediadorMyCards.obtenerInstancia().getHand();
                        // Cartas carta = cartas.Obtener(new Random().nextInt(cartas.Largo()));
                        // Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", 0, 0, false, Json.CartaToString(carta)));
                        break;
                    case "congelar":
                        // MediadorServidor.obtenerInstancia().setMyTurn(false);
                        // Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("", "", MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(), true, "saltado"));
                        break;
                    case "jugar":
                        MediadorServidor.obtenerInstancia().saltado();
                        break;
                    case "saltado":
                        MediadorServidor.obtenerInstancia().setMyTurn(true);
                        break;
                }

            } catch (Exception e) {
                System.out.println("no había mensaje secreto");
            }
        }
    }
}
