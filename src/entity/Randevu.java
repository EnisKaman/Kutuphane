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
public class Randevu {

    public int getRandevu_id() {
        return randevu_id;
    }

    public void setRandevu_id(int randevu_id) {
        this.randevu_id = randevu_id;
    }

    public String getRandevu_isteyen_adsoyad() {
        return randevu_isteyen_adsoyad;
    }

    public void setRandevu_isteyen_adsoyad(String randevu_isteyen_adsoyad) {
        this.randevu_isteyen_adsoyad = randevu_isteyen_adsoyad;
    }

    public String getRandevu_isteyen_email() {
        return randevu_isteyen_email;
    }

    public void setRandevu_isteyen_email(String randevu_isteyen_email) {
        this.randevu_isteyen_email = randevu_isteyen_email;
    }

    public String getRandevu_veren_adsoyad() {
        return randevu_veren_adsoyad;
    }

    public void setRandevu_veren_adsoyad(String randevu_veren_adsoyad) {
        this.randevu_veren_adsoyad = randevu_veren_adsoyad;
    }

    public String getRandevu_veren_email() {
        return randevu_veren_email;
    }

    public void setRandevu_veren_email(String randevu_veren_email) {
        this.randevu_veren_email = randevu_veren_email;
    }

    public String getRandevu_konusu() {
        return randevu_konusu;
    }

    public void setRandevu_konusu(String randevu_konusu) {
        this.randevu_konusu = randevu_konusu;
    }

    public Timestamp getRandevu_talep_tarihi() {
        return randevu_talep_tarihi;
    }

    public void setRandevu_talep_tarihi(Timestamp randevu_talep_tarihi) {
        this.randevu_talep_tarihi = randevu_talep_tarihi;
    }

    public String getRandevu_durum() {
        return randevu_durum;
    }

    public void setRandevu_durum(String randevu_durum) {
        this.randevu_durum = randevu_durum;
    }

    public Timestamp getRandevu_olusturma_tarih() {
        return randevu_olusturma_tarih;
    }

    public void setRandevu_olusturma_tarih(Timestamp randevu_olusturma_tarih) {
        this.randevu_olusturma_tarih = randevu_olusturma_tarih;
    }
    int randevu_id;
    String randevu_isteyen_adsoyad;
    String randevu_isteyen_email;
    String randevu_veren_adsoyad;
    String randevu_veren_email;
    String randevu_konusu;
    Timestamp randevu_talep_tarihi;
    String randevu_durum;
    Timestamp randevu_olusturma_tarih;
}
