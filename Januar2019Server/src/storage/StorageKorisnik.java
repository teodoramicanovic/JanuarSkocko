/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import dbb.DBBroker;
import domen.Broj;
import domen.Korisnik;
import java.util.ArrayList;
import java.util.List;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class StorageKorisnik {

    
    private final List<Korisnik> lista;

    public StorageKorisnik() {
    lista = new ArrayList<>();
    }
    
//    public Korisnik findbyId(String korisnickoIme) throws Exception {
//        for (Korisnik k : lista) {
//            if(k==null) continue;
//            if(k.getKorisnickoIme().equals(korisnickoIme))
//                return k;
//        }
//        throw new Exception("Ne postoji korsinik sa datim korisnickim imenom");
//    }
    
    //direktno u bazi trazimo korisnika
    
    
    public Korisnik findbyId(String korisnickoIme) throws Exception{
     DBBroker dbb= Kontroler.getInstanca().getBroker();
            dbb.ucitajDriver();
            dbb.otvoriKonekciju();
            Korisnik korisnik=  dbb.vratiKorisnikaPoId(korisnickoIme);
            
            
            
            dbb.zatvoriKonekciju();
            return korisnik;
    
    }
    
}
