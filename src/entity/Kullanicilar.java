/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Enis
 */
public class Kullanicilar {

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getGuvenliksorusu() {
        return guvenliksorusu;
    }

    public void setGuvenliksorusu(String guvenliksorusu) {
        this.guvenliksorusu = guvenliksorusu;
    }

    public String getGuvenlikcevap() {
        return guvenlikcevap;
    }

    public void setGuvenlikcevap(String guvenlikcevap) {
        this.guvenlikcevap = guvenlikcevap;
    }

    public int getTemarengi() {
        return temarengi;
    }

    public void setTemarengi(int temarengi) {
        this.temarengi = temarengi;
    }

    public int getYetkituru() {
        return yetkituru;
    }

    public void setYetkituru(int yetkituru) {
        this.yetkituru = yetkituru;
    }
    int kullaniciID;
    String adsoyad;
    String email;
    String sifre;
    String telefon;
    String guvenliksorusu;
    String guvenlikcevap;
    int temarengi;
    int yetkituru;
}
