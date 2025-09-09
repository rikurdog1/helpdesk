/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.base.basetomee.usuario.aplicacion;

import com.base.basetomee.usuario.dominio.usuario;
import com.base.basetomee.util.Result;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author rikurdog31
 */
public interface UsuarioServInt {
    
    public Result<String> regNuevoUsuario(usuario bean);
    public Result<usuario> getUsuario(String id);
    public Result<List<usuario>> getAll();
    public Result<String> modificar(usuario bean);
    public Result<String> eliminar(String id);
    public Result<String> cambioClave(usuario bean, String clave);
    public Future<String> procesarDatosAsync(String input);
}