package com.Hernan_Boggini.LiterAlura.repository;

import com.Hernan_Boggini.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento <= :ano AND (a.anoDeFallecimiento IS NULL OR a.anoDeFallecimiento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano")Integer ano);

    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento = :ano")
    List<Autor> findAutoresVivosPorFechaDeNacimiento(@Param("ano") Integer ano);

    @Query("SELECT a FROM Autor a WHERE a.anoDeFallecimiento = :ano")
    List<Autor> findAutoresPorFechaDeFallecimiento(@Param("ano") Integer ano);

    boolean existsByAutor(String nombreAutor);

    Autor getReferenceByAutor(String nombreAutor);
}
