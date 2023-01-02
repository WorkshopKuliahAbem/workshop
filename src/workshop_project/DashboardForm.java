/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop_project;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import static workshop_project.master_pegawai.getDate;

/**
 *
 * @author Tole
 */
public class DashboardForm extends javax.swing.JFrame {

    Utils util = new Utils();

    ArrayList<Integer> total_pengeluaran = new ArrayList<Integer>();
    ArrayList<Integer> total_pemasukan = new ArrayList<Integer>();
    String[] bulan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};

    /**
     * Creates new form DashboardForm
     */
    public DashboardForm() throws SQLException {
        initComponents();
        tanggal.setText(getDate());
        name.setText(util.nama);
        nik.setText(util.nik);
        try {
            showPengeluaranChart();
            showPemasukanChart();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            saldo.setText("Saldo: Rp. " + Utils.getSaldo());
        } catch (SQLException e) {
            throw e;
        }
    }

    void showPengeluaranChart() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Statement st = (Statement) Utils.foderoDB().createStatement();
        for (int i = 1; i <= 12; i++) {
            int total = 0;
            String query = "Select sum(jumlah_pengeluaran) as jumlah from pengeluaran where month(tanggal_pengeluaran) = " + i;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                total = (rs.getString("jumlah") != null) ? Integer.valueOf(rs.getString("jumlah")) : 0;
                System.out.println("test");
            }
//            System.out.println(total);
            total_pengeluaran.add(total);
        }

        for (int i = 0; i < 12; i++) {
            dataset.setValue(total_pengeluaran.get(i), "Total", bulan[i]);
        }
        JFreeChart chart = ChartFactory.createBarChart("Pengeluaran", "Bulan", "Total", dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer render = (BarRenderer) plot.getRenderer();
        Color clr = new Color(204, 0, 51);
        render.setSeriesFillPaint(0, clr);

        ChartPanel cp = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(cp, BorderLayout.CENTER);
        jPanel1.validate();
    }

    void showPemasukanChart() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Statement st = (Statement) Utils.foderoDB().createStatement();
        for (int i = 1; i <= 12; i++) {
            int total = 0;
            String query = "Select sum(jumlah_pendapatan) as jumlah from pendapatan where month(tanggal_pemasukan) = " + i;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                total = (rs.getString("jumlah") != null) ? Integer.valueOf(rs.getString("jumlah")) : 0;
                System.out.println("test");
            }
//            System.out.println(total);
            total_pemasukan.add(total);
        }

        for (int i = 0; i < 12; i++) {
            dataset.setValue(total_pemasukan.get(i), "Total", bulan[i]);
        }
        JFreeChart chart = ChartFactory.createBarChart("Pendapatan", "Bulan", "Total", dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer render = (BarRenderer) plot.getRenderer();
        Color clr = new Color(204, 0, 51);
        render.setSeriesFillPaint(0, clr);

        ChartPanel cp = new ChartPanel(chart);
        jPanel2.removeAll();
        jPanel2.add(cp, BorderLayout.CENTER);
        jPanel2.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_dashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        m_pegawai = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        m_gaji = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        m_bonus = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        m_minus = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        m_pengeluaran = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        m_pendapatan = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        m_laporan = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        saldo = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        tanggal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        nik = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1);
        jPanel1.setBounds(230, 160, 520, 170);

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));
        jPanel2.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel2);
        jPanel2.setBounds(230, 330, 520, 170);

        m_dashboard.setBackground(new java.awt.Color(167, 191, 191));
        m_dashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(114, 114, 114));
        jLabel2.setText("Dashboard");

        javax.swing.GroupLayout m_dashboardLayout = new javax.swing.GroupLayout(m_dashboard);
        m_dashboard.setLayout(m_dashboardLayout);
        m_dashboardLayout.setHorizontalGroup(
            m_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_dashboardLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        m_dashboardLayout.setVerticalGroup(
            m_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_dashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        getContentPane().add(m_dashboard);
        m_dashboard.setBounds(10, 150, 170, 30);

        m_pegawai.setBackground(new java.awt.Color(244, 244, 244));
        m_pegawai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pegawaiMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(114, 114, 114));
        jLabel3.setText("Pegawai");

        javax.swing.GroupLayout m_pegawaiLayout = new javax.swing.GroupLayout(m_pegawai);
        m_pegawai.setLayout(m_pegawaiLayout);
        m_pegawaiLayout.setHorizontalGroup(
            m_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_pegawaiLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel3)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        m_pegawaiLayout.setVerticalGroup(
            m_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_pegawaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        getContentPane().add(m_pegawai);
        m_pegawai.setBounds(10, 190, 170, 30);

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
        jLabel4.setText("Gaji");

        javax.swing.GroupLayout m_gajiLayout = new javax.swing.GroupLayout(m_gaji);
        m_gaji.setLayout(m_gajiLayout);
        m_gajiLayout.setHorizontalGroup(
            m_gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_gajiLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel4)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        m_gajiLayout.setVerticalGroup(
            m_gajiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_gajiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        getContentPane().add(m_gaji);
        m_gaji.setBounds(10, 230, 170, 30);

        m_bonus.setBackground(new java.awt.Color(244, 244, 244));
        m_bonus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_bonus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_bonus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_bonusMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(114, 114, 114));
        jLabel5.setText("Bonus");

        javax.swing.GroupLayout m_bonusLayout = new javax.swing.GroupLayout(m_bonus);
        m_bonus.setLayout(m_bonusLayout);
        m_bonusLayout.setHorizontalGroup(
            m_bonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_bonusLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        m_bonusLayout.setVerticalGroup(
            m_bonusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_bonusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        getContentPane().add(m_bonus);
        m_bonus.setBounds(10, 270, 170, 30);

        m_minus.setBackground(new java.awt.Color(244, 244, 244));
        m_minus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_minus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_minusMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(114, 114, 114));
        jLabel6.setText("Minus");

        javax.swing.GroupLayout m_minusLayout = new javax.swing.GroupLayout(m_minus);
        m_minus.setLayout(m_minusLayout);
        m_minusLayout.setHorizontalGroup(
            m_minusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_minusLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        m_minusLayout.setVerticalGroup(
            m_minusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_minusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        getContentPane().add(m_minus);
        m_minus.setBounds(10, 310, 170, 30);

        m_pengeluaran.setBackground(new java.awt.Color(244, 244, 244));
        m_pengeluaran.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pengeluaranMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(114, 114, 114));
        jLabel7.setText("Pengeluaran");

        javax.swing.GroupLayout m_pengeluaranLayout = new javax.swing.GroupLayout(m_pengeluaran);
        m_pengeluaran.setLayout(m_pengeluaranLayout);
        m_pengeluaranLayout.setHorizontalGroup(
            m_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_pengeluaranLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel7)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        m_pengeluaranLayout.setVerticalGroup(
            m_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_pengeluaranLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        getContentPane().add(m_pengeluaran);
        m_pengeluaran.setBounds(10, 350, 170, 30);

        m_pendapatan.setBackground(new java.awt.Color(244, 244, 244));
        m_pendapatan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_pendapatan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_pendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_pendapatanMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(114, 114, 114));
        jLabel8.setText("Pendapatan");

        javax.swing.GroupLayout m_pendapatanLayout = new javax.swing.GroupLayout(m_pendapatan);
        m_pendapatan.setLayout(m_pendapatanLayout);
        m_pendapatanLayout.setHorizontalGroup(
            m_pendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_pendapatanLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel8)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        m_pendapatanLayout.setVerticalGroup(
            m_pendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_pendapatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(m_pendapatan);
        m_pendapatan.setBounds(10, 390, 170, 30);

        m_laporan.setBackground(new java.awt.Color(244, 244, 244));
        m_laporan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        m_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_laporanMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(114, 114, 114));
        jLabel9.setText("Laporan");

        javax.swing.GroupLayout m_laporanLayout = new javax.swing.GroupLayout(m_laporan);
        m_laporan.setLayout(m_laporanLayout);
        m_laporanLayout.setHorizontalGroup(
            m_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_laporanLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel9)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        m_laporanLayout.setVerticalGroup(
            m_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_laporanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        getContentPane().add(m_laporan);
        m_laporan.setBounds(10, 430, 170, 30);

        jPanel12.setBackground(new java.awt.Color(252, 102, 103));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("LOGOUT");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(32, 32, 32))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        getContentPane().add(jPanel12);
        jPanel12.setBounds(20, 480, 150, 40);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 120, 190, 400);

        jLabel11.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 26)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 149, 51));
        jLabel11.setText("DASHBOARD");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(400, 110, 190, 40);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        name.setForeground(new java.awt.Color(114, 114, 114));
        name.setText("Nama Pegawai");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(name)
                .addContainerGap())
        );

        getContentPane().add(jPanel16);
        jPanel16.setBounds(40, 90, 150, 30);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13);
        jPanel13.setBounds(200, 110, 580, 390);

        saldo.setFont(new java.awt.Font("Lexend", 0, 13)); // NOI18N
        saldo.setForeground(new java.awt.Color(114, 114, 114));
        saldo.setText("Saldo");
        getContentPane().add(saldo);
        saldo.setBounds(300, 80, 200, 20);

        jPanel17.setBackground(new java.awt.Color(239, 245, 245));

        tanggal.setFont(new java.awt.Font("Lexend", 0, 13)); // NOI18N
        tanggal.setForeground(new java.awt.Color(114, 114, 114));
        tanggal.setText("Tanggal");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel17);
        jPanel17.setBounds(200, 80, 80, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/workshop_project/Dashboard - Jadi_800x449.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 80, 800, 450);

        jPanel14.setBackground(new java.awt.Color(239, 245, 245));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel14);
        jPanel14.setBounds(0, 530, 800, 70);

        jPanel15.setBackground(new java.awt.Color(239, 245, 245));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel15);
        jPanel15.setBounds(0, 0, 800, 90);

        nik.setText("jLabel12");
        getContentPane().add(nik);
        nik.setBounds(600, 80, 45, 16);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pegawaiMouseClicked
        try {
            String sql = "SELECT * FROM pegawai WHERE nik = '" + nik.getText() + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.next();
            if (rs.getString("tipe").equals("karyawan")) {
                JOptionPane.showMessageDialog(null, "Anda tidak berhak mengakses menu ini !");
            } else {
                try {
                    // TODO add your handling code here:
                    new master_pegawai().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_m_pegawaiMouseClicked

    private void m_bonusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_bonusMouseClicked
        try {
            String sql = "SELECT * FROM pegawai WHERE nik = '" + nik.getText() + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.next();
            if (rs.getString("tipe").equals("karyawan")) {
                JOptionPane.showMessageDialog(null, "Anda tidak berhak mengakses menu ini !");
            } else {
                try {
                    // TODO add your handling code here:
                    new MasterBonus().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_m_bonusMouseClicked

    private void m_minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_minusMouseClicked
        try {
            String sql = "SELECT * FROM pegawai WHERE nik = '" + nik.getText() + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.next();
            if (rs.getString("tipe").equals("karyawan")) {
                JOptionPane.showMessageDialog(null, "Anda tidak berhak mengakses menu ini !");
            } else {
                try {
                    // TODO add your handling code here:
                    new MasterMinus().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_m_minusMouseClicked

    private void m_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_laporanMouseClicked
        try {
            // TODO add your handling code here:
            new LaporanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_laporanMouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void m_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pengeluaranMouseClicked
        try {
            // TODO add your handling code here:
            new PengeluaranForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pengeluaranMouseClicked

    private void m_gajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_gajiMouseClicked
        try {
            String sql = "SELECT * FROM pegawai WHERE nik = '" + nik.getText() + "'";
            Connection conn = (Connection) Workshop_project.foderoDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            rs.next();
            if (rs.getString("tipe").equals("karyawan")) {
                JOptionPane.showMessageDialog(null, "Anda tidak berhak mengakses menu ini !");
            } else {
                try {
                    // TODO add your handling code here:
                    new PembayaranGaji().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_m_gajiMouseClicked

    private void m_pendapatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_pendapatanMouseClicked
        try {
            // TODO add your handling code here:
            new PendapatanForm().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_m_pendapatanMouseClicked

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
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DashboardForm().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel m_bonus;
    private javax.swing.JPanel m_dashboard;
    private javax.swing.JPanel m_gaji;
    private javax.swing.JPanel m_laporan;
    private javax.swing.JPanel m_minus;
    public javax.swing.JPanel m_pegawai;
    private javax.swing.JPanel m_pendapatan;
    private javax.swing.JPanel m_pengeluaran;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nik;
    private javax.swing.JLabel saldo;
    private javax.swing.JLabel tanggal;
    // End of variables declaration//GEN-END:variables
}
