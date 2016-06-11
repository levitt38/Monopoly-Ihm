/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameAcceuil;

import IHM.FrameJeu;
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
                int xnew = -850;
                int xold = 0;
                nouveau.revalidate();
                for(int i=0;i<170;i++){
                    xnew += 5; xold += 5;
                    nouveau.setLocation(xnew, 0);
                    nouveau.revalidate();
                    ancien.setLocation(xold, 0);
                    ancien.revalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
    }
    
    public static void RightToLeft(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(850, 0);
                int xnew = 850;
                int xold = 0;
                nouveau.revalidate();
                for(int i=0;i<170;i++){
                    xnew -= 5; xold -= 5;
                    nouveau.setLocation(xnew, 0);
                    nouveau.revalidate();
                    ancien.setLocation(xold, 0);
                    ancien.revalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
    }
    
    public static void DowntoUp(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(0, 600);
                nouveau.revalidate();
                for(int i=0;i<100;i++){
                    nouveau.setLocation(0, nouveau.getY()-6);
                    nouveau.revalidate();
                    ancien.setLocation(0, ancien.getY()-6);
                    ancien.revalidate();
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    public static void UptoDown(final JComponent nouveau, final JComponent ancien){
                nouveau.setLocation(0, -600);
                nouveau.revalidate();
                for(int i=0;i<100;i++){
                    nouveau.setLocation(0, nouveau.getY()+6);
                    nouveau.revalidate();
                    ancien.setLocation(0, ancien.getY()+6);
                    ancien.revalidate();
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
    }
    
    public static void LeftToRight(final JComponent nouveau){
                nouveau.setLocation(-850, nouveau.getY());
                nouveau.revalidate();
                for(int i=0;i<170;i++){
                    nouveau.setLocation(nouveau.getX()+5, nouveau.getY());
                    nouveau.revalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public static void RightToLeft(final JComponent nouveau){
                if(nouveau.getX()==-850){
                    return;
                }
                nouveau.setLocation(0, nouveau.getY());
                nouveau.revalidate();
                for(int i=0;i<170;i++){
                    nouveau.setLocation(nouveau.getX()-5, nouveau.getY());
                    nouveau.revalidate();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
    }
    
    public static void AnimSelectPerso(final JComponent nouveau){
                nouveau.setLocation(nouveau.getX(), -300);
                nouveau.revalidate();
                for(int i=0;i<150;i++){
                    nouveau.setLocation(nouveau.getX(), nouveau.getY()+2);
                    nouveau.revalidate();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
    }
    
    public static void AnimSelectPersoReverse(final JComponent nouveau){
                if(nouveau.getY()==-300){
                    return;
                }
                nouveau.revalidate();
                for(int i=0;i<150;i++){
                    nouveau.setLocation(nouveau.getX(), nouveau.getY()-2);
                    nouveau.revalidate();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AnimationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
    }
    
    public void afficherMenu(final JComponent nouveau){
        nouveau.setLocation(-300, nouveau.getY());
        nouveau.revalidate();
        for(int i=0;i<100;i++){
            nouveau.setLocation(nouveau.getX()+3, nouveau.getY());
            nouveau.revalidate();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void cacherMenu(final JComponent nouveau){
        nouveau.setLocation(0, nouveau.getY());
        nouveau.revalidate();
        for(int i=0;i<100;i++){
            nouveau.setLocation(nouveau.getX()-3, nouveau.getY());
            nouveau.revalidate();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void afficherMenuAction(final JComponent nouveau){
        nouveau.setLocation(1280, nouveau.getY());
        nouveau.revalidate();
        for(int i=0;i<100;i++){
            nouveau.setLocation(nouveau.getX()-4, nouveau.getY());
            nouveau.revalidate();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void cacherMenuAction(final JComponent nouveau){
        nouveau.setLocation(880, nouveau.getY());
        nouveau.revalidate();
        for(int i=0;i<100;i++){
            nouveau.setLocation(nouveau.getX()+4, nouveau.getY());
            nouveau.revalidate();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
