package Shapes;

import Toolbars.TopBar;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {

    protected Point topLeft;
    protected Point bottomRight;

    protected Color color;
    protected Color strokeColor;
    protected int stroke = TopBar.stroke;

    public Shape() {
    }

    /**
     * @param g Graphics
     *          draws the shape it is called for
     */
    public abstract void draw(Graphics g);

    /**
     * @return the attributes of the shape in a string
     */
    public abstract String shapeToString();


    //setLocation not an abstract function because parameters need to be defined in the superclass and the parameters have to be different for each shape

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param arr array of points
     * @return int array with only point.x
     */
    protected int[] getXCoords(Point[] arr) {
        int[] xArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            xArr[i] = arr[i].x;
        }
        return xArr;
    }

    //THESE FUNCTIONS RETURN THE X AND Y COORDDINATES OF THE POINTS ARRAY SEPARATELY

    /**
     * @param arr array of points
     * @return int array with only point.y
     */
    protected int[] getYCoords(Point[] arr) {
        int[] yArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            yArr[i] = arr[i].y;
        }
        return yArr;
    }

    //setters and getters for filesaving

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public int getStroke() {
        return stroke;
    }

}