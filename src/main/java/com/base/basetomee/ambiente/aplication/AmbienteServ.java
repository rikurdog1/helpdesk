package com.base.basetomee.ambiente.aplication;

import com.base.basetomee.ambiente.dominio.AmbienteRecord;
import com.base.basetomee.ambiente.infrestructura.AmbienteInt;
import com.base.basetomee.ambiente.infrestructura.AmbienteRepo;
import com.base.basetomee.util.Result;
import jakarta.inject.Inject;
import com.base.basetomee.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AmbienteServ implements AmbienteServInt{

    private static final Logger log= LogManager.getLogger(AmbienteServ.class);
    @Inject
    AmbienteInt ambienteRecod;

    @Override
    public Result<AmbienteRecord> nuevo(AmbienteRecord bean) {
        log.debug("Implementacion del metodo Insert");
        return ambienteRecod.registrar(AmbienteRecord.CodGenerator(bean));
    }

    @Override
    public Result<AmbienteRecord> get(int id) {
        return ambienteRecod.read(id);
    }

    @Override
    public Result<List<AmbienteRecord>> getAll() {
        return null;
    }

    @Override
    public Result<AmbienteRecord> modificar(AmbienteRecord bean) {
        log.debug("Se invoco el metodo de actualizacion");
        Result<AmbienteRecord> ambiente = ambienteRecod.read(bean.co_ambiente());
        if (!ambiente.IsSuccess())return  ambiente;
        return ambienteRecod.update(bean);

    }

    @Override
    public Result<AmbienteRecord> eliminar(int id) {
        log.debug("Invocando el metodo DELETE");
        Result<AmbienteRecord> delete = ambienteRecod.eliminar(id);
        return new Result().OK("true");
    }
}
