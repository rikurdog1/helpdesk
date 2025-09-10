/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.base.basetomee.usuario.infraestructura;

import com.base.basetomee.usuario.dominio.usuario;
import com.base.basetomee.util.Result;
import jakarta.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author rikurdog31
 */

@Log4j2
public class UsuarioRepo implements UsuarioRepoInt{
    
    @Resource(name = "jdbc/PostgresDB")
    DataSource bd;

    @Override
    public Result<usuario> registrar(usuario bean) {
        
        String sql = """
                    INSERT INTO PUBLIC.USERS (ID, NAME) 
                    VALUES(?,?) 
                """;
        
        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setInt(1, bean.id());
            pstmt.setString(2, bean.nombre());
            /*
            pstmt.setString(3, bean.apellido());
            pstmt.setString(4, bean.clave());
            pstmt.setString(5, bean.celular());
            pstmt.setString(6, bean.email());
            pstmt.setString(7, bean.rol());
            pstmt.setString(8, bean.estado());         
            pstmt.setTimestamp(9, java.sql.Timestamp.valueOf(bean.creacion()));
            pstmt.setTimestamp(10, java.sql.Timestamp.valueOf(bean.actualizacion()));
            */
            //pstmt.setDate(15, java.sql.Date.valueOf(demanda.getFevalor()));
            //pstmt.setBigDecimal(16, demanda.getMtousd());
            
            int affectedRows = pstmt.executeUpdate();
             con.commit();
            
            return new Result<usuario>().OK(bean);
            
        } catch (Exception e) {           
                log.error(e.getMessage());
                return new Result<usuario>().Fail(e.getMessage());   
        }
    }

    @Override
    public Result<usuario> read(int id) {
        String sql = "SELECT I.* FROM PUBLIC.USERS I WHERE I.ID = ?";
        usuario bean = null;
        
        try(final Connection con = bd.getConnection();
            PreparedStatement st = con.prepareStatement(sql);)
        {
            st.setInt(1, id);
            ResultSet orset = st.executeQuery();
            
            while (orset.next()) {                                                
                bean = parse(orset);
            }
        
            return new Result<usuario>().OK(bean);
            
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<usuario>().Fail(e.getMessage());
        } 
    }

    @Override
    public Result<usuario> update(usuario bean) {
        
        String sql = "UPDATE PUBLIC.USERS SET NAME = ?, WHERE ID = ?";
             
        try(final Connection con = bd.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql))
        {                                    
            pstmt.setInt(1, bean.id());
            pstmt.setString(2, bean.nombre());
            pstmt.setString(3, bean.apellido());
            pstmt.setString(4, bean.clave());
            pstmt.setString(5, bean.celular());
            pstmt.setString(6, bean.email());
            pstmt.setString(7, bean.rol());
            pstmt.setString(8, bean.estado());         
            pstmt.setTimestamp(9, java.sql.Timestamp.valueOf(bean.creacion()));
            pstmt.setTimestamp(10, java.sql.Timestamp.valueOf(bean.actualizacion()));
                   
            pstmt.executeUpdate(); 
            
            return new Result<usuario>().OK(bean);
                               
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result<usuario>().Fail(e.getMessage());
        }
    }

    @Override
    public Result<List<usuario>> listar() {
        String sql = """
                    SELECT I.* 
                    FROM OCIB.DEMANDA I 
                    WHERE ST_DEMANDA IN ('R', 'P') AND CO_JORNADA = ? AND CO_MONEDA = ? 
                    AND CO_INSTRUMENTO = ?  AND TC_CAMBIO_DEMANDA = ? AND IN_CONDICION = ? 
                    AND CO_INSTITUCION != ?            
                    AND TO_CHAR(FE_DEMANDA, 'YYYYMMDDHH24MISS') <= ? 
                    ORDER BY FE_DEMANDA
                    """;
        
        List<usuario> beans = new ArrayList<>();                     
               
        try(final Connection con = bd.getConnection();
            PreparedStatement st = con.prepareStatement(sql);)
        {                    
             //BigDecimal b = new BigDecimal(d, MathContext.DECIMAL64);                 
               
            ResultSet orset = st.executeQuery();
            
            while (orset.next()) {                 
               
                beans.add(parse(orset));
            }
        
            return new Result<List<usuario>>().OK(beans);
            
        } catch (Exception e) {
            log.error(e.getMessage());      
            return new Result<List<usuario>>().Fail(e.getMessage());
        } 
    }

    @Override
    public Result<String> eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    protected usuario parse(ResultSet orset) throws SQLException{
     
        /*
        usuario bean = new usuario(orset.getString("CO_DEMANDA"), orset.getString("CO_DEMANDA"), orset.getString("CO_DEMANDA"),
                                   orset.getString("CO_DEMANDA"), orset.getString("CO_DEMANDA"), orset.getString("CO_DEMANDA"),
                                   orset.getString("CO_DEMANDA"), orset.getString("CO_DEMANDA"),
                                   orset.getTimestamp("FE_VALOR").toLocalDateTime(), orset.getTimestamp("FE_VALOR").toLocalDateTime());
        */                          
        
        usuario bean = new usuario(orset.getInt("ID"), orset.getString("NAME"), null,
                                   null, null, null,
                                   null, null,
                                   null, null);
                             
        return bean;  
     }
}