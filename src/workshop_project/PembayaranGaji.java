/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package workshop_project;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
/**
 *
 * @author alans
 */
public class PembayaranGaji extends javax.swing.JFrame {
public int id_pengeluaran;
public int id_bonus;
public int id_minus;
    
private void tabel(){    
    DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("No");
        model2.addColumn("Nama Pengawai");
        model2.addColumn("Minus");
        model2.addColumn("Ket.Minus");
        model2.addColumn("Gaji");
        model2.addColumn("Bonus");
        model2.addColumn("Ket.Bonus");
        model2.addColumn("ID Pengeluaran");
        model2.addColumn("ID Gaji");
        model2.addColumn("NIK");
        
        try{
    int no =1 ;
//    String sql = "SELECT pegawai.nama_pegawai, pengeluaran.nik, gaji.minus, gaji.keterangan_minus, pegawai.gaji_pegawai, gaji.bonus, pengeluaran.id_pengeluaran FROM pengeluaran JOIN pegawai on pengeluaran.nik = pegawai.nik JOIN gaji ON pengeluaran.id_pengeluaran= gaji.id_pengeluaran";
        
//    SELECT pegawai.nama_pegawai, pengeluaran.nik, gaji.id_pengeluaran,gaji.id_bonus, mst_minus.nama_minus, mst_minus.nominal_minus, mst_bonus.nama_bonus, mst_bonus.nominal_bonus FROM pengeluaran join pegawai on pengeluaran.nik = pegawai.nik JOIN gaji on pengeluaran.id_pengeluaran = gaji.id_pengeluaran join mst_bonus on gaji.id_bonus = mst_bonus.id_bonus join mst_minus ON gaji.id_minus = mst_minus.id_minus;
   String sql = "SELECT pegawai.nama_pegawai,pegawai.gaji_pegawai, pengeluaran.nik, mst_minus.nama_minus, mst_minus.nominal_minus, mst_bonus.nama_bonus, mst_bonus.nominal_bonus FROM pengeluaran join pegawai on pengeluaran.nik = pegawai.nik JOIN gaji on pengeluaran.id_pengeluaran = gaji.id_pengeluaran join mst_bonus on gaji.id_bonus = mst_bonus.id_bonus join mst_minus ON gaji.id_minus = mst_minus.id_minus";
        Connection conn = (Connection)Workshop_project.foderoDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
          while(res.next()){
            model2.addRow(new Object[]{
                no++,  
                res.getString("nama_pegawai"),
                res.getString("nominal_minus"), 
                res.getString("nama_minus"), 
                res.getString("gaji_pegawai"), 
                res.getString("nominal_bonus"),
                res.getString("nama_bonus"),
                res.getString("id_pengeluaran"),
                res.getString("id_gaji"),
                res.getString("nik")});
          }
          tabel.setModel(model2);
        }catch(Exception e){
        }
}

public void tampil_bonus(){
    try{
        String sql = "select * from mst_bonus";
        Connection conn = (Connection)Workshop_project.foderoDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
        
        while(res.next()){
            cb_bonus.addItem(res.getString("nama_bonus"));
        }
        
        res.last();
        int jumlahdata = res.getRow();
        res.first();
        
    }catch(Exception e){
        
    }
}

public void tampil_minus(){
    try{
        String sql = "select * from mst_minus";
        Connection conn = (Connection)Workshop_project.foderoDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
        
        while(res.next()){
            cb_minus.addItem(res.getString("nama_minus"));
        }
        res.last();
        int jmldata = res.getRow();
        res.first();
        
    }catch(Exception e){
        
    }
}
    

private void kosong(){
        txt_nama.enable();
        txt_nama.setText(null);
        txt_gaji.enable();
        txt_gaji.setText(null);
        cb_bonus.setSelectedIndex(0);
        cb_minus.setSelectedIndex(0);
        txt_keteranganMinus.setText(null);
        
    }

  static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dt = LocalDateTime.now();
        return String.valueOf(dtf.format(dt));
    }
    /**
     * Creates new form PembayaranGaji
     */
    public PembayaranGaji() {
        initComponents();
        tabel();
        kosong();
//        tanggal.setText(getDate());
        tampil_bonus();
        tampil_minus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        xxxxxxx = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        txt_gaji = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        btn_clear = new javax.swing.JButton();
        xxxxxxx1 = new javax.swing.JLabel();
        cb_minus = new javax.swing.JComboBox<>();
        cb_bonus = new javax.swing.JComboBox<>();
        txt_keteranganMinus = new javax.swing.JTextField();
        txt_keteranganBonus = new javax.swing.JTextField();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Pembayaran Gaji - Jadi_Revisi Rezise.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 600));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Nominal Minus :");

        jLabel4.setText("Nominal Bonus :");

        txt_nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nikActionPerformed(evt);
            }
        });
        txt_nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nikKeyPressed(evt);
            }
        });

        jLabel5.setText("Jumlah Gaji :");

        xxxxxxx.setText("Keterangan Minus :");

        jLabel2.setText("Nama Pegawai :");

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 16, 12));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(255, 153, 50));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jLabel8.setText("Cari NIK :");

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(255, 153, 50));
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        xxxxxxx1.setText("Keterangan Bonus:");

        cb_minus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cb_minusKeyPressed(evt);
            }
        });

        cb_bonus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_bonusItemStateChanged(evt);
            }
        });
        cb_bonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_bonusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_minus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(xxxxxxx)
                    .addComponent(txt_keteranganMinus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gaji, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(xxxxxxx1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_bonus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_keteranganBonus, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xxxxxxx)
                    .addComponent(xxxxxxx1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_bonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_keteranganBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_keteranganMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_clear)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 550, 0);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
//     try{
//            String sql = "UPDATE gaji SET minus = '"+ txt_minus.getText()
//                    +"', keterangan_minus = '"+txt_keteranganMinus.getText()+"', bonus = '"+ txt_bonus.getText()
//                    + "'WHERE id_pengeluaran = '"+id_pengeluaran+"'";
//            java.sql.Connection conn=(Connection)Workshop_project.foderoDB();
//            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Data berhasil diedit");
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
//        }
//        tabel();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
          try{
            String sql = "DELETE gaji,pengeluaran FROM gaji, pengeluaran where pengeluaran.id_pengeluaran = gaji.id_pengeluaran and gaji.id_pengeluaran ='"+id_pengeluaran+"'";
            String sql2 = "DELETE pengeluaran, gaji FROM pengeluaran, gaji where pengeluaran.id_pengeluaran = gaji.id_pengeluaran and pengeluaran.id_pengeluaran ='"+id_pengeluaran+"'";
            java.sql.Connection conn = (Connection)Workshop_project.foderoDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
            pst.execute();
            pst2.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tabel();
        kosong();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        try{
            String sql1 = "insert into pengeluaran (nik, keterangan_pengeluaran, jenis_pengeluaran, jumlah_pengeluaran, tanggal_pengeluaran) values('"
                    +txt_nik.getText()+"', 'pengeluaran gaji', 'gaji', "+Integer.valueOf(txt_gaji.getText())+", now())";
            
            
            Connection conn = (Connection)Workshop_project.foderoDB();
            PreparedStatement pst1=conn.prepareStatement(sql1);
            pst1.execute();
            String sql2 = "select * from pengeluaran order by id_pengeluaran desc limit 1";
            Statement stm2 = conn.createStatement();
            ResultSet res = stm2.executeQuery(sql2);
           
            res.next();
            int id_pengeluaran = res.getInt("id_pengeluaran");
            String sql = "insert into gaji (id_bonus,id_minus, id_pengeluaran) values('"
                    +id_bonus+","
                    +id_minus+", "+ id_pengeluaran +")";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
            // memanggil ulang table
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tabel();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed
     
    }//GEN-LAST:event_txt_nikActionPerformed

    private void txt_nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nikKeyPressed
        // TODO add your handling code here:
        
        String nik = txt_nik.getText();
      
        try{
             String sql= "select * from pegawai where nik like '%"+ nik +"%' limit 1";
        Connection conn = (Connection)Workshop_project.foderoDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
        
        if(res.next()){
            txt_nama.setText(res.getString("nama_pegawai"));
            txt_gaji.setText(res.getString("gaji_pegawai"));
           
        }
        }catch(Exception e){
            
        }           
    }//GEN-LAST:event_txt_nikKeyPressed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
         String nama = txt_nama.getText();
      
        try{
             String sql= "select * from pegawai where nama_pegawai like '%"+ nama +"%' limit 1";
                Connection conn = (Connection)Workshop_project.foderoDB();
                Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery(sql);
            if(res.next()){
                    txt_nik.setText(res.getString("nik"));
                    txt_gaji.setText(res.getString("gaji_pegawai"));
                  

        }
        }catch(Exception e){
            
        }           
    }//GEN-LAST:event_txt_namaActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        
        int i = tabel.getSelectedRow();
        TableModel tbl = tabel.getModel();
        // Mengambil value dari table
        String field1 = tbl.getValueAt(i, 1).toString();
        String field2 = tbl.getValueAt(i, 2).toString();
        String field3 = tbl.getValueAt(i, 3).toString();
        String field4 = tbl.getValueAt(i, 4).toString();
        String field5 = tbl.getValueAt(i, 5).toString();
        String field6 = tbl.getValueAt(i, 6).toString();
        String field7 = tbl.getValueAt(i, 7).toString();
        // Paste data yang telah diambil
        txt_nama.setText(field1);
        txt_nama.disable();
       
       
        txt_gaji.setText(field4);
        txt_gaji.disable();
        txt_nik.setText(field7);
        id_pengeluaran = Integer.parseInt(field6);
        
       
       
    }//GEN-LAST:event_tabelMouseClicked

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void cb_minusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_minusKeyPressed
        // TODO add your handling code here:
           try{
            String nama = String.valueOf(cb_minus.getSelectedItem());
            String sql = "select * from mst_minus WHERE nama_minus = '"+ nama +"'";
            Connection conn = (Connection)Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            txt_keteranganMinus.setText(res.getString("nominal_minus"));
        }       
    }catch(Exception e){
        
    }
        
    }//GEN-LAST:event_cb_minusKeyPressed

    private void cb_bonusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_bonusItemStateChanged
        try{
        String nama = String.valueOf(cb_bonus.getSelectedItem());
        String sql = "select * from mst_bonus WHERE nama_bonus = '"+ nama +"'";
        Connection conn = (Connection)Workshop_project.foderoDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()){
            txt_keteranganBonus.setText(res.getString("nominal_bonus"));
        }
//        res.last();
//        int jmldata = res.getRow();
//        res.first();
//        
    }catch(Exception e){
        
    }
    }//GEN-LAST:event_cb_bonusItemStateChanged

    private void cb_bonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_bonusActionPerformed
        // TODO add your handling code here:
         
//        String bonus = cb_bonus.getSelectedItem();
//      
//        try{
//             String sql= "select * from mst_bonus where nama_bonus like '%"+ bonus +"%' limit 1";
//        Connection conn = (Connection)Workshop_project.foderoDB();
//        Statement stm = conn.createStatement();
//        ResultSet res = stm.executeQuery(sql);
//        
//        if(res.next()){
//            txt_keteranganBonus.setText(res.getString("gaji_pegawai"));
//           
//        }
//        }catch(Exception e){
//            
//        }    
    }//GEN-LAST:event_cb_bonusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PembayaranGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PembayaranGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PembayaranGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PembayaranGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PembayaranGaji().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cb_bonus;
    private javax.swing.JComboBox<String> cb_minus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txt_gaji;
    private javax.swing.JTextField txt_keteranganBonus;
    private javax.swing.JTextField txt_keteranganMinus;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JLabel xxxxxxx;
    private javax.swing.JLabel xxxxxxx1;
    // End of variables declaration//GEN-END:variables
}
