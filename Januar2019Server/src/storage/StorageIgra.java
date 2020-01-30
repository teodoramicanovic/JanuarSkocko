/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import dbb.DBBroker;
import domen.Igra;
import java.util.ArrayList;
import java.util.List;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class StorageIgra {
    
    private List<Igra> lista;

    public StorageIgra() {
    lista = new ArrayList<>();
    }

    public void dodajIgru(Igra nova) {
        lista.add(nova);
    }

    public void sacuvajIgru(Igra igra) throws Exception {
        DBBroker dbb= Kontroler.getInstanca().getBroker();
       
      try{  
        dbb.ucitajDriver();
        dbb.otvoriKonekciju();
        dbb.sacuvajIgru(igra);
        dbb.commit();
      }catch(Exception ex){
          dbb.rollback();
          dbb.zatvoriKonekciju();
      throw  new Exception("Greska prilikom cuvanja igre");
      }
                
        
        
    }
    
    
}
