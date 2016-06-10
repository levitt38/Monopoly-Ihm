/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Jeu.Cartes.Carte;
import Network.ActionReseau;
import Network.Client;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author mouhatcl
 */
public class DataModel implements Serializable{
    private Joueur j;
    private Carreau c;
    private int i;
    private Boolean bool;
    private HashMap<String, Carreau> carreaux;
    private Evenement e;
    private Carte carte;
    private String s;
    private ActionReseau action_reseau;
    private Client client;
    private Monopoly monopoly;

    public String getS() {
        return s;
    }
    
    public void setI(int i) {
        this.i = i;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    public DataModel(ActionReseau action_reseau,Client client){
        this.action_reseau = action_reseau;
        this.client = client;
    }
    
    public DataModel(Evenement evenement, Monopoly monopoly){
        this.e = evenement;
        this.monopoly = monopoly;
    }
    
    public DataModel(Joueur j, Monopoly monopoly,Evenement evenement){
        this.e = evenement;
        this.monopoly = monopoly;
        this.j = j;
    }
    
    public DataModel(ActionReseau action, Monopoly monopoly){
        this.action_reseau = action;
        this.monopoly = monopoly;
    }

    public DataModel(Evenement e, String s) {
        this.e = e;
        this.s = s;
    }
    
    public DataModel(Evenement e, Boolean b) {
        this.e = e;
        this.bool = b;
    }

    public DataModel(int i, Joueur j, Evenement e) {
        this.i = i;
        this.j = j;
        this.e = e;
    }
    
    public DataModel(int i, String s, Evenement e){
        this.i = i;
        this.s = s;
        this.e = e;
    }

    public DataModel(Evenement e) {
        this(null,null,0,null,e,null);
    }
    
    public DataModel(Evenement e, Client client) {
        this(null,null,0,null,e,null,client);
    }
    
    public DataModel(Joueur j,Evenement e){
        this(j, null, 0,null,e,null);
    }
    
    public DataModel(Joueur j,Evenement e,Client client){
        this(j, null, 0,null,e,null,client);
    }
    
    public DataModel(Joueur j,HashMap<String, Carreau> carreaux,Evenement e){
        this(j, null, 0,carreaux,e,null);
    }
    
    public DataModel(Joueur j,HashMap<String, Carreau> carreaux,Evenement e,Client client){
        this(j, null, 0,carreaux,e,null,client);
    }

    public DataModel(HashMap<String, Carreau> carreaux,Evenement e) {
        this(null,null,0,carreaux,e,null);
    }

    public DataModel(Joueur j, Carreau c,Evenement e) {
        this(j,c,0,null,e,null);
    }
    
    public DataModel(Joueur j, Carreau c,Evenement e, Client client) {
        this.j = j;
        this.c = c;
        this.e = e;
        this.client = client;
    }
    
    public DataModel(Evenement e, Carte c, Joueur j) {
        this(j,null,0,null,e,c);
    }
    
    public DataModel(Evenement e, Carte c, Joueur j, Client client) {
        this(j,null,0,null,e,c,client);
    }

    public DataModel(Joueur j, Carreau c, int i, HashMap<String, Carreau> carreaux,Evenement e,Carte carte) {
        this.j = j;
        this.c = c;
        this.i = i;
        this.carreaux = carreaux;
        this.e = e;
        this.carte = carte;
    }
    
    public DataModel(Joueur j, Carreau c, int i, HashMap<String, Carreau> carreaux,Evenement e,Carte carte,Client client) {
        this.j = j;
        this.c = c;
        this.i = i;
        this.carreaux = carreaux;
        this.e = e;
        this.carte = carte;
        this.client = client;
    }

    public Joueur getJ() {
        return j;
    }

    public Carreau getC() {
        return c;
    }

    public int getI() {
        return i;
    }

    public HashMap<String, Carreau> getCarreaux() {
        return this.carreaux;
    }

    public Evenement getE() {
        return e;
    }

    public Carte getCarte() {
        return carte;
    }

    public ActionReseau getAction_reseau() {
        return action_reseau;
    }

    public Client getClient() {
        return client;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
    
    
}
