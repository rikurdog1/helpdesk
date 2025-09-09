/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.exception;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author rikurdog31
 */
@Getter
@Setter
@Log4j2
@JsonbPropertyOrder({"status", "titulo", "mensaje"})
public class ProblemDetails  {
    private Response.Status status;
    private String titulo;
    private List<String> mensaje;
    
    
    public ProblemDetails(int codigo, String titulo, String mensaje) {
        log.error("El mensaje de error: " + mensaje);
        this.status = Response.Status.fromStatusCode(codigo);
        this.titulo = titulo;
        this.mensaje = Arrays.asList(mensaje) ;
    }
    
        public ProblemDetails(int codigo, String tilulo, List<String> mensaje) {
        this.status = Response.Status.fromStatusCode(codigo);
        this.titulo = tilulo;
        this.mensaje = mensaje;
    }
}