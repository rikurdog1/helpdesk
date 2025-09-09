/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.usuario.aplicacion;

import com.base.basetomee.usuario.dominio.usuario;
import com.base.basetomee.util.Result;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.faulttolerance.Asynchronous;

/**
 *
 * @author rikurdog31
 */
@Log4j2
@ApplicationScoped
public class UsuarioServ implements UsuarioServInt{
  
    @Override
    public Result<String> regNuevoUsuario(usuario bean) {
        
        log.debug("Registrando un nuevo usuario.");  
        log.debug("bean: " + bean.id());  
        
        return new Result<String>().OK("true");
    }

    @Override
    public Result<usuario> getUsuario(String id) {
        return new Result<usuario>().OK(new usuario("caracas", "W#$r3432", "rrrrrr", "mmmmmm", "rmir@gmail.com", "V10123456", "A", "ADMIN", LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public Result<List<usuario>> getAll() {
        List<usuario> list = List.of();
        
        return new Result<List<usuario>>().OK(list);
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