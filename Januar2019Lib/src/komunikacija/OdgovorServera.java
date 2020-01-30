/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class OdgovorServera implements Serializable{
    private boolean uspesno;
    private Object odgovor;
    private Exception greska;
    private int operacije;

    public OdgovorServera() {
    }

    public OdgovorServera( int operacije,boolean uspesno, Object odgovor, Exception greska) {
        this.uspesno = uspesno;
        this.odgovor = odgovor;
        this.greska = greska;
        this.operacije = operacije;
    }

    public Exception getGreska() {
        return greska;
    }

    public void setGreska(Exception greska) {
        this.greska = greska;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public int getOperacije() {
        return operacije;
    }

    public void setOperacije(int operacije) {
        this.operacije = operacije;
    }
    
    
    
}
