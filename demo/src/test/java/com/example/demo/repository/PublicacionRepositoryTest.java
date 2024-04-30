package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Publicacion;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositoryTest {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Test
    public void guardarPublicacionTest(){
        Publicacion publicacion = new Publicacion();
        publicacion.setNombrePublicacion("Hola");

        Publicacion resultado = publicacionRepository.save(publicacion);

        assertNotNull(resultado.getId());
        assertEquals("Hola", resultado.getNombrePublicacion());

    }
}
