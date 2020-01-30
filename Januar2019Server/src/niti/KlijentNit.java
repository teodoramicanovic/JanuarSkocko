/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Igra;
import domen.Korisnik;
import domen.Pokusaj;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.OdgovorServera;
import komunikacija.ZahtevKorisnika;
import konstante.Konstante;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class KlijentNit extends Thread{
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Korisnik korisnik;
    int preostaloLogovanja;
    Igra igra;

    public KlijentNit(Socket socket) throws IOException {
        
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            korisnik = null;// default je isto null
            preostaloLogovanja = 3;
      
    }

    @Override
    public void run() {
        while(true){
            try {
                //dokle god nije regisrovan necu novu igru da otvorim za klijenta
                ZahtevKorisnika zk =(ZahtevKorisnika) in.readObject();
                if(zk.getOperacija() != Konstante.SO_PRIJAVA && korisnik == null){
                Exception greska = new Exception("Morate biti prijavljeni da bi ste trazili ovu operaciju!");
                    OdgovorServera o = new OdgovorServera(Konstante.GRESKA,false, null, greska);
                    out.writeObject(o);
                    out.flush();
                    continue;
                }
                switch(zk.getOperacija()){
                    case Konstante.SO_PRIJAVA:
                        String korisnickoIme = (String) zk.getPodaci();
                        try{
                        Korisnik pronadjen = Kontroler.getInstanca().ulogujKorisnika(korisnickoIme);
                        this.korisnik = pronadjen;
                        out.writeObject(new OdgovorServera(Konstante.PRONADJEN,true, pronadjen, null));
                        igra = Kontroler.getInstanca().novaIgra(pronadjen);
                        }catch(Exception ex){
                        preostaloLogovanja --;
                        out.writeObject(new OdgovorServera(Konstante.GRESKA,false, null, ex));
                        if(preostaloLogovanja == 0){
                        odjaviKorisnika(new Exception("Nemate prava vise na logovanje"));
                        }
                        }break;
                     case Konstante.SO_ODJAVA: odjaviKorisnika(null);break;
                    case Konstante.SO_POKUSAJ:
                        Integer pokusajBroj =(Integer) zk.getPodaci();
                        izvrsiPokusaj(pokusajBroj.intValue());
                        break;

                
                
                }
            } catch (IOException ex) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

    private void odjaviKorisnika(Exception exception) throws IOException {
OdgovorServera o = new OdgovorServera(Konstante.SO_ODJAVIKORISNIKa,false,null,exception);
Kontroler.getInstanca().obrisiKorisnika(this);
        this.interrupt();

    }

    private void izvrsiPokusaj(int pokusajBroj) throws IOException, Exception {
      //3 ishoda:
      //pogodio, istekla igra, ni pogodio ni istekla igra
       Pokusaj pokusaj = igra.izvrsiPokusajNad(pokusajBroj);
OdgovorServera o = new OdgovorServera(Konstante.OS_ISHODPOKUSAJA, false, null, null);
       switch(pokusaj.getIshod()){ // obavestavamo i servera i klijenta a ako se desilo nesto sto prouzrokuje kraj igre pogodio ili 6 gresaka
           case Konstante.IP_POgodio:
               Kontroler.getInstanca().krajIgre(igra);
               Kontroler.getInstanca().obrisiKorisnika(this);
this.interrupt();
               break;
           case Konstante.IP_POGRESIO: 
               
               break;
           case Konstante.IP_6GRESAKA://sacuva u bazi igru i otkaci korisnika
                              Kontroler.getInstanca().krajIgre(igra);
Kontroler.getInstanca().obrisiKorisnika(this);
this.interrupt();
               break;
                       


       } 
       
    }

    public void javiBrojKorisnika(int brojKorisnika) throws IOException {
        out.writeObject(new OdgovorServera(Konstante.IP_BROJKORISNIKA, true, brojKorisnika, null));
    }
    
    
    
    
    
    
}
