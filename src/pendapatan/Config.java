/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendapatan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Administrator
 */
public class Config {
    public static Connection mysqlconfig;
    public static Connection foderoDB()throws SQLException{
        try{
            String url="jdbc:mysql://localhost:3306/project_akhir-db"; //url databse
            String user ="root"; //user database
            String pass=""; //password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
            
        } catch(Exception e){
            System.err.println("koneksi gagal" +e.getMessage());
        }
        return mysqlconfig;
}
}
