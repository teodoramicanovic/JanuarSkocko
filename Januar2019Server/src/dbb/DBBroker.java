/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import com.mysql.jdbc.Connection;
import domen.Broj;
import domen.Igra;
import domen.Korisnik;
import domen.Pokusaj;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class DBBroker {
    private Connection konekcija;

    public DBBroker() {
    }
    
    public void ucitajDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void otvoriKonekciju(){
    String url = "jdbc:mysql://localhost:3306/januar2019";
        try {
            konekcija = (Connection) DriverManager.getConnection(url, "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void zatvoriKonekciju(){
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public void commit(){
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    public void rollback(){
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Broj vratiSledeciBroj() throws Exception {
        String upit = "SELECT * FROM broj";
        
        try {
            Statement stat = konekcija.createStatement();
            ResultSet rs = stat.executeQuery(upit);
            if(rs.next()){
            int brojId = rs.getInt("brojid");
            int vrednost = rs.getInt("vrednost");
            return new Broj(brojId, vrednost);
            
            }
            throw  new Exception();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Greska prilikom iscitavanja iz baze");
        }
        
    }

    public Korisnik vratiKorisnikaPoId(String korisnickoime) throws Exception {
          String upit = "SELECT * FROM korisnik WHERE korisnickoime="+korisnickoime;
        
        try {
            Statement stat = konekcija.createStatement();
            ResultSet rs = stat.executeQuery(upit);
            if(rs.next()){
            String ki = rs.getString("korisnickoime");
            String ime = rs.getString("ime");
                        String prezime = rs.getString("prezime");

            return new Korisnik(ki, ime, prezime);
            }
            throw  new Exception("Ne postoji u bazi takav korisnik");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Greska prilikom iscitavanja iz baze");
        }
        
    }

    public void sacuvajIgru(Igra igra) throws SQLException {
        //skidamo u bazi da ne bude autoinkrement za igru
        String nadjiID = "SECET MAX(igraid) FROM igra";
        Statement stat = konekcija.createStatement();
        ResultSet rs = stat.executeQuery(nadjiID);
        int igraId = 0;
        if(rs.next()){
        igraId = rs.getInt("maxid")+1;
        String upitIgra = "INSERT INTO igra VALUES(?,?,?)";
            PreparedStatement ps = konekcija.prepareStatement(upitIgra);
            ps.setInt(1, igraId);
            ps.setInt(2, igra.getZadatiBroj().getVrednost());
            ps.setString(2, igra.getKorisnik().getKorisnickoIme());
            ps.executeUpdate();
            
            for (Pokusaj p : igra.getListapokusaja()) {
                String upitPokusaj = "INSERT INTO pokusaj VALUES (?,?,?)";
                 PreparedStatement pres = konekcija.prepareStatement(upitPokusaj);
            pres.setInt(1, igraId);
            pres.setInt(2, p.getRbPOkusaja());
            pres.setInt(2, p.getPokusajBroj());
            pres.executeUpdate();
                
                
                
            }
        }
        
    }
    
    
    
}
