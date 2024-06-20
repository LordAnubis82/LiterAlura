package com.Hernan_Boggini.LiterAlura.repository;

import com.Hernan_Boggini.LiterAlura.model.Autor;
import com.Hernan_Boggini.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.channels.SelectableChannel;
import java.time.Year;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM libro l WHERE LOWER(l.titulo) = LOWER(:titulo)")
    List<Libro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento <= :ano AND(a.anoDeFallesimiento IS NULL OR a.anoDeFallesimiento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano")Year ano);

    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento = :ano AND (a.anoFallecimiento >= :ano)")
    List<Autor> findAutoresVivosPorFechaDeNacimiento(@Param("ano") Year ano);

    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento <= :ano AND a.anoFallecimiento = :ano")
    List<Autor> findAutoresPorFechaDeFallecimiento(@Param("ano") Year ano);

    @Query("SELECT l FROM Libro l WHERE l.idioma LIKE %:idioma%")
    List<Libro>findByIdioma(@Param("idioma") String idioma);

}
