package Kutuphane;

public class Sinif {
    private String kitapadi;
    private String yazaradi;
    private String ad;
    private String soyad;
    private String tcno;
    private String uyeno; 
    private String yil;
    
    Sinif(){
        kitapadiAyarla("");
        yazaradiAyarla("");
        adAyarla("");
        soyadAyarla("");
        tcnoAyarla("");
        uyenoAyarla("");        
    }
    Sinif(String u,String t,String a,String s){
        adAyarla(a);
        soyadAyarla(s);
        tcnoAyarla(t);
        uyenoAyarla(u);        
    }
    Sinif(String ki,String ya,String uy){
        kitapadiAyarla(ki);
        yazaradiAyarla(ya);
        yilAyarla(uy);        
    }
    
    public void kitapadiAyarla(String kitap){
        kitapadi = kitap;
    }      
    
    public String kitapadiGetir(){
    return kitapadi;
    }
    public void yilAyarla(String yili){
        yil = yili;
    }      
    
    public String yilGetir(){
    return yil;
    }
    
    public void yazaradiAyarla(String yazar){
        yazaradi = yazar;
    }      
    
    public String yazaradiGetir(){
    return yazaradi;
    }
    
    public void adAyarla(String isim){
        ad = isim;
    }   
    
    public String adGetir(){
    return ad;
    }
    
    public void soyadAyarla(String soyisim){
        soyad = soyisim;
    }
    
    public String soyadGetir(){
    return soyad;
    }
    
    public void tcnoAyarla(String tcnum){
        tcno = tcnum;
    }   
    
    public String tcnoGetir(){
    return tcno;
    }
    
    public void uyenoAyarla(String uyenum){
        uyeno = uyenum;
    }   
    
    public String uyenoGetir(){
    return uyeno;
    }
       
    
}
