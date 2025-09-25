package com.base.basetomee.organizacion.area.dominio;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "AreaRepo", description = "Datos del Area.")
@Log4j2
public record AreaRecord(

        //Anotaciones para validar codigo del Area
        @Schema(description = "Codigo Area:", required = true, example = "1", hidden = false)
        //@Size(min = 1, max = 6, message = "Codigo Area: debe contener un rango de (6) digitos")
        //@NotNull(message = "Codigo Area: no puede estar vacio.")
        @JsonbProperty("co_area")
        int co_area,

        @Schema(description = "Codigo Empresa:", required = true, example = "J123456789", hidden = false)
        //@Size(min = 8, max = 13, message = "Codigo Empresa: debe contener un rango de (8 a 13) digitos")
        //@NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @JsonbProperty("co_empresa")
        String co_empresa,


        @Schema(description = "Nombre Area:", required = true, example = "Gestion de Servicios", hidden = false)
        @Size(min = 2, max = 20, message = "Nombre Area: tamaño incorrecto.")
        //@NotNull(message = "El Nombre del Area no puede quedar vacio.")
        //@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty("nb_area")
        String nb_area,

        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @JsonbProperty("fe_registro")
        String fe_registro,

        @Schema(description = "Estado", required = true, example = "ACTIVO", hidden = false)
        //@Size(min = 1, max = 20, message = "Estado: tamaño incorrecto, el rango debe ser comprendido entre 3 y 10 caracteres")
        //@NotNull(message = "El Nombre del Departamento no puede quedar vacio.")
        @JsonbProperty("st_area")
        String st_area

        ) {

    //Metodo para generar un codigo randon para departamento


}
