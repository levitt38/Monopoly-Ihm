/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

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
    
    public DataModel(Joueur j){
        this(j, null, 0,null);
    }

    public DataModel(HashMap<String, Carreau> carreaux) {
        this(null,null,0,carreaux);
    }

    public DataModel(Joueur j, Carreau c) {
        this(j,c,0,null);
    }

    public DataModel(Joueur j, Carreau c, int i, HashMap<String, Carreau> carreaux) {
        this.j = j;
        this.c = c;
        this.i = i;
        this.carreaux = carreaux;
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
}
