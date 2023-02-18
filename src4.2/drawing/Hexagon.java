package drawing;

import java.awt.*;
import java.awt.Rectangle;

public class Hexagon extends Polygon{

    public Hexagon () {
        super();

        int[] xPentagon = new int[5];
        int[] yPentagon = new int[5];
        int radiusPent = 200;
        int xCoord;
        int yCoord;

        for (int i = 0; i < xPentagon.length; i++) {
            xCoord = 400+ (int) ( radiusPent * Math.cos(Math.toRadians(90+ (i*30))) );
            yCoord = 300+ (int) ( radiusPent * Math.sin(Math.toRadians(90+ (i*30))) );
            super.addPoint(xCoord, yCoord);
        }

    }
    public void paint(Color color, Hexagon hexagon, Graphics g) {
        g.setColor(color);
        g.drawPolygon(hexagon);
        Rectangle boundRect = hexagon.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);

    }

}


/*

package drawing;

import java.awt.*;
import java.awt.Rectangle;

public class Hexagon extends Polygon {

    public Hexagon () {

    }
    public Hexagon (int[] x, int[] y) {
        super(x, y, 6);

    }

    public void paint (Color color, Hexagon hex, Graphics g) {
        g.setColor(color);
        g.drawPolygon(hex);
        Rectangle boundRect = hex.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);
    }

}


 */