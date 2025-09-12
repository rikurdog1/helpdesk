package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface DepartamentoServInt {

    public Result<DepartamentoRecord> nuevo(DepartamentoRecord bean);
    public Result<DepartamentoRecord> get(String id);
    public Result<List<DepartamentoRecord>> getAll();
    public Result<DepartamentoRecord> modificar(DepartamentoRecord bean);
    public Result<String> eliminar(String id);

}
