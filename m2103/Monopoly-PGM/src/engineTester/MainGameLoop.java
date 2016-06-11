/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import IHM.Plateau;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Cube;
import entities.Player;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.LWJGLException;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 *
 * @author Louis
 */
public class MainGameLoop {
    
    
    public static void main(String[] args){
        
        Loader loader = new Loader();

        DisplayManager.createDisplay();

        List<Entity> entities = new ArrayList<>();
        // skybox
        ModelTexture textureSkybox = new ModelTexture(loader.loadTexture("skybox3"));
        ModelData dataSkybox = OBJFileLoader.loadOBJ("skybox1");
        RawModel modelSkybox = loader.loadToVAO(dataSkybox.getVertices(), dataSkybox.getTextureCoords(),dataSkybox.getNormals() , dataSkybox.getIndices());
        TexturedModel tMSkybox = new TexturedModel(modelSkybox, textureSkybox);
        Entity skybox = new Entity(tMSkybox, new Vector3f(0,0,0), 0, 0, 0, 100, true);
        tMSkybox.getTexture().setUseFakeLighting(true);
        entities.add(skybox);
        
        // maison
        ModelTexture textureMaison = new ModelTexture(loader.loadTexture("maison"));
        ModelData dataMaison = OBJFileLoader.loadOBJ("maison");
        RawModel modelMaison = loader.loadToVAO(dataMaison.getVertices(), dataMaison.getTextureCoords(),dataMaison.getNormals() , dataMaison.getIndices());
        TexturedModel tMMaison = new TexturedModel(modelMaison, textureMaison);
        Entity maison = new Entity(tMMaison, new Vector3f(0,0.015f,0), 0, 0, 0, 0.005f, true);
        entities.add(maison);
        
        // plateau
        ModelTexture texturePlateau = new ModelTexture(loader.loadTexture("banana"));
        ModelData dataPlateau = OBJFileLoader.loadOBJ("banana");
        RawModel modelPlateau = loader.loadToVAO(dataPlateau.getVertices(), dataPlateau.getTextureCoords(),dataPlateau.getNormals() , dataPlateau.getIndices());
        TexturedModel tMPlateau = new TexturedModel(modelPlateau, texturePlateau);
        Entity plateau = new Entity(tMPlateau, new Vector3f(0,0,0), 0, 0, 0, 1, true);
        entities.add(plateau);
        
        
        //cartes caisse de communauté
        ModelTexture textureCartes = new ModelTexture(loader.loadTexture("cartes"));
        ModelData dataCartesChance = OBJFileLoader.loadOBJ("cartesCCom");
        RawModel modelCartesChance = loader.loadToVAO(dataCartesChance.getVertices(), dataCartesChance.getTextureCoords(),dataCartesChance.getNormals() , dataCartesChance.getIndices());
        TexturedModel tMCartesChance = new TexturedModel(modelCartesChance, textureCartes);
        Entity cartesChance = new Entity(tMCartesChance, new Vector3f(0.3f,0,0.3f), 0, -135, 0, 0.1f, true);
        entities.add(cartesChance);
        
        
        // cartes chance
        ModelData dataCartesCComm = OBJFileLoader.loadOBJ("cartesChance");
        RawModel modelCartesCComm = loader.loadToVAO(dataCartesCComm.getVertices(), dataCartesCComm.getTextureCoords(),dataCartesCComm.getNormals() , dataCartesCComm.getIndices());
        TexturedModel tMCartesCComm = new TexturedModel(modelCartesCComm, textureCartes);
        Entity cartesCComm = new Entity(tMCartesCComm, new Vector3f(-0.3f,0,-0.3f), 0, 45, 0, 0.1f, true);
        entities.add(cartesCComm);
        
        // test entity
        Cube dataCursor = new Cube(31,1);
        RawModel modelcursor = loader.loadToVAO(dataCursor.getVertices(), dataCursor.getTextureCoords(),dataCursor.getNormals() , dataCursor.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("terrain"));
        TexturedModel texturedModelCursor = new TexturedModel(modelcursor, texture);
        Entity cursor=new Entity(texturedModelCursor,new Vector3f(0,0,0),0,0,0,1,true);
        
        
        Light light = new Light(new Vector3f(-20000,20000,20000),new Vector3f(1,1,1));
        //Mouse.setGrabbed(true);
        Player player = new Player(7,7,7,0,0,0,"Louis",loader);
        MasterRenderer renderer = new MasterRenderer();
        Camera camera = new Camera(player,renderer);
        camera.setTest(cursor);
        camera.setPosition(15,5,15);
        
        while(!Display.isCloseRequested()){
            camera.move();
            skybox.setPosition(player.getX(), player.getY(), player.getZ());
            for (Entity tmp1:entities){
                    //tmp1.setPosition(0,0,tmp1.getPosition().getZ()+0.01f);
                    //tmp1.setRotX(tmp1.getRotX()+1);
                    //tmp1.setRotY(tmp1.getRotY()+1);
                    renderer.processEntity(tmp1);
                }
            //renderer.processTerrain(terrain2);
            renderer.render(light,camera);

            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
    
    public static int coordsToNumCase(float x, float y){
        return Plateau.coordsToNumCase((int)((x+1)/2*Plateau.width),(int)((y+1)/2*Plateau.height));
    }
}
