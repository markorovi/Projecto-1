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

    public static JSONObject parseJSONObject(String jsonSource) {
        return new JSONObject(jsonSource);
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

     public static String VarToString(String tipoCarta, String id, int vida, int mana, boolean abandonar, String mensaje) {
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("carta seleccionada", tipoCarta);
         jsonObject.put("vida", vida);
         jsonObject.put("mana", mana);
         jsonObject.put("abandonar", abandonar);
         jsonObject.put("mensaje", mensaje);

         return jsonObject.toString();
     }

     public static JsonNode getCartas(String cartas){
        JsonNode jsonNode = null;
        JSONObject temporal = new JSONObject();
        int i = 0;
        boolean bucle = true;

        try{
            Json.parse(cartas);
        } catch (Exception e) {
            bucle = false;
        }
        while (true) {
            try {

            } catch (Exception e) {
                break;
            }
        }
        return jsonNode;
     }

     public static String CartasToString(ListaSimple<Cartas> cartas){
        JSONObject jsonObject = new JSONObject();

        int largo = cartas.Largo();
        for (int i = 0; i < largo; i++){

            Cartas carta = cartas.ObtenerValor(i);
            JSONObject cartaTemp = new JSONObject();
            cartaTemp.put("carta Seleccionada", carta.getTipo());
            cartaTemp.put("id", carta.getId());



            jsonObject.put(String.valueOf(i), cartaTemp.toString());
        }

        return jsonObject.toString();
     }
}
