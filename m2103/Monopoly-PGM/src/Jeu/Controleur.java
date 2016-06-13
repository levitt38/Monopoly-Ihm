/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;
import Data.TypeCarte;
import Exceptions.joueurTripleDouble;
import Exceptions.pasAssezDeMaisonsException;
import IHM.TextColors;
import Jeu.Cartes.Carte;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mouhatcl
 */
public class Controleur implements Serializable{
    protected Monopoly monopoly;
    protected boolean partieContinue = true;
    protected boolean lancerDouble = false;
    protected boolean rejouez = true;
    protected Observateur observateur;
    
    public Controleur() {
        this.monopoly = new Monopoly();
    }
    
    public void setObservateur(Observateur obs){
        this.observateur = obs;
    }

    public Observateur getObservateur() {
        return observateur;
    }
    
    public void payerJoueur(Joueur j){
        j.recevoirPaie();
        this.observateur.notifier(new DataModel(j,Evenement.PasseParDepart));
    }
    
    protected int lancerD6(){
        return (int)(Math.random()*100%6)+1;
    }
    
    public void tirerCarte(Joueur j,TypeCarte t){
        Carte c = this.getMonopoly().getCartes(t).pollFirst();
        c.setOwner(j);
        j.addCartePossedee(c);
        this.observateur.notifier(new DataModel(Evenement.CarteTiree,c,j));
    }
    
    public void useCarte(Carte c){
        Evenement ev = c.use(this.monopoly);
        switch(ev){
            case PasseParDepart: this.payerJoueur(c.getOwner());break;
            case VerifJoueurs : for(Joueur j:this.monopoly.getJoueurs()){
                if(j.estBankrupt()){
                    this.observateur.notifier(new DataModel(j, Evenement.Bankrupt));
                }
            }
            case Bankrupt : this.observateur.notifier(new DataModel(c.getOwner(), Evenement.Bankrupt));
        }
        c.resetOwner();
        this.monopoly.getCartes(c.getType()).addLast(c);
    }
    
    public Carreau lancerDesAvancer(Joueur j) throws joueurTripleDouble{
        //Lancer1
        int lancer = lancerD6(), position = 0;
        //Lancer2
        int lancer2 = lancerD6();
        //Est-ce un double ?
        if(lancer==lancer2){
            this.lancerDouble = true;
            // le joueur avait il fait un double au tour precedant ?
            if(j.isDernierDouble()){
            j.setDoublesALaSuite(j.getDoublesALaSuite()+1);
            } else {j.setDoublesALaSuite(1);}
            // le joueur en est il a son troisième double ?
            if(j.getDoublesALaSuite()>=3){
                j.setDoublesALaSuite(0);
                throw new joueurTripleDouble();
            } else { observateur.notifier(new DataModel(j,Evenement.Double));}
        } else { this.lancerDouble=false;
            j.setDoublesALaSuite(0);
        }
        lancer += lancer2;
        //Cette ligne sert a récupérer le montant des dès du lancer pour réaliser le loyer d'une compagnie
        for (Compagnie c : this.getMonopoly().getCompagnies()){
            c.setDernierLancer(lancer);
        }
        //Recup position du joueur
        position = j.getPositionCourante().getNumero()+lancer;
        //Est-ce un jour de paye ?
        if(position>40){
            payerJoueur(j);
        }
        position = position%40;
        //Affichage IHM des dès
        observateur.notifier(new DataModel(lancer,j,Evenement.LancersDes));
        //Return carreau correspondant
        return monopoly.getCarreau(position);
    }
    
    public void joueurDead(Joueur j){
        
        for (Propriete p:j.getProprietes()){
            this.monopoly.setNbMaisons(this.monopoly.getNbMaisons()+p.resetMaisons());
            this.monopoly.setNbHotels(this.monopoly.getNbHotels()+(p.resetHotel() ? 1 : 0));
            p.resetProprietaire();
            j.removeCarreauAchetable(p);
        }
        for (Gare g:j.getGares()){
            g.resetProprietaire();
            j.removeCarreauAchetable(g);
        }
        for (Compagnie c:j.getCompagnies()){
            c.resetProprietaire();
            j.removeCarreauAchetable(c);
        }
        this.observateur.notifier(new DataModel(j,Evenement.Bankrupt));
    }
    
    public boolean partieEstFinie(){
        int nbAlive = 0;
        for(Joueur j:this.monopoly.getJoueurs()){
            if (!j.estBankrupt()){
                nbAlive+=1;
            }
        }
        return nbAlive<=1;/*
        if(this.monopoly.getJoueurs().size()<2){
            this.partieContinue = false;
            
            throw new partieFinieException();
        }*/
    }
    
    public void restePrison(Joueur j){
        int lancer1, lancer2;
        lancer1 = lancerD6();
        lancer2 = lancerD6();
        if(lancer1==lancer2){
            j.setPositionCourante(this.monopoly.getCarreau(10));
            this.observateur.notifier(new DataModel(j,Evenement.SortieDePrisonDes));
            // IL FAUDRA GERER LE FAIT QUE LE JOUEUR REJOUE DIRECT DANS LA MAINLOOP
            return; // sort de la méthode
        } else {this.observateur.notifier(new DataModel(j,Evenement.ResterPrison)); }
        
        j.setNb_toursEnPrison(j.getNb_toursEnPrison()+1);
        
        if(j.getNb_toursEnPrison()==3){
            j.setCash(j.getCash()-50);
            j.setPositionCourante(this.monopoly.getCarreau(10));
            this.observateur.notifier(new DataModel(j,Evenement.SortieDePrisonCaution));
        }
    }
    
    public void gestionPrisonnier(Joueur j){
        //Il faudra gérer si le joueur possède la carte SortirdePrison
        boolean carteUsed = false;
        if (j.hasCartePrison()){
            this.observateur.notifier(new DataModel(j,Evenement.UsePossibleCarteSortiePrison));
        }else{
            this.restePrison(j);
        }
        
    }
    
    public void construire(Propriete p){
        if(p.getProprietaire().getCash()<p.getPrixMaison()){
            this.observateur.notifier(new DataModel(Evenement.PasAssezDArgent));
        }else{
            this.monopoly.construire(p);
        }
    }
    
    public void jouerUnCoup(Joueur j){
        try{
            if(j.estPrisonnier()){
                gestionPrisonnier(j);
            }
            if(! j.estPrisonnier()){
                j.setPositionCourante(lancerDesAvancer(j));
                j.setRejouerCarte(false);
                do{
                    Carreau c = j.getPositionCourante();
                    Evenement res = c.action(j);
                    switch(res){
                        case PayerLoyer :j.payerLoyer((CarreauAchetable)c);
                                         break;
                        case EstEnPrison : gestionPrisonnier(j); break;
                        case AllerEnPrison : j.setPositionCourante(this.monopoly.getPrison());
                                             break;
                        case PayerPenalite :j.payer(((CarreauPenalite)c).getPenalite());
                                            break;
                        default : ;
                    }

                    // L'observateur traite en fonction du type d'évenement
                    if (res != Evenement.EstEnPrison){
                        observateur.notifier(new DataModel(j, c, res));
                    }
                }while(j.isRejouerCarte()&&j.getPositionCourante().getNumero()!=40);
                // Construction de bâtiments
                this.construction(j);
            }
          // si le joueur en est a son 3eme double => go to prison
        } catch(joueurTripleDouble e){
            this.observateur.notifier(new DataModel(Evenement.AllerEnPrisonDes));
            j.setPositionCourante(this.monopoly.getPrison());}
    }

    
    public void construction(Joueur j){
        ArrayList<Propriete> pc = j.getProprietesConstructibles();
        if(this.monopoly.getNbMaisons()==0){
            for (Propriete p:pc){
                if(p.getNbMaisons()<4){
                    pc.remove(p);
                }
            }

        }   
        if(this.monopoly.getNbHotels()==0){
            for (Propriete p:pc){
                if(p.getNbMaisons()==4){
                    pc.remove(p);
                }
            }
        }
        HashMap<String, Carreau> pca = new HashMap<>();
        for(Propriete p:pc){
            pca.put(p.getNomCarreau(), p);
        }
    // On gère les constructions éventuelles si le joueur possède tous les carreaux d'un groupe
        if(j.getProprietesConstructibles().size()>0){
            this.observateur.notifier(new DataModel(j,pca,Evenement.Construction));
        }
            
    }
    
    public void ajouterJoueur(String s){
        this.monopoly.addJoueur(new Joueur(s,this.monopoly.getCarreau(0),this.monopoly.getJoueurs().size()+1));
    }
    
    public void mainLoop(){
    while(this.rejouez){ // rejouez est par defaut true, car si l'user decide de quitter le programme se ferme tout seul    
        this.observateur.notifier(new DataModel(Evenement.InitialiserPartie)); //demande le nb_joueurs + noms
        // Attribution de l'ordre de jeu
        int max = 0;
        int a = 0;
        int d;
        for(int i = 0;i < this.getMonopoly().getJoueurs().size(); i++){

            do{

                d = lancerD6()+lancerD6();

            }while(d == max);
            if(max<d){

                max = d;
                a = i;
            }


        }
        
        int tour = a;
        while(!this.partieEstFinie()){
            Joueur j=this.getMonopoly().getJoueurs().get(tour);
            if(!j.estBankrupt()){
                this.observateur.notifier(new DataModel(j,this.monopoly.getCarreaux(),Evenement.DebutTour));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.jouerUnCoup(j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(j.estBankrupt()){
                    joueurDead(j); 
                }
                observateur.notifier(new DataModel(Evenement.FinTour));
            }
            if((!this.lancerDouble||j.estPrisonnier())&&(!j.estBankrupt())){
                tour=(tour+1)%this.monopoly.getJoueurs().size();
            }
        }
        for (Joueur j:this.getMonopoly().getJoueurs()){
            if(!j.estBankrupt()){
                this.observateur.notifier(new DataModel(j,Evenement.PartieTerminee));
            }
        }
    }
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void joueurAchete(Carreau c, Joueur j) {
        ((CarreauAchetable) c).acheter(j);
    }

    public boolean isLancerDouble() {
        return lancerDouble;
    }
    
   
}
