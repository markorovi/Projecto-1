package com.github.monstertecg.main;

import com.github.monstertecg.logs.LoggingHandler;
import com.github.monstertecg.sockets.*;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) {
        Logger LOGGER = Logger.getLogger(Main.class.getName());


        FileHandler fHandler = (new LoggingHandler().Handler(LOGGER));
        LOGGER.info("Hola");
        fHandler.close();
    }

}
