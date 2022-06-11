/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kutuphane;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeriTabani {

//import com.mysql.cj.xdevapi.Statement;
    private String kullanici_adi = "root";
    private String parola = "";
    private Connection con = null;

    private Statement sorgum = null;
    private PreparedStatement preparedStatement = null;

    public VeriTabani() {
        String url = "jdbc:mysql://localhost:3306/final?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        try {
            con = (Connection) DriverManager.getConnection(url, kullanici_adi, parola);

        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            ex.printStackTrace();
        }

    }

    boolean GirisYap(String k_adi, String sifre,int durum) {

        PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("select * from tbl_kullanici where kuladi=? and sifre=? and durum=?");
            sorgu.setString(1, k_adi);
            sorgu.setString(2, sifre);
            sorgu.setInt(3, durum);
            ResultSet rs = sorgu.executeQuery();
            if(rs.next()){  
                return true;
            }else{
                return false;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    ArrayList<String> ListBolumler() {
        ArrayList<String> Results=new ArrayList<>();
        PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("select * from tbl_bolumler");

            ResultSet rs = sorgu.executeQuery();
            while(rs.next()){  
                Results.add(rs.getString("bolumadi"));
            }
         
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Results;
    }
    
    int getBolumNo(String bolumAdi) {

        PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("select * from tbl_bolumler where bolumadi=?");
            sorgu.setString(1, bolumAdi);
     
            ResultSet rs = sorgu.executeQuery();
            if(rs.next()){  
                return rs.getInt("bolumno");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    String getBolumAdi(int bolumNo) {

        PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("select * from tbl_bolumler where bolumno=?");
            sorgu.setInt(1, bolumNo);
     
            ResultSet rs = sorgu.executeQuery();
            if(rs.next()){  
                return rs.getString("bolumAdi");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }
    boolean PersonelEkle(String TCno,String Ad,String Soyad,String BolumAdi){
    
            PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("INSERT INTO tbl_personel (tcno,ad,soyad,bolumno) values (?,?,?,?)");
            sorgu.setString(1, TCno);
            sorgu.setString(2, Ad);
            sorgu.setString(3, Soyad);
          
            sorgu.setInt(4,getBolumNo(BolumAdi));
            int rs = sorgu.executeUpdate();
            if(rs>0)
            return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
        ArrayList<Personel> ListPersonel() {
        ArrayList<Personel> Results=new ArrayList<>();
        PreparedStatement sorgu;
        try {
            sorgu = con.prepareStatement("select * from tbl_personel");

            ResultSet rs = sorgu.executeQuery();
            while(rs.next()){  
                Results.add(new Personel(rs.getString("tcno"),rs.getString("ad"),rs.getString("soyad"),getBolumAdi(rs.getInt("bolumno"))));
            }
         
           
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabani.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Results;
    }
        
        
        
        
        
}
