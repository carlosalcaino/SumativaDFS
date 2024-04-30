package com.example.demo.model;

public class Comentarios {
    
    private String codigoComentario;
    private String comentario;  
    
    
    public Comentarios(String codigoComentario,String comentario){
        this.codigoComentario = codigoComentario;
        this.comentario = comentario;
    }

    public String getCodigoComentario() {
        return codigoComentario;
    }

    public String getComentario() {
        return comentario;
    }

}
