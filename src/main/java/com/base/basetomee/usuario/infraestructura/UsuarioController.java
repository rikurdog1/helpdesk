/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.usuario.infraestructura;

import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.usuario.aplicacion.UsuarioServInt;
import com.base.basetomee.usuario.dominio.user;
import com.base.basetomee.usuario.dominio.usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


/**
 *
 * @author rikurdog31
 */

@Log4j2
@Path("/Usuario")
@ApplicationScoped
@Tag(name = "Servicios de gestión de usuarios.")
@OpenAPIDefinition(info = @Info(title = "Grstión de usuario.", version = "1.0"))
public class UsuarioController {
    
    @Inject
    UsuarioServInt serv;
    
    @GET()
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
     
    @APIResponse(responseCode = "200", description = "Usuario encontrado.",
             content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = usuario.class)))
    @APIResponse(responseCode = "404", description = "Usuario no encontrado.")
    @APIResponse(responseCode = "409", description = "Error de validación datos")
    @Operation(summary = "Buscar usuario.", description = "Permite busar un usuario.")
    public Response getUsuario() {
        log.error("creo que tengo un error.");
               
        usuario usuari = serv.getUsuario("").get();
        return  Response.ok(usuari).type(MediaType.APPLICATION_JSON).build() ;
    }
    
    @POST()
    @Path("/set")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})
    @APIResponse(responseCode = "200", description = "Respuestas posible del registro de usuario.",
             content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = usuario.class))) 
    @APIResponse(responseCode = "409", description = "Error de validación datos.",
             content = @Content(mediaType = "application/problem+json",
                                schema = @Schema(implementation = ProblemDetails.class))) 
    @Operation(summary = "Registrar usuario.", description = "Permite registrar un nuevo usuario.")
    public Response getUsuario(@RequestBody(description = "Datos del usuario", required = true, 
                               content = @Content(schema = @Schema(name = "usuarioRecord",  type = SchemaType.OBJECT, implementation = usuario.class)))
                               @Valid usuario bean) {
        
        log.error(bean.id());
        
        user us = new user();
        us.setNombre("ricardo");
        us.setApellido("Martinez");
        
        usuario usuari = serv.getUsuario("").get();
       
        return  Response.ok(usuari).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET()
    @Path("/asincrona")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFuture() {
        log.debug("creo que tengo un Debug.");
               
        usuario usuari = serv.getUsuario("").get();
        //return CompletableFuture.supplyAsync(()-> serv.getUsuario("").get());
        //return new AsyncResult<>(serv.getUsuario("").get());
        //return  (Future<usuario>) Response.ok(usuari).type(MediaType.APPLICATION_JSON).build() ;
        return  Response.ok(usuari).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/procesar/{dato}")
    @Produces(MediaType.TEXT_PLAIN)
    public String procesar(@PathParam("dato") String dato) throws InterruptedException, ExecutionException {
        
        log.debug("1111111");
        return serv.procesarDatosAsync(dato).get();
    }
}