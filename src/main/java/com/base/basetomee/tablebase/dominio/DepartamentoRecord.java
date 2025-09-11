    package com.base.basetomee.tablebase.dominio;
import jakarta.json.bind.annotation.JsonbProperty;


public record DepartamentoRecord(
        @JsonbProperty("co_dpt") String co_dpt,
        @JsonbProperty("co_emp") String co_emp,
        @JsonbProperty("nb_dpt") String nb_dpt,
        @JsonbProperty("st_estado") String st_estado

        ) {
}
