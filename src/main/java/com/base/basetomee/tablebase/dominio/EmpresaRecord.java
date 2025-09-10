package com.base.basetomee.tablebase.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "empresaRecord", description = "Datos de Empresa.")

public record EmpresaRecord(

        //Anotaciones para validar Codigo de Empresa
        @Schema(description = "Codigo Empresa:", required = true, example = "123456", hidden = false)
        @Size(min= 6, max = 6, message = "Codigo Empresa: debe contener un rango de (6) digitos" )
        @NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @Positive(message = "Codigo Empresa: debe ser a cero")
        @JsonbProperty("co_emp") String co_emp,


        //Anotaciones para validar Nombre Empresa
        @Schema(description = "Nombre Empresa:", required = true, example = "Soluciones Sycom", hidden = false)
        @Size(min = 3, max = 20, message = "Nombre Empresa: tamaño incorrecto.")
        @NotNull(message = "El Nombre de la empresa no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty("nb_emp") String nb_emp,

        @Schema(description = "Estado:", required = true, example = "ACTIVO", hidden = false)
        @Size(min = 3, max = 10, message = "Estado: tamaño incorrecto, debe asignar un valor comprendido entre 3 y 10 digitos")
        @NotNull(message = "Estado: no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z]*$", message = "Solo se permite caracteres de tipo String")
        @JsonbProperty("st_estado") String st_estado



) {




}
