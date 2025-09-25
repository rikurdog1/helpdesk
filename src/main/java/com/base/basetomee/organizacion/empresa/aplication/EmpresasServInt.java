package com.base.basetomee.organizacion.empresa.aplication;

import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface EmpresasServInt {

    public Result<EmpresaRecord> nuevo(EmpresaRecord bean);
    Result<EmpresaRecord> get(String id);

    public Result<List<EmpresaRecord>> getAll();
    public Result<EmpresaRecord> modificar(EmpresaRecord bean);
    public Result<String> eliminar(String id);

}
