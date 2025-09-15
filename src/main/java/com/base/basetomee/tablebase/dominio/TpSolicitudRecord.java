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
import java.time.format.DateTimeFormatter;

@Schema(name = "tpSolicitudRecord", description = "Datos de Solicitud.")
@Log4j2

public record TpSolicitudRecord(
    //Anotaciones para validar Codigo solicitud
    @Schema(description = "Codigo Solicitud:", required = true, example = "1234567891", hidden = false)
    @Size(min = 10, max = 10, message = "Codigo Solicitud: debe contener un rango de (10) digitos")
    @NotNull(message = "Codigo Solicitud: no puede estar vacio.")
    @JsonbProperty("co_soli")
    String co_soli,

    //Anotaciones para validar alarma
    @Schema(description = "Nombre Solicitud:", required = true, example = "Incidencia IBP", hidden = false)
    @Size(min = 3, max = 150, message = "Solicitud: tamaño incorrecto.")
    @NotNull(message = "El tipo Solicitud no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("solicitud")
    String nb_soli,

    @Schema(description = "Fecha de solicitud: ", required = true, example = "2025-09-15", hidden = false)
    @NotNull(message = "La fecha de solicitud no puede quedar vacia")
    @JsonbProperty("datetracer")
    String datetracer



) {

    static public TpSolicitudRecord GeneradorCoTpSoli(TpSolicitudRecord bean){
        var a = RandomStringUtils.randomNumeric(10);

        LocalDate fechaActual = LocalDate.now();
        var dateSoli = fechaActual.toString();

        log.debug(bean);
        return new TpSolicitudRecord(
               a, bean.nb_soli, dateSoli
        );
    }

}
