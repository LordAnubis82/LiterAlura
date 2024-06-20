package com.Hernan_Boggini.LiterAlura.model;


import jakarta.persistence.*;

@Entity
@Table
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    private String idioma;

    private Integer anoDeNacimientoDelAutor;

    private Integer anoDeFallecimientoDelAutor;

    private double numeroDeDescargas;

    //GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getAnoDeNacimientoDelAutor() {
        return anoDeNacimientoDelAutor;
    }

    public void setAnoDeNacimientoDelAutor(Integer anoDeNacimientoDelAutor) {
        this.anoDeNacimientoDelAutor = anoDeNacimientoDelAutor;
    }

    public Integer getAnoDeFallecimientoDelAutor() {
        return anoDeFallecimientoDelAutor;
    }

    public void setAnoDeFallecimientoDelAutor(Integer anoDeFallecimientoDelAutor) {
        this.anoDeFallecimientoDelAutor = anoDeFallecimientoDelAutor;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
    //CONSTRUCTORES

    public Libro(LibroDto libroDTO){
        this.titulo = libroDTO.titulo();
        Autor autor = new Autor(libroDTO.autores().get(0));
        this.autor = autor;
        this.idioma = libroDTO.idioma().get(0);
        this.numeroDeDescargas = libroDTO.numeroDeDescargas();

    }
    public Libro(Long idApi, String titulo, Autor autor, String idioma, Double numeroDeDescargas){
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
    }
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Downloads: " + numeroDeDescargas + "\n" +
                "----------------------------------------";
    }
}
