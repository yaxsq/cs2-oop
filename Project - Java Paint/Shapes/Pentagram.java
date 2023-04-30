package Shapes;

import DrawingPanel.DrawingPanel;

import java.awt.*;
import java.io.Serializable;

public class Pentagram extends Shape implements Serializable {

    private Point[] points;
    private Point initialPoint;

    public Pentagram(Point topLeft, Point bottomRight, Color color) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.color = color;
    }

    public Pentagram(Point topLeft, Color color, Color strokeColor) {
        initialPoint = new Point(topLeft.x, topLeft.y);

        points = new Point[5];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(topLeft.x, topLeft.y);
        }

        this.color = color;
        this.strokeColor = strokeColor;
    }

    public Pentagram() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        int[] xPoints = {points[0].x, points[2].x, points[4].x, points[1].x, points[3].x};          //setting up arrays for pentagram coords
        int[] yPoints = {points[0].y, points[2].y, points[4].y, points[1].y, points[3].y};

//        g.fillPolygon(getXCoords(points), getYCoords(points), 5);
        g.fillPolygon(xPoints, yPoints, 5);
    }

    public void changePoints(Point movingPoint) {
        double length = Math.sqrt(Math.pow(points[0].x - movingPoint.x, 2) + Math.pow(points[0].y - movingPoint.y, 2));

        for (int i = 0; i < points.length; i++) {
            points[i].x = initialPoint.x + (int) (length * Math.cos(Math.toRadians(90 + (72 * i))));
            points[i].y = initialPoint.y + (int) (length * Math.sin(Math.toRadians(90 + (72 * i))));
        }
    }

    private void setSmallerPoints() {

    }

    @Override
    public String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

    //setters and getters for filesaving

    public void setInitialPoint(Point initialPoint) {
        this.initialPoint = initialPoint;
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }
}
