/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.Evenement;
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

/**
 *
 * @author nourik
 */
public class EventHandler implements Observateur{
    private Controleur controleur;
    private Ihm ihm = new IhmSwing(); // ligne à changer pour changer d'IHM
    
    public EventHandler(Controleur controleur){
        this.controleur = controleur;
        this.controleur.setObservateur(this);
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
                                this.ihm.affiche(j.getNomJoueur()+"paye un loyer de "+cAchetable.getPrixAchat()+"€ a"+cAchetable.getProprietaire().getNomJoueur());
                             break;
            case SurSaCase : this.ihm.affiche("Vous êtes sur une de vos propriété, détendez vous"); break;
            case AchatPossible : cAchetable = (CarreauAchetable) c;
                            String choix = "non";                                    
                          if(this.ihm.askYN("Voulez-vous acheter "+cAchetable.getNomCarreau()+" pour "+cAchetable.getPrixAchat()+"€ ?")){
                              this.controleur.joueurAchete(c,j);
                          } break;
            case AchatImpossible : this.ihm.affiche("Vous n'avez pas le budget pour acheter ce bien"); break;
            // Events concernant cases autres
            case AllerEnPrison : this.ihm.affiche("Joueur "+j.getNomJoueur()+" envoyé en prison!");
                                 break;
            case PayerPenalite :this.ihm.affiche("Le joueur "+j.getNomJoueur()+" paie "+((CarreauPenalite)c).getPenalite()+"$");
                                break;
            case SortieDePrisonDes : this.ihm.affiche("Vous avez fait un double, fin de votre séjour en prison ! Vous pouvez jouer");break;
            case SortieDePrisonCaution : this.ihm.affiche("Fin de vos 3 tours en prison ! vous payez 50€");break;
            case SortieDePrisonCarte : this.ihm.affiche("Vous utilisez vos relations au gouvernement pour sortir de prison ...");break;
            case ResterPrison : this.ihm.affiche("Vous n'avez pas fait de double, vous restez en prison !");break;
            case Bankrupt : this.ihm.affiche("Le joueur "+j.getNomJoueur()+" vient d'être éliminé");break;
            case PasseParDepart : this.ihm.affiche("Joueur "+j.getNomJoueur()+" recoit sa paie : +200€");break;
            case PartieTerminee : this.ihm.affiche("Partie Terminée !! Le joueur "+j.getNomJoueur()+" l'emporte");break;
            case FinTour : this.ihm.afficherFinTour();break;
            case TirerCarte : this.tirerCarte(((CarreauCarte)c).getTypeCarte());this.controleur.tirerCarte(j,((CarreauCarte)c).getTypeCarte());break;
            case PasAssezDArgent : this.ihm.affiche("Vous n'avez pas assez d'argent pour effectuer cette action.");break;
            case PasNivele : this.ihm.affiche("Vous devez d'abord construire sur les autres terrains de ce groupe.");break;
            case PlusDeMaisons : this.ihm.affiche("Il n'y a plus de maisons disponibles.");break;
            case TropDeMaisons : this.ihm.affiche("Il y a déjà le nombre maximal de maisons sur ce terrain.");break;
            case DebutTour : this.ihm.afficherPlateau(d.getCarreaux());
                            this.ihm.afficherJoueur(j);break;
            case InitialiserPartie : int nb = this.askNb("Entrez le nombre de joueurs",2,6);
                                    for (int i = 0;i<nb;i++){
                                        this.controleur.ajouterJoueur(this.ihm.askStr("Entre le nom du joueur"+i));
                                    }break;
            case CarteTiree : boolean prison = d.getCarte().getClass().equals(CarteSortiePrison.class);
                              this.ihm.afficherCarte(d.getCarte());
                              if(!prison){
                                  this.controleur.useCarte(d.getCarte());
                              }break;
            case AllerEnPrisonDes : this.ihm.affiche("","C'est votre 3ème double !/nDirection, la prison !");break;
            case UsePossibleCarteSortiePrison : if(this.ihm.askYN("Voulez-vous utiliser une carte Sortie de Prison ?")){
                                                    this.controleur.useCarte(j.getCartePrison());
                                                }else{
                                                    this.controleur.restePrison(j);
                                                }break;
            case Construction : if(this.ihm.askYN("Voulez-vous construire ?")){
                                    this.controleur.construire(this.askRueAConstruire(d.getCarreaux()));
                                    this.controleur.construction(j);
                                }break;
            case Double : this.ihm.affiche("Vous avez fait un double !");break;
            case LancersDes : this.affiche("Résultat lancer : "+j.getNomJoueur()+" a fait un "+d.getI());break;
            default : this.ihm.affiche("Vous êtes tranquille. Pour le moment...");;
        }
    }
    
     public Propriete askRueAConstruire(HashMap<String,Carreau> ca){
        // fonction d'ihm qui fait le taff
        return null;
    }
    
    public void tirerCarte(TypeCarte t){
        this.ihm.affiche("Tirer une Carte","Vous tirez une carte "+t.toString()+".");
    }

    private int askNb(String message, int i, int i0) {
        ArrayList<String> choix = new ArrayList<>();
        for(int j=i;j<=i0;j++){
            choix.add(Integer.toString(j));
        }
        return Integer.valueOf(this.ihm.askListe(choix, message));
    }

}
