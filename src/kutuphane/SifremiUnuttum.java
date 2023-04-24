/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kutuphane;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Enis
 */
public class SifremiUnuttum extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;

    public SifremiUnuttum() {
        initComponents();
        logoSifreGizle.setVisible(false);
        logoSifreTekrarGizle.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSifremiUnuttum = new javax.swing.JPanel();
        pnlEmail = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmailSifremiUnuttum = new javax.swing.JTextField();
        pnlSifre = new javax.swing.JPanel();
        lblSifre = new javax.swing.JLabel();
        lblSifreTekrar = new javax.swing.JLabel();
        txtSifreSifremiUnuttum = new javax.swing.JPasswordField();
        txtSifreTekrarSifremiUnuttum = new javax.swing.JPasswordField();
        logoSifreGoster = new javax.swing.JLabel();
        logoSifreGizle = new javax.swing.JLabel();
        logoSifreTekrarGoster = new javax.swing.JLabel();
        logoSifreTekrarGizle = new javax.swing.JLabel();
        pnlGuvenlik = new javax.swing.JPanel();
        lblGuvenlikSorusu = new javax.swing.JLabel();
        lblGuvenlikCevap = new javax.swing.JLabel();
        cbGuvenlikSifremiUnuttum = new javax.swing.JComboBox<>();
        txtCevapSifremiUnuttum = new javax.swing.JTextField();
        btnSifreDegistir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlSifremiUnuttum.setForeground(new java.awt.Color(204, 204, 204));
        pnlSifremiUnuttum.setPreferredSize(new java.awt.Dimension(512, 477));

        pnlEmail.setPreferredSize(new java.awt.Dimension(400, 100));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setText("Email");

        javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(pnlEmail);
        pnlEmail.setLayout(pnlEmailLayout);
        pnlEmailLayout.setHorizontalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEmailLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addGap(120, 120, 120))
                    .addComponent(txtEmailSifremiUnuttum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEmailLayout.setVerticalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmailSifremiUnuttum, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        pnlSifre.setPreferredSize(new java.awt.Dimension(400, 100));

        lblSifre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifre.setText("Şifre");

        lblSifreTekrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifreTekrar.setText("Şifre (Tekrar)");

        logoSifreGoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/eye32.png"))); // NOI18N
        logoSifreGoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoSifreGosterMouseClicked(evt);
            }
        });

        logoSifreGizle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/hide32.png"))); // NOI18N
        logoSifreGizle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoSifreGizleMouseClicked(evt);
            }
        });

        logoSifreTekrarGoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/eye32.png"))); // NOI18N
        logoSifreTekrarGoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoSifreTekrarGosterMouseClicked(evt);
            }
        });

        logoSifreTekrarGizle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/hide32.png"))); // NOI18N
        logoSifreTekrarGizle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoSifreTekrarGizleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSifreLayout = new javax.swing.GroupLayout(pnlSifre);
        pnlSifre.setLayout(pnlSifreLayout);
        pnlSifreLayout.setHorizontalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(lblSifre))
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(lblSifreTekrar))
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSifreLayout.createSequentialGroup()
                                .addComponent(txtSifreTekrarSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(logoSifreTekrarGoster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoSifreTekrarGizle))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSifreLayout.createSequentialGroup()
                                .addComponent(txtSifreSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(logoSifreGoster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoSifreGizle)))))
                .addGap(38, 121, Short.MAX_VALUE))
        );
        pnlSifreLayout.setVerticalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblSifre)
                .addGap(8, 8, 8)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSifreSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoSifreGoster)
                    .addComponent(logoSifreGizle))
                .addGap(14, 14, 14)
                .addComponent(lblSifreTekrar)
                .addGap(2, 2, 2)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSifreTekrarSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoSifreTekrarGoster)
                    .addComponent(logoSifreTekrarGizle)))
        );

        pnlGuvenlik.setPreferredSize(new java.awt.Dimension(400, 100));

        lblGuvenlikSorusu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikSorusu.setText("Güvenlik Sorusu");

        lblGuvenlikCevap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikCevap.setText("Cevap");

        cbGuvenlikSifremiUnuttum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "İlk okul öğretmeninizin adı nedir?", "İlk evcil hayvanınızın adı nedir?", "Doğum yeriniz neresidir?", "Anne kızlık soyadınız nedir?", "En sevdiğiniz yemek nedir?" }));

        javax.swing.GroupLayout pnlGuvenlikLayout = new javax.swing.GroupLayout(pnlGuvenlik);
        pnlGuvenlik.setLayout(pnlGuvenlikLayout);
        pnlGuvenlikLayout.setHorizontalGroup(
            pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lblGuvenlikSorusu))
                    .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblGuvenlikCevap))
                    .addGroup(pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbGuvenlikSifremiUnuttum, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCevapSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGuvenlikLayout.setVerticalGroup(
            pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGuvenlikSorusu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbGuvenlikSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGuvenlikCevap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCevapSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnSifreDegistir.setText("Şifre Değiştir");
        btnSifreDegistir.setPreferredSize(new java.awt.Dimension(256, 30));
        btnSifreDegistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSifreDegistirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSifremiUnuttumLayout = new javax.swing.GroupLayout(pnlSifremiUnuttum);
        pnlSifremiUnuttum.setLayout(pnlSifremiUnuttumLayout);
        pnlSifremiUnuttumLayout.setHorizontalGroup(
            pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSifre, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGuvenlik, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(pnlEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btnSifreDegistir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSifremiUnuttumLayout.setVerticalGroup(
            pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGuvenlik, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSifreDegistir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
// ŞİFRE GÖSTERME BİTİŞ
    private void logoSifreGosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoSifreGosterMouseClicked
        txtSifreSifremiUnuttum.setEchoChar((char) 0);
        logoSifreGoster.setVisible(false);
        logoSifreGizle.setLocation(logoSifreGoster.getLocation());
        logoSifreGizle.setVisible(true);

    }//GEN-LAST:event_logoSifreGosterMouseClicked

    private void logoSifreGizleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoSifreGizleMouseClicked
        txtSifreSifremiUnuttum.setEchoChar('*');
        logoSifreGizle.setVisible(false);
        logoSifreGoster.setVisible(true);
    }//GEN-LAST:event_logoSifreGizleMouseClicked
// ŞİFRE GÖSTERME BİTİŞ

// ŞİFRE TEKRAR GÖSTERME BAŞLANGIÇ    
    private void logoSifreTekrarGosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoSifreTekrarGosterMouseClicked
        txtSifreTekrarSifremiUnuttum.setEchoChar((char) 0);
        logoSifreTekrarGoster.setVisible(false);
        logoSifreTekrarGizle.setLocation(logoSifreTekrarGoster.getLocation());
        logoSifreTekrarGizle.setVisible(true);

    }//GEN-LAST:event_logoSifreTekrarGosterMouseClicked

    private void logoSifreTekrarGizleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoSifreTekrarGizleMouseClicked
        txtSifreTekrarSifremiUnuttum.setEchoChar('*');
        logoSifreTekrarGizle.setVisible(false);
        logoSifreTekrarGoster.setVisible(true);
    }//GEN-LAST:event_logoSifreTekrarGizleMouseClicked

    private void btnSifreDegistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSifreDegistirActionPerformed
        var email = txtEmailSifremiUnuttum.getText().trim();
        var sifre = txtSifreSifremiUnuttum.getText();
        var cevap = txtCevapSifremiUnuttum.getText().trim();
        try {
            String sql = "UPDATE public.kullanicilar SET sifre=? WHERE email=? and guvenlikcevap=?;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, sifre);
            pst.setString(2, email);
            pst.setString(3, cevap);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String sql2 = "SELECT * FROM public.kullanicilar WHERE email=? AND sifre=?;";
            pst = conn.prepareStatement(sql2);
            pst.setString(1, email);
            pst.setString(2, sifre);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showConfirmDialog(null, "Şifre Başarıyla Değiştirildi !");

            } else {
                JOptionPane.showConfirmDialog(null, "Kullanıcı Adı Ve Şifrenizi Kontrol Ediniz!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSifreDegistirActionPerformed
// ŞİFRE TEKRAR GÖSTERME BİTİŞ

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
            java.util.logging.Logger.getLogger(SifremiUnuttum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SifremiUnuttum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SifremiUnuttum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SifremiUnuttum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SifremiUnuttum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSifreDegistir;
    private javax.swing.JComboBox<String> cbGuvenlikSifremiUnuttum;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGuvenlikCevap;
    private javax.swing.JLabel lblGuvenlikSorusu;
    private javax.swing.JLabel lblSifre;
    private javax.swing.JLabel lblSifreTekrar;
    private javax.swing.JLabel logoSifreGizle;
    private javax.swing.JLabel logoSifreGoster;
    private javax.swing.JLabel logoSifreTekrarGizle;
    private javax.swing.JLabel logoSifreTekrarGoster;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlGuvenlik;
    private javax.swing.JPanel pnlSifre;
    private javax.swing.JPanel pnlSifremiUnuttum;
    private javax.swing.JTextField txtCevapSifremiUnuttum;
    private javax.swing.JTextField txtEmailSifremiUnuttum;
    private javax.swing.JPasswordField txtSifreSifremiUnuttum;
    private javax.swing.JPasswordField txtSifreTekrarSifremiUnuttum;
    // End of variables declaration//GEN-END:variables
}
