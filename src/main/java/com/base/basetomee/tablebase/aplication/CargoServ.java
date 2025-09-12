package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.CargoRecord;
import com.base.basetomee.tablebase.infrestructura.CargoInt;
import jakarta.inject.Inject;
import com.base.basetomee.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class CargoServ implements CargoServInt{

    private static final Logger log= LogManager.getLogger(CargoServ.class);
    @Inject
    CargoInt cargoRepo;

    @Override
    public Result<CargoRecord> nuevo(CargoRecord bean) {
        log.debug("Se esta quedando en Nuevo CargoServ");
        return cargoRepo.registrar(CargoRecord.GenCodCRG(bean));
    }

    @Override
    public Result<CargoRecord> get(String id) {
        return cargoRepo.read(id);
    }

    @Override
    public Result<List<CargoRecord>> getAll() {
        return null;
    }

    @Override
    public Result<CargoRecord> modificar(CargoRecord bean) {
        log.debug("Se esta quedanto en Modificar CargoServ");
        //Validamos que el co_dpt exista
        Result<CargoRecord> cargo = cargoRepo.read(bean.co_carg());
        if(! cargo.IsSuccess()) return cargo;
        return cargoRepo.update(bean);
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }
}
