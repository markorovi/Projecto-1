package com.github.monstertecg.sockets;

import com.github.monstertecg.logs.LoggingHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/** Genera conectividad entre clientes
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1
 */
public class Conectividad {

    private Logger LOGGER = Logger.getLogger(Conectividad.class.getName());

    private static Conectividad instancia;

    private Socket socket;
    private ServerSocket serverSocket;
    private int puertoEnUso;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private static boolean conexionActualizada = false;
    private boolean conectado = false;
    private int tiempoEsperando = 0;
    private int conteoComprobacion = 0;


    public synchronized static Conectividad obtenerInstancia(){
        if (instancia == null){
            instancia = new Conectividad();
        }
        return instancia;
    }

    private void ComprobadorDeConexion() {

        while (this.conectado){
            if (this.tiempoEsperando == 15){
                //mostrar mensaje de que el otro compa anda en las nubes
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

        while (this.conectado){
            if (this.conteoComprobacion == 5){
                //enviar mensaje
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

    public void EnviarMensaje (String hostDestino, int puertoDestino, String mensaje, int puertoOrigen){

        try {

            socket = new Socket(hostDestino, puertoDestino);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeUTF(puertoOrigen + ": " + mensaje);

            outputStream.close();

            socket.close();

            return;

        }
        catch (IOException e) {

            System.out.println("Problemas de conexión.");
            return;

        }
    }

    /** Genera el bucle para la espera de paquetes.
     * @throws IOException
     */

    private void BucleDeConexion() throws IOException{

        this.puertoEnUso = BuscaPuerto();

        this.serverSocket = new ServerSocket(puertoEnUso);

        new Thread(()->{
            this.ComprobadorDeConexion();
        }).start();

        new Thread(()->{
            this.BucleComprobador();
        }).start();

        while (true) {

            try {

                this.socket = serverSocket.accept();

                new Thread(()->{
                    this.Conectado();
                }).start();

            }
            catch (IOException e) {

                this.socket.close();

            }
        }
    }

    public void Conectado() {

        String recibido;
        while (true) {
            try {
                recibido = this.inputStream.readUTF();

                ComprobarMensajeSecreto(recibido);

                break;

            } catch (IOException e) {
                FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info(e.getMessage());
                fHandler.close();
            }
        }
        try {
            this.inputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void ComprobarMensajeSecreto(String texto) {
        switch (texto){
            case "acknowledge":
                this.tiempoEsperando = 0;
                break;

            case "desconectar":
                //desconectar todo
                break;

            default:
                break;
        }
        System.out.println(texto);
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

        FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
        LOGGER.info("Buscando puerto...");
        fHandler.close();

        while (encontrado == false) {

            if (puerto > 65535) {
                fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.warning("No hay puertos disponibles.");
                fHandler.close();

                break;
            }

            else if (RevisaPuerto(puerto)){
                fHandler = (new LoggingHandler().Handler(LOGGER));
                LOGGER.info("Puerto encontrado: " + puerto);
                fHandler.close();
                encontrado = true;
            }

            else{
                puerto++;
            }
        }

        return puerto;
    }
}