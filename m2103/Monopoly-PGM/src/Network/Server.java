/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import IHM.Affichage;
import IHM.EventHandler;
import IHM.Questions;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class Server {
    private ControleurServer controleur;
    private ServerHandler handler;
    private HashMap<Integer, Client> liste_client;
    private int port, nb_joueur;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private ServerSocket socketServeur;
    private Socket socketClient;
    private boolean partieContinue = true;
    
    public Server(int port){
        this.liste_client = new HashMap<>();
        this.controleur = new ControleurServer();
        this.handler = new ServerHandler(controleur, this);
        controleur.setObservateur(handler);
        //this.port = Questions.askNb("Veuillez rentrer le port d'écoute du serveur");
        this.port = port;
    }
    
    public void InitHost(){
	
        InitMessage message;
        // Connexion du premier client
        try {
            socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur sur le port "+this.port);
            socketClient = socketServeur.accept();
            System.out.println("Connexion de host "+socketClient.getInetAddress());
            this.sOutput = new ObjectOutputStream(socketClient.getOutputStream());
            //this.sOutput.flush(); //
            this.sInput = new ObjectInputStream(socketClient.getInputStream());
            
            
            try {
                message = (InitMessage) this.sInput.readObject();
                // Inscription du premier client
                // inscription niveau jeu
                this.controleur.getMonopoly().addJoueur(new Joueur(message.getNom_joueur(),this.controleur.getMonopoly().getCarreau(0),1));
                // inscription niveau réseau
                ObjectOutputStream outStream = this.sOutput;
                ObjectInputStream inStream  = this.sInput;     
                this.liste_client.put(1,new Client(1,message.getNom_joueur(),this.controleur,outStream,inStream));
                System.out.println("Connexion réussie du joueur host "+message.getNom_joueur());
                // envoi du message "demande du nb_joueur
                message = new InitMessage("est host","",1); // 1 => position du joueur
                sOutput.writeObject(message);
                sOutput.flush();
                // réception du nb_joueur en provenance du client
                message = (InitMessage) this.sInput.readObject();
                this.nb_joueur = message.getNb_joueur();
                System.out.println("Le host a choisi une partie a "+nb_joueur+" joueurs");
                        
            } catch (ClassNotFoundException ex) {
                System.out.println("L'object envoyé est mauvais");;
            }
        
            
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void InitGuests(){
        for(int i=2;i<this.nb_joueur+1;i++){
            InitMessage message;
            // Connexion du premier client
            try {
                System.out.println("En attente des clients guest");
                Socket socketClient = socketServeur.accept();
                System.out.println("Connexion de guest "+socketClient.getInetAddress());
                
                this.sInput = new ObjectInputStream(socketClient.getInputStream());
                this.sOutput = new ObjectOutputStream(socketClient.getOutputStream());
                //sOutput.flush(); //

                try {
                    message = (InitMessage) this.sInput.readObject();
                    // Inscription du premier client
                    // inscription niveau jeu
                    Joueur joueur = new Joueur(message.getNom_joueur(),this.controleur.getMonopoly().getCarreau(0),i);
                    this.controleur.getMonopoly().addJoueur(joueur);
                    // inscription niveau réseau
                    ObjectOutputStream outStream = this.sOutput;
                    ObjectInputStream inStream  = this.sInput; 
                    this.liste_client.put(i, new Client(i,message.getNom_joueur(),this.controleur,outStream,inStream));
                    System.out.println("Connexion réussie du joueur guest "+message.getNom_joueur());
                    // envoi fictif pour respecter l'équilibre des streams
                    message = new InitMessage("est guest","",i); // i correspond a la position du joueur
                    sOutput.writeObject(message);
                    sOutput.flush();

                } catch (ClassNotFoundException ex) {
                    System.out.println("L'object envoyé est mauvais");;
                }

            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Liste des clients connectés en fin d'initialisation :");
        for(Client client : this.liste_client.values()){
            System.out.println(client.getNom_joueur());
        }
    }
    
    public void InitPartie(){
        DataModel message = new DataModel(ActionReseau.Init, this.controleur.getMonopoly());
        for(Client client : this.liste_client.values()){
            sendMessage(client, message);
        }
    }
    
    public void mainLoop(){
        int a = 0; // Provisoire en attendant d'implementer le tirage au sort
        int tour = (a%2)+1; //necessaire, car dictionnaire
        while(!this.controleur.partieEstFinie()){
            Client client = this.liste_client.get(tour);
            System.out.println(client.getPosition_joueur()); //
            Joueur j = this.controleur.getMonopoly().getJoueurs().get(tour-1); // -1 necessaire car collection
            System.out.println(j.getNomJoueur());
            System.out.println(j.getCash());
            if(!j.estBankrupt()){
                this.controleur.observateur.notifier(new DataModel(j,this.controleur.getMonopoly(),Evenement.DebutTour)); //affiche Plateau
                this.controleur.jouerUnCoup(j,client);
                if(j.estBankrupt()){
                    this.controleur.joueurDead(j); 
                    System.out.println("bankrupt"); ////
                }
                this.controleur.getObservateur().notifier(new DataModel(Evenement.FinTour));
            }
            if(/*(!this.controleur.isLancerDouble())&&*/(!j.estBankrupt())){
                tour=(tour)%this.controleur.getMonopoly().getJoueurs().size();
                tour ++; //necessaire, car dictionnaire
                System.out.println("rentrer dans incr");
            }
            this.handler.setControleur(this.controleur);
        }
        for (Joueur j:this.controleur.getMonopoly().getJoueurs()){
            if(!j.estBankrupt()){
                this.controleur.getObservateur().notifier(new DataModel(j,Evenement.PartieTerminee));
            }
        }
    }

    
    public static void main(String[] args){
        Server server = new Server(2999);
        server.InitHost();
        server.InitGuests();
        server.InitPartie();
        server.mainLoop();
    }
    
    public void sendMessage(Client c, DataModel message){
        try {
            this.sOutput = c.getOutputServer(); this.sOutput.reset();
            this.sInput = c.getInputServer(); 
            this.sOutput.writeUnshared(message);
            this.sOutput.flush();
        } catch (IOException ex) {
            this.handler.notifier(new DataModel(2, "Partie terminée ", Evenement.Affiche));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.exit(0);
        }
    }
    
    public DataModel receiveMessage(Client c){
        DataModel message = null;
        try {
        this.sInput = c.getInputServer(); 
        this.sOutput = c.getOutputServer(); this.sOutput.reset();
        message = (DataModel) this.sInput.readUnshared();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    
    public void sendMessagetoAll(DataModel message){
        for(Client client : this.liste_client.values()){
            sendMessage(client, message);
        }
    }
}
