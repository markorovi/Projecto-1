package com.github.monstertecg.main;

import com.github.monstertecg.json.Json;
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

        System.out.println("Funciona, tenga un buen día.");

        // Probando conectividad

        Conectividad conexion;

        Scanner scanner = new Scanner(System.in);
        String eleccion;
        String eleccion1;
        String textoMensaje;

        System.out.println("¿Quiere ser anfitrión o invitado?");
        while (true) {
            System.out.println("Ingrese 1 para anfitrión.");
            System.out.println("Ingrese 2 para invitado.");
            eleccion = scanner.nextLine();

            if (eleccion.equals("1")){
                System.out.println();
                textoMensaje = Json.VarToString("","",0,0,false,"¡holaaaaaaaaaaaaaaaa soy anfitrión!");
                conexion = Conectividad.SerAnfitrion();
                break;
            } else if (eleccion.equals("2")) {
                textoMensaje = Json.VarToString("","",0,0,false,"Y yo soy invitado. uwu");
                conexion = Conectividad.SerInvitado();

                System.out.println("Escriba el puerto del anfitrión al que se quiere conectar");
                eleccion = scanner.nextLine();

                System.out.println("Escriba la ip del anfitrión al que se quiere conectar");
                eleccion1 = scanner.nextLine();

                conexion.EstablecerDestino(eleccion, eleccion1);

                break;
            } else {
                //System.out.println("\nIngrese un número válido.\n");
            }
        }


        new Thread(conexion::BucleDeConexion).start();

        conexion.EnviarMensaje(textoMensaje);

        boolean salir = false;
        while (!salir) {
            eleccion = scanner.nextLine();
            conexion.EnviarMensaje(Json.VarToString("","",0,0,false,eleccion));
        }

    }

}
