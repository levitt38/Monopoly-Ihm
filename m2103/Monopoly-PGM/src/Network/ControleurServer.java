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
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Propriete;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nourik
 */
public class ControleurServer extends Controleur{ // permet de notifier via ServerHandler
    
    public ControleurServer(){
        super();
    }
    
    public void tirerCarte(Joueur j,TypeCarte t,Client client){
        Carte c = this.getMonopoly().getCartes(t).pollFirst();
        c.setOwner(j);
        j.addCartePossedee(c);
        this.observateur.notifier(new DataModel(Evenement.CarteTiree,c,j,client));
    }
    
    
    public void gestionPrisonnier(Joueur j, Client client){
        //Il faudra gérer si le joueur possède la carte SortirdePrison
        boolean carteUsed = false;
        if (j.hasCartePrison()){
            this.observateur.notifier(new DataModel(j,Evenement.UsePossibleCarteSortiePrison,client));
        }else{
            this.restePrison(j);
        }
        
    }
    
    public void construire(Propriete p, Client client){
        if(p.getProprietaire().getCash()<p.getPrixMaison()){
            this.observateur.notifier(new DataModel(Evenement.PasAssezDArgent,client));
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
                    super.observateur.notifier(new DataModel(j, c, res, client));
                }
                // Construction de bâtiments
                this.construction(j,client);
            }
          // si le joueur en est a son 3eme double => go to prison
        } catch(joueurTripleDouble e){
            this.observateur.notifier(new DataModel(j,Evenement.AllerEnPrisonDes));
            j.setPositionCourante(this.monopoly.getPrison());}
    }
}
