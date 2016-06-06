/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;
import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author nourik
 */
public class Prison extends AutreCarreau{
    private HashSet<Joueur> listeDétenus;
    
    public Prison(int num){
        super(num,"Prison");
        this.listeDétenus = new HashSet<>();
    }
    
    @Override
    public TypeCarreau getType(){
        return TypeCarreau.Prison;
    }
    
    @Override
   public Evenement action(Joueur j) { 
       return Evenement.EstEnPrison;
   }
 
    public void emprisonnerDétenu(Joueur j){
        j.setPositionCourante(this);
        j.setPrisonnier(true);
    }
    
    public void libérerDétenu(Joueur j){
        this.removeJoueur(j);
        j.setPrisonnier(false);
    }
    
}
