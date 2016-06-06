package Jeu;

import Data.CouleurPropriete;
import Data.TypeCarreau;
import Data.TypeCarte;
import Exceptions.pasAssezDeMaisonsException;
import IHM.Questions;
import Jeu.Cartes.Carte;
import Jeu.Cartes.CarteAvancer;
import Jeu.Cartes.CarteBouger;
import Jeu.Cartes.CarteJoueursPayer;
import Jeu.Cartes.CartePayer;
import Jeu.Cartes.CartePayerConstructions;
import Jeu.Cartes.CarteTP;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class Monopoly{
        private HashMap<String,Carreau> carreaux = new HashMap<>();
        private ArrayList<Joueur> joueurs = new ArrayList<>();
        private HashMap<CouleurPropriete,Groupe> groupes;
        private Queue<Carte> cartesChance = new ArrayDeque<>();
        private Queue<Carte> cartesCommunaute = new ArrayDeque<>();
        private int nbMaisons;
        private int nbHotels;
        
	public void CreerPlateau(String dataFilename){
		buildGamePlateau(dataFilename);
	}
	
	private void buildGamePlateau(String dataFilename)
	{
		try{
			ArrayList<String[]> data = readDataFile(dataFilename, ",");
			
			
			for(int i=0; i<data.size(); ++i){
				String caseType = data.get(i)[0];
				if(caseType.compareTo("P") == 0){
                                        if(groupes.get(CouleurPropriete.valueOf(data.get(i)[3]))==null){
                                            groupes.put(CouleurPropriete.valueOf(data.get(i)[3]), new Groupe(CouleurPropriete.valueOf(data.get(i)[3])));
                                        }
                                        Groupe g = groupes.get(CouleurPropriete.valueOf(data.get(i)[3]));
					//System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        int[] prix = new int[6];
                                        for (int j=5;j<11;j++){
                                            prix[j-5]=Integer.valueOf(data.get(i)[j]);
                                        }
                                       Propriete p = new Propriete(Integer.valueOf(data.get(i)[1])-1,data.get(i)[2],g,
                                                Integer.valueOf(data.get(i)[4]),prix,Integer.valueOf(data.get(i)[11]));
                                        
                                        g.addPropriete(p);
                                        getCarreaux().put(Integer.toString(i),p);
                                        
				}
				else if(caseType.compareTo("G") == 0){
					//System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        getCarreaux().put(Integer.toString(i),new Gare(Integer.valueOf(data.get(i)[1])-1,data.get(i)[2],Integer.valueOf(data.get(i)[3])));
				}
				else if(caseType.compareTo("C") == 0){
					//System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        Compagnie c = new Compagnie(Integer.valueOf(data.get(i)[1])-1,data.get(i)[2],Integer.valueOf(data.get(i)[3]));
                                        getCarreaux().put(Integer.toString(i),c);
				}
                                else if(caseType.compareTo("CC") == 0){
                                        CarreauCarte c = new CarreauCarte(i, data.get(i)[2]);
                                        this.getCarreaux().put(Integer.toString(i), c);
				}
                                else if(caseType.compareTo("CP") == 0){
                                        CarreauPenalite c = new CarreauPenalite(i,data.get(i)[2],-Integer.valueOf(data.get(i)[3]));
                                        this.getCarreaux().put(Integer.toString(i), c);
                                }
                                else if(caseType.compareTo("CI") == 0){
                                        CarreauInutile c = new CarreauInutile(i, data.get(i)[2]);
                                        this.getCarreaux().put(Integer.toString(i), c);
                                }
                                else if(caseType.compareTo("PP") == 0){
                                        this.getCarreaux().put(Integer.toString(i), new Prison(i));
                                }else if(caseType.compareTo("CAP") == 0){
                                        CarreauAllerEnPrison c = new CarreauAllerEnPrison(i,data.get(i)[2]);
                                        this.getCarreaux().put(Integer.toString(i), c);
                                }
				else
                                    if (i!=41){
					System.err.println("[buildGamePlateau()] : Invalid Data type"+data.get(i)[1]);
                                    }
			}
                        this.getCarreaux().put("40",new Prison(40));
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
	}
	
	private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		BufferedReader reader  = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = reader.readLine()) != null){
			data.add(line.split(token));
		}
		reader.close();
		
		return data;
	}

        public void addJoueur(Joueur j){
            
            this.joueurs.add(j);
            
        }
        public ArrayList<Joueur> getJoueurs(){
            
            return this.joueurs;
            
        }
    /**
     * @return the carreaux
     */
        
    public HashMap<String,Carreau> getCarreaux() {
        return carreaux;
    }
    
    public void construire(Propriete p) throws pasAssezDeMaisonsException{
        if(p.getNbMaisons()<4){
            if (this.nbMaisons==0){
                throw new pasAssezDeMaisonsException();
            }else{
                this.nbMaisons--;
                p.construireMaison();
            }
        }else{
            this.nbHotels--;
            this.nbMaisons+=p.construireHotel();
        }
        
    }
    
    
    public Carreau getCarreau(int i){
        return this.getCarreaux().get(Integer.toString(i));
    }

    public HashSet<Compagnie> getCompagnies() {
        HashSet<Compagnie> h = new HashSet<>();
        for (int i=0;i<this.getCarreaux().size();i++){
            if (this.getCarreau(i).getType().equals(TypeCarreau.Compagnie)){
                h.add((Compagnie) this.getCarreau(i));
            }
        }
        return h;
    }
    
    public Monopoly() {
        this.groupes = new HashMap<>();
        this.CreerPlateau("./src/Data/data.txt");
        this.creerCartes("./src/Data/chance.txt",TypeCarte.chance);
        this.creerCartes("./src/Data/chance.txt",TypeCarte.caisseDeCommunauté);
        this.nbHotels = 12;
        this.nbMaisons = 32;
    }

    public Prison getPrison() {
        return (Prison) this.carreaux.get("40");
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
    private ArrayList<String[]> readDataFile1(String filename, String token) throws FileNotFoundException, IOException
    {
            ArrayList<String[]> data = new ArrayList<String[]>();

            BufferedReader reader  = new BufferedReader(new FileReader(filename));
            String file = "";
            while(reader.ready()){
                file+=reader.read();
            }
            reader.close();
            String[] tmp = new String[3];
            int i = 0;
            for (String s:file.split(token)){
                tmp[i] = s;
                if (i==2){
                    data.add(tmp);
                    i=0;
                }else{
                    i++;
                }
            }
            return data;
    }
    
    private void creerCartes(String dataFileName, TypeCarte type) {
            try{
                ArrayList<String[]> data = readDataFile1(dataFileName, "|");
                for(int i=0; i<data.size(); ++i){
                        String caseType = data.get(i)[0];
                        Carte c = null;
                        if(caseType.compareTo("0") == 0){
                                c = new CarteTP(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }
                        else if(caseType.compareTo("1") == 0){
                                c = new CarteAvancer(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }
                        else if(caseType.compareTo("2") == 0){
                                c = new CarteBouger(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }
                        else if(caseType.compareTo("3") == 0){
                                c = new CarteJoueursPayer(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }
                        else if(caseType.compareTo("4") == 0){
                                c = new CartePayer(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }
                        else if(caseType.compareTo("5") == 0){
                                c = new CartePayerConstructions(data.get(i)[2], type, Integer.valueOf(data.get(i)[2]));
                        }else
                            System.err.println("[buildGamePlateau()] : Invalid Data type"+data.get(i)[1]);
                        if(c!=null){
                            if (type == TypeCarte.chance){
                                this.getCartesChance().add(c);
                            }else{
                                this.getCartesCommunaute().add(c);
                            }
                        }
                }
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
    }

    public Queue<Carte> getCartesChance() {
        return cartesChance;
    }

    public Queue<Carte> getCartesCommunaute() {
        return cartesCommunaute;
    }
    
    
}

