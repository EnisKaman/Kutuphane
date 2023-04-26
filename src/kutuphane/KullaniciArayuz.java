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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class KullaniciArayuz extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String email = null;
    String sifre = null;
    int tema = 0;
    String tarih;
    String saat;

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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlKitapAlma = new javax.swing.JPanel();
        pnlAldigimKitaplar = new javax.swing.JPanel();
        pnlRandevu = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlRandevuAl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRandevuAlKonu = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        cbRandevuKisi = new javax.swing.JComboBox<>();
        cbSaat = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRandevuAl = new javax.swing.JButton();
        pnlRandevuGecmisi = new javax.swing.JPanel();
        lblSettings = new javax.swing.JLabel();
        pnlSettings = new javax.swing.JPanel();
        pnlSettingsKapat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRenk = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSettingsKaydet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pnlKitapAlmaLayout = new javax.swing.GroupLayout(pnlKitapAlma);
        pnlKitapAlma.setLayout(pnlKitapAlmaLayout);
        pnlKitapAlmaLayout.setHorizontalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlKitapAlmaLayout.setVerticalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kitap Alma", pnlKitapAlma);

        javax.swing.GroupLayout pnlAldigimKitaplarLayout = new javax.swing.GroupLayout(pnlAldigimKitaplar);
        pnlAldigimKitaplar.setLayout(pnlAldigimKitaplarLayout);
        pnlAldigimKitaplarLayout.setHorizontalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlAldigimKitaplarLayout.setVerticalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Aldığım Kitaplar", pnlAldigimKitaplar);

        txtRandevuAlKonu.setColumns(20);
        txtRandevuAlKonu.setRows(5);
        jScrollPane2.setViewportView(txtRandevuAlKonu);

        jLabel2.setText("Tarih Seçiniz");

        jLabel3.setText("Saat Seçiniz");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

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

        javax.swing.GroupLayout pnlRandevuAlLayout = new javax.swing.GroupLayout(pnlRandevuAl);
        pnlRandevuAl.setLayout(pnlRandevuAlLayout);
        pnlRandevuAlLayout.setHorizontalGroup(
            pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevuAlLayout.createSequentialGroup()
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2)))
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(170, 170, 170)
                                .addComponent(jLabel4))
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbSaat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(cbRandevuKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevuAlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
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
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbRandevuKisi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(cbSaat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Randevu Al", pnlRandevuAl);

        javax.swing.GroupLayout pnlRandevuGecmisiLayout = new javax.swing.GroupLayout(pnlRandevuGecmisi);
        pnlRandevuGecmisi.setLayout(pnlRandevuGecmisiLayout);
        pnlRandevuGecmisiLayout.setHorizontalGroup(
            pnlRandevuGecmisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlRandevuGecmisiLayout.setVerticalGroup(
            pnlRandevuGecmisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Randevu Geçmişi", pnlRandevuGecmisi);

        javax.swing.GroupLayout pnlRandevuLayout = new javax.swing.GroupLayout(pnlRandevu);
        pnlRandevu.setLayout(pnlRandevuLayout);
        pnlRandevuLayout.setHorizontalGroup(
            pnlRandevuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        pnlRandevuLayout.setVerticalGroup(
            pnlRandevuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Randevu", pnlRandevu);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 530));

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

        getContentPane().add(pnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        jTabbedPane1.setVisible(true);
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
        jTabbedPane1.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_lblSettingsMouseClicked

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            tarih = format.format(date);
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void cbSaatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSaatActionPerformed
        saat = cbSaat.getSelectedItem().toString();
        System.out.println("" + tarih + " " + saat + "");
    }//GEN-LAST:event_cbSaatActionPerformed

    private void btnRandevuAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandevuAlActionPerformed
        try {
            String randevuverenkisiadsoyad = cbRandevuKisi.getSelectedItem().toString();
            String randevuverenkisiemail = "(Select email from public.kullanicilar where adsoyad = ?;)";
            String randevuisteyenkisiadsoyad = "(Select adsoyad from public.kullanicilar where email = ?;)";
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
            pst.setString(1, "admin");
            rs = pst.executeQuery();
            if (rs.next()) {
                cbRandevuKisi.addItem(rs.getString("adsoyad"));
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
    private javax.swing.JButton btnRandevuAl;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbRandevuKisi;
    private javax.swing.JComboBox<String> cbSaat;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JList<String> lstRenk;
    private javax.swing.JPanel pnlAldigimKitaplar;
    private javax.swing.JPanel pnlKitapAlma;
    private javax.swing.JPanel pnlRandevu;
    private javax.swing.JPanel pnlRandevuAl;
    private javax.swing.JPanel pnlRandevuGecmisi;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JTextArea txtRandevuAlKonu;
    // End of variables declaration//GEN-END:variables
}
