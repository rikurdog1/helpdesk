package com.base.basetomee.tablebase.infrestructura;
import com.base.basetomee.tablebase.dominio.DepartamentoRecord;
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
public class DepartamentoRepositorio implements DepartamentoInt{
    @Resource(name = "jdbc/postgres")
    DataSource bd;

    @Override
    public Result<DepartamentoRecord> registrar(DepartamentoRecord bean) {
        String sql = """
                        INSERT INTO PUBLIC.departamento (co_dpt, co_emp, nb_dpt, st_estado)
                        VALUES(?,?,?,?)
                     """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.co_dpt());
            pstmt.setString(2, bean.co_emp());
            pstmt.setString(3, bean.nb_dpt());
            pstmt.setString(4, bean.st_estado());



            int affectedRows = pstmt.executeUpdate();
            con.commit();

            return new Result<DepartamentoRecord>().OK(bean);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<DepartamentoRecord>().Fail(e.getMessage());
        }
}

    @Override
    public Result<DepartamentoRecord> read(String id) {
        String sql = """
                    SELECT I.* FROM PUBLIC.DEPARTAMENTO I WHERE co_dpt=?
                """;
            DepartamentoRecord bean = null;

            //Driver Resource
            try(final Connection con = bd.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql))
            {
                pstmt.setString(1, id);
                ResultSet orset = pstmt.executeQuery();

                while (orset.next()) {
                    bean = parse(orset);
                }

                return new Result<DepartamentoRecord>().OK(bean);

            }catch (Exception e){
                log.error(e.getMessage());
                return new Result<DepartamentoRecord>().Fail(e.getMessage());
            }
    }

    @Override
    public Result<DepartamentoRecord> update(DepartamentoRecord bean) {
        String sql = """
                    UPDATE PUBLIC.DEPARTAMENTO SET co_dpt=?, co_emp=?, nb_dpt=?, st_estado=? WHERE co_dpt=?
                """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setString(1, bean.co_dpt());
            pstmt.setString(2, bean.co_emp());
            pstmt.setString(3, bean.nb_dpt());
            pstmt.setString(4, bean.st_estado());
            pstmt.setString(5, bean.co_dpt());

            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return new Result<DepartamentoRecord>().OK(bean);

        }catch (Exception e){
            log.debug(bean);
            return new Result<DepartamentoRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<List<DepartamentoRecord>> listar() {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }



    protected DepartamentoRecord parse(ResultSet orset) throws SQLException{

        return new DepartamentoRecord(orset.getString("co_dpt"), orset.getString("co_emp"), orset.getString("nb_dpt") ,orset.getString("st_estado"));

    }

}
