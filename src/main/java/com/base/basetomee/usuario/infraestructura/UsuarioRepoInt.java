/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.base.basetomee.usuario.infraestructura;

import com.base.basetomee.usuario.dominio.usuario;
import com.base.basetomee.util.Result;
import java.util.List;

/**
 *
 * @author rikurdog31
 */
public interface UsuarioRepoInt {
    
    public Result<usuario> registrar(usuario bean);
    public Result<usuario> read(int id);
    public Result<usuario> update(usuario bean);
    public Result<List<usuario>> listar();  
    public Result<String> eliminar(String id);
}