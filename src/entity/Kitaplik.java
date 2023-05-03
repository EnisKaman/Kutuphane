/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.security.Timestamp;

/**
 *
 * @author Enis
 */
public class Kitaplik {

    public int getKitapID() {
        return kitapID;
    }

    public void setKitapID(int kitapID) {
        this.kitapID = kitapID;
    }

    public int getKitap_kodu() {
        return kitap_kodu;
    }

    public void setKitap_kodu(int kitap_kodu) {
        this.kitap_kodu = kitap_kodu;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public byte[] getKitap_resim() {
        return kitap_resim;
    }

    public void setKitap_resim(byte[] kitap_resim) {
        this.kitap_resim = kitap_resim;
    }

    public String getYazar_adsoyad() {
        return yazar_adsoyad;
    }

    public void setYazar_adsoyad(String yazar_adsoyad) {
        this.yazar_adsoyad = yazar_adsoyad;
    }

    public String getYayin_evi() {
        return yayin_evi;
    }

    public void setYayin_evi(String yayin_evi) {
        this.yayin_evi = yayin_evi;
    }

    public String getKitap_turu() {
        return kitap_turu;
    }

    public void setKitap_turu(String kitap_turu) {
        this.kitap_turu = kitap_turu;
    }

    public int getOkuma_sayisi() {
        return okuma_sayisi;
    }

    public void setOkuma_sayisi(int okuma_sayisi) {
        this.okuma_sayisi = okuma_sayisi;
    }

    public Timestamp getKitap_ekleme_tarih() {
        return kitap_ekleme_tarih;
    }

    public void setKitap_ekleme_tarih(Timestamp kitap_ekleme_tarih) {
        this.kitap_ekleme_tarih = kitap_ekleme_tarih;
    }

    public String getKitap_durum() {
        return kitap_durum;
    }

    public void setKitap_durum(String kitap_durum) {
        this.kitap_durum = kitap_durum;
    }
    int kitapID;
    int kitap_kodu;
    String kitap_adi;
    byte[] kitap_resim;
    String yazar_adsoyad;
    String yayin_evi;
    String kitap_turu;
    int okuma_sayisi;
    Timestamp kitap_ekleme_tarih;
    String kitap_durum;
}
