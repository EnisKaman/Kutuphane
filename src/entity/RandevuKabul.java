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
public class RandevuKabul {

    public int getRandevu_kabul_id() {
        return randevu_kabul_id;
    }

    public void setRandevu_kabul_id(int randevu_kabul_id) {
        this.randevu_kabul_id = randevu_kabul_id;
    }

    public String getRandevu_kabul_isteyen_adsoyad() {
        return randevu_kabul_isteyen_adsoyad;
    }

    public void setRandevu_kabul_isteyen_adsoyad(String randevu_kabul_isteyen_adsoyad) {
        this.randevu_kabul_isteyen_adsoyad = randevu_kabul_isteyen_adsoyad;
    }

    public String getRandevu_kabul_isteyen_email() {
        return randevu_kabul_isteyen_email;
    }

    public void setRandevu_kabul_isteyen_email(String randevu_kabul_isteyen_email) {
        this.randevu_kabul_isteyen_email = randevu_kabul_isteyen_email;
    }

    public String getRandevu_kabul_veren_adsoyad() {
        return randevu_kabul_veren_adsoyad;
    }

    public void setRandevu_kabul_veren_adsoyad(String randevu_kabul_veren_adsoyad) {
        this.randevu_kabul_veren_adsoyad = randevu_kabul_veren_adsoyad;
    }

    public String getRandevu_kabul_veren_email() {
        return randevu_kabul_veren_email;
    }

    public void setRandevu_kabul_veren_email(String randevu_kabul_veren_email) {
        this.randevu_kabul_veren_email = randevu_kabul_veren_email;
    }

    public String getRandevu_kabul_konusu() {
        return randevu_kabul_konusu;
    }

    public void setRandevu_kabul_konusu(String randevu_kabul_konusu) {
        this.randevu_kabul_konusu = randevu_kabul_konusu;
    }

    public Timestamp getRandevu_kabul_talep_tarihi() {
        return randevu_kabul_talep_tarihi;
    }

    public void setRandevu_kabul_talep_tarihi(Timestamp randevu_kabul_talep_tarihi) {
        this.randevu_kabul_talep_tarihi = randevu_kabul_talep_tarihi;
    }

    public String getRandevu_kabul_durum() {
        return randevu_kabul_durum;
    }

    public void setRandevu_kabul_durum(String randevu_kabul_durum) {
        this.randevu_kabul_durum = randevu_kabul_durum;
    }

    public Timestamp getRandevu_kabul_olusturma_tarih() {
        return randevu_kabul_olusturma_tarih;
    }

    public void setRandevu_kabul_olusturma_tarih(Timestamp randevu_kabul_olusturma_tarih) {
        this.randevu_kabul_olusturma_tarih = randevu_kabul_olusturma_tarih;
    }
    int randevu_kabul_id;
    String randevu_kabul_isteyen_adsoyad;
    String randevu_kabul_isteyen_email;
    String randevu_kabul_veren_adsoyad;
    String randevu_kabul_veren_email;
    String randevu_kabul_konusu;
    Timestamp randevu_kabul_talep_tarihi;        
    String randevu_kabul_durum;
    Timestamp randevu_kabul_olusturma_tarih;
}
