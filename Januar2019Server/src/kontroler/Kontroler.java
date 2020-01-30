/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dbb.DBBroker;
import domen.Igra;
import domen.Korisnik;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import niti.KlijentNit;
import niti.ServerNit;
import storage.StorageBroj;
import storage.StorageIgra;
import storage.StorageKorisnik;

/**
 *
 * @author Korisnik
 */
public class Kontroler {
    private static Kontroler instanca;
    private final ServerNit server;
    private final List<KlijentNit> listaKlijenata;
    private final StorageKorisnik storageKorisnik;
    private final StorageIgra storageIgra;
    private final StorageBroj storageBroj;
    private DBBroker dbb;

    private Kontroler() {
        dbb= new DBBroker();
        server = new ServerNit();
      listaKlijenata = new ArrayList<>();
      storageKorisnik = new StorageKorisnik();
      storageIgra = new StorageIgra();
      storageBroj = new StorageBroj();
    }
    
    public static Kontroler getInstanca(){
    if (instanca == null)
    instanca = new Kontroler();
    return instanca;
    
    }
    
    public void inicijalizovanjeServera(){
            server.start();
  
    }
    public void pokreniServer(){
    server.pokreniServer();
    }
    
    public void zaustaviServer(){
    server.zaustaviServer();
    }

    public void dodajKorisnikaNaServer(Socket s) throws IOException {
        KlijentNit klijent = new KlijentNit(s);
        klijent.start();
        listaKlijenata.add(klijent);
        javiBrojKorisnika();
    }

    public Korisnik ulogujKorisnika(String korisnickoIme) throws Exception {
        return storageKorisnik.findbyId(korisnickoIme);
    }

    public void obrisiKorisnika(KlijentNit korisnik) throws IOException {
        listaKlijenata.remove(korisnik);
        javiBrojKorisnika();
       
    }

    public Igra novaIgra(Korisnik pronadjen) throws Exception {
        Igra nova = new Igra(-1, storageBroj.BrojSledeciBroj(), pronadjen, new ArrayList());
        storageIgra.dodajIgru(nova);
        return nova;
    }

    public void krajIgre(Igra igra) throws Exception {
        storageIgra.sacuvajIgru(igra);
    }

    private void javiBrojKorisnika() throws IOException {
        for (KlijentNit klijentNit : listaKlijenata) {
            klijentNit.javiBrojKorisnika(listaKlijenata.size());
        }
    }

    public DBBroker getBroker(){
    return dbb;
    }
    
}
