package drawing;

import java.awt.*;
import java.awt.Rectangle;


public class Triangle extends Polygon {

    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;

    public Triangle(int[] xPoints, int[] yPoints) {
        super(xPoints, yPoints, 3);



        /*
        if (xPoints.length == 3 && yPoints.length == 3) {
            this.xPoints = xPoints;
            this.yPoints = yPoints;
            this.nPoints = nPoints;
        }
        else {
            System.out.println("INVALID INPUT");
            System.exit(1);
        }*/
    }

    public void paint(Color color, Triangle triangle, Graphics g) {
        g.setColor(color);
        g.drawPolygon(triangle);
        Rectangle boundRect = triangle.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);
    }

    /*
    public void paint(Graphics g) {

        g.setColor(Color.red);

        g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g.drawLine(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
        g.drawLine(xPoints[2], yPoints[2], xPoints[0], yPoints[0]);


        for (int i = 1; i < xPoints.length; i++) {
            g.drawLine(xPoints[i], yPoints[i], xPoints[i-1], yPoints[i-1]);
            if (i==xPoints.length-1)
                g.drawLine(xPoints[0], yPoints[0], xPoints[i], yPoints[i]);
        }
    }
    */

}
