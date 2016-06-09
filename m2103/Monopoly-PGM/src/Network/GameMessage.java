/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Jeu.Controleur;
import Jeu.Joueur;
import Jeu.Monopoly;
import java.io.Serializable;

/**
 *
 * @author nourik
 */
public class GameMessage implements Serializable {
    private ActionReseau type;
    private Monopoly monopoly;
    
    public GameMessage(ActionReseau type, Monopoly monopoly){
        this.type = type;
        this.monopoly = monopoly;
    }

    public ActionReseau getType() {
        return type;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    
}
