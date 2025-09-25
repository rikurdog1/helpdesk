package com.base.basetomee.organizacion.subarea.aplication;
import com.base.basetomee.organizacion.subarea.dominio.SubareaRecord;
import com.base.basetomee.util.Result;
import java.util.List;

public interface SubareaServInt {
    public Result<SubareaRecord>Nuevo(SubareaRecord bean);
    public Result<List<SubareaRecord>> getAll();
    public Result<SubareaRecord> modificar(SubareaRecord bean);
    public Result<SubareaRecord> eliminar(int id);}


