package com.base.basetomee.tablebase.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;

@Schema(name = "empresaRecord", description = "Datos de Empresa.")
@Log4j2

public record EmpresaRecord(
        //Anotaciones para validar Codigo de Empresa

        @Schema(description = "Codigo Empresa:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Empresa: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @JsonbProperty("co_emp")
        String co_emp,
        
        //Anotaciones para validar Nombre Empresa
        @Schema(description = "Nombre Empresa:", required = true, example = "Soluciones Sycom", hidden = false)
        @Size(min = 3, max = 20, message = "Nombre Empresa: tamaño incorrecto.")
        @NotNull(message = "El Nombre de la empresa no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty("nb_emp")
        String nb_emp,
        
        @Schema(description = "Estado:", required = true, example = "ACTIVO", hidden = false)
        @Size(min = 3, max = 10, message = "Estado: tamaño incorrecto, debe asignar un valor comprendido entre 3 y 10 digitos")
        @NotNull(message = "Estado: no puede quedar vacio.")
        @JsonbProperty("st_estado")
        String st_estado
        ) {

    //Metodo para generar un codigo randon para Empresa
    static public EmpresaRecord GeneradorCodEmpresa(EmpresaRecord bean){
        var a = RandomStringUtils.randomNumeric(6);
        log.debug(a);
        return new EmpresaRecord(
                a , bean.nb_emp,"ACTIVO"
        );

    }

}
