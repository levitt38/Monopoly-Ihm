/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import javax.swing.JOptionPane;

/**
 *
 * @author Louis
 */
public class IhmBoiteMessage {
    public static boolean afficherBoiteMessage(String titre, String message, int mode){
        int choix;
       if (mode==0){
           JOptionPane.showConfirmDialog(null,message,titre,JOptionPane.CLOSED_OPTION);
           return true;
        }else{
           choix = JOptionPane.showConfirmDialog(null,message,titre,JOptionPane.YES_NO_OPTION);
           return choix==JOptionPane.YES_OPTION;
       } 
    }
    
    public static boolean afficherBoiteMessage(String message, int mode){
        return afficherBoiteMessage("Pop-up", message, mode);
    }
    
    public static String afficherBoiteMessage(String titre, String message){
        return JOptionPane.showInputDialog(null,message, titre);
    }
}
