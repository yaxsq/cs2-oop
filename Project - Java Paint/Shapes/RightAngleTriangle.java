package Shapes;

import java.awt.*;
import java.io.Serializable;

public class RightAngleTriangle extends EquilateralTriangle implements Serializable {

    public RightAngleTriangle(Point topLeft, Point bottomRight, Color color, Color strokeColor) {
        super();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        super.setColor(color);
        this.strokeColor = strokeColor;

        setCoords(topLeft, bottomRight);
    }

    public RightAngleTriangle() {}

    private void setCoords(Point topLeft, Point bottomRight) {
        points = new Point[3];

        points[0] = new Point(topLeft.x, topLeft.y);
        points[1] = new Point(topLeft.x, bottomRight.y);
        points[2] = new Point(bottomRight.x, bottomRight.y);

        setSmallerPoints(topLeft, bottomRight);
    }

    private void setSmallerPoints(Point topLeft, Point bottomRight) {
        smallerPoints = new Point[3];

        if (bottomRight.x < topLeft.x) {
            smallerPoints[0] = new Point(topLeft.x - stroke / 2, topLeft.y + stroke);
            smallerPoints[1] = new Point(topLeft.x - stroke * 2, bottomRight.y - stroke);
            smallerPoints[2] = new Point(bottomRight.x + stroke * 2, bottomRight.y - stroke);
        } else {
            smallerPoints[0] = new Point(topLeft.x + stroke / 2, topLeft.y + stroke);
            smallerPoints[1] = new Point(topLeft.x + stroke, bottomRight.y - stroke);
            smallerPoints[2] = new Point(bottomRight.x - stroke * 2, bottomRight.y - stroke);
        }

        if (bottomRight.x < topLeft.x && bottomRight.y < topLeft.y) {
            smallerPoints[0] = new Point(topLeft.x - stroke / 2, topLeft.y - stroke);
            smallerPoints[1] = new Point(topLeft.x - stroke * 2, bottomRight.y + stroke);
            smallerPoints[2] = new Point(bottomRight.x + stroke * 2, bottomRight.y + stroke);
        }

        if (bottomRight.x > topLeft.x && bottomRight.y < topLeft.y) {
            smallerPoints[0] = new Point(topLeft.x + stroke / 2, topLeft.y - stroke);
            smallerPoints[1] = new Point(topLeft.x + stroke, bottomRight.y + stroke);
            smallerPoints[2] = new Point(bottomRight.x - stroke * 2, bottomRight.y + stroke);
        }
    }

    @Override
    public String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }
}
