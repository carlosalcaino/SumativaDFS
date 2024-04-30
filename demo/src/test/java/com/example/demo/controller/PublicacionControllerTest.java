package com.example.demo.controller;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionServiceImpl;

@WebMvcTest(PublicacionController.class)
public class PublicacionControllerTest {
  @Autowired
  private MockMvc mockMvc;
    
  @MockBean
  private PublicacionServiceImpl publicacionServicioMock;

  @Test

  public void obtenerTodasTest() throws Exception{

    Publicacion publicacion1 = new Publicacion();
    publicacion1.setNombrePublicacion("Primer Comentario");
    publicacion1.setId(1L);
    Publicacion publicacion2 = new Publicacion();
    publicacion2.setNombrePublicacion("Segundo Comentario");
    publicacion2.setId(2L);
    List<Publicacion> publicaciones = Arrays.asList(publicacion1,publicacion2);
    when(publicacionServicioMock.getAllPublicaciones()).thenReturn(publicaciones);

    mockMvc.perform(MockMvcRequestBuilders.get("/publicacion"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.is("Primer Comentario")))
           .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",Matchers.is("Segundo Comentario")));

  }

}
