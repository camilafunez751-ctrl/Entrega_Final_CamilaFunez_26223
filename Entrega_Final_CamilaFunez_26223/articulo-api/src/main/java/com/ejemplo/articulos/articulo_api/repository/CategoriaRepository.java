package com.ejemplo.articulos.articulo_api.repository;

import com.ejemplo.articulos.articulo_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
}
