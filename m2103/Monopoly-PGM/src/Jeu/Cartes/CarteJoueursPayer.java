/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Cartes;

import Data.Evenement;
import Data.TypeCarte;
import Jeu.Joueur;
import Jeu.Monopoly;

/**
 *
 * @author Louis
 */
public class CarteJoueursPayer extends CarteArgent{

    public CarteJoueursPayer(String text, TypeCarte type, int somme) {
        super(text, type, somme);
    }

    @Override
    public Evenement use(Monopoly m) {
        int montant = 0;
        for (Joueur j:m.getJoueurs()){
            if(j!=this.getOwner()){
                j.payer(this.getSomme()); // éGALMENT ATTENTION JOUEUR PEUT ICI MOURIR
                montant += this.getSomme();
            }
        }
        this.getOwner().payer(-montant);
        return Evenement.Rien;
    }
    
}
