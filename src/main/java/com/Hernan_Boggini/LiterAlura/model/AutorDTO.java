package com.Hernan_Boggini.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year") Integer anoDeNacimiento,
        @JsonAlias("death_year") Integer anoFallecmiento) {
    @Override
    public String toString() {
        return "Autor: " + autor;
    }

}

