package com.Hernan_Boggini.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvierteDatos implements IConvierteDatos {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public <T> T obtenerDatos(String json, Class<T> clase){
        try {
            return objectMapper.readValue(json, clase);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public List<LibroDTO> obtenerListaDeLibros(String json){
        try{
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, LibroDTO.class);
            return objectMapper.readValue(json, listType);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
