package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface DepartamentoInt {

    public Result<DepartamentoRecord> registrar(DepartamentoRecord bean);
    Result<DepartamentoRecord> read(String id);
    public Result<DepartamentoRecord> update(DepartamentoRecord bean);
    public Result<List<DepartamentoRecord>> listar();
    public Result<String> eliminar(String id);

}
