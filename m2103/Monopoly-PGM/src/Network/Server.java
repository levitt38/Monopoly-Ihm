/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import IHM.EventHandler;
import IHM.Questions;
import Jeu.Controleur;
import Jeu.Joueur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class Server {
    private Controleur controleur;
    private EventHandler ihm;
    private HashMap<Integer, Client> liste_client;
    private int port, nb_joueur;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private ServerSocket socketServeur;
    private Socket socketClient;
    
    public Server(int port){
        this.liste_client = new HashMap<>();
        this.controleur = new Controleur();
        this.ihm = new EventHandler(controleur);
        controleur.setObservateur(ihm);
        this.port = Questions.askNb("Veuillez rentrer le port d'écoute du serveur");
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
            this.sInput = new ObjectInputStream(socketClient.getInputStream());
            
            try {
                message = (InitMessage) this.sInput.readObject();
                // Inscription du premier client
                // inscription niveau jeu
                Joueur joueur = new Joueur(message.getNom_joueur(),this.controleur.getMonopoly().getCarreau(0));
                this.controleur.getMonopoly().addJoueur(joueur);
                // inscription niveau réseau
                Client client1 = new Client("chaine pas prise en compte");
                client1.setClient(1,message.getNom_joueur(),this.controleur,this.ihm,this.sOutput);
                this.liste_client.put(1,client1);
                System.out.println("Connexion réussie du joueur host "+client1.getNom_joueur());
                // envoi du message "demande du nb_joueur
                message = new InitMessage("nb_joueur","",0);
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
            System.out.println("Le serveur a crashé telle une bite sur le port "+this.port);
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

                this.sOutput = new ObjectOutputStream(socketClient.getOutputStream());
                this.sInput = new ObjectInputStream(socketClient.getInputStream());

                try {
                    message = (InitMessage) sInput.readObject();
                    this.nb_joueur = message.getNb_joueur();
                    // Inscription du premier client
                    // inscription niveau jeu
                    Joueur joueur = new Joueur(message.getNom_joueur(),this.controleur.getMonopoly().getCarreau(0));
                    this.controleur.getMonopoly().addJoueur(joueur);
                    // inscription niveau réseau
                    Client client1 = new Client("chaine pas prise en compte");
                    client1.setClient(i,message.getNom_joueur(),this.controleur,this.ihm,this.sOutput);
                    this.liste_client.put(i, client1);
                    System.out.println("Connexion réussie du joueur guest "+client1.getNom_joueur());
                    // envoi fictif pour respecter l'équilibre des streams
                    message = new InitMessage("pas host","",0);
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
        GameMessage message = new GameMessage(ActionsGame.Init, this.controleur.getMonopoly());
        for(Client client : this.liste_client.values()){
            sendMessage(client, message);
        }
    }
    
    public static void main(String[] args){
        Server server = new Server(2999);
        server.InitHost();
        server.InitGuests();
        server.InitPartie();
    }
    
    public void sendMessage(Client c, GameMessage message){
        try {
            this.sOutput = c.getOutputServer();
            
            this.sOutput.writeObject(message);
            this.sOutput.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
