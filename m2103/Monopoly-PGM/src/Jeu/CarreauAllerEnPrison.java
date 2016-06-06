/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;

/**
 *
 * @author tordoe
 */
public class CarreauAllerEnPrison extends AutreCarreau {
    
    public CarreauAllerEnPrison(int num, String chaine) {
        super(num, chaine);
    }
    
    @Override
    public Evenement action(Joueur j) {
        return Evenement.AllerEnPrison;
    }
    
    
    
}
