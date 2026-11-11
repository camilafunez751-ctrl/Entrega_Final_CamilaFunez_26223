package com.ejemplo.articulos.articulo_api.model;

public class Servicio extends Producto {

    public Servicio() {
        super();
    }

    public Servicio(String nombre, Double precio) {
        super(nombre, precio);
    }

    @Override
    public String obtenerTipo() {
        return "Servicio";
    }
}