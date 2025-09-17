package com.base.basetomee.ambiente.aplication;

import com.base.basetomee.ambiente.dominio.AmbienteRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface AmbienteServInt {

    public Result<AmbienteRecord> nuevo(AmbienteRecord bean);
    Result<AmbienteRecord> get(int id);

    public Result<List<AmbienteRecord>> getAll();
    public Result<AmbienteRecord> modificar(AmbienteRecord bean);
    public Result<AmbienteRecord> eliminar(int id);

}
