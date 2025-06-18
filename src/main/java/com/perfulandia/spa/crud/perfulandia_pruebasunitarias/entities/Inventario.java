package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name="inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producto;
    private int cantidad;
    private String ubicacion;
    private int fechaIngreso;


    public Inventario() {
    }


    public Inventario(Long id, String producto, int cantidad, String ubicacion, int fechaIngreso) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.fechaIngreso = fechaIngreso;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getProducto() {
        return producto;
    }


    public void setProducto(String producto) {
        this.producto = producto;
    }


    public int getCantidad() {
        return cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public String getUbicacion() {
        return ubicacion;
    }


    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public int getFechaIngreso() {
        return fechaIngreso;
    }


    public void setFechaIngreso(int fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    


    





}
