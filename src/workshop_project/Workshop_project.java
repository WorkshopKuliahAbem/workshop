/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop_project;

/**
 * @author ABADI
 */

import java.sql.*;
import com.mysql.jdbc.Driver;
public class Workshop_project {
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
}
