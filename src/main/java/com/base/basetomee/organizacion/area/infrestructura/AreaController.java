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
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

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

        var result = services.nuevo(bean);
            if(!result.IsSuccess()) {
                return Response.status(400)
                        .entity(result.getMsj())
                        .build();
            }
            return  Response.ok(result.get()).type(MediaType.APPLICATION_JSON).build();
        }

        @GET()
        @Path("/listar")
        @Produces(MediaType.APPLICATION_JSON)

        @APIResponse(responseCode = "200", description = "Lista de la tabla area",
           content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AreaRecord.class, type = SchemaType.ARRAY)))

        @APIResponse(responseCode = "409", description ="Error de validacion",
            content = @Content(mediaType = "application/json",
               schema = @Schema(implementation = ProblemDetails.class )))

        @Operation(summary = "Listar todos los registros de la tabla Area", description = "Permite obtener la lista de todos los registros")



        public Response listAll(){
            List<AreaRecord> areaResult = services.getAll().get();
            log.debug(areaResult);

            if (areaResult.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontro registros en la tabla area")
                        .build();
            }else {
                return Response.ok(areaResult).build();
            }
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
