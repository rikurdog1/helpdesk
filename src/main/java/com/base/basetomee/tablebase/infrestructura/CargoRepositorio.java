package com.base.basetomee.tablebase.infrestructura;

import com.base.basetomee.tablebase.dominio.CargoRecord;
import com.base.basetomee.util.Result;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.security.cert.Extension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class CargoRepositorio implements CargoInt{

    @Resource(name = "jdbc/postgres")
    DataSource db;

    @Override
    public Result<CargoRecord> registrar(CargoRecord bean) {
        String sql = """
                   INSERT INTO PUBLIC.CARGO (co_carg, co_dpt, co_emp, nb_carg, st_estado)
                   VALUES(?,?,?,?,?)
                """;

        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, bean.co_carg());
            pstmt.setString(2, bean.co_dpt());
            pstmt.setString(3, bean.co_emp());
            pstmt.setString(4, bean.nb_carg());
            pstmt.setString(5, bean.st_estado());

            int affectedRows  = pstmt.executeUpdate();
            con.commit();

            return new Result<CargoRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<CargoRecord>().Fail(e.getMessage());
        }


    }

    @Override
    public Result<CargoRecord> read(String id) {
        String sql = """
                    SELECT I.* FROM PUBLIC.CARGO I WHERE co_carg=?
                """;
        CargoRecord bean = null;

        //Diver Resources
        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, id);
            ResultSet orset = pstmt.executeQuery();

            while (orset.next()) {
                bean = parse(orset);
            }

            return new Result<CargoRecord>().OK(bean);

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result<CargoRecord>().Fail(e.getMessage());
        }


    }

    @Override
    public Result<CargoRecord> update(CargoRecord bean) {
        String sql = """
                UPDATE PUBLIC.CARGO SET co_carg=?, co_dpt=?, co_emp=?, nb_carg=?, st_estado=? WHERE co_carg=?
                """;

        try(final Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, bean.co_carg());
            pstmt.setString(2, bean.co_dpt());
            pstmt.setString(3, bean.co_emp());
            pstmt.setString(4, bean.nb_carg());
            pstmt.setString(5, bean.st_estado());
            pstmt.setString(6, bean.co_carg());


            int affectedRow = pstmt.executeUpdate();
            con.commit();
            return new Result<CargoRecord>().OK(bean);

        } catch (Exception e) {
            log.debug(bean);
            return new Result<CargoRecord>().Fail(e.getMessage());
        }

    }

    @Override
    public Result<List<CargoRecord>> listar() {
        return null;
    }

    @Override
    public Result<String> eliminar(String id) {
        return null;
    }



    protected CargoRecord parse(ResultSet orset) throws SQLException{

        return new CargoRecord(orset.getString("co_carg"), orset.getString("co_dpt"), orset.getString("co_emp"), orset.getString("nb_carg") ,orset.getString("st_estado"));

    }



}
