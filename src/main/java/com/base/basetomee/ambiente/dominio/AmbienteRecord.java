package com.base.basetomee.ambiente.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

@Schema(name = "cargoRecord", description = "Datos del Cargo.")
@Log4j2

public record AmbienteRecord(

        @Schema(description = "Nombre Ambiente:", required = true, example = "Produccion", hidden = false)
        @Size(min = 3, max = 100, message = "Nombre Ambiente: tamaño incorrecto")
        @NotNull(message = "Codigo Cargo: no puede estar vacio.")
        @JsonbProperty ("nb_ambiente") String nb_ambiente,

        @Schema(description = "Codigo Ambiente:", required = true, example = "123456", hidden = false)
        @JsonbProperty ("co_ambiente")
        int co_ambiente,

        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @NotNull(message = "La fecha de Emision no puede quedar vacia")
        @JsonbProperty("dateemision")
        String dateemision

) {

    static public AmbienteRecord CodGenerator(AmbienteRecord bean) {
        var a = RandomStringUtils.randomNumeric(6);
        //Convertidor
        int codAm = Integer.parseInt(a);

        LocalDate dateActual = LocalDate.now();
        var dateEmi = dateActual.toString();
        log.debug(a);
        return new AmbienteRecord(
                bean.nb_ambiente(), codAm, dateEmi
        );
    }

}
