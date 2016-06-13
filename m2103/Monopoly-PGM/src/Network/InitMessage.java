/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.Serializable;

/**
 *
 * @author nourik
 */
public final class InitMessage implements Serializable{
    private String nom_joueur;
    private int nb_joueur;
    private String type;   // connexion/nb_joueur/rien
    private static final long serialVersionUID = 7526471155622776147L;
    
    //demande serveur
    public InitMessage(){
        
    }
    
    //réponse client
    public InitMessage(String type, String nom_joueur, int nb_joueur){
        this.nom_joueur = nom_joueur;
        this.nb_joueur = nb_joueur;
        this.type = type;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public int getNb_joueur() {
        return nb_joueur;
    }

    public String getType() {
        return type;
    }
    
    
    
}
