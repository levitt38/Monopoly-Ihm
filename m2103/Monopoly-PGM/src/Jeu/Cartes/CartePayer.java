/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Cartes;

import Data.Evenement;
import Data.TypeCarte;
import Jeu.Monopoly;

/**
 *
 * @author Louis
 */
public class CartePayer extends CarteArgent{

    public CartePayer(String text, TypeCarte type, int somme) {
        super(text, type, somme);
    }

    @Override
    public Evenement use(Monopoly m) {
        this.getOwner().payer(this.getSomme());
        return (this.getOwner().getCash()>=0) ? Evenement.Rien : Evenement.Bankrupt;
    }
    
}
