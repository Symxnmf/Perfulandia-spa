package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.restcontrollers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.UsuarioRestControllers;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Usuario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.UsuarioService;

@WebMvcTest(UsuarioRestControllers.class)
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void listarUsuariosTest() throws Exception {
        when(usuarioService.findByAll()).thenReturn(List.of());
        mockmvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUsuarioExistenteTest() {
        Usuario usuario = new Usuario(1L, "Pedro", "López", "pedro@mail.com", "912345678");
        try {
            when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));
            mockmvc.perform(get("/api/usuarios/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void verUsuarioNoExistenteTest() throws Exception {
        when(usuarioService.findById(99L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/usuarios/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario nuevoUsuario = new Usuario(null, "Carla", "Gómez", "carla@mail.com", "912345679");
        Usuario usuarioCreado = new Usuario(1L, "Carla", "Gómez", "carla@mail.com", "912345679");
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioCreado);

        mockmvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isCreated());
    }
}