/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import dbb.DBBroker;
import domen.Broj;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class StorageBroj {
    private List<Broj> lista;

    public StorageBroj() {
        lista = new ArrayList<>();
    }
    
    public Broj BrojSledeciBroj() throws Exception{
       
            DBBroker dbb= Kontroler.getInstanca().getBroker();
            dbb.ucitajDriver();
            dbb.otvoriKonekciju();
            Broj broj =  dbb.vratiSledeciBroj();
            
            
            
            dbb.zatvoriKonekciju();
            return broj;

    }

   
    
    
}
