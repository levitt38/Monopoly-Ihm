/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.Evenement;
import Jeu.Cartes.Carte;
import java.util.HashMap;

/**
 *
 * @author mouhatcl
 */
public class DataModel {
    private Joueur j;
    private Carreau c;
    private int i;
    private HashMap<String, Carreau> carreaux;
    private Evenement e;
    private Carte carte;

    public DataModel(int i, Joueur j, Evenement e) {
        this.i = i;
        this.j = j;
        this.e = e;
    }

    public DataModel(Evenement e) {
        this(null,null,0,null,e,null);
    }
    
    public DataModel(Joueur j,Evenement e){
        this(j, null, 0,null,e,null);
    }
    
    public DataModel(Joueur j,HashMap<String, Carreau> carreaux,Evenement e){
        this(j, null, 0,carreaux,e,null);
    }

    public DataModel(HashMap<String, Carreau> carreaux,Evenement e) {
        this(null,null,0,carreaux,e,null);
    }

    public DataModel(Joueur j, Carreau c,Evenement e) {
        this(j,c,0,null,e,null);
    }
    
    public DataModel(Evenement e, Carte c, Joueur j) {
        this(j,null,0,null,e,c);
    }

    public DataModel(Joueur j, Carreau c, int i, HashMap<String, Carreau> carreaux,Evenement e,Carte carte) {
        this.j = j;
        this.c = c;
        this.i = i;
        this.carreaux = carreaux;
        this.e = e;
        this.carte = carte;
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
        return carreaux;
    }

    public Evenement getE() {
        return e;
    }

    public Carte getCarte() {
        return carte;
    }
}
