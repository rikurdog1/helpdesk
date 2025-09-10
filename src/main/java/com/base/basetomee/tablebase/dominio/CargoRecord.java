package com.base.basetomee.tablebase.dominio;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


public record CargoRecord(
    @JsonbProperty ("co_cargo") String co_cargo,
    @JsonbProperty ("co_dpt") String co_dpt,
    @JsonbProperty ("co_emp") String co_emp,
    @JsonbProperty ("nb_carg") String nb_carg,
    @JsonbProperty ("st_estado") String st_estado

    ) {
}
