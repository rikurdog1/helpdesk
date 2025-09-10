package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface EmpresaInt {

    public Result<EmpresaRecord> registrar(EmpresaRecord bean);
    public Result<EmpresaRecord> read(int id);
    public Result<EmpresaRecord> update(EmpresaRecord bean);
    public Result<List<EmpresaRecord>> listar();
    public Result<String> eliminar(String id);

}
