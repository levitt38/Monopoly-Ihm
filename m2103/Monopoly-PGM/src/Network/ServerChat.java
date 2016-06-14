/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Jeu.DataModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class ServerChat {
    private ControleurServer controleur;
    private HashMap<Integer, ClientChat> liste_client;
    private int port, nb_joueur;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private ServerSocket socketServeur;
    private Socket socketClient;
    
    public ServerChat(int port, int nb_joueur){
        this.port = port;
        this.nb_joueur = nb_joueur;
    }
    
    public void startServer(){
        try {
            this.socketServeur = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initClients(){
        for(int i=1;i<=this.nb_joueur;i++){
            Socket socketClient;
            try {
                socketClient = socketServeur.accept();
                this.sInput = new ObjectInputStream(socketClient.getInputStream());
                this.sOutput = new ObjectOutputStream(socketClient.getOutputStream());
                
                ObjectOutputStream outStream = this.sOutput;
                ObjectInputStream inStream  = this.sInput; 
                //this.liste_client.put(i, new ClientChat(i,outStream,inStream));
                
                
            } catch (IOException ex) {
                Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void mainLoop(){
        while(true){
            //DataModel message = this.receiveMessage();
        }
    }
    
    public void sendMessage(Client c, DataModel message){
        try {
            this.sOutput = c.getOutputServer(); this.sOutput.reset();
            this.sInput = c.getInputServer(); 
            this.sOutput.writeUnshared(message);
            this.sOutput.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public DataModel receiveMessage(){
        DataModel message = null;
        Socket socketclient = this.socketServeur.accept();
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
    
    public void sendMessagetoAll(DataModel message){
        //for(Client client : this.liste_client.values()){
            sendMessage(client, message);
        }
    }*/
}
