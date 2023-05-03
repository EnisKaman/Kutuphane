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
public class KitapAlIstek {

    public int getKitap_al_istek_id() {
        return kitap_al_istek_id;
    }

    public void setKitap_al_istek_id(int kitap_al_istek_id) {
        this.kitap_al_istek_id = kitap_al_istek_id;
    }

    public String getKitap_al_istek_kitap_adi() {
        return kitap_al_istek_kitap_adi;
    }

    public void setKitap_al_istek_kitap_adi(String kitap_al_istek_kitap_adi) {
        this.kitap_al_istek_kitap_adi = kitap_al_istek_kitap_adi;
    }

    public String getKitap_al_istek_kitap_yayinevi() {
        return kitap_al_istek_kitap_yayinevi;
    }

    public void setKitap_al_istek_kitap_yayinevi(String kitap_al_istek_kitap_yayinevi) {
        this.kitap_al_istek_kitap_yayinevi = kitap_al_istek_kitap_yayinevi;
    }

    public String getKitap_al_istek_isteyen_ad_soyad() {
        return kitap_al_istek_isteyen_ad_soyad;
    }

    public void setKitap_al_istek_isteyen_ad_soyad(String kitap_al_istek_isteyen_ad_soyad) {
        this.kitap_al_istek_isteyen_ad_soyad = kitap_al_istek_isteyen_ad_soyad;
    }

    public String getKitap_al_istek_isteyen_email() {
        return kitap_al_istek_isteyen_email;
    }

    public void setKitap_al_istek_isteyen_email(String kitap_al_istek_isteyen_email) {
        this.kitap_al_istek_isteyen_email = kitap_al_istek_isteyen_email;
    }

    public String getKitap_al_istek_durum() {
        return kitap_al_istek_durum;
    }

    public void setKitap_al_istek_durum(String kitap_al_istek_durum) {
        this.kitap_al_istek_durum = kitap_al_istek_durum;
    }

    public Timestamp getKitap_al_istek_guncelleme_tarihi() {
        return kitap_al_istek_guncelleme_tarihi;
    }

    public void setKitap_al_istek_guncelleme_tarihi(Timestamp kitap_al_istek_guncelleme_tarihi) {
        this.kitap_al_istek_guncelleme_tarihi = kitap_al_istek_guncelleme_tarihi;
    }

    public String getKitap_al_istek_veren_ad_soyad() {
        return kitap_al_istek_veren_ad_soyad;
    }

    public void setKitap_al_istek_veren_ad_soyad(String kitap_al_istek_veren_ad_soyad) {
        this.kitap_al_istek_veren_ad_soyad = kitap_al_istek_veren_ad_soyad;
    }

    public String getKitap_al_istek_veren_email() {
        return kitap_al_istek_veren_email;
    }

    public void setKitap_al_istek_veren_email(String kitap_al_istek_veren_email) {
        this.kitap_al_istek_veren_email = kitap_al_istek_veren_email;
    }

    public Timestamp getKitap_al_istek_geri_verme_tarihi() {
        return kitap_al_istek_geri_verme_tarihi;
    }

    public void setKitap_al_istek_geri_verme_tarihi(Timestamp kitap_al_istek_geri_verme_tarihi) {
        this.kitap_al_istek_geri_verme_tarihi = kitap_al_istek_geri_verme_tarihi;
    }
    int kitap_al_istek_id;
    String kitap_al_istek_kitap_adi;
    String kitap_al_istek_kitap_yayinevi;
    String kitap_al_istek_isteyen_ad_soyad;
    String kitap_al_istek_isteyen_email;
    String kitap_al_istek_durum;
    Timestamp kitap_al_istek_guncelleme_tarihi;
    String kitap_al_istek_veren_ad_soyad;
    String kitap_al_istek_veren_email;
    Timestamp kitap_al_istek_geri_verme_tarihi;
}
