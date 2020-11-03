package com.github.monstertecg.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    // public JsonNode
}
