package com.base.basetomee.organizacion.area.aplication;

import com.base.basetomee.organizacion.area.dominio.AreaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface AreaServInt {

    public Result<AreaRecord> nuevo(AreaRecord bean);
    public Result<AreaRecord> get(int id);
    public Result<List<AreaRecord>> getAll();
    public Result<AreaRecord> modificar(AreaRecord bean);
    public Result<String> eliminar(String id);

}
