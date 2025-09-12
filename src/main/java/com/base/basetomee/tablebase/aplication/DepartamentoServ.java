package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
import com.base.basetomee.tablebase.infrestructura.DepartamentoInt;
import jakarta.inject.Inject;
import com.base.basetomee.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class DepartamentoServ implements DepartamentoServInt{
    //Iniciar logger
    private static final Logger log = LogManager.getLogger(DepartamentoServ.class);
    @Inject
    DepartamentoInt departamentoRepo;

    @Override
    public Result<DepartamentoRecord> nuevo(DepartamentoRecord bean) {
        log.debug("Se esta quedando en DepartamentoServ");
        return departamentoRepo.registrar(DepartamentoRecord.GenCodDPT(bean));
    }

    @Override
    public Result<DepartamentoRecord> get(String id) {
        return departamentoRepo.read(id);
    }

    @Override
    public Result<List<DepartamentoRecord>> getAll() {
        return null;
    }

    @Override
    public Result<DepartamentoRecord> modificar(DepartamentoRecord bean) {
        log.debug("Se esta quedanto en Modificar DepartamentoServ");
        //Validamos que el co_dpt exista
        Result<DepartamentoRecord> departamento = departamentoRepo.read(bean.co_emp());
        if(! departamento.IsSuccess()) return departamento;
        return departamentoRepo.update(bean);
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }


}
