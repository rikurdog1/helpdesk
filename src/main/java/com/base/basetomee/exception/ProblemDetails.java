/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.exception;

import jakarta.enterprise.inject.Typed;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 *
 * @author rikurdog31
 */

@Builder
@Data
@Log4j2
@JsonbPropertyOrder({"status", "titulo", "mensaje"})
public class ProblemDetails  {
    @Schema(description = "Tipo")
    private int type;
    @Schema(description = "Titulo")
    private String title;
    @Schema(description = "Detalle del error")
    private String detail;
    @Schema(description = "Estado del error")
    private int status;
}