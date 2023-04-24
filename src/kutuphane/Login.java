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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Enis
 */
public class Login extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String email = "";
    String sifre = "";

    File file = new File("C:/Users/ekmn2/OneDrive/Belgeler/New Folder/Kutuphane/src/kutuphane/BeniHatirla.txt");
    FileWriter writer;
    FileReader fileReader = new FileReader(file);
    String line;

    BufferedReader br = new BufferedReader(fileReader);

    public Login() throws IOException {
        this.writer = new FileWriter(file);
        initComponents();
        logoGizle.setVisible(false);
        logoGizleKayitOl.setVisible(false);
        logoSifreGizle.setVisible(false);
        logoSifreTekrarGizle.setVisible(false);
        pnlHesapOlustur.setVisible(false);
        pnlSifremiUnuttum.setVisible(false);

        while ((line = br.readLine()) != null) {
            txtEmail.setText(line);
        }
        br.close();
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
        lblSifre = new javax.swing.JLabel();
        lblSifreTekrar = new javax.swing.JLabel();
        txtSifreKayitOl = new javax.swing.JPasswordField();
        txtSifreTekrarKayitOl = new javax.swing.JPasswordField();
        logoGosterKayitOl = new javax.swing.JLabel();
        logoGizleKayitOl = new javax.swing.JLabel();
        pnlGuvenlik = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCevap = new javax.swing.JTextArea();
        lblGuvenlikSorusu = new javax.swing.JLabel();
        lblGuvenlikCevap = new javax.swing.JLabel();
        cbGuvenlik = new javax.swing.JComboBox<>();
        btnKayitOl = new javax.swing.JButton();
        lblHesapOlusturKapat = new javax.swing.JLabel();
        pnlSifremiUnuttum = new javax.swing.JPanel();
        pnlEmail1 = new javax.swing.JPanel();
        lblEmail1 = new javax.swing.JLabel();
        txtEmailSifremiUnuttum = new javax.swing.JTextField();
        lblSifremiUnuttumKapat = new javax.swing.JLabel();
        pnlSifre1 = new javax.swing.JPanel();
        lblSifre1 = new javax.swing.JLabel();
        lblSifreTekrar1 = new javax.swing.JLabel();
        txtSifreSifremiUnuttum = new javax.swing.JPasswordField();
        txtSifreTekrarSifremiUnuttum = new javax.swing.JPasswordField();
        logoSifreGoster = new javax.swing.JLabel();
        logoSifreGizle = new javax.swing.JLabel();
        logoSifreTekrarGoster = new javax.swing.JLabel();
        logoSifreTekrarGizle = new javax.swing.JLabel();
        pnlGuvenlik1 = new javax.swing.JPanel();
        lblGuvenlikSorusu1 = new javax.swing.JLabel();
        lblGuvenlikCevap1 = new javax.swing.JLabel();
        cbGuvenlikSifremiUnuttum = new javax.swing.JComboBox<>();
        txtCevapSifremiUnuttum = new javax.swing.JTextField();
        btnSifreDegistir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuDarkMode = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

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
        btnGiris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirisActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(51, 204, 255));
        jLabel5.setText("Hesabınız Yok Mu?");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoGoster)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoGizle))
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel5)))
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
                    .addComponent(logoGoster)
                    .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoGizle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(109, Short.MAX_VALUE))
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
                .addContainerGap(172, Short.MAX_VALUE)
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

        lblSifre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifre.setText("Şifre");

        lblSifreTekrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifreTekrar.setText("Şifre (Tekrar)");

        txtSifreKayitOl.setEchoChar('*');
        txtSifreKayitOl.setMinimumSize(new java.awt.Dimension(232, 30));
        txtSifreKayitOl.setPreferredSize(new java.awt.Dimension(232, 30));

        txtSifreTekrarKayitOl.setEchoChar('*');
        txtSifreTekrarKayitOl.setMinimumSize(new java.awt.Dimension(232, 30));
        txtSifreTekrarKayitOl.setPreferredSize(new java.awt.Dimension(232, 30));

        logoGosterKayitOl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/eye32.png"))); // NOI18N
        logoGosterKayitOl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoGosterKayitOlMouseClicked(evt);
            }
        });

        logoGizleKayitOl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/hide32.png"))); // NOI18N
        logoGizleKayitOl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoGizleKayitOlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSifreLayout = new javax.swing.GroupLayout(pnlSifre);
        pnlSifre.setLayout(pnlSifreLayout);
        pnlSifreLayout.setHorizontalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSifreKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(lblSifre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoGizleKayitOl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoGosterKayitOl, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lblSifreTekrar))
                    .addComponent(txtSifreTekrarKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlSifreLayout.setVerticalGroup(
            pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifreLayout.createSequentialGroup()
                .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSifreLayout.createSequentialGroup()
                        .addComponent(logoGizleKayitOl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoGosterKayitOl))
                    .addGroup(pnlSifreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlSifreLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(lblSifre)
                            .addGap(6, 6, 6)
                            .addComponent(txtSifreKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlSifreLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(lblSifreTekrar)
                            .addGap(6, 6, 6)
                            .addComponent(txtSifreTekrarKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        cbGuvenlik.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "İlk okul öğretmeninizin adı nedir?", "İlk evcil hayvanınızın adı nedir?", "Doğum yeriniz neresidir?", "Anne kızlık soyadınız nedir?", "En sevdiğiniz yemek nedir?" }));

        javax.swing.GroupLayout pnlGuvenlikLayout = new javax.swing.GroupLayout(pnlGuvenlik);
        pnlGuvenlik.setLayout(pnlGuvenlikLayout);
        pnlGuvenlikLayout.setHorizontalGroup(
            pnlGuvenlikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlikLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbGuvenlik, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        btnKayitOl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKayitOlActionPerformed(evt);
            }
        });

        lblHesapOlusturKapat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/close_64px.png"))); // NOI18N
        lblHesapOlusturKapat.setToolTipText("");
        lblHesapOlusturKapat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHesapOlusturKapat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHesapOlusturKapatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHesapOlusturLayout = new javax.swing.GroupLayout(pnlHesapOlustur);
        pnlHesapOlustur.setLayout(pnlHesapOlusturLayout);
        pnlHesapOlusturLayout.setHorizontalGroup(
            pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                        .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSifre, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                            .addComponent(pnlGuvenlik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHesapOlusturLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHesapOlusturLayout.createSequentialGroup()
                                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(165, 165, 165))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                                    .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnlTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(85, 85, 85))
                                .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                                    .addComponent(pnlAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblHesapOlusturKapat)
                                    .addContainerGap()))))))
        );
        pnlHesapOlusturLayout.setVerticalGroup(
            pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHesapOlusturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHesapOlusturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHesapOlusturKapat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlGuvenlik, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pnlSifremiUnuttum.setForeground(new java.awt.Color(204, 204, 204));
        pnlSifremiUnuttum.setPreferredSize(new java.awt.Dimension(512, 477));

        pnlEmail1.setPreferredSize(new java.awt.Dimension(400, 100));

        lblEmail1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail1.setText("Email");

        lblSifremiUnuttumKapat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/close_64px.png"))); // NOI18N
        lblSifremiUnuttumKapat.setToolTipText("");
        lblSifremiUnuttumKapat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSifremiUnuttumKapat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSifremiUnuttumKapatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlEmail1Layout = new javax.swing.GroupLayout(pnlEmail1);
        pnlEmail1.setLayout(pnlEmail1Layout);
        pnlEmail1Layout.setHorizontalGroup(
            pnlEmail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmail1Layout.createSequentialGroup()
                .addGroup(pnlEmail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEmail1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtEmailSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEmail1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEmail1)
                        .addGap(171, 171, 171)))
                .addComponent(lblSifremiUnuttumKapat)
                .addContainerGap())
        );
        pnlEmail1Layout.setVerticalGroup(
            pnlEmail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmail1Layout.createSequentialGroup()
                .addGroup(pnlEmail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSifremiUnuttumKapat)
                    .addGroup(pnlEmail1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblEmail1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlSifre1.setPreferredSize(new java.awt.Dimension(400, 100));

        lblSifre1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifre1.setText("Şifre");

        lblSifreTekrar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSifreTekrar1.setText("Şifre (Tekrar)");

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

        javax.swing.GroupLayout pnlSifre1Layout = new javax.swing.GroupLayout(pnlSifre1);
        pnlSifre1.setLayout(pnlSifre1Layout);
        pnlSifre1Layout.setHorizontalGroup(
            pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifre1Layout.createSequentialGroup()
                .addGroup(pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSifre1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(lblSifre1))
                    .addGroup(pnlSifre1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(lblSifreTekrar1))
                    .addGroup(pnlSifre1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSifre1Layout.createSequentialGroup()
                                .addComponent(txtSifreTekrarSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(logoSifreTekrarGoster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoSifreTekrarGizle))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSifre1Layout.createSequentialGroup()
                                .addComponent(txtSifreSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(logoSifreGoster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoSifreGizle)))))
                .addGap(38, 54, Short.MAX_VALUE))
        );
        pnlSifre1Layout.setVerticalGroup(
            pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifre1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblSifre1)
                .addGroup(pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSifre1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoSifreGoster)
                            .addComponent(logoSifreGizle)))
                    .addGroup(pnlSifre1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSifreSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(lblSifreTekrar1)
                .addGap(2, 2, 2)
                .addGroup(pnlSifre1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSifreTekrarSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoSifreTekrarGoster)
                    .addComponent(logoSifreTekrarGizle)))
        );

        pnlGuvenlik1.setPreferredSize(new java.awt.Dimension(400, 100));

        lblGuvenlikSorusu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikSorusu1.setText("Güvenlik Sorusu");

        lblGuvenlikCevap1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGuvenlikCevap1.setText("Cevap");

        cbGuvenlikSifremiUnuttum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "İlk okul öğretmeninizin adı nedir?", "İlk evcil hayvanınızın adı nedir?", "Doğum yeriniz neresidir?", "Anne kızlık soyadınız nedir?", "En sevdiğiniz yemek nedir?" }));

        javax.swing.GroupLayout pnlGuvenlik1Layout = new javax.swing.GroupLayout(pnlGuvenlik1);
        pnlGuvenlik1.setLayout(pnlGuvenlik1Layout);
        pnlGuvenlik1Layout.setHorizontalGroup(
            pnlGuvenlik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlik1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(pnlGuvenlik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGuvenlik1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lblGuvenlikSorusu1))
                    .addGroup(pnlGuvenlik1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblGuvenlikCevap1))
                    .addGroup(pnlGuvenlik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbGuvenlikSifremiUnuttum, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCevapSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGuvenlik1Layout.setVerticalGroup(
            pnlGuvenlik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGuvenlik1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGuvenlikSorusu1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbGuvenlikSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGuvenlikCevap1)
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
            .addComponent(pnlSifre1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGuvenlik1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(pnlEmail1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btnSifreDegistir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSifremiUnuttumLayout.setVerticalGroup(
            pnlSifremiUnuttumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSifremiUnuttumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSifre1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGuvenlik1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSifreDegistir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
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
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlLoginLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(401, Short.MAX_VALUE)
                    .addComponent(pnlHesapOlustur, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 433, Short.MAX_VALUE)
                    .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLoginLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlHesapOlustur, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlSifremiUnuttum, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 22, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

////////////////////////////////////////// DARK MOD TIKLAMA BAŞLANGIÇ
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
/////////////////////////////////////////// DARK MOD TIKLAMA BİTİŞ 
    /////////////////////////////////////// Kayıt Ol Başlangıç //////////////////////////////////////
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        pnlLoginLogo.setVisible(false);
        pnlSifremiUnuttum.setVisible(false);
        pnlHesapOlustur.setVisible(true);
        pnlHesapOlustur.setBounds(pnlLoginLogo.getBounds());

        /* KayitOl kayitol = new KayitOl();
        kayitol.setVisible(true);
        kayitol.setSize(590, 477);
         */
    }//GEN-LAST:event_jLabel5MouseClicked
    /////////////////////////////////////// Kayıt Ol Bitiş //////////////////////////////////////
    /////////////////////////////////////ŞİFRE GÖSTERME BAŞLANGIÇ
    private void logoGosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGosterMouseClicked
        txtSifre.setEchoChar((char) 0);
        logoGoster.setVisible(false);
        logoGizle.setLocation(logoGoster.getLocation());
        logoGizle.setVisible(true);
    }//GEN-LAST:event_logoGosterMouseClicked

    private void logoGizleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGizleMouseClicked
        txtSifre.setEchoChar('*');
        logoGizle.setVisible(false);
        logoGoster.setVisible(true);
    }//GEN-LAST:event_logoGizleMouseClicked
    /////////////////////////////////////////ŞİFRE GÖSTERME BİTİŞ 
////////////////////////////////////////////Şifremi Unuttum Başlangıç /////////////////////////////////////
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pnlLoginLogo.setVisible(false);
        pnlHesapOlustur.setVisible(false);
        pnlSifremiUnuttum.setVisible(true);
        pnlSifremiUnuttum.setBounds(pnlLoginLogo.getBounds());
        //SifremiUnuttum sifremiunuttum = new SifremiUnuttum();
        //sifremiunuttum.setVisible(true);
        //sifremiunuttum.setLocation(579, 477);
    }//GEN-LAST:event_jLabel4MouseClicked
////////////////////////////////////////////Şifremi Unuttum Bitiş /////////////////////////////////////
/////////////////////////////////// Giriş butonu Başlangıç//////////////////////////////////////
    private void btnGirisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirisActionPerformed

        try {
            String metin = txtEmail.getText();
            try {
                writer.write(metin);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.close();

            email = txtEmail.getText().trim();
            sifre = txtSifre.getText().trim();
            try {
                String sql = "SELECT * FROM public.kullanicilar WHERE email=? AND sifre=?;";
                pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, sifre);
                rs = pst.executeQuery();
                if (rs.next()) {

                    JOptionPane.showConfirmDialog(null, "Hoşgeldiniz");

                } else {
                    JOptionPane.showConfirmDialog(null, "Kullanıcı Adı Ve Şifrenizi Kontrol Ediniz!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            /////////////////////////////////// Giriş butonu Bitiş//////////////////////////////////////
/////////////////////////////////////////// Giriş Log Kaydı Başlangıç/////////////////////////////////////////
            try {
                InetAddress ip;
                ip = InetAddress.getLocalHost();
                String sqllog = "INSERT INTO public.kullanici_log(adsoyad, email, ip, islem)VALUES ((Select adsoyad from public.kullanicilar WHERE email='" + email + "'),'" + email + "', '" + ip.getHostAddress() + "','Giriş');";
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
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGirisActionPerformed
/////////////////////////////////////////// Giriş Log Kaydı Bitiş/////////////////////////////////////////
/////////////////////////////////////////// Kayıt Ol Logo Gizleme Başlangıç/////////////////////////////////////////
    private void logoGosterKayitOlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGosterKayitOlMouseClicked
        txtSifreKayitOl.setEchoChar((char) 0);
        txtSifreTekrarKayitOl.setEchoChar((char) 0);
        var gosterLocation = logoGosterKayitOl.getLocation();
        var gizleLocation = logoGizleKayitOl.getLocation();
        logoGosterKayitOl.setVisible(false);
        logoGizleKayitOl.setVisible(true);
        logoGosterKayitOl.setLocation(0, 0);
        logoGizleKayitOl.setLocation(0, 0);

    }//GEN-LAST:event_logoGosterKayitOlMouseClicked

    private void logoGizleKayitOlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoGizleKayitOlMouseClicked
        txtSifreKayitOl.setEchoChar('*');
        txtSifreTekrarKayitOl.setEchoChar('*');
        logoGizleKayitOl.setVisible(false);
        logoGosterKayitOl.setVisible(true);
    }//GEN-LAST:event_logoGizleKayitOlMouseClicked
/////////////////////////////////////////// Kayıt Ol Logo Gizleme Bitiş/////////////////////////////////////////
/////////////////////////////////////////// Kayıt Ol Başlangıç/////////////////////////////////////////
    private void btnKayitOlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKayitOlActionPerformed
        var adsoyad = txtAdSoyad.getText();
        var email = txtEmailKayit.getText();
        var telefon = Long.parseLong(txtTelefon.getText().trim().toString());
        var sifre = txtSifreKayitOl.getText();
        var guvenliksorusu = cbGuvenlik.getSelectedItem().toString();
        var guvenlikcevap = txtCevap.getText();

        // "INSERT INTO public.Kullanicilar(adsoyad, email, telefon, sifre, guvenliksorusu, guvenlikcevap) VALUES ( 'Enis Kaman', 'e.kmn2002@hotmail.com', 05527464336, 'enes3349', 'abc', 'def');"
        try {
            String sql = "INSERT INTO public.kullanicilar(adsoyad, email, sifre, telefon, guvenliksorusu, guvenlikcevap)VALUES ('" + adsoyad + "', '" + email + "', '" + sifre + "'," + telefon + ", '" + guvenliksorusu + "', '" + guvenlikcevap + "');";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showConfirmDialog(null, "Kayıt Başarılı");

            } else {
                JOptionPane.showConfirmDialog(null, "Kayıt Başarısız");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKayitOlActionPerformed
/////////////////////////////////////////// Kayıt Ol Bitiş/////////////////////////////////////////
/////////////////////////////////////////// Şifremi Unuttum Logo Gizleme Başlangıç/////////////////////////////////////////
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
/////////////////////////////////////////// Şifremi Unuttum Logo Gizleme Bitiş/////////////////////////////////////////
/////////////////////////////////////////// Şifremi Unuttum Başlangıç/////////////////////////////////////////
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
/////////////////////////////////////////// Şifremi Unuttum Bitiş///////////////////////////////////////// 

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed
/////////////////////////////////////////// Çıkış Log Başlangıç///////////////////////////////////////// 
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
/////////////////////////////////////////// Çıkış Log Bitiş///////////////////////////////////////// 
/////////////////////////////////////////// Hesap Oluştur Kapat Başlangıç///////////////////////////////////////// 
    private void lblHesapOlusturKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHesapOlusturKapatMouseClicked
        pnlHesapOlustur.setVisible(false);
        pnlSifremiUnuttum.setVisible(false);
        pnlLoginLogo.setVisible(true);
    }//GEN-LAST:event_lblHesapOlusturKapatMouseClicked
/////////////////////////////////////////// Hesap Oluştur Kapat Bitiş///////////////////////////////////////// 
/////////////////////////////////////////// Şifremi Unuttum Kapat Başlangıç///////////////////////////////////////// 
    private void lblSifremiUnuttumKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSifremiUnuttumKapatMouseClicked
        pnlHesapOlustur.setVisible(false);
        pnlSifremiUnuttum.setVisible(false);
        pnlLoginLogo.setVisible(true);    }//GEN-LAST:event_lblSifremiUnuttumKapatMouseClicked
/////////////////////////////////////////// Şifremi Unuttum Kapat Bitiş///////////////////////////////////////// 

    // MAİN BAŞLANGIÇ
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // MAİN BİTİŞ

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiris;
    private javax.swing.JButton btnKayitOl;
    private javax.swing.JButton btnSifreDegistir;
    private javax.swing.JComboBox<String> cbGuvenlik;
    private javax.swing.JComboBox<String> cbGuvenlikSifremiUnuttum;
    private javax.swing.JCheckBox chkBeniHatirla;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAd;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGuvenlikCevap;
    private javax.swing.JLabel lblGuvenlikCevap1;
    private javax.swing.JLabel lblGuvenlikSorusu;
    private javax.swing.JLabel lblGuvenlikSorusu1;
    private javax.swing.JLabel lblHesapOlusturKapat;
    private javax.swing.JLabel lblSifre;
    private javax.swing.JLabel lblSifre1;
    private javax.swing.JLabel lblSifreTekrar;
    private javax.swing.JLabel lblSifreTekrar1;
    private javax.swing.JLabel lblSifremiUnuttumKapat;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel logoGizle;
    private javax.swing.JLabel logoGizleKayitOl;
    private javax.swing.JLabel logoGoster;
    private javax.swing.JLabel logoGosterKayitOl;
    private javax.swing.JLabel logoSifreGizle;
    private javax.swing.JLabel logoSifreGoster;
    private javax.swing.JLabel logoSifreTekrarGizle;
    private javax.swing.JLabel logoSifreTekrarGoster;
    private javax.swing.JCheckBoxMenuItem menuDarkMode;
    private javax.swing.JPanel pnlAdSoyad;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlEmail1;
    private javax.swing.JPanel pnlGuvenlik;
    private javax.swing.JPanel pnlGuvenlik1;
    private javax.swing.JPanel pnlHesapOlustur;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlLoginLogo;
    private javax.swing.JPanel pnlSifre;
    private javax.swing.JPanel pnlSifre1;
    private javax.swing.JPanel pnlSifremiUnuttum;
    private javax.swing.JPanel pnlTelefon;
    private javax.swing.JTextArea txtAdSoyad;
    private javax.swing.JTextArea txtCevap;
    private javax.swing.JTextField txtCevapSifremiUnuttum;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtEmailKayit;
    private javax.swing.JTextField txtEmailSifremiUnuttum;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JPasswordField txtSifreKayitOl;
    private javax.swing.JPasswordField txtSifreSifremiUnuttum;
    private javax.swing.JPasswordField txtSifreTekrarKayitOl;
    private javax.swing.JPasswordField txtSifreTekrarSifremiUnuttum;
    private javax.swing.JTextArea txtTelefon;
    // End of variables declaration//GEN-END:variables
}
