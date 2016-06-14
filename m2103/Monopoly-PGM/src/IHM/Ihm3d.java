/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.Evenement;
import Data.EventIhm;
import Data.TypePions;
import FrameAccueil.AnimationFrame;
import FrameAccueil.FrameAcceuil;
import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Network.Client;
import engineTester.MonopolyTest;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Ihm3d extends Ihm{
    private FrameAcceuil frame_accueil;
    private FrameJeu frame_jeu;
    private Client client; // dans le cas du mode online, un object client est crée
    private Controleur controler; //c'est ce controleur qui géreré une partie locale
    private EventHandler handler; //observateur associé au controleur, tjr dans le cas partie locale
    
    public Ihm3d(){
        this.frame_accueil = new FrameAcceuil(this);
        this.frame_jeu = new FrameJeu(this);
    }
    
    public void startPartieLocal(){
        this.controler = new MonopolyTest("1,0,1,0,2,0,3,7,8,7,9");
        this.handler = new EventHandler(this.controler, this);  
        this.controler.setObservateur(this.handler);  // 
        this.frame_accueil.dispose();
        this.frame_jeu.setVisible(true);
        this.frame_jeu.afficher();
        new Thread(){
            public void run(){
                System.out.println(SwingUtilities.isEventDispatchThread()+"main");
                controler.mainLoop();
            }
        }.start();
        
    }
    
    public void startConnecttoServer(){
        client = new Client(this);
        new Thread(){
            public void run(){
            client.ConnecttoServer();
            }
        }.start();    
    }
    
    public void startPartieOnline(){
        // appel au préalable de startConnecttoServer()
        new Thread(){
            public void run(){
            client.InitConnexion();
            if(client.isHost()){
                client.InitNb_Joueur();
            }
            frame_accueil.dispose();
            frame_jeu.setVisible(true);
            frame_jeu.afficher();
            client.InitPartie();
            client.mainLoop();
             }
        }.start();  
    }
    
    public TypePions askTypePion(int num_joueur){ // methode propre a ihm3d
        String choix = ""; TypePions type = TypePions.Rien;
        for(JTextField tf : this.frame_accueil.getListe_choixPions()){
            
        System.out.println(tf.getName());
                                         if(Integer.valueOf(tf.getText())==num_joueur){
                                             choix = tf.getName(); break;
                                         }
        } switch(choix){
            case "PersoPortal" : type =  TypePions.Portal; break;
            case "PersoBanane" : type = TypePions.Banane; break;
            case "PersoHorloge" : type =  TypePions.Horloge; break;
            case "PersoHamburger" : type =  TypePions.Hamburger; break;
            case "PersoCanette" : type =  TypePions.Canette; break;
            case "PersoTelephone" : type = TypePions.Telephone; break;
          }
        return type;
    }
    
    public void affiche(EventIhm e,final String s, int num){
        switch(e){
            case askConstruire : SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        frame_jeu.cacher2Boutons();
                                        frame_jeu.getTextAction().setText(convertTexttoAction(s));
                                        frame_jeu.showAction();
                                }});
                                break;
            case afficherTirerCarte : SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            frame_jeu.cacher2Boutons();
                                            frame_jeu.getTextAction().setText(convertTexttoAction(s));
                                            frame_jeu.showAction();
                                    }});
                                    while(this.frame_jeu.isCarteSelectionnée()==false){

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                                this.frame_jeu.setCarteSelectionnée(false);
                            break;
            default : 
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                frame_jeu.getTextAction().setText(convertTexttoAction(s));
                                frame_jeu.showAction();
                        }});
                        while(this.frame_jeu.isYesNoSaisi()==false){

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                    this.frame_jeu.setYesNoSaisi(false);
                    this.frame_jeu.cacherBoutons();  
                        break;
        }            
}
    
    public void affiche(EventIhm e, String titre, String s){
        this.affiche(e, s,0);
    }
    
    public int askNb(EventIhm e, String s){
        int reponse = 0;
        switch(e){
            case askNb_joueur : reponse = Integer.valueOf(this.frame_accueil.getTFnb_joueur().getText().trim());
                                break;
            case askPort : reponse = Integer.valueOf(this.frame_accueil.getTFport().getText().trim());
                            break;                    
        }
        return reponse;
    }
    
    public String askStr(EventIhm e, String s, int num){ // num => avoir nom de ce joueur
        String reponse = "";
        switch(e){
            case askdeBase : // pas d'interet pour l'instant 
                break;
            case askIp : reponse =  this.frame_accueil.getTFip().getText();
                break;  
            case askNom : switch(num){
                case 0 : reponse = this.frame_accueil.getTFnom().getText(); break; // nom 1ere frame
                case 1 : reponse = this.frame_accueil.getTFnom1().getText(); break;
                case 2 : reponse = this.frame_accueil.getTFnom2().getText(); break;
                case 3 : reponse = this.frame_accueil.getTFnom3().getText(); break;
                case 4 : reponse = this.frame_accueil.getTFnom4().getText(); break;
                case 5 : reponse = this.frame_accueil.getTFnom5().getText(); break;
                case 6 : reponse = this.frame_accueil.getTFnom6().getText(); break;
            }
                break;
            case askConstruire : this.frame_jeu.setCarreauSelectionné(false);
                                this.affiche(EventIhm.askConstruire, "Cliquer sur la propriété à construire", 0);
                                System.out.println("Demande de construction");
                                // Animation droite => gauche dans version finale
                                while(this.frame_jeu.isCarreauSelectionné()==false){

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("3d"+this.frame_jeu.getPanelPlateau1().getOp().getCarreauSelected());
                                reponse = String.valueOf(this.frame_jeu.getPanelPlateau1().getOp().getCarreauSelected());
                                this.frame_jeu.setCarreauSelectionné(false);
                break;
        }
        return reponse;
    }
    
    public boolean askYN(final String s){
        Boolean choix;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame_jeu.showBoutons();
                    frame_jeu.getTextAction().setText(s);
                    frame_jeu.showAction();
                    
            } });      
           
        
                
        System.out.println(this.frame_jeu.getTextAction().getText());
        // Animation droite => gauche dans version finale
        while(this.frame_jeu.isYesNoSaisi()==false){
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        choix = this.frame_jeu.isYesNoChoix();
        this.frame_jeu.setYesNoSaisi(false);
        this.frame_jeu.cacherBoutons();
        return choix;
    };
    
    public void afficherFinTour(){
        this.affiche(EventIhm.affichedeBase, "Fin de ce Tour",0);
    }
        
    public void afficherCarte(Carte c){
        this.affiche(EventIhm.affichedeBase, c.getText(),0);
    }
    
    public void afficherJoueur(final Joueur j){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame_jeu.getTextJoueur().setText(j.affiche3d());
                    frame_jeu.showJoueur();
                }
            });
    }            
    
    
    public void afficherCarreau(final Carreau c){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame_jeu.getTextCarreau().setText(c.affiche3d());
                    frame_jeu.showCarreau();
                }
            });
    }
    
    public void afficherPlateau(final HashMap<String,Carreau> c){
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame_jeu.afficherPlateau(c);
        }
            });
    }
    
    public String askListe(ArrayList<String> choix,String message){
        return null;
    }

    public FrameAcceuil getFrame_accueil() {
        return frame_accueil;
    }

    public FrameJeu getFrame_jeu() {
        return frame_jeu;
    }

    public Client getClient() {
        return client;
    }
    
    public String convertTexttoAction(String s){
        String retour;
        if(s.length()>=26){
            String s1 = s.substring(0, 25);
            String s2 = s.substring(25, s.length());
            retour = "<html>"+s1+"<BR>";
            retour += s2+"</html>";
        } else { retour = s; }
        return retour;
    }
    
    public static void main(String[] args){
        Ihm3d ihm = new Ihm3d();
    }
    
}
