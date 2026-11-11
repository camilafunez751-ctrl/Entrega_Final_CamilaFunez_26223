package com.ejemplo.articulos.articulo_api;

import com.ejemplo.articulos.articulo_api.generics.RepositorioGenerico;
import com.ejemplo.articulos.articulo_api.model.Articulo;
import com.ejemplo.articulos.articulo_api.model.Categoria;
import com.ejemplo.articulos.articulo_api.model.Producto;
import com.ejemplo.articulos.articulo_api.model.Servicio;

public class PruebaPolimorfismo {

    public static void main(String[] args) {

        Producto producto1 = new Articulo();
        Producto producto2 = new Servicio();

        System.out.println(producto1.obtenerTipo());
        System.out.println(producto2.obtenerTipo());

        RepositorioGenerico<Producto> repositorio = new RepositorioGenerico<>();

        repositorio.agregar(producto1);
        repositorio.agregar(producto2);

        System.out.println("Cantidad de productos: "
                + repositorio.listarTodos().size());

        RepositorioGenerico<Categoria> categorias = new RepositorioGenerico<>();

        categorias.agregar(new Categoria(null, "Librería"));
        categorias.agregar(new Categoria(null, "Alimenticio"));

        System.out.println("Cantidad de categorías: "
                + categorias.listarTodos().size());
    }
}