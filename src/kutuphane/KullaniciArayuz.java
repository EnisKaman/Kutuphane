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
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KullaniciArayuz extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String email = null;
    String sifre = null;
    int tema = 0;
    String tarih;
    String tarihkitap;
    String saat;
    String kitapal_yayinevi;
    int belgekodu = 0;

    public void KitaplarTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT k.kitap_adi ,k.yazar_adsoyad, k.yayin_evi,k.kitap_turu,(Select e.okuma_sayisi from public.kitap_envanter e where e.kitap_adi = k.kitap_adi and e.kitap_yayinevi = k.yayin_evi),(Select e.elde_olan from public.kitap_envanter e where e.kitap_adi = k.kitap_adi and e.kitap_yayinevi = k.yayin_evi) FROM public.kitaplik k;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Adı");
            model.addColumn("Yazar");
            model.addColumn("Yayın Evi");
            model.addColumn("Kitap Türü");
            model.addColumn("Okuma Sayısı");

            while (rs.next()) {
                int kitapsayisi = rs.getInt("elde_olan");
                if (kitapsayisi > 0) {
                    Object[] row = new Object[5];
                    row[0] = rs.getString("kitap_adi");
                    row[1] = rs.getString("yazar_adsoyad");
                    row[2] = rs.getString("yayin_evi");
                    row[3] = rs.getString("kitap_turu");
                    row[4] = rs.getInt("okuma_sayisi");
                    model.addRow(row);
                }

            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblKitapAl.setDefaultRenderer(Object.class, centerRenderer);
            tblKitapAl.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ArsivBelgelerimTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT belge_adi,belge_yayinlayan_kisi,belge_kodu,belge_yayin_yili,belge_turu FROM public.arsiv;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Belge Adı");
            model.addColumn("Yayınlayan Kisi");
            model.addColumn("Belge Kodu");
            model.addColumn("Yayın Yılı");
            model.addColumn("Belge Türü");
            model.addColumn("Belge Nüshası");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("belge_adi");
                row[1] = rs.getString("belge_yayinlayan_kisi");
                row[2] = rs.getInt("belge_kodu");
                row[3] = rs.getInt("belge_yayin_yili");
                row[4] = rs.getString("belge_turu");
                row[5] = "Gör";
                model.addRow(row);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblBelgelerim.setDefaultRenderer(Object.class, centerRenderer);
            tblBelgelerim.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BekleyenRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu where randevu_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Eden Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_veren_adsoyad");
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
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblBekleyenRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblBekleyenRandevu.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KabulRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu_kabul where randevu_kabul_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Veren Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_kabul_isteyen_adsoyad");
                row[1] = rs.getString("randevu_kabul_konusu");
                String randevutaleptarih = rs.getString("randevu_kabul_talep_tarihi");
                LocalDateTime dateTime = LocalDateTime.parse(randevutaleptarih, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String yeniDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[2] = yeniDateTime;
                row[3] = rs.getString("randevu_kabul_durum");
                String originalDateTime = rs.getString("randevu_kabul_olusturma_tarih");
                LocalDateTime dateTime2 = LocalDateTime.parse(originalDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                String newDateTime = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[4] = newDateTime;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblKabulRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblKabulRandevu.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RetRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu_ret where randevu_ret_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Veren Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Reddedilme Sebebi");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("randevu_ret_isteyen_adsoyad");
                row[1] = rs.getString("randevu_ret_konusu");
                String randevutaleptarih = rs.getString("randevu_ret_talep_tarihi");
                LocalDateTime dateTime = LocalDateTime.parse(randevutaleptarih, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String yeniDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[2] = yeniDateTime;
                row[3] = rs.getString("randevu_ret_durum");
                row[4] = rs.getString("randevu_ret_sebep");
                String originalDateTime = rs.getString("randevu_ret_olusturma_tarih");
                LocalDateTime dateTime2 = LocalDateTime.parse(originalDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                String newDateTime = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[5] = newDateTime;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblRetRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblRetRandevu.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PDFGoster() throws FileNotFoundException, IOException {
        try {
            String sql = "SELECT belge_nushasi,belge_nushasi_adi FROM public.arsiv WHERE belge_kodu = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, belgekodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                //JOptionPane.showMessageDialog(null, "asdfas");
                byte[] pdfData = rs.getBytes("belge_nushasi");
                String belgenushasiadi = rs.getString("belge_nushasi_adi");
                String dosyaAdi = belgenushasiadi;

                // Byte dizisini PDF dosyasına yazma işlemi
                FileOutputStream fos = new FileOutputStream(dosyaAdi);
                fos.write(pdfData);
                fos.close();

                // PDF dosyasını varsayılan PDF görüntüleyici ile açma
                Desktop.getDesktop().open(new File(dosyaAdi));
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KullaniciArayuz() {
        initComponents();
    }

    public KullaniciArayuz(String email, String sifre, int tema) {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;
        initComponents();
        pnlSettings.setVisible(false);
        AdminCekme();
        BekleyenRandevuTabloVerileri();
        KabulRandevuTabloVerileri();
        RetRandevuTabloVerileri();
        KitaplarTabloVerileri();
        ArsivBelgelerimTabloVerileri();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblKutuphane = new javax.swing.JLabel();
        lblArsiv = new javax.swing.JLabel();
        lblDiger = new javax.swing.JLabel();
        tabKutuphane = new javax.swing.JTabbedPane();
        pnlKitapAlma = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKitapAl = new javax.swing.JTable();
        txtYayinEvi = new javax.swing.JTextField();
        txtKitapAdi = new javax.swing.JTextField();
        KitapAlTarihSecici = new com.toedter.calendar.JDateChooser();
        cbKutuphaneci = new javax.swing.JComboBox<>();
        btnKitapAl = new javax.swing.JButton();
        pnlAldigimKitaplar = new javax.swing.JPanel();
        lblSettings = new javax.swing.JLabel();
        pnlSettings = new javax.swing.JPanel();
        pnlSettingsKapat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRenk = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSettingsKaydet = new javax.swing.JButton();
        tabArsiv = new javax.swing.JTabbedPane();
        pnlBelgeIste = new javax.swing.JPanel();
        pnlBelgelerim = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblBelgelerim = new javax.swing.JTable();
        btnPDFAc = new javax.swing.JButton();
        tabDiger = new javax.swing.JTabbedPane();
        pnlRandevu = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlRandevuAl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRandevuAlKonu = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbRandevuKisi = new javax.swing.JComboBox<>();
        cbSaat = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRandevuAl = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        pnlBekleyenRandevu = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBekleyenRandevu = new javax.swing.JTable();
        pnlKabulEdilenRandevu = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKabulRandevu = new javax.swing.JTable();
        pnlReddedilenRandevu = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRetRandevu = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRetSebep = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKutuphane.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        lblKutuphane.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_literature_64px.png"))); // NOI18N
        lblKutuphane.setText("Kütüphane");
        lblKutuphane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblKutuphane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKutuphaneMouseClicked(evt);
            }
        });
        getContentPane().add(lblKutuphane, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        lblArsiv.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblArsiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_view_64px.png"))); // NOI18N
        lblArsiv.setText("Arşiv");
        lblArsiv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblArsiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArsivMouseClicked(evt);
            }
        });
        getContentPane().add(lblArsiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        lblDiger.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblDiger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_flicker_free_64px.png"))); // NOI18N
        lblDiger.setText("Diğer");
        lblDiger.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDiger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDigerMouseClicked(evt);
            }
        });
        getContentPane().add(lblDiger, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        tblKitapAl.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKitapAl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapAlMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKitapAl);

        txtYayinEvi.setEnabled(false);

        txtKitapAdi.setEnabled(false);

        KitapAlTarihSecici.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                KitapAlTarihSeciciPropertyChange(evt);
            }
        });

        btnKitapAl.setText("Kitap Al");
        btnKitapAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapAlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKitapAlmaLayout = new javax.swing.GroupLayout(pnlKitapAlma);
        pnlKitapAlma.setLayout(pnlKitapAlmaLayout);
        pnlKitapAlmaLayout.setHorizontalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtYayinEvi, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(KitapAlTarihSecici, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbKutuphaneci, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitapAlmaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKitapAl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(323, 323, 323))
        );
        pnlKitapAlmaLayout.setVerticalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(KitapAlTarihSecici, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKutuphaneci)
                    .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKitapAdi)
                        .addComponent(txtYayinEvi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(btnKitapAl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        tabKutuphane.addTab("Kitap Alma", pnlKitapAlma);

        javax.swing.GroupLayout pnlAldigimKitaplarLayout = new javax.swing.GroupLayout(pnlAldigimKitaplar);
        pnlAldigimKitaplar.setLayout(pnlAldigimKitaplarLayout);
        pnlAldigimKitaplarLayout.setHorizontalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlAldigimKitaplarLayout.setVerticalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        tabKutuphane.addTab("Aldığım Kitaplar", pnlAldigimKitaplar);

        getContentPane().add(tabKutuphane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        lblSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/settings_64px.png"))); // NOI18N
        lblSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingsMouseClicked(evt);
            }
        });
        getContentPane().add(lblSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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

        getContentPane().add(pnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 530));

        javax.swing.GroupLayout pnlBelgeIsteLayout = new javax.swing.GroupLayout(pnlBelgeIste);
        pnlBelgeIste.setLayout(pnlBelgeIsteLayout);
        pnlBelgeIsteLayout.setHorizontalGroup(
            pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlBelgeIsteLayout.setVerticalGroup(
            pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        tabArsiv.addTab("Belge İste", pnlBelgeIste);

        tblBelgelerim.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBelgelerim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBelgelerimMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblBelgelerim);

        btnPDFAc.setText("jButton1");
        btnPDFAc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFAcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBelgelerimLayout = new javax.swing.GroupLayout(pnlBelgelerim);
        pnlBelgelerim.setLayout(pnlBelgelerimLayout);
        pnlBelgelerimLayout.setHorizontalGroup(
            pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(pnlBelgelerimLayout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(btnPDFAc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBelgelerimLayout.setVerticalGroup(
            pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgelerimLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnPDFAc)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        tabArsiv.addTab("Belgelerim", pnlBelgelerim);

        getContentPane().add(tabArsiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        pnlRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRandevuAlKonu.setColumns(20);
        txtRandevuAlKonu.setRows(5);
        jScrollPane2.setViewportView(txtRandevuAlKonu);

        jLabel2.setText("Tarih Seçiniz");

        jLabel3.setText("Saat Seçiniz");

        cbSaat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30" }));
        cbSaat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSaatActionPerformed(evt);
            }
        });

        jLabel4.setText("Kime Randevu Alacaksınız ");

        jLabel5.setText("Randevu Konusu");

        btnRandevuAl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRandevuAl.setText("Randevu Al");
        btnRandevuAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandevuAlActionPerformed(evt);
            }
        });

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlRandevuAlLayout = new javax.swing.GroupLayout(pnlRandevuAl);
        pnlRandevuAl.setLayout(pnlRandevuAlLayout);
        pnlRandevuAlLayout.setHorizontalGroup(
            pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(170, 170, 170)
                                .addComponent(jLabel4))
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addComponent(cbSaat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(cbRandevuKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevuAlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(323, 323, 323))
        );
        pnlRandevuAlLayout.setVerticalGroup(
            pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(cbRandevuKisi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(cbSaat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Randevu Al", pnlRandevuAl);

        pnlBekleyenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBekleyenRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblBekleyenRandevu);

        pnlBekleyenRandevu.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        jTabbedPane2.addTab("Bekleyen Randevularım", pnlBekleyenRandevu);

        pnlKabulEdilenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKabulRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblKabulRandevu);

        pnlKabulEdilenRandevu.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        jTabbedPane2.addTab("Kabul Edilen Randevularım", pnlKabulEdilenRandevu);

        pnlReddedilenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRetRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRetRandevu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRetRandevuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblRetRandevu);

        pnlReddedilenRandevu.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 320));

        txtRetSebep.setColumns(20);
        txtRetSebep.setRows(5);
        jScrollPane6.setViewportView(txtRetSebep);

        pnlReddedilenRandevu.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 346, 800, 110));

        jLabel6.setText("Randevunun Reddedilme Sebebi");
        pnlReddedilenRandevu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jTabbedPane2.addTab("Reddedilen Randevularım", pnlReddedilenRandevu);

        pnlRandevu.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tabDiger.addTab("Randevu", pnlRandevu);

        getContentPane().add(tabDiger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        tabKutuphane.setVisible(true);
    }//GEN-LAST:event_pnlSettingsKapatMouseClicked

    private void lstRenkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstRenkMouseClicked
        RenkSecmeListe();
    }//GEN-LAST:event_lstRenkMouseClicked

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
            java.util.logging.Logger.getLogger(KullaniciArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSettingsKaydetActionPerformed

    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        tabKutuphane.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_lblSettingsMouseClicked

    private void cbSaatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSaatActionPerformed
        saat = cbSaat.getSelectedItem().toString();
        System.out.println("" + tarih + " " + saat + "");
    }//GEN-LAST:event_cbSaatActionPerformed

    private void btnRandevuAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandevuAlActionPerformed
        try {
            String randevuverenkisiadsoyad = cbRandevuKisi.getSelectedItem().toString();
            String tarihsaat = "" + tarih + " " + saat + ":00";
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = inputFormat.parse(tarihsaat);
            String formattedDate = outputFormat.format(date);
            Timestamp timestamp = Timestamp.valueOf(formattedDate);

            String sql = "INSERT INTO public.randevu(randevu_isteyen_adsoyad, randevu_isteyen_email, randevu_veren_adsoyad, randevu_veren_email, randevu_konusu, randevu_talep_tarihi, randevu_durum) VALUES ((Select adsoyad from public.kullanicilar where email = ?), ?, ?, (Select email from public.kullanicilar where adsoyad = ?), ?, ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, email);
            pst.setString(3, randevuverenkisiadsoyad);
            pst.setString(4, randevuverenkisiadsoyad);
            pst.setString(5, txtRandevuAlKonu.getText());
            pst.setTimestamp(6, timestamp);
            pst.setString(7, "Beklemede");
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Randevu Eklendi");
            } else {
                JOptionPane.showMessageDialog(null, "Randevu Eklenmedi");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRandevuAlActionPerformed

    private void tblRetRandevuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRetRandevuMouseClicked
        int retindex = tblRetRandevu.getSelectedRow();
        if (retindex >= 0) {
            txtRetSebep.setText((String) tblRetRandevu.getValueAt(retindex, 4));
        }

    }//GEN-LAST:event_tblRetRandevuMouseClicked

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            tarih = format.format(date);
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void tblKitapAlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapAlMouseClicked
        int kitapindex = tblKitapAl.getSelectedRow();
        if (kitapindex >= 0) {
            txtKitapAdi.setText((String) tblKitapAl.getValueAt(kitapindex, 0));
            kitapal_yayinevi = (String) tblKitapAl.getValueAt(kitapindex, 2);
            txtYayinEvi.setText(kitapal_yayinevi);
        }
    }//GEN-LAST:event_tblKitapAlMouseClicked

    private void btnKitapAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapAlActionPerformed
        try {
            String kitapal_kitapadi = txtKitapAdi.getText();
            String kutuphaneciadi = cbKutuphaneci.getSelectedItem().toString();
            String adcekmesql = "(SELECT adsoyad FROM public.kullanicilar where email = ?)";
            String emailcekmesql = "(SELECT email FROM public.kullanicilar where adsoyad = ?)";
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = inputFormat.parse(tarihkitap);
            String formattedDate = outputFormat.format(date);
            Timestamp timestamp = Timestamp.valueOf(formattedDate);

            String sql = "INSERT INTO public.kitap_al_istek( kitap_al_istek_kitap_adi, kitap_al_istek_kitap_yayinevi, kitap_al_istek_isteyen_ad_soyad, kitap_al_istek_isteyen_email, kitap_al_istek_veren_ad_soyad, kitap_al_istek_veren_email, kitap_al_istek_geri_verme_tarihi, kitap_al_istek_durum) VALUES (?, ?, " + adcekmesql + ", ?, ?, " + emailcekmesql + ", ?, 'Beklemede');";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapal_kitapadi);
            pst.setString(2, kitapal_yayinevi);
            pst.setString(3, email);
            pst.setString(4, email);
            pst.setString(5, kutuphaneciadi);
            pst.setString(6, kutuphaneciadi);
            pst.setTimestamp(7, timestamp);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İstek Başarılı");
                txtKitapAdi.setText("");
                txtYayinEvi.setText("");
                KitapAlTarihSecici.setDate(null);
                tblKitapAl.clearSelection();
            } else {
                JOptionPane.showMessageDialog(null, "İstek Başarısız");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnKitapAlActionPerformed

    private void KitapAlTarihSeciciPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_KitapAlTarihSeciciPropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            tarihkitap = format.format(date) + " 18:00:00";
            System.out.println(tarihkitap);
        }
    }//GEN-LAST:event_KitapAlTarihSeciciPropertyChange

    private void lblKutuphaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKutuphaneMouseClicked
        Font font = lblKutuphane.getFont();
        Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        lblKutuphane.setFont(kalınFont);
        lblArsiv.setFont(font);
        lblDiger.setFont(font);
        tabArsiv.setVisible(false);
        tabDiger.setVisible(false);
        tabKutuphane.setVisible(true);
    }//GEN-LAST:event_lblKutuphaneMouseClicked

    private void lblArsivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArsivMouseClicked
        Font font = lblArsiv.getFont();
        Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        lblArsiv.setFont(kalınFont);
        lblKutuphane.setFont(font);
        lblDiger.setFont(font);
        tabArsiv.setVisible(true);
        tabDiger.setVisible(false);
        tabKutuphane.setVisible(false);
    }//GEN-LAST:event_lblArsivMouseClicked

    private void lblDigerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDigerMouseClicked
        Font font = lblDiger.getFont();
        Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        lblArsiv.setFont(font);
        lblKutuphane.setFont(font);
        lblDiger.setFont(kalınFont);
        tabArsiv.setVisible(false);
        tabDiger.setVisible(true);
        tabKutuphane.setVisible(false);
    }//GEN-LAST:event_lblDigerMouseClicked

    private void tblBelgelerimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBelgelerimMouseClicked
        int belgelerimindex = tblBelgelerim.getSelectedRow();
        belgekodu = (int) tblBelgelerim.getValueAt(belgelerimindex, 2);
    }//GEN-LAST:event_tblBelgelerimMouseClicked

    private void btnPDFAcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFAcActionPerformed
        try {
            PDFGoster();
        } catch (IOException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPDFAcActionPerformed

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

    public void AdminCekme() {
        try {
            String sql = "SELECT adsoyad FROM public.kullanicilar WHERE yetkituru = ?;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbRandevuKisi.addItem(rs.getString("adsoyad"));
                cbKutuphaneci.addItem(rs.getString("adsoyad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            java.util.logging.Logger.getLogger(KullaniciArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KullaniciArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KullaniciArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KullaniciArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KullaniciArayuz().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser KitapAlTarihSecici;
    private javax.swing.JButton btnKitapAl;
    private javax.swing.JButton btnPDFAc;
    private javax.swing.JButton btnRandevuAl;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbKutuphaneci;
    private javax.swing.JComboBox<String> cbRandevuKisi;
    private javax.swing.JComboBox<String> cbSaat;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblArsiv;
    private javax.swing.JLabel lblDiger;
    private javax.swing.JLabel lblKutuphane;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JList<String> lstRenk;
    private javax.swing.JPanel pnlAldigimKitaplar;
    private javax.swing.JPanel pnlBekleyenRandevu;
    private javax.swing.JPanel pnlBelgeIste;
    private javax.swing.JPanel pnlBelgelerim;
    private javax.swing.JPanel pnlKabulEdilenRandevu;
    private javax.swing.JPanel pnlKitapAlma;
    private javax.swing.JPanel pnlRandevu;
    private javax.swing.JPanel pnlRandevuAl;
    private javax.swing.JPanel pnlReddedilenRandevu;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JTabbedPane tabArsiv;
    private javax.swing.JTabbedPane tabDiger;
    private javax.swing.JTabbedPane tabKutuphane;
    private javax.swing.JTable tblBekleyenRandevu;
    private javax.swing.JTable tblBelgelerim;
    private javax.swing.JTable tblKabulRandevu;
    private javax.swing.JTable tblKitapAl;
    private javax.swing.JTable tblRetRandevu;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextArea txtRandevuAlKonu;
    private javax.swing.JTextArea txtRetSebep;
    private javax.swing.JTextField txtYayinEvi;
    // End of variables declaration//GEN-END:variables
}
