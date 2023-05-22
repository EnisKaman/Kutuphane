package kutuphane;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class KitapGuncelleme extends javax.swing.JFrame {

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
    AdminArayuzu adm;

    public KitapGuncelleme() {
        initComponents();
    }

    public KitapGuncelleme(int kitapid, int kitapkodu, String kitapadi, String yazaradi, String yayinevi, String kitapturu, byte[] imagedata, AdminArayuzu adm) {
        initComponents();
        this.adm = adm;
        this.kitapid = kitapid;
        txtKitapKodu.setText(String.valueOf(kitapkodu));
        txtKitapAdi.setText(kitapadi);
        txtYazarAdSoyad.setText(yazaradi);
        txtYayinEvi.setText(yayinevi);
        cbKitapTuru.setSelectedItem(kitapturu);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblYazarAdSoyad.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYazarAdSoyad.setText("Yazar Ad Soyad");

        txtYazarAdSoyad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtPropertyChange(evt);
            }
        });

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
        pnlResim.add(lblResim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 250));

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
                .addGap(21, 21, 21)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKitapAdi)
                    .addComponent(lblKitapKodu)
                    .addComponent(lblYayinEvi)
                    .addComponent(lblKitapTuru)
                    .addComponent(lblKitapResmi)
                    .addComponent(lblYazarAdSoyad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGoruntuSec)
                            .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbKitapTuru, javax.swing.GroupLayout.Alignment.LEADING, 0, 315, Short.MAX_VALUE)
                                .addComponent(txtYayinEvi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtKitapKodu, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtKitapAdi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(12, 12, 12)
                        .addComponent(pnlResim, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlKitapEklemeLayout.setVerticalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
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
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKitapResmi)
                            .addComponent(btnGoruntuSec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );

        getContentPane().add(pnlKitapEkleme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 788, 340));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoruntuSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoruntuSecActionPerformed
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.showOpenDialog(null);
        dosya = chooser.getSelectedFile();
        String eskidosyaadi = dosya.getAbsolutePath();
        dosyaadi = eskidosyaadi.replace("\\", "\\\\");
        ImageIcon resim = new ImageIcon(dosyaadi);
        Image image = resim.getImage().getScaledInstance(pnlResim.getWidth(), pnlResim.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon boyutlanmisresim = new ImageIcon(image);
        lblResim.setIcon(boyutlanmisresim);
        goruntudurum = true;
    }//GEN-LAST:event_btnGoruntuSecActionPerformed

    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        try {
            
            String sql = "UPDATE public.kitaplik SET kitap_kodu=?, kitap_adi=?, kitap_resim=?, yazar_adsoyad=?, yayin_evi=?, kitap_turu=? WHERE kitap_id = ?;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtKitapKodu.getText()));
            pst.setString(2, txtKitapAdi.getText());
            if (goruntudurum == false) {
                pst.setBytes(3, imagedata);
            } else if (goruntudurum == true) {
                fis = new FileInputStream(dosya);
                pst.setBinaryStream(3, fis, dosya.length());
            }
            pst.setString(4, txtYazarAdSoyad.getText());
            pst.setString(5, txtYayinEvi.getText());
            pst.setString(6, cbKitapTuru.getSelectedItem().toString());
            pst.setInt(7, kitapid);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Güncellem Başarılı");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KitapGuncelleme.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KitapGuncelleme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKaydetActionPerformed

    private void txtPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtPropertyChange

    }//GEN-LAST:event_txtPropertyChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        adm.GuncellemeFormKapatildi();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(KitapGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitapGuncelleme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoruntuSec;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JComboBox<String> cbKitapTuru;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapResmi;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
