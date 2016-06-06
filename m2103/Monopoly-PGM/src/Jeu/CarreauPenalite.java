/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;

/**
 *
 * @author zussym
 */
public class CarreauPenalite extends AutreCarreau{

    private int penalite;
    
    public CarreauPenalite(int numero, String nomCarreau, int pe) {
        super(numero, nomCarreau);
        this.setPenalite(pe);
    }

    @Override
    public TypeCarreau getType() {
        
        return TypeCarreau.Penalite;
        
    }

    @Override
    public Evenement action(Joueur j) {
        
        return Evenement.PayerPenalite;
        
    }
    
    public void setPenalite(int penalite) {
        this.penalite = penalite;
    }

    public int getPenalite() {
        return penalite;
    }
}
