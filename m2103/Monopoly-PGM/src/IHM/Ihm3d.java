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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author nourik
 */
public class Ihm3d extends Ihm{
    private FrameAcceuil frame_accueil;
    private FrameJeu frame_jeu;
    private Client client; // dans le cas du mode online, un object client est crée
    
    public Ihm3d(){
        this.frame_accueil = frame_accueil;
        this.frame_jeu = frame_jeu;
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
    
    public void affiche(EventIhm e,String s){
        this.frame_jeu.getTextAction().setText(s);
        // Animation droite => gauche dans version finale
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    public void affiche(EventIhm e, String titre, String s){
        this.affiche(e, s);
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
        Boolean choix;
        this.frame_jeu.getTextAction().setText("Fin du Tour");
        // Animation droite => gauche dans version finale
        while(this.frame_jeu.isYesNoSaisi()==false){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        choix = this.frame_jeu.isYesNoChoix();
        this.frame_jeu.setYesNoSaisi(false);
        return choix;
    };
    
    public void afficherFinTour(){
        this.affiche(EventIhm.affichedeBase, "Fin de ce Tour");
    };
        
    public void afficherCarte(Carte c){
        this.affiche(EventIhm.affichedeBase, c.getText());
    };
    
    public void afficherJoueur(Joueur j){
        this.frame_jeu.getTextJoueur().setText(j.affiche3d());
        // Animation gauche => droite dans version finale
    };
    
    public void afficherCarreau(Carreau c){
        this.frame_jeu.getTextCarreau().setText(c.affiche3d());
        // Animation gauche => droite dans version finale
    };
    public void afficherPlateau(HashMap<String,Carreau> c){
        
    };
    public String askListe(ArrayList<String> choix,String message){
        return null;
    };
    
    
}
