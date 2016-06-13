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
public class Point {
    private double x;
    private double y;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "x : "+this.x+"  y : "+this.y;
    }
    
    public void negate(){
        this.x=-this.x;
        this.y=-this.y;
    }
    
    public static Point negate(Point p){
        return new Point(-p.x,-p.y);
    }
}
