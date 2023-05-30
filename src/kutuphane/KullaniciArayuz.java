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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.management.Notification;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellEditorBelgeOkumaBirlikte;
import raven.cell.TableActionCellEditorBelgeOkumaTarama;
import raven.cell.TableActionCellEditorView;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionCellRenderBelgeOkumaBirlikte;
import raven.cell.TableActionCellRenderView;
import raven.cell.TableActionEvent;
import raven.cell.TableActionEventBelgeOkumaBirlikte;
import raven.cell.TableActionEventBelgeOkumaTarama;
import raven.cell.TableActionEventKullanici;
import raven.glasspanepopup.GlassPanePopup;
import sample.notification.Notifications;

public class KullaniciArayuz extends javax.swing.JFrame {

    Connection conn = new Baglanti().getConnection();
    ResultSet rs = null;
    CallableStatement proc = null;
    PreparedStatement pst = null;
    String email = null;
    String sifre = null;
    int tema = 0;
    String tarih;
    String tarihkitap;
    String saat;
    String kitapal_yayinevi;
    int belgekodu = 0;
    String arayuzdurumu = "kutuphane";
    List<byte[]> resimler = new ArrayList<>();
    HashMap<Integer, Integer> BelgeKoduToKayitTuruBelgeIsteTabloRow = new HashMap<>();
    HashMap<Integer, Integer> BelgeKoduToKayitTuruBelgelerimTabloRow = new HashMap<>();

    public void KitaplarTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT k.kitap_adi ,k.yazar_adsoyad, k.yayin_evi,k.kitap_turu,(Select e.okuma_sayisi from public.kitap_envanter e where e.kitap_adi = k.kitap_adi and e.kitap_yayinevi = k.yayin_evi),(Select e.elde_olan from public.kitap_envanter e where e.kitap_adi = k.kitap_adi and e.kitap_yayinevi = k.yayin_evi) FROM public.kitaplik k;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Adı");
            model.addColumn("Yazar");
            model.addColumn("Yayın Evi");
            model.addColumn("Kitap Türü");
            model.addColumn("Okuma Sayısı");
            model.addColumn("Görüntüle");

            while (rs.next()) {
                int kitapsayisi = rs.getInt("elde_olan");
                if (kitapsayisi > 0) {
                    Object[] row = new Object[6];
                    row[0] = rs.getString("kitap_adi");
                    row[1] = rs.getString("yazar_adsoyad");
                    row[2] = rs.getString("yayin_evi");
                    row[3] = rs.getString("kitap_turu");
                    row[4] = rs.getInt("okuma_sayisi");
                    row[5] = rs.getInt("okuma_sayisi");
                    model.addRow(row);
                }

            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblKitapAl.setRowSorter(shorter);
            tblKitapAl.setDefaultRenderer(Object.class, centerRenderer);
            tblKitapAl.setModel(model);
            TableActionEventKullanici event = new TableActionEventKullanici() {
                @Override
                public void onView(int row) {
                    String kitapadi = (String) tblKitapAl.getValueAt(row, 0);
                    String yayinevi = (String) tblKitapAl.getValueAt(row, 2);
                    ViewKitapGonder(kitapadi, yayinevi);
                }
            };

            tblKitapAl.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderView());
            tblKitapAl.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorView(event));
            tblKitapAl.setDefaultRenderer(Object.class, centerRenderer);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AldigimKitaplarTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.kitap_al_kabul WHERE kitap_al_kabul_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kitap Kodu");
            model.addColumn("Kitap Adı");
            model.addColumn("Yayın Evi");
            model.addColumn("Durum");
            model.addColumn("Getirme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("kitap_al_kabul_kitap_kodu");
                row[1] = rs.getString("kitap_al_kabul_kitap_adi");
                row[2] = rs.getString("kitap_al_kabul_kitap_yayinevi");
                row[3] = rs.getString("kitap_al_kabul_durum");
                row[4] = rs.getTimestamp("kitap_al_kabul_geri_getirme_tarihi");
                model.addRow(row);

            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblAldigimKitaplar.setRowSorter(shorter);
            tblAldigimKitaplar.setDefaultRenderer(Object.class, centerRenderer);
            tblAldigimKitaplar.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ArsivBelgelerimTabloVerileri() {
        try {
            String sql = "SELECT aik.*,(SELECT a.belge_turu FROM public.arsiv a WHERE a.belge_kodu = aik.arsiv_istek_kabul_belge_kodu), (SELECT a.belge_kayit_turu FROM public.arsiv a WHERE a.belge_kodu = aik.arsiv_istek_kabul_belge_kodu) FROM public.arsiv_istek_kabul aik WHERE aik.arsiv_istek_kabul_isteyen_email = ?;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
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
                row[0] = rs.getString("arsiv_istek_kabul_belge_adi");
                row[1] = rs.getString("arsiv_istek_kabul_yayinlayan_adi");
                int belgekodu = rs.getInt("arsiv_istek_kabul_belge_kodu");
                int belgekayitturu = rs.getInt("belge_kayit_turu");
                row[2] = belgekodu;
                row[3] = rs.getInt("arsiv_istek_kabul_yayin_yili");
                row[4] = rs.getString("belge_turu");
                row[5] = belgekayitturu;
                model.addRow(row);
                BelgeKoduToKayitTuruBelgelerimTabloRow.put(belgekodu, belgekayitturu);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblBelgelerim.setRowSorter(shorter);
            tblBelgelerim.setModel(model);
            tblBelgelerim.setDefaultRenderer(Object.class, centerRenderer);
            TableActionEventBelgeOkumaBirlikte event = new TableActionEventBelgeOkumaBirlikte() {
                @Override
                public void PdfOkuma(int row) {
                    int belgekodu = (int) tblBelgelerim.getValueAt(row, 2);
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
                    int belgekodu = (int) tblBelgelerim.getValueAt(row, 2);
                    int kayitturu = BelgeKoduToKayitTuruBelgelerimTabloRow.get(belgekodu);
                    if (kayitturu == 2 || kayitturu == 1) {
                        TaranmisBelgeGoster(belgekodu);
                    }
                    if (kayitturu == 0) {
                        JOptionPane.showMessageDialog(null, "Bu belgede yalnızca PDF vardır.");
                    }
                }
            };
            tblBelgelerim.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderBelgeOkumaBirlikte());
            tblBelgelerim.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorBelgeOkumaBirlikte(event));

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ArsivBelgeIsteTabloVerileri() {
        try {
            String sql = "SELECT DISTINCT belge_adi,belge_yayinlayan_kisi,belge_kodu,belge_yayin_yili,belge_turu, belge_kayit_turu  FROM public.arsiv order by belge_kodu asc;";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Belge Adı");
            model.addColumn("Yayınlayan Kisi");
            model.addColumn("Belge Kodu");
            model.addColumn("Yayın Yılı");
            model.addColumn("Belge Türü");
            model.addColumn("İşlem");

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
                BelgeKoduToKayitTuruBelgeIsteTabloRow.put(belgekodu, belgekayitturu);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblBelgeIste.setRowSorter(shorter);
            tblBelgeIste.setDefaultRenderer(Object.class, centerRenderer);
            tblBelgeIste.setModel(model);
            TableActionEventBelgeOkumaBirlikte event = new TableActionEventBelgeOkumaBirlikte() {
                @Override
                public void PdfOkuma(int row) {
                    int belgekodu = (int) tblBelgeIste.getValueAt(row, 2);
                    int kayitturu = BelgeKoduToKayitTuruBelgeIsteTabloRow.get(belgekodu);
                    if (kayitturu == 2 || kayitturu == 0) {
                        PDFGoster(belgekodu);
                    }
                    if (kayitturu == 1) {
                        JOptionPane.showMessageDialog(null, "Bu belgede yalnızca taranmış bir belge vardır.");
                    }
                }

                @Override
                public void TaranmisOkuma(int row) {
                    int belgekodu = (int) tblBelgeIste.getValueAt(row, 2);
                    int kayitturu = BelgeKoduToKayitTuruBelgeIsteTabloRow.get(belgekodu);
                    if (kayitturu == 2 || kayitturu == 1) {
                        TaranmisBelgeGoster(belgekodu);
                    }
                    if (kayitturu == 0) {
                        JOptionPane.showMessageDialog(null, "Bu belgede yalnızca PDF vardır.");
                    }
                }
            };
            tblBelgeIste.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderBelgeOkumaBirlikte());
            tblBelgeIste.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorBelgeOkumaBirlikte(event));

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void BekleyenRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu where randevu_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Eden Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_veren_adsoyad");
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
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblBekleyenRandevu.setRowSorter(shorter);
            tblBekleyenRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblBekleyenRandevu.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KabulRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu_kabul where randevu_kabul_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Veren Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("randevu_kabul_isteyen_adsoyad");
                row[1] = rs.getString("randevu_kabul_konusu");
                String randevutaleptarih = rs.getString("randevu_kabul_talep_tarihi");
                LocalDateTime dateTime = LocalDateTime.parse(randevutaleptarih, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String yeniDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[2] = yeniDateTime;
                row[3] = rs.getString("randevu_kabul_durum");
                String originalDateTime = rs.getString("randevu_kabul_olusturma_tarih");
                LocalDateTime dateTime2 = LocalDateTime.parse(originalDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                String newDateTime = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[4] = newDateTime;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblKabulRandevu.setRowSorter(shorter);
            tblKabulRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblKabulRandevu.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RetRandevuTabloVerileri() {
        try {
            String sql = "SELECT * FROM public.randevu_ret where randevu_ret_isteyen_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Randevu Veren Kişi");
            model.addColumn("Randevu Konusu");
            model.addColumn("Randevu Tarihi");
            model.addColumn("Randevu Durumu");
            model.addColumn("Reddedilme Sebebi");
            model.addColumn("Güncelleme Tarihi");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("randevu_ret_isteyen_adsoyad");
                row[1] = rs.getString("randevu_ret_konusu");
                String randevutaleptarih = rs.getString("randevu_ret_talep_tarihi");
                LocalDateTime dateTime = LocalDateTime.parse(randevutaleptarih, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String yeniDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[2] = yeniDateTime;
                row[3] = rs.getString("randevu_ret_durum");
                row[4] = rs.getString("randevu_ret_sebep");
                String originalDateTime = rs.getString("randevu_ret_olusturma_tarih");
                LocalDateTime dateTime2 = LocalDateTime.parse(originalDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                String newDateTime = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                row[5] = newDateTime;
                model.addRow(row);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            TableRowSorter shorter = new TableRowSorter(model);
            tblRetRandevu.setRowSorter(shorter);
            tblRetRandevu.setDefaultRenderer(Object.class, centerRenderer);
            tblRetRandevu.setModel(model);

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

    public void PlaceHolder(TextField textField, String mesaj) {
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(mesaj)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(mesaj);
                }
            }
        });
    }

    public void PlaceHolder(JTextArea textField, String mesaj) {
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(mesaj)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(mesaj);
                }
            }
        });
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

    private static void kaydet(List<byte[]> resimler, String hedefDizin) throws IOException {
        for (int i = 0; i < resimler.size(); i++) {
            byte[] imageData = resimler.get(i);
            String dosyaAdi = "resim_" + i + ".jpg";
            String dosyaYolu = hedefDizin + dosyaAdi;

            try (OutputStream outputStream = new FileOutputStream(dosyaYolu)) {
                outputStream.write(imageData);
            }

            System.out.println("Resim " + dosyaAdi + " kaydedildi.");
        }
    }

    public void ViewKitapGonder(String kitapadi, String yayinevi) {
        try {
            String yazaradi = null;
            String kitapturu = null;
            String kitapozeti = null;
            byte[] imagedata = null;
            int toplamkitapsayisi = 0;
            int eldeolankitapsayisi = 0;

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

            String sqlkitapsayisi = "SELECT kitap_sayisi, elde_olan FROM public.kitap_envanter WHERE kitap_adi = ? AND kitap_yayinevi = ?;";
            pst = conn.prepareStatement(sqlkitapsayisi);
            pst.setString(1, kitapadi);
            pst.setString(2, yayinevi);
            rs = pst.executeQuery();
            if (rs.next()) {
                toplamkitapsayisi = rs.getInt("kitap_sayisi");
                eldeolankitapsayisi = rs.getInt("elde_olan");
            }

            KullaniciKitapDetayliGoruntule kdg = new KullaniciKitapDetayliGoruntule(kitapadi, yazaradi, yayinevi, kitapturu, kitapozeti, imagedata, toplamkitapsayisi, eldeolankitapsayisi);
            kdg.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KitapSuresiBul(String email) {
        try {
            int years, months, days, hours, minutes, kitapkodu = 0, count = 0, ustid = 0;
            String adsoyad = null, kitapadi = null, tarih, mesaj = null, yayinevi = null;
            Timestamp timestamp = null;
            String sql = "SELECT *,\n"
                    + "       EXTRACT(YEAR FROM date_difference) AS years,\n"
                    + "       EXTRACT(MONTH FROM date_difference) AS months,\n"
                    + "       EXTRACT(DAY FROM date_difference) AS days,\n"
                    + "	   EXTRACT(HOUR FROM date_difference) AS hours,\n"
                    + "	   EXTRACT(MINUTE FROM date_difference) AS minutes\n"
                    + "FROM (SELECT *, AGE(kitap_al_kabul_geri_getirme_tarihi, CURRENT_TIMESTAMP) AS date_difference\n"
                    + "  FROM public.kitap_al_kabul WHERE kitap_al_kabul_isteyen_email = ?\n"
                    + ") AS subquery;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while (rs.next()) {
                years = rs.getInt("years");
                months = rs.getInt("months");
                days = rs.getInt("days");
                hours = rs.getInt("hours");
                minutes = rs.getInt("minutes");
                adsoyad = rs.getString("kitap_al_kabul_isteyen_ad_soyad");
                kitapadi = rs.getString("kitap_al_kabul_kitap_adi");
                kitapkodu = rs.getInt("kitap_al_kabul_kitap_kodu");
                tarih = rs.getString("kitap_al_kabul_geri_getirme_tarihi");
                yayinevi = rs.getString("kitap_al_kabul_kitap_yayinevi");
                
                if (days <= 0 && hours <= 0 && minutes <= 0) {
                    timestamp = Timestamp.valueOf(tarih);
                    mesaj = "Sayın " + adsoyad + ";\n\nAdı: " + kitapadi + "\nKodu: " + kitapkodu + " olan kitabın \n" + tarih + " tarihli son getirme süresi geçmiştir.\nDetayları bildirim kısmından görüntüleyebilirsiniz.";
                    JOptionPane.showMessageDialog(null, mesaj);
                    
                }
                if (days <= 0 && hours >= 0 && minutes >= 0) {
                    mesaj = "Sayın " + adsoyad + ";\n\nAdı: " + kitapadi + "\nKodu: " + kitapkodu + " olan kitabın \n" + tarih + " tarihli son getirme gününe girmiş bulunmaktasınız."
                            + "\nKitap iadesi için: " + hours + " saat, " + minutes + " dakika süre kalmıştır.";
                    JOptionPane.showMessageDialog(null, mesaj);
                }
                
            }
            KitapBildirimEkle(timestamp, kitapkodu, count, kitapadi, adsoyad, yayinevi, ustid);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminArayuzu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void KitapBildirimEkle(Timestamp timestamp, int kitapkodu, int count, String kitapadi, String adsoyad, String yayinevi, int ustid){
        try {
            String sqlbidirimkitapbul = "SELECT COUNT(*) FROM public.kitap_bildirim WHERE \n"
                    + "kitap_bildirim_kitap_kodu = ? AND \n"
                    + "kitap_bildirim_ilgili_email = ? AND \n"
                    + "kitap_bildirim_getirme_tarihi = ?;";
            pst = conn.prepareStatement(sqlbidirimkitapbul);
            pst.setInt(1, kitapkodu);
            pst.setString(2, email);
            pst.setTimestamp(3, timestamp);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                if (count == 0) {
                    String sqlgecti = "INSERT INTO public.kitap_bildirim"
                            + "(kitap_bildirim_kitap_adi, kitap_bildirim_kitap_kodu, kitap_bildirim_ilgili_email, kitap_bildirim_ilgili_adsoyad, kitap_bildirim_durum, kitap_bildirim_getirme_tarihi, kitap_bildirim_kitap_yayin_evi) "
                            + "VALUES (?, ?, ?, ?, ?, ?,?);";
                    pst = conn.prepareStatement(sqlgecti);
                    pst.setString(1, kitapadi);
                    pst.setInt(2, kitapkodu);
                    pst.setString(3, email);
                    pst.setString(4, adsoyad);
                    pst.setString(5, "Kitabın Geri Getirme Tarihi Geçti");
                    
                    pst.setTimestamp(6, timestamp);
                    pst.setString(7, yayinevi);
                    int sonuc = pst.executeUpdate();
                    if (sonuc == 1) {
                        
                        JOptionPane.showMessageDialog(null, "Bildirim Kitap Eklendi");
                        
                        String sqlbildirimcek = "SELECT * FROM public.kitap_bildirim ORDER BY kitap_bildirim_id DESC LIMIT 1;";
                        pst = conn.prepareStatement(sqlbildirimcek);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            kitapadi = rs.getString("kitap_bildirim_kitap_adi");
                            kitapkodu = rs.getInt("kitap_bildirim_kitap_kodu");
                            tarih = rs.getString("kitap_bildirim_getirme_tarihi");
                            ustid = rs.getInt("kitap_bildirim_id");
                        }

                        String sqlbildirimekle = "INSERT INTO public.bildirim(\n"
                                + "	bildirim_turu, bildirim_aciklama, bildirim_email, bildirim_durum, bildirim_ustid, bildirim_gonderim_tipi)\n"
                                + "	VALUES (?, ?, ?, ?, ?, ?);";
                        pst = conn.prepareStatement(sqlbildirimekle);
                        pst.setString(1, "Kitap");
                        pst.setString(2, kitapkodu + " kodlu, " + kitapadi + " adlı kitabın geri getirme süresi " + tarih + "'da bitmiştir.");
                        pst.setString(3, email);
                        pst.setString(4, "Beklemede");
                        pst.setInt(5, ustid);
                        pst.setString(6, "Bireysel");
                        int cevap  = pst.executeUpdate();
                        if (cevap == 1) {
                            JOptionPane.showMessageDialog(null, "Bildirim Eklendi");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BildirimSayisi() {
        try {
            int count = 0;
            String sql = "SELECT COUNT(bildirim_email) FROM public.bildirim WHERE bildirim_email = ? OR bildirim_email = 'Herkes' AND bildirim_durum = ?;";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, "Beklemede");
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
            badgeButton1.setText(String.valueOf(count));
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KullaniciArayuz() {
        initComponents();
    }

    public KullaniciArayuz(String email, String sifre, int tema) {
        this.email = email;
        this.sifre = sifre;
        this.tema = tema;
        initComponents();
        GlassPanePopup.install(this);
        KitapSuresiBul(email);
        TemaRengi();
        AdminCekme();
        BekleyenRandevuTabloVerileri();
        KabulRandevuTabloVerileri();
        RetRandevuTabloVerileri();
        KitaplarTabloVerileri();
        ArsivBelgelerimTabloVerileri();
        ArsivBelgeIsteTabloVerileri();
        AldigimKitaplarTabloVerileri();
        BildirimSayisi();
        pnlSettings.setVisible(false);
        badgeButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabKutuphane = new javax.swing.JTabbedPane();
        pnlKitapAlma = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKitapAl = new javax.swing.JTable();
        txtYayinEvi = new javax.swing.JTextField();
        txtKitapAdi = new javax.swing.JTextField();
        KitapAlTarihSecici = new com.toedter.calendar.JDateChooser();
        cbKutuphaneci = new javax.swing.JComboBox<>();
        btnKitapAl = new javax.swing.JButton();
        txtKitapAlmaArama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlAldigimKitaplar = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblAldigimKitaplar = new javax.swing.JTable();
        txtAldigimKitaplarArama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblSettings = new javax.swing.JLabel();
        lblDiger = new javax.swing.JLabel();
        lblArsiv = new javax.swing.JLabel();
        lblKutuphane = new javax.swing.JLabel();
        badgeButton1 = new raven.notibutton.BadgeButton();
        pnlSettings = new javax.swing.JPanel();
        pnlSettingsKapat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRenk = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSettingsKaydet = new javax.swing.JButton();
        tabArsiv = new javax.swing.JTabbedPane();
        pnlBelgeIste = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblBelgeIste = new javax.swing.JTable();
        txtBelgeAdi = new javax.swing.JTextField();
        txtBelgeKodu = new javax.swing.JTextField();
        cbBelgeKutuphaneci = new javax.swing.JComboBox<>();
        txtYayinlayanAdi = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtIstemeSebebi = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        btnBelgeIste = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtBelgeIsteArama = new javax.swing.JTextField();
        pnlBelgelerim = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblBelgelerim = new javax.swing.JTable();
        btnPDFAc = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtBelgelerimArama = new javax.swing.JTextField();
        tabDiger = new javax.swing.JTabbedPane();
        pnlRandevu = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnlRandevuAl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRandevuAlKonu = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbRandevuKisi = new javax.swing.JComboBox<>();
        cbSaat = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRandevuAl = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        pnlBekleyenRandevu = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBekleyenRandevu = new javax.swing.JTable();
        pnlKabulEdilenRandevu = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKabulRandevu = new javax.swing.JTable();
        pnlReddedilenRandevu = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRetRandevu = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRetSebep = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKitapAl.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKitapAl.setRowHeight(40);
        tblKitapAl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitapAlMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKitapAl);

        txtYayinEvi.setEnabled(false);

        txtKitapAdi.setEnabled(false);

        KitapAlTarihSecici.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                KitapAlTarihSeciciPropertyChange(evt);
            }
        });

        btnKitapAl.setText("Kitap Al");
        btnKitapAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKitapAlActionPerformed(evt);
            }
        });

        txtKitapAlmaArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtKitapAlmaAramaPropertyChange(evt);
            }
        });

        jLabel9.setText("Tabloda Arama Yapın");

        javax.swing.GroupLayout pnlKitapAlmaLayout = new javax.swing.GroupLayout(pnlKitapAlma);
        pnlKitapAlma.setLayout(pnlKitapAlmaLayout);
        pnlKitapAlmaLayout.setHorizontalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                        .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtYayinEvi, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(KitapAlTarihSecici, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbKutuphaneci, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitapAlmaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitapAlmaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtKitapAlmaArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKitapAlmaLayout.createSequentialGroup()
                                .addComponent(btnKitapAl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(312, 312, 312)))))
                .addContainerGap())
        );
        pnlKitapAlmaLayout.setVerticalGroup(
            pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKitapAlmaArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKitapAlmaLayout.createSequentialGroup()
                        .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKitapAlmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtYayinEvi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbKutuphaneci, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnKitapAl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(KitapAlTarihSecici, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        tabKutuphane.addTab("Kitap Alma", pnlKitapAlma);

        tblAldigimKitaplar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(tblAldigimKitaplar);

        txtAldigimKitaplarArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAldigimKitaplarAramaPropertyChange(evt);
            }
        });

        jLabel8.setText("Tabloda Arama Yapın");

        javax.swing.GroupLayout pnlAldigimKitaplarLayout = new javax.swing.GroupLayout(pnlAldigimKitaplar);
        pnlAldigimKitaplar.setLayout(pnlAldigimKitaplarLayout);
        pnlAldigimKitaplarLayout.setHorizontalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAldigimKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAldigimKitaplarLayout.createSequentialGroup()
                        .addGap(0, 457, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtAldigimKitaplarArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11))
                .addContainerGap())
        );
        pnlAldigimKitaplarLayout.setVerticalGroup(
            pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAldigimKitaplarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAldigimKitaplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAldigimKitaplarArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabKutuphane.addTab("Aldığım Kitaplar", pnlAldigimKitaplar);

        getContentPane().add(tabKutuphane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        lblSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/settings_64px.png"))); // NOI18N
        lblSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingsMouseClicked(evt);
            }
        });
        getContentPane().add(lblSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDiger.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblDiger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_flicker_free_64px.png"))); // NOI18N
        lblDiger.setText("Diğer");
        lblDiger.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDiger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDigerMouseClicked(evt);
            }
        });
        getContentPane().add(lblDiger, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        lblArsiv.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblArsiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_view_64px.png"))); // NOI18N
        lblArsiv.setText("Arşiv");
        lblArsiv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblArsiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArsivMouseClicked(evt);
            }
        });
        getContentPane().add(lblArsiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        lblKutuphane.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        lblKutuphane.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/icons8_literature_64px.png"))); // NOI18N
        lblKutuphane.setText("Kütüphane");
        lblKutuphane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblKutuphane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKutuphaneMouseClicked(evt);
            }
        });
        getContentPane().add(lblKutuphane, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        badgeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon/zil.png"))); // NOI18N
        badgeButton1.setText("0");
        badgeButton1.setBadgeColor(new java.awt.Color(0, 153, 51));
        badgeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                badgeButton1MouseClicked(evt);
            }
        });
        badgeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badgeButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(badgeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

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

        getContentPane().add(pnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        tabArsiv.setToolTipText("");

        tblBelgeIste.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBelgeIste.setRowHeight(40);
        tblBelgeIste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBelgeIsteMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblBelgeIste);

        txtBelgeAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeAdi.setText("Belge Adı");
        txtBelgeAdi.setEnabled(false);

        txtBelgeKodu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBelgeKodu.setText("Belge Kodu");
        txtBelgeKodu.setEnabled(false);

        txtYayinlayanAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtYayinlayanAdi.setText("Yayınlayan Adı");
        txtYayinlayanAdi.setEnabled(false);

        txtIstemeSebebi.setColumns(20);
        txtIstemeSebebi.setRows(5);
        jScrollPane10.setViewportView(txtIstemeSebebi);

        jLabel7.setText("Bu belgeyi neden almak istiyorsunuz ? ( * Zorunlu )");

        btnBelgeIste.setText("Belge İste");
        btnBelgeIste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBelgeIsteActionPerformed(evt);
            }
        });

        jLabel10.setText("Tabloda Arama Yapın :");

        txtBelgeIsteArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtBelgeIsteAramaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlBelgeIsteLayout = new javax.swing.GroupLayout(pnlBelgeIste);
        pnlBelgeIste.setLayout(pnlBelgeIsteLayout);
        pnlBelgeIsteLayout.setHorizontalGroup(
            pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgeIsteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtBelgeIsteArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBelgeAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBelgeKodu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYayinlayanAdi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 91, Short.MAX_VALUE))
                            .addComponent(jScrollPane10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbBelgeKutuphaneci, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBelgeIste, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlBelgeIsteLayout.setVerticalGroup(
            pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBelgeIsteArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                        .addComponent(txtBelgeAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtYayinlayanAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtBelgeKodu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBelgeIsteLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBelgeIsteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBelgeIsteLayout.createSequentialGroup()
                                .addComponent(cbBelgeKutuphaneci, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBelgeIste, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );

        tabArsiv.addTab("Belge İste", pnlBelgeIste);

        tblBelgelerim.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBelgelerim.setRowHeight(40);
        tblBelgelerim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBelgelerimMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblBelgelerim);

        btnPDFAc.setText("Dosyayı Göster");
        btnPDFAc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFAcActionPerformed(evt);
            }
        });

        jLabel11.setText("Tabloda Arama Yapın :");

        txtBelgelerimArama.setToolTipText("Tablodaki Herhangi Bir Veriyi Arayabilirsiniz");
        txtBelgelerimArama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtBelgelerimAramaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlBelgelerimLayout = new javax.swing.GroupLayout(pnlBelgelerim);
        pnlBelgelerim.setLayout(pnlBelgelerimLayout);
        pnlBelgelerimLayout.setHorizontalGroup(
            pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgelerimLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgelerimLayout.createSequentialGroup()
                        .addComponent(btnPDFAc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(301, 301, 301))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBelgelerimLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtBelgelerimArama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlBelgelerimLayout.setVerticalGroup(
            pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBelgelerimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBelgelerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBelgelerimArama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPDFAc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tabArsiv.addTab("Belgelerim", pnlBelgelerim);

        getContentPane().add(tabArsiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        pnlRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRandevuAlKonu.setColumns(20);
        txtRandevuAlKonu.setRows(5);
        jScrollPane2.setViewportView(txtRandevuAlKonu);

        jLabel2.setText("Tarih Seçiniz");

        jLabel3.setText("Saat Seçiniz");

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

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlRandevuAlLayout = new javax.swing.GroupLayout(pnlRandevuAl);
        pnlRandevuAl.setLayout(pnlRandevuAlLayout);
        pnlRandevuAlLayout.setHorizontalGroup(
            pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(170, 170, 170)
                                .addComponent(jLabel4))
                            .addGroup(pnlRandevuAlLayout.createSequentialGroup()
                                .addComponent(cbSaat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(cbRandevuKisi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRandevuAlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(323, 323, 323))
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
                .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRandevuAlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(cbRandevuKisi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(cbSaat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Randevu Al", pnlRandevuAl);

        pnlBekleyenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBekleyenRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblBekleyenRandevu);

        pnlBekleyenRandevu.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        jTabbedPane2.addTab("Bekleyen Randevularım", pnlBekleyenRandevu);

        pnlKabulEdilenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKabulRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblKabulRandevu);

        pnlKabulEdilenRandevu.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        jTabbedPane2.addTab("Kabul Edilen Randevularım", pnlKabulEdilenRandevu);

        pnlReddedilenRandevu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRetRandevu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRetRandevu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRetRandevuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblRetRandevu);

        pnlReddedilenRandevu.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 320));

        txtRetSebep.setColumns(20);
        txtRetSebep.setRows(5);
        jScrollPane6.setViewportView(txtRetSebep);

        pnlReddedilenRandevu.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 346, 800, 110));

        jLabel6.setText("Randevunun Reddedilme Sebebi");
        pnlReddedilenRandevu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jTabbedPane2.addTab("Reddedilen Randevularım", pnlReddedilenRandevu);

        pnlRandevu.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tabDiger.addTab("Randevu", pnlRandevu);

        getContentPane().add(tabDiger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
///////////////////////////////////////////////// Ayarlar Kapatama Butonu Başlangıç /////////////////////////////////////////////////  
    private void pnlSettingsKapatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingsKapatMouseClicked

        pnlSettings.setVisible(false);
        lblSettings.setVisible(true);
        tabKutuphane.setVisible(true);
        lblArsiv.setVisible(true);
        lblDiger.setVisible(true);
        lblKutuphane.setVisible(true);
        badgeButton1.setVisible(true);
    }//GEN-LAST:event_pnlSettingsKapatMouseClicked
///////////////////////////////////////////////// Ayarlar Kapatama Butonu Bitiş /////////////////////////////////////////////////  

///////////////////////////////////////////////// Listeden Renk Seçme Başlangıç /////////////////////////////////////////////////  
    private void lstRenkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstRenkMouseClicked
        RenkSecmeListe();
    }//GEN-LAST:event_lstRenkMouseClicked
///////////////////////////////////////////////// Listeden Renk Seçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Ayarlar Tema Rengi Kaydet Başlangıç ///////////////////////////////////////////////// 
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
///////////////////////////////////////////////// Ayarlar Tema Rengi Kaydet Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Ayarlar Açma Butonu Başlangıç ///////////////////////////////////////////////// 
    private void lblSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingsMouseClicked
        tabKutuphane.setVisible(false);
        tabArsiv.setVisible(false);
        tabDiger.setVisible(false);
        lblSettings.setVisible(false);
        pnlSettings.setVisible(true);
        lblArsiv.setVisible(false);
        lblDiger.setVisible(false);
        lblKutuphane.setVisible(false);
        badgeButton1.setVisible(false);
    }//GEN-LAST:event_lblSettingsMouseClicked
///////////////////////////////////////////////// Ayarlar Açma Butonu Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// ComboBox Saat Seçme Başlangıç ///////////////////////////////////////////////// 
    private void cbSaatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSaatActionPerformed
        saat = cbSaat.getSelectedItem().toString();
        System.out.println("" + tarih + " " + saat + "");
    }//GEN-LAST:event_cbSaatActionPerformed
///////////////////////////////////////////////// ComboBox Saat Seçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// ComboBox Saat Seçme Başlangıç ///////////////////////////////////////////////// 
    private void btnRandevuAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandevuAlActionPerformed
        try {
            String randevuverenkisiadsoyad = cbRandevuKisi.getSelectedItem().toString();
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
///////////////////////////////////////////////// ComboBox Saat Seçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Reddedilen Randevualr Tablosu Tıklama Başlangıç ///////////////////////////////////////////////// 
    private void tblRetRandevuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRetRandevuMouseClicked
        int retindex = tblRetRandevu.getSelectedRow();
        if (retindex >= 0) {
            txtRetSebep.setText((String) tblRetRandevu.getValueAt(retindex, 4));
        }

    }//GEN-LAST:event_tblRetRandevuMouseClicked
///////////////////////////////////////////////// Reddedilen Randevualr Tablosu Tıklama Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Takvim Tarih Seçme Başlangıç ///////////////////////////////////////////////// 
    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            tarih = format.format(date);
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange
///////////////////////////////////////////////// Takvim Tarih Seçme Bitiş ///////////////////////////////////////////////// 

    ///////////////////////////////////////////////// Kitap Al Tablosu Tıklma Başlangıç ///////////////////////////////////////////////// 
    private void tblKitapAlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitapAlMouseClicked
        int kitapindex = tblKitapAl.getSelectedRow();
        if (kitapindex >= 0) {
            txtKitapAdi.setText((String) tblKitapAl.getValueAt(kitapindex, 0));
            kitapal_yayinevi = (String) tblKitapAl.getValueAt(kitapindex, 2);
            txtYayinEvi.setText(kitapal_yayinevi);
        }
    }//GEN-LAST:event_tblKitapAlMouseClicked
    ///////////////////////////////////////////////// Kitap Al Tablosu Tıklma Bitiş ///////////////////////////////////////////////// 

    ///////////////////////////////////////////////// Kitap Al Buton Tıklma Başlangıç ///////////////////////////////////////////////// 
    private void btnKitapAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKitapAlActionPerformed
        try {
            String kitapal_kitapadi = txtKitapAdi.getText();
            String kutuphaneciadi = cbKutuphaneci.getSelectedItem().toString();
            String adcekmesql = "(SELECT adsoyad FROM public.kullanicilar where email = ?)";
            String emailcekmesql = "(SELECT email FROM public.kullanicilar where adsoyad = ?)";
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = inputFormat.parse(tarihkitap);
            String formattedDate = outputFormat.format(date);
            Timestamp timestamp = Timestamp.valueOf(formattedDate);

            String sql = "INSERT INTO public.kitap_al_istek( kitap_al_istek_kitap_adi, kitap_al_istek_kitap_yayinevi, kitap_al_istek_isteyen_ad_soyad, kitap_al_istek_isteyen_email, kitap_al_istek_veren_ad_soyad, kitap_al_istek_veren_email, kitap_al_istek_geri_verme_tarihi, kitap_al_istek_durum) VALUES (?, ?, " + adcekmesql + ", ?, ?, " + emailcekmesql + ", ?, 'Beklemede');";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kitapal_kitapadi);
            pst.setString(2, kitapal_yayinevi);
            pst.setString(3, email);
            pst.setString(4, email);
            pst.setString(5, kutuphaneciadi);
            pst.setString(6, kutuphaneciadi);
            pst.setTimestamp(7, timestamp);
            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İstek Başarılı");
                txtKitapAdi.setText("");
                txtYayinEvi.setText("");
                KitapAlTarihSecici.setDate(null);
                tblKitapAl.clearSelection();
            } else {
                JOptionPane.showMessageDialog(null, "İstek Başarısız");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnKitapAlActionPerformed
    ///////////////////////////////////////////////// Kitap Al Buton Tıklma Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Kitap Al Tarih Seçme Başlangıç ///////////////////////////////////////////////// 
    private void KitapAlTarihSeciciPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_KitapAlTarihSeciciPropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            tarihkitap = format.format(date) + " 18:00:00";
            System.out.println(tarihkitap);
        }
    }//GEN-LAST:event_KitapAlTarihSeciciPropertyChange
///////////////////////////////////////////////// Kitap Al Tarih Seçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Kütüphaneye Geçme Başlangıç ///////////////////////////////////////////////// 
    private void lblKutuphaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKutuphaneMouseClicked
        if (arayuzdurumu != "kutuphane") {
            Font font = lblKutuphane.getFont();
            Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
            lblKutuphane.setFont(kalınFont);
            lblArsiv.setFont(font);
            lblDiger.setFont(font);
            tabArsiv.setVisible(false);
            tabDiger.setVisible(false);
            tabKutuphane.setVisible(true);
            arayuzdurumu = "kutuphane";
        }
    }//GEN-LAST:event_lblKutuphaneMouseClicked
///////////////////////////////////////////////// Kütüphaneye Geçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Arşive Geçme Başlangıç ///////////////////////////////////////////////// 
    private void lblArsivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArsivMouseClicked
        if (arayuzdurumu != "arsiv") {
            Font font = lblArsiv.getFont();
            Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
            lblArsiv.setFont(kalınFont);
            lblKutuphane.setFont(font);
            lblDiger.setFont(font);
            tabArsiv.setVisible(true);
            tabDiger.setVisible(false);
            tabKutuphane.setVisible(false);
            arayuzdurumu = "arsiv";
        }
    }//GEN-LAST:event_lblArsivMouseClicked
///////////////////////////////////////////////// Arşive Geçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Diğer Geçme Başlangıç ///////////////////////////////////////////////// 
    private void lblDigerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDigerMouseClicked
        if (arayuzdurumu != "diger") {
            Font font = lblDiger.getFont();
            Font kalınFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
            lblArsiv.setFont(font);
            lblKutuphane.setFont(font);
            lblDiger.setFont(kalınFont);
            tabArsiv.setVisible(false);
            tabDiger.setVisible(true);
            tabKutuphane.setVisible(false);
            arayuzdurumu = "diger";
        }
    }//GEN-LAST:event_lblDigerMouseClicked
///////////////////////////////////////////////// Diğer Geçme Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Belgelerim Tablosu Tıklama Başlangıç ///////////////////////////////////////////////// 
    private void tblBelgelerimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBelgelerimMouseClicked
        int belgelerimindex = tblBelgelerim.getSelectedRow();
        belgekodu = (int) tblBelgelerim.getValueAt(belgelerimindex, 2);
    }//GEN-LAST:event_tblBelgelerimMouseClicked
///////////////////////////////////////////////// Belgelerim Tablosu Tıklama Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// PDF Gösterme Butonu Başlangıç ///////////////////////////////////////////////// 
    private void btnPDFAcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFAcActionPerformed
        /*try {
            PDFGoster();
        } catch (IOException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnPDFAcActionPerformed
///////////////////////////////////////////////// PDF Gösterme Butonu Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Çıkış - Log Kaydı Alma Başlangıç ///////////////////////////////////////////////// 
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
///////////////////////////////////////////////// Çıkış - Log Kaydı Alma Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Belge İSteme Tablosu TIklama Başlangıç ///////////////////////////////////////////////// 
    private void tblBelgeIsteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBelgeIsteMouseClicked
        int belgeisteindex = tblBelgeIste.getSelectedRow();
        txtBelgeAdi.setText((String) tblBelgeIste.getValueAt(belgeisteindex, 0));
        txtYayinlayanAdi.setText((String) tblBelgeIste.getValueAt(belgeisteindex, 1));
        int belgekodu = (int) tblBelgeIste.getValueAt(belgeisteindex, 2);
        txtBelgeKodu.setText(String.valueOf(belgekodu));
    }//GEN-LAST:event_tblBelgeIsteMouseClicked
///////////////////////////////////////////////// Belge İSteme Tablosu TIklama Bitiş ///////////////////////////////////////////////// 

///////////////////////////////////////////////// Belge İSteme Buton Başlangıç ///////////////////////////////////////////////// 
    private void btnBelgeIsteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBelgeIsteActionPerformed
        try {
            String belgeadi = txtBelgeAdi.getText();
            String yayinlayanadi = txtYayinlayanAdi.getText();
            int belgeninkodu = Integer.parseInt(txtBelgeKodu.getText());
            String verenadsoyad = cbBelgeKutuphaneci.getSelectedItem().toString();
            String verenemail = "(SELECT email FROM public.kullanicilar WHERE adsoyad = ?)";
            String isteyenadsoayd = "(SELECT adsoyad FROM public.kullanicilar WHERE email = ?)";
            String yayinyili = "(SELECT belge_yayin_yili FROM public.arsiv WHERE belge_kodu = ?)";
            String isteksebebi = txtIstemeSebebi.getText();

            String sql = "INSERT INTO public.arsiv_istek_bekleme(arsiv_istek_bekleme_belge_adi, arsiv_istek_bekleme_belge_kodu, arsiv_istek_bekleme_yayinlayan_adi, arsiv_istek_bekleme_yayin_yili, arsiv_istek_bekleme_isteyen_adsoyad, arsiv_istek_bekleme_isteyen_email, arsiv_istek_bekleme_veren_adsoyad, arsiv_istek_bekleme_veren_email, arsiv_istek_bekleme_durum,arsiv_istek_bekleme_istek_sebebi) VALUES (?, ?, ?, " + yayinyili + ", " + isteyenadsoayd + ", ?, ?, " + verenemail + ", ?, ?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, belgeadi);
            pst.setInt(2, belgeninkodu);
            pst.setString(3, yayinlayanadi);
            pst.setInt(4, belgeninkodu);
            pst.setString(5, email);
            pst.setString(6, email);
            pst.setString(7, verenadsoyad);
            pst.setString(8, verenadsoyad);
            pst.setString(9, "Beklemede");
            pst.setString(10, isteksebebi);

            int sonuc = pst.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İstek Başarılı");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBelgeIsteActionPerformed

    private void txtAldigimKitaplarAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAldigimKitaplarAramaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAldigimKitaplarAramaPropertyChange

    private void txtKitapAlmaAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtKitapAlmaAramaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitapAlmaAramaPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        txtAldigimKitaplarArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                AldigimKitaplarTabloVerileri();
                search(tblAldigimKitaplar, txtAldigimKitaplarArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                AldigimKitaplarTabloVerileri();
                search(tblAldigimKitaplar, txtAldigimKitaplarArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                AldigimKitaplarTabloVerileri();
                search(tblAldigimKitaplar, txtAldigimKitaplarArama);
            }

        });

        txtKitapAlmaArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitapAl, txtKitapAlmaArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitapAl, txtKitapAlmaArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                KitaplarTabloVerileri();
                search(tblKitapAl, txtKitapAlmaArama);
            }

        });

        txtBelgelerimArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                ArsivBelgelerimTabloVerileri();
                search(tblBelgelerim, txtBelgelerimArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                ArsivBelgelerimTabloVerileri();
                search(tblBelgelerim, txtBelgelerimArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ArsivBelgelerimTabloVerileri();
                search(tblBelgelerim, txtBelgelerimArama);
            }

        });

        txtBelgeIsteArama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                ArsivBelgeIsteTabloVerileri();
                search(tblBelgeIste, txtBelgeIsteArama);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                ArsivBelgeIsteTabloVerileri();
                search(tblBelgeIste, txtBelgeIsteArama);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ArsivBelgeIsteTabloVerileri();
                search(tblBelgeIste, txtBelgeIsteArama);
            }

        });
    }//GEN-LAST:event_formWindowActivated

    private void txtBelgeIsteAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtBelgeIsteAramaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBelgeIsteAramaPropertyChange

    private void txtBelgelerimAramaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtBelgelerimAramaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBelgelerimAramaPropertyChange

    private void badgeButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_badgeButton1MouseClicked
        //JOptionPane.showMessageDialog(null, "asd");
    }//GEN-LAST:event_badgeButton1MouseClicked

    private void badgeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badgeButton1ActionPerformed
        GlassPanePopup.showPopup(new Notifications(email));
    }//GEN-LAST:event_badgeButton1ActionPerformed

    public void TemaRengi() {
        if (tema == 0) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 1) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 2) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 3) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 4) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 5) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();

                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcDarkOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);

                }
            });
        } else if (tema == 6) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 7) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 8) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCyanLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 9) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCarbonIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 10) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 11) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (tema == 12) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMonokaiProIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        }
    }

    public void RenkSecmeListe() {
        if (lstRenk.isSelectedIndex(0)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 0;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(1)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 1;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(2)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 2;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(3)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 3;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(4)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 4;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(5)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 5;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcDarkOrangeIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(6)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 6;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(7)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 7;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatSolarizedDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(8)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 8;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCyanLightIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(9)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 9;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatCarbonIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(10)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 10;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(11)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 11;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatGitHubDarkIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        } else if (lstRenk.isSelectedIndex(12)) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    var ui = badgeButton1.getUI();
                    tema = 12;
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMonokaiProIJTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                    badgeButton1.setUI(ui);
                }
            });
        }
    }

    public void AdminCekme() {
        try {
            String sql = "SELECT adsoyad FROM public.kullanicilar WHERE yetkituru = ?;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbRandevuKisi.addItem(rs.getString("adsoyad"));
                cbKutuphaneci.addItem(rs.getString("adsoyad"));
                cbBelgeKutuphaneci.addItem(rs.getString("adsoyad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KullaniciArayuz().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser KitapAlTarihSecici;
    private raven.notibutton.BadgeButton badgeButton1;
    private javax.swing.JButton btnBelgeIste;
    private javax.swing.JButton btnKitapAl;
    private javax.swing.JButton btnPDFAc;
    private javax.swing.JButton btnRandevuAl;
    private javax.swing.JButton btnSettingsKaydet;
    private javax.swing.JComboBox<String> cbBelgeKutuphaneci;
    private javax.swing.JComboBox<String> cbKutuphaneci;
    private javax.swing.JComboBox<String> cbRandevuKisi;
    private javax.swing.JComboBox<String> cbSaat;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblArsiv;
    private javax.swing.JLabel lblDiger;
    private javax.swing.JLabel lblKutuphane;
    private javax.swing.JLabel lblSettings;
    private javax.swing.JList<String> lstRenk;
    private javax.swing.JPanel pnlAldigimKitaplar;
    private javax.swing.JPanel pnlBekleyenRandevu;
    private javax.swing.JPanel pnlBelgeIste;
    private javax.swing.JPanel pnlBelgelerim;
    private javax.swing.JPanel pnlKabulEdilenRandevu;
    private javax.swing.JPanel pnlKitapAlma;
    private javax.swing.JPanel pnlRandevu;
    private javax.swing.JPanel pnlRandevuAl;
    private javax.swing.JPanel pnlReddedilenRandevu;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JLabel pnlSettingsKapat;
    private javax.swing.JTabbedPane tabArsiv;
    private javax.swing.JTabbedPane tabDiger;
    private javax.swing.JTabbedPane tabKutuphane;
    private javax.swing.JTable tblAldigimKitaplar;
    private javax.swing.JTable tblBekleyenRandevu;
    private javax.swing.JTable tblBelgeIste;
    private javax.swing.JTable tblBelgelerim;
    private javax.swing.JTable tblKabulRandevu;
    private javax.swing.JTable tblKitapAl;
    private javax.swing.JTable tblRetRandevu;
    private javax.swing.JTextField txtAldigimKitaplarArama;
    private javax.swing.JTextField txtBelgeAdi;
    private javax.swing.JTextField txtBelgeIsteArama;
    private javax.swing.JTextField txtBelgeKodu;
    private javax.swing.JTextField txtBelgelerimArama;
    private javax.swing.JTextArea txtIstemeSebebi;
    private javax.swing.JTextField txtKitapAdi;
    private javax.swing.JTextField txtKitapAlmaArama;
    private javax.swing.JTextArea txtRandevuAlKonu;
    private javax.swing.JTextArea txtRetSebep;
    private javax.swing.JTextField txtYayinEvi;
    private javax.swing.JTextField txtYayinlayanAdi;
    // End of variables declaration//GEN-END:variables
}
