/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox;

/**
 *
 * @author mouhatcl
 */
public class Rectangle {
    private Point p0;
    private Point p1;
    private double angle = 0;

    public Rectangle(double x0, double x1, double y0, double y1) {
        this.p0 = new Point(x0,y0);
        this.p1 = new Point(x1, y1);
    }

    public double getX0() {
        return p0.getX();
    }

    public Point getP0() {
        return p0;
    }

    public Point getP1() {
        return p1;
    }
    public double getY0() {
        return p0.getY();
    }
    public double getX1() {
        return p1.getX();
    }
    public double getY1() {
        return p1.getY();
    }
    
    public Point getCentre(){
        return new Point((this.getX0()+this.getX1())/2,(this.getY0()+this.getY1())/2);
    }
    
    public void scale(double s){
        Point centre = this.getCentre();
        this.p0.setX((this.getX0()-centre.getX())*s+centre.getX());
        this.p1.setX((this.getX1()-centre.getX())*s+centre.getX());
        this.p0.setY((this.getY0()-centre.getY())*s+centre.getY());
        this.p1.setY((this.getY1()-centre.getY())*s+centre.getY());
    }
    
    public double getRayon(){
        return Math.sqrt((this.getX0()-this.getX1())*(this.getX0()-this.getX1())+(this.getY0()-this.getY1())*(this.getY0()-this.getY1()))/2;
    }
    
    public void rotate(double a){
        this.angle+=a;
        if(this.angle>2*Math.PI){
            this.angle-=Math.PI*2;
        }else if(this.angle<0){
            this.angle+=Math.PI*2;
        }
        double r = this.getRayon();
        Point centre = this.getCentre();
        centre.negate();
        this.moveCentre(centre);
        this.scale(1/r);
        Point p0 = new Point(this.getX0(),this.getY0());
        Point p1 = new Point(this.getX1(),this.getY1());
        this.p0.setX(Math.cos(a+Math.atan2(p0.getY(),p0.getX())));
        this.p1.setX(Math.cos(a+Math.atan2(p1.getY(),p1.getX())));
        this.p0.setY(Math.sin(a+Math.atan2(p0.getY(),p0.getX())));
        this.p1.setY(Math.sin(a+Math.atan2(p1.getY(),p1.getX())));
        this.scale(r);
        centre.negate();
        this.moveCentre(centre);
    }
    
    public void moveCentre(Point p){
        this.p0.setX(this.getX0()+p.getX());
        this.p1.setX(this.getX1()+p.getX());
        this.p0.setY(this.getY0()+p.getY());
        this.p1.setY(this.getY1()+p.getY());
    }
    
    public Point[] getFourPoints(){
        Point[] p= new Point[4];
        p[0] = this.p0;
        p[1] = this.p1;
        double a = this.angle;
        this.rotate(-this.angle);
        Rectangle r = new Rectangle(this.getX0(),this.getX1(),this.getY1(),this.getY0());
        r.rotate(a);
        this.rotate(a);
        p[2] = r.p0;
        p[3] = r.p1;
        return p;
    }
    
    @Override
    public String toString(){
        return this.getP0().toString()+" "+this.getP1().toString();
    }
}
