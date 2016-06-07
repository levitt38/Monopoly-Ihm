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
    
    public Prison(int num){
        super(num,"Prison");
    }
    
    @Override
    public TypeCarreau getType(){
        return TypeCarreau.Prison;
    }
    
    @Override
   public Evenement action(Joueur j) { 
       return Evenement.EstEnPrison;
   }
    
}
