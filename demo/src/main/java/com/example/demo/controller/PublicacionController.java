package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/publicacion")
public class PublicacionController {

    private static final Logger log = LoggerFactory.getLogger(PublicacionController.class);


    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public CollectionModel<EntityModel<Publicacion>> getAllPublicacion() {
        List<Publicacion> publicaciones = publicacionService.getAllPublicaciones();
        log.info("GET /publicacion");
        log.info("GET /Retornando las publicaciones");
        List<EntityModel<Publicacion>> publicacionResources = publicaciones.stream()
         .map(publicacion -> EntityModel.of(publicacion,
          WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(publicacion.getId())).withSelfRel()
         ))
         .collect(Collectors.toList());
         
    WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPublicacion());
    CollectionModel<EntityModel<Publicacion>> resources = CollectionModel.of(publicacionResources, linkTo.withRel("publicacion"));

    return resources;
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> getPublicacionById(@PathVariable Long id)  {
        Optional<Publicacion> publicacion = publicacionService.getPublicacionById(id);

     if (publicacion.isEmpty()) {
      log.error("No existe la Publicacion ID {}",id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No existe la Publicacion ID " + id));
     }
       
        return ResponseEntity.ok(publicacion);
    }


    @PostMapping
    public EntityModel<Publicacion> createPublicacion(@RequestBody Publicacion publicacion) {
      Publicacion createpublicacion = publicacionService.createPublicacion(publicacion);
      return EntityModel.of(createpublicacion,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(createpublicacion.getId())).withSelfRel(),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPublicacion()).withRel("all-publicacion"));
      }
      

/* 
   @PostMapping
   public ResponseEntity<Object> createPublicacion(@RequestBody Publicacion publicacion) {
     Publicacion createpublicacion = publicacionService.createPublicacion(publicacion);
     if (createpublicacion == null) {
        log.error("Error al crear la publicacion {}",publicacion);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear la publicacion"));
     }
     return ResponseEntity.ok(createpublicacion);
   }
   */

   @PutMapping("/{id}")
   public Publicacion updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {   
       return publicacionService.updatePublicacion(id, publicacion);
   }

   @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable Long id){
        publicacionService.deletePublicacion(id);
    }

    static class ErrorResponse {
     private final String message;

     public ErrorResponse(String message){
     
      this.message = message;

     }
     public String getMessage(){

        return message;
     }
        
    }
   
   
}
