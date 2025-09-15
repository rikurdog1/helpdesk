package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.usuario.dominio.usuario;
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
public class EmpresaRepositorio implements EmpresaInt {
    @Resource(name = "jdbc/PostgresDB")
    DataSource bd;


    @Override
    public Result<EmpresaRecord> registrar(EmpresaRecord bean) {
        String sql = """
                        INSERT INTO PUBLIC.EMPRESA (co_emp, nb_emp, st_estado, dateemision)
                        VALUES(?,?,?,?)
                     """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.co_emp());
            pstmt.setString(2, bean.nb_emp());
            pstmt.setString(3, bean.st_estado());
            pstmt.setString(4, bean.dateemision());

            int affectedRows = pstmt.executeUpdate();
            con.commit();
            return new Result<EmpresaRecord>().OK(bean);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<EmpresaRecord>().Fail(e.getMessage());
        }
    }

    @Override
    public Result<EmpresaRecord> read(String id) {
        String sql = """
                SELECT I.* FROM PUBLIC.EMPRESA I WHERE co_emp= ?
                """;
        EmpresaRecord bean = null;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, id);
            ResultSet orset = pstmt.executeQuery();

            while (orset.next()) {
                bean = parse(orset);
            }

           return new Result<EmpresaRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<EmpresaRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<EmpresaRecord> update(EmpresaRecord bean) {
        String sql = """
                    UPDATE PUBLIC.EMPRESA SET  nb_emp=?, st_estado=? WHERE co_emp=?
                """;
        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(2, bean.nb_emp());
            pstmt.setString(3, bean.st_estado());
            pstmt.setString(4, bean.co_emp());

            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return  new Result<EmpresaRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<EmpresaRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<List<EmpresaRecord>> listar() {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }


    protected EmpresaRecord parse(ResultSet orset) throws SQLException{

        return new EmpresaRecord(orset.getString("co_emp"), orset.getString("nb_emp"),
                orset.getString("st_estado"), orset.getString("dateemision"));

    }

}
