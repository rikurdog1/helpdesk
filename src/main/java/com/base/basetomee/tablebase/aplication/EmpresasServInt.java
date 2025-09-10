package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;
import java.util.concurrent.Future;

public interface EmpresasServInt {

    public Result<EmpresaRecord> nuevo(EmpresaRecord bean);
    public Result<EmpresaRecord> get(int id);
    public Result<List<EmpresaServ>> getAll();
    public Result<String> modificar(EmpresaRecord bean);
    public Result<String> eliminar(String id);

}
