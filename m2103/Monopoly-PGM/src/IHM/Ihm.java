/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.Evenement;
import Data.TypeCarreau;
import Jeu.AutreCarreau;
import Jeu.Carreau;
import Jeu.CarreauAchetable;
import Jeu.CarreauPenalite;
import Jeu.Controleur;
import Jeu.Joueur;
import Jeu.Monopoly;
import Jeu.Observateur;

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
    
    @Override
    public int askNb(String s){
        return Questions.askNb(s);
    }
    
    @Override
    public String askStr(String s){
        return Questions.askStr(s);
    }
    
    @Override
    public boolean askYN(String s){
        return IhmBoiteMessage.afficherBoiteMessage(s, 1);
        //return Questions.askYN(s);
    }
    
    @Override
    public void notifier(Evenement event, Carreau c, Joueur j){
        CarreauAchetable cAchetable = (c.getType()==TypeCarreau.Gare || c.getType()==TypeCarreau.ProprieteAConstruire ||
                            c.getType()==TypeCarreau.Compagnie) ? (CarreauAchetable)c : null;
        AutreCarreau cAutre = (c.getType()==TypeCarreau.AllerEnPrison || c.getType()==TypeCarreau.AutreCarreau ||
        c.getType()==TypeCarreau.Carte || c.getType()==TypeCarreau.Prison ||c.getType()==TypeCarreau.Penalite) ? (AutreCarreau)c : null;
        switch(event){
            // Event concernant cases achetables
            case PayerLoyer : this.affiche(j.getNomJoueur()+"paye un loyer de "+cAchetable.getPrixAchat()+"€ a"+cAchetable.getProprietaire().getNomJoueur());
                             break;
            case SurSaCase : this.affiche("Vous êtes sur une de vos propriété, détendez vous"); break;
            case AchatPossible : String choix = "non";                                    
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
            case SortieDePrisonCaution : this.affiche("Fin de vos 3 tours en prison ! vous payez 50€");
            case SortieDePrisonCarte : this.affiche("Vous utilisez vos relations au gouvernement pour sortir de prison ...");break;
            case ResterPrison : this.affiche("Vous n'avez pas fait de double, vous restez en prison !");break;
            case Bankrupt : this.affiche("Le joueur "+j.getNomJoueur()+" vient d'être éliminé");break;
            case PasseParDepart : this.affiche("Joueur "+j.getNomJoueur()+" recoit sa paie : +200€");break;
            case PartieTerminee : this.affiche("Partie Terminée !! Le joueur "+j.getNomJoueur()+" l'emporte");
            default : this.affiche("Vous êtes tranquille. Pour le moment...");;
        }
    }
    
    @Override
    public void afficherFinTour(){
        //Affichage.afficherFinTour();
        IhmBoiteMessage.afficherBoiteMessage("FIN DU TOUR", 0);
    }
    
    @Override
    public void afficherJoueur(Joueur j){
        Affichage.AfficherJoueur(j);
    }
    
    @Override
    public void afficherCarreau(Carreau c){
        Affichage.AfficherCarreau(c);
    }
    
    @Override
    public void afficherPlateau(Monopoly p){
        this.fenetreJeu.getPlateau().setCarreaux(p.getCarreaux());
        this.fenetreJeu.getPlateau().repaint();
        //Affichage.afficherPlateau(p);
    }
}
