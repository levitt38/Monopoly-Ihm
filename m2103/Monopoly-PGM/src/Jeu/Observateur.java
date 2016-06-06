/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;

/**
 *
 * @author nourik
 */
public interface Observateur {
    // Affichage utilisateur
    public void affiche(String s);
    public void afficherFinTour();
    public void afficherJoueur(Joueur j);
    public void afficherCarreau(Carreau c);
    public void afficherPlateau(Monopoly p);
    // Demandes utilisateur
    public int askNb(String s);
    public String askStr(String s);
    public boolean askYN(String s);
    //Envoie un evenement a traiter par la classe ihm
    public void notifier(Evenement e, Carreau c, Joueur j);
    
}
