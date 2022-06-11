package Kutuphane;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class bağlantı {
    private final String URL = "jdbc:mysql://localhost:3306/odev?useUnicode=true&characterEncoding=UTF-8";
    private final String USER = "root";
    private final String PASS = "";
    private Connection baglanti = null;
    bağlantı(){ 
        try{
            baglanti = DriverManager.getConnection(URL,USER,PASS);
            
        }catch(SQLException ex){
        ex.printStackTrace();
        System.exit(1);
        }
    }
    public List<Sinif> kitapListele(){
        List<Sinif> veriler = new ArrayList<Sinif>();
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement();
        gelen = kmt.executeQuery("select * from kitap");
        while(gelen.next())
        {
        veriler.add(new Sinif(gelen.getString("kitapadi"),gelen.getString("yazaradi"),gelen.getString("yili")));
        
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        
        return veriler;
    
    }
    public List<Sinif> boskitapListele(){
        List<Sinif> veriler = new ArrayList<Sinif>();
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement();
        gelen = kmt.executeQuery("select * from kitap where alan is null");
        while(gelen.next())
        {
        veriler.add(new Sinif(gelen.getString("kitapadi"),gelen.getString("yazaradi"),gelen.getString("yili")));
        
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        System.out.println(veriler.toArray());
        return veriler;
    
    }
    public List<Sinif> uyeListele(){
        List<Sinif> veriler = new ArrayList<Sinif>();
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement();
        gelen = kmt.executeQuery("select * from uyeler");
        while(gelen.next())
        {
        veriler.add(new Sinif(gelen.getString("uyeno"),gelen.getString("tcno"),gelen.getString("adi"),gelen.getString("soyadi")));
        
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        
        return veriler;
        }
    public int yeniKitap(String kitap,String yazar,String yil){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("insert into kitap(kitapadi,yazaradi,yili) values ('"+kitap+"','"+yazar+"','"+yil+"')");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
     
    }
    public int silKitap(String kitap,String yazar,String yil){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("DELETE FROM kitap WHERE kitapadi='"+kitap+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
     
    }
    public int silUye(String tc,String ad,String soyad){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("DELETE FROM uyeler WHERE tcno='"+tc+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
     
    }
    public int yeniUye(String uyeno,String tc,String ad,String soyad){
 
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("insert into uyeler(uyeno,tcno,adi,soyadi) values ('"+uyeno+"','"+tc+"','"+ad+"','"+soyad+"')");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
     
    }
    public String[] uyeBul(String uyeno,String tcno)
    {
        String [] bulunan = new String[2];
        ResultSet gelen = null;
        try {
            Statement kmt = baglanti.createStatement();
            gelen = kmt.executeQuery("select adi,soyadi from uyeler where uyeno='"+uyeno+"' AND tcno='"+tcno+"'");
            while(gelen.next())
            {
            bulunan[0] = gelen.getString("adi");
            bulunan[1] = gelen.getString("soyadi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bulunan;
    }
    public String[] uyeBul2(String uyeno)
    {
        String [] bulunan = new String[3];
        ResultSet gelen = null;
        try {
            Statement kmt = baglanti.createStatement();
            gelen = kmt.executeQuery("select adi,soyadi,tcno from uyeler where uyeno='"+uyeno+"'");
            while(gelen.next())
            {
            bulunan[0] = gelen.getString("adi");
            bulunan[1] = gelen.getString("soyadi");
            bulunan[2] = gelen.getString("tcno");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bulunan;
    }
public int editKitap(String kitap1,String kitap2,String yazar,String yil){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("update kitap set kitapadi='"+kitap2+"',yazaradi='"+yazar+"',yili='"+yil+"' where kitapadi='"+kitap1+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
     
    } 
public int edituye(String tc,String ad,String soyad){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("update uyeler set tcno='"+tc+"',adi='"+ad+"',soyadi='"+soyad+"' where tcno='"+tc+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;    
    }
public int kitapAyırt(String kitap,String uyeno){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("update kitap set alan='"+uyeno+"' where kitapadi='"+kitap+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;    
    } 
public int kitapBırak(String kitap,String uyeno){
        int i=0;
        try {           
            Statement kmt = baglanti.createStatement();
            i = kmt.executeUpdate("update kitap set alan=null where kitapadi='"+kitap+"'");
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;    
    }
public List<Sinif> ara(String ney,String aranan){    
List<Sinif> veriler = new ArrayList<Sinif>();
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement();
        gelen = kmt.executeQuery("select * from kitap where "+ney+"='"+aranan+"'");
        while(gelen.next())
        {
        veriler.add(new Sinif(gelen.getString("kitapadi"),gelen.getString("yazaradi"),gelen.getString("yili")));
        
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        
        return veriler;
}
public List<Sinif> ara2(String ney,String aranan){    
List<Sinif> veriler = new ArrayList<Sinif>();
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement();
        gelen = kmt.executeQuery("select * from kitap where "+ney+" LIKE '%"+aranan+"%'");
        while(gelen.next())
        {
        veriler.add(new Sinif(gelen.getString("kitapadi"),gelen.getString("yazaradi"),gelen.getString("yili")));
        
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        
        return veriler;
}
public String [] uyeyeAit(String uyeno){    

        String [] dizi = new String[10];
        ResultSet gelen = null;
        try{
        Statement kmt = baglanti.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_READ_ONLY);
        gelen = kmt.executeQuery("select kitapadi from kitap where alan='"+uyeno+"'");

        int i=0;
        while(gelen.next())
        {
        dizi[i]=gelen.getString("kitapadi"); 
        i++;
        }
        }catch(SQLException s)
        {
        s.printStackTrace();
        }
        try{
            gelen.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        
        }
        
        return dizi;
}
    }
