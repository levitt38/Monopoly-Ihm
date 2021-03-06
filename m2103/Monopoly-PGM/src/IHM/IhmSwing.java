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
public class IhmSwing extends Ihm{
    private FenetreJeu fenetreJeu;
    //private FrameJeu fenetreJeu;

    
    public IhmSwing() {
        this.fenetreJeu = new FenetreJeu();
        //this.fenetreJeu = new FrameJeu();
        this.fenetreJeu.afficher();
    }
    
    @Override
    public void affiche(EventIhm e, String s, int num) {
        IhmBoiteMessage.afficherBoiteMessage(s, 0);
    }

    @Override
    public void affiche(EventIhm e, String titre, String s) {
        IhmBoiteMessage.afficherBoiteMessage(titre, s, 0);
    }

    @Override
    public int askNb(EventIhm e, String s) {
        return Integer.valueOf(IhmBoiteMessage.afficherBoiteMessage("", s));
    }

    @Override
    public String askStr(EventIhm e,String s,int num) {
        return IhmBoiteMessage.afficherBoiteMessage("", s);
    }

    @Override
    public boolean askYN(String s) {
        return IhmBoiteMessage.afficherBoiteMessage(s, 1);
    }

    @Override
    public void afficherFinTour() {
        IhmBoiteMessage.afficherBoiteMessage("FIN DU TOUR", 0);
    }

    @Override
    public void afficherCarte(Carte c) {
        this.affiche(EventIhm.affichedeBase,c.getType().toString(),c.getText());
    }

    @Override
    public void afficherJoueur(Joueur j) {
        Affichage.AfficherJoueur(j);
    }

    @Override
    public void afficherCarreau(Carreau c) {
        Affichage.AfficherCarreau(c);
    }

    @Override
    public void afficherPlateau(HashMap<String, Carreau> c) {
        this.fenetreJeu.afficherPlateau(c);
    }

    @Override
    public String askListe(ArrayList<String> choix, String message) {
        String s;boolean tmp;
        do{
            s=this.askStr(EventIhm.askdeBase, message,0);
            tmp = false;
            for(String st:choix){
                tmp = tmp || st.equalsIgnoreCase(st);
            }
        }while(!tmp);
        return s.toLowerCase();
    }
    
}
