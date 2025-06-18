package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.restcontrollers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.NotificacionRestController;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Notificacion;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.NotificacionService;

@WebMvcTest(NotificacionRestController.class)
public class NotificacionRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificacionService notificacionService;

    @Test
    public void verNotificacionesTest() throws Exception {
        when(notificacionService.findByAll()).thenReturn(List.of());
        mockmvc.perform(get("/api/notificacion")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnaNotificacionTest() {
        Notificacion unaNotificacion = new Notificacion(1L, "Email", "usuario@ejemplo.com", "Mensaje de prueba", 20250618, "Enviado");
        try {
            when(notificacionService.findById(1L)).thenReturn(Optional.of(unaNotificacion));
            mockmvc.perform(get("/api/notificacion/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void notificacionNoExisteTest() throws Exception {
        when(notificacionService.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/notificacion/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearNotificacionTest() throws Exception {
        Notificacion unaNotificacion = new Notificacion(1, "SMS", "123456789", "Mensaje nuevo", 20250618, "Pendiente");
        Notificacion otraNotificacion = new Notificacion(2, "SMS", "123456789", "Mensaje nuevo", 20250618, "Pendiente");
        when(notificacionService.save(any(Notificacion.class))).thenReturn(otraNotificacion);

        mockmvc.perform(post("/api/notificacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unaNotificacion)))
                .andExpect(status().isCreated());
    }
}
