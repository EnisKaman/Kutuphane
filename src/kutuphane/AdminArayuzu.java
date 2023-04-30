/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kutuphane;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.chart.ModelPieChart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enis
 */
public class AdminArayuzu extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String email = null;
    String sifre = null;
    int tema = 0;
    String dosyaadi = null;
    FileInputStream fis;

    public void KitaplarTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT kitap_adi,kitap_kodu,yazar_adsoyad,yayin_evi,kitap_turu,okuma_sayisi FROM public.kitaplik;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Kitap Adı");
            model.addColumn("Yazar");
            model.addColumn("Yayın Evi");
            model.addColumn("Kitap Türü");
            model.addColumn("Okuma Sayısı");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("kitap_kodu");
                row[1] = rs.getString("kitap_adi");
                row[2] = rs.getString("yazar_adsoyad");
                row[3] = rs.getString("yayin_evi");
                row[4] = rs.getString("kitap_turu");
                row[5] = rs.getInt("okuma_sayisi");
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblKitaplar.setDefaultRenderer(Object.class, centerRenderer);
            tblKitaplar.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model2 = new DefaultTableModel();
            model2.addColumn("Randevu İsteyen Kişi");
            model2.addColumn("Randevu Konusu");
            model2.addColumn("Randevu Tarihi");
            model2.addColumn("Randevu Durumu");
            model2.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_isteyen_adsoyad");
                row[1] = rs.getString("randevu_konusu");
                String randevutaleptarih = rs.getString("randevu_talep_tarihi");
                LocalDateTime dateTime = LocalDateTime.parse(randevutaleptarih, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String yeniDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[2] = yeniDateTime;
                row[3] = rs.getString("randevu_durum");
                String originalDateTime = rs.getString("randevu_olusturma_tarih");
                //LocalDateTime dateTime2 = LocalDateTime.parse(originalDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                //String newDateTime = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[4] = originalDateTime;
                model2.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblRandevular.setDefaultRenderer(Object.class, centerRenderer);
            tblRandevular.setModel(model2);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void PastaGrafik(){
        try {
            pieChart1.clearData();
            String sql = "SELECT Distinct islem, COUNT(islem) FROM public.kullanici_log group by islem;;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            int index = 0;
            String islem = "";
            while (rs.next()) {
                islem = rs.getString(1);
                int cikis = rs.getInt(2);
                pieChart1.addData(new ModelPieChart(islem, cikis,Renkler(index++)));
                //lblRenk1.setBackground(Renkler(index++));
                //lblRenk1Adı.setText(islem);
            }
            
            pnlRenk1.setBackground(Renkler(0));
            lblRenk1Adı.setText("Çıkış");
            pnlRenk2.setBackground(Renkler(1));
            lblRenk2Adı.setText("Giriş");
            pnlRenk3.setBackground(Renkler(2));
            lblRenk3Adı.setText("Giriş Yapmadan Çıkış");
            pnlRenk4.setBackground(Renkler(3));
            lblRenk4Adı.setText("Kullanıcı Adı ya da Şifre Yanlış");
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Color Renkler(int index){
        Color[] color = new Color[]{new Color(255,102,102),new Color(29,184,80),new Color(206,215,33),new Color(55,55,227)};
        return color[index];
    }

    public AdminArayuzu() {
        initComponents();
    }

    public AdminArayuzu(String email, String sifre, int tema) {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;

        initComponents();
        KitaplarTabloVerileri();
        RandevuTabloVerileri();
        PastaGrafik();
        pnlSettings.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSettings = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlKitapEkleme = new javax.swing.JPanel();
        lblYazarAdSoyad = new javax.swing.JLabel();
        txtYazarAdSoyad = new javax.swing.JTextField();
        lblKitapKodu = new javax.swing.JLabel();
        lblKitapAdi = new javax.swing.JLabel();
        lblYayinEvi = new javax.swing.JLabel();
        lblKitapTuru = new javax.swing.JLabel();
        txtKitapAdi = new javax.swing.JTextField();
        txtKitapKodu = new javax.swing.JTextField();
        txtYayinEvi = new javax.swing.JTextField();
        cbKitapTuru = new javax.swing.JComboBox<>();
        lblKitapResmi = new javax.swing.JLabel();
        btnGoruntuSec = new javax.swing.JButton();
        pnlResim = new javax.swing.JPanel();
        lblResim = new javax.swing.JLabel();
        btnKaydet = new javax.swing.JButton();
        pnlKitapOnaylama = new javax.swing.JPanel();
        pnlKitaplar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKitaplar = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        btnKitapAra = new javax.swing.JButton();
        pnlUyeIslemleri = new javax.swing.JPanel();
        pnlRandevular = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRandevular = new javax.swing.JTable();
        btnKabul = new javax.swing.JButton();
        btnRet = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSebep = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtRandevuIsteyenKisi = new javax.swing.JTextField();
        txtRandevuTarih = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtKonu = new javax.swing.JTextArea();
        pnlDuyurular = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JPanel();
        pieChart1 = new javaswingdev.chart.PieChart();
        lblRenk4Adı = new javax.swing.JLabel();
        pnlRenk1 = new javax.swing.JPanel();
        pnlRenk2 = new javax.swing.JPanel();
        lblRenk1Adı = new javax.swing.JLabel();
        pnlRenk3 = new javax.swing.JPanel();
        pnlRenk4 = new javax.swing.JPanel();
        lblRenk2Adı = new javax.swing.JLabel();
        lblRenk3Adı = new javax.swing.JLabel();
        pnlSettings = new javax.swing.JPanel();
        pnlSettingsKapat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRenk = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSettingsKaydet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/settings_64px.png"))); // NOI18N
        lblSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingsMouseClicked(evt);
            }
        });
        getContentPane().add(lblSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        lblYazarAdSoyad.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYazarAdSoyad.setText("Yazar Ad Soyad");

        lblKitapKodu.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapKodu.setText("Kitap Kodu");

        lblKitapAdi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapAdi.setText("Kitap Adı");

        lblYayinEvi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYayinEvi.setText("Yayın Evi");

        lblKitapTuru.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru.setText("Kitap Türü");

        cbKitapTuru.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hikaye", "Roman", "Masal", "Biyografi", "Anı", "Gezi Yazısı" }));

        lblKitapResmi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapResmi.setText("Kitap Kapağı");

        btnGoruntuSec.setText("Görüntü Seçiniz");
        btnGoruntuSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoruntuSecActionPerformed(evt);
            }
        });

        pnlResim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlResim.add(lblResim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 260));

        btnKaydet.setText("Kaydet");
        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaydetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKitapEklemeLayout = new javax.swing.GroupLayout(pnlKitapEkleme);
        pnlKitapEkleme.setLayout(pnlKitapEklemeLayout);
        pnlKitapEklemeLayout.setHorizontalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYazarAdSoyad)
                            .addComponent(lblKitapAdi)
                            .addComponent(lblKitapKodu)
                            .addComponent(lblYayinEvi)
                            .addComponent(lblKitapTuru)
                            .addComponent(lblKitapResmi))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKitapEklemeLayout.createSequentialGroup()
                            .addComponent(btnGoruntuSec)
                            .addGap(54, 54, 54)
                            .addComponent(pnlResim, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                        .addComponent(txtKitapAdi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtKitapKodu, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtYayinEvi, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlKitapEklemeLayout.setVerticalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lblYazarAdSoyad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKitapAdi)
                    .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKitapKodu)
                    .addComponent(txtKitapKodu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYayinEvi)
                    .addComponent(txtYayinEvi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKitapTuru)
                    .addComponent(cbKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResim, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKitapResmi)
                            .addComponent(btnGoruntuSec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblYazarAdSoyad.getAccessibleContext().setAccessibleName("lblYazarAdSoyad");

        jTabbedPane1.addTab("Kitap Ekleme", pnlKitapEkleme);

        javax.swing.GroupLayout pnlKitapOnaylamaLayout = new javax.swing.GroupLayout(pnlKitapOnaylama);
        pnlKitapOnaylama.setLayout(pnlKitapOnaylamaLayout);
        pnlKitapOnaylamaLayout.setHorizontalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        pnlKitapOnaylamaLayout.setVerticalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kitap Onaylama", pnlKitapOnaylama);

        tblKitaplar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kitap Kodu", "Kitap Adı", "Yazar", "Yayın Evi", "Türü", "Okunma Sayısı"
            }
        ));
        tblKitaplar.setToolTipText("");
        jScrollPane2.setViewportView(tblKitaplar);

        btnKitapAra.setText("Ara");

        javax.swing.GroupLayout pnlKitaplarLayout = new javax.swing.GroupLayout(pnlKitaplar);
        pnlKitaplar.setLayout(pnlKitaplarLayout);
        pnlKitaplarLayout.setHorizontalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitaplarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKitapAra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlKitaplarLayout.setVerticalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKitapAra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kitaplar", pnlKitaplar);

        javax.swing.GroupLayout pnlUyeIslemleriLayout = new javax.swing.GroupLayout(pnlUyeIslemleri);
        pnlUyeIslemleri.setLayout(pnlUyeIslemleriLayout);
        pnlUyeIslemleriLayout.setHorizontalGroup(
            pnlUyeIslemleriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        pnlUyeIslemleriLayout.setVerticalGroup(
            pnlUyeIslemleriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Üye İşlemleri", pnlUyeIslemleri);

        tblRandevular.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRandevular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRandevularMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblRandevular);

        btnKabul.setText("Kabul Et");
        btnKabul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabulActionPerformed(evt);
            }
        });

        btnRet.setText("Reddet");
        btnRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetActionPerformed(evt);
            }
        });

        txtSebep.setColumns(20);
        txtSebep.setRows(5);
        jScrollPane4.setViewportView(txtSebep);

        jLabel2.setText("Sebep");

        txtKonu.setColumns(20);
        txtKonu.setRows(5);
        jScrollPane5.setViewportView(txtKonu);

        javax.swing.GroupLayout pnlRandevularLayout = new javax.swing.GroupLayout(pnlRandevular);
        pnlRandevular.setLayout(pnlRandevularLayout);
        pnlRandevularLayout.setHorizontalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(pnlRandevularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRandevuIsteyenKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKabul))
                .addGap(18, 18, 18)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRandevularLayout.createSequentialGroup()
                        .addComponent(btnRet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(txtRandevuTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        pnlRandevularLayout.setVerticalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevularLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRandevuTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRandevuIsteyenKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRandevularLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevularLayout.createSequentialGroup()
                        .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(btnRet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKabul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );

        jTabbedPane1.addTab("Randevular", pnlRandevular);

        javax.swing.GroupLayout pnlDuyurularLayout = new javax.swing.GroupLayout(pnlDuyurular);
        pnlDuyurular.setLayout(pnlDuyurularLayout);
        pnlDuyurularLayout.setHorizontalGroup(
            pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        pnlDuyurularLayout.setVerticalGroup(
            pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Duyurular", pnlDuyurular);

        lblRenk4Adı.setText("2");
        pieChart1.add(lblRenk4Adı);
        lblRenk4Adı.setBounds(620, 120, 170, 20);

        javax.swing.GroupLayout pnlRenk1Layout = new javax.swing.GroupLayout(pnlRenk1);
        pnlRenk1.setLayout(pnlRenk1Layout);
        pnlRenk1Layout.setHorizontalGroup(
            pnlRenk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk1Layout.setVerticalGroup(
            pnlRenk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pieChart1.add(pnlRenk1);
        pnlRenk1.setBounds(590, 30, 20, 20);

        javax.swing.GroupLayout pnlRenk2Layout = new javax.swing.GroupLayout(pnlRenk2);
        pnlRenk2.setLayout(pnlRenk2Layout);
        pnlRenk2Layout.setHorizontalGroup(
            pnlRenk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk2Layout.setVerticalGroup(
            pnlRenk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pieChart1.add(pnlRenk2);
        pnlRenk2.setBounds(590, 60, 20, 20);

        lblRenk1Adı.setText("2");
        pieChart1.add(lblRenk1Adı);
        lblRenk1Adı.setBounds(620, 30, 140, 20);

        javax.swing.GroupLayout pnlRenk3Layout = new javax.swing.GroupLayout(pnlRenk3);
        pnlRenk3.setLayout(pnlRenk3Layout);
        pnlRenk3Layout.setHorizontalGroup(
            pnlRenk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk3Layout.setVerticalGroup(
            pnlRenk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pieChart1.add(pnlRenk3);
        pnlRenk3.setBounds(590, 90, 20, 20);

        javax.swing.GroupLayout pnlRenk4Layout = new javax.swing.GroupLayout(pnlRenk4);
        pnlRenk4.setLayout(pnlRenk4Layout);
        pnlRenk4Layout.setHorizontalGroup(
            pnlRenk4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk4Layout.setVerticalGroup(
            pnlRenk4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pieChart1.add(pnlRenk4);
        pnlRenk4.setBounds(590, 120, 20, 20);

        lblRenk2Adı.setText("2");
        pieChart1.add(lblRenk2Adı);
        lblRenk2Adı.setBounds(620, 60, 140, 20);

        lblRenk3Adı.setText("2");
        pieChart1.add(lblRenk3Adı);
        lblRenk3Adı.setBounds(620, 90, 140, 20);

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dashboard", pnlDashboard);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 788, 540));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Kütüphaneci Paneli");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pnlSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSettingsKapat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/close_64px.png"))); // NOI18N
        pnlSettingsKapat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlSettingsKapat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSettingsKapatMouseClicked(evt);
            }
        });
        pnlSettings.add(pnlSettingsKapat, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, -1, -1));

        lstRenk.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Aydınlık", "Karanlık", "macOS Aydınlık", "macOS Karanlık", "Arc Turuncu Aydınlık", "Arc Turuncu Karanlık", "Sönük Aydınlık", "Sönük Karanlık", "Cyan Aydınlık", "Carbon", "GitHub Aydınlık", "GitHub Karanlık", "Monokai Pro " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstRenk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstRenkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstRenk);

        pnlSettings.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 130, 250));

        jLabel1.setText("Tema Rengi Seçiniz");
        pnlSettings.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        btnSettingsKaydet.setText("Kaydet");
        btnSettingsKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsKaydetActionPerformed(evt);
            }
        });
        pnlSettings.add(btnSettingsKaydet, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, -1, -1));

        getContentPane().add(pnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
///////////////////////////////////////////////// Veritabanından Tema Rengi Çekme Başlangıç /////////////////////////////////////////////////

    public void TemaRengi() {
        if (tema == 0) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    //FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 1) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //FlatAnimatedLafChange.showSnapshot();
                    FlatDarkLaf.setup();
                    FlatLaf.updateUI();
                    //FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 2) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 3) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    //FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 4) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 5) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcDarkOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 6) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 7) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 8) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCyanLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 9) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCarbonIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 10) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 11) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (tema == 12) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMonokaiProIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        }
    }
///////////////////////////////////////////////// Veritabanından Tema Rengi Çekme Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Listeden Tema Rengi Çekme Başlangıç /////////////////////////////////////////////////    
    public void RenkSecmeListe() {
        if (lstRenk.isSelectedIndex(0)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 0;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(1)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 1;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(2)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 2;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(3)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 3;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(4)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 4;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(5)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 5;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcDarkOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(6)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 6;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(7)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 7;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(8)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 8;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCyanLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(9)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 9;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCarbonIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(10)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 10;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(11)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 11;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else if (lstRenk.isSelectedIndex(12)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tema = 12;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMonokaiProIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        }
    }
///////////////////////////////////////////////// Listeden Tema Rengi Çekme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Ayarlar Kaptma Başlangıç /////////////////////////////////////////////////      
    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_pnlSettingsKapatMouseClicked
///////////////////////////////////////////////// Ayarlar Kaptma Bitiş /////////////////////////////////////////////////  

    private void lstRenkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstRenkMouseClicked
        RenkSecmeListe();
    }//GEN-LAST:event_lstRenkMouseClicked

///////////////////////////////////////////////// Ayarlar Açma Başlangıç /////////////////////////////////////////////////    
    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        jTabbedPane1.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_lblSettingsMouseClicked
///////////////////////////////////////////////// Ayarlar Açma Bitiş /////////////////////////////////////////////////    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TemaRengi();
    }//GEN-LAST:event_formWindowActivated
///////////////////////////////////////////////// Şeçilen Rengi Veritabanına Kaydetme Başlangıç /////////////////////////////////////////////////        
    private void btnSettingsKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsKaydetActionPerformed
        try {
            String sql = "UPDATE public.kullanicilar SET temarengi=? WHERE email=? and sifre=?;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, tema);
            pst.setString(2, email);
            pst.setString(3, sifre);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(rootPane, "Veriler Kaydedildi");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Veriler Kaydedilmedi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSettingsKaydetActionPerformed
///////////////////////////////////////////////// Şeçilen Rengi Veritabanına Kaydetme Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Çıkış Log Kaydı Başlangıç /////////////////////////////////////////////////    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            String sqllog = "INSERT INTO public.kullanici_log(adsoyad, email, ip, islem)VALUES ((Select adsoyad from public.kullanicilar WHERE email='" + email + "'),'" + email + "', '" + ip.getHostAddress() + "','Çıkış');";
            pst = conn.prepareStatement(sqllog);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showConfirmDialog(null, "Log Kayıt Başarılı");

            } else {
                JOptionPane.showConfirmDialog(null, " Log Kayıt Başarısız");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing
///////////////////////////////////////////////// Çıkış Log Kaydı Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Resim Seçme Başlangıç /////////////////////////////////////////////////  
    private void btnGoruntuSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoruntuSecActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File dosya = chooser.getSelectedFile();
        dosyaadi = dosya.getAbsolutePath();
        ImageIcon resim = new ImageIcon(dosyaadi);
        Image image = resim.getImage().getScaledInstance(pnlResim.getWidth(), pnlResim.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon boyutlanmisresim = new ImageIcon(image);
        lblResim.setBounds(pnlResim.getBounds());
        //pnlResim.add(lblResim);
        lblResim.setIcon(boyutlanmisresim);
    }//GEN-LAST:event_btnGoruntuSecActionPerformed
///////////////////////////////////////////////// Resim Seçme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Envantere Kaydetme Başlangıç /////////////////////////////////////////////////  
    public void EnvantereEkle(String kitapadi, int kitapkodu, int count) {
        if (count == 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO kitap_envanter (kitap_adi, kitap_kodu, kitap_sayisi) VALUES (?, ?, ?)");
                ps.setString(1, kitapadi);
                ps.setInt(2, kitapkodu);
                ps.setInt(3, 1);
                int sonuc = ps.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Eklendi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Eklenmedi");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (count > 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE public.kitap_envanter SET kitap_sayisi=? WHERE kitap_kodu =?;");
                ps.setInt(1, count);
                ps.setInt(2, kitapkodu);
                int sonuc = ps.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Güncellendi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Güncellenmedi");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
///////////////////////////////////////////////// Envantere Kaydetme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Kitap Veritabanına Kaydetme Başlangıç /////////////////////////////////////////////////      
    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        String yazaradsoyad = txtYazarAdSoyad.getText();
        String kitapadi = txtKitapAdi.getText();
        String yayinevi = txtYayinEvi.getText();
        int kitapkodu = Integer.parseInt(txtKitapKodu.getText());
        String kitapturu = cbKitapTuru.getSelectedItem().toString();

        try {
            fis = new FileInputStream(dosyaadi);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO kitaplik (yazar_adsoyad, kitap_adi, kitap_resim, kitap_kodu, yayin_evi, kitap_turu) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, yazaradsoyad);
            ps.setString(2, kitapadi);
            ps.setBinaryStream(3, fis, dosyaadi.length());
            ps.setInt(4, kitapkodu);
            ps.setString(5, yayinevi);
            ps.setString(6, kitapturu);
            int sonuc = ps.executeUpdate();
            if (sonuc == 1) {
                fis.close();
                ps.close();
                PreparedStatement ps2 = conn.prepareStatement("SELECT Count(kitap_kodu) FROM public.kitaplik where kitap_kodu =?;");
                ps2.setInt(1, kitapkodu);
                rs = ps2.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt("count");
                    EnvantereEkle(kitapadi, kitapkodu, count);
                }

                KitaplarTabloVerileri();
                JOptionPane.showMessageDialog(null, "Kitap Eklendi");
            } else {
                JOptionPane.showMessageDialog(null, "Kitap Eklenmedi");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResimKoyma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResimKoyma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKaydetActionPerformed
///////////////////////////////////////////////// Kitap Veritabanına Kaydetme Bitiş ///////////////////////////////////////////////// 

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnKabulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabulActionPerformed
        int tabloIndex = tblRandevular.getSelectedRow();
        String randevuisteyenkisi = (String) tblRandevular.getValueAt(tabloIndex, 0);
        String randevukonu = (String) tblRandevular.getValueAt(tabloIndex, 1);
        String randevutarih = (String) tblRandevular.getValueAt(tabloIndex, 2) + ":00";
        Timestamp timestamp = Timestamp.valueOf(randevutarih);
        if (tabloIndex >= 0) {
            try {
                try {
                    String sqldelete = "DELETE FROM public.randevu WHERE randevu_isteyen_adsoyad = ? and randevu_konusu = ? and randevu_talep_tarihi = ?;";
                    pst = conn.prepareStatement(sqldelete);
                    pst.setString(1, randevuisteyenkisi);
                    pst.setString(2, randevukonu);
                    pst.setTimestamp(3, timestamp);
                    int sonucdelete = pst.executeUpdate();
                    if (sonucdelete == 1) {
                        JOptionPane.showMessageDialog(null, "Silindi");
                        RandevuTabloVerileri();
                    } else {
                        JOptionPane.showMessageDialog(null, "Silinmedi");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
                String sqlinsert = "INSERT INTO public.randevu_kabul(randevu_kabul_isteyen_adsoyad, randevu_kabul_isteyen_email, randevu_kabul_veren_adsoyad, randevu_kabul_veren_email, randevu_kabul_konusu, randevu_kabul_talep_tarihi, randevu_kabul_durum) VALUES (?, (Select email from public.kullanicilar where adsoyad = ?), (Select adsoyad from public.kullanicilar where email = ?), ?, ?, ?, ?);";
                pst = conn.prepareStatement(sqlinsert);
                pst.setString(1, randevuisteyenkisi);
                pst.setString(2, randevuisteyenkisi);
                pst.setString(3, email);
                pst.setString(4, email);
                pst.setString(5, randevukonu);
                pst.setTimestamp(6, timestamp);
                pst.setString(7, "Kabul Edildi");
                int sonucinsert = pst.executeUpdate();
                if (sonucinsert == 1) {
                    JOptionPane.showMessageDialog(null, "İnsert oldu");
                } else {
                    JOptionPane.showMessageDialog(null, "İnsert Olmadı");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Randevu Seçilmedi");
        }
    }//GEN-LAST:event_btnKabulActionPerformed

    private void tblRandevularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRandevularMouseClicked
        int tabloIndex = tblRandevular.getSelectedRow();
        txtRandevuIsteyenKisi.setText((String) tblRandevular.getValueAt(tabloIndex, 0));
        txtKonu.setText((String) tblRandevular.getValueAt(tabloIndex, 1));
        txtRandevuTarih.setText((String) tblRandevular.getValueAt(tabloIndex, 2));
    }//GEN-LAST:event_tblRandevularMouseClicked

    private void btnRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetActionPerformed
        if (!txtSebep.getText().isEmpty()) {
            int tabloIndex = tblRandevular.getSelectedRow();
            String randevuisteyenkisi = (String) tblRandevular.getValueAt(tabloIndex, 0);
            String randevukonu = (String) tblRandevular.getValueAt(tabloIndex, 1);
            String randevutarih = (String) tblRandevular.getValueAt(tabloIndex, 2) + ":00";
            Timestamp timestamp = Timestamp.valueOf(randevutarih);
            if (tabloIndex >= 0) {
                try {
                    try {
                        String sqldelete = "DELETE FROM public.randevu WHERE randevu_isteyen_adsoyad = ? and randevu_konusu = ? and randevu_talep_tarihi = ?;";
                        pst = conn.prepareStatement(sqldelete);
                        pst.setString(1, randevuisteyenkisi);
                        pst.setString(2, randevukonu);
                        pst.setTimestamp(3, timestamp);
                        int sonucdelete = pst.executeUpdate();
                        if (sonucdelete == 1) {
                            JOptionPane.showMessageDialog(null, "Silindi");
                            RandevuTabloVerileri();
                        } else {
                            JOptionPane.showMessageDialog(null, "Silinmedi");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String sqlinsert = "INSERT INTO public.randevu_ret(randevu_ret_isteyen_adsoyad, randevu_ret_isteyen_email, randevu_ret_veren_adsoyad, randevu_ret_veren_email, randevu_ret_konusu, randevu_ret_talep_tarihi, randevu_ret_durum, randevu_ret_sebep) VALUES (?, (Select email from public.kullanicilar where adsoyad = ?), (Select adsoyad from public.kullanicilar where email = ?), ?, ?, ?, ?, ?);";
                    pst = conn.prepareStatement(sqlinsert);
                    pst.setString(1, randevuisteyenkisi);
                    pst.setString(2, randevuisteyenkisi);
                    pst.setString(3, email);
                    pst.setString(4, email);
                    pst.setString(5, randevukonu);
                    pst.setTimestamp(6, timestamp);
                    pst.setString(7, "Reddedildi");
                    pst.setString(8, txtSebep.getText());
                    int sonucinsert = pst.executeUpdate();
                    if (sonucinsert == 1) {
                        JOptionPane.showMessageDialog(null, "İnsert oldu");
                    } else {
                        JOptionPane.showMessageDialog(null, "İnsert Olmadı");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Randevu Seçilmedi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Litfen Bir Sebep Giriniz");
        }
    }//GEN-LAST:event_btnRetActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoruntuSec;
    private javax.swing.JButton btnKabul;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JButton btnKitapAra;
    private javax.swing.JButton btnRet;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbKitapTuru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapResmi;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblRenk1Adı;
    private javax.swing.JLabel lblRenk2Adı;
    private javax.swing.JLabel lblRenk3Adı;
    private javax.swing.JLabel lblRenk4Adı;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JList<String> lstRenk;
    private javaswingdev.chart.PieChart pieChart1;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDuyurular;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlKitapOnaylama;
    private javax.swing.JPanel pnlKitaplar;
    private javax.swing.JPanel pnlRandevular;
    private javax.swing.JPanel pnlRenk1;
    private javax.swing.JPanel pnlRenk2;
    private javax.swing.JPanel pnlRenk3;
    private javax.swing.JPanel pnlRenk4;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JPanel pnlUyeIslemleri;
    private javax.swing.JTable tblKitaplar;
    private javax.swing.JTable tblRandevular;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextArea txtKonu;
    private javax.swing.JTextField txtRandevuIsteyenKisi;
    private javax.swing.JTextField txtRandevuTarih;
    private javax.swing.JTextArea txtSebep;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
