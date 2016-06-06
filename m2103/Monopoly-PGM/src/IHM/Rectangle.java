/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Point;

/**
 *
 * @author Louis
 */
public class Rectangle {
    private Point p0;
    private Point p1;

    public Rectangle(int x0, int x1, int y0, int y1) {
        this.p0 = new Point(x0,y0);
        this.p1 = new Point(x1, y1);
    }

    public int getX0() {
        return p0.x;
    }
    public int getY0() {
        return p0.y;
    }
    public int getX1() {
        return p1.x;
    }
    public int getY1() {
        return p1.y;
    }
    
}
