package com.base.basetomee.ambiente.infrestructura;
import com.base.basetomee.ambiente.dominio.AmbienteRecord;
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
public class AmbienteRepo  implements AmbienteInt{

    @Resource(name = "jdbc/postgres")
    DataSource db;

    @Override
    public Result<AmbienteRecord> registrar(AmbienteRecord bean) {
        String sql = """
                   INSERT INTO PUBLIC.AMBIENTE (nb_ambiente, co_ambiente, dateemision)
                   VALUES(?,?,?)
                """;

        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, bean.nb_ambiente());
            pstmt.setInt(2, bean.co_ambiente());
            pstmt.setString(3, bean.dateemision());

            int affectedRows  = pstmt.executeUpdate();
            con.commit();

            return new Result<AmbienteRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<AmbienteRecord>().Fail(e.getMessage());
        }
    }

    @Override
    public Result<AmbienteRecord> read(int id) {
        String sql = """
                SELECT I.* FROM PUBLIC.AMBIENTE I WHERE co_ambiente=?
                """;

        AmbienteRecord bean = null;

        try(final Connection con = db.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                bean = parse(rs);
            }
            return new Result<AmbienteRecord>().OK(bean);


        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<AmbienteRecord>().Fail(e.getMessage());
        }
    }

    @Override
    public Result<AmbienteRecord> update(AmbienteRecord bean) {
        String sql = """
                UPDATE PUBLIC.AMBIENTE SET nb_ambiente=? WHERE co_ambiente=?
                """;
        try (final Connection con = db.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.nb_ambiente());
            pstmt.setInt(2, bean.co_ambiente());

            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return  new Result<AmbienteRecord>().OK(bean);

        }catch (Exception e ){
            log.error(e.getMessage());
            return new Result<AmbienteRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<AmbienteRecord> eliminar(int id) {
        String  sql = """
                DELETE FROM PUBLIC.AMBIENTE WHERE co_ambiente=?;
               """;

        try(final Connection con = db.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setInt(1, id);

            int affectedRow = pstmt.executeUpdate();
            con.commit();

            if (affectedRow > 0 ){
                return new Result<String>().OK("Registro: " + id + " eliminado con exito!");
            }else {
                return new Result<String>().Fail("No se encontrol el id: " + id);
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<String>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<List<AmbienteRecord>> listar() {
        return null;
    }



    protected AmbienteRecord parse(ResultSet rs) throws SQLException{

        return  new AmbienteRecord(rs.getString("nb_ambiente"), rs.getInt("co_ambiente"),
                rs.getString("dateemision"));
    }

}
