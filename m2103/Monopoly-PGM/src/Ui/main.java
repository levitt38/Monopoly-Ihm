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
import engineTester.MonopolyTest;

/**
 *
 * @author nourik
 */
public class main{
    
    
    
    
    public static void main (String[] args) {
        Controleur c = new MonopolyTest("1,0,1,0,2,0,3,7,8,7,9");
        EventHandler handler = new EventHandler(c, new IhmConsole(c));
        c.setObservateur(handler);
        c.mainLoop();
    } 
   
   
    
   
   
   
}

