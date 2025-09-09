/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.base.basetomee.usuario.infraestructura;

import com.base.basetomee.usuario.dominio.usuario;
import java.util.List;

/**
 *
 * @author rikurdog31
 */
public interface UsuarioRepoInt {
    
    public boolean registrar(usuario bean);
    public usuario get(String id);
    public usuario update(usuario bean);
    public List<usuario> listar();   
}