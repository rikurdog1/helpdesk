package com.base.basetomee.organizacion.empresa.infrestructura;


import com.base.basetomee.ambiente.dominio.AmbienteRecord;
import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.organizacion.empresa.aplication.EmpresasServInt;
import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
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
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.List;

@Log4j2
@Path("/empresas")
@ApplicationScoped
@Tag(name = "Servicios Diseño Organizacional ")
@OpenAPIDefinition(info = @Info(title = "Diseño Organizacional.", version = "1.0"))
public class EmpresaContorller {

    @Inject
    EmpresasServInt services;

    @POST()
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa del registro de Empresa.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Registrar Empresa.", description = "Permite registrar una nueva empresa.")



    public Response getEmpresa(@Valid EmpresaRecord bean) {

        log.debug(bean.co_emp());


        var result = services.nuevo(bean);
        if (!result.IsSuccess()){
            return Response.status(400)
                    .entity(result.getMsj())
                    .build();
        }
       // EmpresaRecord empresaRecord = services.nuevo(bean).get();

        return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)

    @APIResponse(responseCode = "200", description = "Lista de todos los registros recuperada exitosamente.",
            content = @Content(mediaType = "application/json",
                    // Usamos 'implementation' para el tipo de objeto individual,
                    // y 'type = ARRAY' para indicar que se devuelve una lista de ellos.
                    schema = @Schema(implementation = EmpresaRecord.class, type = SchemaType.ARRAY)))


    @APIResponse(responseCode = "409", description = "Error de  validacion de datos.",
       content = @Content(mediaType = "application/json",
            schema =  @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Listar todos los registros.", description = "Permite obtener una lista de todos los registros de la tabla.")

    public Response ListEmpresa() {
        List<EmpresaRecord> empresaResult = services.getAll().get();
        log.debug(empresaResult);

        if (empresaResult.isEmpty()){
            // Responde con 404 si no hay data en empresas
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron empresas.")
                    .build();
        } else {
            // Responde con 200 OK si la lista NO está vacía
            return Response.ok(empresaResult).build();
        }
    }


    @PATCH()
    @Path("/modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Modificacion Exitosa, de Empresa.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Actualizar Empresa.", description = "Permite Actualizar una empresa existente.")

    public Response UpdateEmpresa(@Valid EmpresaRecord bean){
        log.debug(bean.co_emp());

        EmpresaRecord empresaRecord = services.modificar(bean).get();
        return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();

    }

    @DELETE()
    @Path("/eliminar/{id}")
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa para eliminar Empresa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Eliminar Solicitud.", description = "Permite eliminar Empresa.")

    public Response deleteRow(@PathParam("id")String id){
        log.debug("Intentando eliminar el id: " + id);

        // Llama al servicio para eliminar el registro
        Result<EmpresaRecord> resultado = services.eliminar(id);

        if (resultado.IsSuccess()) {
            return Response.ok(resultado.get()).type(MediaType.APPLICATION_JSON).build();

        } else {
            // En caso de error, devolver un estado 404 (Not Found) o 409 (Conflict)
            return Response.status(Response.Status.CONFLICT)
                    .entity("")
                    .type("application/problem+json")
                    .build();
        }

    }




}
