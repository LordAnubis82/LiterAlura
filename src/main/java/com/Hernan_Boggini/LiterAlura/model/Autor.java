package com.Hernan_Boggini.LiterAlura.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String autor;
    @Column(name = "Año_de_nacimiento")
     private Year anoDeNacimiento;
    @Column(name = "Año_ de_fallecido")
    private Year anoDeFallecimiento;
    @OneToMany(mappedBy = "autor",fetch =FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // GETERS Y SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getAnoDeNacimiento() {
        return anoDeNacimiento;
    }

    public void setAnoDeNacimiento(Year anoDeNacimiento) {
        this.anoDeNacimiento = anoDeNacimiento;
    }

    public Year getAnoDeFallecimiento() {
        return anoDeFallecimiento;
    }

    public void setAnoDeFallecimiento(Year anoDeFallecimiento) {
        this.anoDeFallecimiento = anoDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    // CONSTRUCTORES
    public Autor(){}

    public static boolean perteneseAno(Year ano){
        return ano != null && !ano.equals(Year.of(0));
    }
    public Autor(AutorDTO autorDTO) {
       this.autor = autorDTO.autor();
       this.anoDeNacimiento = autorDTO.anoDeNacimiento() != null ? Year.of(autorDTO.anoDeNacimiento()) : null;
       this.anoDeFallecimiento = autorDTO.anoFallecmiento() != null ? Year.of(autorDTO.anoFallecmiento()) : null;
    }
    public Autor(String autor, Year anoDeNacimiento, Year anoDeFallecimiento) {
        this.autor = autor;
        this.anoDeNacimiento = anoDeNacimiento;
        this.anoDeFallecimiento = anoDeFallecimiento;
    }

    @Override
    public String toString(){
        String anoNacimientoHpb = anoDeNacimiento != null ? anoDeNacimiento.toString() : "Desconocido";
        String anoFallecimientoHpb = anoDeFallecimiento != null ? anoDeFallecimiento.toString() : "Desconocido";
        return "Autor: " + autor + " (Nacido en " + anoNacimientoHpb + ", Fallecido en " + anoFallecimientoHpb + ")";

    }
}
