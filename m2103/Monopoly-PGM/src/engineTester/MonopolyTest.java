/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import Jeu.Controleur;
import java.util.ArrayList;

/**
 *
 * @author mouhatcl
 */
public class MonopolyTest extends Controleur{
    ArrayList<Integer> listeCoups ;
    int indice = 0;

    public MonopolyTest(ArrayList<Integer> listeCoups) {
        super();
        this.listeCoups = listeCoups;
    }
    
    @Override
    protected int lancerD6() {
        return listeCoups.get(this.indice);
    }
    
    
}
