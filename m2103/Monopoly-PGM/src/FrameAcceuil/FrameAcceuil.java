/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameAcceuil;

import java.awt.Frame;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nourik
 */
public class FrameAcceuil extends javax.swing.JFrame {
    private int XDrag,YDrag;

    /**
     * Creates new form FrameAcceuil
     */
    public FrameAcceuil() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        PanelNbJoueur = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        FondNbJoueur = new javax.swing.JLabel();
        PanelReseau = new javax.swing.JPanel();
        TFip = new javax.swing.JTextField();
        TFport = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        FondReseau = new javax.swing.JLabel();
        Button = new javax.swing.JButton();
        TextField = new javax.swing.JTextField();
        FondTextField = new javax.swing.JLabel();
        RadioOnline = new javax.swing.JRadioButton();
        RadioLocal = new javax.swing.JRadioButton();
        BExit = new javax.swing.JButton();
        BMin = new javax.swing.JButton();
        BarreHaut = new javax.swing.JLabel();
        Fond = new javax.swing.JLabel();
        FondBis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(850, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        PanelNbJoueur.setOpaque(false);
        PanelNbJoueur.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PanelNbJoueurMouseMoved(evt);
            }
        });
        PanelNbJoueur.setLayout(null);

        jTextField1.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Nb Joueurs");
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        PanelNbJoueur.add(jTextField1);
        jTextField1.setBounds(380, 255, 100, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonBis.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        PanelNbJoueur.add(jButton1);
        jButton1.setBounds(630, 240, 165, 53);

        FondNbJoueur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BandeauNbJoueur.png"))); // NOI18N
        PanelNbJoueur.add(FondNbJoueur);
        FondNbJoueur.setBounds(0, 200, 850, 129);

        getContentPane().add(PanelNbJoueur);
        PanelNbJoueur.setBounds(-850, 271, 850, 329);

        PanelReseau.setOpaque(false);
        PanelReseau.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PanelReseauMouseMoved(evt);
            }
        });
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
        TFip.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        TFip.setSelectionColor(new java.awt.Color(255, 255, 255));
        PanelReseau.add(TFip);
        TFip.setBounds(110, 255, 180, 30);

        TFport.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TFport.setForeground(new java.awt.Color(255, 255, 255));
        TFport.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFport.setText("Port");
        TFport.setBorder(null);
        TFport.setOpaque(false);
        PanelReseau.add(TFport);
        TFport.setBounds(430, 255, 70, 30);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonBis.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelReseau.add(jButton2);
        jButton2.setBounds(630, 240, 199, 63);

        FondReseau.setBackground(new java.awt.Color(51, 51, 51));
        FondReseau.setForeground(new java.awt.Color(255, 255, 255));
        FondReseau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BandeauReseau.png"))); // NOI18N
        PanelReseau.add(FondReseau);
        FondReseau.setBounds(0, 200, 850, 129);

        getContentPane().add(PanelReseau);
        PanelReseau.setBounds(-850, 271, 850, 329);

        Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/Bouton.png"))); // NOI18N
        Button.setBorder(null);
        Button.setContentAreaFilled(false);
        Button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Button);
        Button.setBounds(630, 500, 165, 53);

        TextField.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextField.setText("Nom");
        TextField.setBorder(null);
        TextField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TextField.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TextField.setOpaque(false);
        getContentPane().add(TextField);
        TextField.setBounds(110, 505, 220, 40);

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

        BExit.setBorderPainted(false);
        BExit.setContentAreaFilled(false);
        BExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BExitActionPerformed(evt);
            }
        });
        getContentPane().add(BExit);
        BExit.setBounds(800, 10, 20, 20);

        BMin.setBorderPainted(false);
        BMin.setContentAreaFilled(false);
        BMin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMinActionPerformed(evt);
            }
        });
        getContentPane().add(BMin);
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
        getContentPane().add(BarreHaut);
        BarreHaut.setBounds(0, 0, 850, 45);

        Fond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/Fond.png"))); // NOI18N
        getContentPane().add(Fond);
        Fond.setBounds(0, 0, 850, 600);

        FondBis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/AcceuilBis.png"))); // NOI18N
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

    private void ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActionPerformed
        new Thread(){
            public void run(){
        
                if(RadioOnline.isSelected()){
                AnimationFrame.LeftToRight(PanelReseau);
                }else{
                AnimationFrame.LeftToRight(PanelNbJoueur);   
                }
                
           }
        }.start();
    }//GEN-LAST:event_ButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Thread(){
            public void run(){
                AnimationFrame.LeftToRight(PanelNbJoueur);
            }
        }.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PanelNbJoueurMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNbJoueurMouseMoved
        if(evt.getY()<200){
            new Thread(){
            public void run(){
                AnimationFrame.RightToLeft(PanelNbJoueur);
            }
            }.start();
        }
    }//GEN-LAST:event_PanelNbJoueurMouseMoved

    private void PanelReseauMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelReseauMouseMoved
        if(evt.getY()<200){
            new Thread(){
            public void run(){
                AnimationFrame.RightToLeft(PanelReseau);
            }
            }.start();
        }
    }//GEN-LAST:event_PanelReseauMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameAcceuil frame = new FrameAcceuil();
                frame.setVisible(true);
                frame.SlideShow();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BExit;
    private javax.swing.JButton BMin;
    private javax.swing.JLabel BarreHaut;
    private javax.swing.JButton Button;
    private javax.swing.JLabel Fond;
    private javax.swing.JLabel FondBis;
    private javax.swing.JLabel FondNbJoueur;
    private javax.swing.JLabel FondReseau;
    private javax.swing.JLabel FondTextField;
    private javax.swing.ButtonGroup GroupButton;
    private javax.swing.JPanel PanelNbJoueur;
    private javax.swing.JPanel PanelReseau;
    private javax.swing.JRadioButton RadioLocal;
    private javax.swing.JRadioButton RadioOnline;
    private javax.swing.JTextField TFip;
    private javax.swing.JTextField TFport;
    private javax.swing.JTextField TextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
