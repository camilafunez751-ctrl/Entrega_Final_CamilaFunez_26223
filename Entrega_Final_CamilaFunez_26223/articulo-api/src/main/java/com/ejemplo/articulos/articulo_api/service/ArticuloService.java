package com.ejemplo.articulos.articulo_api.service;

import com.ejemplo.articulos.articulo_api.model.Articulo;
import java.util.List;

public interface ArticuloService {

    List<Articulo> listarTodos();

    Articulo buscarPorId(Long id);

    Articulo guardar(Articulo articulo);

    Articulo actualizar(Long id, Articulo articulo);

    void eliminar(Long id);

    List<Articulo> buscarFlexible(String busqueda);
}
