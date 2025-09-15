package com.base.basetomee.tablebase.aplication;

import com.base.basetomee.tablebase.dominio.TpSolicitudRecord;
import com.base.basetomee.tablebase.infrestructura.TpSolicitudInt;
import jakarta.inject.Inject;
import com.base.basetomee.util.Result;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
public class TpSolicitudServ implements TpSolicitudServInt {

    private static final Logger log= LogManager.getLogger(TpSolicitudServ.class);
    @Inject
    TpSolicitudInt tpSolicitudInt;

    @Override
    public Result<TpSolicitudRecord> nuevo(TpSolicitudRecord bean) {
        log.debug(bean);
        return tpSolicitudInt.registrar(TpSolicitudRecord.GeneradorCoTpSoli(bean));
    }

    @Override
    public Result<TpSolicitudRecord> get(String id) {
        return  tpSolicitudInt.read(id);
    }

    @Override
    public Result<List<TpSolicitudRecord>> getAll() {
        return null;
    }

    @Override
    public Result<TpSolicitudRecord> modificar(TpSolicitudRecord bean) {
        log.debug("Se esta quedando en Modificar Tipo Solicitud");
        Result<TpSolicitudRecord> solicitud = tpSolicitudInt.read(bean.co_soli());
        if(! solicitud.IsSuccess()) return solicitud;

        return tpSolicitudInt.update(bean);
    }

    @Override
    public Result<TpSolicitudRecord> eliminar(String id) {
        log.debug("Servicio llamado: Eliminar");
        Result<TpSolicitudRecord> delete = tpSolicitudInt.eliminar(id);
        return new Result().OK("true");
    }
}
