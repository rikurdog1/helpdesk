package com.base.basetomee.organizacion.area.infrestructura;
import com.base.basetomee.organizacion.area.dominio.AreaRecord;
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
public class AreaRepositorio implements AreaInt {
    @Resource(name = "jdbc/postgres")
    DataSource bd;

    @Override
    public Result<AreaRecord> registrar(AreaRecord bean) {

        log.debug("222222");
        String sql = """
                        INSERT INTO PUBLIC.AREA (nb_area, co_empresa, st_area)
                        VALUES(?,?,?)
                     """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.nb_area());
            pstmt.setString(2, bean.co_empresa());
            pstmt.setString(3, bean.st_area());

            int affectedRows = pstmt.executeUpdate();
            con.commit();
            return new Result<AreaRecord>().OK(bean);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<AreaRecord>().Fail(e.getMessage());
        }
}

    @Override
    public Result<AreaRecord> read(int id) {
        String sql = """
                    SELECT I.* FROM PUBLIC.DEPARTAMENTO I WHERE co_area=?
                """;
            AreaRecord bean = null;

            //Driver Resource
            try(final Connection con = bd.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql))
            {
                pstmt.setInt(1, id);
                ResultSet orset = pstmt.executeQuery();

                while (orset.next()) {
                    bean = parse(orset);
                }

                return new Result<AreaRecord>().OK(bean);

            }catch (Exception e){
                log.error(e.getMessage());
                return new Result<AreaRecord>().Fail(e.getMessage());
            }
    }

    @Override
    public Result<AreaRecord> update(AreaRecord bean) {
        String sql = """
                    UPDATE PUBLIC.DEPARTAMENTO SET nb_area=?, co_empresa=?, st_area=? WHERE co_area=?
                """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setString(1, bean.nb_area());
            pstmt.setString(2, bean.co_empresa());
            pstmt.setString(3, bean.st_area());
            pstmt.setInt(4, bean.co_area());

            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return new Result<AreaRecord>().OK(bean);

        }catch (Exception e){
            log.debug(bean);
            return new Result<AreaRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<List<AreaRecord>> listar() {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }

    protected AreaRecord parse(ResultSet orset) throws SQLException{

        return new AreaRecord(orset.getInt("co_area"), orset.getString("nb_area"),
                orset.getString("fe_registro"), orset.getString("co_empresa"), orset.getString("st_area"));

    }

}
