package com.github.monstertecg.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Genera conectividad entre clientes
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.3
 */

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    public static JsonNode parse(String jsonSource) throws IOException {
        return objectMapper.readTree(jsonSource);
    }

}
