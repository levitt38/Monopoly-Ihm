package Jeu;

import Data.TypeCarreau;


public class Compagnie extends CarreauAchetable {
        private int dernierLancer = 0;

        public Compagnie(int num, String chaine){
            super(num,chaine,150);
        }
        
        public Compagnie(int numero, String nomCarreau, int prixAchat) {
            super(numero, nomCarreau, prixAchat);
        }
        
        @Override
        public TypeCarreau getType(){
            return TypeCarreau.Compagnie;
        }

        public void setDernierLancer(int dernierLancer) {
            this.dernierLancer = dernierLancer;
        }
        
        @Override
	public int calculLoyer() {
            int nb_compagnies = 0;
            if(this.getProprietaire()!=null){
                nb_compagnies = this.getProprietaire().getCompagnies().size();
            }
            switch(nb_compagnies){
                case 1 : return 4*this.dernierLancer;
                case 2 : return 10*this.dernierLancer;
                default : return 0;
            }
        }
}