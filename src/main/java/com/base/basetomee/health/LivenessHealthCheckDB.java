package com.base.basetomee.health;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rikurdog31
 */
@Liveness
@ApplicationScoped
public class LivenessHealthCheckDB implements HealthCheck{
    
    @Resource(name = "jdbc/PostgresDB")
    DataSource bd;
    
    String time;
    
   @PostConstruct
   private void ini(){
       time = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
   }
    
    @Override
    public HealthCheckResponse call() {
        
        boolean bdStatus = pingBD();
        
        return HealthCheckResponse
                .builder()
                .name("Base de datos check")
                .down()
                .status(bdStatus)
                .withData("status", (bdStatus) ? "ALIVE": "DOWN")
                .withData("At", LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"))).build(); 
    }
           
    
    private boolean pingBD(){
    
        String sql = "SELECT 1";
            
        try(Connection con = bd.getConnection();
            Statement st = con.createStatement()){

            st.executeQuery(sql);
            return true;

        }catch(SQLException e){
            return false;
        }   
    }
}