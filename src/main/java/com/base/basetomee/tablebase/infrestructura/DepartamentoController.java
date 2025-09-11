package com.base.basetomee.tablebase.infrestructura;
import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.tablebase.aplication.DepartamentoServInt;
import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
@Path("/Departamento")
@ApplicationScoped
@Tag(name = "Servicios Gestion de Tablas Base ")
@OpenAPIDefinition(info = @Info(title = "Servicios Gestion de Tablas Base.", version = "1.0"))
public class DepartamentoController {

        @Inject
        DepartamentoServInt services;

        @POST()
        @Path("/registrar")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

        @APIResponse(responseCode = "200", description = "Respuesta Exitosa del registro de Departamento.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = EmpresaRecord.class)))

        @APIResponse(responseCode = "409", description = "Error de validación datos.",
                content = @Content(mediaType = "application/problem+json",
                        schema = @Schema(implementation = ProblemDetails.class)))

        @Operation(summary = "Registrar Departamento.", description = "Permite registrar un nuevo Departamento.")

        public Response getDPT(@Valid DepartamentoRecord bean) {

            log.debug(bean.co_emp());

            DepartamentoRecord departamentoRecord = services.nuevo(bean).get();

            return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();
        }



}
