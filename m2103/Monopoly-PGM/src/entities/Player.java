/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import textures.ModelTexture;

/**
 *
 * @author louis
 */
public class Player {
    private Entity entity;
    private String name;
    private final float sensitivity = 0.2f;
    private final float speed = 0.1f;
    
    public Player(float posx,float posy,float posz,float rotx,float roty,float rotz,String name,Loader loader){
        Cube dataPlayer = new Cube(31,1);
        RawModel modelPlayer = loader.loadToVAO(dataPlayer.getVertices(), dataPlayer.getTextureCoords(),dataPlayer.getNormals() , dataPlayer.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("terrain"));
        TexturedModel texturedModelPlayer = new TexturedModel(modelPlayer, texture);
        Vector3f position = new Vector3f(posx,posy,posz);
        entity=new Entity(texturedModelPlayer,position,rotx,roty,rotz,1,true);
        this.name=name;
    }
    
    public Entity getEntity(){
        return entity;
    }
    public void moveYaw(float rot){
        entity.setRotY(getYaw()+rot);
    }
    
    public void movePitch(float rot){
        entity.setRotX(getPitch()+rot);
        
    }
    
    public void setRotZ(float rot){
        
    }
    
    public void move(float x,float y,float z){
        setPosition(getX()+x,getY()+y,getZ()+z);
    }
    
    public Vector3f getPosition() {
        return entity.getPosition();
    }

    public float getPitch() {
        return entity.getRotX();
    }

    public void setYaw(float f){
        this.entity.setRotY(f);
    }
    
    public float getX(){
        return entity.getPosition().x;
    }
    
    public float getY(){
        return entity.getPosition().y;
    }
    
    public float getZ(){
        return entity.getPosition().z;
    }
    
    public float getYaw() {
        return entity.getRotY();
    }

    public float getRoll() {
        return 5;
    }
    
    public void setPosition(float x, float y, float z){
        entity.setPosition(x,y,z);
    }

    void setPitch(float f) {
        this.getEntity().setRotX(f);
    }
}
