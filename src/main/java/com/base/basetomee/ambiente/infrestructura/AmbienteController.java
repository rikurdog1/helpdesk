package com.base.basetomee.ambiente.infrestructura;

import com.base.basetomee.ambiente.aplication.AmbienteServInt;
import com.base.basetomee.ambiente.dominio.AmbienteRecord;
import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.util.Result;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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


@Log4j2
@Path("/ambiente")
@ApplicationScoped
@Tag(name = "Servicio Gestion de Solicitudes")
@OpenAPIDefinition(info = @Info(title = "Servicios Gestion de Ambiente.", version = "1.0"))

public class AmbienteController {

    @Inject
    AmbienteServInt services;

    @POST()
    @Path("/ambiente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa del registro de Ambiente.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AmbienteRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Registrar Ambiente.", description = "Permite registrar un nuevo Ambiente.")

    public Response getAmbiente(@Valid AmbienteRecord bean){
        log.debug(bean.co_ambiente());

        AmbienteRecord ambienteRecord = services.nuevo(bean).get();
        return Response.ok(bean).type(MediaType.APPLICATION_JSON).build();

    }


    @PATCH()
    @Path("/modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa para actualizacion de ambiente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProblemDetails.class)))

    //409
    @APIResponse(responseCode = "409", description = "Error de validacion de datos",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Actualizar Ambiente", description = "Actualizar Ambiente")

    public Response setAmbiente(@Valid AmbienteRecord bean){
        log.debug(bean.co_ambiente());

        AmbienteRecord ambienteRecord = services.modificar(bean).get();
        return Response.ok(bean).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE()
    @Path("/eliminar/{id}")
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa para eliminar ambiente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Eliminar Solicitud.", description = "Permite eliminar ambientes.")

    public Response deleteRow(@PathParam("id")int id){
        log.debug("Intentando eliminar el id: " + id);

        // Llama al servicio para eliminar el registro
        Result<AmbienteRecord> resultado = services.eliminar(id);

        if (resultado.IsSuccess()) {
            return Response.ok(resultado.get()).type(MediaType.APPLICATION_JSON).build();

        } else {
            // En caso de error, devolver un estado 404 (Not Found) o 409 (Conflict)
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ProblemDetails("Error", "No se pudo eliminar el registro: " + resultado.getMsj()))
                    .type("application/problem+json")
                    .build();
        }

    }

}

