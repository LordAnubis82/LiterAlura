package com.Hernan_Boggini.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.util.StringBuilders;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDto(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Double numeroDeDescargas,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("authors") List<AutorDTO> autores) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Autor(es):\n");
        for (AutorDTO autor : autores){
            sb.append("  - ").append(autor.autor()).append("\n");
       }
        sb.append("Idioma(s): ").append(String.join(", ", idioma)).append("\n");
        sb.append("Downloads: ").append(numeroDeDescargas).append("\n");
        sb.append("----------------------------------------");
        return sb.toString();
    }
}