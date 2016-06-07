/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import IHM.Ihm;
import IHM.Questions;
import Jeu.Controleur;
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
    private Ihm ihm;
    private Monopoly monopoly;
    private Joueur joueur;
    private InetAddress ip_serveur, ip_client;
    private Socket socket;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private int port_serveur, port_client;
    
    public Client(){
        // En attendant l'initialisation, pour pouvoir utiliser les méthodes d'affichage
        this.controleur = new Controleur();
        this.ihm = new Ihm(controleur);
        this.controleur.setObservateur(ihm);
        this.monopoly = this.controleur.getMonopoly();
        // Les infos pour se connecter au serveur
        String ip = Questions.askStr("Rentrer l'adresse ip du serveur");
        try {
            this.ip_serveur = InetAddress.getByName(ip.trim());
        } catch (UnknownHostException ex) {ex.printStackTrace();}
        this.port_serveur = Questions.askNb("Rentrer le port d'écoute du serveur");
        try {
            this.socket = new Socket(this.ip_serveur,this.port_serveur);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
    
    public Client(String serveur){
        // En attendant l'initialisation, pour pouvoir utiliser les méthodes d'affichage
        this.controleur = new Controleur();
        this.ihm = new Ihm(controleur);
        this.controleur.setObservateur(ihm);
        this.monopoly = this.controleur.getMonopoly();
        // Les infos pour se connecter au serveur
    }
    
    public void InitConnexion(){
        String nom_joueur = Questions.askStr("Rentrer le nom de votre joueur");
        InitMessage message = new InitMessage("connexion",nom_joueur, 0);
      
        try {
            this.sOutput = new ObjectOutputStream(socket.getOutputStream());
            this.sInput = new ObjectInputStream(socket.getInputStream());
            
            
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
    
    public static void main(String[] args){
        Client client = new Client();
        client.InitConnexion();
        if(client.isHost()){
            client.InitNb_Joueur();
        }
    }
    
    public void setClient(Joueur joueur,Controleur controleur,Ihm ihm,InetAddress ip_client,int port_client){
        this.joueur = joueur;
        this.controleur = controleur;
        this.ihm = ihm;
        this.ip_client = ip_client;
        this.port_client = port_client;
        
    }

    public Controleur getControleur() {
        return controleur;
    }

    public Ihm getIhm() {
        return ihm;
    }

    public Joueur getJoueur() {
        return joueur;
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
    
    
    
    
}
