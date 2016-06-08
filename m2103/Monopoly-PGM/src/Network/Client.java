/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import IHM.Affichage;
import IHM.EventHandler;
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
    private Controleur controleur;
    private EventHandler ihm;
    private Monopoly monopoly;
    private Joueur joueur;
    private InetAddress ip_serveur, ip_client;
    private Socket socketOut, socketIn;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput, OutputServer;
    private int port_serveur, port_client, position_joueur;
    private String nom_joueur;
    
    public Client(){
        // En attendant l'initialisation, pour pouvoir utiliser les méthodes d'affichage
        this.controleur = new Controleur();
        this.ihm = new EventHandler(controleur);
        this.controleur.setObservateur(ihm);
        this.monopoly = this.controleur.getMonopoly();
        // Les infos pour se connecter au serveur
            /*
            String ip = Questions.askStr("Rentrer l'adresse ip du serveur");
            try {
            this.ip_serveur = InetAddress.getByName(ip.trim());
            } catch (UnknownHostException ex) {ex.printStackTrace();}
            this.port_serveur = Questions.askNb("Rentrer le port d'écoute du serveur");*/
        try {
            this.ip_serveur = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port_serveur = 2999;
        try {
            this.socketOut = new Socket(this.ip_serveur,this.port_serveur);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public Client(String serveur){
        // En attendant l'initialisation, pour pouvoir utiliser les méthodes d'affichage
        this.controleur = new Controleur();
        this.ihm = new EventHandler(controleur);
        this.controleur.setObservateur(ihm);
        this.monopoly = this.controleur.getMonopoly();
        // Les infos pour se connecter au serveur
    }
    
    public GameMessage receiveMessage(){
        GameMessage message = null;
        try {
            message = (GameMessage) this.sInput.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    
    public void sendMessage(GameMessage message){
        try {
            this.sOutput.writeObject(message);
            this.sOutput.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InitConnexion(){
        String nom_joueur = Questions.askStr("Rentrer le nom de votre joueur");
        InitMessage message = new InitMessage("connexion",nom_joueur, 0);
      
        try {
            this.sOutput = new ObjectOutputStream(socketOut.getOutputStream());
            this.sInput = new ObjectInputStream(socketOut.getInputStream());
            
            
            this.sOutput.writeObject(message);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public void InitNb_Joueur(){
        int nb_joueur = Questions.askNb("Rentrer le nombre de joueur dans la partie");
        InitMessage message = new InitMessage("nb_joueur","", nb_joueur);
    try {
            this.sOutput.writeObject(message);
            
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public boolean isHost(){
        InitMessage message = null;
        try {
            try {
                message = (InitMessage) this.sInput.readObject();
            } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
            
        } catch (IOException ex) { ex.printStackTrace(); }
        return (message.getType().trim().equals("nb_joueur"));
    }
    
    public void InitPartie(){
        Questions.affiche("Bienvenue dans l'arène, la partie va commencer !");
        // Affichage plateau
        GameMessage message = receiveMessage();
        if(message.getType()==ActionsGame.Init){
        this.monopoly = message.getMonopoly();
        Affichage.afficherPlateau(this.monopoly.getCarreaux());
        // Affichage joueur
        //Affichage.AfficherJoueur(this.monopoly.getJoueurs().get(this.position_joueur-1));
        Questions.affiche(String.valueOf(this.position_joueur));
        for(Joueur joueur : this.monopoly.getJoueurs()){
            Affichage.AfficherJoueur(joueur);
        }
        } else {System.out.println("bad type"); }
    }
    
    public static void main(String[] args){
        Client client = new Client();
        client.InitConnexion();
        if(client.isHost()){
            client.InitNb_Joueur();
        }
        client.InitPartie();
    }
    
    public void setClient(int position_joueur,String nom_joueur,Controleur controleur,
            EventHandler ihm,ObjectOutputStream stream){
        this.position_joueur = position_joueur;
        this.nom_joueur = nom_joueur;
        this.controleur = controleur;
        this.ihm = ihm;
        this.OutputServer = stream;
        
    }

    public Controleur getControleur() {
        return controleur;
    }

    public EventHandler getIhm() {
        return ihm;
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

    public Socket getSocketIn() {
        return socketIn;
    }

    public ObjectOutputStream getOutputServer() {
        return OutputServer;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }
    
    
    
    
}
