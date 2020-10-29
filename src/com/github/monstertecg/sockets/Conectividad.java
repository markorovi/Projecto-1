package com.github.monstertecg.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Genera conectividad entre clientes
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1
 */
public class Conectividad {

    private Socket socket;
    private ServerSocket serverSocket;
    
    public void ReservarPuerto() throws IOException {

    }

    /** Prueba si un puerto está disponible
     * 
     * @param puerto de prueba
     * @return valor buleano de si el puerto está disponible
     */

    private boolean RevisaPuerto(int puerto) {
        boolean resultado;

        try {

            serverSocket = new ServerSocket(puerto);
            serverSocket.close();
            resultado = true;

        }
        catch (Exception e) {
            resultado = false;
        }

        return (resultado);
    }

    /** Busca un puerto disponible, comenzando en 40 000
     * 
     * @return puerto disponible
     */

    public int BuscaPuerto() {

        int puerto = 40000;
        boolean encontrado = false;

        System.out.println("Buscando puerto...");

        while (encontrado == false) {

            if (puerto > 65535) {
                System.out.println("No hay puertos disponibles.");
                break;
            }

            else if (RevisaPuerto(puerto)){
                System.out.println("Puerto encontrado: " + puerto);
                encontrado = true;
            }

            else{
                puerto++;
            }
        }

        return puerto;
    }
}