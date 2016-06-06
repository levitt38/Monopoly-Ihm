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
public class CarteAvancer extends CarteDeplacement{

    public CarteAvancer(String text, TypeCarte type, int location) {
        super(text, type, location);
    }

    @Override
    public Evenement use(Monopoly m) {
        Evenement ret = (this.getOwner().getPositionCourante().getNumero()>=this.getDeplacement()) ? Evenement.PasseParDepart : Evenement.Rien;
        getOwner().setPositionCourante(m.getCarreau(this.getDeplacement()));
        return ret;
        // reste à voir comment il joue ce tour, je sais pas encore
    }
    
}
