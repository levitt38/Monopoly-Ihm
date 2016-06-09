/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Data.TypeCarreau;
import IHM.Ihm;
import IHM.IhmConsole;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauInutile;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Observateur;

/**
 *
 * @author nourik
 */
public class ClientHandler implements Observateur{
    private Controleur controleur;
    private Ihm ihm = new IhmConsole();
    public Client client;
    
    public ClientHandler(Controleur controleur, Client client){
        this.controleur = controleur;
        this.controleur.setObservateur(this);
        this.client = client;
    }

    @Override
    public void notifier(DataModel d) {
        Evenement event = (d.getE()!=null) ? d.getE() : Evenement.Rien;
        Carreau c = (d.getC()!=null) ? d.getC() : new CarreauInutile(0, ""); 
        Joueur j = (d.getJ()!=null) ? d.getJ() : new Joueur("", c);
        CarreauAchetable cAchetable;
        Client client = (d.getClient()!=null) ? d.getClient() : new Client();
        DataModel message;
        switch(d.getE()){
            case AfficheClient : this.ihm.affiche(d.getS()); 
                                break;
            case DebutTour : this.ihm.afficherPlateau(d.getMonopoly().getCarreaux());
                            this.ihm.afficherJoueur(d.getMonopoly().getJoueurs().get(this.client.getPosition_joueur()-1));
                                break;
            case FinTour : this.ihm.afficherFinTour();
                                break;
            case AchatPossible : // Ou logique avec le case du dessous, car mêmes instructions => pas de break
            case UsePossibleCarteSortiePrison : message = this.client.receiveMessage();
                            String s = message.getS();
                            Boolean choix = this.ihm.askYN(s);
                            message.setBool(choix);
                            this.client.sendMessage(message);
                                break;
            case Construction : message = this.client.receiveMessage();
                            s = message.getS();
                            choix = this.ihm.askYN(s);
                            message.setBool(choix);
                            this.client.sendMessage(message);
                            if(choix==true){
                                message = this.client.receiveMessage();
                                int num_case = this.ihm.askNb(message.getS());
                                message.setI(num_case);
                                this.client.sendMessage(message);
                            }
                             break;            
            case AskString : d.setS(this.ihm.askStr(d.getS()));break;
            case AskNb : d.setI(this.ihm.askNb(d.getS()));break;
            case Affiche : this.ihm.affiche(d.getS()); break;
            default : this.ihm.affiche("Vous êtes tranquille. Pour le moment...");;              
        }
    }
    
}
