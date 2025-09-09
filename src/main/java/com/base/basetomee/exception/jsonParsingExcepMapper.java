/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.exception;

import jakarta.json.stream.JsonParsingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author rikurdog31
 */

@Log4j2
@Provider
public class jsonParsingExcepMapper implements ExceptionMapper<JsonParsingException> {
    @Override
    public Response toResponse(JsonParsingException e) {
        ProblemDetails ae = preparMensaje(e);
        log.info("Desde el error de JsonParsingException");
        return Response.status(ae.getStatus()).entity(ae).type(MediaType.APPLICATION_JSON).build();
    }
   
    protected ProblemDetails preparMensaje(JsonParsingException e){
        return new ProblemDetails(409, "Error en operacion...", "Error en formato de Json " + e.getMessage());             
    } 
}