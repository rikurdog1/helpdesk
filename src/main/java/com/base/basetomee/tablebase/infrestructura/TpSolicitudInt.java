package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.TpSolicitudRecord;
import com.base.basetomee.util.Result;
import java.util.List;

public interface TpSolicitudInt {

    public Result<TpSolicitudRecord> registrar(TpSolicitudRecord bean);
    public Result<TpSolicitudRecord> read(String id);
    public Result<TpSolicitudRecord> update(TpSolicitudRecord bean);
    public Result<List<TpSolicitudRecord>> listar();
    public Result<String> eliminar(String id);

}
