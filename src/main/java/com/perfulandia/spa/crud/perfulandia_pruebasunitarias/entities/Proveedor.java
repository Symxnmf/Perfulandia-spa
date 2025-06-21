package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor {
    private Long id;
    private String direccion;
    private String nombreEmpresa;
    private String contacto;
    
    public Proveedor() {
    }

    public Proveedor(Long id, String direccion, String nombreEmpresa, String contacto) {
        this.id = id;
        this.direccion = direccion;
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
