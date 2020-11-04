package com.github.monstertecg.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaCircularDoble;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.github.monstertecg.listasEnlazadas.ListaStack;
import com.github.monstertecg.sockets.Conectividad;
import com.github.monstertecg.sockets.Decodificador;
import com.gmail.markorovi24.Cartas.Cartas;

import java.io.IOException;

/**
 * Ejecuta el código completo
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Funciona, tenga un buen día.");

        Conectividad conexion = Conectividad.obtenerInstancia();

        new Thread(conexion::BucleDeConexion).start();

        String mensaje = Json.VarToString("hechizos", "1", 1000, 200, false, "anfitrion");

        conexion.EstablecerDestino("40000", "127.1.1.1");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        conexion.EnviarMensaje(mensaje);
    }

}
