/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Tole
 */
public class Utils {
    private static Connection koneksi;
    public static Connection foderoDB() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/project_akhir_db";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url, user, pass);
        } catch (Exception e){
            System.err.println("Koneksi Gagal "+e.getMessage());
        }
        return koneksi;
    }
    
    public static void execQuery(String query) throws SQLException{
        Connection conn = (Connection) foderoDB();
        PreparedStatement stm = conn.prepareStatement(query);
        stm.execute();
    }
}
