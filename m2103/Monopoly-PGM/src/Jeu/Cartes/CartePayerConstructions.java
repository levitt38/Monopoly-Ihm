/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Cartes;

import Data.Evenement;
import Data.TypeCarte;
import Jeu.Monopoly;
import Jeu.Propriete;

/**
 *
 * @author Louis
 */
public class CartePayerConstructions extends CarteArgent{

    public CartePayerConstructions(String text, TypeCarte type, int somme) {
        super(text, type, somme);
    }

    @Override
    public Evenement use(Monopoly m) {
        int nbHotels = 0;
        int nbMaisons = 0;
        for(Propriete p:this.getOwner().getProprietes()){
            nbMaisons += p.getNbMaisons();
            nbHotels += (p.hasHotel()) ? 1 : 0 ;
        }
        this.getOwner().payer((100+15*this.getSomme())*nbHotels+(25+15*this.getSomme())*nbMaisons);
        return (this.getOwner().getCash()>=0) ? Evenement.Rien : Evenement.Bankrupt;
    }
    
}
