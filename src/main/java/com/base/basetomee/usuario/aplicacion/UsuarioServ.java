/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.usuario.aplicacion;

import com.base.basetomee.usuario.dominio.usuario;
import com.base.basetomee.usuario.infraestructura.UsuarioRepoInt;
import com.base.basetomee.util.Result;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author rikurdog31
 */
@Log4j2
@ApplicationScoped
public class UsuarioServ implements UsuarioServInt{
  
    @Inject
    UsuarioRepoInt userRepo;
    
    @Override
    public Result<usuario> regNuevoUsuario(usuario bean) {
        
        log.debug("Registrando un nuevo usuario.");  
        log.debug("Código del Usuario: " + bean.id());  
          
        return  userRepo.registrar(usuario.nuevo(bean));    
    }

    @Override
    public Result<usuario> getUsuario(int id) {
            log.debug(RandomStringUtils.randomAlphabetic(6));         
        return userRepo.read(id);
    }

    @Override
    public Result<List<usuario>> getAll() {   
        return userRepo.listar();
    }

    @Override
    public Result<String> modificar(usuario bean) {
        return new Result().OK("true");
    }

    @Override
    public Result<String> eliminar(String id) {
        return new Result().OK("true");
    }

    @Override
    public Result<String> cambioClave(usuario bean, String clave) {
        return new Result().OK("true");
    }
    
    @Override
    @Asynchronous
    public Future<String> procesarDatosAsync(String input) {
        
        log.debug("22222");
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simular tarea larga (3 segundos)
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Procesamiento interrumpido", e);
            }
            return "Resultado procesado para: " + input.toUpperCase();
        });
    }
}