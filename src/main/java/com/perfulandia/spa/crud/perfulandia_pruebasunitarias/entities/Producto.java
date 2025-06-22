package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int precio;
    private int stock;
    private String descripcion;
    private String categoria;

    


    public Producto() {
    }

    




    public Producto(Long id, String nombre, int precio, int stock, String descripcion, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }


    





    public Long getId() {
        return id;
    }






    public void setId(Long id) {
        this.id = id;
    }






    public String getNombre() {
        return nombre;
    }






    public void setNombre(String nombre) {
        this.nombre = nombre;
    }






    public int getPrecio() {
        return precio;
    }






    public void setPrecio(int precio) {
        this.precio = precio;
    }






    public int getStock() {
        return stock;
    }






    public void setStock(int stock) {
        this.stock = stock;
    }






    public String getDescripcion() {
        return descripcion;
    }






    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }






    public String getCategoria() {
        return categoria;
    }






    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }




}
