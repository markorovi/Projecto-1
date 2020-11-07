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
 * @since 0.1.0
 */
public class Conectividad {

    private static final Logger LOGGER = Logger.getLogger(Conectividad.class.getName());
    private static Conectividad instancia;
    private Socket socket;
    private ServerSocket serverSocket;
    private boolean anfitrion;

    // Aspectos de host
    private String ipPropia;
    private String ipDestino = "";
    private int puertoDestino;
    private int puertoEnUso;


    /**
     * constructor
     */
    public Conectividad() {
        try {
            this.ipPropia = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
            LOGGER.info(e.getMessage());
            fHandler.close();
        }
    }

    public synchronized static Conectividad obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conectividad();
        }
        return instancia;
    }

    /**
     * genera una instancia y establece el estado de anfitrión
     *
     * @return
     */
    public static Conectividad SerAnfitrion() {
        obtenerInstancia().EstablecerAnfitrion(true);
        return instancia;
    }

    /**
     * genera una instancia y establece el estado de invitado
     *
     * @return
     */
    public static Conectividad SerInvitado() {
        obtenerInstancia().EstablecerAnfitrion(false);
        return instancia;
    }

    /**
     * Envía una cadena a través de socekets
     *
     * @param mensaje
     */
    public void EnviarMensaje(String mensaje) {

        try {

            Socket socketEnvio = new Socket(this.ipDestino, puertoDestino);

            DataOutputStream outputStream = new DataOutputStream(socketEnvio.getOutputStream());

            outputStream.writeUTF(mensaje);

            outputStream.close();


        } catch (IOException e) {

            System.out.println("Problemas de conexión.");
        }
    }

    /**
     * Genera el bucle para la espera de paquetes.
     */
    public void BucleDeConexion() {

        this.puertoEnUso = this.BuscaPuerto();

        if (!anfitrion) {
            EnviarMensaje(Json.VarToString(
                    "", "", 0, 0, false, (
                            new JSONObject("{\"ip\":\"" + this.ipPropia + "\"," +
                                    " \"puerto\":\"" + this.puertoEnUso + "\"}").toString())));
        }

        try {
            this.serverSocket = new ServerSocket(puertoEnUso);
        } catch (IOException e) {
            FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
            LOGGER.info(e.getMessage());
            fHandler.close();
        }

        while (true) {
            this.Conectado();
        }
    }

    /**
     * revisa constantemente si hay mensajes nuevos
     */
    private void Conectado() {

        JsonNode jnode;

        DataInputStream inputStream;
        while (true) {
            try {

                this.socket = serverSocket.accept();

                inputStream = new DataInputStream(socket.getInputStream());

                String entrada = inputStream.readUTF();

                jnode = Json.parse(entrada);

                Decodificador.DecodificarMiscelaneos(jnode);

                // Comprueba cartas
                Decodificador.DecodificarCartas(jnode);

                try {
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    LOGGER.info("La conexión tuvo problemas para cerrarse. Mensaje de error: " + e.getMessage());
                    break;
                }

            } catch (IOException e) {
                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info(e.getMessage());
                fHandler.close();
                break;
            }
        }
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
        LOGGER.info("Buscando puerto...");

        while (!encontrado) {

            if (puerto > 65535) {
                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.warning("No hay puertos disponibles.");
                fHandler.close();

                break;
            } else if (RevisaPuerto(puerto)) {
                LOGGER.info("Puerto encontrado: " + puerto);
                encontrado = true;
            } else {
                puerto++;
            }
        }

        return puerto;
    }

    /**
     * revuelve un array de strings con los datos del origen
     *
     * @return
     */
    public String[] ObtenerOrigen() {
        return new String[]{Integer.toString(puertoEnUso), this.ipPropia};
    }

    public void EstablecerDestino(String puertoDestino, String ipDestino) {
        this.puertoDestino = Integer.parseInt(puertoDestino);
        this.ipDestino = ipDestino;
    }

    /**
     * revuelve un array de strings con los datos del destino
     *
     * @return
     */
    public String[] ObtenerDestino() {
        return new String[]{Integer.toString(puertoDestino), ipDestino};
    }

    /**
     * cambia el estado de anfitrión-invitado
     *
     * @param anfitrion
     */
    public void EstablecerAnfitrion(boolean anfitrion) {
        this.anfitrion = anfitrion;
    }

    /**
     * obtiene el estado de anfitrión-invitado
     *
     * @return
     */
    public boolean ObtenerAnfitrion() {
        return this.anfitrion;
    }

    /**
     * devuelve un booleano que especifica si ya tiene con quién realizar comunicación
     *
     * @return
     */
    public boolean isConnected() {
        return !ipDestino.equals("");
    }
}