package com.Hernan_Boggini.LiterAlura.model;

import jakarta.persistence.*;


import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String autor;

    @Column(name = "Año_de_nacimiento")
     private Integer anoDeNacimiento;

    @Column(name = "Año_de_fallecido")
    private Integer anoDeFallecimiento;

    @OneToMany(mappedBy = "autor",fetch =FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // GETERS Y SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnoDeNacimiento() {
        return anoDeNacimiento;
    }

    public void setAnoDeNacimiento(Integer anoDeNacimiento) {
        this.anoDeNacimiento = anoDeNacimiento;
    }

    public Integer getAnoDeFallecimiento() {
        return anoDeFallecimiento;
    }

    public void setAnoDeFallecimiento(Integer anoDeFallecimiento) {
        this.anoDeFallecimiento = anoDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // CONSTRUCTORES
    public Autor(){}

    public static boolean perteneseAno(Integer ano){
        return ano != null && !ano.equals(Year.of(0));
    }

    public Autor(AutorDTO autorDTO) {
       this.autor = autorDTO.autor();
       this.anoDeNacimiento = autorDTO.anoDeNacimiento() != null ? autorDTO.anoDeNacimiento() : null;
       this.anoDeFallecimiento = autorDTO.anoFallecmiento() != null ? autorDTO.anoFallecmiento() : null;
    }
public Autor(String autor, Integer anoDeNacimiento, Integer anoDeFallecimiento) {
        this.autor = autor;
        this.anoDeNacimiento = anoDeNacimiento;
        this.anoDeFallecimiento = anoDeFallecimiento;
    }

    @Override
    public String toString(){
        String anoNacimientoHpb = anoDeNacimiento != null ? anoDeNacimiento.toString() : "Desconocido";
        String anoFallecimientoHpb = anoDeFallecimiento != null ? anoDeFallecimiento.toString() : "Desconocido";
        return  autor + " (Nacido en " + anoNacimientoHpb + ", Fallecido en " + anoFallecimientoHpb + ")";

    }
}
