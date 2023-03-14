import java.awt.*;

public class Triangle extends Shape {

    private Point[] points;

    /**
     * @param points array of three points
     * @param color  color
     *               CONSTRUCTOR
     */
    Triangle(Point[] points, Color color) {
        this.points = points.clone();
        super.setColor(color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(this.getColor());

        /*
        for (int i = 0; i < points.length - 1; i++) {
            g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
        }
        g.drawLine(points[0].x, points[0].y, points[points.length - 1].x, points[points.length - 1].y);
         */

        g.fillPolygon(getXCoords(points), getYCoords(points), 3);
    }

    /**
     * @param arr array of points
     * @return int array with only point.x
     */
    private int[] getXCoords(Point[] arr) {
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
    private int[] getYCoords(Point[] arr) {
        int[] yArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            yArr[i] = arr[i].y;
        }
        return yArr;
    }

    @Override
    String shapeToString() {
        return points[0].x + "," + points[1].x + "," + points[2].x + "," + points[0].y + "," + points[1].y + "," + points[2].y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

}