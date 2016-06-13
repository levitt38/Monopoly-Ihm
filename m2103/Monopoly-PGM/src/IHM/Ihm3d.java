/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.EventIhm;
import Data.TypePions;
import FrameAcceuil.AnimationFrame;
import FrameAcceuil.FrameAcceuil;
import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Controleur;
import Jeu.Joueur;
import Network.Client;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import sun.reflect.generics.tree.VoidDescriptor;

/**
 *
 * @author nourik
 */
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
        this.controler = new Controleur();
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
        new Thread(){
            public void run(){
            client = new Client(new Ihm3d());
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
            client.setIhm(new IhmConsole(controler));
            frame_accueil.setVisible(false);
            frame_jeu.setVisible(true);
            client.InitPartie();
            client.mainLoop();
             }
        }.start();  
    }
    
    public TypePions askTypePion(int num_joueur){ // methode propre a ihm3d
        String choix = ""; TypePions type = TypePions.Rien;
        for(JTextField tf : this.frame_accueil.getListe_choixPions()){
                                         if(Integer.valueOf(tf.getText().trim())==num_joueur){
                                             choix = tf.getName().trim(); break;
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
       
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame_jeu.getTextAction().setText(s);
                    frame_jeu.showAction();
                }
            });
        
        
        
                 
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
        }
        return reponse;
    }
    
    public boolean askYN(final String s){
        Boolean choix;
        
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    frame_jeu.getTextAction().setText(s);
                }});
    

    
        System.out.println(this.frame_jeu.getTextAction().getText());
        // Animation droite => gauche dans version finale
        while(this.frame_jeu.isYesNoSaisi()==false){
            System.out.println("debug");
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ihm3d.class.getName()).log(Level.SEVERE, null, ex);
            }*/
    }
        choix = this.frame_jeu.isYesNoChoix();
        this.frame_jeu.setYesNoSaisi(false);
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
                    frame_jeu.getTextCarreau().setText(j.getPositionCourante().affiche3d());
                    frame_jeu.showJoueur();
                    frame_jeu.showCarreau();
                }
            });
        
        
        
    }            
    
    
    public void afficherCarreau(final Carreau c){
       
    }
    
    public void afficherPlateau(HashMap<String,Carreau> c){
        this.frame_jeu.afficherPlateau(c);
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
    
    public static void main(String[] args){
        Ihm3d ihm = new Ihm3d();
    }
    
}
