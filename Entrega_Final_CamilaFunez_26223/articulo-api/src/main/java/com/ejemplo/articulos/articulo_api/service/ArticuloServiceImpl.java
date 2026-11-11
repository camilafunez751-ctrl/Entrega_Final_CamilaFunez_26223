package com.ejemplo.articulos.articulo_api.service;

import com.ejemplo.articulos.articulo_api.model.Articulo;
import com.ejemplo.articulos.articulo_api.model.Categoria;
import com.ejemplo.articulos.articulo_api.repository.ArticuloRepository;
import com.ejemplo.articulos.articulo_api.repository.CategoriaRepository;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository repositorio;
    private final CategoriaRepository categoriaRepository;

    public ArticuloServiceImpl(
            ArticuloRepository repositorio,
            CategoriaRepository categoriaRepository) {
        this.repositorio = repositorio;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Articulo> listarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Articulo buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Articulo guardar(Articulo articulo) {

        if (articulo.getCategoria() != null
                && articulo.getCategoria().getId() != null) {

    Categoria categoria = categoriaRepository
        .findById(articulo.getCategoria().getId())
        .orElseThrow(() ->
        new RuntimeException("Categoría no encontrada"));

        articulo.setCategoria(categoria);
        }

        return repositorio.save(articulo);
    }

    @Override
    public Articulo actualizar(Long id, Articulo articulo) {

        Articulo existente = repositorio.findById(id).orElse(null);

        if (existente != null) {

        existente.setNombre(articulo.getNombre());
        existente.setPrecio(articulo.getPrecio());
        existente.setFechaVencimiento(articulo.getFechaVencimiento());

        if (articulo.getCategoria() != null
        && articulo.getCategoria().getId() != null) {

    Categoria categoria = categoriaRepository
        .findById(articulo.getCategoria().getId())
        .orElseThrow(() ->
        new RuntimeException("Categoría no encontrada"));

        existente.setCategoria(categoria);
            }

            return repositorio.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Articulo> buscarFlexible(String busqueda) {

        if (busqueda == null || busqueda.isBlank()) {
            return repositorio.findAll();
        }

        String termino = normalizar(busqueda);

        return repositorio.findAll().stream()
        .filter(articulo ->
        articulo.getNombre() != null
        && normalizar(articulo.getNombre()).contains(termino))
        .toList();
        }

    private String normalizar(String texto) {

        String sinAcentos = Normalizer.normalize(
                texto, Normalizer.Form.NFD
        ).replaceAll("\\p{M}", "");

        return sinAcentos
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", "");
    }
}
