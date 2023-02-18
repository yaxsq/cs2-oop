package drawing;

import java.awt.*;
import java.awt.Rectangle;

public class Pentagon extends Polygon {

    public Pentagon () {

    }
    public Pentagon ( int[] x, int[] y) {

        super (x, y, 5);
        /*
        super();

        for (int i = 0; i < x.length; i++) {
            int xCoord = x[i];
            int yCoord = y[i];
            super.addPoint(xCoord, yCoord);
        }
         */

    }

    public void paint(Color color, Pentagon pentagon, Graphics g) {
        g.setColor(color);
        g.drawPolygon(pentagon);
        Rectangle boundRect = pentagon.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);

    }


}
