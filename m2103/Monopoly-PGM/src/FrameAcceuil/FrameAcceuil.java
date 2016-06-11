/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameAcceuil;

import IHM.EventHandler;
import IHM.Ihm3d;
import IHM.IhmConsole;
import Jeu.Controleur;
import Network.Client;
import java.awt.Frame;
import java.awt.EventQueue;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author nourik
 */
public class FrameAcceuil extends javax.swing.JFrame {
    private int XDrag,YDrag; // servent a drag la frame
    private HashSet<JTextField> liste_choixPions;
    private Client client; // dans le cas du mode online, un object client est crée

    /**
     * Creates new form FrameAcceuil
     */
    public FrameAcceuil() {
        initComponents();
        setListePions();
        this.setLocationRelativeTo(null);
        this.SlideShow();
    }
    
    public void setListePions(){
        this.liste_choixPions = new HashSet<>();
        this.liste_choixPions.add(this.PersoPortal);
        this.liste_choixPions.add(this.PersoBanane);
        this.liste_choixPions.add(this.PersoHorloge);
        this.liste_choixPions.add(this.PersoHamburger);
        this.liste_choixPions.add(this.PersoTelephone);
        this.liste_choixPions.add(this.PersoCanette);
    }
 
    public void SlideShow(){
        new Thread(){
            public void run(){
                int i=0;
                while(true){
                    switch(i%2){
                        case 0 : try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) { ex.printStackTrace();}
                                AnimationFrame.LeftToRight(FondBis, Fond);
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) { ex.printStackTrace();}
                                AnimationFrame.UptoDown(Fond, FondBis);
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) { ex.printStackTrace();}
                                i ++;
                                break;
                        case 1 : AnimationFrame.RightToLeft(FondBis, Fond);
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) { ex.printStackTrace();}
                                AnimationFrame.DowntoUp(Fond, FondBis);
                                i ++;
                                break;
                    }
                }
               }
       }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupButton = new javax.swing.ButtonGroup();
        PanelBarreHaut = new javax.swing.JPanel();
        BExit = new javax.swing.JButton();
        BMin = new javax.swing.JButton();
        BarreHaut = new javax.swing.JLabel();
        SelectPerso = new javax.swing.JPanel();
        PersoPortal = new javax.swing.JTextField();
        PersoBanane = new javax.swing.JTextField();
        PersoHorloge = new javax.swing.JTextField();
        PersoHamburger = new javax.swing.JTextField();
        PersoTelephone = new javax.swing.JTextField();
        PersoCanette = new javax.swing.JTextField();
        LabelPerso = new javax.swing.JLabel();
        PanelNbJoueur = new javax.swing.JPanel();
        TFnb_joueur = new javax.swing.JTextField();
        ButtonFrameNbJoueurs = new javax.swing.JButton();
        FondNbJoueur = new javax.swing.JLabel();
        PanelReseau = new javax.swing.JPanel();
        TFip = new javax.swing.JTextField();
        TFport = new javax.swing.JTextField();
        ButtonFrameReseau = new javax.swing.JButton();
        FondReseau = new javax.swing.JLabel();
        Buttonframe1 = new javax.swing.JButton();
        TFnom = new javax.swing.JTextField();
        FondTextField = new javax.swing.JLabel();
        RadioOnline = new javax.swing.JRadioButton();
        RadioLocal = new javax.swing.JRadioButton();
        Fond = new javax.swing.JLabel();
        FondBis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(850, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        PanelBarreHaut.setOpaque(false);
        PanelBarreHaut.setLayout(null);

        BExit.setBorderPainted(false);
        BExit.setContentAreaFilled(false);
        BExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BExitActionPerformed(evt);
            }
        });
        PanelBarreHaut.add(BExit);
        BExit.setBounds(800, 10, 20, 20);

        BMin.setBorderPainted(false);
        BMin.setContentAreaFilled(false);
        BMin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMinActionPerformed(evt);
            }
        });
        PanelBarreHaut.add(BMin);
        BMin.setBounds(750, 10, 20, 20);

        BarreHaut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BarreHaut.png"))); // NOI18N
        BarreHaut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BarreHaut.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarreHautMouseDragged(evt);
            }
        });
        BarreHaut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarreHautMousePressed(evt);
            }
        });
        PanelBarreHaut.add(BarreHaut);
        BarreHaut.setBounds(0, 0, 850, 45);

        getContentPane().add(PanelBarreHaut);
        PanelBarreHaut.setBounds(0, 0, 850, 45);

        SelectPerso.setOpaque(false);
        SelectPerso.setLayout(null);

        PersoPortal.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoPortal.setForeground(new java.awt.Color(255, 255, 255));
        PersoPortal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoPortal.setText("1");
        PersoPortal.setBorder(null);
        PersoPortal.setOpaque(false);
        SelectPerso.add(PersoPortal);
        PersoPortal.setBounds(60, 224, 80, 30);

        PersoBanane.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoBanane.setForeground(new java.awt.Color(255, 255, 255));
        PersoBanane.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoBanane.setText("2");
        PersoBanane.setBorder(null);
        PersoBanane.setOpaque(false);
        SelectPerso.add(PersoBanane);
        PersoBanane.setBounds(202, 224, 80, 30);

        PersoHorloge.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoHorloge.setForeground(new java.awt.Color(255, 255, 255));
        PersoHorloge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoHorloge.setText("3");
        PersoHorloge.setBorder(null);
        PersoHorloge.setOpaque(false);
        SelectPerso.add(PersoHorloge);
        PersoHorloge.setBounds(337, 224, 80, 30);

        PersoHamburger.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoHamburger.setForeground(new java.awt.Color(255, 255, 255));
        PersoHamburger.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoHamburger.setText("4");
        PersoHamburger.setBorder(null);
        PersoHamburger.setOpaque(false);
        SelectPerso.add(PersoHamburger);
        PersoHamburger.setBounds(474, 224, 80, 30);

        PersoTelephone.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoTelephone.setForeground(new java.awt.Color(255, 255, 255));
        PersoTelephone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoTelephone.setText("5");
        PersoTelephone.setBorder(null);
        PersoTelephone.setOpaque(false);
        SelectPerso.add(PersoTelephone);
        PersoTelephone.setBounds(600, 224, 80, 30);

        PersoCanette.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        PersoCanette.setForeground(new java.awt.Color(255, 255, 255));
        PersoCanette.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PersoCanette.setText("6");
        PersoCanette.setBorder(null);
        PersoCanette.setOpaque(false);
        SelectPerso.add(PersoCanette);
        PersoCanette.setBounds(717, 224, 80, 30);

        LabelPerso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/SelectPerso.png"))); // NOI18N
        SelectPerso.add(LabelPerso);
        LabelPerso.setBounds(0, 0, 850, 300);

        getContentPane().add(SelectPerso);
        SelectPerso.setBounds(0, -300, 850, 300);

        PanelNbJoueur.setOpaque(false);
        PanelNbJoueur.setLayout(null);

        TFnb_joueur.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TFnb_joueur.setForeground(new java.awt.Color(255, 255, 255));
        TFnb_joueur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFnb_joueur.setText("Nb Joueurs");
        TFnb_joueur.setBorder(null);
        TFnb_joueur.setOpaque(false);
        PanelNbJoueur.add(TFnb_joueur);
        TFnb_joueur.setBounds(380, 55, 100, 30);

        ButtonFrameNbJoueurs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonBis.png"))); // NOI18N
        ButtonFrameNbJoueurs.setBorderPainted(false);
        ButtonFrameNbJoueurs.setContentAreaFilled(false);
        ButtonFrameNbJoueurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFrameNbJoueursActionPerformed(evt);
            }
        });
        PanelNbJoueur.add(ButtonFrameNbJoueurs);
        ButtonFrameNbJoueurs.setBounds(630, 40, 165, 53);

        FondNbJoueur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BandeauNbJoueur.png"))); // NOI18N
        PanelNbJoueur.add(FondNbJoueur);
        FondNbJoueur.setBounds(0, 0, 850, 129);

        getContentPane().add(PanelNbJoueur);
        PanelNbJoueur.setBounds(-850, 471, 850, 129);

        PanelReseau.setOpaque(false);
        PanelReseau.setLayout(null);

        TFip.setBackground(new java.awt.Color(51, 51, 51));
        TFip.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TFip.setForeground(new java.awt.Color(255, 255, 255));
        TFip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFip.setText("jTextField1");
        TFip.setBorder(null);
        TFip.setCaretColor(new java.awt.Color(255, 255, 255));
        TFip.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        TFip.setOpaque(false);
        TFip.setSelectionColor(new java.awt.Color(255, 255, 255));
        PanelReseau.add(TFip);
        TFip.setBounds(110, 55, 180, 30);

        TFport.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TFport.setForeground(new java.awt.Color(255, 255, 255));
        TFport.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFport.setText("Port");
        TFport.setBorder(null);
        TFport.setOpaque(false);
        PanelReseau.add(TFport);
        TFport.setBounds(430, 55, 70, 30);

        ButtonFrameReseau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonBis.png"))); // NOI18N
        ButtonFrameReseau.setBorderPainted(false);
        ButtonFrameReseau.setContentAreaFilled(false);
        ButtonFrameReseau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFrameReseauActionPerformed(evt);
            }
        });
        PanelReseau.add(ButtonFrameReseau);
        ButtonFrameReseau.setBounds(630, 40, 197, 61);

        FondReseau.setBackground(new java.awt.Color(51, 51, 51));
        FondReseau.setForeground(new java.awt.Color(255, 255, 255));
        FondReseau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BandeauReseau.png"))); // NOI18N
        PanelReseau.add(FondReseau);
        FondReseau.setBounds(0, 0, 850, 129);

        getContentPane().add(PanelReseau);
        PanelReseau.setBounds(-850, 471, 850, 129);

        Buttonframe1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/Bouton.png"))); // NOI18N
        Buttonframe1.setBorder(null);
        Buttonframe1.setContentAreaFilled(false);
        Buttonframe1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Buttonframe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonframe1ActionPerformed(evt);
            }
        });
        getContentPane().add(Buttonframe1);
        Buttonframe1.setBounds(630, 500, 165, 53);

        TFnom.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TFnom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFnom.setText("Nom");
        TFnom.setBorder(null);
        TFnom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TFnom.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TFnom.setOpaque(false);
        getContentPane().add(TFnom);
        TFnom.setBounds(110, 505, 220, 40);

        FondTextField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/TextField.png"))); // NOI18N
        getContentPane().add(FondTextField);
        FondTextField.setBounds(80, 500, 287, 51);

        GroupButton.add(RadioOnline);
        RadioOnline.setContentAreaFilled(false);
        getContentPane().add(RadioOnline);
        RadioOnline.setBounds(410, 500, 20, 20);

        GroupButton.add(RadioLocal);
        RadioLocal.setContentAreaFilled(false);
        getContentPane().add(RadioLocal);
        RadioLocal.setBounds(410, 530, 20, 20);

        Fond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/Fond.png"))); // NOI18N
        Fond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FondMousePressed(evt);
            }
        });
        getContentPane().add(Fond);
        Fond.setBounds(0, 0, 850, 600);

        FondBis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/AcceuilBis.png"))); // NOI18N
        FondBis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FondBisMousePressed(evt);
            }
        });
        getContentPane().add(FondBis);
        FondBis.setBounds(850, 0, 850, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BExitActionPerformed

    private void BMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMinActionPerformed
        new Thread(){
            public void run(){
                setState(Frame.ICONIFIED);
            }
        }.start();
    }//GEN-LAST:event_BMinActionPerformed

    private void BarreHautMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarreHautMousePressed
        this.XDrag = evt.getX();
        this.YDrag = evt.getY();
    }//GEN-LAST:event_BarreHautMousePressed

    private void BarreHautMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarreHautMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-this.XDrag, y-this.YDrag);
    }//GEN-LAST:event_BarreHautMouseDragged

    private void Buttonframe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonframe1ActionPerformed
        new Thread(){
            public void run(){
        
                if(RadioOnline.isSelected()){
                AnimationFrame.LeftToRight(PanelReseau);
                }else{
                new Thread(){   
                  public void run(){
                        AnimationFrame.LeftToRight(PanelNbJoueur); 
                  }  }.start();
                    AnimationFrame.AnimSelectPerso(SelectPerso);
                }
            }
        }.start();
    }//GEN-LAST:event_Buttonframe1ActionPerformed

    private void ButtonFrameReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFrameReseauActionPerformed
        new Thread(){
            public void run(){
                new Thread(){   
                  public void run(){
                        AnimationFrame.LeftToRight(PanelNbJoueur); 
                  }  }.start();
                    AnimationFrame.AnimSelectPerso(SelectPerso);
                }
        }.start();
        this.client = new Client(new Ihm3d(this));
        client.ConnecttoServer();
    }//GEN-LAST:event_ButtonFrameReseauActionPerformed

    private void ButtonFrameNbJoueursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFrameNbJoueursActionPerformed
        if(this.RadioOnline.isSelected()){
            this.client.InitConnexion();
            if(client.isHost()){
                client.InitNb_Joueur();
            }
            client.setIhm(new IhmConsole());
            client.InitPartie();
            client.mainLoop();
            this.setVisible(false);
        } else {
            Controleur c = new Controleur();
        EventHandler ihm = new EventHandler(c);
        c.setObservateur(ihm);
        c.mainLoop();
        this.setVisible(false);
        }
    }//GEN-LAST:event_ButtonFrameNbJoueursActionPerformed

    private void FondBisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FondBisMousePressed
        new Thread(){
            public void run(){
                new Thread(){
                    public void run(){
                        AnimationFrame.AnimSelectPersoReverse(SelectPerso);
                    }}.start();
                AnimationFrame.RightToLeft(PanelNbJoueur);
                AnimationFrame.RightToLeft(PanelReseau);
            }
            }.start();
    }//GEN-LAST:event_FondBisMousePressed

    private void FondMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FondMousePressed
        new Thread(){
            public void run(){
                new Thread(){
                    public void run(){
                        AnimationFrame.AnimSelectPersoReverse(SelectPerso);
                    }}.start();
                AnimationFrame.RightToLeft(PanelNbJoueur);
                AnimationFrame.RightToLeft(PanelReseau);
            }
            }.start();
    }//GEN-LAST:event_FondMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameAcceuil frame = new FrameAcceuil();
                frame.setVisible(true);
            }
        });
    }

    public JTextField getTFnom() {
        return TFnom;
    }

    public JTextField getTFip() {
        return TFip;
    }

    public JTextField getTFport() {
        return TFport;
    }

    public JTextField getTFnb_joueur() {
        return TFnb_joueur;
    }

    public HashSet<JTextField> getListe_choixPions() {
        return liste_choixPions;
    }
    
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BExit;
    private javax.swing.JButton BMin;
    private javax.swing.JLabel BarreHaut;
    private javax.swing.JButton ButtonFrameNbJoueurs;
    private javax.swing.JButton ButtonFrameReseau;
    private javax.swing.JButton Buttonframe1;
    private javax.swing.JLabel Fond;
    private javax.swing.JLabel FondBis;
    private javax.swing.JLabel FondNbJoueur;
    private javax.swing.JLabel FondReseau;
    private javax.swing.JLabel FondTextField;
    private javax.swing.ButtonGroup GroupButton;
    private javax.swing.JLabel LabelPerso;
    private javax.swing.JPanel PanelBarreHaut;
    private javax.swing.JPanel PanelNbJoueur;
    private javax.swing.JPanel PanelReseau;
    private javax.swing.JTextField PersoBanane;
    private javax.swing.JTextField PersoCanette;
    private javax.swing.JTextField PersoHamburger;
    private javax.swing.JTextField PersoHorloge;
    private javax.swing.JTextField PersoPortal;
    private javax.swing.JTextField PersoTelephone;
    private javax.swing.JRadioButton RadioLocal;
    private javax.swing.JRadioButton RadioOnline;
    private javax.swing.JPanel SelectPerso;
    private javax.swing.JTextField TFip;
    private javax.swing.JTextField TFnb_joueur;
    private javax.swing.JTextField TFnom;
    private javax.swing.JTextField TFport;
    // End of variables declaration//GEN-END:variables
}
