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
 * @author mouhatcl
 */
public abstract class CarreauAchetable extends Carreau{
	private Joueur _proprietaire;
        private int prixAchat;

    public CarreauAchetable(int numero, String nomCarreau,int prixAchat) {
        super(numero, nomCarreau);
        this.prixAchat = prixAchat;
    }
    public CarreauAchetable(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }
        public int getPrixAchat(){
            return this.prixAchat;
        }

	public abstract int calculLoyer() ;

	public Joueur getProprietaire() {
		return this._proprietaire;
	}

	public void acheter(Joueur aJ) {
            aJ.payerPropriete(this.getPrixAchat());
            aJ.addCarreauAchetable(this);
            this.setProprietaire(aJ);
	}
 
        public boolean verifierAchat(Joueur j){
            return this.getPrixAchat()<=j.getCash();
        }
        @Override
        public abstract TypeCarreau getType();
        
        @Override
        public Evenement action(Joueur j){
            CarreauAchetable c = (CarreauAchetable)j.getPositionCourante();
            Joueur proprio = c.getProprietaire();
            if(proprio!=null){
                if(c.getProprietaire().equals(j)){
                    return Evenement.SurSaCase;
                } else { return Evenement.PayerLoyer;}
            }else{
                if(c.verifierAchat(j)){
                    return Evenement.AchatPossible;
                }else{
                    return Evenement.AchatImpossible;
                }
            }
        }

    public void setProprietaire(Joueur _proprietaire) {
        this._proprietaire = _proprietaire;
    }
    
    public void resetProprietaire(){
        this._proprietaire = null;
    }
}
