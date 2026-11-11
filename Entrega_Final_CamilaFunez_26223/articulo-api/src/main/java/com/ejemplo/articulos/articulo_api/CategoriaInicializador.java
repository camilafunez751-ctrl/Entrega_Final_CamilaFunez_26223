
package com.ejemplo.articulos.articulo_api;

import com.ejemplo.articulos.articulo_api.model.Categoria;
import com.ejemplo.articulos.articulo_api.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriaInicializador implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public CategoriaInicializador(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) {

        if (!categoriaRepository.existsByNombre("Alimenticio")) {
            categoriaRepository.save(new Categoria(null, "Alimenticio"));
        }

        if (!categoriaRepository.existsByNombre("Electrónico")) {
            categoriaRepository.save(new Categoria(null, "Electrónico"));
        }

        if (!categoriaRepository.existsByNombre("Limpieza")) {
            categoriaRepository.save(new Categoria(null, "Limpieza"));
        }

        if (!categoriaRepository.existsByNombre("Bazar")) {
            categoriaRepository.save(new Categoria(null, "Bazar"));
        }

        if (!categoriaRepository.existsByNombre("Librería")) {
            categoriaRepository.save(new Categoria(null, "Librería"));
        }
    }
}

