/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Jeu.Carreau;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Louis
 */
public class FenetreJeu extends JFrame {

    private Plateau plateau;
    private JPanel panel;
    private int width = 800;
    private int height = 800;
    
    public FenetreJeu() {
        super("Monopoly");
        /*panel = new JPanel();
        panel.setPreferredSize(new Dimension(this.width,this.height));
        this.panel.add(this.plateau);
        this.add(this.panel);
        this.pack();*/
        this.plateau = new Plateau(width,height);
        this.add(this.plateau);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        //setSize(800, 800);

    }

    public void afficher() {
        setSize(800, 800);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public Plateau getPlateau() {
        return plateau;
    }
    
    public void afficherPlateau(HashMap<String, Carreau> c){
        this.getPlateau().setCarreaux(c);
        this.getPlateau().repaint();
    }
}
