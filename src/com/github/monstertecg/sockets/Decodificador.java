package com.github.monstertecg.sockets;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.monstertecg.json.Json;

import java.io.IOException;

public class Decodificador {

    private static void Decodificar (String mensajeCodificado) throws IOException {
        JsonNode jsonNode = Json.parse(mensajeCodificado);
        String tipoCarta = jsonNode.get("Carta seleccionada").asText();
        String idCarta = jsonNode.get("id").asText();

        // Asegura el tipo de carta

        switch (tipoCarta){
            case "esbirros":
                switch (idCarta) {
                    case "0":
                        // llamar clase de esbirros
                }
                break;
        }
        /*
            Llama al medidador de cartas
         */
    }
}
