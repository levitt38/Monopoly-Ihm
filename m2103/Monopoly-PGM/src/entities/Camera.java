/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import renderEngine.MasterRenderer;
import toolbox.Maths;

/**
 *
 * @author Louis
 */
public class Camera {
    public Vector3f position = new Vector3f(0,0,0);
    public float pitch = 0;
    public float yaw = 0;
    private float roll;
    private final float sensitivity = -0.02f;
    private final float WHEEL_SPEED = 0.01f;
    private final float ZOOM_OUT_LIMIT = 10;
    private final float ZOOM_IN_LIMIT = 2;
    
    private Entity test;

    public void setTest(Entity test) {
        this.test = test;
    }
    private final Player player;
    private boolean wasPressed = false;
    private MasterRenderer rend;


    public Camera(Player player, MasterRenderer r){
        this.player=player;
        this.rend = r;
    }
    
    public void move(){
        /*if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
            this.move((float) (speed*sin(toRadians(getYaw()))),(float) (-1*speed*sin(toRadians(getPitch()))),(float) (-1*speed*cos(toRadians(getYaw()))));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            this.move((float) (-1*speed*sin(toRadians(getYaw()))),(float) (speed*sin(toRadians(getPitch()))),(float) (speed*cos(toRadians(getYaw()))));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            this.move((float) (speed*sin(toRadians(getYaw()- 90))), 0,(float) (-1*speed*cos(toRadians(getYaw()-90))));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.move((float)(-1*speed*sin(toRadians(getYaw()-90))),0,(float) (speed*cos(toRadians(getYaw()-90))));
        }
        player.moveYaw(Mouse.getDX()*sensitivity);
        player.movePitch(-1*Mouse.getDY()*sensitivity);*/
        if(Mouse.isButtonDown(1)){
            if(this.wasPressed){
                double ancienAngle = Math.atan2((double) this.getPosition().getX(),(double) this.getPosition().getZ());
                int dx = Mouse.getDX();
                int dy = Mouse.getDY();
                double dv = this.getDistanceCentreV();
                float newX = (float)(this.getDistanceCentre()*Math.sin(ancienAngle+dx*sensitivity));
                float newZ = (float)(this.getDistanceCentre()*Math.cos(ancienAngle+dx*sensitivity));

                player.setPosition(newX,this.getPlayer().getY(),newZ);
                float newY = (float)(dv*Math.sin(Math.atan2((double) this.getPosition().getY(),(double) this.getDistanceCentre())+dy*sensitivity));

                double angle = Math.atan2((double) this.getPosition().getX(),(double) this.getPosition().getZ());
                double newDist = Math.sqrt(dv*dv-newY*newY);
                newX = (float)(newDist*Math.sin(angle));
                newZ = (float)(newDist*Math.cos(angle));
                player.setPosition(newX,newY,newZ);
                player.setYaw((float)Math.toDegrees(-Math.atan2((double) this.getPosition().getX(),(double) this.getPosition().getZ())));
                player.setPitch((float)Math.toDegrees(Math.atan2(newY, this.getDistanceCentre())));
            }else{
                this.wasPressed = true;
                Mouse.getDX();Mouse.getDY();
            }
        }else{
            this.wasPressed = false;
        }
        
        int dw = (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) ? 60 : 0 ;
        dw = (dw!=0) ? dw : ((Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))? -60:0);
        dw = (dw!=0) ? dw : Mouse.getDWheel();
        double dDistance = (this.getDistanceCentreV()-((double)dw)*this.WHEEL_SPEED);
        if(dw!=0 && ((dw>0 && dDistance>this.ZOOM_IN_LIMIT)||(dw<0 && dDistance<this.ZOOM_OUT_LIMIT) )){
            float ratio =(float)( dDistance/this.getDistanceCentreV());
            player.setPosition(player.getPosition().getX()*ratio,player.getPosition().getY()*ratio, player.getPosition().getZ()*ratio);
        }
        
        if(Mouse.isButtonDown(0)){
            // Ray Casting
            int mX = Mouse.getX();
            int mY = Mouse.getY();
            
            int width = renderEngine.DisplayManager.getWIDTH();
            int height = renderEngine.DisplayManager.getHEIGHT();
                        
            float x = 1.0f-(2.0f * mX) / width;
            float y = 1.0f - (2.0f * mY) / height;
            float z = 1.0f;
            Vector4f ray_clip = new Vector4f(x,y, -1.0f, 1.0f);
            
            Matrix4f projection_matrix = this.rend.getProjectionMatrix();
            Matrix4f invProjection_matrix = Matrix4f.invert(projection_matrix, null);
            //Vector4f ray_eye =  (invProjection_matrix) * ray_clip;
            Vector4f v = Matrix4f.transform(invProjection_matrix,ray_clip,null);
            
            v = new Vector4f(v.x,v.y,1.0f,0f);
            Matrix4f viewMatrix = Maths.createViewMatrix(this);
            v = Matrix4f.transform(Matrix4f.invert(viewMatrix,null), v, null);
            
            
            v.normalise(v);
            
            float n = -this.getPosition().y/v.y;
            float nx = this.getPosition().x + n * v.x;
            float nz = this.getPosition().z + n * v.z;
            this.test.setPosition(nx, 0,nz);
            System.out.println("x : "+nx+"  y : "+nz);
        }
        
        
        
    }

    public Vector3f getPosition() {
        return player.getPosition();
    }

    public Player getPlayer() {
        return player;
    }

    public double getDistanceCentre(){
        return Math.sqrt(this.getPosition().getX()*this.getPosition().getX()+this.getPosition().getZ()*this.getPosition().getZ());
    }
    
    public float getPitch() {
        return player.getPitch();
    }

    public float getYaw() {
        return player.getYaw();
    }

    public float getRoll() {
        return roll;
    }
    
    public void setPosition(float x, float y, float z){
        position.x+=x;
        position.y+=y;
        position.z+=z;
    }

    private double getDistanceCentreV() {
        double x = this.getPosition().getX();
        double y = this.getPosition().getY();
        double z = this.getPosition().getZ();
        return Math.sqrt(x*x+y*y+z*z);
    }

}
