package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Proveedor;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.ProveedorService;




@RestController
@RequestMapping("api/proveedores")
public class ProveedorRestControllers {
    @Autowired
    private ProveedorService service;

    @GetMapping
    public List<Proveedor> List(){
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Proveedor> ProveedorOptional = service.findById(id);
        if (ProveedorOptional.isPresent()){
            return ResponseEntity.ok(ProveedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear (@RequestBody Proveedor unProveedor){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unProveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Proveedor unProveedor){
        Optional <Proveedor> ProveedorOptional = service.findById(id);
        if(ProveedorOptional.isPresent()){
            Proveedor Proveedorexistente = ProveedorOptional.get();
            Proveedorexistente.setDireccion(unProveedor.getDireccion());
            Proveedorexistente.setNombreEmpresa(unProveedor.getNombreEmpresa());
            Proveedorexistente.setContacto(unProveedor.getContacto());
            Proveedor Proveedormodificado = service.save(Proveedorexistente);
            return ResponseEntity.ok(Proveedormodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Proveedor unProveedor = new Proveedor();
        unProveedor.setId(id);
        Optional<Proveedor> ProveedorOptional = service.delete(unProveedor);
        if (ProveedorOptional.isPresent()){
            return ResponseEntity.ok(ProveedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}