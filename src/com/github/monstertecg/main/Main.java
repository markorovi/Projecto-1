package com.github.monstertecg.main;

import com.github.monstertecg.sockets.Conectividad;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Ejecuta el código completo
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Todo funciona, tenga un buen día.");

        // variables -----------------------------------------------------------

        Conectividad conexion;
        JSONObject json = new JSONObject();

        json.put("Mensaje", "Hola");

        // Código ---------------------------------------------------------------

        conexion = Conectividad.obtenerInstancia();

        Scanner scan = new Scanner(System.in);

        conexion.EstablecerDestino("40000", conexion.ObtenerOrigen()[1]);

        new Thread(conexion::BucleDeConexion).start();

        System.out.println(conexion.ObtenerDestino()[1]);

        conexion.EnviarMensaje(json.toString());
    }

}
