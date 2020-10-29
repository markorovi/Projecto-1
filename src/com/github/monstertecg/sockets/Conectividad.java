package com.github.monstertecg.sockets;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.logs.LoggingHandler;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Genera conectividad entre clientes
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1
 */
public class Conectividad {

    private static Conectividad instancia;
    private static boolean conexionActualizada = false;
    private Logger LOGGER = Logger.getLogger(Conectividad.class.getName());
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean conectado = false;
    private int tiempoEsperando = 0;
    private int conteoComprobacion = 0;
    private JSONObject jsonObject = new JSONObject();

    // Aspectos de host
    private String ipPropia;
    private String ipDestino;
    private int puertoDestino;
    private int puertoEnUso;


    public synchronized static Conectividad obtenerInstancia(String ipDestino, int puertoDestino) {
        if (instancia == null) {
            instancia = new Conectividad();
        }

        instancia.ipDestino = ipDestino;
        instancia.puertoDestino = puertoDestino;

        return instancia;
    }

    /**
     * Detiene el juego si el oponenete no responde.
     */

    private void ComprobadorDeConexion() {

        while (this.conectado) {
            if (this.tiempoEsperando == 15) {
                //mostrar mensaje de que el otro compa anda en las nubes
                System.out.println("El otro compa juega desde el surquí.");
            }
            try {
                Thread.sleep(1000);
                this.tiempoEsperando++;

            } catch (InterruptedException e) {

                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.warning(e.getMessage());
                fHandler.close();
            }

        }
    }

    private void BucleComprobador() {

        while (this.conectado) {
            if (this.conteoComprobacion == 5) {
                this.jsonObject.put("MensajeSecreto", "acknowledge");
                this.EnviarMensaje(this.jsonObject.toString());
            }
            try {
                Thread.sleep(1000);
                this.tiempoEsperando++;

            } catch (InterruptedException e) {

                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.warning(e.getMessage());
                fHandler.close();
            }
            conteoComprobacion++;

        }
    }

    public void EnviarMensaje(String mensaje) {

        try {

            Socket socketEnvio = new Socket(this.ipDestino, puertoDestino);

            this.outputStream = new DataOutputStream(socketEnvio.getOutputStream());

            outputStream.writeUTF(mensaje);

            outputStream.close();

            socket.close();

        } catch (IOException e) {

            System.out.println("Problemas de conexión.");

        }
    }

    /**
     * Genera el bucle para la espera de paquetes.
     *
     * @throws IOException
     */

    private void BucleDeConexion() throws IOException {

        try {
            this.ipPropia = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.puertoEnUso = BuscaPuerto();

        this.serverSocket = new ServerSocket(puertoEnUso);

        new Thread(this::ComprobadorDeConexion).start();

        new Thread(this::BucleComprobador).start();

        this.conectado = true;

        while (true) {

            try {

                this.socket = serverSocket.accept();

                new Thread(this::Conectado).start();

            } catch (IOException e) {

                this.socket.close();

            }
        }
    }

    public void Conectado() {

        JsonNode jnode;

        while (true) {
            try {

                jnode = Json.parse(this.inputStream.readUTF());

                try {
                    ComprobarMensajeSecreto(jnode.get("MensajeSecreto").asText());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            } catch (IOException e) {
                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info(e.getMessage());
                fHandler.close();
            }
        }
        try {
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ComprobarMensajeSecreto(String texto) throws IOException {
        switch (texto) {
            case "acknowledge":
                this.tiempoEsperando = 0;
                break;

            case "desconectar":
                this.socket.close();
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

    public int BuscaPuerto() {

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
}