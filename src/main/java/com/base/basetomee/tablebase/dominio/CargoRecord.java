package com.base.basetomee.tablebase.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

@Schema(name = "cargoRecord", description = "Datos del Cargo.")
@Log4j2


public record CargoRecord(

        @Schema(description = "Codigo Cargo:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Cargo: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Cargo: no puede estar vacio.")
        @JsonbProperty ("co_cargo") String co_carg,

        @Schema(description = "Codigo Departamento:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Departamento: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Departamento: no puede estar vacio.")
        @JsonbProperty ("co_dpt") String co_dpt,

        @Schema(description = "Codigo Empresa:", required = true, example = "123456", hidden = false)
        @Size(min = 6, max = 6, message = "Codigo Empresa: debe contener un rango de (6) digitos")
        @NotNull(message = "Codigo Empresa: no puede estar vacio.")
        @JsonbProperty ("co_emp") String co_emp,

        @Schema(description = "Nombre Cargo:", required = true, example = "Desarrollador", hidden = false)
        @Size(min = 3, max = 120, message = "Nombre Cargo: tamaño incorrecto.")
        @NotNull(message = "El Nombre del Cargo no puede quedar vacio.")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
        @JsonbProperty ("nb_carg") String nb_carg,

        @Schema(description = "Estado", required = true, example = "ACTIVO", hidden = false)
        @Size(min = 3, max = 20, message = "Estado: tamaño incorrecto, el rango debe ser comprendido entre 3 y 10 caracteres")
        @NotNull(message = "El Nombre del Departamento no puede quedar vacio.")
        @JsonbProperty ("st_estado") String st_estado,

        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @NotNull(message = "La fecha de Emision no puede quedar vacia")
        @JsonbProperty("dateemision")
        String dateemision

    ) {

    //Metodo para generar un codigo randon para departamento
    static public CargoRecord GenCodCRG(CargoRecord bean) {
        var a = RandomStringUtils.randomNumeric(6);
        LocalDate dateActual = LocalDate.now();
        var dateEmi = dateActual.toString();
        log.debug(a);
        return new CargoRecord(
                a, bean.co_dpt, bean.co_emp, bean.nb_carg, "ACTIVO", dateEmi
        );
    }

}
