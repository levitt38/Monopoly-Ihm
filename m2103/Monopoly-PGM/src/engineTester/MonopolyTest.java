/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import Data.Evenement;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mouhatcl
 */
public class MonopolyTest extends Controleur{
    int[] listeCoups ;
    int indice = 0;

    public MonopolyTest(String coups) {
        super();
        String[] coup = coups.split(",");
        int[] coupsint = new int[coup.length];
        for(int i = 0; i<coup.length;i++){
            coupsint[i]=Integer.valueOf(coup[i]);
        }
        this.listeCoups = coupsint;
    }
    
    @Override
    protected int lancerD6() {
        if(indice<this.listeCoups.length){
            return listeCoups[this.indice++];
        }else{
            System.err.println("Plus de dés.");
            System.exit(0);
            return 0;
        }
    }
    
    @Override
    public void mainLoop(){
    while(this.rejouez){ // rejouez est par defaut true, car si l'user decide de quitter le programme se ferme tout seul    
        this.observateur.notifier(new DataModel(Evenement.InitialiserPartie)); //demande le nb_joueurs + noms
        // Attribution de l'ordre de jeu
        int max = 0;
        int a = 0;
        int d;
        for(int i = 0;i < this.getMonopoly().getJoueurs().size(); i++){

            do{

                d = super.lancerD6()+super.lancerD6();

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

}
