
package Jeu;

import Data.Evenement;
import Data.TypeCarreau;

/**
 *
 * @author nourik
 */
public abstract class AutreCarreau extends Carreau {


    public AutreCarreau(int num, String chaine){
        super(num,chaine);
    }


    @Override
    public Evenement action(Joueur j) {
        if(j.estPrisonnier()){
            return Evenement.EstEnPrison;
        } 
        else if(this.getNumero()==30) { return Evenement.AllerEnPrison; }
        return Evenement.Rien;
    }
    
    
}