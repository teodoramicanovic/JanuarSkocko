/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import form.FormaServer;
import konstante.Konstante;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class Start {
    
    public static void main(String[] args) {
          Kontroler.getInstanca().inicijalizovanjeServera();
        new FormaServer().setVisible(true);
      
    }
}
