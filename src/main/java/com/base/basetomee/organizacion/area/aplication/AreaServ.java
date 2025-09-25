package com.base.basetomee.organizacion.area.aplication;

import com.base.basetomee.organizacion.area.dominio.AreaRecord;
import com.base.basetomee.organizacion.area.infrestructura.AreaInt;
import jakarta.inject.Inject;
import com.base.basetomee.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class AreaServ implements AreaServInt {
    //Iniciar logger
    private static final Logger log = LogManager.getLogger(AreaServ.class);
    @Inject
    AreaInt AreaRepo;

    @Override
    public Result<AreaRecord> nuevo(AreaRecord bean) {
        log.debug("Se esta quedando en AreaServ");
        return AreaRepo.registrar(bean);
    }

    @Override
    public Result<AreaRecord> get(int id) {
        return AreaRepo.read(id);    }

    @Override
    public Result<List<AreaRecord>> getAll() {
        return null;
    }

    @Override
    public Result<AreaRecord> modificar(AreaRecord bean) {
        log.debug("Se esta quedanto en Modificar AreaServServ");
        //Validamos que el co_dpt exista
        Result<AreaRecord> area = AreaRepo.read(bean.co_area());
        if(! area.IsSuccess()) return area;
        return AreaRepo.update(bean);
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }


}
