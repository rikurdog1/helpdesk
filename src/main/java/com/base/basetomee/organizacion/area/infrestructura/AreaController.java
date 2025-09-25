package com.base.basetomee.organizacion.area.infrestructura;
import com.base.basetomee.exception.ProblemDetails;
import com.base.basetomee.organizacion.area.aplication.AreaServInt;
import com.base.basetomee.organizacion.area.dominio.AreaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Log4j2
@Path("/area")
@ApplicationScoped
@Tag(name = "Servicios Diseño Organizacional ")
@OpenAPIDefinition(info = @Info(title = "Area.", version = "1.0"))
public class AreaController {

        @Inject
        AreaServInt services;

        @POST()
        @Path("/registrar")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

        @APIResponse(responseCode = "200", description = "Respuesta Exitosa del registro de Departamento.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AreaRecord.class)))

        @APIResponse(responseCode = "409", description = "Error de validación datos.",
                content = @Content(mediaType = "application/problem+json",
                        schema = @Schema(implementation = ProblemDetails.class)))

        @Operation(summary = "Registrar Departamento.", description = "Permite registrar un nuevo Departamento.")

        public Response getDPT(@Valid AreaRecord bean) {

        var resul = services.nuevo(bean);
            if(!resul.IsSuccess()) {
                return Response.status(409).type(MediaType.APPLICATION_JSON)
                        .entity(ProblemDetails.builder().type(5)
                                .detail(resul.getMsj())
                                .title("Web Error")
                                .status(409)
                                .build()).build();
            }

            return  Response.ok(resul.get()).type(MediaType.APPLICATION_JSON).build();
        }

        //Metodo para modificacion
        @PATCH()
        @Path("/modificar")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces({MediaType.APPLICATION_JSON, "application/problem+json"})

        @APIResponse(responseCode = "200", description = "Respuesta Exitosa, actualizacion de Departamento.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AreaRecord.class)))

        @APIResponse(responseCode = "409", description = "Error de validación datos.",
                content = @Content(mediaType = "application/problem+json",
                        schema = @Schema(implementation = ProblemDetails.class)))

        @Operation(summary = "Actualizar Area.", description = "Actualizar un nuevo Departamento.")

        public Response UpdateDepartamento(@Valid AreaRecord bean){
            log.debug(bean.co_area());

            AreaRecord areaRecord = services.modificar(bean).get();
            return  Response.ok(bean).type(MediaType.APPLICATION_JSON).build();

        }


}
