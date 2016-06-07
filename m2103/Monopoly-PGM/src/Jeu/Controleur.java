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
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author mouhatcl
 */
public class Controleur {
    protected Monopoly monopoly;
    protected boolean partieContinue = true;
    protected boolean lancerDouble = false;
    private Observateur observateur;
    
    public Controleur() {
        this.monopoly = new Monopoly();
    }
    
    public void setObservateur(Observateur obs){
        this.observateur = obs;
    }

    public void payerJoueur(Joueur j){
        j.recevoirPaie();
        this.observateur.notifier(new DataModel(j,Evenement.PasseParDepart));
    }
    
    private int lancerD6(){
        return (int)(Math.random()*100%6)+1;
    }
    
    public void tirerCarte(Joueur j,TypeCarte t){
        Carte c = this.getMonopoly().getCartes(t).pollFirst();
        c.setOwner(j);
        j.addCartePossedee(c);
        this.observateur.notifier(new DataModel(Evenement.CarteTiree,c,j));
    }
    
    public void useCarte(Carte c){
        c.use(this.monopoly);
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
            } else {j.setDoublesALaSuite(0);}
            // le joueur en est il a son troisième double ?
            if(j.getDoublesALaSuite()>=3){
                j.setDoublesALaSuite(0);
                this.observateur.notifier(new DataModel(Evenement.AllerEnPrisonDes));
                throw new joueurTripleDouble();
            } else { observateur.affiche(TextColors.BLUE+"Vous avez fait un double !"+TextColors.RESET); }
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
        observateur.affiche(TextColors.BLUE+"Résultat lancer : "+j.getNomJoueur()+" a fait un "+lancer+TextColors.RESET);
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
            this.monopoly.getPrison().libérerDétenu(j);
            j.setPositionCourante(this.monopoly.getCarreau(10+lancer1+lancer2));
            this.observateur.notifier(new DataModel(j,Evenement.SortieDePrisonDes));
            // IL FAUDRA GERER LE FAIT QUE LE JOUEUR REJOUE DIRECT DANS LA MAINLOOP
            return; // sort de la méthode
        } else {this.observateur.notifier(new DataModel(j,Evenement.ResterPrison)); }
        
        j.setNb_toursEnPrison(j.getNb_toursEnPrison()+1);
        
        if(j.getNb_toursEnPrison()==3){
            j.setCash(j.getCash()-50);
            this.monopoly.getPrison().libérerDétenu(j);
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
    
    public void jouerUnCoup(Joueur j){
        try{
            if(! j.estPrisonnier()){
                j.setPositionCourante(lancerDesAvancer(j));
            }
            Carreau c = j.getPositionCourante();
            Evenement res = c.action(j);
            switch(res){
                case PayerLoyer :j.payerLoyer((CarreauAchetable)c);
                                 break;
                case EstEnPrison : gestionPrisonnier(j); break;
                case AllerEnPrison : getMonopoly().getPrison().emprisonnerDétenu(j);
                                     break;
                case PayerPenalite :j.payer(((CarreauPenalite)c).getPenalite());
                                    break;
                default : ;
            }
            
            // L'observateur traite en fonction du type d'évenement
            if (res != Evenement.EstEnPrison){
                observateur.notifier(new DataModel(j, c, res));
            }
            // Construction de bâtiments
            boolean construire = false;
            HashMap<String, Carreau> pc = new HashMap<>();
            for(Propriete p:j.getProprietesConstructibles()){
                pc.put(p.getNomCarreau(), p);
            }
            if(j.getProprietesConstructibles().size()>0){
                this.observateur.notifier(new DataModel(pc,Evenement.Construction));
            }
            // On gère les constructions éventuelles si le joueur possède tous les carreaux d'un groupe
            
            
            /*while(construire&&j.getProprietesConstructibles().size()>0){
                String s;
                do{
                    s=observateur.askStr("Entrez le nom d'une rue");
                }while(!this.monopoly.getCarreaux().containsKey(s) || !(this.monopoly.getCarreaux().get(s).getType()==TypeCarreau.ProprieteAConstruire));
                Propriete p = (Propriete) this.monopoly.getCarreaux().get(s);
                if(p.getNbMaisons()<5){
                    if(p.getPrixMaison()<=j.getCash()){
                        if(p.getNbMaisons()<=p.getGroupe().getMinMaisons()){
                            try {
                                this.monopoly.construire(p);
                            } catch (pasAssezDeMaisonsException ex) {
                                this.observateur.notifier(new DataModel(Evenement.PlusDeMaisons));
                            }
                        }else{
                            this.observateur.notifier(new DataModel(Evenement.PasNivele));
                        }
                    }else{
                        this.observateur.notifier(new DataModel(Evenement.PasAssezDArgent));
                    }
                }else{
                    this.observateur.notifier(new DataModel(Evenement.TropDeMaisons));
                }
            }*/
            
            
          // si le joueur en est a son 3eme double => go to prison
        } catch(joueurTripleDouble e){this.monopoly.getPrison().emprisonnerDétenu(j);};
    }

    
    public void ajouterJoueur(String s){
        this.monopoly.addJoueur(new Joueur(s,this.monopoly.getCarreau(0)));
    }
    
    public void mainLoop(){
        this.observateur.notifier(new DataModel(Evenement.InitialiserPartie));
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
                this.jouerUnCoup(j);
                if(j.estBankrupt()){
                    joueurDead(j); 
                }
                observateur.notifier(new DataModel(Evenement.FinTour));
            }
            if((!this.lancerDouble)&&(!j.estBankrupt())){
                tour=(tour+1)%this.monopoly.getJoueurs().size();
            }
        }
        for (Joueur j:this.getMonopoly().getJoueurs()){
            if(!j.estBankrupt()){
                this.observateur.notifier(new DataModel(j,Evenement.PartieTerminee));
            }
        }
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void joueurAchete(Carreau c, Joueur j) {
        ((CarreauAchetable) c).acheter(j);
    }
    
    
   
}
