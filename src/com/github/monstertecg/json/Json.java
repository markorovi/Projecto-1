package com.github.monstertecg.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.monstertecg.listasEnlazadas.ListaSimple;
import com.gmail.markorovi24.Cartas.Cartas;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Genera conectividad entre clientes
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.1.7
 */

public class Json {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parse(String jsonSource) throws JsonProcessingException{
        return objectMapper.readTree(jsonSource);
    }

    public static JsonNode getFromFile(String path) throws IOException {

        String json = null;

        try {

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                json = sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parse(json);
    }

     public static String VarToString(String tipoCarta, String id, int vida, int mana, boolean saltar, String mensaje) {
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("carta seleccionada", tipoCarta);
         jsonObject.put("id", id);
         jsonObject.put("vida", vida);
         jsonObject.put("mana", mana);
         jsonObject.put("saltar", saltar);
         jsonObject.put("mensaje", mensaje);

         return jsonObject.toString();
     }

     public static String CartaToString(Cartas carta){
        JSONObject cartaTemp = new JSONObject();

        cartaTemp.put("tipo", carta.getTipo());
        cartaTemp.put("id", carta.getId());

        return cartaTemp.toString();
     }
}
