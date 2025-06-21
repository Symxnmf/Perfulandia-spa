package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Usuario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UsuarioRestControllers {
    @Autowired
    private UsuarioService service;

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Devuelve una lista con todos los usuarios registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @GetMapping
    public List<Usuario> List(){
        return service.findByAll();
    }

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Devuelve los datos de un usuario específico si existe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Usuario encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear nuevo usuario",
        description = "Guarda un nuevo usuario en la base de datos"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Usuario creado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @PostMapping
    public ResponseEntity<Usuario> crear (@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }
    
    @Operation(
        summary = "Actualizar usuario existente",
        description = "Modifica los datos de un usuario si existe el ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Usuario actualizado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional<Usuario> usuarioOptional = service.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNombre(unUsuario.getNombre());
            usuarioExistente.setApellido(unUsuario.getApellido());
            usuarioExistente.setCorreo(unUsuario.getCorreo());
            usuarioExistente.setCelular(unUsuario.getCelular());
            Usuario usuarioModificado = service.save(usuarioExistente);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario por su ID si existe"
    )
    @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional<Usuario> usuarioOptional = service.delete(unUsuario);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
