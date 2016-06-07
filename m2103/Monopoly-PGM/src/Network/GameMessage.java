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
    private ActionsGame type;
    private Monopoly monopoly;
    
    public GameMessage(ActionsGame type, Monopoly monopoly){
        this.type = type;
        this.monopoly = monopoly;
    }

    public ActionsGame getType() {
        return type;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    
}
