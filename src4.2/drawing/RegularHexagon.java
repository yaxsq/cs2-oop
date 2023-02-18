package drawing;

import java.awt.*;

public class RegularHexagon extends Hexagon{

    public RegularHexagon () {
        super();

        int[] xPentagon = new int[5];
        int[] yPentagon = new int[5];
        int radiusPent = 200;
        int xCoord;
        int yCoord;

        int angle = 120;

        for (int i = 0; i < xPentagon.length; i++) {
            xCoord = 400+ (int) ( radiusPent * Math.cos(Math.toRadians(90+ (i*30))) );
            yCoord = 300+ (int) ( radiusPent * Math.sin(Math.toRadians(90+ (i*30))) );
            super.addPoint(xCoord, yCoord);
        }

    }
    public void paint (Color color, RegularHexagon regHex, Graphics g) {
        super.paint(color, regHex, g);
    }

}
