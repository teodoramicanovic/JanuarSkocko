/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import konstante.Konstante;

/**
 *
 * @author Korisnik
 */
public class Pokusaj implements Serializable {

    private int rbPOkusaja;
    private int PokusajBroj;
    private int naMestu;
    private int vanMesta;
    private Igra igra;
    private int ishod;

    public Pokusaj() {
    }

    public Pokusaj(int PokusajBroj, Igra igra) {
        this.rbPOkusaja = igra.getListapokusaja().size() + 1;
        this.PokusajBroj = PokusajBroj;
        this.igra = igra;
        odigrajPokusaj();
    }

    public int getRbPOkusaja() {
        return rbPOkusaja;
    }

    public void setRbPOkusaja(int rbPOkusaja) {
        this.rbPOkusaja = rbPOkusaja;
    }

    public int getPokusajBroj() {
        return PokusajBroj;
    }
    
    

    public void setPokusajBroj(int PokusajBroj) {
        this.PokusajBroj = PokusajBroj;
    }

    @Override
    public String toString() {
        return "" + PokusajBroj;
    }

    private void odigrajPokusaj() {
        int z = igra.getZadatiBroj().getVrednost();
        int p = PokusajBroj;
        naMestu = 0;
        vanMesta = 0;
        boolean[] iskorisceniNaMestu = new boolean[4];
        boolean[] iskorisceniVanMesta = new boolean[4];

        //oodredjivanje onih koji su na mestu
        int az = z;
        int ap = p;

        for (int poz = 3; poz >= 0; poz--) {
            int cz = az % 10;
            int cp = ap % 10;

            if (cp == cz) {
                iskorisceniNaMestu[poz] = true;
                naMestu++;
            }
            az /= 10;
            ap /= 10;

        }

        //Odredjivanje onih koji postoje al nisu na mestu
        az = z;
        ap = p;
        for (int poz = 3; poz >= 0; poz--) {
            int cp = ap % 10;
            ap /= 10;
            if (iskorisceniNaMestu[poz]) {
                continue;
            }
            az = z;
            for (int poz2 = 3; poz2 >= 0; poz2--) {
                int cz = az % 10;
                az /= 10;
                if (iskorisceniNaMestu[poz2] || iskorisceniVanMesta[poz2]) {
                    continue;
                }
                if (cz == cp) {
                    iskorisceniVanMesta[poz2] = true;
                    vanMesta++;
                    break;
                }

            }
        }

    }

    void odrediIshod() {
if(naMestu == 4 ){
ishod = Konstante.IP_POgodio;
}
else if (rbPOkusaja == 6){
    ishod = Konstante.IP_6GRESAKA;

}
else ishod = Konstante.IP_POGRESIO;
    }

    public int getNaMestu() {
        return naMestu;
    }

    public void setNaMestu(int naMestu) {
        this.naMestu = naMestu;
    }

    public int getVanMesta() {
        return vanMesta;
    }

    public void setVanMesta(int vanMesta) {
        this.vanMesta = vanMesta;
    }

    public Igra getIgra() {
        return igra;
    }

    public void setIgra(Igra igra) {
        this.igra = igra;
    }

    public int getIshod() {
        return ishod;
    }

    public void setIshod(int ishod) {
        this.ishod = ishod;
    }

}

