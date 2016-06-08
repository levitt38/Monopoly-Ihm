/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.TypeCarreau;
import Jeu.Carreau;
import Jeu.Gare;
import Jeu.Joueur;
import Jeu.Monopoly;
import Jeu.Observateur;
import Jeu.Propriete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author mouhatcl
 */
public class Affichage {
    
    private static final String TOP_LEFT_JOINT = "┌";
    private static final String TOP_RIGHT_JOINT = "┐";
    private static final String BOTTOM_LEFT_JOINT = "└";
    private static final String BOTTOM_RIGHT_JOINT = "┘";
    private static final String TOP_JOINT = "┬";
    private static final String BOTTOM_JOINT = "┴";
    private static final String LEFT_JOINT = "├";
    private static final String JOINT = "┼";
    private static final String RIGHT_JOINT = "┤";
    private static final String HORIZONTAL_LINE = "─";
    private static final String PADDING = " ";
    private static final String VERTICAL_LINE = "│";
    
    public static void afficherFinTour(){
        Questions.affiche(TextColors.RED+"FIN DU TOUR ------------------------------"+TextColors.RESET);
    }
    public static void AfficherJoueur(Joueur j){
        Questions.affiche(j.getNomJoueur());
        Questions.increment();
        Questions.affiche(Integer.toString(j.getCash())+" $");
        Questions.affiche(j.getPositionCourante().getNomCarreau());
        if(j.getNbGares()>0){
        Questions.affiche("Gares :");
        Questions.increment();
            for (Gare g:j.getGares()){
                Questions.affiche(g.getNomCarreau());
            }
            Questions.decrement();
        }
        if(!j.getProprietes().isEmpty()){
            Questions.affiche("Proprietes :");
            Questions.increment();
            for (Propriete p:j.getProprietes()){
                Questions.affiche(p.getNomCarreauColored());
            }
            Questions.decrement();
        }
        Questions.decrement();
    }
    
    public static void AfficherCarreau(Carreau c){
        Questions.affiche(c.getNomCarreau());
        Questions.increment();
        Questions.affiche(c.getClass().toString());
        Questions.affiche(Integer.toString(c.getNumero()));
        Questions.decrement();
    }
    
    public static ArrayList<Integer> casesInColumn(int i){
        ArrayList<Integer> ret = new ArrayList<>();
        switch(i){
            case 0: for(int j=10;j<=20;j++){ret.add(j);}; break;
            case 10: ret.add(0);for(int j=30;j<40;j++){ret.add(j);}; break;
            default : ret.add(20+i);ret.add(10-i);;
        }
        return ret;
    }
    
    public static ArrayList<Integer> casesInRow(int i){
        ArrayList<Integer> ret = new ArrayList<>();
        switch(i){
            case 0: for(int j=20;j<=30;j++){ret.add(j);}; break;
            case 10: for(int j=0;j<=10;j++){ret.add(j);}; break;
            default : ret.add(30+i);ret.add(20-i);;
        }
        return ret;
    }
    
    public static int heightCarreau(Carreau c){
        // not implemented yet
        return 1+c.getJoueurs().size();
    }
    
    public static int widthCarreau(Carreau c){
        // not fully implemented yet
        return c.getNomCarreau().length();
    }
    
    public static void afficherPlateau(HashMap<String, Carreau> ca){
        
        int[] nbRow = new int[11]; // le nombre de caractères de chaque colonne
        int[] nbColumn = new int[11]; // le nombre de lignes de chaque ligne
        int a;
        for(int i=0;i<nbRow.length;i++){
            nbRow[i] = 0;
            for (int j:casesInColumn(i)){
                a = widthCarreau(ca.get(Integer.toString(j)));
                nbRow[i] = Integer.max(nbRow[i], a);
            }
        }
        for(int i=0;i<nbColumn.length;i++){
            nbColumn[i] = 0;
            for (int j:casesInRow(i)){
                a = heightCarreau(ca.get(Integer.toString(j)));
                nbColumn[i] = Integer.max(nbColumn[i], a);
            }
        }
        
        ArrayList<String> aff = new ArrayList<>();
        String tmp;
        for (int i =0;i<nbColumn.length;i++){
            tmp = "";
            if (i==0){
                for (int j =0;j<nbRow.length;j++){
                    if(j==0){
                        tmp += TOP_LEFT_JOINT;
                    }
                    for (int k = 0;k<nbRow[j];k++){
                        tmp+=HORIZONTAL_LINE;
                    }
                    if(j<nbRow.length-1){
                        tmp+=TOP_JOINT;
                    }else{
                        tmp+=TOP_RIGHT_JOINT;
                    }
                }
                aff.add(tmp);
            }else{
                for (int j =0;j<nbRow.length;j++){
                    if(j==0){
                        tmp += LEFT_JOINT;
                    }
                    for (int k = 0;k<nbRow[j];k++){
                        tmp+=HORIZONTAL_LINE;
                    }
                    if(j<nbRow.length-1){
                        tmp+=JOINT;
                    }else{
                        tmp+=RIGHT_JOINT;
                    }
                }
                aff.add(tmp);
            }
            for (int j = 0;j<nbColumn[i];j++){
                tmp="";
                for (int k =0;k<nbRow.length;k++){
                    if(k==0){
                        tmp += VERTICAL_LINE;
                    }
                    if (i==0 || i==10 || k==0 || k==10){
                        Carreau c = ca.get(Integer.toString(numCase(k,i)));
                        if(j==0){
                            if(c.getType()==TypeCarreau.ProprieteAConstruire){
                                Propriete cc = (Propriete) c;
                                tmp+=cc.getNomCarreauColored();
                            }else{
                                tmp+=c.getNomCarreau();
                            }
                            for (int l = 0;l<nbRow[k]-c.getNomCarreau().length();l++){
                                tmp+=PADDING;
                            }
                        }else{
                            if(j<=c.getJoueurs().size()){
                                tmp+=TextColors.RED+c.getJoueurs().get(j-1).getNomJoueur()+TextColors.RESET;
                                for (int l = 0;l<nbRow[k]-c.getJoueurs().get(j-1).getNomJoueur().length();l++){
                                    tmp+=PADDING;
                                }
                            }else{
                                for (int l = 0;l<nbRow[k];l++){
                                    tmp+=PADDING;
                                }
                            }
                        }
                    }else{
                        for (int l = 0;l<nbRow[k];l++){
                            tmp+=PADDING;
                        }
                    }
                    if(k<nbRow.length-1){
                        tmp+=VERTICAL_LINE;
                    }else{
                        tmp+=VERTICAL_LINE;
                    }
                }
                aff.add(tmp);
            }
        }
        tmp = "";
        for (int j =0;j<nbRow.length;j++){
                if(j==0){
                    tmp += BOTTOM_LEFT_JOINT;
                }
                for (int k = 0;k<nbRow[j];k++){
                    tmp+=HORIZONTAL_LINE;
                }
                if(j<nbRow.length-1){
                    tmp+=BOTTOM_JOINT;
                }else{
                    tmp+=BOTTOM_RIGHT_JOINT;
                }
        }
        aff.add(tmp);
        
        
        for(String s:aff){
            Questions.affiche(s);
        }
        
    }
    
    public static int numCase(int x,int y){
        if (y==10){
            return 10-x;
        }else if (x==0){
            return 20-y;
        }else if(x==10){
            return 30+y;
        }else{
            return 20+x;           
        }
    }
}
