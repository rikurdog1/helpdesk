/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.util;

/**
 *
 * @author rikurdog31
 * @param <E>
 */

public class Result<E> {
    private boolean succe = false;
    private String _Msj;
    private E _resp;
    
    public Result OK(E resp){
        this._resp = resp;
        this.succe = true;
        return this;
    }
    
    public Result Fail(String Msj){
        this._Msj = Msj;
        this.succe = false;
        return this;
    }
    
    public boolean IsSuccess(){return this.succe;} 
    public E get(){return this._resp;}
    public String getMsj(){return this._Msj;}
}