package com.Hernan_Boggini.LiterAlura.service;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class ConsumoAPI {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ConsumoAPI.class);

    public String obtenerDatos(String direcion){
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // Configurar para seguir redirecionamentos
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direcion))
                .build();


        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Status Code: " + response.statusCode());
            logger.info("Response Body: " + response.body());
            if (response.statusCode() == 200) {
                return response.body();
            }else {
                throw new RuntimeException("Failed to fetch data. HTTP Status Code: " + response.statusCode());
            }

        }catch (IOException | InterruptedException e){
            throw new  RuntimeException("Error fetching data from API: " + e.getMessage(), e);
        }
    }


}
