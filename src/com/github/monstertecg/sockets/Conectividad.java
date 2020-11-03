package com.github.monstertecg.sockets;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.logs.LoggingHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Genera conectividad entre clientes
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1.0
 */
public class Conectividad {

    private static Conectividad instancia;
    private static final Logger LOGGER = Logger.getLogger(Conectividad.class.getName());
    private Socket socket;
    private ServerSocket serverSocket;

    // Asegurar conexión
    private boolean seguirIntentando = true;
    private boolean conectado = false;
    private int tiempoEsperando = 0;
    private int conteoComprobacion = 0;
    private static boolean conexionActualizada = false;

    // Aspectos de host
    private String ipPropia;
    private String ipDestino;
    private int puertoDestino;
    private int puertoEnUso;


    public synchronized static Conectividad obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conectividad();
        }

        try {
            instancia.ipPropia = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
            LOGGER.info(e.getMessage());
            fHandler.close();
        }
        return instancia;
    }

    public void EnviarMensaje(String mensaje) {

        try {

            System.out.println("enviando " + mensaje);

            Socket socketEnvio = new Socket(this.ipDestino, puertoDestino);

            DataOutputStream outputStream = new DataOutputStream(socketEnvio.getOutputStream());

            System.out.println("antes de enviar");
            outputStream.writeUTF(mensaje);
            System.out.println("después de enviar");

            outputStream.close();

            socket.close();

        } catch (IOException e) {

            System.out.println("Problemas de conexión.");

        }
    }

    /**
     * Genera el bucle para la espera de paquetes.
     */

    public void BucleDeConexion() {

        this.puertoEnUso = BuscaPuerto();

        try {
            this.serverSocket = new ServerSocket(puertoEnUso);
        } catch (IOException e) {
            FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
            LOGGER.info(e.getMessage());
            fHandler.close();        }

        this.conectado = true;

        while (true) {

            try {

                this.socket = serverSocket.accept();

                new Thread(this::Conectado).start();

            } catch (IOException e) {

                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info(e.getMessage());
                fHandler.close();

                try {
                    this.socket.close();
                } catch (IOException ioException) {
                    fHandler = (new LoggingHandler().Handler(LOGGER));
                    LOGGER.info(ioException.getMessage());
                    fHandler.close();
                }

            }
        }
    }

    public void Conectado() {

        JsonNode jnode;

        DataInputStream inputStream;
        while (true) {
            try {

                this.socket = serverSocket.accept();

                inputStream = new DataInputStream(socket.getInputStream());

                jnode = Json.parse(inputStream.readUTF());

                ComprobarMensajeSecreto(jnode.get("Mensaje").asText());

                /* Añadir comprobación para:
                    Cartas
                    Cambio de turno
                    Solicitud de pausa
                    Sincronización de reloj
                    no sé qué otra putada más
                 */

                System.out.println(jnode.get("MensajeSecreto").asText());

                break;

            } catch (IOException e) {
                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info(e.getMessage());
                fHandler.close();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            this.LOGGER.info("La conexión tuvo problemas para cerrarse. Mensaje de error: " + e.getMessage());
        }
    }

    private void ComprobarMensajeSecreto(String texto) throws IOException {
        switch (texto) {
            case "acknowledge":
                this.tiempoEsperando = 0;
                break;

            case "desconectar":
                this.socket.close();
                System.out.println("Se desconectó voluntariamente.");
                break;

            default:
                break;
        }
        System.out.println(texto);
    }

    /**
     * Prueba si un puerto está disponible
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

        } catch (Exception e) {
            resultado = false;
        }

        return (resultado);
    }

    /**
     * Busca un puerto disponible, comenzando en 40 000
     *
     * @return puerto disponible
     */

    private int BuscaPuerto() {

        int puerto = 40000;
        boolean encontrado = false;

        FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
        LOGGER.info("Buscando puerto...");
        fHandler.close();

        while (!encontrado) {

            if (puerto > 65535) {
                fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.warning("No hay puertos disponibles.");
                fHandler.close();

                break;
            } else if (RevisaPuerto(puerto)) {
                fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info("Puerto encontrado: " + puerto);
                fHandler.close();
                encontrado = true;
            } else {
                puerto++;
            }
        }

        return puerto;
    }

    public String[] ObtenerOrigen(){ return new String[] {Integer.toString(puertoEnUso), this.ipPropia}; }

    public void EstablecerDestino(String puertoDestino, String ipDestino){
        this.puertoDestino = Integer.parseInt(puertoDestino);
        this.ipDestino = ipDestino;
    }

    public String[] ObtenerDestino (){ return new String[] {Integer.toString(puertoDestino), ipDestino}; }

}