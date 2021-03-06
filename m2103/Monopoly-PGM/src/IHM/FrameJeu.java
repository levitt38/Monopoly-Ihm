/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import FrameAccueil.AnimationFrame;
import Jeu.Carreau;
import Jeu.Joueur;
import java.awt.Frame;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Louis
 */
public class FrameJeu extends javax.swing.JFrame {
    private int XDrag, YDrag;
    private int width = 800;
    private int height = 800;
    private int num_carreau;
    private boolean carreauAffiche = false, carreauSelectionné = false, joueurAffiche = false, carteChanceSelectionnée = false, carteCommunauteSelectionnée = false;
    private HashMap<String, Carreau> plateau;
    private boolean YesNoSaisi, YesNoChoix;
    private Ihm3d ihm;
    
    public FrameJeu(Ihm3d ihm3d) {              
        this.ihm = ihm3d;
        initComponents();
        //this.panelPlateau1.setIhm3d(ihm3d);
        this.YesNoSaisi = false; // ce boolean permet d'attendre un input utilisateur Yes/No
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.cacherBoutons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BarreVerticale = new javax.swing.JPanel();
        BExit = new javax.swing.JButton();
        BMin = new javax.swing.JButton();
        FondBarre = new javax.swing.JLabel();
        PanelAction = new javax.swing.JPanel();
        TextAction = new javax.swing.JLabel();
        BActionNon = new javax.swing.JButton();
        BActionOui = new javax.swing.JButton();
        FondAction = new javax.swing.JLabel();
        PanelCarreau = new javax.swing.JPanel();
        TextCarreau = new javax.swing.JLabel();
        ExitCarreau = new javax.swing.JButton();
        FondCarreau = new javax.swing.JLabel();
        PanelJoueur = new javax.swing.JPanel();
        TextJoueur = new javax.swing.JLabel();
        ExitJoueur = new javax.swing.JButton();
        FondJoueur = new javax.swing.JLabel();
        panelPlateau1 = new IHM.PanelPlateau();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        BarreVerticale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarreVerticaleMousePressed(evt);
            }
        });
        BarreVerticale.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarreVerticaleMouseDragged(evt);
            }
        });
        BarreVerticale.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BExit.setBorderPainted(false);
        BExit.setContentAreaFilled(false);
        BExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BExitActionPerformed(evt);
            }
        });
        BarreVerticale.add(BExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 7, 25, 20));

        BMin.setBorderPainted(false);
        BMin.setContentAreaFilled(false);
        BMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMinActionPerformed(evt);
            }
        });
        BarreVerticale.add(BMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 44, 25, 20));

        FondBarre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BarreVertical.png"))); // NOI18N
        BarreVerticale.add(FondBarre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 720));

        getContentPane().add(BarreVerticale);
        BarreVerticale.setBounds(0, 0, 40, 720);

        PanelAction.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextAction.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextAction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TextAction.setText("Bienvenue dans la partie");
        TextAction.setIconTextGap(10);
        PanelAction.add(TextAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 120));

        BActionNon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonNon.png"))); // NOI18N
        BActionNon.setBorderPainted(false);
        BActionNon.setContentAreaFilled(false);
        BActionNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionNonActionPerformed(evt);
            }
        });
        PanelAction.add(BActionNon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 100, 53));

        BActionOui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonOui.png"))); // NOI18N
        BActionOui.setBorderPainted(false);
        BActionOui.setContentAreaFilled(false);
        BActionOui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionOuiActionPerformed(evt);
            }
        });
        PanelAction.add(BActionOui, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 100, 53));

        FondAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelAction.png"))); // NOI18N
        PanelAction.add(FondAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(PanelAction);
        PanelAction.setBounds(1280, 0, 400, 200);

        PanelCarreau.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextCarreau.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextCarreau.setForeground(new java.awt.Color(204, 204, 204));
        TextCarreau.setText("...............");
        PanelCarreau.add(TextCarreau, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 240, 200));

        ExitCarreau.setBorderPainted(false);
        ExitCarreau.setContentAreaFilled(false);
        ExitCarreau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitCarreauActionPerformed(evt);
            }
        });
        PanelCarreau.add(ExitCarreau, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 5, 40, 40));

        FondCarreau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelCarreau2.png"))); // NOI18N
        PanelCarreau.add(FondCarreau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(PanelCarreau);
        PanelCarreau.setBounds(-300, 360, 300, 360);

        PanelJoueur.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextJoueur.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextJoueur.setForeground(new java.awt.Color(204, 204, 204));
        TextJoueur.setText("............");
        PanelJoueur.add(TextJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 240, 200));

        ExitJoueur.setBorderPainted(false);
        ExitJoueur.setContentAreaFilled(false);
        ExitJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitJoueurActionPerformed(evt);
            }
        });
        PanelJoueur.add(ExitJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 5, 40, 40));

        FondJoueur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelJoueur2.png"))); // NOI18N
        PanelJoueur.add(FondJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(PanelJoueur);
        PanelJoueur.setBounds(-300, 0, 300, 360);

        javax.swing.GroupLayout panelPlateau1Layout = new javax.swing.GroupLayout(panelPlateau1);
        panelPlateau1.setLayout(panelPlateau1Layout);
        panelPlateau1Layout.setHorizontalGroup(
            panelPlateau1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        panelPlateau1Layout.setVerticalGroup(
            panelPlateau1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        getContentPane().add(panelPlateau1);
        panelPlateau1.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BActionNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionNonActionPerformed
        this.YesNoChoix = false; //le choix est non
        this.YesNoSaisi = true; //continue le programme
        this.cacherAction();
    }//GEN-LAST:event_BActionNonActionPerformed

    private void BActionOuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionOuiActionPerformed
        this.YesNoChoix = true; //le choix est non
        this.YesNoSaisi = true; //continue le programme
        this.cacherAction();
    }//GEN-LAST:event_BActionOuiActionPerformed

    private void ExitJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitJoueurActionPerformed
        new Thread(){
            public void run(){
                joueurAffiche = false;
                AnimationFrame.cacherMenu(PanelJoueur);
            }
        }.start();
        
    }//GEN-LAST:event_ExitJoueurActionPerformed

    private void ExitCarreauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitCarreauActionPerformed
        new Thread(){
            public void run(){
                carreauAffiche = false;
                AnimationFrame.cacherMenu(PanelCarreau);
            }
        }.start();
    }//GEN-LAST:event_ExitCarreauActionPerformed

    private void BMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMinActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_BMinActionPerformed

    private void BExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BExitActionPerformed

    private void BarreVerticaleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarreVerticaleMousePressed
        this.XDrag = evt.getX();
        this.YDrag = evt.getY();
    }//GEN-LAST:event_BarreVerticaleMousePressed

    private void BarreVerticaleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarreVerticaleMouseDragged
        this.setLocation(evt.getXOnScreen()-this.XDrag, evt.getYOnScreen()-this.YDrag);
    }//GEN-LAST:event_BarreVerticaleMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameJeu(new Ihm3d());
            }
        });
    }
    
    public void mouseLoop(){
        while(true){
            int x = Mouse.getX();
            if(x<20&&x>0&&!this.carreauAffiche){
                this.carreauAffiche = true;
                new Thread(){
                    public void run(){
                        //afficherMenu();
                    }
                }.start();

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void menuLoop(){
        while(true){
            if(this.panelPlateau1!=null&&this.panelPlateau1.getOp()!=null&&this.panelPlateau1.getOp().isLoadingCompleted()&&Mouse.isButtonDown(0)){
                this.num_carreau = this.panelPlateau1.getOp().getCarreauSelected();
                if(this.plateau!=null&&this.num_carreau>=0){
                    this.ihm.afficherCarreau(this.plateau.get(Integer.toString(this.num_carreau)));
                    this.carreauSelectionné = true; //recup construction
                }
                // la suite est juste du test, elle sera remove
                else if(this.plateau!=null&&(this.num_carreau==IhmOpenGL.CARTES_CHANCE||this.num_carreau==IhmOpenGL.CARTES_COMMUNAUTE)){
                    if(!this.carreauAffiche){               //          __
                        this.carreauAffiche = true;  
                        this.carteChanceSelectionnée = true;//               __/o \__      
                        new Thread(){                       //       \____   \     
                            public void run(){              //     __   //\   \    
                                //afficherMenu();           //  __/o \-//--\   \_/ 
                            }                               //  \____  ___  \  |   
                        }.start();                          //       ||   \ |\ |   
                    }                                       //      _||   _||_||
                    String s = (this.num_carreau==IhmOpenGL.CARTES_CHANCE) ? "Tas de cartes chance" : "Tas de cartes caisse de communauté";
                    //this.afficherStr(s);
                }
                else if(this.plateau!=null&&(this.num_carreau==IhmOpenGL.CARTES_CHANCE||this.num_carreau==IhmOpenGL.CARTES_COMMUNAUTE)){
                    if(!this.carreauAffiche){               //          __
                        this.carreauAffiche = true;  
                        this.carteCommunauteSelectionnée = true;//               __/o \__      
                        new Thread(){                       //       \____   \     
                            public void run(){              //     __   //\   \    
                                //afficherMenu();           //  __/o \-//--\   \_/ 
                            }                               //  \____  ___  \  |   
                        }.start();                          //       ||   \ |\ |   
                    }                                       //      _||   _||_||
                    String s = (this.num_carreau==IhmOpenGL.CARTES_CHANCE) ? "Tas de cartes chance" : "Tas de cartes caisse de communauté";
                    //this.afficherStr(s);
                }
                 // permet de recup un choix pour une demande de construction
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void afficher() {
        setSize(1280, 720);
        setLocationRelativeTo(null); 
        setVisible(true);
        this.panelPlateau1.initialiserPlateau(this.ihm);
        new Thread(){
            public void run(){
                mouseLoop();
            }
        }.start();
        
        new Thread(){
            public void run(){
                menuLoop();
            }
        }.start();
    }

    public void afficherPlateau(final HashMap<String, Carreau> c){
        this.plateau = c;
        new Thread(){
                public void run(){
                    panelPlateau1.rafraîchirPlateau(c);
                    //this.panelPlateau1.getOp().afficherPlateau(c);
                }
        }.start();
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActionNon;
    private javax.swing.JButton BActionOui;
    private javax.swing.JButton BExit;
    private javax.swing.JButton BMin;
    private javax.swing.JPanel BarreVerticale;
    private javax.swing.JButton ExitCarreau;
    private javax.swing.JButton ExitJoueur;
    private javax.swing.JLabel FondAction;
    private javax.swing.JLabel FondBarre;
    private javax.swing.JLabel FondCarreau;
    private javax.swing.JLabel FondJoueur;
    private javax.swing.JPanel PanelAction;
    private javax.swing.JPanel PanelCarreau;
    private javax.swing.JPanel PanelJoueur;
    private javax.swing.JLabel TextAction;
    private javax.swing.JLabel TextCarreau;
    private javax.swing.JLabel TextJoueur;
    private IHM.PanelPlateau panelPlateau1;
    // End of variables declaration//GEN-END:variables

    public JLabel getTextAction() {
        return TextAction;
    }

    public JLabel getTextCarreau() {
        return TextCarreau;
    }

    public JLabel getTextJoueur() {
        return TextJoueur;
    }

    public boolean isYesNoSaisi() {
        return YesNoSaisi;
    }

    public void setYesNoSaisi(boolean YesNoSaisi) {
        this.YesNoSaisi = YesNoSaisi;
    }
    
    public boolean isYesNoChoix() {
        return YesNoChoix;
    }

    public JPanel getPanelAction() {
        return PanelAction;
    }

    public JPanel getPanelCarreau() {
        return PanelCarreau;
    }

    public JPanel getPanelJoueur() {
        return PanelJoueur;
    }

    public int getNum_carreau() {
        return num_carreau;
    }

    public boolean isCarreauSelectionné() {
        return carreauSelectionné;
    }

    public void setCarreauSelectionné(boolean carreauSelectionné) {
        this.carreauSelectionné = carreauSelectionné;
    }

    public PanelPlateau getPanelPlateau1() {
        return panelPlateau1;
    }

    public boolean isCarteChanceSelectionnée() {
        return carteChanceSelectionnée;
    }

    public boolean isCarteCommunauteSelectionnée() {
        return carteCommunauteSelectionnée;
    }

    public void setCarteChanceSelectionnée(boolean carteChanceSelectionnée) {
        this.carteChanceSelectionnée = carteChanceSelectionnée;
    }

    public void setCarteCommunauteSelectionnée(boolean carteCommunauteSelectionnée) {
        this.carteCommunauteSelectionnée = carteCommunauteSelectionnée;
    }

    
    
    
    
    
 
    public void showAction(){
        new Thread(){
            public void run(){
                
                AnimationFrame.afficherMenuAction(PanelAction);
            }
        }.start();
        
    }
    
    public void cacherAction(){
        new Thread(){
            public void run(){
                AnimationFrame.cacherMenuAction(PanelAction);
            }
        }.start();
    }
    
    public void showJoueur(){
        if(! this.joueurAffiche){
        joueurAffiche = true;
        new Thread(){
            public void run(){
                AnimationFrame.afficherMenu(PanelJoueur);
            }
        }.start();
        }  
    }
    
    public void showCarreau(){
        if(! this.carreauAffiche){
        carreauAffiche = true;
        new Thread(){
            public void run(){
                AnimationFrame.afficherMenu(PanelCarreau);
            }
        }.start();
        }
    }
    
    public void cacherBoutons(){
        this.BActionNon.setVisible(false);
        this.BActionOui.setVisible(true);
    }
    
    public void showBoutons(){
        this.BActionNon.setVisible(true);
        //this.BActionOui.setVisible(true);
    }
   
    public void cacher2Boutons(){
        this.BActionNon.setVisible(false);
        this.BActionOui.setVisible(false);
    }
    
    public void show2Boutons(){
        this.BActionNon.setVisible(true);
        this.BActionOui.setVisible(true);
    }
    
}
