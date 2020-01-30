/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class Igra implements Serializable{
    private int igraId;
    private Broj zadatiBroj;
    private Korisnik korisnik;
    private List<Pokusaj> listapokusaja;

    public Igra() {
        listapokusaja = new ArrayList<>();
    }

    public Igra(int igraId, Broj zadatiBroj, Korisnik korisnik, List<Pokusaj> listapokusaja) {
        this.igraId = igraId;
        this.zadatiBroj = zadatiBroj;
        this.korisnik = korisnik;
        this.listapokusaja = listapokusaja;
    }

    public List<Pokusaj> getListapokusaja() {
        return listapokusaja;
    }

    public void setListapokusaja(List<Pokusaj> listapokusaja) {
        this.listapokusaja = listapokusaja;
    }

    public int getIgraId() {
        return igraId;
    }

    public void setIgraId(int igraId) {
        this.igraId = igraId;
    }

    public Broj getZadatiBroj() {
        return zadatiBroj;
    }

    public void setZadatiBroj(Broj zadatiBroj) {
        this.zadatiBroj = zadatiBroj;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return String.format("id:{0}, broj:{1}, korisnik:{2}", igraId,zadatiBroj,korisnik);
    }

    public Pokusaj izvrsiPokusajNad(int pokusajBroj) {
        Pokusaj p = new Pokusaj(pokusajBroj, this);
        p.odrediIshod();
        listapokusaja.add(p);
        return p;
    }

  


    
    
}
