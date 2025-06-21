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

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Usuario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.UsuarioService;


@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestControllers {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> List(){
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Usuario> UsuarioOptional = service.findById(id);
        if (UsuarioOptional.isPresent()){
            return ResponseEntity.ok(UsuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crear (@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional <Usuario> UsuarioOptional = service.findById(id);
        if(UsuarioOptional.isPresent()){
            Usuario Usuarioexistente = UsuarioOptional.get();
            Usuarioexistente.setNombre(unUsuario.getNombre());
            Usuarioexistente.setApellido(unUsuario.getApellido());
            Usuarioexistente.setCorreo(unUsuario.getCorreo());
            Usuarioexistente.setCelular(unUsuario.getCelular());
            Usuario Usuariomodificado = service.save(Usuarioexistente);
            return ResponseEntity.ok(Usuariomodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional<Usuario> UsuarioOptional = service.delete(unUsuario);
        if (UsuarioOptional.isPresent()){
            return ResponseEntity.ok(UsuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
