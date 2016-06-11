/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Data.EventIhm;
import Data.TypeCarreau;
import IHM.Affichage;
import IHM.Ihm;
import IHM.IhmConsole;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauInutile;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Monopoly;
import Jeu.Observateur;

/**
 *
 * @author nourik
 */
public class ClientHandler implements Observateur{
    private Ihm ihm;
    public Client client;
    
    public ClientHandler(Client client, Ihm ihm){
        this.client = client;
        this.ihm = ihm;
    }

    @Override
    public void notifier(DataModel d) {
        CarreauAchetable cAchetable;
        DataModel messagetoServer;
        String s;
        Boolean choix;
        Monopoly test; ///
        switch(d.getE()){
            case AfficheClient : this.ihm.affiche(EventIhm.affichedeBase,d.getS()); 
                                break;
            case DebutTour : this.ihm.afficherPlateau(d.getMonopoly().getCarreaux());
                            this.ihm.afficherJoueur(d.getJ());
                            test = d.getMonopoly();
                            Affichage.AfficherCarreau(test.getCarreau(0));
                                break;
            case FinTour : this.ihm.afficherFinTour();
                                break;
            case AchatPossible : System.out.println("Achat possible recu : "+d.getE());
                            s = d.getS();
                            choix = this.ihm.askYN(s);
                            messagetoServer = new DataModel(Evenement.AchatPossible, choix);
                            this.client.sendMessage(messagetoServer);
                                break;
            case UsePossibleCarteSortiePrison : 
                            s = d.getS();
                            choix = this.ihm.askYN(s);
                            messagetoServer = new DataModel(Evenement.AchatPossible, choix);
                            this.client.sendMessage(messagetoServer);
                                break;
            case Construction : 
                            s = d.getS();
                            choix = this.ihm.askYN(s);
                            messagetoServer = new DataModel(Evenement.AchatPossible, choix);
                            this.client.sendMessage(messagetoServer);
                            if(choix==true){
                                messagetoServer = this.client.receiveMessage();
                                int num_case = this.ihm.askNb(EventIhm.askdeBase,messagetoServer.getS());
                                messagetoServer.setI(num_case);
                                this.client.sendMessage(messagetoServer);
                            }
                             break;            
            case AskString : d.setS(this.ihm.askStr(EventIhm.askdeBase, d.getS()));break;
            case AskNbJoueurs : d.setI(this.ihm.askNb(EventIhm.askNb_joueur,d.getS()));break;
            case AskNb : d.setI(this.ihm.askNb(EventIhm.askdeBase,d.getS()));break;
            case Affiche : this.ihm.affiche(EventIhm.affichedeBase,d.getS()); break;
            case Rien : this.ihm.affiche(EventIhm.affichedeBase,"Event suspect");
            default : this.ihm.affiche(EventIhm.affichedeBase,"Vous êtes tranquille. Pour le moment...");;              
        }
    }
    
}
