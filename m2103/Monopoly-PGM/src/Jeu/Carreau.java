package Jeu;

import Data.Evenement;
import Data.TypeCarreau;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Carreau implements Serializable{
	private int _numero;
	private String _nomCarreau;
        private ArrayList<Joueur> joueurs;

    private void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
        
        
    public Carreau(int numero, String nomCarreau){
        this._nomCarreau = nomCarreau;
        this._numero = numero;
        this.setJoueurs(new ArrayList<Joueur>());
    }

    public int getNumero() {
        return _numero;
    }


    public synchronized String getNomCarreau() {
        return _nomCarreau;
    }
    
    public abstract TypeCarreau getType();
    
    public abstract Evenement action(Joueur j);

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    public void addJoueur(Joueur j){
        this.getJoueurs().add(j);
    }
    
    public void removeJoueur(Joueur j){
        if(this.getJoueurs().contains(j)){
            this.getJoueurs().remove(j);
        }
    }
    
    public abstract String affiche3d();

}