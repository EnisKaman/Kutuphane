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
public class RandevuRet {

    public int getRandevu_ret_id() {
        return randevu_ret_id;
    }

    public void setRandevu_ret_id(int randevu_ret_id) {
        this.randevu_ret_id = randevu_ret_id;
    }

    public String getRandevu_ret_isteyen_adsoyad() {
        return randevu_ret_isteyen_adsoyad;
    }

    public void setRandevu_ret_isteyen_adsoyad(String randevu_ret_isteyen_adsoyad) {
        this.randevu_ret_isteyen_adsoyad = randevu_ret_isteyen_adsoyad;
    }

    public String getRandevu_ret_isteyen_email() {
        return randevu_ret_isteyen_email;
    }

    public void setRandevu_ret_isteyen_email(String randevu_ret_isteyen_email) {
        this.randevu_ret_isteyen_email = randevu_ret_isteyen_email;
    }

    public String getRandevu_ret_veren_adsoyad() {
        return randevu_ret_veren_adsoyad;
    }

    public void setRandevu_ret_veren_adsoyad(String randevu_ret_veren_adsoyad) {
        this.randevu_ret_veren_adsoyad = randevu_ret_veren_adsoyad;
    }

    public String getRandevu_ret_veren_email() {
        return randevu_ret_veren_email;
    }

    public void setRandevu_ret_veren_email(String randevu_ret_veren_email) {
        this.randevu_ret_veren_email = randevu_ret_veren_email;
    }

    public String getRandevu_ret_konusu() {
        return randevu_ret_konusu;
    }

    public void setRandevu_ret_konusu(String randevu_ret_konusu) {
        this.randevu_ret_konusu = randevu_ret_konusu;
    }

    public Timestamp getRandevu_ret_talep_tarihi() {
        return randevu_ret_talep_tarihi;
    }

    public void setRandevu_ret_talep_tarihi(Timestamp randevu_ret_talep_tarihi) {
        this.randevu_ret_talep_tarihi = randevu_ret_talep_tarihi;
    }

    public String getRandevu_ret_durum() {
        return randevu_ret_durum;
    }

    public void setRandevu_ret_durum(String randevu_ret_durum) {
        this.randevu_ret_durum = randevu_ret_durum;
    }

    public String getRandevu_ret_sebep() {
        return randevu_ret_sebep;
    }

    public void setRandevu_ret_sebep(String randevu_ret_sebep) {
        this.randevu_ret_sebep = randevu_ret_sebep;
    }

    public Timestamp getRandevu_ret_olusturma_tarih() {
        return randevu_ret_olusturma_tarih;
    }

    public void setRandevu_ret_olusturma_tarih(Timestamp randevu_ret_olusturma_tarih) {
        this.randevu_ret_olusturma_tarih = randevu_ret_olusturma_tarih;
    }
    int randevu_ret_id;
    String randevu_ret_isteyen_adsoyad;
    String randevu_ret_isteyen_email;
    String randevu_ret_veren_adsoyad;
    String randevu_ret_veren_email;
    String randevu_ret_konusu;
    Timestamp randevu_ret_talep_tarihi;
    String randevu_ret_durum;
    String randevu_ret_sebep;
    Timestamp randevu_ret_olusturma_tarih;
}
