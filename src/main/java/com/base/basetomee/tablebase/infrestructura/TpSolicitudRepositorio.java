package com.base.basetomee.tablebase.infrestructura;
import com.base.basetomee.tablebase.dominio.TpSolicitudRecord;
import com.base.basetomee.util.Result;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class TpSolicitudRepositorio implements TpSolicitudInt{

    @Resource(name = "jdbc/postgres")
    DataSource db;

    @Override
    public Result<TpSolicitudRecord> registrar(TpSolicitudRecord bean) {
        String sql = """
                    INSERT INTO PUBLIC.TPSOLICITUD (co_soli, nb_soli, datetracer)VALUES(?,?,?)
                """;
        try (final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1,bean.co_soli());
            pstmt.setString(2,bean.nb_soli());
            pstmt.setString(3, bean.datetracer());

            int affectedRow = pstmt.executeUpdate();
            con.commit();

            return  new Result<TpSolicitudRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<TpSolicitudRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<TpSolicitudRecord> read(String id) {
        String sql = """
                    SELECT I.* FROM PUBLIC.TPSOLICITUD I WHERE I.co_soli=?
                """;
        TpSolicitudRecord bean = null;

        //Driver Resource
        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, id);
            ResultSet orset = pstmt.executeQuery();


            while (orset.next()){
                bean = parse(orset);
            }

            return new Result<TpSolicitudRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<TpSolicitudRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<TpSolicitudRecord> update(TpSolicitudRecord bean) {
        String sql = """
                UPDATE PUBLIC.TPSOLICITUD SET nb_soli=? WHERE co_soli=?
                """;
        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, bean.nb_soli());
            pstmt.setString(2, bean.co_soli());

            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return  new Result<TpSolicitudRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<TpSolicitudRecord>().Fail(e.getMessage());
        }
    }

    @Override
    public Result<List<TpSolicitudRecord>> listar() {
        return null;
    }

    @Override
    public Result<TpSolicitudRecord> eliminar(String id) {
        String sql = """
                DELETE FROM PUBLIC.TPSOLICITUD WHERE co_soli=?;
            """;

        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            // Limpiar el ID antes de usarlo
            String sanitizedId = id.trim();
            pstmt.setString(1, id);

            int rowsAffected = pstmt.executeUpdate();
            con.commit();

            if (rowsAffected > 0) {
                // Si el numero de filas afectadas es mayor a 0, la eliminación fue exitosa
                return new Result<String>().OK("Registro con ID " + id + " eliminado correctamente.");
            } else {
                // Si rowsAffected es 0, no se encontró el registro
                return new Result<String>().Fail("No se encontró el registro con el ID: " + id + ".");
            }

        } catch (Exception e) {
            log.error("Error al eliminar el registro: " + e.getMessage());
            return new Result<String>().Fail("Error al eliminar el registro: " + e.getMessage());
        }
    }


    protected TpSolicitudRecord parse(ResultSet orset) throws SQLException{

        return new TpSolicitudRecord(orset.getString("co_soli"),
                orset.getString("nb_soli"), orset.getString("datetracer")  );

    }


}
