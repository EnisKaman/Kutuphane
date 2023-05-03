/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Enis
 */
public class KitapEnvanter {

    public int getEnvanter_id() {
        return envanter_id;
    }

    public void setEnvanter_id(int envanter_id) {
        this.envanter_id = envanter_id;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public int getKitap_sayisi() {
        return kitap_sayisi;
    }

    public void setKitap_sayisi(int kitap_sayisi) {
        this.kitap_sayisi = kitap_sayisi;
    }

    public String getKitap_yayinevi() {
        return kitap_yayinevi;
    }

    public void setKitap_yayinevi(String kitap_yayinevi) {
        this.kitap_yayinevi = kitap_yayinevi;
    }

    public int getOkuma_sayisi() {
        return okuma_sayisi;
    }

    public void setOkuma_sayisi(int okuma_sayisi) {
        this.okuma_sayisi = okuma_sayisi;
    }

    public int getElde_olan() {
        return elde_olan;
    }

    public void setElde_olan(int elde_olan) {
        this.elde_olan = elde_olan;
    }
    int envanter_id;
    String kitap_adi;
    int kitap_sayisi;
    String kitap_yayinevi;
    int okuma_sayisi;
    int elde_olan;
}
