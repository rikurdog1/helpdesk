package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface DepartamentoServInt {

    public Result<DepartamentoRecord> nuevo(DepartamentoRecord bean);
    public Result<DepartamentoRecord> get(int id);
    public Result<List<DepartamentoServ>> getAll();
    public Result<String> modificar(DepartamentoRecord bean);
    public Result<String> eliminar(String id);

}
