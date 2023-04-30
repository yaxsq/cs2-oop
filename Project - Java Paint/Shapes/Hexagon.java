package Shapes;

import java.awt.*;
import java.io.Serializable;

public class Hexagon extends Shape implements Serializable {

    private Point[] points;
    private Point[] smallerPoints;

    public Hexagon(Point topLeft, Point bottomRight, Color color, Color strokeColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        super.setColor(color);
        this.strokeColor = strokeColor;

        setCoords(this.topLeft, this.bottomRight);
    }

    public Hexagon() {
    }

    @Override
    public void draw(Graphics g) {

        if (strokeColor == null) {                                              //if strokecolor is null, only shape is drawn
            g.setColor(this.getColor());
            g.fillPolygon(getXCoords(points), getYCoords(points), 6);
        } else {
            g.setColor(strokeColor);                                            //sets colors and draws the shape with stroke
            g.fillPolygon(getXCoords(points), getYCoords(points), 6);
            g.setColor(this.getColor());
            g.fillPolygon(getXCoords(smallerPoints), getYCoords(smallerPoints), 6);
        }

//        for (int i = 0; i < points.length; i++) {
//            g.drawString("P" + i, points[i].x, points[i].y);
//        }
    }

    /**
     * @param topLeft
     * @param bottomRight sets the coordinates for the shape
     */
    private void setCoords(Point topLeft, Point bottomRight) {
        points = new Point[6];

        if (bottomRight.y < topLeft.y) {                                                            //Adjusting if mouse dragged upwards
            points[0] = new Point(topLeft.x, topLeft.y - stroke);
            points[1] = new Point(bottomRight.x, topLeft.y - stroke);
            points[2] = new Point(bottomRight.x + (bottomRight.x - topLeft.x) - stroke, (topLeft.y + bottomRight.y) / 2);
            points[3] = new Point(bottomRight.x + stroke, bottomRight.y + stroke);
            points[4] = new Point(topLeft.x + stroke, bottomRight.y + stroke);
            points[5] = new Point(topLeft.x - (points[2].x - bottomRight.x) - stroke, (topLeft.y + bottomRight.y) / 2);
        } else {
            points[0] = new Point(topLeft.x, topLeft.y);
            points[1] = new Point(bottomRight.x, topLeft.y);
            points[2] = new Point(bottomRight.x + (bottomRight.x - topLeft.x), (topLeft.y + bottomRight.y) / 2);
            points[3] = new Point(bottomRight.x, bottomRight.y);
            points[4] = new Point(topLeft.x, bottomRight.y);
            points[5] = new Point(topLeft.x - (points[2].x - bottomRight.x), (topLeft.y + bottomRight.y) / 2);
        }

        setSmallerPoints(topLeft, bottomRight);
    }

    /**
     * @param topLeft
     * @param bottomRight sets coordinates for the inner shape
     *                    size adjusted according to stroke
     */
    private void setSmallerPoints(Point topLeft, Point bottomRight) {
        smallerPoints = new Point[6];

        if (bottomRight.x < topLeft.x) {                                                            //Adjusting if mouse dragged to the left
            smallerPoints[0] = new Point(points[0].x - stroke, points[0].y + stroke);
            smallerPoints[1] = new Point(points[1].x + stroke, points[1].y + stroke);
            smallerPoints[2] = new Point(points[2].x + stroke, points[2].y);
            smallerPoints[3] = new Point(points[3].x + stroke, points[3].y - stroke);
            smallerPoints[4] = new Point(points[4].x - stroke, points[4].y - stroke);
            smallerPoints[5] = new Point(points[5].x - stroke, points[5].y);
        } else {
            smallerPoints[0] = new Point(points[0].x + stroke, points[0].y + stroke);
            smallerPoints[1] = new Point(points[1].x - stroke, points[1].y + stroke);
            smallerPoints[2] = new Point(points[2].x - stroke, points[2].y);
            smallerPoints[3] = new Point(points[3].x - stroke, points[3].y - stroke);
            smallerPoints[4] = new Point(points[4].x + stroke, points[4].y - stroke);
            smallerPoints[5] = new Point(points[5].x + stroke, points[5].y);
        }

        if (bottomRight.y < topLeft.y) {                                                            //Adjusting if mouse dragged upwards
            smallerPoints[0] = new Point(points[0].x + stroke, points[0].y - stroke);
            smallerPoints[1] = new Point(points[1].x - stroke, points[1].y - stroke);
            smallerPoints[3] = new Point(points[3].x - stroke, points[3].y + stroke);
            smallerPoints[4] = new Point(points[4].x + stroke, points[4].y + stroke);
        }

    }

    @Override
    public String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

    //setters and getters for filesaving

    public void setSmallerPoints(Point[] smallerPoints) {
        this.smallerPoints = smallerPoints;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public Point[] getSmallerPoints() {
        return smallerPoints;
    }
}
