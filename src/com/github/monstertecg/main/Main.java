package com.github.monstertecg.main;

import com.github.monstertecg.sockets.Conectividad;

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

        Conectividad conexion;

        conexion = Conectividad.obtenerInstancia();

        Scanner scan = new Scanner(System.in);

        if (scan.nextLine().equals("1")) {
            conexion.EstablecerDestino("40000", conexion.ObtenerOrigen()[1]);

            conexion.BucleDeConexion();
        }

        conexion.EstablecerDestino("40000", conexion.ObtenerOrigen()[1]);

        System.out.println(conexion.ObtenerDestino()[1]);

        conexion.EnviarMensaje("Hola");
    }

}
