/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Cartes;

import Data.TypeCarte;

/**
 *
 * @author Louis
 */
public abstract class CarteDeplacement extends Carte{
    private int deplacement;

    public CarteDeplacement(String text, TypeCarte type, int location) {
        super(text, type);
        this.deplacement = location;
    }

    public int getDeplacement() {
        return deplacement;
    }
    
    
    
}
