package com.base.basetomee.tablebase.infrestructura;
import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.tablebase.aplication.TpSolicitudServInt;
import com.base.basetomee.tablebase.dominio.TpSolicitudRecord;
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
@Path("/tpsolicitud")
@ApplicationScoped
@Tag(name = "Servicios Gestion de Tablas Base ")
@OpenAPIDefinition(info = @Info(title = "Servicios Gestion de Tablas Base.", version = "1.0"))

public class TpSolicitudController {

    @Inject
    TpSolicitudServInt services;

    @POST()
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa del registro de Solicitud.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TpSolicitudRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Registrar Solicitud.", description = "Permite registrar una solicitud.")


    public Response getTpSoli(@Valid TpSolicitudRecord bean){

        log.debug(bean.co_soli());

            TpSolicitudRecord tpSolicitudRecord = services.nuevo(bean).get();

            return Response.ok(bean).type(MediaType.APPLICATION_JSON).build();
    }


    //Metodo modificar
    @POST()
    @Path("/modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa de actualizacion de Solicitud.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TpSolicitudRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Registrar Solicitud.", description = "Permite registrar una solicitud.")

    public Response UpdateEmpresa(@Valid TpSolicitudRecord bean){
        log.debug(bean.co_soli());

        TpSolicitudRecord tpSolicitudRecord = services.modificar(bean).get();
        return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();

    }

    //Metodo Delete
    @DELETE()
    @Path("/eliminar/{id}")
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa para eliminar solicitud",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Eliminar Solicitud.", description = "Permite eliminar una solicitud por ID.")

    public Response deleteRow(@PathParam("id") String id){
        log.debug("Intentando eliminar ID: " + id);

        // Llama al servicio para eliminar el registro
        Result<TpSolicitudRecord> resultado = services.eliminar(id);

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
