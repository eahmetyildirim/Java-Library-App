package Kutuphane;


public class Personel {
    String tcno;
    String ad;
    String soyad;
    String bolumAdi;

    public Personel(String tcno, String ad, String soyad, String bolumAdi) {
        this.tcno = tcno;
        this.ad = ad;
        this.soyad = soyad;
        this.bolumAdi = bolumAdi;
    }

   

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBolumAdi() {
        return bolumAdi;
    }

    public void setBolumAdi(String bolumAdi) {
        this.bolumAdi = bolumAdi;
    }
    
    
}
