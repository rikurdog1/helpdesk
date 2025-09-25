package com.base.basetomee.organizacion.empresa.aplication;
import com.base.basetomee.organizacion.empresa.infrestructura.EmpresaInt;
import jakarta.inject.Inject;

import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmpresaServ implements EmpresasServInt {
    private static final Logger log = LogManager.getLogger(EmpresaServ.class);
    @Inject
    EmpresaInt empresaRepo;


    @Override
    public Result<EmpresaRecord> nuevo(EmpresaRecord bean) {
        log.debug("Se esta quedando en Nuevo EmpresaServ");
        System.out.println();
       return empresaRepo.registrar(EmpresaRecord.GeneradorCodEmpresa(bean));
    }

    @Override
    public Result<EmpresaRecord> get(String id) {
        return empresaRepo.read(id);
    }

    @Override
    public Result<List<EmpresaRecord>> getAll() {
        return null;
    }

    @Override
    public Result<EmpresaRecord> modificar(EmpresaRecord bean) {
        log.debug("Se esta quedando en Modificar EmpresaServ");
        Result<EmpresaRecord> empresa = empresaRepo.read(bean.co_emp());
        if(! empresa.IsSuccess()) return empresa;

        return empresaRepo.update(bean);
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }
}
