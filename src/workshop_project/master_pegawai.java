/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package workshop_project;

/**
 * @author bima
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

import workshop_project.Workshop_project;

public class master_pegawai extends javax.swing.JFrame {

    Utils util = new Utils();

    /**
     * Creates new form master_pegawai
     */
    public master_pegawai() throws SQLException {
        initComponents();
        tabel();
        name.setText(util.nama);
        nik.setText(util.nik);
        tanggal.setText(getDate());
        btn_edit.setEnabled(false);
        try {
            saldo.setText("Saldo: Rp. " + Utils.getSaldo());
        } catch (SQLException e) {
            throw e;
        }
    }

    // menampilkan tanggal di layar
    static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dt = LocalDateTime.now();
        return String.valueOf(dtf.format(dt));
    }

    // validasi
    boolean val_simpan() {
        if (field_nik.getText().length() < 1 || field_nama.getText().length() < 1 || field_alamat.getText().length() < 1
                || field_gaji.getText().length() < 1 || field_username.getText().length() < 1 || field_nomer.getText().length() < 1
                || field_password.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Field harus diisi semua!", "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // validasi
    boolean val_delete() {
        if (field_nik.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu data terlebih dahulu!", "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // validasi username
    boolean val_username() {
        try {
            String sql = "SELECT * FROM pegawai WHERE username ='" + field_username.getText() + "'";
            Statement statement = (Statement) Workshop_project.foderoDB().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM pegawai WHERE username ='" + field_username.getText() + "'");
            if (!rs.next()) {
                return true;
            }
            JOptionPane.showMessageDialog(null, "Username telah ada !", "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
        }
        return true;
    }

    // validasi NIK
    boolean val_nik() {
        if (field_nik.getText().length() != 16) {
            JOptionPane.showMessageDialog(null, "NIK harus 16 karakter !", "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // clear text
    void clear_text() {
        field_nik.setText("");
        field_nama.setText("");
        field_alamat.setText("");
        field_gaji.setText("");
        field_username.setText("");
        field_nomer.setText("");
        field_password.setText("");
        jenis_kelamin.clearSelection();
        btn_edit.setEnabled(false);
        field_nik.setEnabled(true);
        field_password.setEnabled(true);
        field_password.setBackground(Color.white);
        btn_simpan.setEnabled(true);
    }

    // isi table
    private void tabel() {
        DefaultTableModel tb = new DefaultTableModel();
        // Memberi nama pada setiap kolom tabel
        tb.addColumn("NIK");
        tb.addColumn("Nama Pegawai");
        tb.addColumn("Username");
        tb.addColumn("Alamat");
        tb.addColumn("No. Telpon ");
        tb.addColumn("Jenis Kelamin");
        tb.addColumn("Gaji Pegawai");
        tabel.setModel(tb);
        try {
            // Mengambil data dibuat dari database
            Statement statement = (Statement) Workshop_project.foderoDB().createStatement();
            ResultSet rs = statement.executeQuery("select * from pegawai group by created_at order by created_at desc");

            while (rs.next()) {
                // Mengambil data dari database berdasarkan nama kolom pada tabel
                // Lalu di tampilkan ke dalam Table
                tb.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama_pegawai"),
                    rs.getString("username"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    rs.getString("jenis_kelamin"),
                    rs.getInt("gaji_pegawai")
                });
                tabel.setModel(tb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jenis_kelamin = new javax.swing.ButtonGroup();
        m_dashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        m_pegawai = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        m_gaji = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        m_pengeluaran = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        m_pendapatan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        m_laporan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        field_nama = new javax.swing.JTextField();
        field_alamat = new javax.swing.JTextField();
        field_username = new javax.swing.JTextField();
        field_nik = new javax.swing.JTextField();
        field_gaji = new javax.swing.JTextField();
        field_nomer = new javax.swing.JTextField();
        field_password = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rdb_laki = new javax.swing.JRadioButton();
        rdb_perempuan = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        cari_data = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        tanggal = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        m_minus = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        m_bonus = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        nik = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        m_dashboard.setBackground(new java.awt.Color(244, 244, 244));
        m_dashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_dashboardMouseClicked(evt);
            }
        });
        m_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 114, 114));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dashboard");
        m_dashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 7, -1, -1));

        getContentPane().add(m_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 30));

        m_pegawai.setBackground(new java.awt.Color(167, 191, 191));
        m_pegawai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(114, 114, 114));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pegawai");
        m_pegawai.add(jLabel3);

        getContentPane().add(m_pegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 170, 30));

        m_gaji.setBackground(new java.awt.Color(244, 244, 244));
        m_gaji.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_gaji.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_gaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_gajiMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(114, 114, 114));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gaji");
        m_gaji.add(jLabel4);

        getContentPane().add(m_gaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 170, 30));

        m_pengeluaran.setBackground(new java.awt.Color(244, 244, 244));
        m_pengeluaran.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pengeluaranMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(114, 114, 114));
        jLabel5.setText("Pengeluaran");
        m_pengeluaran.add(jLabel5);

        getContentPane().add(m_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 170, 30));

        m_pendapatan.setBackground(new java.awt.Color(244, 244, 244));
        m_pendapatan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pendapatan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_pendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pendapatanMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(114, 114, 114));
        jLabel6.setText("Pendapatan");
        m_pendapatan.add(jLabel6);

        getContentPane().add(m_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 170, 30));

        m_laporan.setBackground(new java.awt.Color(244, 244, 244));
        m_laporan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_laporanMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(114, 114, 114));
        jLabel7.setText("Laporan");
        m_laporan.add(jLabel7);

        getContentPane().add(m_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 170, 30));

        jPanel9.setBackground(new java.awt.Color(252, 102, 103));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("LOGOUT");
        jPanel9.add(jLabel8);

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 150, 40));

        field_nama.setBackground(new java.awt.Color(255, 255, 255));
        field_nama.setForeground(new java.awt.Color(114, 114, 114));
        getContentPane().add(field_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 220, -1));

        field_alamat.setBackground(new java.awt.Color(255, 255, 255));
        field_alamat.setForeground(new java.awt.Color(114, 114, 114));
        getContentPane().add(field_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 220, -1));

        field_username.setBackground(new java.awt.Color(255, 255, 255));
        field_username.setForeground(new java.awt.Color(114, 114, 114));
        getContentPane().add(field_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 220, -1));

        field_nik.setBackground(new java.awt.Color(255, 255, 255));
        field_nik.setForeground(new java.awt.Color(114, 114, 114));
        field_nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                field_nikKeyTyped(evt);
            }
        });
        getContentPane().add(field_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 230, -1));

        field_gaji.setBackground(new java.awt.Color(255, 255, 255));
        field_gaji.setForeground(new java.awt.Color(114, 114, 114));
        field_gaji.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                field_gajiKeyTyped(evt);
            }
        });
        getContentPane().add(field_gaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 230, -1));

        field_nomer.setBackground(new java.awt.Color(255, 255, 255));
        field_nomer.setForeground(new java.awt.Color(114, 114, 114));
        field_nomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                field_nomerKeyTyped(evt);
            }
        });
        getContentPane().add(field_nomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 230, -1));

        field_password.setBackground(new java.awt.Color(255, 255, 255));
        field_password.setForeground(new java.awt.Color(114, 114, 114));
        field_password.setDisabledTextColor(new java.awt.Color(192, 192, 192));
        getContentPane().add(field_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 230, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(114, 114, 114));
        jLabel11.setText("Nama Pegawai :");

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(114, 114, 114));
        jLabel12.setText("Alamat :");

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(114, 114, 114));
        jLabel13.setText("Username :");

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(114, 114, 114));
        jLabel14.setText("NIK :");

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(114, 114, 114));
        jLabel15.setText("Gaji Pegawai :");

        jLabel16.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(114, 114, 114));
        jLabel16.setText("No Telpon :");

        jLabel17.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(114, 114, 114));
        jLabel17.setText("Password :");

        jLabel18.setForeground(new java.awt.Color(114, 114, 114));
        jLabel18.setText("Cari Data");

        jenis_kelamin.add(rdb_laki);
        rdb_laki.setForeground(new java.awt.Color(114, 114, 114));
        rdb_laki.setText("Laki-laki");

        jenis_kelamin.add(rdb_perempuan);
        rdb_perempuan.setForeground(new java.awt.Color(114, 114, 114));
        rdb_perempuan.setText("Perempuan");
        rdb_perempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_perempuanActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(114, 114, 114));
        jLabel19.setText("Jenis Kelamin :");

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
        jScrollPane2.setViewportView(tabel);

        cari_data.setBackground(new java.awt.Color(255, 255, 255));
        cari_data.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N
        cari_data.setForeground(new java.awt.Color(114, 114, 114));
        cari_data.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari_dataKeyPressed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(255, 153, 50));
        btn_simpan.setText("Simpan");
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 16, 12));
        btn_hapus.setText("Hapus");
        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(90, 90, 90));
        btn_edit.setText("Edit");
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(90, 90, 90));
        btn_clear.setText("Clear");
        btn_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cari_data, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(201, 201, 201)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(rdb_laki, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdb_perempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(202, 202, 202)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdb_laki, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdb_perempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cari_data, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_hapus)
                    .addComponent(btn_edit)
                    .addComponent(btn_clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 590, 390));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        name.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        name.setForeground(new java.awt.Color(114, 114, 114));
        name.setText("Nama Pegawai");
        jPanel11.add(name);
        name.setBounds(0, 0, 150, 30);

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 84, 150, 30));

        jPanel13.setBackground(new java.awt.Color(239, 245, 245));
        jPanel13.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        tanggal.setFont(new java.awt.Font("Lexend", 0, 13)); // NOI18N
        tanggal.setForeground(new java.awt.Color(114, 114, 114));
        tanggal.setText("Tanggal");

        saldo.setFont(new java.awt.Font("Lexend", 0, 13)); // NOI18N
        saldo.setForeground(new java.awt.Color(114, 114, 114));
        saldo.setText("Saldo");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(saldo))
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        m_minus.setBackground(new java.awt.Color(244, 244, 244));
        m_minus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_minus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_minusMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(114, 114, 114));
        jLabel10.setText("Minus");

        javax.swing.GroupLayout m_minusLayout = new javax.swing.GroupLayout(m_minus);
        m_minus.setLayout(m_minusLayout);
        m_minusLayout.setHorizontalGroup(
            m_minusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_minusLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        m_minusLayout.setVerticalGroup(
            m_minusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_minusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(m_minus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 30));

        m_bonus.setBackground(new java.awt.Color(244, 244, 244));
        m_bonus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_bonus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_bonus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_bonusMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(114, 114, 114));
        jLabel20.setText("Bonus");

        javax.swing.GroupLayout m_bonusLayout = new javax.swing.GroupLayout(m_bonus);
        m_bonus.setLayout(m_bonusLayout);
        m_bonusLayout.setHorizontalGroup(
            m_bonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_bonusLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        m_bonusLayout.setVerticalGroup(
            m_bonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_bonusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        getContentPane().add(m_bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jPanel19.setBackground(new java.awt.Color(239, 245, 245));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        jPanel20.setBackground(new java.awt.Color(239, 245, 245));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 50, 20));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 170, 10));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 170, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 10));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 170, 10));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 10));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, -1));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 170, 10));

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/workshop_project/Master Pegawai - Jadi_800x449.jpg"))); // NOI18N
        jLabel2.setName("gambar dasar"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        jPanel3.setBackground(new java.awt.Color(239, 245, 245));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 800, 80));

        nik.setText("jLabel9");
        getContentPane().add(nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        clear_text();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        int opt = JOptionPane.showConfirmDialog(null, "Yakin Untuk Mengedit Data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            try {
                String jenis_kelamin = null;
                if (rdb_laki.isSelected()) {
                    jenis_kelamin = "Laki-Laki";
                } else if (rdb_perempuan.isSelected()) {
                    jenis_kelamin = "Perempuan";
                }
                String sql = "UPDATE pegawai SET nama_pegawai = '" + field_nama.getText()
                        + "', username = '" + field_username.getText() + "', alamat = '" + field_alamat.getText()
                        + "', no_telp = '" + field_nomer.getText() + "', gaji_pegawai = '" + field_gaji.getText()
                        + "', jenis_kelamin = '" + jenis_kelamin + "'WHERE nik = '" + field_nik.getText() + "'";
                java.sql.Connection conn = (Connection) Workshop_project.foderoDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil diedit");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
            }
            tabel();
            clear_text();
            field_nik.setEnabled(true);
            field_password.setEnabled(true);
            field_password.setBackground(Color.white);
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        if (val_delete()) {
            int opt = JOptionPane.showConfirmDialog(null, "Yakin Untuk Menghapus", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {

                try {
                    String sql = "DELETE FROM pegawai where nik='" + field_nik.getText() + "'";
                    java.sql.Connection conn = (Connection) Workshop_project.foderoDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "berhasil di hapus");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                tabel();
                clear_text();
                field_nik.setEnabled(true);
                field_password.setEnabled(true);
                field_password.setBackground(Color.white);
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if (val_simpan()) {
            if (val_nik()) {
                if (val_username()) {
                    try {
                        String jenis_kelamin = null;
                        if (rdb_laki.isSelected()) {
                            jenis_kelamin = "Laki-Laki";
                        } else if (rdb_perempuan.isSelected()) {
                            jenis_kelamin = "Perempuan";
                        }

                        String tipe = "karyawan";
                        String sql = "INSERT INTO pegawai (nik,nama_pegawai,username,PASSWORD,alamat,no_telp,jenis_kelamin,tipe,gaji_pegawai,created_at) VALUES ('" + field_nik.getText() + "','" + field_nama.getText() + "',"
                                + "'" + field_username.getText() + "','" + field_password.getText() + "',"
                                + "'" + field_alamat.getText() + "','" + field_nomer.getText() + "',"
                                + "'" + jenis_kelamin + "',"
                                + "'" + tipe + "','" + field_gaji.getText() + "', now())";
                        java.sql.Connection conn = (Connection) Workshop_project.foderoDB();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        // memanggil ulang table
                        tabel();
                        clear_text();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void cari_dataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_dataKeyPressed
        // TODO add your handling code here:
        String cari = cari_data.getText();

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("nik");
        dtm.addColumn("nama_pegawai");
        dtm.addColumn("username");
        dtm.addColumn("alamat");
        dtm.addColumn("no_telp");
        dtm.addColumn("jenis_kelamin");
        dtm.addColumn("gaji_pegawai");
        tabel.setModel(dtm);
        try {
            Statement statement = (Statement) Workshop_project.foderoDB().createStatement();
            ResultSet res = statement.executeQuery("select * from pegawai where nik like '%" + cari + "%' or nama_pegawai like '%" + cari + "%'");

            while (res.next()) {
                dtm.addRow(new Object[]{
                    res.getString("nik"),
                    res.getString("nama_pegawai"),
                    res.getString("username"),
                    res.getString("alamat"),
                    res.getString("no_telp"),
                    res.getString("jenis_kelamin"),
                    res.getString("gaji_pegawai")
                });
                tabel.setModel(dtm);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cari_dataKeyPressed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int i = tabel.getSelectedRow();
        TableModel tbl = tabel.getModel();
        // Mengambil value dari table
        String field1 = tbl.getValueAt(i, 0).toString();
        String field2 = tbl.getValueAt(i, 1).toString();
        String field3 = tbl.getValueAt(i, 2).toString();
        String field4 = tbl.getValueAt(i, 3).toString();
        String field5 = tbl.getValueAt(i, 4).toString();
        String field6 = tbl.getValueAt(i, 5).toString();
        String field7 = tbl.getValueAt(i, 6).toString();
        // Paste data yang telah diambil
        field_nik.setText(field1);
        field_nik.disable();
        field_nama.setText(field2);
        field_username.setText(field3);
        field_alamat.setText(field4);
        field_nomer.setText(field5);
        if (field6.equals("Laki-Laki")) {
            rdb_laki.setSelected(true);
        } else {
            rdb_perempuan.setSelected(true);
        }
        field_gaji.setText(field7);
        field_password.disable();
        field_password.setBackground(Color.black);
        btn_edit.setEnabled(true);
        btn_simpan.setEnabled(false);
    }//GEN-LAST:event_tabelMouseClicked

    private void rdb_perempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_perempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_perempuanActionPerformed

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void m_bonusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_bonusMouseClicked
        try {
            // TODO add your handling code here:
            new MasterBonus().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_bonusMouseClicked

    private void m_minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_minusMouseClicked
        try {
            // TODO add your handling code here:
            new MasterMinus().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_minusMouseClicked

    private void m_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_laporanMouseClicked
        try {
            // TODO add your handling code here:
            new LaporanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_laporanMouseClicked

    private void m_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_dashboardMouseClicked
        try {
            // TODO add your handling code here:
            new DashboardForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_dashboardMouseClicked

    private void m_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pengeluaranMouseClicked
        try {
            // TODO add your handling code here:
            new PengeluaranForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pengeluaranMouseClicked

    private void m_pendapatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pendapatanMouseClicked
        try {
            // TODO add your handling code here:
            new PendapatanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pendapatanMouseClicked

    private void m_gajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_gajiMouseClicked
        try {
            new PembayaranGaji().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_gajiMouseClicked

    private void field_gajiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_gajiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_field_gajiKeyTyped

    private void field_nomerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_nomerKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_field_nomerKeyTyped

    private void field_nikKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_nikKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_field_nikKeyTyped

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
            java.util.logging.Logger.getLogger(master_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(master_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(master_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(master_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new master_pegawai().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(master_pegawai.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTextField cari_data;
    private javax.swing.JTextField field_alamat;
    private javax.swing.JTextField field_gaji;
    private javax.swing.JTextField field_nama;
    private javax.swing.JTextField field_nik;
    private javax.swing.JTextField field_nomer;
    private javax.swing.JTextField field_password;
    private javax.swing.JTextField field_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.ButtonGroup jenis_kelamin;
    private javax.swing.JPanel m_bonus;
    private javax.swing.JPanel m_dashboard;
    private javax.swing.JPanel m_gaji;
    private javax.swing.JPanel m_laporan;
    private javax.swing.JPanel m_minus;
    private javax.swing.JPanel m_pegawai;
    private javax.swing.JPanel m_pendapatan;
    private javax.swing.JPanel m_pengeluaran;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nik;
    private javax.swing.JRadioButton rdb_laki;
    private javax.swing.JRadioButton rdb_perempuan;
    private javax.swing.JLabel saldo;
    private javax.swing.JTable tabel;
    private javax.swing.JLabel tanggal;
    // End of variables declaration//GEN-END:variables

}
