/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author rikurdog31
 */
@Log4j2
@Provider
public class constrViolatExcepMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.error("Desde el error de ConstraintViolationException");
        ProblemDetails ae = preparMensaje(e);
        log.error("Desde el error de ConstraintViolationException");
        return Response.status(ae.getStatus()).entity(ae).type(MediaType.APPLICATION_JSON).build();
    }
    
     protected ProblemDetails preparMensaje(ConstraintViolationException e){ 
         log.error("Desde el error de ConstraintViolationException 1");
         return new ProblemDetails(409, 
                   "Algunos campos no pasan la validación correspondiente.", 
                   e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));              
    }    
}