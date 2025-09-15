package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.CargoRecord;
import com.base.basetomee.tablebase.dominio.TpSolicitudRecord;
import com.base.basetomee.util.Result;

import java.util.List;

public interface TpSolicitudServInt {

    public Result<TpSolicitudRecord> nuevo(TpSolicitudRecord bean);
    Result<TpSolicitudRecord> get(String id);

    public Result<List<TpSolicitudRecord>> getAll();
    public Result<TpSolicitudRecord> modificar(TpSolicitudRecord bean);
    public Result<TpSolicitudRecord> eliminar(String id);

}
