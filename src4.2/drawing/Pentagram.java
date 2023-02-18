package drawing;

public class Pentagram {

    public Pentagram() {
        super();

        int[] xPentagram = new int[5];
        int[] yPentagram = new int[5];
        int radiusPent = 800;

        for (int i = 0; i < xPentagram.length; i++) {
            xPentagram[i] = 400+ (int) ( radiusPent * Math.cos(Math.toRadians(90+ (i*72))) );
            yPentagram[i] = 300+ (int) ( radiusPent * Math.sin(Math.toRadians(90+ (i*72))) );
        }

    }

}
