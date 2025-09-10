package com.base.basetomee.tablebase.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "empresaRecord", description = "Datos de Empresa.")

public record EmpresaRecord(
        @JsonbProperty("co_emp") String co_emp,
        @JsonbProperty("nb_emp") String nb_emp,
        @JsonbProperty("st_estado") String st_estado
) {




}
