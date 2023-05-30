/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kutuphane;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Enis
 */
public class KullaniciKitapIade extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    byte[] imagedata = null;
    static int kitapid;
    boolean goruntudurum = false;
    String dosyayolu = null;
    String dosyaadi = null;
    File dosya;
    FileInputStream fis;
    String bildirim;
    KullaniciArayuz kul;
    String email;

    public KullaniciKitapIade() {
        initComponents();
    }

    public KullaniciKitapIade(String email, int kitapkodu, String kitapadi, String yazaradi, String yayinevi, String kitapturu, String kitapozeti, byte[] imagedata, String gerigetirme, String bildirim, KullaniciArayuz kul) {
        initComponents();
        this.kul = kul;
        this.email = email;
        txtKitapKodu.setText(String.valueOf(kitapkodu));
        txtKitapAdi.setText(kitapadi);
        txtYazarAdSoyad.setText(yazaradi);
        txtYayinEvi.setText(yayinevi);
        txtKitapTuru.setText(kitapturu);
        txtGeriGetirme.setText(gerigetirme);
        this.imagedata = imagedata;
        InputStream input = new ByteArrayInputStream(imagedata);
        BufferedImage image = null;
        try {
            image = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(ResimKoyma.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon icon = new ImageIcon(image);
        Image resim = icon.getImage().getScaledInstance(pnlResim.getWidth(), pnlResim.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon boyutlanmisresim = new ImageIcon(resim);
        lblResim.setIcon(boyutlanmisresim);
        this.bildirim = bildirim;
    }

    public KullaniciKitapIade(int kitapkodu, String kitapadi, String yazaradi, String yayinevi, String kitapturu, String kitapozeti, byte[] imagedata, String gerigetirme) {
        initComponents();
        txtKitapKodu.setText(String.valueOf(kitapkodu));
        txtKitapAdi.setText(kitapadi);
        txtYazarAdSoyad.setText(yazaradi);
        txtYayinEvi.setText(yayinevi);
        txtKitapTuru.setText(kitapturu);
        txtGeriGetirme.setText(gerigetirme);
        this.imagedata = imagedata;
        InputStream input = new ByteArrayInputStream(imagedata);
        BufferedImage image = null;
        try {
            image = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(ResimKoyma.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon icon = new ImageIcon(image);
        Image resim = icon.getImage().getScaledInstance(pnlResim.getWidth(), pnlResim.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon boyutlanmisresim = new ImageIcon(resim);
        lblResim.setIcon(boyutlanmisresim);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlKitapEkleme = new javax.swing.JPanel();
        lblYazarAdSoyad = new javax.swing.JLabel();
        txtYazarAdSoyad = new javax.swing.JTextField();
        lblKitapAdi = new javax.swing.JLabel();
        lblYayinEvi = new javax.swing.JLabel();
        lblKitapTuru = new javax.swing.JLabel();
        txtKitapAdi = new javax.swing.JTextField();
        txtYayinEvi = new javax.swing.JTextField();
        pnlResim = new javax.swing.JPanel();
        lblResim = new javax.swing.JLabel();
        lblKitapTuru1 = new javax.swing.JLabel();
        txtKitapTuru = new javax.swing.JTextField();
        txtGeriGetirme = new javax.swing.JTextField();
        lblYazarAdSoyad1 = new javax.swing.JLabel();
        txtKitapKodu = new javax.swing.JTextField();
        btnIadeEt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblYazarAdSoyad.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYazarAdSoyad.setText("Yazar Ad Soyad");

        txtYazarAdSoyad.setEnabled(false);
        txtYazarAdSoyad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtYazarAdSoyadtxtPropertyChange(evt);
            }
        });

        lblKitapAdi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapAdi.setText("Kitap Adı");

        lblYayinEvi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYayinEvi.setText("Yayın Evi");

        lblKitapTuru.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru.setText("Kitap Türü");

        txtKitapAdi.setEnabled(false);

        txtYayinEvi.setEnabled(false);

        pnlResim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlResim.add(lblResim, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 440));

        lblKitapTuru1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru1.setText("Geri Getirme Tarihi");

        txtKitapTuru.setEnabled(false);

        txtGeriGetirme.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGeriGetirme.setEnabled(false);

        lblYazarAdSoyad1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYazarAdSoyad1.setText("Kitap Kodu");

        txtKitapKodu.setEnabled(false);
        txtKitapKodu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtKitapKodutxtPropertyChange(evt);
            }
        });

        btnIadeEt.setText("İade Et");
        btnIadeEt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIadeEtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKitapEklemeLayout = new javax.swing.GroupLayout(pnlKitapEkleme);
        pnlKitapEkleme.setLayout(pnlKitapEklemeLayout);
        pnlKitapEklemeLayout.setHorizontalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKitapTuru)
                            .addComponent(lblYazarAdSoyad1)
                            .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblKitapAdi)
                                .addComponent(lblYayinEvi))
                            .addComponent(lblYazarAdSoyad))
                        .addGap(39, 39, 39)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtKitapKodu, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYayinEvi, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addComponent(lblKitapTuru1)
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIadeEt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGeriGetirme, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(pnlResim, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlKitapEklemeLayout.setVerticalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(pnlResim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKitapKodu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYazarAdSoyad1))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYazarAdSoyad))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKitapAdi))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtYayinEvi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYayinEvi))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKitapTuru))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGeriGetirme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKitapTuru1))
                        .addGap(60, 60, 60)
                        .addComponent(btnIadeEt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(pnlKitapEkleme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 788, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtYazarAdSoyadtxtPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtYazarAdSoyadtxtPropertyChange

    }//GEN-LAST:event_txtYazarAdSoyadtxtPropertyChange

    private void txtKitapKodutxtPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtKitapKodutxtPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitapKodutxtPropertyChange

    private void btnIadeEtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIadeEtActionPerformed
        try {
            String sqldelete = "DELETE FROM public.kitap_al_kabul WHERE kitap_al_kabul_kitap_kodu = ?;";
            pst = conn.prepareStatement(sqldelete);
            pst.setString(1, txtKitapKodu.getText());
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                String sqldeletebildirim = "DELETE FROM public.bildirim WHERE bildirim_aciklama = ?;";
                pst = conn.prepareStatement(sqldeletebildirim);
                pst.setString(1, bildirim);
                int cevap = pst.executeUpdate();
                if (cevap == 1) {
                    String sqldeletekitapbildirim = "DELETE FROM public.kitap_bildirim WHERE kitap_bildirim_ilgili_email = ? AND kitap_bildirim_kitap_kodu = ?;";
                    pst = conn.prepareStatement(sqldeletekitapbildirim);
                    pst.setString(1, email);
                    pst.setInt(2, Integer.parseInt(txtKitapKodu.getText()));
                    int cevap3 = pst.executeUpdate();
                    if (cevap3 == 1) {
                        String sqlupdate = "UPDATE public.kitaplik SET kitap_durum=? WHERE kitap_kodu = ?;";
                        pst = conn.prepareStatement(sqlupdate);
                        pst.setString(1, "Envanterde");
                        pst.setInt(2, Integer.parseInt(txtKitapKodu.getText()));
                        int cevap2 = pst.executeUpdate();
                        if (cevap2 == 1) {
                            JOptionPane.showMessageDialog(null, "Kitap Başarıyla İade Edildi", "Kitap İade", JOptionPane.PLAIN_MESSAGE);
                            kul.BildirimSayisi();
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            //Logger.getLogger(KullaniciKitapIade.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnIadeEtActionPerformed

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
            java.util.logging.Logger.getLogger(KullaniciKitapIade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KullaniciKitapIade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KullaniciKitapIade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KullaniciKitapIade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KullaniciKitapIade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIadeEt;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblKitapTuru1;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JLabel lblYazarAdSoyad1;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JTextField txtGeriGetirme;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextField txtKitapTuru;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
