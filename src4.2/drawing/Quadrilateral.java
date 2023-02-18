package drawing;

import java.awt.*;
import java.awt.Rectangle;

public class Quadrilateral extends Polygon {

    public Quadrilateral(int[] x, int[] y) {
        super(x, y, 4);
    }

    public void paint (Color color, Quadrilateral quad, Graphics g) {
        g.setColor(color);
        g.drawPolygon(quad);
        Rectangle boundRect = quad.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);
    }

}
