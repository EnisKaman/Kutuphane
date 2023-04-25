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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    

    public void TabloVerileri() {
        try {
            String sql = "SELECT * FROM public.kitaplik;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Litap Adı");
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
            
            tblKitaplar.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AdminArayuzu() {
        initComponents();
    }

    public AdminArayuzu(String email, String sifre, int tema) {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;
        
        
        initComponents();
        TabloVerileri();
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
        pnlUyeIslemleri = new javax.swing.JPanel();
        pnlRandevular = new javax.swing.JPanel();
        pnlDuyurular = new javax.swing.JPanel();
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

        javax.swing.GroupLayout pnlKitaplarLayout = new javax.swing.GroupLayout(pnlKitaplar);
        pnlKitaplar.setLayout(pnlKitaplarLayout);
        pnlKitaplarLayout.setHorizontalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlKitaplarLayout.setVerticalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlRandevularLayout = new javax.swing.GroupLayout(pnlRandevular);
        pnlRandevular.setLayout(pnlRandevularLayout);
        pnlRandevularLayout.setHorizontalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        pnlRandevularLayout.setVerticalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
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

    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        jTabbedPane1.setVisible(true);
    }//GEN-LAST:event_pnlSettingsKapatMouseClicked

    private void lstRenkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstRenkMouseClicked
        RenkSecmeListe();
    }//GEN-LAST:event_lstRenkMouseClicked

    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        jTabbedPane1.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_lblSettingsMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TemaRengi();
    }//GEN-LAST:event_formWindowActivated

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            String sqllog = "INSERT INTO public.kullanici_log(adsoyad, email, ip, islem)VALUES ((Select adsoyad from public.kullanicilar WHERE email='" + email + "'),'" + email + "', '" + ip.getHostAddress() + "','Çıkış');";
            pst = conn.prepareStatement(sqllog);
            rs = pst.executeQuery();
            if (rs.next()) {
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
                TabloVerileri();
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

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoruntuSec;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbKitapTuru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapResmi;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JList<String> lstRenk;
    private javax.swing.JPanel pnlDuyurular;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlKitapOnaylama;
    private javax.swing.JPanel pnlKitaplar;
    private javax.swing.JPanel pnlRandevular;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JPanel pnlUyeIslemleri;
    private javax.swing.JTable tblKitaplar;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
