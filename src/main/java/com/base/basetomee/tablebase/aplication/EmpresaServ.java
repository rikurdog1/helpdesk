package com.base.basetomee.tablebase.aplication;
import com.base.basetomee.tablebase.infrestructura.EmpresaInt;
import jakarta.inject.Inject;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public class EmpresaServ implements EmpresasServInt {
    @Inject
    EmpresaInt empresaRepo;


    @Override
    public Result<EmpresaRecord> nuevo(EmpresaRecord bean) {
        System.out.println("11111");
        return empresaRepo.registrar(bean);

    }

    @Override
    public Result<EmpresaRecord> get(int id) {
        return null;
    }

    @Override
    public Result<List<EmpresaServ>> getAll() {
        return null;
    }

    @Override
    public Result<String> modificar(EmpresaRecord bean) {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }
}
