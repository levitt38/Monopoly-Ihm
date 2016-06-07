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
    private HashSet<Client> liste_client;
    private int port, nb_joueur;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private ServerSocket socketServeur;
    private Socket socketClient;
    
    public Server(int port){
        this.liste_client = new HashSet<>();
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
                Client client1 = new Client("server");
                client1.setClient(joueur,this.controleur,this.ihm,socketClient.getInetAddress(),socketServeur.getLocalPort());
                this.liste_client.add(client1);
                System.out.println("Connexion réussie du joueur host "+client1.getJoueur().getNomJoueur());
                // envoi du message "demande du nb_joueur
                message = new InitMessage("nb_joueur","",0);
                sOutput.writeObject(message);
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
                    Client client1 = new Client("server");
                    client1.setClient(joueur,this.controleur,this.ihm,socketClient.getInetAddress(),socketServeur.getLocalPort());
                    this.liste_client.add(client1);
                    System.out.println("Connexion réussie du joueur guest "+client1.getJoueur().getNomJoueur());

                } catch (ClassNotFoundException ex) {
                    System.out.println("L'object envoyé est mauvais");;
                }

            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Liste des clients connectés en fin d'initialisation :");
        for(Client client : this.liste_client){
            System.out.println(client.getJoueur().getNomJoueur());
        }
    }
    
    public void InitPartie(){
        
    }
    
    public static void main(String[] args){
        Server server = new Server(2999);
        server.InitHost();
        server.InitGuests();
    }
}
