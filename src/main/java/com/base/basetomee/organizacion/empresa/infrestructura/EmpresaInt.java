package com.base.basetomee.organizacion.empresa.infrestructura;

import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface EmpresaInt {

    public Result<EmpresaRecord> registrar(EmpresaRecord bean);
    public Result<EmpresaRecord> read(String id);
    public Result<EmpresaRecord> update(EmpresaRecord bean);
    public Result<List<EmpresaRecord>> listar();
    public Result<String> eliminar(String id);

}
