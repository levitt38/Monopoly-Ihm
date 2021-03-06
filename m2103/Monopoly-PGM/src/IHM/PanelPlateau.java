
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Jeu.Carreau;
import java.awt.Canvas;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Louis
 */
public class PanelPlateau extends JPanel{
    boolean swing = false;
    private Canvas c = new Canvas();
    private IhmOpenGL op;
    private Plateau plateau;

    public PanelPlateau() {
    }
 
    public IhmOpenGL getOp() {
        return op;
    }
   
    public void initialiserPlateau(Ihm3d ihm3d){
        try {
            this.add(c);
            c.setSize(1280,720);
            Display.setParent(c);
            op = new IhmOpenGL(ihm3d);
        } catch (LWJGLException ex) {
            this.swing = true;
            this.plateau = new Plateau(800,800);
        }
        
    }

    public Plateau getPlateau() {
        return plateau;
    }
    
    public void rafraîchirPlateau(HashMap<String, Carreau> c){
        if(this.swing){
            this.getPlateau().setCarreaux(c);
            this.getPlateau().repaint();
        }else{
            op.afficherPlateau(c);
        }
    }
    
    
    
}