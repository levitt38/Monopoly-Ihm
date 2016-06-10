/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.EventIhm;
import Data.TypePions;
import FrameAcceuil.FrameAcceuil;
import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Joueur;
import Network.Client;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextField;

/**
 *
 * @author nourik
 */
public class Ihm3d extends Ihm{
    private FrameAcceuil frame_accueil;
    // le display 3d
    private Client client; // dans le cas du mode online, un object client est crée
    
    public Ihm3d(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame_accueil = new FrameAcceuil();
                frame_accueil.setVisible(true);
            }
        });
        // instancier le display ?
    }
    
    public Ihm3d(FrameAcceuil frame){
            this.frame_accueil = frame;
        // instancier le display ?
    }
    
    public TypePions askTypePion(int num_joueur){ // methode propre a ihm3d
        String choix = ""; TypePions type = TypePions.Rien;
        for(JTextField tf : this.frame_accueil.getListe_choixPions()){
                                         if(Integer.valueOf(tf.getText().trim())==num_joueur){
                                             choix = tf.getName().trim(); break;
                                         }
        } switch(choix){
            case "PersoPortal" : type =  TypePions.Portal; break;
            case "PersoBanane" : type = TypePions.Banane; break;
            case "PersoHorloge" : type =  TypePions.Horloge; break;
            case "PersoHamburger" : type =  TypePions.Hamburger; break;
            case "PersoCanette" : type =  TypePions.Canette; break;
            case "PersoTelephone" : type = TypePions.Telephone; break;
          }
        return type;
    }
    
    public void affiche(String s){
        
    };
    
    public void affiche(String titre, String s){
        
    };
    
    public int askNb(EventIhm e, String s){
        int reponse = 0;
        switch(e){
            case askNb_joueur : reponse = Integer.valueOf(this.frame_accueil.getTFnb_joueur().getText().trim());
                                break;
            case askPort : reponse = Integer.valueOf(this.frame_accueil.getTFport().getText().trim());
                            break;                    
        }
        return reponse;
    };
    
    public String askStr(EventIhm e, String s){
        String reponse = "";
        switch(e){
            case askdeBase : // pas d'interet pour l'instant 
                break;
            case askIp : reponse =  this.frame_accueil.getTFip().getText();
                break;  
            case askNom : reponse = this.frame_accueil.getTFnom().getText();
                break;
        }
        return reponse;
    }
    
    public boolean askYN(String s){
        return true;
    };
    
    public void afficherFinTour(){
        
    };
        
    public void afficherCarte(Carte c){
        
    };
    
    public void afficherJoueur(Joueur j){
        
    };
    
    public void afficherCarreau(Carreau c){
        
    };
    public void afficherPlateau(HashMap<String,Carreau> c){
        
    };
    public String askListe(ArrayList<String> choix,String message){
        return null;
    };
    
    
}
