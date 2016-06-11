/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Jeu.Carreau;
import Jeu.Joueur;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Louis
 */
public class FrameJeu extends javax.swing.JFrame {

    private int width = 800;
    private int height = 800;
    private boolean menuAff = false;
    private HashMap<String, Carreau> plateau;
    private boolean YesNoSaisi, YesNoChoix;
    
    public FrameJeu(Ihm3d ihm3d) {
        super("Xtrem Monopoly");
        initComponents();
        //this.panelPlateau1.setIhm3d(ihm3d);
        this.YesNoSaisi = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EnattendantOpengl = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 300));
        getContentPane().setLayout(null);

        EnattendantOpengl.setLayout(null);

        PanelAction.setLayout(null);

        TextAction.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        TextAction.setText("Voulez vous acheter X");
        PanelAction.add(TextAction);
        TextAction.setBounds(50, 30, 310, 80);

        BActionNon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonNon.png"))); // NOI18N
        BActionNon.setBorderPainted(false);
        BActionNon.setContentAreaFilled(false);
        BActionNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionNonActionPerformed(evt);
            }
        });
        PanelAction.add(BActionNon);
        BActionNon.setBounds(70, 140, 100, 53);

        BActionOui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/BoutonOui.png"))); // NOI18N
        BActionOui.setBorderPainted(false);
        BActionOui.setContentAreaFilled(false);
        BActionOui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionOuiActionPerformed(evt);
            }
        });
        PanelAction.add(BActionOui);
        BActionOui.setBounds(260, 140, 100, 53);

        FondAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelAction.png"))); // NOI18N
        PanelAction.add(FondAction);
        FondAction.setBounds(0, 0, 400, 200);

        EnattendantOpengl.add(PanelAction);
        PanelAction.setBounds(880, 0, 400, 200);

        PanelCarreau.setLayout(null);

        TextCarreau.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextCarreau.setText("<html>Twinkle, twinkle, little star,<BR>How I wonder what you are.<BR>Up above the world so high,<BR>Like a diamond in the sky.</html>");
        PanelCarreau.add(TextCarreau);
        TextCarreau.setBounds(30, 110, 240, 200);

        ExitCarreau.setBorderPainted(false);
        ExitCarreau.setContentAreaFilled(false);
        ExitCarreau.setOpaque(false);
        PanelCarreau.add(ExitCarreau);
        ExitCarreau.setBounds(254, 5, 40, 40);

        FondCarreau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelCarreau.png"))); // NOI18N
        PanelCarreau.add(FondCarreau);
        FondCarreau.setBounds(0, 0, 300, 360);

        EnattendantOpengl.add(PanelCarreau);
        PanelCarreau.setBounds(0, 360, 300, 360);

        PanelJoueur.setLayout(null);

        TextJoueur.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextJoueur.setText("<html>Twinkle, twinkle, little star,<BR>How I wonder what you are.<BR>Up above the world so high,<BR>Like a diamond in the sky.</html>");
        PanelJoueur.add(TextJoueur);
        TextJoueur.setBounds(30, 110, 240, 200);

        ExitJoueur.setBorderPainted(false);
        ExitJoueur.setContentAreaFilled(false);
        PanelJoueur.add(ExitJoueur);
        ExitJoueur.setBounds(254, 5, 40, 40);

        FondJoueur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/PanelJoueur.png"))); // NOI18N
        PanelJoueur.add(FondJoueur);
        FondJoueur.setBounds(0, 0, 300, 360);

        EnattendantOpengl.add(PanelJoueur);
        PanelJoueur.setBounds(0, 0, 300, 360);

        getContentPane().add(EnattendantOpengl);
        EnattendantOpengl.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BActionNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionNonActionPerformed
        this.YesNoChoix = false; //le choix est non
        this.YesNoSaisi = true; //continue le programme
    }//GEN-LAST:event_BActionNonActionPerformed

    private void BActionOuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionOuiActionPerformed
        this.YesNoChoix = true; //le choix est non
        this.YesNoSaisi = true; //continue le programme
    }//GEN-LAST:event_BActionOuiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameJeu(new Ihm3d()).setVisible(true);
            }
        });
    }
    /*
    public void mouseLoop(){
        while(true){
            int x = Mouse.getX();
            if(x<20&&x>0&&!this.menuAff){
                this.menuAff = true;
                new Thread(){
                    public void run(){
                        afficherMenu();
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
                int nc = this.panelPlateau1.getOp().getCarreauSelected();
                if(this.plateau!=null&&nc>=0){
                    if(!this.menuAff){
                        this.menuAff = true;
                        new Thread(){
                            public void run(){
                                afficherMenu();
                            }
                        }.start();
                    }
                    this.afficherCarreau(this.plateau.get(Integer.toString(nc)));
                }
                // la suite est juste du test, elle sera remove
                else if(this.plateau!=null&&(nc==IhmOpenGL.CARTES_CHANCE||nc==IhmOpenGL.CARTES_COMMUNAUTE)){
                    if(!this.menuAff){
                        this.menuAff = true;
                        new Thread(){
                            public void run(){
                                afficherMenu();
                            }
                        }.start();
                    }
                    String s = (nc==IhmOpenGL.CARTES_CHANCE) ? "Tas de cartes chance" : "Tas de cartes caisse dde communauté";
                    this.afficherStr(s);
                }
                
                // fin des tests
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
        this.panelPlateau1.initialiserPlateau();
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

    public void afficherPlateau(HashMap<String, Carreau> c){
        this.plateau = c;
        this.panelPlateau1.rafraîchirPlateau(c);
    }*/
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActionNon;
    private javax.swing.JButton BActionOui;
    private javax.swing.JPanel EnattendantOpengl;
    private javax.swing.JButton ExitCarreau;
    private javax.swing.JButton ExitJoueur;
    private javax.swing.JLabel FondAction;
    private javax.swing.JLabel FondCarreau;
    private javax.swing.JLabel FondJoueur;
    private javax.swing.JPanel PanelAction;
    private javax.swing.JPanel PanelCarreau;
    private javax.swing.JPanel PanelJoueur;
    private javax.swing.JLabel TextAction;
    private javax.swing.JLabel TextCarreau;
    private javax.swing.JLabel TextJoueur;
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

    
}
