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
public class CarteSortiePrison extends Carte{

    public CarteSortiePrison(String text, TypeCarte type) {
        super(text, type);
    }

    @Override
    public Evenement use(Monopoly m) {
        this.getOwner().setPositionCourante(m.getCarreau(10));
        return Evenement.SortieDePrison;
    }
    
}
