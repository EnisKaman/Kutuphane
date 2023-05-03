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
import javaswingdev.chart.PieChart;
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
    String kitapisteyenemail;
    String kitapisteyenadsoyad;
    String kitapgetirmetarih;
    String kitapyayinevi;
    int secilenkitapid = 0;

    public void KitaplarTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT kitap_adi,kitap_kodu,yazar_adsoyad,yayin_evi,kitap_turu,kitap_durum FROM public.kitaplik;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Kitap Adı");
            model.addColumn("Yazar");
            model.addColumn("Yayın Evi");
            model.addColumn("Kitap Türü");
            model.addColumn("Kitap Durumu");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("kitap_kodu");
                row[1] = rs.getString("kitap_adi");
                row[2] = rs.getString("yazar_adsoyad");
                row[3] = rs.getString("yayin_evi");
                row[4] = rs.getString("kitap_turu");
                row[5] = rs.getString("kitap_durum");
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

    public void KitapOnayTabloVerileri() throws ParseException {
        try {
            String sql = "SELECT * FROM public.kitap_al_istek where kitap_al_istek_durum = 'Beklemede';";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Adı");
            model.addColumn("Yayın Evi");
            model.addColumn("İsteyen Ad Soyad");
            model.addColumn("İsteyen Email");
            model.addColumn("Getirme Tarihi");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("kitap_al_istek_kitap_adi");
                row[1] = rs.getString("kitap_al_istek_kitap_yayinevi");
                row[2] = rs.getString("kitap_al_istek_isteyen_ad_soyad");
                row[3] = rs.getString("kitap_al_istek_isteyen_email");
                String gerigetirme = rs.getString("kitap_al_istek_geri_verme_tarihi");
                SimpleDateFormat girisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat cikisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date2 = girisFormat.parse(gerigetirme);
                String songerigetirmeDate = cikisFormat.format(date2);
                row[4] = songerigetirmeDate;
                String tarihkitap = rs.getString("kitap_al_istek_guncelleme_tarihi");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = inputFormat.parse(tarihkitap);
                String formattedDate = outputFormat.format(date);
                row[5] = formattedDate;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblKitapOnay.setDefaultRenderer(Object.class, centerRenderer);
            tblKitapOnay.setModel(model);
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

    public void PastaGrafik() {
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
                pieChart1.addData(new ModelPieChart(islem, cikis, Renkler(index++)));
                //lblRenk1.setBackground(Renkler(index++));
                //lblRenk1Adı.setText(islem);
            }

            pnlRenk1.setBackground(Renkler(0));
            lblRenk1Adi.setText("Çıkış");
            pnlRenk2.setBackground(Renkler(1));
            lblRenk2Adi.setText("Giriş");
            pnlRenk3.setBackground(Renkler(2));
            lblRenk3Adi.setText("Giriş Yapmadan Çıkış");
            pnlRenk4.setBackground(Renkler(3));
            lblRenk4Adi.setText("Kullanıcı Adı ya da Şifre Yanlış");
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Color Renkler(int index) {
        Color[] color = new Color[]{new Color(255, 102, 102), new Color(29, 184, 80), new Color(206, 215, 33), new Color(55, 55, 227)};
        return color[index];
    }

    public void KitapIstekKaldirma(int kalkacakindex, String durum, int kitapkodu, String kitapadi, boolean kabul) throws SQLException, ParseException {
        String sqldelete = "UPDATE public.kitap_al_istek SET kitap_al_istek_durum=? WHERE kitap_al_istek_ID = ?;";
        pst = conn.prepareStatement(sqldelete);
        pst.setString(1, durum);
        pst.setInt(2, secilenkitapid);
        int sonuc = pst.executeUpdate();
        if (sonuc == 1) {
            JOptionPane.showMessageDialog(null, "Silindi");
        }
        
        if (kabul == true) {
            String sql = "UPDATE public.kitaplik SET kitap_durum = ? where kitap_kodu = ?;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapisteyenemail);
            pst.setInt(2, kitapkodu);
            int cevap = pst.executeUpdate();
            if (cevap == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Şu anda " + kitapisteyenemail);
            }

            System.out.println("asdasasdasd");
            String sqlupdate = "UPDATE public.kitap_envanter SET elde_olan = elde_olan - 1 WHERE kitap_adi=?;";
            pst = conn.prepareStatement(sqlupdate);
            pst.setString(1, kitapadi);
            int donus = pst.executeUpdate();
            if (donus == 1) {
                JOptionPane.showMessageDialog(null, "Azaldı");
            }
        }

        txtEnvanterdeKalan.setText("");
        txtToplamKitap.setText("");
        cbKitapKodları.removeAllItems();
        KitapOnayTabloVerileri();
    }

    public AdminArayuzu() {
        initComponents();
    }

    public AdminArayuzu(String email, String sifre, int tema) throws ParseException {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;

        initComponents();
        KitaplarTabloVerileri();
        RandevuTabloVerileri();
        PastaGrafik();
        KitapOnayTabloVerileri();
        TemaRengi();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKitapOnay = new javax.swing.JTable();
        cbKitapKodları = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtToplamKitap = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEnvanterdeKalan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtKitapReddetmeSebep = new javax.swing.JTextArea();
        btnKitapOnayla = new javax.swing.JButton();
        btnKitapReddet = new javax.swing.JButton();
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
        lblRenk1Adi = new javax.swing.JLabel();
        lblRenk2Adi = new javax.swing.JLabel();
        lblRenk3Adi = new javax.swing.JLabel();
        lblRenk4Adi = new javax.swing.JLabel();
        pnlRenk1 = new javax.swing.JPanel();
        pnlRenk2 = new javax.swing.JPanel();
        pnlRenk3 = new javax.swing.JPanel();
        pnlRenk4 = new javax.swing.JPanel();
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

        tblKitapOnay.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKitapOnay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapOnayMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblKitapOnay);

        jLabel3.setText("Eldeki Kitapların Kodları");

        txtToplamKitap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtToplamKitap.setEnabled(false);

        jLabel4.setText("Toplam Kitap");

        txtEnvanterdeKalan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEnvanterdeKalan.setEnabled(false);

        jLabel5.setText(" Envanterde Kalan ");

        txtKitapReddetmeSebep.setColumns(20);
        txtKitapReddetmeSebep.setRows(5);
        txtKitapReddetmeSebep.setName("Sebep"); // NOI18N
        jScrollPane7.setViewportView(txtKitapReddetmeSebep);

        btnKitapOnayla.setForeground(new java.awt.Color(153, 255, 153));
        btnKitapOnayla.setText("Onayla");
        btnKitapOnayla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapOnaylaActionPerformed(evt);
            }
        });

        btnKitapReddet.setForeground(new java.awt.Color(255, 51, 51));
        btnKitapReddet.setText("Reddet");
        btnKitapReddet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapReddetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKitapOnaylamaLayout = new javax.swing.GroupLayout(pnlKitapOnaylama);
        pnlKitapOnaylama.setLayout(pnlKitapOnaylamaLayout);
        pnlKitapOnaylamaLayout.setHorizontalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKitapKodları, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)))
                .addGap(54, 54, 54)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEnvanterdeKalan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKitapOnayla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKitapReddet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlKitapOnaylamaLayout.setVerticalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnKitapOnayla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbKitapKodları, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEnvanterdeKalan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKitapReddet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)
                        .addContainerGap())))
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
            .addGap(0, 499, Short.MAX_VALUE)
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

        txtRandevuIsteyenKisi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtRandevuTarih.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Duyurular", pnlDuyurular);

        lblRenk1Adi.setText("jLabel6");
        pieChart1.add(lblRenk1Adi);
        lblRenk1Adi.setBounds(630, 30, 160, 16);

        lblRenk2Adi.setText("jLabel6");
        pieChart1.add(lblRenk2Adi);
        lblRenk2Adi.setBounds(630, 60, 160, 16);

        lblRenk3Adi.setText("jLabel6");
        pieChart1.add(lblRenk3Adi);
        lblRenk3Adi.setBounds(630, 90, 160, 16);

        lblRenk4Adi.setText("jLabel6");
        pieChart1.add(lblRenk4Adi);
        lblRenk4Adi.setBounds(630, 120, 160, 16);

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
        pnlRenk1.setBounds(600, 30, 20, 20);

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
        pnlRenk2.setBounds(600, 60, 20, 20);

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
        pnlRenk3.setBounds(600, 90, 20, 20);

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
        pnlRenk4.setBounds(600, 120, 20, 20);

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dashboard", pnlDashboard);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 788, 530));
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
                //JOptionPane.showConfirmDialog(null, "Log Kayıt Başarılı");

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
    public void EnvantereEkle(String kitapadi, int kitapkodu, int count, String yayinevi) {
        if (count == 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO kitap_envanter (kitap_adi, kitap_sayisi, kitap_yayinevi,elde_olan) VALUES (?, ?, ?, ?)");
                ps.setString(1, kitapadi);
                ps.setInt(2, 1);
                ps.setString(3, yayinevi);
                ps.setInt(4, 1);
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
                PreparedStatement ps = conn.prepareStatement("UPDATE public.kitap_envanter SET kitap_sayisi=?,elde_olan = elde_olan + 1 WHERE kitap_yayinevi =? and kitap_adi =?;");
                ps.setInt(1, count);
                ps.setString(2, yayinevi);
                ps.setString(3, kitapadi);
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
        try {
            String yazaradsoyad = txtYazarAdSoyad.getText();
            String kitapadi = txtKitapAdi.getText();
            String yayinevi = txtYayinEvi.getText();
            int kitapkodu = Integer.parseInt(txtKitapKodu.getText());
            String kitapturu = cbKitapTuru.getSelectedItem().toString();

            String sqlkitapkodu = "Select COUNT(kitap_kodu) from public.kitaplik where kitap_kodu = ?;";
            pst = conn.prepareStatement(sqlkitapkodu);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            int sayi = 0;
            if (rs.next()) {
                sayi = rs.getInt("count");
            }
            if (sayi == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Zaten Envantere Eklenmiş");
            } else {
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
                        PreparedStatement ps2 = conn.prepareStatement("SELECT Count(kitap_adi) FROM public.kitaplik where kitap_adi =? and yayin_evi =?;");
                        ps2.setString(1, kitapadi);
                        ps2.setString(2, yayinevi);
                        rs = ps2.executeQuery();

                        if (rs.next()) {
                            int count = rs.getInt("count");
                            EnvantereEkle(kitapadi, kitapkodu, count, yayinevi);
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
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKaydetActionPerformed
///////////////////////////////////////////////// Kitap Veritabanına Kaydetme Bitiş ///////////////////////////////////////////////// 

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked
///////////////////////////////////////////////// Randevu Kabul Başlangıç ///////////////////////////////////////////////// 
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
///////////////////////////////////////////////// Randevu Kabul Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Randevu Tablosu Tıklama Başlangıç /////////////////////////////////////////////////
    private void tblRandevularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRandevularMouseClicked
        int tabloIndex = tblRandevular.getSelectedRow();
        txtRandevuIsteyenKisi.setText((String) tblRandevular.getValueAt(tabloIndex, 0));
        txtKonu.setText((String) tblRandevular.getValueAt(tabloIndex, 1));
        txtRandevuTarih.setText((String) tblRandevular.getValueAt(tabloIndex, 2));
    }//GEN-LAST:event_tblRandevularMouseClicked
///////////////////////////////////////////////// Randevu Kabul Bitiş /////////////////////////////////////////////////7

///////////////////////////////////////////////// Randevu Reddetme Başlangıç /////////////////////////////////////////////////    
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
///////////////////////////////////////////////// Randevu Reddetme Bitiş /////////////////////////////////////////////////   

///////////////////////////////////////////////// Kitap Onay Tablosu Tıklma Başlangıç /////////////////////////////////////////////////   
    private void tblKitapOnayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapOnayMouseClicked
        try {
            int kitaponayindex = tblKitapOnay.getSelectedRow();
            String kitapadi = (String) tblKitapOnay.getValueAt(kitaponayindex, 0);
            kitapyayinevi = (String) tblKitapOnay.getValueAt(kitaponayindex, 1);
            kitapisteyenadsoyad = (String) tblKitapOnay.getValueAt(kitaponayindex, 2);
            kitapisteyenemail = (String) tblKitapOnay.getValueAt(kitaponayindex, 3);
            kitapgetirmetarih = (String) tblKitapOnay.getValueAt(kitaponayindex, 4) + ":00";

            String sql = "SELECT k.kitap_kodu,(Select e.kitap_sayisi from public.kitap_envanter e where k.kitap_adi = e.kitap_adi and k.yayin_evi = e.kitap_yayinevi),(Select e.elde_olan from public.kitap_envanter e where k.kitap_adi = e.kitap_adi and k.yayin_evi = e.kitap_yayinevi) FROM public.kitaplik k where k.kitap_adi = ? and k.yayin_evi = ? and k.kitap_durum = 'Envanterde' Order By k.kitap_kodu ASC;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapadi);
            pst.setString(2, kitapyayinevi);
            rs = pst.executeQuery();

            while (rs.next()) {
                cbKitapKodları.addItem(rs.getString("kitap_kodu"));
                txtEnvanterdeKalan.setText(rs.getString("elde_olan"));
                txtToplamKitap.setText(rs.getString("kitap_sayisi"));
            }

            String sqlidalma = "Select kitap_al_istek_id from public.kitap_al_istek where kitap_al_istek_geri_verme_tarihi=?";
            pst = conn.prepareStatement(sqlidalma);
            SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = girisFormat.parse(kitapgetirmetarih);
            String songerigetirmeDate = cikisFormat.format(date2);
            Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
            pst.setTimestamp(1, timestamp);
            rs = pst.executeQuery();
            if (rs.next()) {
                secilenkitapid = rs.getInt("kitap_al_istek_id");
                System.out.println(secilenkitapid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblKitapOnayMouseClicked
///////////////////////////////////////////////// Kitap Onay Tablosu Tıklma Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Kitap Onaylama Buton Başlangıç /////////////////////////////////////////////////
    private void btnKitapOnaylaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapOnaylaActionPerformed
        try {
            int kitapkodu = Integer.parseInt(cbKitapKodları.getSelectedItem().toString());
            String kitapadi = null;
            String sqlkitpadi = "Select kitap_adi from public.kitaplik where kitap_kodu = ?";
            pst = conn.prepareStatement(sqlkitpadi);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                kitapadi = rs.getString("kitap_adi");
            }

            String kitapverenadsoyad = "(Select adsoyad from public.kullanicilar where email = ?)";

            String sql = "INSERT INTO public.kitap_al_kabul( kitap_al_kabul_kitap_kodu, kitap_al_kabul_kitap_adi, kitap_al_kabul_isteyen_ad_soyad, kitap_al_kabul_isteyen_email, kitap_al_kabul_veren_ad_soyad, kitap_al_kabul_veren_email, kitap_al_kabul_durum, kitap_al_kabul_geri_getirme_tarihi, kitap_al_kabul_kitap_yayinevi) VALUES (?, ?, ?, ?," + kitapverenadsoyad + ",  ?, 'Kabul Edildi', ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, kitapkodu);
            pst.setString(2, kitapadi);
            pst.setString(3, kitapisteyenadsoyad);
            pst.setString(4, kitapisteyenemail);
            pst.setString(5, email);
            pst.setString(6, email);
            SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = girisFormat.parse(kitapgetirmetarih);
            String songerigetirmeDate = cikisFormat.format(date2);
            Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
            pst.setTimestamp(7, timestamp);
            pst.setString(8, kitapyayinevi);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi");
            } else {
                JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi hata");
            }
            KitapIstekKaldirma(secilenkitapid, "Kabul Edildi", kitapkodu, kitapadi, true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKitapOnaylaActionPerformed
///////////////////////////////////////////////// Kitap Onaylama Buton Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Kitap Reddetme Buton Başlangıç /////////////////////////////////////////////////
    private void btnKitapReddetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapReddetActionPerformed
        if (txtKitapReddetmeSebep.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütfen Reddedilme Sebebi Giriniz");
        } else {
            try {
                int kitapkodu = Integer.parseInt(cbKitapKodları.getSelectedItem().toString());
                String sebep = txtKitapReddetmeSebep.getText();

                String kitapadi = null;
                String sqlkitpadi = "Select kitap_adi from public.kitaplik where kitap_kodu = ?";
                pst = conn.prepareStatement(sqlkitpadi);
                pst.setInt(1, kitapkodu);
                rs = pst.executeQuery();
                if (rs.next()) {
                    kitapadi = rs.getString("kitap_adi");
                }
                String kitapverenadsoyad = "(Select adsoyad from public.kullanicilar where email = ?)";

                String sql = "INSERT INTO public.kitap_al_ret(kitap_al_ret_kitap_adi, kitap_al_ret_isteyen_ad_soyad, kitap_al_ret_isteyen_email, kitap_al_ret_veren_ad_soyad, kitap_al_ret_veren_email, kitap_al_ret_durum, kitap_al_ret_geri_getirme_tarihi, kitap_al_ret_kitap_yayinevi, kitap_al_ret_sebep) VALUES (?, ?, ?," + kitapverenadsoyad + ",  ?, 'Kabul Edildi', ?, ?, ?);";
                pst = conn.prepareStatement(sql);
                pst.setString(1, kitapadi);
                pst.setString(2, kitapisteyenadsoyad);
                pst.setString(3, kitapisteyenemail);
                pst.setString(4, email);
                pst.setString(5, email);
                SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = girisFormat.parse(kitapgetirmetarih);
                String songerigetirmeDate = cikisFormat.format(date2);
                Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
                pst.setTimestamp(6, timestamp);
                pst.setString(7, kitapyayinevi);
                pst.setString(8, sebep);
                int sonuc = pst.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Reddedildi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi hata");
                }
                KitapIstekKaldirma(secilenkitapid, "Reddedildi", kitapkodu, kitapadi, false);

            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnKitapReddetActionPerformed
/////////////////////////////////////////////// Kitap Reddetme Buton Bitiş /////////////////////////////////////////////////

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoruntuSec;
    private javax.swing.JButton btnKabul;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JButton btnKitapAra;
    private javax.swing.JButton btnKitapOnayla;
    private javax.swing.JButton btnKitapReddet;
    private javax.swing.JButton btnRet;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbKitapKodları;
    private javax.swing.JComboBox<String> cbKitapTuru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapResmi;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblRenk1Adi;
    private javax.swing.JLabel lblRenk2Adi;
    private javax.swing.JLabel lblRenk3Adi;
    private javax.swing.JLabel lblRenk4Adi;
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
    private javax.swing.JTable tblKitapOnay;
    private javax.swing.JTable tblKitaplar;
    private javax.swing.JTable tblRandevular;
    private javax.swing.JTextField txtEnvanterdeKalan;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextArea txtKitapReddetmeSebep;
    private javax.swing.JTextArea txtKonu;
    private javax.swing.JTextField txtRandevuIsteyenKisi;
    private javax.swing.JTextField txtRandevuTarih;
    private javax.swing.JTextArea txtSebep;
    private javax.swing.JTextField txtToplamKitap;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
