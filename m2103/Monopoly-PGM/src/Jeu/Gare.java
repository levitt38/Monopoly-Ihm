package Jeu;

import Data.TypeCarreau;

public class Gare extends CarreauAchetable {

        public Gare(int num, String chaine){
            super(num,chaine,200);
        }
        
        @Override
        public TypeCarreau getType(){
            return TypeCarreau.Gare;
        }
 
        @Override
	public int calculLoyer() {
            int nb_gares = 0;
            if (this.getProprietaire()!=null){
                nb_gares = this.getProprietaire().getGares().size();
            }
            return 25*nb_gares;
	}

    public Gare(int numero, String nomCarreau, int prixAchat) {
        super(numero, nomCarreau, prixAchat);
    }

        @Override
    public String affiche3d(){
        String s = "<html>Nom : "+this.getNomCarreau()+"<BR>";
        if(this.getProprietaire()!=null){
            s += "Proprietaire : "+this.getProprietaire().getNomJoueur()+"<BR>";
        }
        s += "</html>";
        return s;
    }    
        
}