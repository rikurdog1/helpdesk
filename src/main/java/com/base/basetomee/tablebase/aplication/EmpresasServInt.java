package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;
import java.util.concurrent.Future;

public interface EmpresasServInt {

    public Result<EmpresaRecord> nuevo(EmpresaRecord bean);
    Result<EmpresaRecord> get(String id);

    public Result<List<EmpresaRecord>> getAll();
    public Result<EmpresaRecord> modificar(EmpresaRecord bean);
    public Result<String> eliminar(String id);

}
