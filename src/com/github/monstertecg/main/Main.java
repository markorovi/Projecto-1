package com.github.monstertecg.main;

import com.github.monstertecg.logs.LoggingHandler;
import com.github.monstertecg.sockets.Conectividad;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) {
        Logger LOGGER = Logger.getLogger(Main.class.getName());


        FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
        //log
        fHandler.close();

        //Conectividad conexion = (Conectividad.obtenerInstancia());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put( "name", "Mars" );
        data.put( "age", 32 );
        data.put( "city", "NY" );

    }

}
