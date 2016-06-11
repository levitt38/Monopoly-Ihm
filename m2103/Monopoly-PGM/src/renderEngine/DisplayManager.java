/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author Louis
 */
public class DisplayManager {
    
    private static final int WIDTH = 1280;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;
    
    public static void createDisplay(){
        
        ContextAttribs attribs = new ContextAttribs(3,2)
        .withForwardCompatible(true)
        .withProfileCore(true);
        /*
        DisplayMode[] modes = {new DisplayMode(WIDTH,HEIGHT)} ;
        try {
            modes = Display.getAvailableDisplayModes();
        } catch (LWJGLException ex) {
            Logger.getLogger(DisplayManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        DisplayMode mode = modes[0];
        for (DisplayMode tmp:modes){
            if (tmp.getWidth()==1600){
                mode=tmp;
            }
        }
        
        */
        
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            //Display.setDisplayMode(mode);
            //Display.setFullscreen(true);
            Display.create(new PixelFormat().withSamples(4).withDepthBits(24), attribs);
            Display.setTitle("Xtrem Monopoly");
        } catch (LWJGLException ex) {
            Logger.getLogger(DisplayManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        
    }
    
    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }
    
    public static void closeDisplay(){
        Display.destroy();
    }
}
