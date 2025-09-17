package com.base.basetomee.ambiente.infrestructura;

import com.base.basetomee.ambiente.dominio.AmbienteRecord;
import com.base.basetomee.util.Result;
import java.util.List;

public interface AmbienteInt {

    public Result<AmbienteRecord> registrar(AmbienteRecord bean);
    public Result<AmbienteRecord> read(int id);
    public Result<AmbienteRecord> update(AmbienteRecord bean);
    public Result<List<AmbienteRecord>> listar();
    public Result<AmbienteRecord> eliminar(int id);
}
