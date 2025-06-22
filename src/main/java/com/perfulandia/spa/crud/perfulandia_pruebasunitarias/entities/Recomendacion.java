package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="recomendacion")
public class Recomendacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String productosSugeridos;


    public Recomendacion() {
    }


    public Recomendacion(Long idCliente, String productosSugeridos) {
        this.idCliente = idCliente;
        this.productosSugeridos = productosSugeridos;
    }


    public Long getIdCliente() {
        return idCliente;
    }


    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    public String getProductosSugeridos() {
        return productosSugeridos;
    }


    public void setProductosSugeridos(String productosSugeridos) {
        this.productosSugeridos = productosSugeridos;
    }

}
