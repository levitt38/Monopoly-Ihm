/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Data.Evenement;
import Data.EventIhm;
import IHM.Affichage;
import IHM.Ihm;
import IHM.Ihm3d;
import IHM.IhmConsole;
import IHM.IhmSwing;
import IHM.Questions;
import Jeu.Controleur;
import Jeu.DataModel;
import Jeu.Joueur;
import Jeu.Monopoly;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class Client implements Serializable{
    private ClientHandler handler;
    private Monopoly monopoly;
    private Joueur joueur;
    private InetAddress ip_serveur, ip_client;
    private Socket socketOut;
    private ObjectInputStream sInput, InputforServer;
    private ObjectOutputStream sOutput, OutputforServer;
    private int port_serveur, port_client, position_joueur;
    private String nom_joueur;
    private Ihm ihm;
    
    public Client(Ihm ihm){ // l'ihm peut être de type console | swing | 3d
        this.ihm = ihm;
        // En attendant l'initialisation, pour pouvoir utiliser les méthodes d'affichage
        this.handler = new ClientHandler(this,this.ihm);
        // Les infos pour se connecter au serveur
            
            /*this.port_serveur = Questions.askNb("Rentrer le port d'écoute du serveur");
            try {
            this.ip_serveur = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        this.nom_joueur = this.ihm.askStr(EventIhm.askNom, "Rentrer votre nom ");
    }
  
    public Client(int position_joueur,String nom_joueur,ControleurServer controleur, // cet object est utilisé par le serveur uniquement dans une collection
            ObjectOutputStream OutputServer, ObjectInputStream InputServer){
        this.ihm = new IhmConsole();
        this.handler = new ClientHandler(this,this.ihm);
        ////////////////////////////
        this.position_joueur = position_joueur;
        this.nom_joueur = nom_joueur;
        this.OutputforServer = OutputServer;
        this.InputforServer = InputServer;
    }
  
    public DataModel receiveMessage(){
        DataModel message = null;
        
        try {
            
        this.sOutput.reset();
            message = (DataModel) this.sInput.readUnshared();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    
    public void sendMessage(DataModel message){
        try {
            this.sOutput.reset();
            this.sOutput.writeUnshared(message);
            this.sOutput.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void ConnecttoServer(){
        String ip = ihm.askStr(EventIhm.askIp, "Rentrer l'adresse ip du serveur");
            try {
            this.ip_serveur = InetAddress.getByName(ip.trim());
            } catch (UnknownHostException ex) {ex.printStackTrace();}
        this.port_serveur = this.ihm.askNb(EventIhm.askPort, "Rentrer le port du serveur");
        try {
            this.socketOut = new Socket(this.ip_serveur,this.port_serveur);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public void InitConnexion(){
        InitMessage message = new InitMessage("connexion",this.nom_joueur, 0);
      
        try {
            
            this.sOutput = new ObjectOutputStream(socketOut.getOutputStream());
            this.sInput = new ObjectInputStream(socketOut.getInputStream());
            
            this.sOutput.writeObject(message);
            this.sOutput.flush();
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public void InitNb_Joueur(){
        DataModel model = new DataModel(2, "Rentrer le nombre de joueurs dans la partie", Evenement.AskNbJoueurs);
        this.handler.notifier(model); // recupère l'entier
        int nb_joueur = model.getI(); // recupère l'entier
        InitMessage message = new InitMessage("nb_joueur","", nb_joueur);
    try {
            this.sOutput.writeObject(message);
            this.sOutput.flush();
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public boolean isHost(){
        InitMessage message = null;
        try {
            try {
                message = (InitMessage) this.sInput.readObject();
            } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
            
        } catch (IOException ex) { ex.printStackTrace(); }
        this.position_joueur = (message.getType().trim().equals("est host")) ? 1 : message.getNb_joueur(); //nb_joueur => pos
        return (message.getType().trim().equals("est host"));
    }
    
    /*public void ackServer(){
        InitMessage message = null;
        try {
            try {
                message = (InitMessage) this.sInput.readObject();
            } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
            
        } catch (IOException ex) { ex.printStackTrace(); }
        this.controleur.getObservateur().notifier(new DataModel(Evenement.Affiche, "Tous les joueurs sont connectés sur le serveur"));
    }*/
    
    public void InitPartie(){
        this.handler.notifier(new DataModel(Evenement.Affiche, "Bienvenue dans l'arène, la partie va commencer !!"));
        // Affichage plateau
        DataModel message = receiveMessage();
        if(message.getAction_reseau()==ActionReseau.Init){
        this.monopoly = message.getMonopoly();
        this.handler.notifier(new DataModel(Evenement.Affiche, "Début Partie"));
        this.handler.notifier(new DataModel(message.getMonopoly().getJoueurs().get(this.position_joueur-1),
                                        message.getMonopoly(),Evenement.DebutTour));
        
        this.handler.notifier(new DataModel(Evenement.Affiche, "Liste des joueurs : "));
        for(Joueur joueur : this.monopoly.getJoueurs()){
            Affichage.AfficherJoueur(joueur);
        }
        } else {System.out.println("bad type"); }
    }
    
    public void mainLoop(){
        DataModel message = null;
        while(true){
            message = receiveMessage();
            if(message != null){
                this.handler.notifier(message);
            }
        }
    }
    
    public static void main(String[] args){
        Client client = new Client(new IhmConsole());   //   CHANGER LE TYPE DE L'IHM A VOTRE GUISE
        client.ConnecttoServer();
        client.InitConnexion();
        if(client.isHost()){
            client.InitNb_Joueur();
        }
        //client.ackServer();
        client.InitPartie();
        client.mainLoop();
    }

    public ClientHandler getHandler() {
        return handler;
    }

    public InetAddress getIp_serveur() {
        return ip_serveur;
    }

    public InetAddress getIp_client() {
        return ip_client;
    }

    public int getPort_serveur() {
        return port_serveur;
    }

    public int getPort_client() {
        return port_client;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }
    
    public ObjectOutputStream getOutputServer() {
        return OutputforServer;
    }

    public ObjectInputStream getInputServer() {
        return InputforServer;
    }

   
    public String getNom_joueur() {
        return nom_joueur;
    }

    public int getPosition_joueur() {
        return position_joueur;
    }

    public void setIhm(Ihm ihm) {
        this.ihm = ihm;
        this.handler = new ClientHandler(this, this.ihm);
    }
    
    
    
    
}
