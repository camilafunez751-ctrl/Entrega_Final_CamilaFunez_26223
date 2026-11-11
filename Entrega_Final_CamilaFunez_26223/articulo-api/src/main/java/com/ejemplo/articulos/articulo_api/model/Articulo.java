package com.ejemplo.articulos.articulo_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
public class Articulo extends Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Articulo() {
        super();
    }

    public Articulo(Long id, String nombre, Double precio,
                    Categoria categoria, LocalDate fechaVencimiento) {
        super(nombre, precio);
        this.id = id;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String obtenerTipo() {
        return "Artículo";
    }
}
