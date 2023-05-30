/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kutuphane;

import entity.Kitaplik;

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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.chart.ModelPieChart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellEditorBelgeOkumaBirlikte;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionCellRenderBelgeOkumaBirlikte;
import raven.cell.TableActionEvent;
import raven.cell.TableActionEventBelgeOkumaBirlikte;

/**
 *
 * @author Enis
 */
public class AdminArayuzu extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    Kitaplik kitaplik = new Kitaplik();
    String email = null;
    String sifre = null;
    int tema = 0;
    String dosyayolu = null;
    String dosyaadi = null;
    File dosya;
    FileInputStream fis;
    String kitapisteyenemail;
    String kitapisteyenadsoyad;
    String kitapgetirmetarih;
    String kitapyayinevi;
    int secilenkitapid = 0;
    Object belgeturu;
    int belgeonaylaindex;
    HashMap<Integer, Integer> BelgeIDToOnaylamaTabloRow = new HashMap<>();
    String tarananbelgesonuclari = null;
    String taranacakbelgeyolu;
    File taranacakbelge;
    HashMap<Integer, Integer> BelgeKoduToKayitTuruBelgelerimTabloRow = new HashMap<>();

    public void KullaniciCekme() {
        try {
            String sql = "SELECT * FROM public.kullanicilar WHERE yetkituru = ?;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 0);
            rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Email");
            model.addColumn("Ad Soyad");

            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString("email");
                row[1] = rs.getString("adsoyad");
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblKullanicilar.setRowSorter(shorter);
            tblKullanicilar.setDefaultRenderer(Object.class, centerRenderer);
            tblKullanicilar.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void KitaplarTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT kitap_adi,kitap_kodu,yazar_adsoyad,yayin_evi,kitap_turu,kitap_durum FROM public.kitaplik ORDER BY kitap_kodu ASC;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Kitap Adı");
            model.addColumn("Yazar");
            model.addColumn("Yayın Evi");
            model.addColumn("Kitap Türü");
            model.addColumn("Kitap Durum");
            model.addColumn("İşlem");

            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("kitap_kodu");
                row[1] = rs.getString("kitap_adi");
                row[2] = rs.getString("yazar_adsoyad");
                row[3] = rs.getString("yayin_evi");
                row[4] = rs.getString("kitap_turu");
                row[5] = rs.getString("kitap_durum");
                row[6] = rs.getString("kitap_durum");
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblKitaplar.setRowSorter(shorter);
            tblKitaplar.setDefaultRenderer(Object.class, centerRenderer);
            tblKitaplar.setModel(model);
            TableActionEvent event = new TableActionEvent() {
                @Override
                public void onEdit(int row) {
                    int kitapkodu = (int) tblKitaplar.getValueAt(row, 0);
                    EditKitapGonder(kitapkodu);
                }

                @Override
                public void onDelete(int row) {
                    int silinecekkitapkodu = (int) tblKitaplar.getValueAt(row, 0);
                    String silinecekkitapadi = (String) tblKitaplar.getValueAt(row, 1);
                    String silinecekkitapyayinevi = (String) tblKitaplar.getValueAt(row, 3);

                    int result = JOptionPane.showConfirmDialog(null, "Kitap Kodu: " + silinecekkitapkodu + "\nKitap Adı: " + silinecekkitapadi + "\nYayın Evi: " + silinecekkitapyayinevi + "\nKitabı Silmek İstediğinizden Emin Misiniz ?");
                    if (result == JOptionPane.YES_OPTION) {
                        DeleteKitap(row, tblKitaplar);
                        model.removeRow(row);
                        tblKitaplar.setModel(model);
                        txtKitapArama.setText("");
                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Kitabı Silmediniz");
                    }
                }

                @Override
                public void onView(int row) {
                    int kitapkodu = (int) tblKitaplar.getValueAt(row, 0);
                    ViewKitapGonder(kitapkodu);
                }
            };
            tblKitaplar.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
            tblKitaplar.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KitapOnayTabloVerileri() throws ParseException {
        try {
            String sql = "SELECT * FROM public.kitap_al_istek where kitap_al_istek_durum = 'Beklemede';";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Adı");
            model.addColumn("Yayın Evi");
            model.addColumn("İsteyen Ad Soyad");
            model.addColumn("İsteyen Email");
            model.addColumn("Getirme Tarihi");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("kitap_al_istek_kitap_adi");
                row[1] = rs.getString("kitap_al_istek_kitap_yayinevi");
                row[2] = rs.getString("kitap_al_istek_isteyen_ad_soyad");
                row[3] = rs.getString("kitap_al_istek_isteyen_email");
                String gerigetirme = rs.getString("kitap_al_istek_geri_verme_tarihi");
                SimpleDateFormat girisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat cikisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date2 = girisFormat.parse(gerigetirme);
                String songerigetirmeDate = cikisFormat.format(date2);
                row[4] = songerigetirmeDate;
                String tarihkitap = rs.getString("kitap_al_istek_guncelleme_tarihi");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = inputFormat.parse(tarihkitap);
                String formattedDate = outputFormat.format(date);
                row[5] = formattedDate;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblKitapOnay.setRowSorter(shorter);
            tblKitapOnay.setDefaultRenderer(Object.class, centerRenderer);
            tblKitapOnay.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ArsivTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT belge_adi,belge_yayinlayan_kisi,belge_kodu,belge_yayin_yili,belge_turu, belge_kayit_turu FROM public.arsiv;";
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
                int belgekodu = rs.getInt("belge_kodu");
                int belgekayitturu = rs.getInt("belge_kayit_turu");
                row[2] = belgekodu;
                row[3] = rs.getInt("belge_yayin_yili");
                row[4] = rs.getString("belge_turu");
                row[5] = belgekayitturu;
                model.addRow(row);
                BelgeKoduToKayitTuruBelgelerimTabloRow.put(belgekodu, belgekayitturu);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblBelgeler.setRowSorter(shorter);
            tblBelgeler.setDefaultRenderer(Object.class, centerRenderer);
            tblBelgeler.setModel(model);
            TableActionEventBelgeOkumaBirlikte event = new TableActionEventBelgeOkumaBirlikte() {
                @Override
                public void PdfOkuma(int row) {
                    int belgekodu = (int) tblBelgeler.getValueAt(row, 2);
                    int kayitturu = BelgeKoduToKayitTuruBelgelerimTabloRow.get(belgekodu);
                    if (kayitturu == 2 || kayitturu == 0) {
                        PDFGoster(belgekodu);
                    }
                    if (kayitturu == 1) {
                        JOptionPane.showMessageDialog(null, "Bu belgede yalnızca taranmış bir belge vardır.");
                    }
                }

                @Override
                public void TaranmisOkuma(int row) {
                    int belgekodu = (int) tblBelgeler.getValueAt(row, 2);
                    int kayitturu = BelgeKoduToKayitTuruBelgelerimTabloRow.get(belgekodu);
                    if (kayitturu == 2 || kayitturu == 1) {
                        TaranmisBelgeGoster(belgekodu);
                    }
                    if (kayitturu == 0) {
                        JOptionPane.showMessageDialog(null, "Bu belgede yalnızca PDF vardır.");
                    }
                }
            };
            tblBelgeler.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderBelgeOkumaBirlikte());
            tblBelgeler.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorBelgeOkumaBirlikte(event));
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BelgeOnayTabloVerileri() throws ParseException {
        try {
            String sql = "SELECT * FROM public.arsiv_istek_bekleme WHERE arsiv_istek_bekleme_durum = 'Beklemede' ORDER BY arsiv_istek_bekleme_guncelleme_tarihi ASC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Kitap Adı");
            model.addColumn("İsteyen Ad Soyad");
            model.addColumn("İsteyen Email");
            model.addColumn("İstek Sebebi");
            model.addColumn("Güncelleme Tarihi");

            int tablorow = 0;
            while (rs.next()) {
                Object[] row = new Object[6];
                int id = rs.getInt("arsiv_istek_bekleme_id");
                BelgeIDToOnaylamaTabloRow.put(tablorow, id);
                tablorow++;
                row[0] = rs.getInt("arsiv_istek_bekleme_belge_kodu");
                row[1] = rs.getString("arsiv_istek_bekleme_belge_adi");
                row[2] = rs.getString("arsiv_istek_bekleme_isteyen_adsoyad");
                row[3] = rs.getString("arsiv_istek_bekleme_isteyen_email");
                row[4] = rs.getString("arsiv_istek_bekleme_istek_sebebi");
                row[5] = rs.getTimestamp("arsiv_istek_bekleme_guncelleme_tarihi");
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblBelgeOnaylama.setRowSorter(shorter);
            tblBelgeOnaylama.setDefaultRenderer(Object.class, centerRenderer);
            tblBelgeOnaylama.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model2 = new DefaultTableModel();
            model2.addColumn("Randevu İsteyen Kişi");
            model2.addColumn("Randevu Konusu");
            model2.addColumn("Randevu Tarihi");
            model2.addColumn("Randevu Durumu");
            model2.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_isteyen_adsoyad");
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
                model2.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model2);
            tblRandevular.setRowSorter(shorter);
            tblRandevular.setDefaultRenderer(Object.class, centerRenderer);
            tblRandevular.setModel(model2);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PastaGrafik() {
        try {
            pieChart1.clearData();
            String sql = "SELECT Distinct islem, COUNT(islem) FROM public.kullanici_log group by islem;;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            int index = 0;
            String islem = "";
            while (rs.next()) {
                islem = rs.getString(1);
                int cikis = rs.getInt(2);
                pieChart1.addData(new ModelPieChart(islem, cikis, Renkler(index++)));
                //lblRenk1.setBackground(Renkler(index++));
                //lblRenk1Adı.setText(islem);
            }

            pnlRenk1.setBackground(Renkler(0));
            lblRenk1Adi.setText("Çıkış");
            pnlRenk2.setBackground(Renkler(1));
            lblRenk2Adi.setText("Giriş");
            pnlRenk3.setBackground(Renkler(2));
            lblRenk3Adi.setText("Giriş Yapmadan Çıkış");
            pnlRenk4.setBackground(Renkler(3));
            lblRenk4Adi.setText("Kullanıcı Adı ya da Şifre Yanlış");
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TaranmisBelgeGoster(int belgekodu) {
        try {
            String sql = "SELECT belge_tarama FROM public.arsiv WHERE belge_kodu = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, belgekodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("belge_tarama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PDFGoster(int belgekodu) {
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
                String dosyaYolu = "C:\\Users\\ekmn2\\OneDrive\\Belgeler\\New Folder\\Kutuphane\\pdf\\" + dosyaAdi;
                FileOutputStream fos = new FileOutputStream(dosyaYolu);
                fos.write(pdfData);
                fos.close();
                Desktop.getDesktop().open(new File(dosyaYolu));

                /*File dosya = new File(dosyaAdi);                
                if (dosya.exists()) {
                    dosya.delete();
                    System.out.println("Dosya silindi: " + dosyaAdi);
                }*/
                // PDF dosyasını varsayılan PDF görüntüleyici ile açma
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DosyaSilme() {
        String klasorYolu = "C:\\Users\\ekmn2\\OneDrive\\Belgeler\\New Folder\\Kutuphane\\pdf\\";
        File klasor = new File(klasorYolu);

        if (klasor.exists()) {
            File[] dosyalar = klasor.listFiles();
            if (dosyalar != null) {
                for (File dosya : dosyalar) {
                    if (dosya.isFile()) {
                        if (dosya.delete()) {
                            System.out.println(dosya.getName() + " dosyası başarıyla silindi.");
                        } else {
                            System.out.println(dosya.getName() + " dosyası silinirken bir hata oluştu.");
                        }
                    }
                }
            }
        } else {
            System.out.println("Belirtilen klasör bulunamadı.");
        }
    }

    public Color Renkler(int index) {
        Color[] color = new Color[]{new Color(255, 102, 102), new Color(29, 184, 80), new Color(206, 215, 33), new Color(55, 55, 227), new Color(0, 255, 51)};
        return color[index];
    }

    public void KitapIstekKaldirma(int kalkacakindex, String durum, int kitapkodu, String kitapadi, boolean kabul) throws SQLException, ParseException {
        String sqldelete = "UPDATE public.kitap_al_istek SET kitap_al_istek_durum=? WHERE kitap_al_istek_ID = ?;";
        pst = conn.prepareStatement(sqldelete);
        pst.setString(1, durum);
        pst.setInt(2, secilenkitapid);
        int sonuc = pst.executeUpdate();
        if (sonuc == 1) {
            JOptionPane.showMessageDialog(null, "Silindi");
        }

        if (kabul == true) {
            String sql = "UPDATE public.kitaplik SET kitap_durum = ? where kitap_kodu = ?;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapisteyenemail);
            pst.setInt(2, kitapkodu);
            int cevap = pst.executeUpdate();
            if (cevap == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Şu anda " + kitapisteyenemail);
            }

            System.out.println("asdasasdasd");
            String sqlupdate = "UPDATE public.kitap_envanter SET elde_olan = elde_olan - 1 WHERE kitap_adi=?;";
            pst = conn.prepareStatement(sqlupdate);
            pst.setString(1, kitapadi);
            int donus = pst.executeUpdate();
            if (donus == 1) {
                JOptionPane.showMessageDialog(null, "Azaldı");
            }
        }

        txtEnvanterdeKalan.setText("");
        txtToplamKitap.setText("");
        cbKitapKodları.removeAllItems();
        KitapOnayTabloVerileri();
    }

    public void search(JTable table, JTextField textField) {
        String searchText = textField.getText().toLowerCase();
        List<Integer> matchingRows = new ArrayList<>();

        // Tabloyu dolaşarak arama yap
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                String cellText = table.getValueAt(row, col).toString().toLowerCase();
                if (cellText.contains(searchText)) {
                    matchingRows.add(row);
                    break; // Sütun içinde eşleşme bulunduğunda döngüyü sonlandır
                }
            }
        }

        // Aranan sonuçları vurgula
        table.clearSelection();
        for (int matchedRow : matchingRows) {
            table.addRowSelectionInterval(matchedRow, matchedRow);
        }

        int rowCount = table.getRowCount();
        int[] selectedRows = table.getSelectedRows();

        for (int i = rowCount - 1; i >= 0; i--) {
            if (!isSelected(i, selectedRows)) {
                ((DefaultTableModel) table.getModel()).removeRow(i);
            }
        }
    }

    private boolean isSelected(int row, int[] selectedRows) {
        for (int selectedRow : selectedRows) {
            if (row == selectedRow) {
                return true;
            }
        }
        return false;
    }

    public void ViewKitapGonder(int kitapkodu) {
        try {

            String kitapadi = null;
            String yazaradi = null;
            String yayinevi = null;
            String kitapturu = null;
            String kitapozeti = null;
            byte[] imagedata = null;
            int toplamkitapsayisi = 0;
            int eldeolankitapsayisi = 0;

            String sql = "Select * FROM public.kitaplik WHERE kitap_kodu = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                kitapadi = rs.getString("kitap_adi");
                yazaradi = rs.getString("yazar_adsoyad");
                yayinevi = rs.getString("yayin_evi");
                kitapturu = rs.getString("kitap_turu");
                kitapozeti = rs.getString("kitap_ozet");
                imagedata = rs.getBytes("kitap_resim");
            }

            String sqlkitapsayisi = "SELECT kitap_sayisi, elde_olan FROM public.kitap_envanter WHERE kitap_adi = ? AND kitap_yayinevi = ?;";
            pst = conn.prepareStatement(sqlkitapsayisi);
            pst.setString(1, kitapadi);
            pst.setString(2, yayinevi);
            rs = pst.executeQuery();
            if (rs.next()) {
                toplamkitapsayisi = rs.getInt("kitap_sayisi");
                eldeolankitapsayisi = rs.getInt("elde_olan");
            }

            AdminKitapDetayliGoruntule dg = new AdminKitapDetayliGoruntule(kitapkodu, kitapadi, yazaradi, yayinevi, kitapturu, kitapozeti, imagedata, toplamkitapsayisi, eldeolankitapsayisi);
            dg.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditKitapGonder(int kitapkodu) {
        try {

            int kitapid = 0;
            String kitapadi = null;
            String yazaradi = null;
            String yayinevi = null;
            String kitapturu = null;
            String kitapozeti = null;
            byte[] imagedata = null;

            String sql = "Select * FROM public.kitaplik WHERE kitap_kodu = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                kitapid = rs.getInt("kitap_id");
                kitapadi = rs.getString("kitap_adi");
                yazaradi = rs.getString("yazar_adsoyad");
                yayinevi = rs.getString("yayin_evi");
                kitapturu = rs.getString("kitap_turu");
                kitapozeti = rs.getString("kitap_ozet");
                imagedata = rs.getBytes("kitap_resim");
            }

            KitapGuncelleme kg = new KitapGuncelleme(kitapid, kitapkodu, kitapadi, yazaradi, yayinevi, kitapturu, kitapozeti, imagedata, this);
            kg.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DeleteKitap(int row, JTable tablo) {
        try {
            int silinecekkitapkodu = (int) tablo.getValueAt(row, 0);
            String silinecekkitapadi = (String) tablo.getValueAt(row, 1);
            String silinecekkitapyayinevi = (String) tablo.getValueAt(row, 3);

            JOptionPane.showMessageDialog(null, silinecekkitapadi + silinecekkitapyayinevi + silinecekkitapkodu);

            String sqldelete = "DELETE FROM public.kitaplik WHERE kitap_kodu = ?;";
            pst = conn.prepareStatement(sqldelete);
            pst.setInt(1, silinecekkitapkodu);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Silindi");
            }

            String sqlenvanter = "UPDATE public.kitap_envanter SET kitap_sayisi=kitap_sayisi - 1, elde_olan=elde_olan - 1 WHERE kitap_adi = ? AND kitap_yayinevi = ?;";
            pst = conn.prepareStatement(sqlenvanter);
            pst.setString(1, silinecekkitapadi);
            pst.setString(2, silinecekkitapyayinevi);
            int sonuc2 = pst.executeUpdate();
            if (sonuc2 == 1) {
                JOptionPane.showMessageDialog(null, "Envanterden 1 azaldı");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AdminArayuzu() {
        initComponents();
    }

    public AdminArayuzu(String email, String sifre, int tema) throws ParseException {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;

        initComponents();
        KitaplarTabloVerileri();
        RandevuTabloVerileri();
        PastaGrafik();
        KitapOnayTabloVerileri();
        TemaRengi();
        ArsivTabloVerileri();
        BelgeOnayTabloVerileri();
        KullaniciCekme();
        pnlSettings.setVisible(false);
        tabArsiv.setVisible(false);
        tabDiger.setVisible(false);
        txtKullaniciBul.enable(false);
        tblKullanicilar.enable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSettings = new javax.swing.JLabel();
        lblKutuphane = new javax.swing.JLabel();
        lblArsiv = new javax.swing.JLabel();
        lblDiger = new javax.swing.JLabel();
        tabKutuphane = new javax.swing.JTabbedPane();
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
        lblKitapResmi1 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtKitapOzet = new javax.swing.JTextArea();
        pnlKitapOnaylama = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKitapOnay = new javax.swing.JTable();
        cbKitapKodları = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtToplamKitap = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEnvanterdeKalan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtKitapReddetmeSebep = new javax.swing.JTextArea();
        btnKitapOnayla = new javax.swing.JButton();
        btnKitapReddet = new javax.swing.JButton();
        pnlKitaplar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKitaplar = new javax.swing.JTable();
        txtKitapArama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pnlSettings = new javax.swing.JPanel();
        pnlSettingsKapat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRenk = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSettingsKaydet = new javax.swing.JButton();
        tabDiger = new javax.swing.JTabbedPane();
        pnlUyeIslemleri = new javax.swing.JPanel();
        pnlRandevular = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRandevular = new javax.swing.JTable();
        btnKabul = new javax.swing.JButton();
        btnRet = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSebep = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtRandevuIsteyenKisi = new javax.swing.JTextField();
        txtRandevuTarih = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtKonu = new javax.swing.JTextArea();
        pnlDuyurular = new javax.swing.JPanel();
        cbDuyuruKisi = new javax.swing.JComboBox<>();
        txtKullaniciBul = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblKullanicilar = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtDuyuru = new javax.swing.JTextArea();
        txtDuyuruBaslik = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnDuyuruYayinla = new javax.swing.JButton();
        pnlDashboard = new javax.swing.JPanel();
        pieChart1 = new javaswingdev.chart.PieChart();
        pnlPastaBilgiler = new javax.swing.JPanel();
        lblRenk4Adi = new javax.swing.JLabel();
        lblRenk3Adi = new javax.swing.JLabel();
        lblRenk2Adi = new javax.swing.JLabel();
        lblRenk1Adi = new javax.swing.JLabel();
        pnlRenk1 = new javax.swing.JPanel();
        pnlRenk2 = new javax.swing.JPanel();
        pnlRenk3 = new javax.swing.JPanel();
        pnlRenk4 = new javax.swing.JPanel();
        tabArsiv = new javax.swing.JTabbedPane();
        pnlBelgeEkle = new javax.swing.JPanel();
        lblBelgeAdi = new javax.swing.JLabel();
        lblYayinlayanAdi = new javax.swing.JLabel();
        lblBelgeYayinTarihi = new javax.swing.JLabel();
        lblBelgeKodu = new javax.swing.JLabel();
        lblBelgeNushasi = new javax.swing.JLabel();
        btnBelgeSec = new javax.swing.JButton();
        txtBelgeKodu = new javax.swing.JTextField();
        txtYayinYili = new javax.swing.JTextField();
        txtYayinlayanAdi = new javax.swing.JTextField();
        txtBelgeAdi = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        treeBelgeTuru = new javax.swing.JTree();
        lblBelgeNushasiAdi = new javax.swing.JLabel();
        btnBelgeEkle = new javax.swing.JButton();
        lblBelgeNushasi1 = new javax.swing.JLabel();
        btnTaranacakBelge = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtTarananBelge = new javax.swing.JTextArea();
        pnlBelgeOnayla = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblBelgeOnaylama = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtIstekSebebi = new javax.swing.JTextArea();
        txtBelgeOnaylamaBelgeKodu = new javax.swing.JTextField();
        txtBelgeOnaylamaBelgeAdi = new javax.swing.JTextField();
        txtBelgeOnaylamaIsteyenAdi = new javax.swing.JTextField();
        txtBelgeOnaylamaGuncellemeTarihi = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtBelgeOnaylamaReddetmeSebebi = new javax.swing.JTextArea();
        btnBelgeOnaylamaKabulEt = new javax.swing.JButton();
        btnBelgeOnaylamaReddet = new javax.swing.JButton();
        pnlBelgeler = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblBelgeler = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtBelgeArama = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
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

        tabKutuphane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabKutuphaneMouseClicked(evt);
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
        pnlResim.add(lblResim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 270));

        btnKaydet.setForeground(new java.awt.Color(153, 255, 51));
        btnKaydet.setText("Kaydet");
        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaydetActionPerformed(evt);
            }
        });

        lblKitapResmi1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblKitapResmi1.setText("Kitap Özeti");

        txtKitapOzet.setColumns(20);
        txtKitapOzet.setRows(5);
        jScrollPane14.setViewportView(txtKitapOzet);

        javax.swing.GroupLayout pnlKitapEklemeLayout = new javax.swing.GroupLayout(pnlKitapEkleme);
        pnlKitapEkleme.setLayout(pnlKitapEklemeLayout);
        pnlKitapEklemeLayout.setHorizontalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addComponent(jScrollPane14)
                        .addGap(333, 333, 333))
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYazarAdSoyad)
                            .addComponent(lblKitapAdi)
                            .addComponent(lblKitapKodu)
                            .addComponent(lblYayinEvi)
                            .addComponent(lblKitapTuru)
                            .addComponent(lblKitapResmi1)
                            .addComponent(lblKitapResmi))
                        .addGap(16, 16, 16)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtYazarAdSoyad)
                            .addComponent(txtKitapAdi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtKitapKodu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtYayinEvi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                                        .addComponent(btnGoruntuSec)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(pnlResim, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        pnlKitapEklemeLayout.setVerticalGroup(
            pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYazarAdSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlKitapEklemeLayout.createSequentialGroup()
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKitapTuru)
                            .addComponent(cbKitapTuru, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(pnlKitapEklemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKitapResmi)
                            .addComponent(btnGoruntuSec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblKitapResmi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblYazarAdSoyad.getAccessibleContext().setAccessibleName("lblYazarAdSoyad");

        tabKutuphane.addTab("Kitap Ekleme", null, pnlKitapEkleme, "Envantere Yeni Kitap Eklemek İçin Kullanırsınız");

        tblKitapOnay.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKitapOnay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapOnayMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblKitapOnay);

        jLabel3.setText("Eldeki Kitapların Kodları");

        txtToplamKitap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtToplamKitap.setEnabled(false);

        jLabel4.setText("Toplam Kitap");

        txtEnvanterdeKalan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEnvanterdeKalan.setEnabled(false);

        jLabel5.setText(" Envanterde Kalan ");

        txtKitapReddetmeSebep.setColumns(20);
        txtKitapReddetmeSebep.setRows(5);
        txtKitapReddetmeSebep.setName("Sebep"); // NOI18N
        jScrollPane7.setViewportView(txtKitapReddetmeSebep);

        btnKitapOnayla.setForeground(new java.awt.Color(153, 255, 153));
        btnKitapOnayla.setText("Onayla");
        btnKitapOnayla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapOnaylaActionPerformed(evt);
            }
        });

        btnKitapReddet.setForeground(new java.awt.Color(255, 51, 51));
        btnKitapReddet.setText("Reddet");
        btnKitapReddet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapReddetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKitapOnaylamaLayout = new javax.swing.GroupLayout(pnlKitapOnaylama);
        pnlKitapOnaylama.setLayout(pnlKitapOnaylamaLayout);
        pnlKitapOnaylamaLayout.setHorizontalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKitapKodları, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)))
                .addGap(54, 54, 54)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEnvanterdeKalan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKitapOnayla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKitapReddet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlKitapOnaylamaLayout.setVerticalGroup(
            pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnKitapOnayla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlKitapOnaylamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbKitapKodları, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtToplamKitap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEnvanterdeKalan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKitapReddet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(pnlKitapOnaylamaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)
                        .addContainerGap())))
        );

        tabKutuphane.addTab("Kitap Onaylama", null, pnlKitapOnaylama, "Kullanıcılardan Gelen Kitap İsteklerini Cevaplayabilirsiniz");

        tblKitaplar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kitap Kodu", "Kitap Adı", "Yazar", "Yayın Evi", "Türü", "Okunma Sayısı"
            }
        ));
        tblKitaplar.setToolTipText("");
        tblKitaplar.setRowHeight(40);
        jScrollPane2.setViewportView(tblKitaplar);

        txtKitapArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtKitapAramaPropertyChange(evt);
            }
        });

        jLabel6.setText("Tabloda Arama Yapın");

        javax.swing.GroupLayout pnlKitaplarLayout = new javax.swing.GroupLayout(pnlKitaplar);
        pnlKitaplar.setLayout(pnlKitaplarLayout);
        pnlKitaplarLayout.setHorizontalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitaplarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtKitapArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlKitaplarLayout.setVerticalGroup(
            pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitaplarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKitapArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        tabKutuphane.addTab("Kitaplar", null, pnlKitaplar, "Envanterdeki Kitapları Görüntülersiniz");

        getContentPane().add(tabKutuphane, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 788, 530));
        tabKutuphane.getAccessibleContext().setAccessibleName("Kütüphaneci Paneli");
        tabKutuphane.getAccessibleContext().setAccessibleDescription("");

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

        tabDiger.addTab("Üye İşlemleri", null, pnlUyeIslemleri, "Üyelerin Bilgilerini Güncelleyip Yeni Admin Ekleyebilirsiniz");

        tblRandevular.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRandevular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRandevularMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblRandevular);

        btnKabul.setText("Kabul Et");
        btnKabul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabulActionPerformed(evt);
            }
        });

        btnRet.setText("Reddet");
        btnRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetActionPerformed(evt);
            }
        });

        txtSebep.setColumns(20);
        txtSebep.setRows(5);
        jScrollPane4.setViewportView(txtSebep);

        jLabel2.setText("Sebep");

        txtRandevuIsteyenKisi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtRandevuTarih.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtKonu.setColumns(20);
        txtKonu.setRows(5);
        jScrollPane5.setViewportView(txtKonu);

        javax.swing.GroupLayout pnlRandevularLayout = new javax.swing.GroupLayout(pnlRandevular);
        pnlRandevular.setLayout(pnlRandevularLayout);
        pnlRandevularLayout.setHorizontalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(pnlRandevularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRandevuIsteyenKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKabul))
                .addGap(18, 18, 18)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRandevularLayout.createSequentialGroup()
                        .addComponent(btnRet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(txtRandevuTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        pnlRandevularLayout.setVerticalGroup(
            pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevularLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRandevuTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRandevuIsteyenKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRandevularLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevularLayout.createSequentialGroup()
                        .addGroup(pnlRandevularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(btnRet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKabul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );

        tabDiger.addTab("Randevular", null, pnlRandevular, "Kullanıcılardan Gelen Randevu Taleplerini Cevaplayabilirsiniz");

        cbDuyuruKisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bütün Kullanıcılar", "Belirli Bir Kullanıcı" }));
        cbDuyuruKisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDuyuruKisiActionPerformed(evt);
            }
        });

        tblKullanicilar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tblKullanicilar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKullanicilarMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblKullanicilar);

        txtDuyuru.setColumns(20);
        txtDuyuru.setRows(5);
        jScrollPane16.setViewportView(txtDuyuru);

        jLabel8.setText("Duyurunun Başlığı");

        jLabel9.setText("Kullanıcı Arama");

        jLabel10.setText("Gönderilecek Kişi");

        btnDuyuruYayinla.setText("Duyuruyu Yayınla");
        btnDuyuruYayinla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuyuruYayinlaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDuyurularLayout = new javax.swing.GroupLayout(pnlDuyurular);
        pnlDuyurular.setLayout(pnlDuyurularLayout);
        pnlDuyurularLayout.setHorizontalGroup(
            pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuyurularLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDuyurularLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel9)
                        .addGap(100, 100, 100))
                    .addGroup(pnlDuyurularLayout.createSequentialGroup()
                        .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlDuyurularLayout.createSequentialGroup()
                                .addComponent(txtDuyuruBaslik, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbDuyuruKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDuyurularLayout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(btnDuyuruYayinla, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDuyurularLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuyurularLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtKullaniciBul, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))))
        );
        pnlDuyurularLayout.setVerticalGroup(
            pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuyurularLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKullaniciBul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDuyuruKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDuyuruBaslik, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuyurularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDuyurularLayout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnDuyuruYayinla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabDiger.addTab("Duyurular", null, pnlDuyurular, "Yeni Duyuru Ekleyebilirsiniz");

        lblRenk4Adi.setText("jLabel6");

        lblRenk3Adi.setText("jLabel6");

        lblRenk2Adi.setText("jLabel6");

        lblRenk1Adi.setText("jLabel6");

        javax.swing.GroupLayout pnlRenk1Layout = new javax.swing.GroupLayout(pnlRenk1);
        pnlRenk1.setLayout(pnlRenk1Layout);
        pnlRenk1Layout.setHorizontalGroup(
            pnlRenk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk1Layout.setVerticalGroup(
            pnlRenk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlRenk2Layout = new javax.swing.GroupLayout(pnlRenk2);
        pnlRenk2.setLayout(pnlRenk2Layout);
        pnlRenk2Layout.setHorizontalGroup(
            pnlRenk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk2Layout.setVerticalGroup(
            pnlRenk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlRenk3Layout = new javax.swing.GroupLayout(pnlRenk3);
        pnlRenk3.setLayout(pnlRenk3Layout);
        pnlRenk3Layout.setHorizontalGroup(
            pnlRenk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk3Layout.setVerticalGroup(
            pnlRenk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlRenk4Layout = new javax.swing.GroupLayout(pnlRenk4);
        pnlRenk4.setLayout(pnlRenk4Layout);
        pnlRenk4Layout.setHorizontalGroup(
            pnlRenk4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        pnlRenk4Layout.setVerticalGroup(
            pnlRenk4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPastaBilgilerLayout = new javax.swing.GroupLayout(pnlPastaBilgiler);
        pnlPastaBilgiler.setLayout(pnlPastaBilgilerLayout);
        pnlPastaBilgilerLayout.setHorizontalGroup(
            pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPastaBilgilerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPastaBilgilerLayout.createSequentialGroup()
                        .addComponent(pnlRenk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblRenk1Adi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPastaBilgilerLayout.createSequentialGroup()
                        .addComponent(pnlRenk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblRenk2Adi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPastaBilgilerLayout.createSequentialGroup()
                        .addComponent(pnlRenk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblRenk3Adi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPastaBilgilerLayout.createSequentialGroup()
                        .addComponent(pnlRenk4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRenk4Adi, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        pnlPastaBilgilerLayout.setVerticalGroup(
            pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPastaBilgilerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRenk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRenk1Adi))
                .addGap(10, 10, 10)
                .addGroup(pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRenk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRenk2Adi))
                .addGap(10, 10, 10)
                .addGroup(pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRenk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRenk3Adi))
                .addGap(10, 10, 10)
                .addGroup(pnlPastaBilgilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRenk4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRenk4Adi))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pieChart1.add(pnlPastaBilgiler);
        pnlPastaBilgiler.setBounds(570, 10, 210, 130);

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        tabDiger.addTab("Dashboard", null, pnlDashboard, "Sistem Hakkındaki Analiz Oranları Görüntülenir");

        getContentPane().add(tabDiger, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 788, 530));

        lblBelgeAdi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblBelgeAdi.setText("Belgenin Adı");

        lblYayinlayanAdi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblYayinlayanAdi.setText("Yayınlayan Adı");

        lblBelgeYayinTarihi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblBelgeYayinTarihi.setText("Yayın Yılı");

        lblBelgeKodu.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblBelgeKodu.setText("Belge Kodu");

        lblBelgeNushasi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblBelgeNushasi.setText("Belgenin Nüshası");

        btnBelgeSec.setText("Dosya Seç");
        btnBelgeSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBelgeSecActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Belgenin Türü");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Tarihi Belgeler");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Mektup");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Günlük");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gazete Küpürü");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Antlaşma");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fotoğraf");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Harita");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Resmi Belge");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Yasa - Karar");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Doğum - Ölüm");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sicil Kaydı");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Akademik Belgeler");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tez");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Makale");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Bildirge");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sunum");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Öğrenci Belgesi");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Kişisel Belgeler");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Kimlik");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Pasaport");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Diploma");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Mektup");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Günlük");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Sanatsal Belgeler");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Resim");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Heykel");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fotoğraf");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Müzik Notaları");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("İş İle İlgili Belgeler");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fatura");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sözleşme");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Raporlar");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Ticari Yazışmalar");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeBelgeTuru.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeBelgeTuru.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeBelgeTuruValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(treeBelgeTuru);

        lblBelgeNushasiAdi.setForeground(new java.awt.Color(255, 0, 51));
        lblBelgeNushasiAdi.setText("Henüz Belge Seçmediniz");

        btnBelgeEkle.setText("Belgeyi Ekle");
        btnBelgeEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBelgeEkleActionPerformed(evt);
            }
        });

        lblBelgeNushasi1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblBelgeNushasi1.setText("Belge Tarama");

        btnTaranacakBelge.setText("Belge Tara");
        btnTaranacakBelge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaranacakBelgeActionPerformed(evt);
            }
        });

        txtTarananBelge.setColumns(20);
        txtTarananBelge.setRows(5);
        txtTarananBelge.setText("Taranan Belge Sonuçları\n");
        jScrollPane13.setViewportView(txtTarananBelge);

        javax.swing.GroupLayout pnlBelgeEkleLayout = new javax.swing.GroupLayout(pnlBelgeEkle);
        pnlBelgeEkle.setLayout(pnlBelgeEkleLayout);
        pnlBelgeEkleLayout.setHorizontalGroup(
            pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBelgeNushasi)
                            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnBelgeSec, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblBelgeNushasi1)
                                .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                                    .addGap(141, 141, 141)
                                    .addComponent(btnTaranacakBelge, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(lblBelgeNushasiAdi)
                                .addGap(88, 88, 88))
                            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnBelgeEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBelgeYayinTarihi)
                            .addComponent(lblBelgeKodu)
                            .addComponent(lblYayinlayanAdi)
                            .addComponent(lblBelgeAdi))
                        .addGap(36, 36, 36)
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBelgeAdi)
                            .addComponent(txtYayinlayanAdi)
                            .addComponent(txtBelgeKodu)
                            .addComponent(txtYayinYili))))
                .addContainerGap())
        );
        pnlBelgeEkleLayout.setVerticalGroup(
            pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBelgeAdi)
                    .addComponent(txtBelgeAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYayinlayanAdi)
                    .addComponent(txtYayinlayanAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBelgeKodu)
                    .addComponent(txtBelgeKodu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYayinYili, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBelgeYayinTarihi))
                .addGap(18, 18, 18)
                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBelgeNushasi)
                            .addComponent(btnBelgeSec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBelgeNushasiAdi))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBelgeEkleLayout.createSequentialGroup()
                                .addGroup(pnlBelgeEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblBelgeNushasi1)
                                    .addComponent(btnTaranacakBelge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBelgeEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabArsiv.addTab("Belge Ekle", null, pnlBelgeEkle, "Arşive Yeni Belge Eklenir");

        pnlBelgeOnayla.setMinimumSize(new java.awt.Dimension(788, 518));
        pnlBelgeOnayla.setPreferredSize(new java.awt.Dimension(788, 518));
        pnlBelgeOnayla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBelgeOnaylama.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBelgeOnaylama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBelgeOnaylamaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblBelgeOnaylama);

        pnlBelgeOnayla.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 788, 330));

        txtIstekSebebi.setColumns(20);
        txtIstekSebebi.setRows(5);
        txtIstekSebebi.setText("Belge İsteme Sebebi");
        txtIstekSebebi.setEnabled(false);
        jScrollPane11.setViewportView(txtIstekSebebi);

        pnlBelgeOnayla.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 200, 164));

        txtBelgeOnaylamaBelgeKodu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeOnaylamaBelgeKodu.setText("Belge Kodu");
        txtBelgeOnaylamaBelgeKodu.setEnabled(false);
        pnlBelgeOnayla.add(txtBelgeOnaylamaBelgeKodu, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 342, 200, 30));

        txtBelgeOnaylamaBelgeAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeOnaylamaBelgeAdi.setText("Belge Adi");
        txtBelgeOnaylamaBelgeAdi.setEnabled(false);
        pnlBelgeOnayla.add(txtBelgeOnaylamaBelgeAdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 384, 200, 30));

        txtBelgeOnaylamaIsteyenAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeOnaylamaIsteyenAdi.setText("İsteyen Adı");
        txtBelgeOnaylamaIsteyenAdi.setEnabled(false);
        pnlBelgeOnayla.add(txtBelgeOnaylamaIsteyenAdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 427, 200, 30));

        txtBelgeOnaylamaGuncellemeTarihi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeOnaylamaGuncellemeTarihi.setText("Güncelleme Tarihi");
        txtBelgeOnaylamaGuncellemeTarihi.setEnabled(false);
        pnlBelgeOnayla.add(txtBelgeOnaylamaGuncellemeTarihi, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 467, 200, 24));

        txtBelgeOnaylamaReddetmeSebebi.setColumns(20);
        txtBelgeOnaylamaReddetmeSebebi.setRows(5);
        jScrollPane12.setViewportView(txtBelgeOnaylamaReddetmeSebebi);

        pnlBelgeOnayla.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 342, 200, 164));

        btnBelgeOnaylamaKabulEt.setForeground(new java.awt.Color(153, 255, 51));
        btnBelgeOnaylamaKabulEt.setText("Kabul Et");
        btnBelgeOnaylamaKabulEt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBelgeOnaylamaKabulEtActionPerformed(evt);
            }
        });
        pnlBelgeOnayla.add(btnBelgeOnaylamaKabulEt, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 385, 128, 30));

        btnBelgeOnaylamaReddet.setForeground(new java.awt.Color(255, 51, 51));
        btnBelgeOnaylamaReddet.setText("Reddet");
        pnlBelgeOnayla.add(btnBelgeOnaylamaReddet, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 428, 128, 30));

        tabArsiv.addTab("Belge Onaylama", pnlBelgeOnayla);

        tblBelgeler.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBelgeler.setRowHeight(40);
        jScrollPane9.setViewportView(tblBelgeler);

        jLabel7.setText("Tabloda Arama Yapın");

        txtBelgeArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtBelgeAramaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlBelgelerLayout = new javax.swing.GroupLayout(pnlBelgeler);
        pnlBelgeler.setLayout(pnlBelgelerLayout);
        pnlBelgelerLayout.setHorizontalGroup(
            pnlBelgelerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgelerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtBelgeArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBelgelerLayout.setVerticalGroup(
            pnlBelgelerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgelerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBelgelerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBelgeArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabArsiv.addTab("Envanterdeki Belgeler", pnlBelgeler);

        getContentPane().add(tabArsiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 800, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
///////////////////////////////////////////////// Veritabanından Tema Rengi Çekme Başlangıç /////////////////////////////////////////////////

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
///////////////////////////////////////////////// Veritabanından Tema Rengi Çekme Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Listeden Tema Rengi Çekme Başlangıç /////////////////////////////////////////////////    
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
///////////////////////////////////////////////// Listeden Tema Rengi Çekme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Ayarlar Kaptma Başlangıç /////////////////////////////////////////////////      
    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        tabKutuphane.setVisible(true);
        lblArsiv.setVisible(true);
        lblDiger.setVisible(true);
        lblKutuphane.setVisible(true);
    }//GEN-LAST:event_pnlSettingsKapatMouseClicked
///////////////////////////////////////////////// Ayarlar Kaptma Bitiş /////////////////////////////////////////////////  

    private void lstRenkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstRenkMouseClicked
        RenkSecmeListe();
    }//GEN-LAST:event_lstRenkMouseClicked

///////////////////////////////////////////////// Ayarlar Açma Başlangıç /////////////////////////////////////////////////    
    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        tabKutuphane.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
        lblArsiv.setVisible(false);
        lblDiger.setVisible(false);
        lblKutuphane.setVisible(false);
    }//GEN-LAST:event_lblSettingsMouseClicked
///////////////////////////////////////////////// Ayarlar Açma Bitiş /////////////////////////////////////////////////    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        txtKitapArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitaplar, txtKitapArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitaplar, txtKitapArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitaplar, txtKitapArama);
            }

        });

        txtBelgeArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                ArsivTabloVerileri();
                search(tblBelgeler, txtBelgeArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                ArsivTabloVerileri();
                search(tblBelgeler, txtBelgeArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ArsivTabloVerileri();
                search(tblBelgeler, txtBelgeArama);
            }

        });
        txtKullaniciBul.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                KullaniciCekme();
                search(tblKullanicilar, txtKullaniciBul);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                KullaniciCekme();
                search(tblKullanicilar, txtKullaniciBul);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                KullaniciCekme();
                search(tblKullanicilar, txtKullaniciBul);
            }

        });
    }//GEN-LAST:event_formWindowActivated
///////////////////////////////////////////////// Şeçilen Rengi Veritabanına Kaydetme Başlangıç /////////////////////////////////////////////////        
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
///////////////////////////////////////////////// Şeçilen Rengi Veritabanına Kaydetme Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Çıkış Log Kaydı Başlangıç /////////////////////////////////////////////////    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DosyaSilme();
        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            String sqllog = "INSERT INTO public.kullanici_log(adsoyad, email, ip, islem)VALUES ((Select adsoyad from public.kullanicilar WHERE email='" + email + "'),'" + email + "', '" + ip.getHostAddress() + "','Çıkış');";
            pst = conn.prepareStatement(sqllog);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                //JOptionPane.showConfirmDialog(null, "Log Kayıt Başarılı");

            } else {
                JOptionPane.showConfirmDialog(null, " Log Kayıt Başarısız");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing
///////////////////////////////////////////////// Çıkış Log Kaydı Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Resim Seçme Başlangıç /////////////////////////////////////////////////  
    private void btnGoruntuSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoruntuSecActionPerformed
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.showOpenDialog(null);
        dosya = chooser.getSelectedFile();
        String eskidosyaadi = dosya.getAbsolutePath();
        dosyaadi = eskidosyaadi.replace("\\", "\\\\");
        ImageIcon resim = new ImageIcon(dosyaadi);
        Image image = resim.getImage().getScaledInstance(pnlResim.getWidth(), pnlResim.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon boyutlanmisresim = new ImageIcon(image);
        lblResim.setBounds(pnlResim.getBounds());
        //pnlResim.add(lblResim);
        lblResim.setIcon(boyutlanmisresim);
        System.out.println(dosyaadi.toString());
    }//GEN-LAST:event_btnGoruntuSecActionPerformed
///////////////////////////////////////////////// Resim Seçme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Envantere Kaydetme Başlangıç /////////////////////////////////////////////////  
    public void EnvantereEkle(String kitapadi, int kitapkodu, int count, String yayinevi) {
        if (count == 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO kitap_envanter (kitap_adi, kitap_sayisi, kitap_yayinevi,elde_olan) VALUES (?, ?, ?, ?)");
                ps.setString(1, kitapadi);
                ps.setInt(2, 1);
                ps.setString(3, yayinevi);
                ps.setInt(4, 1);
                int sonuc = ps.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Eklendi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Eklenmedi");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (count > 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE public.kitap_envanter SET kitap_sayisi=?,elde_olan = elde_olan + 1 WHERE kitap_yayinevi =? and kitap_adi =?;");
                ps.setInt(1, count);
                ps.setString(2, yayinevi);
                ps.setString(3, kitapadi);
                int sonuc = ps.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Güncellendi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Envantere Güncellenmedi");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
///////////////////////////////////////////////// Envantere Kaydetme Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Kitap Veritabanına Kaydetme Başlangıç /////////////////////////////////////////////////      
    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        try {
            String yazaradsoyad = txtYazarAdSoyad.getText();
            String kitapadi = txtKitapAdi.getText();
            String yayinevi = txtYayinEvi.getText();
            int kitapkodu = Integer.parseInt(txtKitapKodu.getText());
            String kitapturu = cbKitapTuru.getSelectedItem().toString();
            String kitapozet = txtKitapOzet.getText();

            String sqlkitapkodu = "Select COUNT(kitap_kodu) from public.kitaplik where kitap_kodu = ?;";
            pst = conn.prepareStatement(sqlkitapkodu);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            int sayi = 0;
            if (rs.next()) {
                sayi = rs.getInt("count");
            }
            if (sayi == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Zaten Envantere Eklenmiş");
            } else {
                try {
                    fis = new FileInputStream(dosya);
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO kitaplik (yazar_adsoyad, kitap_adi, kitap_resim, kitap_kodu, yayin_evi, kitap_turu, kitap_ozet) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setString(1, yazaradsoyad);
                    ps.setString(2, kitapadi);
                    ps.setBinaryStream(3, fis, dosya.length());
                    ps.setInt(4, kitapkodu);
                    ps.setString(5, yayinevi);
                    ps.setString(6, kitapturu);
                    ps.setString(7, kitapozet);
                    int sonuc = ps.executeUpdate();
                    if (sonuc == 1) {
                        fis.close();
                        ps.close();
                        PreparedStatement ps2 = conn.prepareStatement("SELECT Count(kitap_adi) FROM public.kitaplik where kitap_adi =? and yayin_evi =?;");
                        ps2.setString(1, kitapadi);
                        ps2.setString(2, yayinevi);
                        rs = ps2.executeQuery();

                        if (rs.next()) {
                            int count = rs.getInt("count");
                            EnvantereEkle(kitapadi, kitapkodu, count, yayinevi);
                        }

                        KitaplarTabloVerileri();
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
            }

            String destinationPath = "C:\\Users\\ekmn2\\OneDrive\\Belgeler\\New Folder\\Kutuphane\\pic\\";
            try {
                File destinationFile = new File(destinationPath, txtKitapAdi.getText() + ".jpg");
                FileInputStream fis = new FileInputStream(dosya);
                FileOutputStream fos = new FileOutputStream(destinationFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                fis.close();
                fos.close();

                JOptionPane.showMessageDialog(null, "Dosya başarıyla kaydedildi.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKaydetActionPerformed
///////////////////////////////////////////////// Kitap Veritabanına Kaydetme Bitiş ///////////////////////////////////////////////// 

    private void tabKutuphaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabKutuphaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabKutuphaneMouseClicked
///////////////////////////////////////////////// Randevu Kabul Başlangıç ///////////////////////////////////////////////// 
    private void btnKabulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabulActionPerformed
        int tabloIndex = tblRandevular.getSelectedRow();
        String randevuisteyenkisi = (String) tblRandevular.getValueAt(tabloIndex, 0);
        String randevukonu = (String) tblRandevular.getValueAt(tabloIndex, 1);
        String randevutarih = (String) tblRandevular.getValueAt(tabloIndex, 2) + ":00";
        Timestamp timestamp = Timestamp.valueOf(randevutarih);
        if (tabloIndex >= 0) {
            try {
                try {
                    String sqldelete = "DELETE FROM public.randevu WHERE randevu_isteyen_adsoyad = ? and randevu_konusu = ? and randevu_talep_tarihi = ?;";
                    pst = conn.prepareStatement(sqldelete);
                    pst.setString(1, randevuisteyenkisi);
                    pst.setString(2, randevukonu);
                    pst.setTimestamp(3, timestamp);
                    int sonucdelete = pst.executeUpdate();
                    if (sonucdelete == 1) {
                        JOptionPane.showMessageDialog(null, "Silindi");
                        RandevuTabloVerileri();
                    } else {
                        JOptionPane.showMessageDialog(null, "Silinmedi");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
                String sqlinsert = "INSERT INTO public.randevu_kabul(randevu_kabul_isteyen_adsoyad, randevu_kabul_isteyen_email, randevu_kabul_veren_adsoyad, randevu_kabul_veren_email, randevu_kabul_konusu, randevu_kabul_talep_tarihi, randevu_kabul_durum) VALUES (?, (Select email from public.kullanicilar where adsoyad = ?), (Select adsoyad from public.kullanicilar where email = ?), ?, ?, ?, ?);";
                pst = conn.prepareStatement(sqlinsert);
                pst.setString(1, randevuisteyenkisi);
                pst.setString(2, randevuisteyenkisi);
                pst.setString(3, email);
                pst.setString(4, email);
                pst.setString(5, randevukonu);
                pst.setTimestamp(6, timestamp);
                pst.setString(7, "Kabul Edildi");
                int sonucinsert = pst.executeUpdate();
                if (sonucinsert == 1) {
                    JOptionPane.showMessageDialog(null, "İnsert oldu");
                } else {
                    JOptionPane.showMessageDialog(null, "İnsert Olmadı");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Randevu Seçilmedi");
        }
    }//GEN-LAST:event_btnKabulActionPerformed
///////////////////////////////////////////////// Randevu Kabul Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Randevu Tablosu Tıklama Başlangıç /////////////////////////////////////////////////
    private void tblRandevularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRandevularMouseClicked
        int tabloIndex = tblRandevular.getSelectedRow();
        txtRandevuIsteyenKisi.setText((String) tblRandevular.getValueAt(tabloIndex, 0));
        txtKonu.setText((String) tblRandevular.getValueAt(tabloIndex, 1));
        txtRandevuTarih.setText((String) tblRandevular.getValueAt(tabloIndex, 2));
    }//GEN-LAST:event_tblRandevularMouseClicked
///////////////////////////////////////////////// Randevu Kabul Bitiş /////////////////////////////////////////////////7

///////////////////////////////////////////////// Randevu Reddetme Başlangıç /////////////////////////////////////////////////    
    private void btnRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetActionPerformed
        if (!txtSebep.getText().isEmpty()) {
            int tabloIndex = tblRandevular.getSelectedRow();
            String randevuisteyenkisi = (String) tblRandevular.getValueAt(tabloIndex, 0);
            String randevukonu = (String) tblRandevular.getValueAt(tabloIndex, 1);
            String randevutarih = (String) tblRandevular.getValueAt(tabloIndex, 2) + ":00";
            Timestamp timestamp = Timestamp.valueOf(randevutarih);
            if (tabloIndex >= 0) {
                try {
                    try {
                        String sqldelete = "DELETE FROM public.randevu WHERE randevu_isteyen_adsoyad = ? and randevu_konusu = ? and randevu_talep_tarihi = ?;";
                        pst = conn.prepareStatement(sqldelete);
                        pst.setString(1, randevuisteyenkisi);
                        pst.setString(2, randevukonu);
                        pst.setTimestamp(3, timestamp);
                        int sonucdelete = pst.executeUpdate();
                        if (sonucdelete == 1) {
                            JOptionPane.showMessageDialog(null, "Silindi");
                            RandevuTabloVerileri();
                        } else {
                            JOptionPane.showMessageDialog(null, "Silinmedi");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String sqlinsert = "INSERT INTO public.randevu_ret(randevu_ret_isteyen_adsoyad, randevu_ret_isteyen_email, randevu_ret_veren_adsoyad, randevu_ret_veren_email, randevu_ret_konusu, randevu_ret_talep_tarihi, randevu_ret_durum, randevu_ret_sebep) VALUES (?, (Select email from public.kullanicilar where adsoyad = ?), (Select adsoyad from public.kullanicilar where email = ?), ?, ?, ?, ?, ?);";
                    pst = conn.prepareStatement(sqlinsert);
                    pst.setString(1, randevuisteyenkisi);
                    pst.setString(2, randevuisteyenkisi);
                    pst.setString(3, email);
                    pst.setString(4, email);
                    pst.setString(5, randevukonu);
                    pst.setTimestamp(6, timestamp);
                    pst.setString(7, "Reddedildi");
                    pst.setString(8, txtSebep.getText());
                    int sonucinsert = pst.executeUpdate();
                    if (sonucinsert == 1) {
                        JOptionPane.showMessageDialog(null, "İnsert oldu");
                    } else {
                        JOptionPane.showMessageDialog(null, "İnsert Olmadı");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Randevu Seçilmedi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Litfen Bir Sebep Giriniz");
        }
    }//GEN-LAST:event_btnRetActionPerformed
///////////////////////////////////////////////// Randevu Reddetme Bitiş /////////////////////////////////////////////////   

///////////////////////////////////////////////// Kitap Onay Tablosu Tıklma Başlangıç /////////////////////////////////////////////////   
    private void tblKitapOnayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapOnayMouseClicked
        try {
            int kitaponayindex = tblKitapOnay.getSelectedRow();
            String kitapadi = (String) tblKitapOnay.getValueAt(kitaponayindex, 0);
            kitapyayinevi = (String) tblKitapOnay.getValueAt(kitaponayindex, 1);
            kitapisteyenadsoyad = (String) tblKitapOnay.getValueAt(kitaponayindex, 2);
            kitapisteyenemail = (String) tblKitapOnay.getValueAt(kitaponayindex, 3);
            kitapgetirmetarih = (String) tblKitapOnay.getValueAt(kitaponayindex, 4) + ":00";

            String sql = "SELECT k.kitap_kodu,(Select e.kitap_sayisi from public.kitap_envanter e where k.kitap_adi = e.kitap_adi and k.yayin_evi = e.kitap_yayinevi),(Select e.elde_olan from public.kitap_envanter e where k.kitap_adi = e.kitap_adi and k.yayin_evi = e.kitap_yayinevi) FROM public.kitaplik k where k.kitap_adi = ? and k.yayin_evi = ? and k.kitap_durum = 'Envanterde' Order By k.kitap_kodu ASC;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapadi);
            pst.setString(2, kitapyayinevi);
            rs = pst.executeQuery();

            while (rs.next()) {
                cbKitapKodları.addItem(rs.getString("kitap_kodu"));
                txtEnvanterdeKalan.setText(rs.getString("elde_olan"));
                txtToplamKitap.setText(rs.getString("kitap_sayisi"));
            }

            String sqlidalma = "Select kitap_al_istek_id from public.kitap_al_istek where kitap_al_istek_geri_verme_tarihi=?";
            pst = conn.prepareStatement(sqlidalma);
            SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = girisFormat.parse(kitapgetirmetarih);
            String songerigetirmeDate = cikisFormat.format(date2);
            Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
            pst.setTimestamp(1, timestamp);
            rs = pst.executeQuery();
            if (rs.next()) {
                secilenkitapid = rs.getInt("kitap_al_istek_id");
                System.out.println(secilenkitapid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblKitapOnayMouseClicked
///////////////////////////////////////////////// Kitap Onay Tablosu Tıklma Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Kitap Onaylama Buton Başlangıç /////////////////////////////////////////////////
    private void btnKitapOnaylaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapOnaylaActionPerformed
        try {
            int kitapkodu = Integer.parseInt(cbKitapKodları.getSelectedItem().toString());
            String kitapadi = null;
            String sqlkitpadi = "Select kitap_adi from public.kitaplik where kitap_kodu = ?";
            pst = conn.prepareStatement(sqlkitpadi);
            pst.setInt(1, kitapkodu);
            rs = pst.executeQuery();
            if (rs.next()) {
                kitapadi = rs.getString("kitap_adi");
            }

            String kitapverenadsoyad = "(Select adsoyad from public.kullanicilar where email = ?)";

            String sql = "INSERT INTO public.kitap_al_kabul( kitap_al_kabul_kitap_kodu, kitap_al_kabul_kitap_adi, kitap_al_kabul_isteyen_ad_soyad, kitap_al_kabul_isteyen_email, kitap_al_kabul_veren_ad_soyad, kitap_al_kabul_veren_email, kitap_al_kabul_durum, kitap_al_kabul_geri_getirme_tarihi, kitap_al_kabul_kitap_yayinevi) VALUES (?, ?, ?, ?," + kitapverenadsoyad + ",  ?, 'Kabul Edildi', ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, kitapkodu);
            pst.setString(2, kitapadi);
            pst.setString(3, kitapisteyenadsoyad);
            pst.setString(4, kitapisteyenemail);
            pst.setString(5, email);
            pst.setString(6, email);
            SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = girisFormat.parse(kitapgetirmetarih);
            String songerigetirmeDate = cikisFormat.format(date2);
            Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
            pst.setTimestamp(7, timestamp);
            pst.setString(8, kitapyayinevi);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi");
            } else {
                JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi hata");
            }
            KitapIstekKaldirma(secilenkitapid, "Sonuçlandırıldı", kitapkodu, kitapadi, true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKitapOnaylaActionPerformed
///////////////////////////////////////////////// Kitap Onaylama Buton Bitiş /////////////////////////////////////////////////

///////////////////////////////////////////////// Kitap Reddetme Buton Başlangıç /////////////////////////////////////////////////
    private void btnKitapReddetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapReddetActionPerformed
        if (txtKitapReddetmeSebep.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütfen Reddedilme Sebebi Giriniz");
        } else {
            try {
                int kitapkodu = Integer.parseInt(cbKitapKodları.getSelectedItem().toString());
                String sebep = txtKitapReddetmeSebep.getText();

                String kitapadi = null;
                String sqlkitpadi = "Select kitap_adi from public.kitaplik where kitap_kodu = ?";
                pst = conn.prepareStatement(sqlkitpadi);
                pst.setInt(1, kitapkodu);
                rs = pst.executeQuery();
                if (rs.next()) {
                    kitapadi = rs.getString("kitap_adi");
                }
                String kitapverenadsoyad = "(Select adsoyad from public.kullanicilar where email = ?)";

                String sql = "INSERT INTO public.kitap_al_ret(kitap_al_ret_kitap_adi, kitap_al_ret_isteyen_ad_soyad, kitap_al_ret_isteyen_email, kitap_al_ret_veren_ad_soyad, kitap_al_ret_veren_email, kitap_al_ret_durum, kitap_al_ret_geri_getirme_tarihi, kitap_al_ret_kitap_yayinevi, kitap_al_ret_sebep) VALUES (?, ?, ?," + kitapverenadsoyad + ",  ?, 'Reddedildi', ?, ?, ?);";
                pst = conn.prepareStatement(sql);
                pst.setString(1, kitapadi);
                pst.setString(2, kitapisteyenadsoyad);
                pst.setString(3, kitapisteyenemail);
                pst.setString(4, email);
                pst.setString(5, email);
                SimpleDateFormat girisFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat cikisFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = girisFormat.parse(kitapgetirmetarih);
                String songerigetirmeDate = cikisFormat.format(date2);
                Timestamp timestamp = Timestamp.valueOf(songerigetirmeDate);
                pst.setTimestamp(6, timestamp);
                pst.setString(7, kitapyayinevi);
                pst.setString(8, sebep);
                int sonuc = pst.executeUpdate();
                if (sonuc == 1) {
                    JOptionPane.showMessageDialog(null, "Kitap Reddedildi");
                } else {
                    JOptionPane.showMessageDialog(null, "Kitap Kabul Edildi hata");
                }
                KitapIstekKaldirma(secilenkitapid, "Sonuçlandırıldı", kitapkodu, kitapadi, false);

            } catch (SQLException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnKitapReddetActionPerformed
/////////////////////////////////////////////// Kitap Reddetme Buton Bitiş /////////////////////////////////////////////////

/////////////////////////////////////////////// Kütüphane Bölümü Açma Başlangıç /////////////////////////////////////////////////    
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
/////////////////////////////////////////////// Kütüphane Bölümü Açma Bitiş /////////////////////////////////////////////////

/////////////////////////////////////////////// Arşiv Bölümü Açma Başlangıç /////////////////////////////////////////////////    
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
/////////////////////////////////////////////// Arşiv Bölümü Açma Bitiş /////////////////////////////////////////////////

/////////////////////////////////////////////// Diğer Bölümü Açma Başlangıç /////////////////////////////////////////////////
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
/////////////////////////////////////////////// Diğer Bölümü Açma Bitiş /////////////////////////////////////////////////

/////////////////////////////////////////////// Belge Ekle Başlangıç /////////////////////////////////////////////////
    private void btnBelgeEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBelgeEkleActionPerformed
        try {
            String belgeadi = txtBelgeAdi.getText();
            String yayinlayanadi = txtYayinlayanAdi.getText();
            int yayinyili = Integer.parseInt(txtYayinYili.getText());
            int belgekodu = Integer.parseInt(txtBelgeKodu.getText());
            String adver = "(Select adsoyad from public.kullanicilar where email = ?)";

            String sql = "INSERT INTO public.arsiv(belge_adi, belge_yayinlayan_kisi, belge_kodu, belge_yayin_yili, belge_turu, belge_nushasi, belge_ekleyen_adsoyad, belge_ekleyen_email,belge_nushasi_adi, belge_tarama, belge_kayit_turu) VALUES (?, ?, ?, ?, ?, ?, " + adver + ", ?, ?, ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, belgeadi);
            pst.setString(2, yayinlayanadi);
            pst.setInt(3, belgekodu);
            pst.setInt(4, yayinyili);
            pst.setString(5, belgeturu.toString());
            if (dosyayolu != null) {
                pst.setBinaryStream(6, new FileInputStream(dosyayolu));
            } else if (dosyayolu == null) {
                pst.setBinaryStream(6, null);
            }

            pst.setString(7, email);
            pst.setString(8, email);
            pst.setString(9, dosyaadi);
            pst.setString(10, tarananbelgesonuclari);
            if (tarananbelgesonuclari != null && dosyayolu != null) {
                pst.setInt(11, 2);
            } else if (dosyayolu != null) {
                pst.setInt(11, 0);
            } else if (tarananbelgesonuclari != null) {
                pst.setInt(11, 1);
            }
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Belge Eklendi");
                ArsivTabloVerileri();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBelgeEkleActionPerformed
/////////////////////////////////////////////// Belge Ekle Bitiş /////////////////////////////////////////////////

/////////////////////////////////////////////// Belge Seçme Başlangıç /////////////////////////////////////////////////
    private void btnBelgeSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBelgeSecActionPerformed
        if (dosyayolu != null) {
            dosyayolu = null;
        } else {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Dosyaları", "pdf");
            chooser.setFileFilter(filter);
            int sonuc = chooser.showOpenDialog(null);
            if (sonuc == JFileChooser.APPROVE_OPTION) {
                File dosya = chooser.getSelectedFile();
                dosyaadi = dosya.getName();
                System.out.println(dosyaadi);
                dosyayolu = dosya.getAbsolutePath();
                lblBelgeNushasiAdi.setText("Belge Seçildi !");
                lblBelgeNushasiAdi.setForeground(Renkler(4));
            }
        }
    }//GEN-LAST:event_btnBelgeSecActionPerformed
/////////////////////////////////////////////// Belge Seçme Bitiş /////////////////////////////////////////////////


    private void treeBelgeTuruValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeBelgeTuruValueChanged
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeBelgeTuru.getLastSelectedPathComponent();
        if (selectedNode != null) {
            belgeturu = selectedNode.getUserObject();
        }
    }//GEN-LAST:event_treeBelgeTuruValueChanged

    private void tblBelgeOnaylamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBelgeOnaylamaMouseClicked
        belgeonaylaindex = tblBelgeOnaylama.getSelectedRow();
        int belgeonaybelgekodu = (int) tblBelgeOnaylama.getValueAt(belgeonaylaindex, 0);
        txtBelgeOnaylamaBelgeKodu.setText(String.valueOf(belgeonaybelgekodu));
        txtBelgeOnaylamaBelgeAdi.setText((String) tblBelgeOnaylama.getValueAt(belgeonaylaindex, 1));
        txtBelgeOnaylamaIsteyenAdi.setText((String) tblBelgeOnaylama.getValueAt(belgeonaylaindex, 2));
        //txtBelgeOnaylamaGuncellemeTarihi.setText((String) tblBelgeOnaylama.getValueAt(belgeonaylaindex, 5));
    }//GEN-LAST:event_tblBelgeOnaylamaMouseClicked

    private void btnBelgeOnaylamaKabulEtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBelgeOnaylamaKabulEtActionPerformed
        try {
            int id = BelgeIDToOnaylamaTabloRow.get(belgeonaylaindex);
            int belgekodu = Integer.parseInt(txtBelgeOnaylamaBelgeKodu.getText());
            String belgeadi = txtBelgeOnaylamaBelgeAdi.getText();
            String yayinlayanadi = "(SELECT belge_yayinlayan_kisi FROM public.arsiv WHERE belge_kodu = ?)";
            String yayinyili = "(SELECT belge_yayin_yili FROM public.arsiv WHERE belge_kodu = ?)";
            String isteyenadi = "(SELECT arsiv_istek_bekleme_isteyen_adsoyad FROM public.arsiv_istek_bekleme WHERE arsiv_istek_bekleme_id = ?)";
            String isteyenemail = "(SELECT arsiv_istek_bekleme_isteyen_email FROM public.arsiv_istek_bekleme WHERE arsiv_istek_bekleme_id = ?)";
            String verenadi = "(Select adsoyad from public.kullanicilar where email = ?)";
            String isteksebebi = txtIstekSebebi.getText();

            String sqlupdate = "UPDATE public.arsiv_istek_bekleme SET arsiv_istek_bekleme_durum = ? WHERE arsiv_istek_bekleme_id = ?;";
            pst = conn.prepareStatement(sqlupdate);
            pst.setString(1, "Kabul Edildi");
            pst.setInt(2, id);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Kabul Edildi Update");
            }

            String sqlinsert = "INSERT INTO public.arsiv_istek_kabul(arsiv_istek_kabul_belge_kodu, arsiv_istek_kabul_belge_adi, arsiv_istek_kabul_yayinlayan_adi, arsiv_istek_kabul_yayin_yili, arsiv_istek_kabul_isteyen_adsoyad, arsiv_istek_kabul_isteyen_email, arsiv_istek_kabul_veren_adsoyad, arsiv_istek_kabul_veren_email, arsiv_istek_kabul_durum, arsiv_istek_kabul_istek_sebebi) VALUES (?, ?, " + yayinlayanadi + ", " + yayinyili + ", " + isteyenadi + ", " + isteyenemail + ", " + verenadi + ", ?, ?, ?);";
            pst = conn.prepareStatement(sqlinsert);
            pst.setInt(1, belgekodu);
            pst.setString(2, belgeadi);
            pst.setInt(3, belgekodu);
            pst.setInt(4, belgekodu);
            pst.setInt(5, id);
            pst.setInt(6, id);
            pst.setString(7, email);
            pst.setString(8, email);
            pst.setString(9, "Kabul Edildi");
            pst.setString(10, isteksebebi);
            int cevap = pst.executeUpdate();
            if (cevap == 1) {
                JOptionPane.showMessageDialog(null, "İnsert Oldu");
                BelgeOnayTabloVerileri();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBelgeOnaylamaKabulEtActionPerformed

    private void btnTaranacakBelgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaranacakBelgeActionPerformed
        if (taranacakbelgeyolu != null) {
            taranacakbelgeyolu = null;
        } else {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Dosyaları", "pdf");
            //chooser.setFileFilter(filter);
            int sonuc = chooser.showOpenDialog(null);
            if (sonuc == JFileChooser.APPROVE_OPTION) {
                taranacakbelge = chooser.getSelectedFile();
                taranacakbelgeyolu = taranacakbelge.getAbsolutePath();
                System.out.println(taranacakbelgeyolu);
                //lblBelgeNushasiAdi.setText("Belge Seçildi !");
                //lblBelgeNushasiAdi.setForeground(Renkler(4));
            }
        }

        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setLanguage("tur");
            String result = tesseract.doOCR(new File(taranacakbelgeyolu));
            //System.out.println(result);
            tarananbelgesonuclari = result;
            txtTarananBelge.setText(tarananbelgesonuclari);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTaranacakBelgeActionPerformed

    private void txtKitapAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtKitapAramaPropertyChange

    }//GEN-LAST:event_txtKitapAramaPropertyChange

    private void txtBelgeAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtBelgeAramaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBelgeAramaPropertyChange

    private void cbDuyuruKisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDuyuruKisiActionPerformed
        if (cbDuyuruKisi.getSelectedItem().toString() == "Belirli Bir Kullanıcı") {
            txtKullaniciBul.enable(true);
            tblKullanicilar.enable(true);
        }
        if (cbDuyuruKisi.getSelectedItem().toString() == "Bütün Kullanıcılar") {
            txtKullaniciBul.enable(false);
            tblKullanicilar.enable(false);
            txtKullaniciBul.setText("");
        }
    }//GEN-LAST:event_cbDuyuruKisiActionPerformed

    private void btnDuyuruYayinlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyuruYayinlaActionPerformed
        try {
            int ustid = 0;

            String sqladsoyad = "(SELECT adsoyad FROM public.kullanicilar WHERE email = ?)";
            String sql = "INSERT INTO public.duyuru(duyuru_yayinlayan_email, duyuru_yayinlayan_adsoyad, duyuru_konu, duyuru_metin, duyuru_gidecek_kisi) VALUES (?, " + sqladsoyad + ", ?, ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, email);
            pst.setString(3, txtDuyuruBaslik.getText());
            pst.setString(4, txtDuyuru.getText());
            if (cbDuyuruKisi.getSelectedItem().toString() == "Belirli Bir Kullanıcı") {
                pst.setString(5, txtKullaniciBul.getText());
            }
            if (cbDuyuruKisi.getSelectedItem().toString() == "Bütün Kullanıcılar") {
                pst.setString(5, "Herkes");
            }
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Duyuru Eklendi");
            }

            String sqlduyurubul = "SELECT * FROM public.duyuru ORDER BY duyuru_id DESC LIMIT 1;";
            pst = conn.prepareStatement(sqlduyurubul);
            rs = pst.executeQuery();
            if (rs.next()) {
                ustid = rs.getInt("duyuru_id");
            }

            String sqlbildirimekle = "INSERT INTO public.bildirim(\n"
                    + "	bildirim_turu, bildirim_aciklama, bildirim_email, bildirim_durum, bildirim_ustid, bildirim_gonderim_tipi)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?);";
            pst = conn.prepareStatement(sqlbildirimekle);
            pst.setString(1, "Duyuru");
            pst.setString(2, txtDuyuru.getText());
            if (cbDuyuruKisi.getSelectedItem().toString() == "Belirli Bir Kullanıcı") {
                pst.setString(3, txtKullaniciBul.getText());
            }
            if (cbDuyuruKisi.getSelectedItem().toString() == "Bütün Kullanıcılar") {
                pst.setString(3, "Herkes");
            }
            pst.setString(4, "Beklemede");
            pst.setInt(5, ustid);
            if (cbDuyuruKisi.getSelectedItem().toString() == "Belirli Bir Kullanıcı") {
                pst.setString(6, "Bireysel");
            }
            if (cbDuyuruKisi.getSelectedItem().toString() == "Bütün Kullanıcılar") {
                pst.setString(6, "Herkes");
            }
            int cevap = pst.executeUpdate();
            if (cevap == 1) {
                JOptionPane.showMessageDialog(null, "Bildirim Eklendi");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDuyuruYayinlaActionPerformed

    private void tblKullanicilarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKullanicilarMouseClicked
        int kisiindex = tblKullanicilar.getSelectedRow();
        txtKullaniciBul.setText((String) tblKullanicilar.getValueAt(kisiindex, 0));
    }//GEN-LAST:event_tblKullanicilarMouseClicked

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBelgeEkle;
    private javax.swing.JButton btnBelgeOnaylamaKabulEt;
    private javax.swing.JButton btnBelgeOnaylamaReddet;
    private javax.swing.JButton btnBelgeSec;
    private javax.swing.JButton btnDuyuruYayinla;
    private javax.swing.JButton btnGoruntuSec;
    private javax.swing.JButton btnKabul;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JButton btnKitapOnayla;
    private javax.swing.JButton btnKitapReddet;
    private javax.swing.JButton btnRet;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JButton btnTaranacakBelge;
    private javax.swing.JComboBox<String> cbDuyuruKisi;
    private javax.swing.JComboBox<String> cbKitapKodları;
    private javax.swing.JComboBox<String> cbKitapTuru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblArsiv;
    private javax.swing.JLabel lblBelgeAdi;
    private javax.swing.JLabel lblBelgeKodu;
    private javax.swing.JLabel lblBelgeNushasi;
    private javax.swing.JLabel lblBelgeNushasi1;
    private javax.swing.JLabel lblBelgeNushasiAdi;
    private javax.swing.JLabel lblBelgeYayinTarihi;
    private javax.swing.JLabel lblDiger;
    private javax.swing.JLabel lblKitapAdi;
    private javax.swing.JLabel lblKitapKodu;
    private javax.swing.JLabel lblKitapResmi;
    private javax.swing.JLabel lblKitapResmi1;
    private javax.swing.JLabel lblKitapTuru;
    private javax.swing.JLabel lblKutuphane;
    private javax.swing.JLabel lblRenk1Adi;
    private javax.swing.JLabel lblRenk2Adi;
    private javax.swing.JLabel lblRenk3Adi;
    private javax.swing.JLabel lblRenk4Adi;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JLabel lblYayinEvi;
    private javax.swing.JLabel lblYayinlayanAdi;
    private javax.swing.JLabel lblYazarAdSoyad;
    private javax.swing.JList<String> lstRenk;
    private javaswingdev.chart.PieChart pieChart1;
    private javax.swing.JPanel pnlBelgeEkle;
    private javax.swing.JPanel pnlBelgeOnayla;
    private javax.swing.JPanel pnlBelgeler;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDuyurular;
    private javax.swing.JPanel pnlKitapEkleme;
    private javax.swing.JPanel pnlKitapOnaylama;
    private javax.swing.JPanel pnlKitaplar;
    private javax.swing.JPanel pnlPastaBilgiler;
    private javax.swing.JPanel pnlRandevular;
    private javax.swing.JPanel pnlRenk1;
    private javax.swing.JPanel pnlRenk2;
    private javax.swing.JPanel pnlRenk3;
    private javax.swing.JPanel pnlRenk4;
    private javax.swing.JPanel pnlResim;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JPanel pnlUyeIslemleri;
    private javax.swing.JTabbedPane tabArsiv;
    private javax.swing.JTabbedPane tabDiger;
    private javax.swing.JTabbedPane tabKutuphane;
    private javax.swing.JTable tblBelgeOnaylama;
    private javax.swing.JTable tblBelgeler;
    private javax.swing.JTable tblKitapOnay;
    private javax.swing.JTable tblKitaplar;
    private javax.swing.JTable tblKullanicilar;
    private javax.swing.JTable tblRandevular;
    private javax.swing.JTree treeBelgeTuru;
    private javax.swing.JTextField txtBelgeAdi;
    private javax.swing.JTextField txtBelgeArama;
    private javax.swing.JTextField txtBelgeKodu;
    private javax.swing.JTextField txtBelgeOnaylamaBelgeAdi;
    private javax.swing.JTextField txtBelgeOnaylamaBelgeKodu;
    private javax.swing.JTextField txtBelgeOnaylamaGuncellemeTarihi;
    private javax.swing.JTextField txtBelgeOnaylamaIsteyenAdi;
    private javax.swing.JTextArea txtBelgeOnaylamaReddetmeSebebi;
    private javax.swing.JTextArea txtDuyuru;
    private javax.swing.JTextField txtDuyuruBaslik;
    private javax.swing.JTextField txtEnvanterdeKalan;
    private javax.swing.JTextArea txtIstekSebebi;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapArama;
    private javax.swing.JTextField txtKitapKodu;
    private javax.swing.JTextArea txtKitapOzet;
    private javax.swing.JTextArea txtKitapReddetmeSebep;
    private javax.swing.JTextArea txtKonu;
    private javax.swing.JTextField txtKullaniciBul;
    private javax.swing.JTextField txtRandevuIsteyenKisi;
    private javax.swing.JTextField txtRandevuTarih;
    private javax.swing.JTextArea txtSebep;
    private javax.swing.JTextArea txtTarananBelge;
    private javax.swing.JTextField txtToplamKitap;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYayinYili;
    private javax.swing.JTextField txtYayinlayanAdi;
    private javax.swing.JTextField txtYazarAdSoyad;
    // End of variables declaration//GEN-END:variables
}
