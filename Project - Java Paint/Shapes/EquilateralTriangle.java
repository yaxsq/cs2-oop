package Shapes;

import java.awt.*;
import java.io.Serializable;

public class EquilateralTriangle extends Triangle implements Serializable {

    protected Point[] points;
    protected Point[] smallerPoints;

    public EquilateralTriangle(Point topLeft, Point bottomRight, Color color, Color strokeColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        super.setColor(color);
        this.strokeColor = strokeColor;

        setCoords(topLeft, bottomRight);
    }

    public EquilateralTriangle() {
    }

    public void draw(Graphics g) {
        if (strokeColor == null) {
            g.setColor(this.getColor());
            g.fillPolygon(super.getXCoords(points), super.getYCoords(points), 3);
        } else {
            g.setColor(strokeColor);
            g.fillPolygon(super.getXCoords(points), super.getYCoords(points), 3);
            g.setColor(this.getColor());
            g.fillPolygon(super.getXCoords(smallerPoints), super.getYCoords(smallerPoints), 3);
        }
    }

    private void setCoords(Point topLeft, Point bottomRight) {
        points = new Point[3];
        int length = (int) Math.sqrt(Math.pow(bottomRight.x - topLeft.x, 2) + Math.pow(bottomRight.y - topLeft.y, 2));

        if (bottomRight.y < topLeft.y) {
            points[0] = new Point(topLeft.x, topLeft.y);                                                                              //top point
            points[1] = new Point((int) (topLeft.x + (length / (Math.sqrt(2)))), (int) (topLeft.y - length / ((Math.sqrt(2)))));  //bottom left point
            points[2] = new Point((int) (topLeft.x - (length / (Math.sqrt(2)))), (int) (topLeft.y - (length / Math.sqrt(2))));    //bottom right point
        } else {
            points[0] = new Point(topLeft.x, topLeft.y);                                                                              //top point
            points[1] = new Point((int) (topLeft.x + (length / (Math.sqrt(2)))), (int) (topLeft.y + length / ((Math.sqrt(2)))));  //bottom left point
            points[2] = new Point((int) (topLeft.x - (length / (Math.sqrt(2)))), (int) (topLeft.y + (length / Math.sqrt(2))));    //bottom right point
        }

//        points[0] = new Point((topLeft.x + bottomRight.x) / 2, topLeft.y);             //top point
//        points[1] = new Point(topLeft.x, bottomRight.y);                                  //bottom left point
//        points[2] = new Point(bottomRight.x, bottomRight.y);                              //bottom right point

        setSmallerPoints(topLeft, bottomRight);
    }

    private void setSmallerPoints(Point topLeft, Point bottomRight) {
        smallerPoints = new Point[3];

        if (bottomRight.y < topLeft.y) {
            smallerPoints[0] = new Point(points[0].x, points[0].y - stroke);                        //top point
            smallerPoints[1] = new Point(points[1].x - stroke, points[1].y + stroke / 2);           //bottom left point
            smallerPoints[2] = new Point(points[2].x + stroke, points[2].y + stroke / 2);           //bottom right point
        } else {
            smallerPoints[0] = new Point(points[0].x, points[0].y + stroke);                        //top point
            smallerPoints[1] = new Point(points[1].x - stroke, points[1].y - stroke / 2);           //bottom left point
            smallerPoints[2] = new Point(points[2].x + stroke, points[2].y - stroke / 2);           //bottom right point
        }
    }

    @Override
    public String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void setSmallerPoints(Point[] smallerPoints) {
        this.smallerPoints = smallerPoints;
    }

    public Point[] getPoints() {
        return points;
    }

    public Point[] getSmallerPoints() {
        return smallerPoints;
    }
}
