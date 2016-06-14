/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import IHM.Ihm;
import IHM.Ihm3d;
import Jeu.Joueur;
import Jeu.Monopoly;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author nourik
 */
public class ClientChat {
    private ClientHandler handler;
    private InetAddress ip_serveur, ip_client;
    private Socket socketOut;
    private ObjectInputStream sInput, InputforServer;
    private ObjectOutputStream sOutput, OutputforServer;
    private int port_serveur, port_client, position_joueur;
    private Ihm3d ihm;
    
    
    public ClientChat(Ihm3d ihm, String ipserveur, int portserveur){
        this.ihm = ihm;
        //this.
    }
}
