package Jeu;
import Data.CouleurPropriete;
import java.util.ArrayList;


public class Groupe {
	private CouleurPropriete couleur;
        private ArrayList<Propriete> proprietes;            //Chaque groupe contient de 2 a 3 priopriete
        
        
        
        public Groupe(CouleurPropriete C){
            this.couleur = C;
            this.setProprietes(new ArrayList<>());
            
        }
    /**
     * @return the couleur
     */
    public CouleurPropriete getCouleur() {
        return couleur;
    }

    /**
     * @return the proprietes
     */
    public ArrayList<Propriete> getProprietes() {
        return proprietes;
    }

    /**
     * @param proprietes the proprietes to set
     */
    private void setProprietes(ArrayList<Propriete> proprietes) {
        this.proprietes = proprietes;
    }
        
    public void addPropriete(Propriete p){
        this.getProprietes().add(p);
    }
    
    public int getMinMaisons(){
        int min = 6;
        for(Propriete p:this.getProprietes()){
            if(p.getNbMaisons()<min){
                min = p.getNbMaisons();
            }
        }
        return min;
    }
        
}