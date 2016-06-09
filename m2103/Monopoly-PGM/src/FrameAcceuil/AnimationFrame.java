/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameAcceuil;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author root
 */
public class AnimationFrame {
    
    public static void LeftToRight(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(-850, 0);
                nouveau.revalidate();
                for(int i=0;i<850;i++){
                    nouveau.setLocation(nouveau.getX()+1, nouveau.getY());
                    nouveau.revalidate();
                    ancien.setLocation(ancien.getX()+1, ancien.getY());
                    ancien.revalidate();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public static void RightToLeft(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(850, 0);
                nouveau.revalidate();
                for(int i=0;i<850;i++){
                    nouveau.setLocation(nouveau.getX()-1, nouveau.getY());
                    nouveau.revalidate();
                    ancien.setLocation(ancien.getX()-1, ancien.getY());
                    ancien.revalidate();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    public static void DowntoUp(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(0, 600);
                nouveau.revalidate();
                for(int i=0;i<600;i++){
                    nouveau.setLocation(nouveau.getX(), nouveau.getY()-1);
                    nouveau.revalidate();
                    ancien.setLocation(ancien.getX(), ancien.getY()-1);
                    ancien.revalidate();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    public static void UptoDown(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(0, -600);
                nouveau.revalidate();
                for(int i=0;i<600;i++){
                    nouveau.setLocation(nouveau.getX(), nouveau.getY()+1);
                    nouveau.revalidate();
                    ancien.setLocation(ancien.getX(), ancien.getY()+1);
                    ancien.revalidate();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    public static void LeftToRight(final JComponent nouveau){
                nouveau.setLocation(-850, nouveau.getY());
                nouveau.revalidate();
                for(int i=0;i<850;i++){
                    nouveau.setLocation(nouveau.getX()+1, nouveau.getY());
                    nouveau.revalidate();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public static void RightToLeft(final JComponent nouveau){
                nouveau.setLocation(0, nouveau.getY());
                nouveau.revalidate();
                for(int i=0;i<850;i++){
                    nouveau.setLocation(nouveau.getX()-1, nouveau.getY());
                    nouveau.revalidate();
                    if(i%2==0){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
    }
}
