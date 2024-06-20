package com.Hernan_Boggini.LiterAlura.menu;

import com.Hernan_Boggini.LiterAlura.model.Libro;
import com.Hernan_Boggini.LiterAlura.model.LibroDto;
import com.Hernan_Boggini.LiterAlura.repository.LibroRepository;
import com.Hernan_Boggini.LiterAlura.service.ConsumoAPI;
import com.Hernan_Boggini.LiterAlura.service.ConvierteDatos;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MenuPrincipal {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private ConsumoAPI consumoAPI;
    @Autowired
    private ConvierteDatos convierteDatos;
    private final Scanner teclado = new Scanner(System.in);
    public  MenuPrincipal(LibroRepository libroRepository, ConsumoAPI consumoAPI, ConvierteDatos convierteDatos){
        this.libroRepository = libroRepository;
        this.consumoAPI = consumoAPI;
        this.convierteDatos = convierteDatos;
    }
    public void ejecutar(){
        boolean correr = true;
        while (correr){
            mostrarMenu();
            var opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarAutoresPorFechaDeNacimiento();
                    break;
                case 6:
                    listarAutoresPorFechaDeFallecimiento();
                    break;
                case 7:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de LiterAlura");
                    correr = false;
            }

            //default -> System.out.println("Opcion invalida");

        }
    }

    private void mostrarMenu() {
        System.out.println("""
                ==========================================================================
                                              Bienvenido a LiterAlura
                                    ¡Una aplicacíon para vos que te gustan los libros! 
                                    Seleciona una opcion del menu:
                ---------------------------------------------------------------------------                                                
                             1-> Buscar titulos por libro
                             2-> Listar libros registrados
                             3-> Listar autores registrados
                             4-> listar autores vivos 
                             5-> listar autores por fecha de nacimiento
                             6-> listar autores por fecha de fallecimiento
                             7-> listar libros por idioma
                             
                             0-> SALIR   
                """);
    }
    private void guardarLibros(List<Libro>libros){libros.forEach(libroRepository::save);}

    private void buscarLibrosPorTitulo(){
        String baseURL = "https://gutendex.com/books?search=";
        try{
            System.out.println("Introduzca el título del libro: ");
            String titulo = teclado.nextLine();
            String direcion = baseURL + titulo.replace(" ", "%20");
            System.out.println("URL de API: " + direcion);

            String jsonResponse = consumoAPI.obtenerDatos(direcion);
            System.out.println("Respuesta de API:" + jsonResponse);
            if(jsonResponse.isEmpty()){
                System.out.println("Esta API esta vacía. ");
                return;
            }
        //Extrae la lista de libros de  "resultados"

            JsonNode rootNode = convierteDatos.getObjectMapper().readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");

            if (resultsNode.isEmpty()){
                System.out.println("No es posible encontrar el libro que buscas");
                return;
            }
            // Convierte los resultados de la API en objetos LibroDTO

            List<Libro> librosDTO = convierteDatos.getObjectMapper()
                    .readerForListOf(LibroDto.class)
                    .readValue(resultsNode);

            //Elimina los duplicados existentes en la base de datos

            List<Libro> librosExistentes = libroRepository.findByTitulo(titulo);
            if (!librosExistentes.isEmpty()){
                System.out.println("Elimina libros duplicados ya existentes en la base de datos ");
             for (Libro libroExistente : librosExistentes){
                 librosDTO.removeIf(libroDTO -> libroExistente.getTitulo().equals(libroDTO.getTitulo()));

             }

            }
            //Guarda los nuevos libros en la base de datos
            if (!librosDTO.isEmpty()){
                System.out.println("Guardando los libros encontrados ");
                List<Libro> librosNuevos = librosDTO.stream().map(Libro::new).collect(Collectors.toList());
                (librosNuevos);
            }
           {

            }


        }
    }
}