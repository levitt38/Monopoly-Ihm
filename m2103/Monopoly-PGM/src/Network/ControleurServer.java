/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Data.TypeCarte;
import Exceptions.joueurTripleDouble;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauPenalite;
import Jeu.Cartes.Carte;
import Jeu.Compagnie;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Gare;
import Jeu.Joueur;
import Jeu.Observateur;
import Jeu.Propriete;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class ControleurServer extends Controleur implements Serializable{ // permet de notifier via ServerHandler
    protected ServerHandler observateur;
    
    public ControleurServer(){
        super();
    }
    
    @Override
    public void payerJoueur(Joueur j){
        j.recevoirPaie();
        this.observateur.notifier(new DataModel(j,Evenement.PasseParDepart));
        try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void setObservateur(ServerHandler obs){
        this.observateur = obs;
    }

    @Override
    public ServerHandler getObservateur() {
        return observateur;
    }
    
    public void tirerCarte(Joueur j,TypeCarte t,Client client){
        Carte c = this.getMonopoly().getCartes(t).pollFirst();
        c.setOwner(j);
        j.addCartePossedee(c);
        this.observateur.notifier(new DataModel(Evenement.CarteTiree,c,j,client));
        try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    @Override
    public Carreau lancerDesAvancer(Joueur j) throws joueurTripleDouble{
        //Lancer1
        int lancer = super.lancerD6(), position = 0;
        //Lancer2
        int lancer2 = super.lancerD6();
        //Est-ce un double ?
        if(lancer==lancer2){
            this.lancerDouble = true;
            // le joueur avait il fait un double au tour precedant ?
            if(j.isDernierDouble()){
            j.setDoublesALaSuite(j.getDoublesALaSuite()+1);
            } else {j.setDoublesALaSuite(0);}
            // le joueur en est il a son troisième double ?
            if(j.getDoublesALaSuite()>=3){
                j.setDoublesALaSuite(0);
                throw new joueurTripleDouble();
            } else { observateur.notifier(new DataModel(j,Evenement.Double));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }}
        } else { this.lancerDouble=false; }
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
        try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
        //Return carreau correspondant
        return monopoly.getCarreau(position);
    }
    
    @Override
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
        try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
    public void restePrison(Joueur j, Client client){
        int lancer1, lancer2;
        lancer1 = lancerD6();
        lancer2 = lancerD6();
        if(lancer1==lancer2){
            j.setPositionCourante(this.monopoly.getCarreau(10));
            this.observateur.notifier(new DataModel(j,Evenement.SortieDePrisonDes, client));
            try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
            // IL FAUDRA GERER LE FAIT QUE LE JOUEUR REJOUE DIRECT DANS LA MAINLOOP
            return; // sort de la méthode
        } else {this.observateur.notifier(new DataModel(j,Evenement.ResterPrison,client)); 
            try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }}
        
        j.setNb_toursEnPrison(j.getNb_toursEnPrison()+1);
        
        if(j.getNb_toursEnPrison()==3){
            j.setCash(j.getCash()-50);
            j.setPositionCourante(this.monopoly.getCarreau(10));
            this.observateur.notifier(new DataModel(j,Evenement.SortieDePrisonCaution,client));
            try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }
    
    public void gestionPrisonnier(Joueur j, Client client){
        //Il faudra gérer si le joueur possède la carte SortirdePrison
        boolean carteUsed = false;
        if (j.hasCartePrison()){
            this.observateur.notifier(new DataModel(j,Evenement.UsePossibleCarteSortiePrison,client));
        }else{
            this.restePrison(j,client);
        }
        
    }
    
    public void construire(Propriete p, Client client){
        if(p.getProprietaire().getCash()<p.getPrixMaison()){
            this.observateur.notifier(new DataModel(Evenement.PasAssezDArgent,client));
            try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }else{
            this.monopoly.construire(p);
        }
    }
    
    public void construction(Joueur j, Client client){
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
            this.observateur.notifier(new DataModel(j,pca,Evenement.Construction,client));
        }
            
    }
    
    public void jouerUnCoup(Joueur j, Client client){
        try{
            if(j.estPrisonnier()){
                gestionPrisonnier(j,client);
            }
            if(! j.estPrisonnier()){
                j.setPositionCourante(lancerDesAvancer(j));
                Carreau c = j.getPositionCourante();
                Evenement res = c.action(j);
                switch(res){
                    case PayerLoyer :j.payerLoyer((CarreauAchetable)c);
                                     break;
                    case EstEnPrison : gestionPrisonnier(j,client); break;
                    case AllerEnPrison : j.setPositionCourante(this.monopoly.getPrison());
                                         break;
                    case PayerPenalite :j.payer(((CarreauPenalite)c).getPenalite());
                                        break;
                    default : ;
                }

                // L'observateur traite en fonction du type d'évenement
                if (res != Evenement.EstEnPrison){
                    System.out.println("notif en cours"); //
                    System.out.println(res);
                    this.getObservateur().notifier(new DataModel(j, c, res, client));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Construction de bâtiments
                this.construction(j,client);
            }
          // si le joueur en est a son 3eme double => go to prison
        } catch(joueurTripleDouble e){
            this.getObservateur().notifier(new DataModel(j,Evenement.AllerEnPrisonDes));
            j.setPositionCourante(this.monopoly.getPrison());
            try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }}
    }
}
