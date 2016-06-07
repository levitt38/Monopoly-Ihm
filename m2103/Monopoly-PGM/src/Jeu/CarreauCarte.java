/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;
import Data.TypeCarte;

/**
 *
 * @author mouhatcl
 */
public class CarreauCarte extends AutreCarreau{
    
    private TypeCarte typeCarte;

    public TypeCarte getTypeCarte() {
        return typeCarte;
    }
    
    public CarreauCarte(int num, String chaine, TypeCarte typeCarte) {
        super(num, chaine);
        this.typeCarte = typeCarte;
    }

    @Override
    public TypeCarreau getType() {
        return TypeCarreau.Carte;
    }

    @Override
    public Evenement action(Joueur j) {
        return Evenement.TirerCarte;
    }
    
    
    
}
