package com.Hernan_Boggini.LiterAlura;

import com.Hernan_Boggini.LiterAlura.menu.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner  {
    @Autowired
    private MenuPrincipal menuPrincipal;

    public LiterAluraApplication(MenuPrincipal menuPrincipal){
        this.menuPrincipal = menuPrincipal;}

    public static void main(String[] args) {SpringApplication.run(LiterAluraApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        menuPrincipal.ejecutar();
    }

}
