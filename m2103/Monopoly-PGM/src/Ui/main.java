/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import IHM.EventHandler;
import IHM.Ihm3d;
import IHM.IhmConsole;
import IHM.IhmSwing;
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
        EventHandler handler = new EventHandler(c, new IhmConsole(c));
        c.setObservateur(handler);
        c.mainLoop();
                

       
       
       
       
    } 
   
   
    
   
   
   
}

