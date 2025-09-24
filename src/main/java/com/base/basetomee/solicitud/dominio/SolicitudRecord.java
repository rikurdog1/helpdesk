package com.base.basetomee.solicitud.dominio;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDate;

@Schema(name = "solicitudRecord", description = "Datos de Solicitud.")
@Log4j2
public record SolicitudRecord(
        @Schema(description = "Codigo solicitud:", required = true, example = "12345678", hidden = false)
        @Size(min=8, max=8, message = "Codigo de solicitud debe contener un rango de (6) digitos")
        @JsonbProperty ("co_solicitud")
        int co_solicitud,

        @Schema(description = "Fecha de registro:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_registro")
        int fe_registro,

        @Schema(description = "Fecha de Vencimiento:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_vencimiento")
        int fe_vencimiento,

        @Schema(description = "Fecha de Resolucion:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_resolucion")
        int fe_resolucion,

        @Schema(description = "Fecha de Cierre:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_cierre")
        int fe_cierre,

        @Schema(description = "Fecha de Ultima actualizacion:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_ult_modif")
        int fe_ult_modif,

        @Schema(description = "Fecha de Ultima actualizacion:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("co_user_resolutor")
        int co_user_resolutor,

        @Schema(description = "Fecha de Ultima actualizacion:", required = true, example = "2025-09-18 11:24:09.807477", hidden = false)
        @JsonbProperty ("fe_atendido")
        int fe_atendido,


        @Schema(description = "Nombre Ambiente:", required = true, example = "Produccion", hidden = false)
        @Size(min = 3, max = 100, message = "Nombre Ambiente: tamaño incorrecto")
        @NotNull(message = "Codigo Cargo: no puede estar vacio.")
        @JsonbProperty ("nb_ambiente") String nb_ambiente,


        @Schema(description = "Fecha de Emision: ", required = true, example = "2025-09-15", hidden = false)
        @NotNull(message = "La fecha de Emision no puede quedar vacia")
        @JsonbProperty("dateemision")
        String dateemision

) {



}
