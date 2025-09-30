package com.base.basetomee.organizacion.empresa.infrestructura;


import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.organizacion.empresa.aplication.EmpresasServInt;
import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
import com.base.basetomee.usuario.dominio.usuario;
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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

        EmpresaRecord empresaRecord = services.nuevo(bean).get();

        return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();
    }


    @GET()
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

    @APIResponse(responseCode = "200", description = "Respuesta Exitosa para listar Empresa.",
            // CORREGIDO: Usar 'type = SchemaType.ARRAY' y 'implementation'
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = EmpresaRecord.class)))

    @APIResponse(responseCode = "409", description = "Error de validación datos.",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetails.class)))

    @Operation(summary = "Listar Empresa.", description = "Permite Listar todas las empresas registradas.")


    public Response listEmpresa() {
        // Llamar al servicio
        Result<List<EmpresaRecord>> empresaResult = services.getAll();
        log.debug(empresaResult.isSuccess());

        // Comprobar el estado de la solicitud de servicio
        if (empresaResult.isSuccess()) {
            // Respuesta exitosa (HTTP 200)
            return Response.ok(empresaResult.get())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } else {
            // Error (HTTP 409)
            ProblemDetails problem = createProblemDetailsFrom(empresaResult);
            log.debug(problem);
            return Response.status(Response.Status.CONFLICT) // 409
                    .entity(problem)
                    .type("application/problem+json")
                    .build();
        }
    }

    private ProblemDetails createProblemDetailsFrom(Result<List<EmpresaRecord>> empresaResult) {
        return null;
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




}
