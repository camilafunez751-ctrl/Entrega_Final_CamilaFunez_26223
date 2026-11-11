package com.ejemplo.articulos.articulo_api.repository;

import com.ejemplo.articulos.articulo_api.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    @Query(value = """
    SELECT *
    FROM articulo
    WHERE REPLACE(
    REPLACE(
    REPLACE(
    REPLACE(
    REPLACE(LOWER(nombre), ' ', ''),
    'á', 'a'),
    'é', 'e'),
    'í', 'i'),
    'ó', 'o') LIKE CONCAT('%', :busqueda, '%')
    """, nativeQuery = true)
    List<Articulo> buscarFlexible(@Param("busqueda") String busqueda);

}
