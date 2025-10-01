package com.base.basetomee.organizacion.empresa.infrestructura;

import com.base.basetomee.organizacion.empresa.dominio.EmpresaRecord;
import com.base.basetomee.util.Result;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.security.cert.Extension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class EmpresaRepositorio implements EmpresaInt {
    @Resource(name = "jdbc/PostgresDB")
    DataSource bd;


    @Override
    public Result<EmpresaRecord> registrar(EmpresaRecord bean) {
        String sql = """
                        INSERT INTO PUBLIC.EMPRESA (co_emp, nb_emp, st_estado, autor)
                        VALUES(?,?,?,?)
                     """;

        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.co_emp());
            pstmt.setString(2, bean.nb_emp());
            pstmt.setString(3, bean.st_estado());
            pstmt.setString(4, bean.autor());

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
            pstmt.setString(1, bean.nb_emp());
            pstmt.setString(2, bean.st_estado());
            pstmt.setString(3, bean.co_emp());

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
        String sql = """
                SELECT * FROM PUBLIC.EMPRESA
            """;

        // Inicializar la lista para guardar TODAS las empresas
        List<EmpresaRecord> empresas = new ArrayList<>();

        try (final Connection con = bd.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet orset = pstmt.executeQuery();


            while (orset.next()) {
                // Agregar cada registro a la lista
                empresas.add(parse(orset));
            }
            log.debug(empresas.size());

            // Devolver la lista COMPLETA como resultado exitoso (HTTP 200)
            // La lista puede ser vacía si no hay registros, ¡lo cual es OK!
            return new Result<List<EmpresaRecord>>().OK(empresas);

        } catch (Exception e) {
            // Capturar errores de conexión o SQL (HTTP 409 o similar)
            log.error("Error al listar empresas: {}", e.getMessage());
            return new Result<List<EmpresaRecord>>().Fail("Error de base de datos: " + e.getMessage());
        }
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }


    protected EmpresaRecord parse(ResultSet orset) throws SQLException{

        return new EmpresaRecord(orset.getString("co_emp"), orset.getString("nb_emp"),
                orset.getString("st_estado"), orset.getString("fe_registro"), orset.getString("autor"));

    }

}
