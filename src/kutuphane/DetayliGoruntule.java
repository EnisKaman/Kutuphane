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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DetayliGoruntule extends javax.swing.JFrame {

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
    
    public DetayliGoruntule() {
        initComponents();
    }

    public DetayliGoruntule(int kitapkodu, String kitapadi, String yazaradi, String yayinevi, String kitapturu, String kitapozeti,  byte[] imagedata, int toplamkitapsayisi, int eldeolankitapsayisi) {
        initComponents();
        txtKitapKodu.setText(String.valueOf(kitapkodu));
        txtKitapAdi.setText(kitapadi);
        txtYazarAdSoyad.setText(yazaradi);
        txtYayinEvi.setText(yayinevi);
        txtKitapTuru.setText(kitapturu);
        txtKitapOzeti.setText(kitapozeti);
        txtToplamKitap.setText(String.valueOf(toplamkitapsayisi));
        txtEldeOlanSayisi.setText(String.valueOf(eldeolankitapsayisi));
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
        pnlResim = new javax.swing.JPanel();
        lblResim = new javax.swing.JLabel();
        lblKitapTuru1 = new javax.swing.JLabel();
        lblKitapTuru2 = new javax.swing.JLabel();
        txtEldeOlanSayisi = new javax.swing.JTextField();
        txtKitapTuru = new javax.swing.JTextField();
        txtToplamKitap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKitapOzeti = new javax.swing.JTextArea();
        lblKitapTuru3 = new javax.swing.JLabel();

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

        lblKitapKodu.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapKodu.setText("Kitap Kodu");

        lblKitapAdi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapAdi.setText("Kitap Adı");

        lblYayinEvi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYayinEvi.setText("Yayın Evi");

        lblKitapTuru.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru.setText("Kitap Türü");

        txtKitapAdi.setEnabled(false);

        txtKitapKodu.setEnabled(false);

        txtYayinEvi.setEnabled(false);

        pnlResim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlResim.add(lblResim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 260));

        lblKitapTuru1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru1.setText("Toplam Kitap Sayısı");

        lblKitapTuru2.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru2.setText("Elde Olan Kitap Sayısı");

        txtEldeOlanSayisi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEldeOlanSayisi.setEnabled(false);

        txtKitapTuru.setEnabled(false);

        txtToplamKitap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtToplamKitap.setEnabled(false);

        txtKitapOzeti.setColumns(20);
        txtKitapOzeti.setRows(5);
        txtKitapOzeti.setEnabled(false);
        jScrollPane1.setViewportView(txtKitapOzeti);

        lblKitapTuru3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapTuru3.setText("Kitap Özeti");

        javax.swing.GroupLayout pnlKitapEklemeLayout = new javax.swing.GroupLayout(pnlKitapEkleme);
        pnlKitapEkleme.setLayout(pnlKitapEklemeLayout);
        pnlKitapEklemeLayout.setHorizontalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKitapTuru3)
                    .addComponent(lblKitapAdi)
                    .addComponent(lblKitapKodu)
                    .addComponent(lblYayinEvi)
                    .addComponent(lblKitapTuru)
                    .addComponent(lblYazarAdSoyad)
                    .addComponent(lblKitapTuru1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYayinEvi)
                            .addComponent(txtKitapKodu)
                            .addComponent(txtKitapAdi)
                            .addComponent(txtYazarAdSoyad)
                            .addComponent(txtKitapTuru)
                            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                                .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblKitapTuru2)
                                .addGap(18, 18, 18)
                                .addComponent(txtEldeOlanSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, Short.MAX_VALUE)))
                        .addGap(34, 34, 34)
                        .addComponent(pnlResim, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(19, 19, 19))
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
                            .addComponent(txtKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKitapTuru1)
                            .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKitapTuru2)
                            .addComponent(txtEldeOlanSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addComponent(pnlResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKitapTuru3))
                .addGap(82, 82, 82))
        );

        getContentPane().add(pnlKitapEkleme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 788, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtYazarAdSoyadtxtPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtYazarAdSoyadtxtPropertyChange

    }//GEN-LAST:event_txtYazarAdSoyadtxtPropertyChange

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
            java.util.logging.Logger.getLogger(DetayliGoruntule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetayliGoruntule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetayliGoruntule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetayliGoruntule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetayliGoruntule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblKitapTuru1;
    private javax.swing.JLabel lblKitapTuru2;
    private javax.swing.JLabel lblKitapTuru3;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JTextField txtEldeOlanSayisi;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextArea txtKitapOzeti;
    private javax.swing.JTextField txtKitapTuru;
    private javax.swing.JTextField txtToplamKitap;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
