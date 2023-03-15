/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kutuphane;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Enis
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        logoGizle.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSifre = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        chkBeniHatirla = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        btnGiris = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        chkSifreGoster = new javax.swing.JCheckBox();
        logoGoster = new javax.swing.JLabel();
        logoGizle = new javax.swing.JLabel();
        pnlLoginLogo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pnlHesapOlustur = new javax.swing.JPanel();
        pnlAdSoyad = new javax.swing.JPanel();
        lblAd = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAdSoyad = new javax.swing.JTextArea();
        pnlEmail = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEmailKayit = new javax.swing.JTextArea();
        lblEmail = new javax.swing.JLabel();
        pnlTelefon = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTelefon = new javax.swing.JTextArea();
        lblTelefon = new javax.swing.JLabel();
        pnlSifre = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSifreTekrar = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtSifreKayit = new javax.swing.JTextArea();
        lblSifre = new javax.swing.JLabel();
        lblSifreTekrar = new javax.swing.JLabel();
        pnlGuvenlik = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCevap = new javax.swing.JTextArea();
        lblGuvenlikSorusu = new javax.swing.JLabel();
        lblGuvenlikCevap = new javax.swing.JLabel();
        cbGuvenlik = new javax.swing.JComboBox<>();
        btnKayitOl = new javax.swing.JButton();
        pnlSifremiUnuttum = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuDarkMode = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setPreferredSize(new java.awt.Dimension(400, 477));

        jPanel4.setMinimumSize(new java.awt.Dimension(150, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 100));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Hoşgeldiniz");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pnlLogin.add(jPanel4);

        jPanel3.setMinimumSize(new java.awt.Dimension(150, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 377));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Email Adresi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Şifre");

        txtSifre.setEchoChar('*');

        chkBeniHatirla.setText("Beni Hatırla");

        jLabel4.setForeground(new java.awt.Color(255, 102, 153));
        jLabel4.setText("Şifremi Unuttum");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(chkBeniHatirla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkBeniHatirla)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGiris.setText("Giriş Yap");

        jLabel5.setForeground(new java.awt.Color(51, 204, 255));
        jLabel5.setText("Hesabınız Yok Mu?");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        chkSifreGoster.setText("Göster");
        chkSifreGoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSifreGosterActionPerformed(evt);
            }
        });

        logoGoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/eye32.png"))); // NOI18N
        logoGoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoGosterMouseClicked(evt);
            }
        });

        logoGizle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/hide32.png"))); // NOI18N
        logoGizle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoGizleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkSifreGoster)
                .addGap(38, 38, 38))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoGoster)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoGizle))
                    .addComponent(jLabel2))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logoGoster))
                    .addComponent(logoGizle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(chkSifreGoster)
                .addGap(61, 61, 61))
        );

        jLabel2.getAccessibleContext().setAccessibleName("lblEmail");

        pnlLogin.add(jPanel3);

        pnlLoginLogo.setForeground(new java.awt.Color(204, 204, 204));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/Kitap.png"))); // NOI18N

        javax.swing.GroupLayout pnlLoginLogoLayout = new javax.swing.GroupLayout(pnlLoginLogo);
        pnlLoginLogo.setLayout(pnlLoginLogoLayout);
        pnlLoginLogoLayout.setHorizontalGroup(
            pnlLoginLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLogoLayout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(109, 109, 109))
        );
        pnlLoginLogoLayout.setVerticalGroup(
            pnlLoginLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLogoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHesapOlustur.setForeground(new java.awt.Color(204, 204, 204));
        pnlHesapOlustur.setPreferredSize(new java.awt.Dimension(512, 477));

        pnlAdSoyad.setPreferredSize(new java.awt.Dimension(400, 100));

        lblAd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAd.setText("Ad Soyad");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtAdSoyad.setColumns(20);
        txtAdSoyad.setRows(5);
        jScrollPane2.setViewportView(txtAdSoyad);

        javax.swing.GroupLayout pnlAdSoyadLayout = new javax.swing.GroupLayout(pnlAdSoyad);
        pnlAdSoyad.setLayout(pnlAdSoyadLayout);
        pnlAdSoyadLayout.setHorizontalGroup(
            pnlAdSoyadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdSoyadLayout.createSequentialGroup()
                .addGroup(pnlAdSoyadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAdSoyadLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAdSoyadLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(lblAd)))
                .addGap(74, 74, 74))
        );
        pnlAdSoyadLayout.setVerticalGroup(
            pnlAdSoyadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdSoyadLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblAd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlEmail.setPreferredSize(new java.awt.Dimension(400, 100));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtEmailKayit.setColumns(20);
        txtEmailKayit.setRows(5);
        jScrollPane3.setViewportView(txtEmailKayit);

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setText("Email");

        javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(pnlEmail);
        pnlEmail.setLayout(pnlEmailLayout);
        pnlEmailLayout.setHorizontalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addGroup(pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEmailLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEmailLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(lblEmail)))
                .addGap(74, 74, 74))
        );
        pnlEmailLayout.setVerticalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTelefon.setPreferredSize(new java.awt.Dimension(400, 100));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtTelefon.setColumns(20);
        txtTelefon.setRows(5);
        jScrollPane4.setViewportView(txtTelefon);

        lblTelefon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelefon.setText("Telefon Numarası");

        javax.swing.GroupLayout pnlTelefonLayout = new javax.swing.GroupLayout(pnlTelefon);
        pnlTelefon.setLayout(pnlTelefonLayout);
        pnlTelefonLayout.setHorizontalGroup(
            pnlTelefonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTelefonLayout.createSequentialGroup()
                .addGroup(pnlTelefonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTelefonLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTelefonLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(lblTelefon)))
                .addGap(74, 74, 74))
        );
        pnlTelefonLayout.setVerticalGroup(
            pnlTelefonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTelefonLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblTelefon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlSifre.setPreferredSize(new java.awt.Dimension(400, 100));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtSifreTekrar.setColumns(20);
        txtSifreTekrar.setRows(5);
        jScrollPane5.setViewportView(txtSifreTekrar);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtSifreKayit.setColumns(20);
        txtSifreKayit.setRows(5);
        jScrollPane6.setViewportView(txtSifreKayit);

        lblSifre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifre.setText("Şifre");

        lblSifreTekrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifreTekrar.setText("Şifre (Tekrar)");

        javax.swing.GroupLayout pnlSifreLayout = new javax.swing.GroupLayout(pnlSifre);
        pnlSifre.setLayout(pnlSifreLayout);
        pnlSifreLayout.setHorizontalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(lblSifre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSifreTekrar)
                .addGap(113, 113, 113))
        );
        pnlSifreLayout.setVerticalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSifre)
                    .addComponent(lblSifreTekrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlGuvenlik.setPreferredSize(new java.awt.Dimension(400, 100));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtCevap.setColumns(20);
        txtCevap.setRows(5);
        jScrollPane7.setViewportView(txtCevap);

        lblGuvenlikSorusu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikSorusu.setText("Güvenlik Sorusu");

        lblGuvenlikCevap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikCevap.setText("Cevap");

        cbGuvenlik.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlGuvenlikLayout = new javax.swing.GroupLayout(pnlGuvenlik);
        pnlGuvenlik.setLayout(pnlGuvenlikLayout);
        pnlGuvenlikLayout.setHorizontalGroup(
            pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbGuvenlik, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(lblGuvenlikSorusu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGuvenlikCevap)
                .addGap(134, 134, 134))
        );
        pnlGuvenlikLayout.setVerticalGroup(
            pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGuvenlikSorusu)
                    .addComponent(lblGuvenlikCevap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbGuvenlik, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnKayitOl.setText("Kayıt Ol");
        btnKayitOl.setPreferredSize(new java.awt.Dimension(250, 30));

        javax.swing.GroupLayout pnlHesapOlusturLayout = new javax.swing.GroupLayout(pnlHesapOlustur);
        pnlHesapOlustur.setLayout(pnlHesapOlusturLayout);
        pnlHesapOlusturLayout.setHorizontalGroup(
            pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHesapOlusturLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
            .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                        .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSifre, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                            .addComponent(pnlGuvenlik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlHesapOlusturLayout.setVerticalGroup(
            pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGuvenlik, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSifremiUnuttum.setForeground(new java.awt.Color(204, 204, 204));
        pnlSifremiUnuttum.setPreferredSize(new java.awt.Dimension(512, 477));

        javax.swing.GroupLayout pnlSifremiUnuttumLayout = new javax.swing.GroupLayout(pnlSifremiUnuttum);
        pnlSifremiUnuttum.setLayout(pnlSifremiUnuttumLayout);
        pnlSifremiUnuttumLayout.setHorizontalGroup(
            pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        pnlSifremiUnuttumLayout.setVerticalGroup(
            pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayarlar");

        menuDarkMode.setText("Karanlık Mod");
        menuDarkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDarkModeActionPerformed(evt);
            }
        });
        jMenu3.add(menuDarkMode);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlHesapOlustur, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLoginLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHesapOlustur, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLoginLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        //ŞİFRE GÖSTERME BAŞLANGIÇ
    
    //ŞİFRE GÖSTERME BİTİŞ
// DARK MOD TIKLAMA BAŞLANGIÇ
    private void menuDarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDarkModeActionPerformed
        if (menuDarkMode.isSelected()) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });
        }
    }//GEN-LAST:event_menuDarkModeActionPerformed
// DARK MOD TIKLAMA BİTİŞ 
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
         KayitOl kayitol = new KayitOl();
        kayitol.setSize(579, 482);
        kayitol.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void chkSifreGosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSifreGosterActionPerformed
        if (chkSifreGoster.isSelected()) {
            
        } else {
            
        }
    }//GEN-LAST:event_chkSifreGosterActionPerformed

    private void logoGosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGosterMouseClicked
        txtSifre.setEchoChar((char)0);
        logoGoster.setVisible(false);
        logoGizle.setLocation(logoGoster.getLocation());
        logoGizle.setVisible(true);
    }//GEN-LAST:event_logoGosterMouseClicked

    private void logoGizleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGizleMouseClicked
        txtSifre.setEchoChar('*');
        logoGizle.setVisible(false);
        logoGoster.setVisible(true);
    }//GEN-LAST:event_logoGizleMouseClicked
                         
    
                   // HESAP OLUŞTUR TIKLAMA BİTİŞ
   
                    // MAİN BAŞLANGIÇ
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
                    // MAİN BİTİŞ
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiris;
    private javax.swing.JButton btnKayitOl;
    private javax.swing.JComboBox<String> cbGuvenlik;
    private javax.swing.JCheckBox chkBeniHatirla;
    private javax.swing.JCheckBox chkSifreGoster;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAd;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGuvenlikCevap;
    private javax.swing.JLabel lblGuvenlikSorusu;
    private javax.swing.JLabel lblSifre;
    private javax.swing.JLabel lblSifreTekrar;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel logoGizle;
    private javax.swing.JLabel logoGoster;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JPanel pnlAdSoyad;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlGuvenlik;
    private javax.swing.JPanel pnlHesapOlustur;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlLoginLogo;
    private javax.swing.JPanel pnlSifre;
    private javax.swing.JPanel pnlSifremiUnuttum;
    private javax.swing.JPanel pnlTelefon;
    private javax.swing.JTextArea txtAdSoyad;
    private javax.swing.JTextArea txtCevap;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtEmailKayit;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JTextArea txtSifreKayit;
    private javax.swing.JTextArea txtSifreTekrar;
    private javax.swing.JTextArea txtTelefon;
    // End of variables declaration//GEN-END:variables
}
