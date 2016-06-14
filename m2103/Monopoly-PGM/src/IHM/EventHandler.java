/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.Evenement;
import Data.EventIhm;
import Data.TypeCarte;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauCarte;
import Jeu.CarreauPenalite;
import Jeu.Cartes.Carte;
import Jeu.Cartes.CarteSortiePrison;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Observateur;
import Jeu.Propriete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class EventHandler implements Observateur{
    private Controleur controleur;
    private Ihm ihm; // ligne à changer pour changer d'IHM
    
    public EventHandler(Controleur controleur, Ihm ihm){
        this.controleur = controleur;
        this.controleur.setObservateur(this);
        this.ihm = ihm;
    }
    
    public void affiche(String s){
        IhmBoiteMessage.afficherBoiteMessage(s, 0);
    }
    
    @Override
    public void notifier(DataModel d){
        Evenement event = d.getE();
        Carreau c = d.getC();
        Joueur j = d.getJ();
        CarreauAchetable cAchetable;
        switch(event){
            // Event concernant cases achetables
            case PayerLoyer : cAchetable = (CarreauAchetable) c;
                                this.ihm.affiche(EventIhm.affichedeBase,j.getNomJoueur()+"paye un loyer de "+cAchetable.calculLoyer()+"€ a"+cAchetable.getProprietaire().getNomJoueur(),0);
                             break;
            case SurSaCase : this.ihm.affiche(EventIhm.affichedeBase,"Vous êtes sur une de vos propriété, détendez vous",0); break;
            case AchatPossible : cAchetable = (CarreauAchetable) c;
                            String choix = "non";                                    
                          if(this.ihm.askYN("Voulez-vous acheter "+cAchetable.getNomCarreau()+" pour "+cAchetable.getPrixAchat()+"€ ?")){
                              this.controleur.joueurAchete(c,j);
                          } break;
            case AchatImpossible : this.ihm.affiche(EventIhm.affichedeBase,"Vous n'avez pas le budget pour acheter ce bien",0); break;
            // Events concernant cases autres
            case AllerEnPrison : this.ihm.affiche(EventIhm.affichedeBase,"Joueur "+j.getNomJoueur()+" envoyé en prison!",0);
                                 break;
            case PayerPenalite :this.ihm.affiche(EventIhm.affichedeBase,"Le joueur "+j.getNomJoueur()+" paie "+((CarreauPenalite)c).getPenalite()+"$",0);
                                break;
            case SortieDePrisonDes : this.ihm.affiche(EventIhm.affichedeBase,"Vous avez fait un double, fin de votre séjour en prison ! Vous pouvez jouer",0);break;
            case SortieDePrisonCaution : this.ihm.affiche(EventIhm.affichedeBase,"Fin de vos 3 tours en prison ! vous payez 50€",0);break;
            case SortieDePrisonCarte : this.ihm.affiche(EventIhm.affichedeBase,"Vous utilisez vos relations au gouvernement pour sortir de prison ...",0);break;
            case ResterPrison : this.ihm.affiche(EventIhm.affichedeBase,"Vous n'avez pas fait de double, vous restez en prison !",0);break;
            case Bankrupt : this.ihm.affiche(EventIhm.affichedeBase,"Le joueur "+j.getNomJoueur()+" vient d'être éliminé",0);break;
            case PasseParDepart : this.ihm.affiche(EventIhm.affichedeBase,"Joueur "+j.getNomJoueur()+" recoit sa paie : +200€",0);break;
            case PartieTerminee : this.ihm.affiche(EventIhm.affichedeBase,"Partie Terminée !! Le joueur "+j.getNomJoueur()+" l'emporte",0);
                                 if(this.ihm.askYN("Voulez vous rejouer ?")==false){
                                     System.exit(0);
                                 }   
                                break;
            case FinTour : this.ihm.afficherFinTour();break;
            case TirerCarte : this.tirerCarte(((CarreauCarte)c).getTypeCarte());
                                try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                this.controleur.tirerCarte(j,((CarreauCarte)c).getTypeCarte());break;
            case PasAssezDArgent : this.ihm.affiche(EventIhm.affichedeBase,"Vous n'avez pas assez d'argent pour effectuer cette action.",0);break;
            case PasNivele : this.ihm.affiche(EventIhm.affichedeBase,"Vous devez d'abord construire sur les autres terrains de ce groupe.",0);break;
            case PlusDeMaisons : this.ihm.affiche(EventIhm.affichedeBase,"Il n'y a plus de maisons disponibles.",0);break;
            case TropDeMaisons : this.ihm.affiche(EventIhm.affichedeBase,"Il y a déjà le nombre maximal de maisons sur ce terrain.",0);break;
            case DebutTour : this.ihm.afficherJoueur(j);
                            this.ihm.afficherPlateau(d.getCarreaux());
    
                            break;
            case InitialiserPartie : int nb = this.ihm.askNb(EventIhm.askNb_joueur, "Entrez le nombre de joueurs");
                                    for (int i = 0;i<nb;i++){
                                        this.controleur.ajouterJoueur(this.ihm.askStr(EventIhm.askNom, "Entre le nom du joueur",i+1));
                                    }break;
            case CarteTiree : boolean prison = d.getCarte().getClass().equals(CarteSortiePrison.class);
                              this.ihm.afficherCarte(d.getCarte());
                              if(!prison){
                                  this.controleur.useCarte(d.getCarte());
                              }break;
            case AllerEnPrisonDes : this.ihm.affiche(EventIhm.affichedeBase,"","C'est votre 3ème double !\nDirection, la prison !");break;
            case UsePossibleCarteSortiePrison : if(this.ihm.askYN("Voulez-vous utiliser une carte Sortie de Prison ?")){
                                                    this.controleur.useCarte(j.getCartePrison());
                                                }else{
                                                    this.controleur.restePrison(j);
                                                }break;
            case Construction : if(this.ihm.askYN("Voulez-vous construire ?")){
                                    this.controleur.construire(this.askRueAConstruire(d.getCarreaux()));
                                    this.controleur.construction(j);
                                }break;
            case Double : this.ihm.affiche(EventIhm.affichedeBase,"Vous avez fait un double !",0);break;
            case LancersDes : this.ihm.affiche(EventIhm.affichedeBase,"Résultat lancer : "+j.getNomJoueur()+" a fait un "+d.getI(),0);break;
            case AskString : d.setS(this.ihm.askStr(EventIhm.askdeBase, d.getS(),0));break;
            case AskNb : d.setI(this.ihm.askNb(EventIhm.askdeBase, d.getS()));break;
            case Affiche : this.ihm.affiche(EventIhm.affichedeBase,d.getS(),0); break;
            case Rien : this.ihm.affiche(EventIhm.affichedeBase, "Vous etes sur le parc !" , 0); break;
            default : this.ihm.affiche(EventIhm.affichedeBase,"Vous êtes tranquille. Pour le moment...",0); break;
        }
    }
    
    public Propriete askRueAConstruire(HashMap<String,Carreau> ca){
        Propriete propriete = null;
        Boolean continu = false;
        String indice = null;
        do{
            indice = this.ihm.askStr(EventIhm.askConstruire, "osef",0);
            continu = (ca.containsKey(indice)) ? true : false;
        }while(!continu);
        return propriete = (Propriete)ca.get(indice);
    }
    
    public void tirerCarte(TypeCarte t){
        this.ihm.affiche(EventIhm.affichedeBase,"Tirer une Carte","Vous tirez une carte "+t.toString()+".");
    }

    private int askNb(String message, int i, int i0) {
        ArrayList<String> choix = new ArrayList<>();
        for(int j=i;j<=i0;j++){
            choix.add(Integer.toString(j));
        }
        return Integer.valueOf(this.ihm.askListe(choix, message));
    }

}
