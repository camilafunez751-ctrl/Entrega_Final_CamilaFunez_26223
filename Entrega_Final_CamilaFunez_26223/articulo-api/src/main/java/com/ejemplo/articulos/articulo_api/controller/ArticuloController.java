package com.ejemplo.articulos.articulo_api.controller;

import com.ejemplo.articulos.articulo_api.model.Articulo;
import com.ejemplo.articulos.articulo_api.service.ArticuloService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    private final ArticuloService servicio;

    public ArticuloController(ArticuloService servicio) {
        this.servicio = servicio;
    }

    // GET: Listar todos los artículos
    @GetMapping
    public List<Articulo> listarTodos() {
        return servicio.listarTodos();
    }

    // GET: Buscar artículos por nombre
    @GetMapping("/buscar")
    public List<Articulo> buscarFlexible(
            @RequestParam String nombre) {
        return servicio.buscarFlexible(nombre);
    }

    // GET: Buscar un artículo por ID
    @GetMapping("/{id}")
    public Articulo buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }

    // POST: Crear un artículo
    @PostMapping
    public Articulo guardar(@RequestBody Articulo articulo) {
        return servicio.guardar(articulo);
    }

    // PUT: Actualizar un artículo
    @PutMapping("/{id}")
    public Articulo actualizar(
            @PathVariable Long id,
            @RequestBody Articulo articulo) {

        return servicio.actualizar(id, articulo);
    }

    // DELETE: Eliminar un artículo
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
