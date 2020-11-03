package com.github.monstertecg.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Almacena los logs en un archivo
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1.2
 */

public class LoggingHandler {

    public FileHandler Handler(final Logger LOGGER) {
        try {
            final FileHandler manejadorDeArchivos = new FileHandler("logging.log", true);

            LOGGER.addHandler(manejadorDeArchivos);

            final SimpleFormatter formateador = new SimpleFormatter();

            manejadorDeArchivos.setFormatter(formateador);

            return manejadorDeArchivos;

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
