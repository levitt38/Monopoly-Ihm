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
public class CarteTP extends CarteDeplacement{

    public CarteTP(String text, TypeCarte type, int location) {
        super(text, type, location);
    }

    @Override
    public Evenement use(Monopoly m) {
        this.getOwner().setPositionCourante(m.getCarreau(this.getDeplacement()));
        return Evenement.Rien;
    }
    
}
