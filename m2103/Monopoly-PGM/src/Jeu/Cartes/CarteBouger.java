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
public class CarteBouger extends CarteDeplacement{

    public CarteBouger(String text, TypeCarte type, int location) {
        super(text, type, location);
    }

    @Override
    public Evenement use(Monopoly m) {
        int carreau = this.getOwner().getPositionCourante().getNumero()+this.getDeplacement();
        Evenement ret = (carreau > 39) ? Evenement.PasseParDepart : Evenement.Rien;
        getOwner().setPositionCourante(m.getCarreau(carreau%40)); // ATTENTION PEUT POSER DES PROBLèMES EN CAS DE CASE NéGATIVE
        return ret;
        // reste à voir comment il joue ce tour, je sais pas encore
    }
    
}
