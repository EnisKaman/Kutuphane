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
public class KitapAlKabul {

    public int getKitap_al_kabul_id() {
        return kitap_al_kabul_id;
    }

    public void setKitap_al_kabul_id(int kitap_al_kabul_id) {
        this.kitap_al_kabul_id = kitap_al_kabul_id;
    }

    public String getKitap_al_kabul_kitap_kodu() {
        return kitap_al_kabul_kitap_kodu;
    }

    public void setKitap_al_kabul_kitap_kodu(String kitap_al_kabul_kitap_kodu) {
        this.kitap_al_kabul_kitap_kodu = kitap_al_kabul_kitap_kodu;
    }

    public String getKitap_al_kabul_kitap_adi() {
        return kitap_al_kabul_kitap_adi;
    }

    public void setKitap_al_kabul_kitap_adi(String kitap_al_kabul_kitap_adi) {
        this.kitap_al_kabul_kitap_adi = kitap_al_kabul_kitap_adi;
    }

    public String getKitap_al_kabul_isteyen_ad_soyad() {
        return kitap_al_kabul_isteyen_ad_soyad;
    }

    public void setKitap_al_kabul_isteyen_ad_soyad(String kitap_al_kabul_isteyen_ad_soyad) {
        this.kitap_al_kabul_isteyen_ad_soyad = kitap_al_kabul_isteyen_ad_soyad;
    }

    public String getKitap_al_kabul_isteyen_email() {
        return kitap_al_kabul_isteyen_email;
    }

    public void setKitap_al_kabul_isteyen_email(String kitap_al_kabul_isteyen_email) {
        this.kitap_al_kabul_isteyen_email = kitap_al_kabul_isteyen_email;
    }

    public String getKitap_al_kabul_veren_ad_soyad() {
        return kitap_al_kabul_veren_ad_soyad;
    }

    public void setKitap_al_kabul_veren_ad_soyad(String kitap_al_kabul_veren_ad_soyad) {
        this.kitap_al_kabul_veren_ad_soyad = kitap_al_kabul_veren_ad_soyad;
    }

    public String getKitap_al_kabul_veren_email() {
        return kitap_al_kabul_veren_email;
    }

    public void setKitap_al_kabul_veren_email(String kitap_al_kabul_veren_email) {
        this.kitap_al_kabul_veren_email = kitap_al_kabul_veren_email;
    }

    public String getKitap_al_kabul_durum() {
        return kitap_al_kabul_durum;
    }

    public void setKitap_al_kabul_durum(String kitap_al_kabul_durum) {
        this.kitap_al_kabul_durum = kitap_al_kabul_durum;
    }

    public Timestamp getKitap_al_kabul_geri_getirme_tarihi() {
        return kitap_al_kabul_geri_getirme_tarihi;
    }

    public void setKitap_al_kabul_geri_getirme_tarihi(Timestamp kitap_al_kabul_geri_getirme_tarihi) {
        this.kitap_al_kabul_geri_getirme_tarihi = kitap_al_kabul_geri_getirme_tarihi;
    }

    public Timestamp getKitap_al_kabul_guncelleme_tarihi() {
        return kitap_al_kabul_guncelleme_tarihi;
    }

    public void setKitap_al_kabul_guncelleme_tarihi(Timestamp kitap_al_kabul_guncelleme_tarihi) {
        this.kitap_al_kabul_guncelleme_tarihi = kitap_al_kabul_guncelleme_tarihi;
    }

    public String getKitap_al_kabul_kitap_yayinevi() {
        return kitap_al_kabul_kitap_yayinevi;
    }

    public void setKitap_al_kabul_kitap_yayinevi(String kitap_al_kabul_kitap_yayinevi) {
        this.kitap_al_kabul_kitap_yayinevi = kitap_al_kabul_kitap_yayinevi;
    }

    int kitap_al_kabul_id;
    String kitap_al_kabul_kitap_kodu;
    String kitap_al_kabul_kitap_adi;
    String kitap_al_kabul_isteyen_ad_soyad;
    String kitap_al_kabul_isteyen_email;
    String kitap_al_kabul_veren_ad_soyad;
    String kitap_al_kabul_veren_email;
    String kitap_al_kabul_durum;
    Timestamp kitap_al_kabul_geri_getirme_tarihi;
    Timestamp kitap_al_kabul_guncelleme_tarihi;
    String kitap_al_kabul_kitap_yayinevi;
}
