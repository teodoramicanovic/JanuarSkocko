/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Konstante;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class ServerNit extends Thread{
private ServerSocket ss;
private boolean pokrenut;

    public ServerNit() {
        pokrenut = false;
        inicijalizujServer();
        
    }


    @Override
    public synchronized void run() {
        while(true){
            try {
                while(!pokrenut){
                    wait();
                }
                Socket s = ss.accept();
                Kontroler.getInstanca().dodajKorisnikaNaServer(s);
            } catch (IOException ex) {
                Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }



    private void inicijalizujServer() {
    try {
        ss = new ServerSocket(Konstante.PORT);
    } catch (IOException ex) {
        Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Greska prilikom inicijalizovanja servera");
    }
    }
    
    public synchronized void pokreniServer(){
    pokrenut = true;
    this.notify();
    }
    
    public void zaustaviServer(){
    pokrenut = false;
    }
    
}
