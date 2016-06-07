/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import IHM.EventHandler;
import Jeu.Carreau;
import Jeu.Controleur;
import Jeu.Monopoly;

/**
 *
 * @author nourik
 */
public class main{
    
    
    
    
    public static void main (String[] args) {
        Controleur c = new Controleur();
        EventHandler ihm = new EventHandler(c);
        c.setObservateur(ihm);
        c.mainLoop();
                

       
       
       
       
    } 
   
   
    
   
   
   
}

