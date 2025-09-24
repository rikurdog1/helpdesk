package com.base.basetomee.solicitud.aplicacion;

import com.base.basetomee.solicitud.dominio.SolicitudRecord;
import com.base.basetomee.util.Result;
import java.util.List;

public interface SolicitudServInt {

    public Result<SolicitudRecord> nuevo(SolicitudRecord bean);
    Result<SolicitudRecord> get(int id);

    public Result<List<SolicitudRecord>> getAll();
    public Result<SolicitudRecord> modificar(SolicitudRecord bean);
    public Result<SolicitudRecord> eliminar(int id);

}
