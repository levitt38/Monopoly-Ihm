/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Jeu.Carreau;
import Jeu.Joueur;
import Jeu.Propriete;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Louis
 */
public class Plateau extends JPanel{

private BufferedImage image;
private int width = 800;
private int height = 800;
private final int LARGEUR_PETITE_CASE = 80*width/1040;
private final int LARGEUR_GRANDE_CASE = 2*LARGEUR_PETITE_CASE; 
private final int LARGEUR_PRISON = 55*width/1040;
private final int LARGEUR_COULEUR = 40*width/1040;
private final int HAUTEUR_CASE = LARGEUR_GRANDE_CASE;
private HashMap<String,Carreau> carreaux;

    public Plateau(int w, int h) {
        setDoubleBuffered(true);
        this.width = w;
        this.height = h;
       try {                
          image = resize(ImageIO.read(new File("./res/plateau.gif")),this.width,this.height);
       } catch (IOException ex) {
            System.err.printf("IMAGE NON TROUVEE");
       }
       
       this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(coordsToNumCase(e.getX(),e.getY()));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        // dessine le fond
        g.drawImage(image, 0, 0, null);
        //dessine les joueurs / maisons
        Graphics2D g2 = (Graphics2D) g;
        if (this.carreaux!=null){
            int nb;
            for(int i = 0;i<this.carreaux.size();i++){
                Carreau c = this.carreaux.get(Integer.toString(i));
                nb = 0;
                Rectangle r = this.numCarreauToCoords(i);
                for (Joueur j:c.getJoueurs()){
                    g2.setColor(Color.red);
                    g2.drawString(j.getNomJoueur(),(r.getX1()+r.getX0())/2,(r.getY1()+r.getY0())/2);
                    nb++;
                }
            }
            //
        }

    }
    
    
    private void afficherMaison(Propriete p){
        int tmp= p.getNbMaisons();
        int numCarreau = p.getNumero();
        int x = 0,y = 0;
        if (numCarreau<10 && numCarreau>0){
            x=this.width-(LARGEUR_PETITE_CASE*(2+numCarreau))+(LARGEUR_PETITE_CASE/8)*(1+2*tmp);
            y=HAUTEUR_CASE+LARGEUR_COULEUR/2;
        }else if (numCarreau<20 && numCarreau>10){
            x=HAUTEUR_CASE-LARGEUR_COULEUR/2;
            y=(numCarreau-10+2)*(LARGEUR_PETITE_CASE)+(LARGEUR_PETITE_CASE/8)*(1+2*tmp);
        }else if (numCarreau<30 && numCarreau>20){
            x=(numCarreau-20+2)*(LARGEUR_PETITE_CASE)-(LARGEUR_PETITE_CASE/8)*(1+2*tmp);
            y=HAUTEUR_CASE-LARGEUR_COULEUR/2;
        }else if (numCarreau<40 && numCarreau>30){
            x=this.width-HAUTEUR_CASE+LARGEUR_COULEUR/2;
            y=(numCarreau-30+2)*(LARGEUR_PETITE_CASE)-(LARGEUR_PETITE_CASE/8)*(1+2*tmp);
        }
        Questions.affiche("x : "+x+"   y : "+y);
    }
    
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
    
    public int coordsToNumCase(int x, int y){
        if (isInZone(x,y,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE),LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE))){
            return -1;
        } else if (isInZone(x,y,0,LARGEUR_GRANDE_CASE,0,LARGEUR_GRANDE_CASE)){
            return 20;
        } else if (isInZone(x,y,LARGEUR_PRISON,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE),this.width-LARGEUR_PRISON)){
            return 40;
        } else if (isInZone(x,y,0,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE),this.width)){
            return 10;
        } else if (isInZone(x,y,(this.width-LARGEUR_GRANDE_CASE),this.width,0,LARGEUR_GRANDE_CASE)){
            return 30;
        } else if (isInZone(x,y,(this.width-LARGEUR_GRANDE_CASE),this.width,(this.width-LARGEUR_GRANDE_CASE),this.width)){
            return 0;
        } else if (isInZone(x,y,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE),0,LARGEUR_GRANDE_CASE)){
            return (x-LARGEUR_PETITE_CASE)/LARGEUR_PETITE_CASE+20;
        } else if (isInZone(x,y,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE),(this.width-LARGEUR_GRANDE_CASE),this.width)){
            return 10-(x-LARGEUR_PETITE_CASE)/LARGEUR_PETITE_CASE;
        } else if (isInZone(x,y,(this.width-LARGEUR_GRANDE_CASE),this.width,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE))){
            return (y-LARGEUR_PETITE_CASE)/LARGEUR_PETITE_CASE+30;
        } else{// if (isInZone(x,y,0,LARGEUR_GRANDE_CASE,LARGEUR_GRANDE_CASE,(this.width-LARGEUR_GRANDE_CASE))){
            return 20-(y-LARGEUR_PETITE_CASE)/LARGEUR_PETITE_CASE;
        }
    }

    
    private Rectangle numCarreauToCoords(int numCarreau){
        if (numCarreau==0 || numCarreau==10 || numCarreau==20 || numCarreau==30 || numCarreau==40){
            if (numCarreau==0){
                return new Rectangle((width-LARGEUR_GRANDE_CASE),width,(width-LARGEUR_GRANDE_CASE),width);
            }else if (numCarreau==20){
                return new Rectangle(0,LARGEUR_GRANDE_CASE,0,0+LARGEUR_GRANDE_CASE);
            }else if (numCarreau==30){
                return new Rectangle((width-LARGEUR_GRANDE_CASE),width,0,LARGEUR_GRANDE_CASE);
            }else if (numCarreau==10){
                return new Rectangle(0,LARGEUR_GRANDE_CASE,(width-LARGEUR_GRANDE_CASE),width);
            }else if (numCarreau==40){
                return new Rectangle(LARGEUR_PRISON,LARGEUR_GRANDE_CASE,width-LARGEUR_GRANDE_CASE,width-LARGEUR_PRISON);
            }
        }else if (numCarreau<10 && numCarreau>0){
            return new Rectangle((9-numCarreau)*LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,(9-numCarreau)*LARGEUR_PETITE_CASE+LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,width-HAUTEUR_CASE,width);
        }else if (numCarreau<20 && numCarreau>10){
            return new Rectangle(0,HAUTEUR_CASE,(19-numCarreau)*LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,(19-numCarreau)*LARGEUR_PETITE_CASE+LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE);
        }else if (numCarreau<30 && numCarreau>20){
            return new Rectangle((numCarreau-21)*LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,(numCarreau-21)*LARGEUR_PETITE_CASE+LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,0,HAUTEUR_CASE);
        }else if (numCarreau<40 && numCarreau>30){
            return new Rectangle(width-HAUTEUR_CASE,width,(numCarreau-31)*LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE,(numCarreau-31)*LARGEUR_PETITE_CASE+LARGEUR_PETITE_CASE+LARGEUR_GRANDE_CASE);
        }
        return null;
    }
    
    private static boolean isInZone(int x, int y, int x0,int x1,int y0,int y1){
        return (x>=x0 && x<=x1 && y>=y0 && y<=y1);
    }

    public void setCarreaux(HashMap<String,Carreau> carreaux) {
        this.carreaux = carreaux;
    }
}
