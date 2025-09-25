package com.base.basetomee.organizacion.area.infrestructura;

import com.base.basetomee.organizacion.area.dominio.AreaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface AreaInt {

    public Result<AreaRecord> registrar(AreaRecord bean);
    Result<AreaRecord> read(int id);
    public Result<AreaRecord> update(AreaRecord bean);
    public Result<List<AreaRecord>> listar();
    public Result<String> eliminar(String id);

}
