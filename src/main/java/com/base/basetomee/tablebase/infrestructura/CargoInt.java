package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.CargoRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface CargoInt {

    public Result<CargoRecord> registrar(CargoRecord bean);
    public Result<CargoRecord> read(String id);
    public Result<CargoRecord> update(CargoRecord bean);
    public Result<List<CargoRecord>> listar();
    public Result<String> eliminar(String id);

}
