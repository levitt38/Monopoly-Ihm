package Jeu;

import Data.TypeCarreau;
import Exceptions.joueurDeadException;
import java.util.ArrayList;
import java.util.HashSet;

public class Joueur {
	private String _nomJoueur;
	private int _cash = 1500;
        private int doublesALaSuite = 0;
        private int nb_toursEnPrison = 0;
        private boolean dernierDouble = false;
        private boolean prisonnier = false;
	private HashSet<Gare> _gares;
        private HashSet<Propriete> _proprietes;
        private HashSet<Compagnie> _compagnies;
        private HashSet<CarreauAchetable> _carreaux;
	private Carreau _positionCourante;

    public Joueur(String _nomJoueur, Carreau pos) {
        this._nomJoueur = _nomJoueur;
        this.setPositionCourante(pos);
        this._gares = new HashSet<>(); 
        this._proprietes = new HashSet<>();
        this._compagnies = new HashSet<>();
        this._carreaux = new HashSet<>();
    }
    
    public boolean estBankrupt(){
        return this.getCash()<0;
    }

    public int getNb_toursEnPrison() {
        return nb_toursEnPrison;
    }

    public void setNb_toursEnPrison(int nb_toursEnPrison) {
        this.nb_toursEnPrison = nb_toursEnPrison;
    }
    
    public boolean estPrisonnier() {
        return prisonnier;
    }

    public void setPrisonnier(boolean prisonnier) {
        this.prisonnier = prisonnier;
    }

    public boolean isDernierDouble() {
        return dernierDouble;
    }

    public void setDernierDouble(boolean dernierDouble) {
        this.dernierDouble = dernierDouble;
    }
    
    public int getDoublesALaSuite() {
        return doublesALaSuite;
    }

    public void setDoublesALaSuite(int doublesALaSuite) {
        this.doublesALaSuite = doublesALaSuite;
    }
    /////////////////////////////////////////////
    public void recevoirPaie(){
        this._cash += 200;
    }
    
    public int getCash() {
        return _cash;
    }
    
    public void recevoirLoyer(int loyer){
        this._cash = getCash()+loyer;
    }
    
    public void payerLoyer(CarreauAchetable c){
        this._cash -= c.calculLoyer();
        c.getProprietaire().recevoirLoyer(c.calculLoyer());
    }
    /////////////////////////////////////////////
    public Carreau getPositionCourante(){
        return this._positionCourante;
    }
    
    public void setPositionCourante(Carreau _positionCourante) {
        if(this._positionCourante != null){
            this._positionCourante.removeJoueur(this);
        }
        _positionCourante.addJoueur(this);
        this._positionCourante = _positionCourante;
    }
    /////////////////////////////////////////////
    public HashSet<Gare> getGares() {
        return _gares;
    }
    public int getNbGares(){
        return getGares().size();
    }
    public HashSet<Compagnie> getCompagnies(){
        return _compagnies;
    }
    
    public int getNbCompagnies(){ 
        return getCompagnies().size();
    }
    /////////////////////////////////////////////
    public void addCarreauAchetable(CarreauAchetable c){
        this._carreaux.add(c);
        TypeCarreau type = c.getType();
        switch(type){
            case ProprieteAConstruire : this._proprietes.add((Propriete)c);break;
            case Gare : this._gares.add((Gare)c);break;
            case Compagnie : this._compagnies.add((Compagnie)c);break;
            default : return;
        }
    }
    
    public void removeCarreauAchetable(CarreauAchetable c){
        TypeCarreau type = c.getType();
        switch(type){
            case ProprieteAConstruire : this._proprietes.remove((Propriete)c);break;
            case Gare : this._gares.remove((Gare)c);break;
            case Compagnie : this._compagnies.remove((Compagnie)c);break;
            default : break;
        }
    }
    /////////////////////////////////////////////
    public String getNomJoueur() {
        return _nomJoueur;
    }

    void payerPropriete(int prixAchat) {
        this.setCash(this.getCash()-prixAchat);
    }

    public void setCash(int _cash) {
        this._cash = _cash;
    }

    public HashSet<Propriete> getProprietes() {
        return _proprietes;
    }
    /////////////////////////////////////////////
    public ArrayList<Propriete> getProprietesConstructibles(){
        HashSet<Groupe> grp = new HashSet<>();
        for(Propriete p:this._proprietes){
            grp.add(p.getGroupe());
        }
        ArrayList<Propriete> propACon = new ArrayList<>();
        for (Groupe g:grp){
            boolean contains = true;
            for(Propriete p:g.getProprietes()){
                contains = contains && this.getProprietes().contains(p);
            }
            if (contains){
                for(Propriete p:g.getProprietes()){
                    propACon.add(p);
                }
            }
        }
        return propACon;
        
    }
   public void payer(int i){
       this.setCash(this.getCash()-i);
   }
}
