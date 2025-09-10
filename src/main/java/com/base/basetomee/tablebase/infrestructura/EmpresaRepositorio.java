package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Log4j2
public class EmpresaRepositorio implements EmpresaInt {
    @Resource(name = "jdbc/PostgresDB")
    DataSource bd;


    @Override
    public Result<EmpresaRecord> registrar(EmpresaRecord bean) {
        String sql = """
                        INSERT INTO PUBLIC.EMPRESAS (co_emp, bn_emp, st_estado)
                        VALUES(?,?,?)
                     """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.co_emp());
            pstmt.setString(2, bean.nb_emp());
            pstmt.setString(3, bean.st_estado());

            int affectedRows = pstmt.executeUpdate();

            return new Result<EmpresaRecord>().OK(bean);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<EmpresaRecord>().Fail(e.getMessage());
        }


    }

    @Override
    public Result<EmpresaRecord> read(int id) {
        return null;
    }

    @Override
    public Result<EmpresaRecord> update(EmpresaRecord bean) {
        return null;
    }

    @Override
    public Result<List<EmpresaRecord>> listar() {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }
}
