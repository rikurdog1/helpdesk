package com.base.basetomee;

import com.base.basetomee.negocio.aplicacion.ResourcePropiedades;
import com.base.basetomee.negocio.aplicacion.holaService;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 */
@Log4j2
@Path("/hello")
@Singleton
public class HelloController {
    
    @Inject
    holaService serv;
    
    @Inject
    @ConfigProperty(name = "injected.value")
    private String injectedValue;
    
    @Resource
    ResourcePropiedades prop;

    @GET
    public String sayHello() {
        log.error("creo que tengo un error.");
        return serv.getSaludo() + " "+ prop.getValor1() + " " + injectedValue;
    }
}