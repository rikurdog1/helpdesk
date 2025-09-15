package com.base.basetomee.tablebase.dominio;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;


@Schema(name = "departamentoRecord", description = "Datos del Departamento.")
@Log4j2
public record DepartamentoRecord(

        //Anotaciones para validar codigo del departamento
        @Schema(description = "Codigo Departamento:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Departamento: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Departamento: no puede estar vacio.")
        @JsonbProperty("co_dpt")
        String co_dpt,

        @Schema(description = "Codigo Empresa:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Empresa: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @JsonbProperty("co_emp")
        String co_emp,

        @Schema(description = "Nombre Departamento:", required = true, example = "Gestion de Servicios", hidden = false)
        @Size(min = 3, max = 20, message = "Nombre Departamento: tamaño incorrecto.")
        @NotNull(message = "El Nombre del Departamento no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty("nb_dpt")
        String nb_dpt,

        @Schema(description = "Estado", required = true, example = "ACTIVO", hidden = false)
        @Size(min = 3, max = 20, message = "Estado: tamaño incorrecto, el rango debe ser comprendido entre 3 y 10 caracteres")
        @NotNull(message = "El Nombre del Departamento no puede quedar vacio.")
        @JsonbProperty("st_estado")
        String st_estado,

        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @NotNull(message = "La fecha de Emision no puede quedar vacia")
        @JsonbProperty("dateemision")
        String dateemision

        ) {

    //Metodo para generar un codigo randon para departamento
    static public DepartamentoRecord GenCodDPT(DepartamentoRecord bean){
        var a = RandomStringUtils.randomNumeric(6);
        LocalDate dateActual = LocalDate.now();
        var dateEmi = dateActual.toString();
        log.debug(a);
        return new DepartamentoRecord(
                a, bean.co_emp , bean.nb_dpt, "ACTIVO", dateEmi
    );
    }


}
