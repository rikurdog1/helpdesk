/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.otro;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.log4j.Log4j2;


/**
 *
 * @author rikurdog31
 */

@Log4j2
@Path("/otros")
@Singleton
public class otros {
    
    @GET
    public String sayHello() {
        log.debug("Hola desde otros como estas.");        
        return "Hello World";
    }
    
}
