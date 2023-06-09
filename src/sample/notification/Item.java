package sample.notification;

import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import kutuphane.AdminArayuzu;
import kutuphane.Baglanti;
import kutuphane.KullaniciArayuz;
import kutuphane.KullaniciKitapDetayliGoruntule;
import kutuphane.KullaniciKitapIade;

/**
 *
 * @author RAVEN
 */


public class Item extends javax.swing.JPanel {
    
    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String kitapadi;
    String yayinevi;
    int kitapkodu;
    KullaniciArayuz kul; 
    String email;
    
    public Item(String name, String des, String kitapadi, String yayinevi, String bildirimturu,String duyurukonu, int kitapkodu, KullaniciArayuz kul, String email) {
        initComponents();
        this.kul=kul;
        this.email=email;
        if (bildirimturu.equalsIgnoreCase("Kitap")) {
            lbName.setText(name);
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        if (bildirimturu.equalsIgnoreCase("Duyuru")) {
            lbName.setText(duyurukonu);
            lbDes.setText("");
        }
        txtDes.setText(des);
        this.kitapadi = kitapadi;
        this.yayinevi = yayinevi;
        this.kitapkodu = kitapkodu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbDes = new javax.swing.JLabel();
        txtDes = new javax.swing.JTextPane();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lbName.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        lbName.setForeground(new java.awt.Color(106, 106, 106));
        lbName.setText("Name");

        lbDes.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        lbDes.setForeground(new java.awt.Color(134, 134, 134));
        lbDes.setText("İade Etmek İçin Tıklayın");

        txtDes.setBackground(new java.awt.Color(255, 255, 255));
        txtDes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDes.setForeground(new java.awt.Color(133, 133, 133));
        txtDes.setText("This is part of a series of short tutorials about specific elements, components, or interactions. We’ll cover the UX, the UI, and the construction inside of Sketch. Plus, there’s a freebie for you at the end!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addGap(18, 18, 18)
                        .addComponent(lbDes)
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(lbDes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (lbName.getText().equalsIgnoreCase("Kitabın Geri Getirme Tarihi Geçti")) {
            ViewKitapGonder(kitapadi, yayinevi);
        }
        
    }//GEN-LAST:event_formMouseClicked

    public void ViewKitapGonder(String kitapadi, String yayinevi) {
        try {
            String yazaradi = null;
            String kitapturu = null;
            String kitapozeti = null;
            byte[] imagedata = null;

            String sql = "Select * FROM public.kitaplik WHERE kitap_adi = ? AND yayin_evi = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapadi);
            pst.setString(2, yayinevi);
            rs = pst.executeQuery();
            if (rs.next()) {
                yazaradi = rs.getString("yazar_adsoyad");
                kitapturu = rs.getString("kitap_turu");
                kitapozeti = rs.getString("kitap_ozet");
                imagedata = rs.getBytes("kitap_resim");
            }


            KullaniciKitapIade kki = new KullaniciKitapIade(email,kitapkodu, kitapadi, yazaradi, yayinevi, kitapturu, kitapozeti, imagedata, yayinevi,txtDes.getText(), kul);
            kki.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDes;
    private javax.swing.JLabel lbName;
    private javax.swing.JTextPane txtDes;
    // End of variables declaration//GEN-END:variables
}
