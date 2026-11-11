package com.ejemplo.articulos.articulo_api.generics;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico<T> {

    private final List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public List<T> listarTodos() {
        return elementos;
    }
}