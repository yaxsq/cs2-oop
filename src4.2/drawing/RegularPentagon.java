package drawing;

import java.awt.*;
import java.awt.Rectangle;

public class RegularPentagon extends Pentagon {

    public RegularPentagon ( ) {
        super();

        int[] xPentagon = new int[5];
        int[] yPentagon = new int[5];
        int radiusPent = 200;
        int xCoord;
        int yCoord;

        for (int i = 0; i < xPentagon.length; i++) {
            xCoord = 400+ (int) ( radiusPent * Math.cos(Math.toRadians(90+ (i*72))) );
            yCoord = 300+ (int) ( radiusPent * Math.sin(Math.toRadians(90+ (i*72))) );
            super.addPoint(xCoord, yCoord);
        }

    }

    public void paint (Color color, RegularPentagon regPent, Graphics g) {
        super.paint(color, regPent, g);

        /*
        g.setColor(color);
        g.drawPolygon(regPent);
        Rectangle boundRect = regPent.getBounds();
        g.drawString("TEXT", boundRect.x + boundRect.width/2, boundRect.y + boundRect.height/2);
         */

    }


}
