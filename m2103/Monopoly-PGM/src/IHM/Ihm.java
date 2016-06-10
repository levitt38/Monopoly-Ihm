/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.EventIhm;
import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Joueur;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Louis
 */
public abstract class Ihm {
    
    
    public abstract void affiche(String s);
    
    public abstract void affiche(String titre, String s);
    
    public abstract int askNb(EventIhm e, String s);
    
    public abstract String askStr(EventIhm e,String s);
    
    public abstract boolean askYN(String s);
    
    public abstract void afficherFinTour();
        
    public abstract void afficherCarte(Carte c);
    
    public abstract void afficherJoueur(Joueur j);
    
    public abstract void afficherCarreau(Carreau c);
    public abstract void afficherPlateau(HashMap<String,Carreau> c);
    public abstract String askListe(ArrayList<String> choix,String message);
    
    
    
    
    
    
    
}
