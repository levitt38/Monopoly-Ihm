/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Joueur;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Louis
 */
public class IhmConsole extends Ihm{

    @Override
    public void affiche(String s) {
        Questions.affiche(s);
    }

    @Override
    public void affiche(String titre, String s) {
        this.affiche(s);//hum
    }

    @Override
    public int askNb(String s) {
        return Questions.askNb(s);
    }

    @Override
    public String askStr(String s) {
        return Questions.askStr(s);
    }

    @Override
    public boolean askYN(String s) {
        return Questions.askYN(s);
    }

    @Override
    public void afficherFinTour() {
        Affichage.afficherFinTour();
    }

    @Override
    public void afficherCarte(Carte c) {
        Questions.affiche(c.getType().toString());
        Questions.increment();
        for(String s:c.getText().split("\n")){
            Questions.affiche(s);
        }
        Questions.decrement();
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
        Affichage.afficherPlateau(c);
    }

    public IhmConsole() {
    }

    @Override
    public String askListe(ArrayList<String> choix, String message) {
        String s;boolean tmp;
        do{
            s=this.askStr(message);
            tmp = false;
            for(String st:choix){
                tmp = tmp || st.equalsIgnoreCase(st);
            }
        }while(!tmp);
        return s.toLowerCase();
    }
    
}
