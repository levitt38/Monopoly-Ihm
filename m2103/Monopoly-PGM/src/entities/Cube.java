/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import models.TexturedModel;
import objConverter.ModelData;

/**
 *
 * @author louis
 */
public class Cube {
    private TexturedModel texturedModel;
    private ModelData model;
    
    public Cube(int all, int id){
        this(all,all,all,all,all,all,id);
    }
    public Cube(int up, int down, int north, int south, int east, int west, int id){
        float[] vertices = {1.0f,0.0f,0.0f,1.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,1.0f,1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,1.0f,1.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,1.0f,1.0f,1.0f,0.0f,1.0f,1.0f,0.0f,0.0f,1.0f,1.0f,0.0f,0.0f,1.0f,1.0f,0.0f,1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,1.0f,1.0f,1.0f,1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,0.0f,1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,0.0f,1.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f};
        float[] normals = {0.0f,0.0f,-1.0f,0.0f,0.0f,1.0f,-1.0f,0.0f,0.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,1.0f,-1.0f,0.0f,0.0f,0.0f,0.0f,-1.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f};
        int[] indices = {4,0,3,4,3,7,2,6,8,2,8,9,1,5,10,11,12,13,14,15,16,17,18,19,20,21,22,21,23,22,24,25,26,24,26,27};
        float[] textures = {
            (north%16)/16f,//bas gauche north
            ((north/16)+1)/16f,//bas gauche north y
            (south%16+1)/16f,//bas droite sud
            ((south/16)+1)/16f,//bas droite sud y
            (west%16+1)/16f,//bas droite west
            ((west/16)+1)/16f,//bas droite west y
            (north%16+1)/16f,//bas droite north
            ((north/16)+1)/16f,//bas droite north y
            (north%16)/16f,//haut gauche north
            ((north/16))/16f,//haut gauche north y
            (south%16+1)/16f,//haut droite sud
            ((south/16))/16f,//haut droite sud y
            (west%16+1)/16f,//haut droite west
            ((west/16))/16f,//haut droite west y
            (north%16+1)/16f,//haut droite north
            ((north/16))/16f,//haut droite north y
            (west%16)/16f,//haut gauche west
            ((west/16))/16f,//haut droite west y
            (west%16)/16f,//bas gauche west
            ((west/16)+1)/16f,//bas gauche west y
            (south%16)/16f,//bas gauche sud
            ((south/16)+1)/16f,//bas gauche sud y
            (south%16+1)/16f,//haut droite sud
            ((south/16))/16f,//haut droite sud y
            (south%16)/16f,//haut gauche sud
            ((south/16))/16f,//haut gauche sud y
            (south%16)/16f,//bas gauche sud
            ((south/16)+1)/16f,//bas gauche sud y
            (east%16+1)/16f,//bas droite east
            ((east/16)+1)/16f,//bas droite east y
            (east%16+1)/16f,//haut droite east
            ((east/16))/16f,//haut droite east y
            (east%16)/16f,//bas gauche east
            ((east/16)+1)/16f,//bas gauche east y
            (east%16+1)/16f,//haut droite east
            ((east/16))/16f,//haut droite east y
            (east%16)/16f,//haut gauche east
            ((east/16))/16f,//haut gauche east y
            (east%16)/16f,//bas gauche east
            ((east/16)+1)/16f,//bas gauche east y
            (up%16+1)/16f,//haut droite up
            ((up/16))/16f,//haut droite up y
            (up%16)/16f,//haut gauche up
            ((up/16))/16f,//haut gauche up y
            (up%16+1)/16f,//bas droite up
            ((up/16)+1)/16f,//bas droite up y
            (up%16)/16f,//bas gauche up
            ((up/16)+1)/16f,//bas gauche up y
            (down%16+1)/16f,//bas droite down
            ((down/16)+1)/16f,//bas droite down y
            (down%16+1)/16f,//haut droite down
            ((down/16))/16f,//haut droite down y
            (down%16)/16f,//haut gauche down
            ((down/16))/16f,//haut gauche down y
            (down%16)/16f,//bas gauche down
            ((down/16+1))/16f};//bas gauche down y
        model = new ModelData(vertices,textures,normals,indices);
    }
    
    
	public float[] getVertices() {
		return model.getVertices();
	}

	public float[] getTextureCoords() {
		return model.getTextureCoords();
	}

	public float[] getNormals() {
		return model.getNormals();
	}

	public int[] getIndices() {
		return model.getIndices();
	}

}
