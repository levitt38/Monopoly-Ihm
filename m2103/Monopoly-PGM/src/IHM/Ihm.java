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
import java.util.HashMap;

/**
 *
 * @author nourik
 */
public class Ihm implements Observateur{
    private Controleur controleur;
    private FenetreJeu fenetreJeu;
    
    public Ihm(Controleur controleur){
        this.controleur = controleur;
        this.controleur.setObservateur(this);
        this.fenetreJeu = new FenetreJeu();
        this.fenetreJeu.afficher();
    }
    
    @Override
    public void affiche(String s){
        IhmBoiteMessage.afficherBoiteMessage(s, 0);
        //Questions.affiche(s);
    }
    
    public void affiche(String titre, String s){
        IhmBoiteMessage.afficherBoiteMessage(titre, s, 0);
    }
    
    public int askNb(String s){
        return Questions.askNb(s);
    }
    
    public String askStr(String s){
        return IhmBoiteMessage.afficherBoiteMessage("", s);
    }
    
    public boolean askYN(String s){
        return IhmBoiteMessage.afficherBoiteMessage(s, 1);
        //return Questions.askYN(s);
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
                                this.affiche(j.getNomJoueur()+"paye un loyer de "+cAchetable.getPrixAchat()+"€ a"+cAchetable.getProprietaire().getNomJoueur());
                             break;
            case SurSaCase : this.affiche("Vous êtes sur une de vos propriété, détendez vous"); break;
            case AchatPossible : cAchetable = (CarreauAchetable) c;
                            String choix = "non";                                    
                          if(this.askYN("Voulez-vous acheter "+cAchetable.getNomCarreau()+" pour "+cAchetable.getPrixAchat()+"€ ?")){
                              this.controleur.joueurAchete(c,j);
                          } break;
            case AchatImpossible : this.affiche("Vous n'avez pas le budget pour acheter ce bien"); break;
            // Events concernant cases autres
            case AllerEnPrison : this.affiche("Joueur "+j.getNomJoueur()+" envoyé en prison!");
                                 break;
            case PayerPenalite :this.affiche("Le joueur "+j.getNomJoueur()+" paie "+((CarreauPenalite)c).getPenalite()+"$");
                                break;
            case SortieDePrisonDes : this.affiche("Vous avez fait un double, fin de votre séjour en prison ! Vous pouvez jouer");break;
            case SortieDePrisonCaution : this.affiche("Fin de vos 3 tours en prison ! vous payez 50€");break;
            case SortieDePrisonCarte : this.affiche("Vous utilisez vos relations au gouvernement pour sortir de prison ...");break;
            case ResterPrison : this.affiche("Vous n'avez pas fait de double, vous restez en prison !");break;
            case Bankrupt : this.affiche("Le joueur "+j.getNomJoueur()+" vient d'être éliminé");break;
            case PasseParDepart : this.affiche("Joueur "+j.getNomJoueur()+" recoit sa paie : +200€");break;
            case PartieTerminee : this.affiche("Partie Terminée !! Le joueur "+j.getNomJoueur()+" l'emporte");break;
            case FinTour : this.afficherFinTour();break;
            case TirerCarte : this.tirerCarte(((CarreauCarte)c).getTypeCarte());break;
            case PasAssezDArgent : this.affiche("Vous n'avez pas assez d'argent pour effectuer cette action.");break;
            case PasNivele : this.affiche("Vous devez d'abord construire sur les autres terrains de ce groupe.");break;
            case PlusDeMaisons : this.affiche("Il n'y a plus de maisons disponibles.");break;
            case TropDeMaisons : this.affiche("Il y a déjà le nombre maximal de maisons sur ce terrain.");break;
            case DebutTour : this.afficherPlateau(d.getCarreaux());
                            this.afficherJoueur(j);break;
            case InitialiserPartie : int nb = this.askNb("Entrez le nombre de joueurs",2,6);
                                    for (int i = 0;i<nb;i++){
                                        this.controleur.ajouterJoueur(this.askStr("Entre le nom du joueur"+i));
                                    }break;
            case CarteTiree : boolean prison = d.getCarte().getClass().equals(CarteSortiePrison.class);
                              this.afficherCarte(d.getCarte(),prison);
                              if(!prison){
                                  this.controleur.useCarte(d.getCarte());
                              }break;
            case AllerEnPrisonDes : this.affiche("","C'est votre 3ème double !/nDirection, la prison !");break;
            case UsePossibleCarteSortiePrison : if(this.askYN("Voulez-vous utiliser une carte Sortie de Prison ?")){
                                                    this.controleur.useCarte(j.getCartePrison());
                                                }else{
                                                    this.controleur.restePrison(j);
                                                }break;
            case Construction : if(this.askYN("Voulez-vous construire ?")){
                                    this.controleur.construire(this.askRueAConstruire(d.getCarreaux()));
                                    this.controleur.construction(j);
                                };
            default : this.affiche("Vous êtes tranquille. Pour le moment...");;
        }
    }
    
    public void afficherFinTour(){
        //Affichage.afficherFinTour();
        IhmBoiteMessage.afficherBoiteMessage("FIN DU TOUR", 0);
    }
    
    public Propriete askRueAConstruire(HashMap<String,Carreau> ca){
        // fonction d'ihm qui fait le taff
        return null;
    }
    
    public void tirerCarte(TypeCarte t){
        this.affiche("Tirer une Carte","Vous tirez une carte "+t.toString()+".");
    }
    
    public void afficherCarte(Carte c,boolean prison){
        this.affiche(c.getType().toString(),c.getText());
    }
    
    public void afficherJoueur(Joueur j){
        Affichage.AfficherJoueur(j);
    }
    
    public void afficherCarreau(Carreau c){
        Affichage.AfficherCarreau(c);
    }
    
    public void afficherPlateau(HashMap<String,Carreau> c){
        this.fenetreJeu.getPlateau().setCarreaux(c);
        this.fenetreJeu.getPlateau().repaint();
        //Affichage.afficherPlateau(p);
    }

    private int askNb(String message, int min, int max) {
        // futur pop-up avec un menu déroulant entre min et max
        return 2;
    }
}
