package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "publicacion")
public class Publicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name= "nombrePublicacion")
    private String nombrePublicacion;


public Long getId() {
    return id;
}

public String getNombrePublicacion() {
    return nombrePublicacion;
}

public void setId(Long id) {
    this.id = id;
}

public void setNombrePublicacion(String nombrePublicacion) {
    this.nombrePublicacion = nombrePublicacion;
}

}
