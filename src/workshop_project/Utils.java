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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Tole
 */
public class Utils {
    public static String nama;
    public static String nik;
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
    
    public static String getSaldo() throws SQLException{
        try{
            String query, query1;
            Statement stm, stm1;
            ResultSet rs, rs1;
            int saldo;
            ArrayList<Integer> pendapatan = new ArrayList<Integer>();
            ArrayList<Integer> pengeluaran = new ArrayList<Integer>();

            query = "select * from pendapatan";
            stm = (Statement) foderoDB().createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                pendapatan.add(Integer.valueOf(rs.getString("jumlah_pendapatan")));
            }
            query = "select * from pengeluaran";
            stm = (Statement) foderoDB().createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                pengeluaran.add(Integer.valueOf(rs.getString("jumlah_pengeluaran")));
            }

            int pend = 0;
            for(int i = 0; i < pendapatan.size(); i++){
                pend += pendapatan.get(i);
            }
            int peng = 0;
            for(int i = 0; i < pengeluaran.size(); i++){
                peng += pengeluaran.get(i);
                System.out.println(pengeluaran.get(i));
            }
            System.out.println(peng);

            saldo = pend - peng;
            double a = Double.valueOf(String.valueOf(saldo));
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

            //merubah mendari 10.000.000.000
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));

            return String.valueOf(nf.format(a));
        } catch(Exception e){
            throw e;
        }
    }
    
    public static String rupiah(String num){
        double a = Double.valueOf(String.valueOf(num));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //merubah mendari 10.000.000.000
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));

        return String.valueOf(nf.format(a));
    }
}
