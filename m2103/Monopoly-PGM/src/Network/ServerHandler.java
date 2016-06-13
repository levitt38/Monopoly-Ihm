/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Data.EventIhm;
import Data.TypeCarte;
import IHM.Ihm;
import IHM.IhmConsole;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauCarte;
import Jeu.CarreauInutile;
import Jeu.CarreauPenalite;
import Jeu.Cartes.CarteSortiePrison;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Observateur;
import Jeu.Propriete;

/**
 *
 * @author nourik
 */
public class ServerHandler implements Observateur{
    private ControleurServer controleur;
    private Ihm ihm;
    public Server server;
    
    public ServerHandler(ControleurServer controleur, Server server){
        this.controleur = controleur;
        this.ihm = new IhmConsole(controleur);
        this.controleur.setObservateur(this);
        this.server = server;
    }

    @Override
    public void notifier(DataModel d) {
        Evenement event = (d.getE()!=null) ? d.getE() : Evenement.Rien;
        Carreau c = (d.getC()!=null) ? d.getC() : new CarreauInutile(0, ""); 
        Joueur j = (d.getJ()!=null) ? d.getJ() : new Joueur("", c);
        CarreauAchetable cAchetable;
        Client client = d.getClient();
        DataModel message;
        switch(d.getE()){
            case PayerLoyer : cAchetable = (CarreauAchetable) c;
                                message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+" paye un loyer de "+cAchetable.getPrixAchat()+"€ a "+cAchetable.getProprietaire().getNomJoueur());
                                this.server.sendMessagetoAll(message);
                             break;
            case SurSaCase : message = new DataModel(Evenement.AfficheClient, "Vous êtes sur une de vos propriété, détendez vous");
                                this.server.sendMessage(client, message);
                              break;
            case AchatPossible : cAchetable = (CarreauAchetable) c;
                             message = new DataModel(Evenement.AchatPossible, "Voulez-vous acheter "+cAchetable.getNomCarreau()+" pour "+cAchetable.getPrixAchat()+"€ ?");
                             this.server.sendMessage(client, message);
                             message = this.server.receiveMessage(client);
                             if(message.getBool()==true){
                              this.controleur.joueurAchete(c,j);
                             } break;
            case AchatImpossible : message = new DataModel(Evenement.AfficheClient, "Vous n'avez pas le budget pour acheter ce bien");
                                this.server.sendMessage(client, message);
                              break;
            // Events concernant cases autres
            case AllerEnPrison : message = new DataModel(Evenement.AfficheClient, "Joueur "+j.getNomJoueur()+" envoyé en prison!");
                                this.server.sendMessagetoAll(message);
                                break;                   
            case PayerPenalite : message = new DataModel(Evenement.AfficheClient, "Le joueur "+j.getNomJoueur()+" paie "+((CarreauPenalite)c).getPenalite()+"$");
                                this.server.sendMessagetoAll(message);
                                break;                     
            case SortieDePrisonDes : message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+"fait un double, fin de votre séjour en prison ! Il peut jouer");
                                this.server.sendMessagetoAll(message);
                                break;
            case SortieDePrisonCaution : message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+" finit ses 3 tours en prison ! il doit payer 50€");
                                this.server.sendMessagetoAll(message);
                              break;
            case SortieDePrisonCarte : message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+"utilise ses relations au gouvernement pour sortir de prison ...");
                                this.server.sendMessagetoAll(message);
                                break;
            case ResterPrison : message = new DataModel(Evenement.AfficheClient, "Vous n'avez pas fait de double, vous restez en prison !");
                                this.server.sendMessage(client, message);
                              break;
            case Bankrupt : message = new DataModel(Evenement.AfficheClient, "Le joueur "+j.getNomJoueur()+" vient d'être éliminé");
                                this.server.sendMessagetoAll(message);
                                break;
            case PasseParDepart : message = new DataModel(Evenement.AfficheClient, "Joueur "+j.getNomJoueur()+" recoit sa paie : +200€");
                                this.server.sendMessagetoAll(message);
                                break;
            case PartieTerminee : message = new DataModel(Evenement.AfficheClient, "Partie Terminée !! Le joueur "+j.getNomJoueur()+" l'emporte");
                                this.server.sendMessagetoAll(message);
                                break;  
            case TirerCarte :   this.controleur.tirerCarte(j,((CarreauCarte)c).getTypeCarte(),client);
                                message = new DataModel(Evenement.AfficheClient, "Vous avez tirez une carte "+((CarreauCarte)c).getTypeCarte());
                                this.server.sendMessage(client, message);
                                break;
            case PasAssezDArgent : message = new DataModel(Evenement.AfficheClient, "Vous n'avez pas assez d'argent pour effectuer cette action.");
                                this.server.sendMessage(client, message);
                                break;
            case PasNivele : message = new DataModel(Evenement.AfficheClient, "Vous devez d'abord construire sur les autres terrains de ce groupe.");
                                this.server.sendMessage(client, message);
                                break;
            case PlusDeMaisons : message = new DataModel(Evenement.AfficheClient, "Il n'y a plus de maisons disponibles.");
                                this.server.sendMessage(client, message);
                                break;
            case TropDeMaisons : message = new DataModel(Evenement.AfficheClient, "Il y a déjà le nombre maximal de maisons sur ce terrain.");
                                this.server.sendMessage(client, message);
                                break;            
            /*case InitialiserPartie : int nb = this.askNb("Entrez le nombre de joueurs",2,6);
                                    for (int i = 0;i<nb;i++){
                                        this.controleur.ajouterJoueur(this.ihm.askStr("Entre le nom du joueur"+i));
                                    }break;*/
            case CarteTiree : boolean prison = d.getCarte().getClass().equals(CarteSortiePrison.class);
                              message = new DataModel(Evenement.AfficheClient, "Vous avez tiré une carte "+d.getCarte().getText());
                              this.server.sendMessage(client, message);
                              if(!prison){
                                  this.controleur.useCarte(d.getCarte());
                              }break;
            case AllerEnPrisonDes : message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+"a fait son 3ème double !/nDirection, la prison !");
                                this.server.sendMessagetoAll(message);
                                break;
            case UsePossibleCarteSortiePrison : message = new DataModel(Evenement.UsePossibleCarteSortiePrison, "Voulez-vous utiliser une carte Sortie de Prison ?");
                                this.server.sendMessage(client, message);
                                message = this.server.receiveMessage(client);
                                if(message.getBool()==true){
                                    this.controleur.useCarte(j.getCartePrison());
                                    message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+" utilise se carte pour sortir de prison");
                                    this.server.sendMessagetoAll(message);
                                }else{
                                    this.controleur.restePrison(j);
                                }break;
            case Construction : message = new DataModel(Evenement.Construction, "Voulez-vous construire ?");
                                this.server.sendMessage(client, message);
                                message = this.server.receiveMessage(client);
                                if(message.getBool()==true){
                                    message = new DataModel(Evenement.ConstructionServeur, "Quelle case voulez-vous construire ?");
                                    this.server.sendMessage(client, message);
                                    message = this.server.receiveMessage(client);
                                    this.controleur.construire((Propriete)this.controleur.getMonopoly().getCarreau(message.getI()));
                                    this.controleur.construction(j);
                                }break;
            case Double : message = new DataModel(Evenement.AfficheClient, j.getNomJoueur()+" a fait un double !");
                                this.server.sendMessagetoAll(message);
                                break;
            case LancersDes : message = new DataModel(Evenement.AfficheClient, "Résultat lancer : "+j.getNomJoueur()+" a fait un "+d.getI());
                                this.server.sendMessagetoAll(message);
                                break;
            // Affichage sur l'interface serveur (pas use, car toujours en mode console) 
            case DebutTour : DataModel messageTour = new DataModel(d.getJ(), d.getMonopoly(),Evenement.DebutTour);
                                this.server.sendMessagetoAll(messageTour);
                            break;                    
            case FinTour : message = new DataModel(Evenement.FinTour);
                                 this.server.sendMessagetoAll(message);
                                break;                     
            case AskString : d.setS(this.ihm.askStr(EventIhm.askdeBase, d.getS(),0));break;
            case AskNb : d.setI(this.ihm.askNb(EventIhm.askdeBase,d.getS()));break;
            case Affiche : this.ihm.affiche(EventIhm.affichedeBase,d.getS(),0); break;
            default : this.ihm.affiche(EventIhm.affichedeBase,"Vous êtes tranquille. Pour le moment...",0);;
        }
        }
    
    public void tirerCarte(TypeCarte t){
        this.ihm.affiche(EventIhm.affichedeBase,"Tirer une Carte","Vous tirez une carte "+t.toString()+".");
    }

    public void setControleur(ControleurServer controleur) {
        this.controleur = controleur;
    }
    
    
}

