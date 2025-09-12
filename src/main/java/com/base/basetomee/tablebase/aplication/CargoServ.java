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
        return null;
    }

    @Override
    public Result<List<CargoRecord>> getAll() {
        return null;
    }

    @Override
    public Result<CargoRecord> modificar(CargoRecord bean) {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }
}
