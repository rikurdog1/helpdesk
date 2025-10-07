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
        log.error("Desde el error de ConstraintViolationException: {}", e.getMessage());
        ProblemDetails ae = preparMensaje(e);
        // El log anterior se repetía, lo he quitado y ajustado el primero para incluir el mensaje del error.
        return Response.status(ae.getStatus()).entity(ae).type(MediaType.APPLICATION_JSON).build();
    }

    protected ProblemDetails preparMensaje(ConstraintViolationException e) {
        log.error("Desde el error de ConstraintViolationException 1: ",  e);

        // Recopila todos los mensajes de violación de restricciones en un solo String
        String validationDetails = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")); // Puedes usar "\n" si prefieres saltos de línea

        int status = Response.Status.BAD_REQUEST.getStatusCode(); // 400 Bad Request es estándar para validación

        // Construye ProblemDetails usando el patrón Builder de Lombok
        return ProblemDetails.builder()
                .status(status) // 400 Bad Request
                .type(status)   // Usar el mismo código para 'type'
                .title("Error de Validación de Datos")
                .detail("Algunos campos no pasan la validación correspondiente: " + validationDetails)
                .build();
    }
}