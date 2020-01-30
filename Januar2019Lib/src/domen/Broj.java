/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class Broj implements Serializable{
    private int brojId;
    private int vrednost;

    public Broj() {
    }

    public Broj(int brojId, int vrednost) {
        this.brojId = brojId;
        this.vrednost = vrednost;
    }

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    public int getBrojId() {
        return brojId;
    }

    public void setBrojId(int brojId) {
        this.brojId = brojId;
    }

  
    public String toString() {
        return ""+vrednost;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Broj other = (Broj) obj;
        if (this.brojId != other.brojId) {
            return false;
        }
        return true;
    }
    
   
    
    
    
    
    
}
