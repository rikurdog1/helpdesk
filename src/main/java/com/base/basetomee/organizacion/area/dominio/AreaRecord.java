package com.base.basetomee.organizacion.area.dominio;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "AreaRepo", description = "Datos del Area.")
@Log4j2
public record AreaRecord(

        //Anotaciones para validar codigo del Area
        @Schema(description = "Codigo Area:", required = true, example = "1", hidden = false)
        //No valida ya que este campo es auto incremental desde pgAdmin @Size(min = 1, message = "Codigo Area: debe contener al menos (1) digito")
        @NotNull(message = "Codigo Area: no puede estar vacio.")
        @JsonbProperty("co_area")
        int co_area,

        @Schema(description = "Nombre Area:", required = true, example = "Gestion de Servicios", hidden = false)
        @Size(min = 3, max=100 , message = "Nombre Area: tamaño incorrecto.")
        @NotNull(message = "El Nombre del Area no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z0-9-,.\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty("nb_area")
        String nb_area,

        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @JsonbProperty("fe_registro")
        String fe_registro,

        @Schema(description = "Codigo Empresa:", required = true, example = "J123456789", hidden = false)
        @Size(min = 7, max = 15, message = "Codigo Empresa: debe contener un rango de (7 a 14) digitos")
        @NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @JsonbProperty("co_empresa")
        String co_empresa,

        @Schema(description = "Estado", required = true, example = "ACTIVO", hidden = false)
        @Size(min = 4, max = 10, message = "Estado: tamaño incorrecto, el rango debe ser comprendido entre 3 y 10 caracteres")
        //@NotNull(message = "El Estado del Area no puede quedar vacio.")
        @JsonbProperty("st_area")
        String st_area

        ) {

    static public AreaRecord loadArea(AreaRecord bean){
        return new AreaRecord(
                bean.co_area, bean.nb_area, bean.fe_registro, bean.co_empresa(), "ACTIVO"
        );
    }

}
