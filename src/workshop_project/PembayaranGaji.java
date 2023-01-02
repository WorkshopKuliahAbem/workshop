/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package workshop_project;

import com.sun.jdi.IntegerValue;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author alans
 */
public class PembayaranGaji extends javax.swing.JFrame {

    Utils util = new Utils();

    void getTabel2() {
        String queryBonus = "select gaji.id_bonus, nama_bonus, nominal_bonus, qty from gaji join mst_bonus on mst_bonus.id_bonus = gaji.id_bonus where gaji.id_pengeluaran =" + id_pengeluaran;

        String queryMinus = "select gaji.id_minus, nama_minus, nominal_minus, qty from gaji join mst_minus on mst_minus.id_minus = gaji.id_minus where gaji.id_pengeluaran =" + id_pengeluaran;

        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("nama bonus / nama minus");
        dt.addColumn("Qty");
        dt.addColumn("subTotal");
        dt.addColumn("Keterangan");
        tabel1.setModel(dt);
        try {
            Statement st = (Statement) Workshop_project.foderoDB().createStatement();
            ResultSet rs = st.executeQuery(queryBonus);
            while (rs.next()) {
                dt.addRow(new Object[]{
                    rs.getString("id_bonus"),
                    rs.getString("nama_bonus"),
                    rs.getString("qty"),
                    Integer.parseInt(rs.getString("nominal_bonus")) * Integer.valueOf(rs.getString("qty")),
                    "Bonus"
                });

                System.out.println(rs.getString("nama_bonus"));
                tabel1.setModel(dt);
            }

            Statement st1 = (Statement) Workshop_project.foderoDB().createStatement();
            ResultSet rs1 = st1.executeQuery(queryMinus);
            while (rs1.next()) {
                dt.addRow(new Object[]{
                    rs1.getString("id_minus"),
                    rs1.getString("nama_minus"),
                    rs1.getString("qty"),
                    Integer.parseInt(rs1.getString("nominal_minus")) * Integer.valueOf(rs1.getString("qty")),
                    "Minus"
                });
                tabel1.setModel(dt);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    public int id_pengeluaran;
    public int id_bonus;
    public int id_minus;
    public int id_bonusMinus;
    int nominal_bonus = 0, nominal_minus = 0, row_tabel1 = 0;

    DefaultTableModel modelMinus = new DefaultTableModel();

    private void tabelMinus() {
        modelMinus.addColumn("ID");
        modelMinus.addColumn("nama bonus / nama minus");
        modelMinus.addColumn("Qty");
        modelMinus.addColumn("SubTotal");
        modelMinus.addColumn("Keterangan");
    }

    private void tabel() {
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("No");
        model2.addColumn("ID Pengeluaran");
        model2.addColumn("Nama Pengawai");
        model2.addColumn("Gaji");
        model2.addColumn("Total Gaji");
        model2.addColumn("Tanggal");

        try {
            int no = 1;
            String sql = "SELECT pegawai.nik, pegawai.nama_pegawai, pegawai.gaji_pegawai, pengeluaran.id_pengeluaran, pengeluaran.jumlah_pengeluaran, pengeluaran.tanggal_pengeluaran FROM pengeluaran JOIN pegawai on pegawai.nik = pengeluaran.nik";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model2.addRow(new Object[]{
                    no++,
                    res.getString("id_pengeluaran"),
                    res.getString("nama_pegawai"),
                    res.getString("gaji_pegawai"),
                    res.getString("jumlah_pengeluaran"),
                    res.getString("tanggal_pengeluaran")});

            }
            tabel.setModel(model2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

    }

    
      public void tampil_nama() {
        try {
            String sql = "select * from pegawai";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                cb_namaPG.addItem(res.getString("nama_pegawai"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
    
      
    public void tampil_bonus() {
        try {
            String sql = "select * from mst_bonus";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                cb_bonus.addItem(res.getString("nama_bonus"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    public void tampil_minus() {
        try {
            String sql = "select * from mst_minus";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                cb_minus.addItem(res.getString("nama_minus"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void kosong() {
        txt_gaji.enable();
        txt_gaji.setText(null);
        txt_keteranganMinus.setText(null);
        txt_keteranganBonus.setText(null);
        id_pengeluaran = 0;
        totalGaji.setText(null);
    }

    static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dt = LocalDateTime.now();
        return String.valueOf(dtf.format(dt));
    }

    private int gajiBersih = 0;

    private void totalGaji() {
        String keteranganBonus = txt_keteranganBonus.getText();
        String keteranganMinus = txt_keteranganMinus.getText();
        String gaji = txt_gaji.getText();
        String jumlahMinus = qtyMinus.getText();
        String jumlahBonus = qtyBonus.getText();

        int gaji1 = gaji.isBlank() ? 0 : Integer.parseInt(gaji);
        int bonus1 = keteranganBonus.isBlank() ? 0 : Integer.parseInt(keteranganBonus);
        int minus1 = keteranganMinus.isBlank() ? 0 : Integer.parseInt(keteranganMinus);
        int jumlahBonus1 = jumlahBonus.isBlank() ? 0 : Integer.parseInt(jumlahBonus);
        int jumlahMinus1 = jumlahMinus.isBlank() ? 0 : Integer.parseInt(jumlahMinus);
        int gajiBersih1 = gaji1 + (nominal_bonus) - (nominal_minus);
        this.gajiBersih = gajiBersih1;

        totalGaji.setText("Rp." + nf.format(gajiBersih1));
    }

    private int nominalGaji = 0;

    /**
     * Creates new form mbayaranGaji
     */
    public PembayaranGaji() throws SQLException {
        initComponents();
        tabel();
        kosong();
        tanggal.setText(getDate());
        name.setText(util.nama);
        nik.setText(util.nik);
        btn_hapus1.setEnabled(false);
        tampil_bonus();
        tampil_minus();
        tampil_nama();
        totalGaji();
        tabelMinus();
        try {
            saldo.setText("Saldo: Rp. " + Utils.getSaldo());
        } catch (SQLException e) {
            throw e;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        nik = new javax.swing.JLabel();
        m_dashboard = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        m_pegawai = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        m_gaji = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        xxxxxxx = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_bayar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        txt_gaji = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_clear = new javax.swing.JButton();
        xxxxxxx1 = new javax.swing.JLabel();
        cb_minus = new javax.swing.JComboBox<>();
        cb_bonus = new javax.swing.JComboBox<>();
        txt_keteranganMinus = new javax.swing.JTextField();
        txt_keteranganBonus = new javax.swing.JTextField();
        qtyMinus = new javax.swing.JTextField();
        qtyBonus = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel1 = new javax.swing.JTable();
        btn_clear2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_hapus1 = new javax.swing.JButton();
        cb_namaPG = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        tanggal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        totalGaji = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        m_pengeluaran = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        m_pendapatan = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        m_bonus = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        m_laporan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        m_minus = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        m_logout = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Pembayaran Gaji - Jadi_Revisi Rezise.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Pembayaran Gaji - Jadi_Revisi Rezise.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel13.setText("jLabel13");

        jLabel20.setText("jLabel20");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(239, 245, 245));

        nik.setText("nik");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(nik)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        m_dashboard.setBackground(new java.awt.Color(244, 244, 244));
        m_dashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_dashboardMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(114, 114, 114));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Dashboard");
        m_dashboard.add(jLabel14);

        getContentPane().add(m_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 170, 30));

        m_pegawai.setBackground(new java.awt.Color(244, 244, 244));
        m_pegawai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pegawaiMouseClicked(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(114, 114, 114));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Pegawai");
        m_pegawai.add(jLabel15);

        getContentPane().add(m_pegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, 30));

        m_gaji.setBackground(new java.awt.Color(167, 191, 191));
        m_gaji.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_gaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_gajiMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(114, 114, 114));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Gaji");
        m_gaji.add(jLabel16);

        getContentPane().add(m_gaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        jLabel5.setText("Gaji Pokok :");

        xxxxxxx.setText("Keterangan Minus :");

        jLabel2.setText("Nama Pegawai :");

        btn_edit.setForeground(new java.awt.Color(0, 0, 0));
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 16, 12));
        btn_hapus.setForeground(new java.awt.Color(0, 0, 0));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_bayar.setBackground(new java.awt.Color(255, 153, 50));
        btn_bayar.setForeground(new java.awt.Color(0, 0, 0));
        btn_bayar.setText("Bayar");
        btn_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayarActionPerformed(evt);
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

        btn_clear.setBackground(new java.awt.Color(255, 153, 50));
        btn_clear.setForeground(new java.awt.Color(0, 0, 0));
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        xxxxxxx1.setText("Keterangan Bonus:");

        cb_minus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "======Keterangan Minus======" }));
        cb_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_minusActionPerformed(evt);
            }
        });
        cb_minus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cb_minusKeyPressed(evt);
            }
        });

        cb_bonus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "======Keterangan Bonus======" }));
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

        txt_keteranganMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_keteranganMinusActionPerformed(evt);
            }
        });

        qtyMinus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyMinusKeyReleased(evt);
            }
        });

        qtyBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyBonusKeyReleased(evt);
            }
        });

        tabel1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel1);

        btn_clear2.setBackground(new java.awt.Color(255, 153, 50));
        btn_clear2.setForeground(new java.awt.Color(0, 0, 0));
        btn_clear2.setText("Clear");
        btn_clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear2ActionPerformed(evt);
            }
        });

        jButton1.setText("Tambah Minus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Tambah Bonus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_hapus1.setBackground(new java.awt.Color(255, 16, 12));
        btn_hapus1.setForeground(new java.awt.Color(0, 0, 0));
        btn_hapus1.setText("Hapus");
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });

        cb_namaPG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "======Nama Pegawai======" }));
        cb_namaPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaPGActionPerformed(evt);
            }
        });
        cb_namaPG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cb_namaPGKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(208, 208, 208)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(102, 102, 102))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(txt_keteranganMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_keteranganBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(cb_namaPG, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xxxxxxx)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(cb_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtyMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_gaji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xxxxxxx1))
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cb_bonus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qtyBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_namaPG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xxxxxxx)
                    .addComponent(xxxxxxx1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_bonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_keteranganBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_keteranganMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bayar)
                    .addComponent(btn_hapus)
                    .addComponent(btn_edit)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear2)
                    .addComponent(btn_hapus1))
                .addGap(88, 88, 88))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, 530));

        jPanel13.setBackground(new java.awt.Color(239, 245, 245));

        tanggal.setForeground(new java.awt.Color(114, 114, 114));
        tanggal.setText("Tanggal");

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel12.setText("Total Gaji :");

        totalGaji.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        totalGaji.setText("GAJI");

        saldo.setForeground(new java.awt.Color(114, 114, 114));
        saldo.setText("Saldo");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(totalGaji))
                            .addComponent(tanggal)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(saldo)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        m_pengeluaran.setBackground(new java.awt.Color(244, 244, 244));
        m_pengeluaran.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pengeluaranMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(114, 114, 114));
        jLabel17.setText("Pengeluaran");
        m_pengeluaran.add(jLabel17);

        getContentPane().add(m_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 30));

        m_pendapatan.setBackground(new java.awt.Color(244, 244, 244));
        m_pendapatan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pendapatanMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(114, 114, 114));
        jLabel18.setText("Pendapatan");
        m_pendapatan.add(jLabel18);

        getContentPane().add(m_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 170, 30));

        m_bonus.setBackground(new java.awt.Color(244, 244, 244));
        m_bonus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_bonus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_bonusMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(114, 114, 114));
        jLabel7.setText("Bonus");
        m_bonus.add(jLabel7);

        getContentPane().add(m_bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 170, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        name.setForeground(new java.awt.Color(114, 114, 114));
        name.setText("Nama Pegawai");
        jPanel11.add(name);

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 84, 100, -1));

        m_laporan.setBackground(new java.awt.Color(244, 244, 244));
        m_laporan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_laporanMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(114, 114, 114));
        jLabel10.setText("Laporan");
        m_laporan.add(jLabel10);

        getContentPane().add(m_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 170, 30));

        m_minus.setBackground(new java.awt.Color(244, 244, 244));
        m_minus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_minusMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(114, 114, 114));
        jLabel11.setText("Minus");
        m_minus.add(jLabel11);

        getContentPane().add(m_minus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 170, -1));

        jPanel14.setBackground(new java.awt.Color(239, 245, 245));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, -1));

        m_logout.setBackground(new java.awt.Color(252, 102, 103));
        m_logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_logoutMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LOGOUT");

        javax.swing.GroupLayout m_logoutLayout = new javax.swing.GroupLayout(m_logout);
        m_logout.setLayout(m_logoutLayout);
        m_logoutLayout.setHorizontalGroup(
            m_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_logoutLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(33, 33, 33))
        );
        m_logoutLayout.setVerticalGroup(
            m_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_logoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(m_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/workshop_project/Master Pegawai - Jadi_Revisi.jpg"))); // NOI18N
        jLabel19.setName("gambar dasar"); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_gajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_gajiMouseClicked

    }//GEN-LAST:event_m_gajiMouseClicked

    private void qtyBonusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyBonusKeyReleased
        // TODO add your handling code here:
        totalGaji();
    }//GEN-LAST:event_qtyBonusKeyReleased

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:

        try {

            String sql = "DELETE  gaji, pengeluaran FROM pengeluaran inner join gaji where pengeluaran.id_pengeluaran = gaji.id_pengeluaran and gaji.id_pengeluaran = '" + id_pengeluaran + "'";
            String sql2 = "DELETE pengeluaran, gaji FROM pengeluaran inner join gaji where pengeluaran.id_pengeluaran = gaji.id_pengeluaran and pengeluaran.id_pengeluaran = '" + id_pengeluaran + "'";

            Connection conn = (Connection) Workshop_project.foderoDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst.execute();
            pst2.execute();

            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tabel();
        kosong();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void cb_minusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_minusKeyPressed

    }//GEN-LAST:event_cb_minusKeyPressed

    private void cb_minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_minusActionPerformed
        try {
            //menginisialkan minus dari comboBox
            String minus = String.valueOf(cb_minus.getSelectedItem());
            //mencari data pada tabel master minus yang dipilih dari nama_minus yang berada pada tabel comboBox
            String sql = "select * from mst_minus WHERE nama_minus = '" + minus + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            //yang dipanggil hanya nominal minus saja
            if (res.next()) {
                txt_keteranganMinus.setText(res.getString("nominal_minus"));
            }
            txt_keteranganMinus.disable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        //memanggil method arimatika dari gaji bersih(gaji pokok + bonus - minus)
        totalGaji();
    }//GEN-LAST:event_cb_minusActionPerformed

    private void cb_bonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_bonusActionPerformed

    }//GEN-LAST:event_cb_bonusActionPerformed

    private void cb_bonusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_bonusItemStateChanged
        try {
            //menginisialkan bonus pada tabel comboBox
            String nama = String.valueOf(cb_bonus.getSelectedItem());
            //menceri data pada tabel master minus yang dipilih dari nama_minus yang berada pada comboBox
            String sql = "select * from mst_bonus WHERE nama_bonus = '" + nama + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                txt_keteranganBonus.setText(res.getString("nominal_bonus"));
            }
            txt_keteranganBonus.disable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        //memanggil method arimatika dari gaji bersih(gaji pokok + bonus - minus)
        totalGaji();
    }//GEN-LAST:event_cb_bonusItemStateChanged

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        kosong();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayarActionPerformed
        // TODO add your handling code here:
        try {
            String sql1 = "insert into pengeluaran (nik, keterangan_pengeluaran, jenis_pengeluaran, jumlah_pengeluaran, tanggal_pengeluaran) values('"
                    + txt_nik.getText() + "', 'pengeluaran gaji', 'gaji', " + gajiBersih + ", now())";
            Connection conn = (Connection) Workshop_project.foderoDB();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.execute();

            String sql2 = "select * from pengeluaran order by id_pengeluaran desc limit 1";
            Statement stm2 = conn.createStatement();
            ResultSet res = stm2.executeQuery(sql2);
            res.next();
            id_pengeluaran = res.getInt("id_pengeluaran");

            DefaultTableModel tbl = (DefaultTableModel) tabel1.getModel();
            for (int x = 0; x < tabel1.getRowCount(); x++) {
                String queryGaji = "";
                if (tbl.getValueAt(x, 4) == "Bonus") {
                    queryGaji = "insert into gaji(id_gaji, id_pengeluaran, id_bonus, id_minus, qty) values(null, " + id_pengeluaran + ", " + tbl.getValueAt(x, 0) + ", null, " + tbl.getValueAt(x, 2) + ")";
                } else if (tbl.getValueAt(x, 4) == "Minus") {
                    queryGaji = "insert into gaji(id_gaji, id_pengeluaran, id_bonus, id_minus, qty) values(null, " + id_pengeluaran + ", null, " + tbl.getValueAt(x, 0) + ", " + tbl.getValueAt(x, 2) + ")";
                }
                try {
                    PreparedStatement pst = conn.prepareStatement(queryGaji);
                    pst.execute();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "query 2" + ex.getMessage());

                }
            }

            // memanggil ulang table
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "query 1" + e.getMessage());
        }
        tabel();
    }//GEN-LAST:event_btn_bayarActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:

        int i = tabel.getSelectedRow();
        TableModel tbl = tabel.getModel();
        // Mengambil value dari table

        String field1 = tbl.getValueAt(i, 1).toString();
        String field2 = tbl.getValueAt(i, 2).toString();
        String field3 = tbl.getValueAt(i, 3).toString();

        //membuat aritmatika untuk gaji bersih
//        int bonus = Integer.parseInt(field5);
//        int minus = Integer.parseInt(field3);
//        int gaji = Integer.parseInt(field7);
//        int gajiBersih = gaji + bonus - minus;
//
//        //membuat angka terdapat format rupiah dan terdapat titik
//        txt_keteranganBonus.setText(nf.format(bonus));
//        txt_keteranganMinus.setText(nf.format(minus));
//        txt_gaji.setText(nf.format(gaji));
//        totalGaji.setText("Rp. "+nf.format(gajiBersih));
        //menginisialkan id_pengeluran
        id_pengeluaran = Integer.valueOf(tbl.getValueAt(i, 1).toString());

        // Paste data yang telah diambil
//        txt_nama.setText(field2);
//        txt_nama.disable();
//        cb_minus.setSelectedItem(field4);
//        cb_bonus.setSelectedItem(field6);
        txt_gaji.disable();
        txt_gaji.setText(field3);

        getTabel2();
    }//GEN-LAST:event_tabelMouseClicked

    private void qtyMinusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyMinusKeyReleased
        // TODO add your handling code here:
        totalGaji();
    }//GEN-LAST:event_qtyMinusKeyReleased

    private void txt_nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nikKeyPressed
        // TODO add your handling code here:
        //menginisialkan nik dari textfield
//        String nik = txt_nik.getText();
//
//        try {
//            //mencari data dari tabel pegawai yang dipilih dari nik yang berada pada textField
//            String sql = "select * from pegawai where nik like '%" + nik + "%' limit 1";
//            Connection conn = (Connection) Workshop_project.foderoDB();
//            Statement stm = conn.createStatement();
//            ResultSet res = stm.executeQuery(sql);
//
//            if (res.next()) {
//                //data yang diambil hanya nama dan gaji
////                txt_nama.setText(res.getString("nama_pegawai"));
//                txt_gaji.setText(res.getString("gaji_pegawai"));
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//        //memanggil method aritmatika gaji bersih(gaji pokok + bonus - minus)
//        totalGaji();
    }//GEN-LAST:event_txt_nikKeyPressed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed

    }//GEN-LAST:event_txt_nikActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
            btn_hapus1.setEnabled(true);



    }//GEN-LAST:event_btn_editActionPerformed

    private void txt_keteranganMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_keteranganMinusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_keteranganMinusActionPerformed

    private void tabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel1MouseClicked
        // TODO add your handling code here:
        row_tabel1 = tabel1.getSelectedRow();
        TableModel tbl = tabel1.getModel();
        int i = tabel1.getSelectedRow();
        int subTotal = 0;
        int jumlah = 0;
        
        if (id_pengeluaran != 0) {
            if (tbl.getValueAt(i, 4) == "Bonus") {
                String getNominal = "select qty, nominal_bonus from gaji join mst_bonus on mst_bonus.id_bonus = gaji.id_bonus where id_pengeluaran = " + id_pengeluaran + " and id_bonus = " + tbl.getValueAt(i, 0) + "";
                try {
                    Statement stmt = (Statement) util.foderoDB().createStatement();
                    ResultSet rst = stmt.executeQuery(getNominal);
                    if (rst.next()) {
                        String getTotal = "select jumlah_pengeluaran from pengeluaran where id_pengeluaran = " + id_pengeluaran;
                        try {
                            Statement stmnt = (Statement) util.foderoDB().createStatement();
                            ResultSet rslt = stmnt.executeQuery(getTotal);
                            if (rslt.next()) {
                                jumlah = Integer.parseInt(rslt.getString("jumlah_pengeluaran"));
                            }
                        } catch(Exception ex){
                            
                        }
                        int t = jumlah - Integer.parseInt(tbl.getValueAt(i, 3).toString());
                        System.out.println(t);
                        JOptionPane.showMessageDialog(rootPane, t);
                        String updateGaji = "update pengeluaran set jumlah_pengeluaran = "+ t + " where id_pengeluaran = "+id_pengeluaran;
                        try{
                            Utils.execQuery(updateGaji);
                        } catch(Exception ex){
                            
                        }
                    }
                } catch(Exception e){
                    
                }
                
                String queryHapus = "delete from gaji where id_pengeluaran = " + id_pengeluaran + " and id_bonus = " + Integer.parseInt(tbl.getValueAt(i, 0).toString());
                try{
//                    Utils.execQuery(queryHapus);
                } catch(Exception e){
                    
                }
            } else {
                String getNominal = "select qty, nominal_minus from gaji join mst_minus on mst_minus.id_minus = gaji.id_minus where id_pengeluaran = " + id_pengeluaran + " and id_minus = " + tbl.getValueAt(i, 0) + "";
                try {
                    Statement stmt = (Statement) util.foderoDB().createStatement();
                    ResultSet rst = stmt.executeQuery(getNominal);
                    if (rst.next()) {
                        String getTotal = "select jumlah_pengeluaran from pengeluaran where id_pengeluaran = " + id_pengeluaran;
                        try {
                            Statement stmnt = (Statement) util.foderoDB().createStatement();
                            ResultSet rslt = stmnt.executeQuery(getTotal);
                            if (rslt.next()) {
                                jumlah = Integer.parseInt(rslt.getString("jumlah_pengeluaran"));
                            }
                        } catch(Exception ex){
                            
                        }
                        int t = jumlah + Integer.parseInt(tbl.getValueAt(i, 3).toString());
                        JOptionPane.showMessageDialog(rootPane, t);
                        
                        String updateGaji = "update pengeluaran set jumlah_pengeluaran = "+ t + " where id_pengeluaran = "+id_pengeluaran;
                        try{
                            Utils.execQuery(updateGaji);
                        } catch(Exception ex){
                            
                        }
                    }
                } catch(Exception e){
                    
                }
                
                String queryHapus = "delete from gaji where id_pengeluaran = " + id_pengeluaran + " and id_minus = " + Integer.parseInt(tbl.getValueAt(i, 0).toString());
                try{
//                    Utils.execQuery(queryHapus);
                    
                } catch(Exception e){
                    
                }
            }
        }


        String field1 = tbl.getValueAt(i, 1).toString();
        String field2 = tbl.getValueAt(i, 2).toString();

        if (tbl.getValueAt(i, 4) == "Bonus") {
            cb_bonus.setSelectedItem(field1);
            qtyBonus.setText(field2);
        } else {
            cb_minus.setSelectedItem(field1);
            qtyMinus.setText(field2);
        }

    }//GEN-LAST:event_tabel1MouseClicked

    private void btn_clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clear2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int id = 0;
        try {
            Connection conn = (Connection) Workshop_project.foderoDB();
            String sql2 = "select * from mst_minus where nama_minus = '" + cb_minus.getSelectedItem() + "'";
            Statement stm2 = conn.createStatement();
            ResultSet res = stm2.executeQuery(sql2);
            res.next();
            id = res.getInt("id_minus");
            int nominal = res.getInt("nominal_minus");
            nominal_minus = nominal * Integer.valueOf(qtyMinus.getText().toString());
            System.out.println(id);
            modelMinus.addRow(new Object[]{
                res.getString("id_minus"),
                cb_minus.getSelectedItem(),
                qtyMinus.getText(),
                res.getInt("nominal_minus") * Integer.valueOf(qtyMinus.getText()),
                "Minus"
            });
            tabel1.setModel(modelMinus);
            totalGaji();
        } catch (SQLException ex) {
            try {
                throw ex;
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int id = 0;
        try {
            Connection conn = (Connection) Workshop_project.foderoDB();
            String sql2 = "select * from mst_bonus where nama_bonus = '" + cb_bonus.getSelectedItem() + "'";
            Statement stm2 = conn.createStatement();
            ResultSet res = stm2.executeQuery(sql2);
            res.next();
            id = res.getInt("id_bonus");
            int nominal = res.getInt("nominal_bonus");
            nominal_bonus = nominal * Integer.valueOf(qtyBonus.getText().toString());
            System.out.println(id);
            modelMinus.addRow(new Object[]{
                res.getString("id_bonus"),
                cb_bonus.getSelectedItem(),
                qtyBonus.getText(),
                res.getInt("nominal_bonus") * Integer.valueOf(qtyBonus.getText()),
                "Bonus"
            });
            tabel1.setModel(modelMinus);
            totalGaji();
        } catch (SQLException ex) {
            try {
                throw ex;
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dt = (DefaultTableModel) tabel1.getModel();
        int nominal_tabel = Integer.valueOf(dt.getValueAt(row_tabel1, 3).toString());
        
        row_tabel1 = tabel1.getSelectedRow();
        TableModel tbl = tabel1.getModel();
        int i = tabel1.getSelectedRow();
        int subTotal = 0;
        int jumlah = 0;
        
        if (id_pengeluaran != 0) {
            if (tbl.getValueAt(i, 4) == "Bonus") {
                String getTotal = "select jumlah_pengeluaran from pengeluaran where id_pengeluaran = " + id_pengeluaran;
                try {
                    Statement stmnt = (Statement) util.foderoDB().createStatement();
                    ResultSet rslt = stmnt.executeQuery(getTotal);
                    if (rslt.next()) {
                        jumlah = Integer.parseInt(rslt.getString("jumlah_pengeluaran"));
                        int t = jumlah - Integer.parseInt(tbl.getValueAt(i, 3).toString());
                        System.out.println(t);
//                        JOptionPane.showMessageDialog(rootPane, t);
                        String updateGaji = "update pengeluaran set jumlah_pengeluaran = "+ t + " where id_pengeluaran = "+id_pengeluaran;
                        try{
                            Utils.execQuery(updateGaji);

                            String queryHapus = "delete from gaji where id_pengeluaran = " + id_pengeluaran + " and id_bonus = " + Integer.parseInt(tbl.getValueAt(i, 0).toString());
                            try{
                                Utils.execQuery(queryHapus);
                                tabel();
                            } catch(Exception e){
                                JOptionPane.showMessageDialog(rootPane, "1" + e);
                            }
                        } catch(Exception ex){
                            JOptionPane.showMessageDialog(rootPane, "2" + ex);
                        }
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "3" + ex);
                }
            } else {
                String getTotal = "select jumlah_pengeluaran from pengeluaran where id_pengeluaran = " + id_pengeluaran;
                try {
                    Statement stmnt = (Statement) util.foderoDB().createStatement();
                    ResultSet rslt = stmnt.executeQuery(getTotal);
                    if (rslt.next()) {
                        jumlah = Integer.parseInt(rslt.getString("jumlah_pengeluaran"));
                        int t = jumlah + Integer.parseInt(tbl.getValueAt(i, 3).toString());
//                        JOptionPane.showMessageDialog(rootPane, t);

                        String updateGaji = "update pengeluaran set jumlah_pengeluaran = "+ t + " where id_pengeluaran = "+id_pengeluaran;
                        try{
                            Utils.execQuery(updateGaji);

                            String queryHapus = "delete from gaji where id_pengeluaran = " + id_pengeluaran + " and id_minus = " + Integer.parseInt(tbl.getValueAt(i, 0).toString());
                            try{
                                Utils.execQuery(queryHapus);
                                tabel();
                            } catch(Exception e){
                                JOptionPane.showMessageDialog(rootPane, "1" + e);
                            }
                        } catch(Exception ex){
                            JOptionPane.showMessageDialog(rootPane, "2" + ex);
                        }
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "3" + ex);
                }
            }
        }
        if (dt.getValueAt(row_tabel1, 4) == "Bonus") {
            nominal_bonus -= nominal_tabel;
            totalGaji();
        } else {
            nominal_minus -= nominal_tabel;
            totalGaji();
        }
        btn_hapus1.setEnabled(false);
        dt.removeRow(row_tabel1);
//        tabel1.setModel(dt);
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void m_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_dashboardMouseClicked
        try {
            // TODO add your handling code here:
            new DashboardForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_dashboardMouseClicked

    private void m_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pegawaiMouseClicked
        try {
            new master_pegawai().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pegawaiMouseClicked

    private void m_bonusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_bonusMouseClicked
        try {
            // TODO add your handling code here:
            new MasterBonus().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_bonusMouseClicked

    private void m_minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_minusMouseClicked
        try {
            // TODO add your handling code here:
            new MasterMinus().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_minusMouseClicked

    private void m_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pengeluaranMouseClicked
        try {
            // TODO add your handling code here:
            new PengeluaranForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pengeluaranMouseClicked

    private void m_pendapatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pendapatanMouseClicked
        try {
            // TODO add your handling code here:
            new PendapatanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pendapatanMouseClicked

    private void m_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_laporanMouseClicked
        try {
            // TODO add your handling code here:
            new LaporanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_laporanMouseClicked

    private void m_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_logoutMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_m_logoutMouseClicked

    private void cb_namaPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaPGActionPerformed
        // TODO add your handling code here:
         try {
            //menginisialkan minus dari comboBox
            String nama = String.valueOf(cb_namaPG.getSelectedItem());
            //mencari data pada tabel master minus yang dipilih dari nama_minus yang berada pada tabel comboBox
            String sql = "select * from pegawai WHERE nama_pegawai = '" + nama + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            //yang dipanggil hanya nominal minus saja
            if (res.next()) {
                txt_gaji.setText(res.getString("gaji_Pegawai"));
            }
            txt_gaji.disable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        //memanggil method arimatika dari gaji bersih(gaji pokok + bonus - minus)
        totalGaji();
    }//GEN-LAST:event_cb_namaPGActionPerformed

    private void cb_namaPGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_namaPGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_namaPGKeyPressed

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


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PembayaranGaji().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranGaji.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bayar;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear2;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JComboBox<String> cb_bonus;
    private javax.swing.JComboBox<String> cb_minus;
    private javax.swing.JComboBox<String> cb_namaPG;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel m_bonus;
    private javax.swing.JPanel m_dashboard;
    private javax.swing.JPanel m_gaji;
    private javax.swing.JPanel m_laporan;
    private javax.swing.JPanel m_logout;
    private javax.swing.JPanel m_minus;
    private javax.swing.JPanel m_pegawai;
    private javax.swing.JPanel m_pendapatan;
    private javax.swing.JPanel m_pengeluaran;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nik;
    private javax.swing.JTextField qtyBonus;
    private javax.swing.JTextField qtyMinus;
    private javax.swing.JLabel saldo;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabel1;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel totalGaji;
    private javax.swing.JTextField txt_gaji;
    private javax.swing.JTextField txt_keteranganBonus;
    private javax.swing.JTextField txt_keteranganMinus;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JLabel xxxxxxx;
    private javax.swing.JLabel xxxxxxx1;
    // End of variables declaration//GEN-END:variables
}
