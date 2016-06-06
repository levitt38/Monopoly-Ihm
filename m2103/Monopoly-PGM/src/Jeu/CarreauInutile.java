/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;

/**
 *
 * @author mouhatcl
 */
public class CarreauInutile extends AutreCarreau{
    
    public CarreauInutile(int num, String chaine) {
        super(num, chaine);
    }
    
    @Override
    public Evenement action(Joueur j) {
        return Evenement.Rien;
    }

    @Override
    public TypeCarreau getType() {
        return TypeCarreau.AutreCarreau;
    }
}
