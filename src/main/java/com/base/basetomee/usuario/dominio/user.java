/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.usuario.dominio;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rikurdog31
 */

@Setter
@Getter
public class user {
    @JsonbProperty("nombre")
    private String nombre;
    @JsonbProperty("apellido")
    private String apellido;
}