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
public abstract class Carte {
    private String text;
    private TypeCarte type;
    private Joueur owner;
    
    public TypeCarte getType(){
        return this.type;
    }

    public Carte(String text, TypeCarte type) {
        this.text = text;
        this.type = type;
        this.owner = null;
    }
    
    public void setOwner(Joueur j){
        this.owner = j;
    }
    
    public void resetOwner(){
        this.owner = null;
    }
    
    protected Joueur getOwner(){
        return this.owner;
    }
    
    public abstract Evenement use(Monopoly m);
}
