/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.negocio.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author rikurdog31
 */
@Log4j2
@ApplicationScoped
public class holaService {
    
    public String getSaludo(){
        return "Hola desde el servicio";
    }
    
}
