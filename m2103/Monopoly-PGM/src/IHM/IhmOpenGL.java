/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Data.TypePions;
import Jeu.Carreau;
import Jeu.Cartes.Carte;
import Jeu.Joueur;
import Jeu.Propriete;
import java.util.ArrayList;
import java.util.HashMap;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Cube;
import entities.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MasterRenderer;
import textures.ModelTexture;
import toolbox.Maths;
import toolbox.Point;
/**
 *
 * @author louis
 */
public class IhmOpenGL {
    
    public static final int CARTES_CHANCE = -3;
    public static final int CARTES_COMMUNAUTE = -4;
    
    public static double width = 2;
    public static double height = 2;
    private final double LARGEUR_PETITE_CASE = 80*width/1040;
    private final double LARGEUR_GRANDE_CASE = 2*LARGEUR_PETITE_CASE; 
    private final double LARGEUR_PRISON = 55*width/1040;
    private final double LARGEUR_COULEUR = 40*width/1040;
    private final double HAUTEUR_CASE = LARGEUR_GRANDE_CASE;

    private Loader loader = new Loader();
    private List<Entity> entities;
    private Entity skybox;
    private Entity plateau;
    private Entity cartesChance;
    private Entity cartesCComm;
    private Entity cursor;
    private Light light;
    private Camera camera;
    private Player player;
    private MasterRenderer renderer;
    private TexturedModel tMMaison;
    private List<Entity> maisons = new ArrayList<>();
    private float hauteurMaison = 0.015f;
    private float sizeMaison = 0.005f;
    private boolean loadingCompleted = false;
    private ArrayList<Entity> pionsNonTries = new ArrayList<>();
    private HashMap<String,Entity> pions = new HashMap<>();
    private Ihm3d ihm3d;
    
    
    public IhmOpenGL(Ihm3d ihm3d){
        
        this.ihm3d = ihm3d;

        new Thread(){
            @Override
            public void run(){
                DisplayManager.createDisplay();
                loader = new Loader();

                entities = new ArrayList<>();

                // skybox
                ModelTexture textureSkybox = new ModelTexture(loader.loadTexture("skybox3"));
                ModelData dataSkybox = OBJFileLoader.loadOBJ("skybox1");
                RawModel modelSkybox = loader.loadToVAO(dataSkybox.getVertices(), dataSkybox.getTextureCoords(),dataSkybox.getNormals() , dataSkybox.getIndices());
                TexturedModel tMSkybox = new TexturedModel(modelSkybox, textureSkybox);
                skybox = new Entity(tMSkybox, new Vector3f(0,0,0), 0, 0, 0, 100, true);
                tMSkybox.getTexture().setUseFakeLighting(true);
                entities.add(skybox);


                // plateau
                ModelTexture texturePlateau = new ModelTexture(loader.loadTexture("plateau"));
                ModelData dataPlateau = OBJFileLoader.loadOBJ("plateau");
                RawModel modelPlateau = loader.loadToVAO(dataPlateau.getVertices(), dataPlateau.getTextureCoords(),dataPlateau.getNormals() , dataPlateau.getIndices());
                TexturedModel tMPlateau = new TexturedModel(modelPlateau, texturePlateau);
                plateau = new Entity(tMPlateau, new Vector3f(0,0,0), 0, 0, 0, 1, true);
                entities.add(plateau);

                // maison
                ModelTexture textureMaison = new ModelTexture(loader.loadTexture("maison"));
                ModelData dataMaison = OBJFileLoader.loadOBJ("maison");
                RawModel modelMaison = loader.loadToVAO(dataMaison.getVertices(), dataMaison.getTextureCoords(),dataMaison.getNormals() , dataMaison.getIndices());
                tMMaison = new TexturedModel(modelMaison, textureMaison);
                /*Entity maison = new Entity(tMMaison, new Vector3f(0,this.hauteurMaison,0), 0, 0, 0, this.sizeMaison, true);
                entities.add(maison);*/

                //cartes caisse de communauté
                ModelTexture textureCartes = new ModelTexture(loader.loadTexture("cartes"));
                ModelData dataCartesChance = OBJFileLoader.loadOBJ("cartesCCom");
                RawModel modelCartesChance = loader.loadToVAO(dataCartesChance.getVertices(), dataCartesChance.getTextureCoords(),dataCartesChance.getNormals() , dataCartesChance.getIndices());
                TexturedModel tMCartesChance = new TexturedModel(modelCartesChance, textureCartes);
                cartesChance = new Entity(tMCartesChance, new Vector3f(0.3f,0,0.3f), 0, -135, 0, 0.1f, true);
                entities.add(cartesChance);
                
                // pions
                
                
                ModelTexture textureBurger = new ModelTexture(loader.loadTexture("burger"));
                ModelData dataBurger = OBJFileLoader.loadOBJ("burger");
                RawModel modelBurger = loader.loadToVAO(dataBurger.getVertices(), dataBurger.getTextureCoords(),dataBurger.getNormals() , dataBurger.getIndices());
                TexturedModel tMBurger = new TexturedModel(modelBurger, textureBurger);
                pionsNonTries.add(new Entity(tMBurger, new Vector3f(0,0,0), 0, 0, 0, 0.03f, true));
                                
                ModelTexture textureBanana = new ModelTexture(loader.loadTexture("banana"));
                ModelData dataBanana = OBJFileLoader.loadOBJ("banana");
                RawModel modelBanana = loader.loadToVAO(dataBanana.getVertices(), dataBanana.getTextureCoords(),dataBanana.getNormals() , dataBanana.getIndices());
                TexturedModel tMBanana = new TexturedModel(modelBanana, textureBanana);
                pionsNonTries.add(new Entity(tMBanana, new Vector3f(0,0,0), 0, 0, 0, 0.05f, true));
                
                ModelTexture textureClock = new ModelTexture(loader.loadTexture("clock"));
                ModelData dataClock = OBJFileLoader.loadOBJ("clock");
                RawModel modelClock = loader.loadToVAO(dataClock.getVertices(), dataClock.getTextureCoords(),dataClock.getNormals() , dataClock.getIndices());
                TexturedModel tMClock = new TexturedModel(modelClock, textureClock);
                pionsNonTries.add(new Entity(tMClock, new Vector3f(0,0,0), 0, 0, 0, 0.1f, true));
                
                ModelTexture textureCan = new ModelTexture(loader.loadTexture("can"));
                ModelData dataCan = OBJFileLoader.loadOBJ("can");
                RawModel modelCan = loader.loadToVAO(dataCan.getVertices(), dataCan.getTextureCoords(),dataCan.getNormals() , dataCan.getIndices());
                TexturedModel tMCan = new TexturedModel(modelCan, textureCan);
                pionsNonTries.add(new Entity(tMCan, new Vector3f(0,0,0), 0, 0, 0, 0.2f, true));
                
                ModelTexture textureNokia = new ModelTexture(loader.loadTexture("nokia"));
                ModelData dataNokia = OBJFileLoader.loadOBJ("nokia");
                RawModel modelNokia = loader.loadToVAO(dataNokia.getVertices(), dataNokia.getTextureCoords(),dataNokia.getNormals() , dataNokia.getIndices());
                TexturedModel tMNokia = new TexturedModel(modelNokia, textureNokia);
                pionsNonTries.add(new Entity(tMNokia, new Vector3f(0,0,0), 0, 0, 0, 0.05f, true));
                
                ModelTexture textureTurret = new ModelTexture(loader.loadTexture("turret"));
                ModelData dataTurret = OBJFileLoader.loadOBJ("turret");
                RawModel modelTurret = loader.loadToVAO(dataTurret.getVertices(), dataTurret.getTextureCoords(),dataTurret.getNormals() , dataTurret.getIndices());
                TexturedModel tMTurret = new TexturedModel(modelTurret, textureTurret);
                pionsNonTries.add(new Entity(tMTurret, new Vector3f(0,0,0), 0, 0, 0, 0.25f, true));
                
                // cartes chance
                ModelData dataCartesCComm = OBJFileLoader.loadOBJ("cartesChance");
                RawModel modelCartesCComm = loader.loadToVAO(dataCartesCComm.getVertices(), dataCartesCComm.getTextureCoords(),dataCartesCComm.getNormals() , dataCartesCComm.getIndices());
                TexturedModel tMCartesCComm = new TexturedModel(modelCartesCComm, textureCartes);
                cartesCComm = new Entity(tMCartesCComm, new Vector3f(-0.3f,0,-0.3f), 0, 45, 0, 0.1f, true);
                entities.add(cartesCComm);

                // test entity
                Cube dataCursor = new Cube(31,1);
                RawModel modelcursor = loader.loadToVAO(dataCursor.getVertices(), dataCursor.getTextureCoords(),dataCursor.getNormals() , dataCursor.getIndices());
                ModelTexture texture = new ModelTexture(loader.loadTexture("terrain"));
                TexturedModel texturedModelCursor = new TexturedModel(modelcursor, texture);
                cursor=new Entity(texturedModelCursor,new Vector3f(0,0,0),0,0,0,1,true);


                //TexturedModel texturedModelTerrain = new TexturedModel(modelTerrain, terrainTexture);
                //Entity testLight = new Entity(texturedModel, new Vector3f(-2,2,-2),0,0,0,1);
                light = new Light(new Vector3f(-20000,20000,20000),new Vector3f(1,1,1));
                //Mouse.setGrabbed(true);
                player = new Player(7,7,7,0,0,0,"Louis",loader);
                //System.out.println(player.getX());
                renderer = new MasterRenderer();

                camera = new Camera(player,renderer);
                camera.setTest(cursor);
                camera.setPosition(15,5,15);
                
                loadingCompleted = true;
                
                while(!Display.isCloseRequested()){
                    camera.move();
                    skybox.setPosition(player.getX(), player.getY(), player.getZ());
                    for (Entity tmp1:entities){
                            //tmp1.setPosition(0,0,tmp1.getPosition().getZ()+0.01f);
                            //tmp1.setRotX(tmp1.getRotX()+1);
                            //tmp1.setRotY(tmp1.getRotY()+1);
                            renderer.processEntity(tmp1);
                        }
                    
                    for(Entity tmp1:maisons){
                        //tmp1.setRotX(tmp1.getRotX()+1);
                        renderer.processEntity(tmp1);
                    }
                    renderer.render(light,camera);

                    DisplayManager.updateDisplay();
                }


                renderer.cleanUp();
                loader.cleanUp();
                DisplayManager.closeDisplay();
            }
        }.start();
    }

    public boolean isLoadingCompleted() {
        return loadingCompleted;
    }
    
    public static int coordsToNumCase(float x, float y){
        if(x<-1||y<-1||x>1||y>1){
            return -2;
        }else{
            return Plateau.coordsToNumCase((int)((x+1)/2*Plateau.width),(int)((y+1)/2*Plateau.height));
        }
    }

    public synchronized void afficherPlateau(HashMap<String, Carreau> c) {
        this.maisons = new ArrayList<>();
        while(this.pionsNonTries.size()<6){
            try {
                Thread.sleep(60);
            } catch (InterruptedException ex) {
                Logger.getLogger(IhmOpenGL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i=0;i<c.size();i++){
            Carreau ca = c.get(Integer.toString(i));
            if (ca instanceof Propriete){
                this.afficherMaisons((Propriete)ca);
            }
            int nb = 0;
            for(Joueur j:ca.getJoueurs()){
                this.afficherPion(j,nb);
                nb++;
            }
        }
    }

    public void afficherPion(Joueur j,int nb){
        double x = 0;double z = 0;
        Rectangle r0 = Plateau.numCarreauToCoords(j.getPositionCourante().getNumero());
        toolbox.Rectangle r = new toolbox.Rectangle(r0.getX0(),r0.getX1(),r0.getY0(),r0.getY1());
        r.scale(1.0/400,new Point(400,400));
        r.moveCentre(new Point(-400,-400));
        System.out.println(r.getCentre().toString());
        if(j.getPositionCourante().getNumero()==10){
            x=-0.95;
            z=-0.95;
        }else if(j.getPositionCourante().getNumero()==40){
            
        }else{
            //x = (0.75-((double)(nb%2))*0.5)*(r.getX0()+r.getX1());
            x=(0.25+0.5*(nb%2))*(r.getX1()-r.getX0())+r.getX0();
            //z = (0.75-((double)(nb%2))*0.5)*(r.getY0()+r.getY1());
            //z = (r.getY0()/4.0*(1.0+2.0*(nb%2)))+(r.getY1()/4.0*(1.0+(2.0*(nb+1)%2)));
            z=(0.25+0.5*(nb%2))*(r.getY1()-r.getY0())+r.getY0();
        }
        Entity pion = this.getPion(j);
        pion.setPosition((float) x,0, (float) z);
        pion.setRotY(90*(j.getPositionCourante().getNumero()/10));
    }
    
    private synchronized void afficherMaisons(Propriete propriete) {
        int numCarreau = propriete.getNumero();
        double x = 0,y = 0;
        for (int i=0;i<propriete.getNbMaisons();i++){//p.getNbMaisons()
            if (numCarreau<10 && numCarreau>0){
                x=this.width-(LARGEUR_PETITE_CASE*(2+numCarreau))+(LARGEUR_PETITE_CASE/8)*(1+2*i);
                y=this.height-HAUTEUR_CASE+LARGEUR_COULEUR/2;
            }else if (numCarreau<20 && numCarreau>10){
                x=HAUTEUR_CASE-LARGEUR_COULEUR/2;
                y=this.height-(numCarreau-10+2)*(LARGEUR_PETITE_CASE)+(LARGEUR_PETITE_CASE/8)*(1+2*i);
            }else if (numCarreau<30 && numCarreau>20){
                x=(numCarreau-20+2)*(LARGEUR_PETITE_CASE)-(LARGEUR_PETITE_CASE/8)*(1+2*i);
                y=HAUTEUR_CASE-LARGEUR_COULEUR/2;
            }else if (numCarreau<40 && numCarreau>30){
                x=this.width-HAUTEUR_CASE+LARGEUR_COULEUR/2;
                y=(numCarreau-30+2)*(LARGEUR_PETITE_CASE)-(LARGEUR_PETITE_CASE/8)*(1+2*i);
            }
            x-=1;y-=1;
            this.maisons.add(new Entity(this.tMMaison,new Vector3f((float)x,this.hauteurMaison,(float)y),0, -90*(numCarreau/10), 0,this.sizeMaison, false));
            //Questions.affiche("x : "+x+"   y : "+y);
        }
    }
    
    private Entity getPion(Joueur j){
        if(!this.pions.containsKey(j.getNomJoueur())){
            this.assignerPion(j);
            this.entities.add(this.pions.get(j.getNomJoueur()));
        }
        return this.pions.get(j.getNomJoueur());
    }
    
    private void assignerPion(Joueur j){
        TypePions t = this.ihm3d.askTypePion(j.getIndicePion()); // LOLOLOLOLOLOLOLOLOLOLOLOLKARIM
        Entity p;
        int i = -1;
        switch(t){
            case Banane: i=1; break;
            case Hamburger: i=0; break;
            case Canette: i=2;break;
            case Horloge: i=3;break;
            case Telephone: i=4;break;
            case Portal: i=5;break;
        }
        this.pions.put(j.getNomJoueur(), this.pionsNonTries.get(i));
    }
    
    public int getCarreauSelected(){
        int c = this.coordsToNumCase(this.cursor.getPosition().x, this.cursor.getPosition().z);
        if(c==-1){
            //System.out.println(this.cursor.getPosition().x+" "+this.cursor.getPosition().z);
            toolbox.Rectangle rcom = new toolbox.Rectangle(-2,2,1,-1);
            rcom.scale(this.cartesCComm.getScale());
            rcom.rotate(-Math.toRadians(this.cartesCComm.getRotY()));
            rcom.moveCentre(new Point(-this.cartesCComm.getPosition().x,-this.cartesCComm.getPosition().z));
            //System.out.println(rcom.toString());
            Point[] points = rcom.getFourPoints();
            double[] vertx = new double[4];
            double[] verty = new double[4];
            for ( int i = 0; i<4;i++){
                vertx[i] = points[i].getX();
                verty[i] = points[i].getY();
            }
            if(Maths.pnpoly(vertx, verty, this.cursor.getPosition().x, this.cursor.getPosition().z)){
                System.out.println("c = caisse comm");
                c=CARTES_COMMUNAUTE;
            }else{
                toolbox.Rectangle rchance = new toolbox.Rectangle(-2,2,1,-1);
                rchance.scale(this.cartesChance.getScale());
                rchance.rotate(-Math.toRadians(this.cartesChance.getRotY()));
                rchance.moveCentre(new Point(-this.cartesChance.getPosition().x,-this.cartesChance.getPosition().z));
                points = rchance.getFourPoints();
                vertx = new double[4];
                verty = new double[4];
                for ( int i = 0; i<4;i++){
                    vertx[i] = points[i].getX();
                    verty[i] = points[i].getY();
                }
                if(Maths.pnpoly(vertx, verty, this.cursor.getPosition().x, this.cursor.getPosition().z)){
                    c=CARTES_CHANCE;
                }
            }
            
            
        }
        return c;
    }
    
}
