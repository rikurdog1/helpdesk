/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.base.basetomee.usuario.dominio;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;



/**
 *
 * @author rikurdog31
 */

@Schema(name = "usuarioRecord", description = "Datos del Usuario.")
public record usuario(@Schema(description = "Id del usuario.", required = true, example = "V1000001", hidden = false)
                      @JsonbProperty("id")
                      String id, 
                      @Schema(description = "Clave del usuario.", required = true, example = "EEW#!$rt3432", hidden = false)
                      @Size(min = 6, max = 20, message = "Clace tamaño incorrecto.")
                      @Pattern(regexp ="^(?=.*[a-z]{2})(?=.*[A-Z]{2})(?=.*\\d{2})(?=.*[$%&#?_!]{2})[\\w\\d$%&#?_!]{6,20}$",  message = "Clave no permitida '${validatedValue}, debe contener dos mayúsculas, dos minúsculas, dos dígitos y dos caracteres $%&#?_!")
                      @JsonbProperty("clave")
                      String clave,
                      @Schema(description = "Nombre del usuario.", required = true, example = "Pedro", hidden = false)
                      @Size(min = 3, max = 50, message = "Campo nombre tamaño incorrecto.")
                      @JsonbProperty("nombre")        
                      String nombre,
                      @Schema(description = "Apellido del usuario.", required = true, example = "Perez", hidden = false)
                      @Size(min = 3, max = 50, message = "Campo apellido tamaño incorrecto.")
                      @JsonbProperty("apellido")         
                      String apellido,
                      @Schema(description = "Email.", required = true, example = "email@gmail.com", hidden = false)
                      @Email(message = "Email no valido")
                      @JsonbProperty("email")         
                      String email,
                      @Schema(description = "Celular.", required = true, example = "04141234578", hidden = false)
                      @NotEmpty(message = "Célulae no puede estar vacio.")
                      @JsonbProperty("celular")        
                      String celular,
                      @Schema(description = "Estado de usuario.", required = true, example = "A", hidden = true)
                      @Pattern(regexp = "(A|I|E)", message = "Estado del usuario no permitido.")
                      @JsonbProperty("estado")        
                      String estado, 
                      @Schema(description = "Rol del usuario.", required = true, example = "ADMIN", hidden = false)
                      @JsonbProperty("rol")
                      String rol,
                      @Schema(description = "fecha de creación.", required = true, example = "2025-09-08 16:53:38", hidden = true)
                      @JsonbProperty("creacion")
                      @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")        
                      LocalDateTime creacion,
                      @Schema(description = "Fecha ultima actualización.", required = true, example = "2025-09-08 16:53:38", hidden = true)
                      @JsonbProperty("actualizacion")
                      @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")        
                      LocalDateTime actualizacion) {
    
    public usuario nuevo(usuario bean){
    
    return new usuario(bean.id, bean.clave, bean.nombre, bean.apellido, bean.email, bean.celular, "A", bean.rol, LocalDateTime.now(), LocalDateTime.now());}


}
