package com.base.basetomee;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 *
 */

@OpenAPIDefinition(info = @Info(
            title = "Help Desk.", 
            version = "1.0.0",
            description = "Servicio soporte a usuario.",
            contact = @Contact(
                    name = "Sycom", 
                    email = "Sycom@gmail.com",
                    url = "http://www.Sycom.com")
            ),
            servers = {
                @Server(url = "/", description = "Local")
            }
    )
@ApplicationPath("/basetomee")
public class BasetomeeRestApplication extends Application {
}