package com.base.basetomee.tablebase.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;

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
    @Schema(description = "Nombre Alarma:", required = true, example = "Rechazos de solicitudes SYPAGO", hidden = false)
    @Size(min = 3, max = 150, message = "Alarma: tamaño incorrecto.")
    @NotNull(message = "El tipo alarma no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("alarma")
    String alarma,

    //Anotaciones para validar consulta
    @Schema(description = "Nombre Consulta:", required = true, example = "RJCT DU01", hidden = false)
    @Size(min = 3, max = 250, message = "Consulta: tamaño incorrecto.")
    @NotNull(message = "El tipo consulta no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("consulta")
    String consulta,

    //Anotaciones para validar incidencia
    @Schema(description = "Nombre Incidencia:", required = true, example = "Operacion Rechazada ", hidden = false)
    @Size(min = 3, max = 500, message = "Incidencia: tamaño incorrecto.")
    @NotNull(message = "El tipo incidencia no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("incidencia")
    String incidencia,


    //Anotaciones para validar Incumplimiento de Normas CCE
    @Schema(description = "Incumplimiento de Normas CCE:", required = true, example = "Errores en procesos", hidden = false)
    @Size(min = 3, max = 500, message = "Incidencia: tamaño incorrecto.")
    @NotNull(message = "El tipo Incumplimiento no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("incumplnormcce")
    String incumplnormcce,

    //Anotaciones para validar notificacion
    @Schema(description = "Notificacion", required = true, example = "100 transacciones rechazadas", hidden = false)
    @Size(min = 3, max = 250, message = "Incidencia: tamaño incorrecto.")
    @NotNull(message = "El tipo notificacion no puede quedar vacio.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Solo se permiten caracteres alfanumer")
    @JsonbProperty("notificacion")
    String notificacion,

    //Anotaciones para validar reclamo
    @Schema(description = "Reclamo", required = true, example = "Descripcion del reclamo", hidden = false)
    @Size(min = 3, max = 500, message = "Reclamo: tamaño incorrecto.")
    @NotNull(message = "El tipo reclamo no puede quedar vacio.")
    @JsonbProperty("reclamo")
    String reclamo

) {

    static public TpSolicitudRecord GeneradorCoTpSoli(TpSolicitudRecord bean){
        var a = RandomStringUtils.randomNumeric(10);
        log.debug(bean);
        return new TpSolicitudRecord(
               a, bean.alarma, bean.consulta, bean.incidencia,
                bean.incumplnormcce, bean.notificacion, bean.reclamo
        );
    }

}
