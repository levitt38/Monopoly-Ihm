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
public abstract class CarteArgent extends Carte{
    private int somme;
    
    public CarteArgent(String text, TypeCarte type, int somme) {
        super(text, type);
        this.somme = somme;
    }

    public int getSomme() {
        return somme;
    }
    
}
