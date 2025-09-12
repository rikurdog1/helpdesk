package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.CargoRecord;
import com.base.basetomee.util.Result;

import java.util.List;
import java.util.concurrent.Future;

public interface CargoServInt {
    public Result<CargoRecord> nuevo(CargoRecord bean);
    Result<CargoRecord> get(String id);

    public Result<List<CargoRecord>> getAll();
    public Result<CargoRecord> modificar(CargoRecord bean);
    public Result<String> eliminar(String id);


}
