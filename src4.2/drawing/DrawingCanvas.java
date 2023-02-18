package drawing;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DrawingCanvas extends Canvas
{
	public void paint(Graphics g)
    {
        setBackground(Color.GRAY);

        /*
        File file = new File("Shapes.txt");
        try {
            Scanner in = new Scanner(file);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

         */

        /*

              //REGULAR  HEXAGON
        RegularHexagon regHex = new RegularHexagon();
        regHex.paint(Color.orange, regHex, g);
        */

        int[] xHex = {};
        int[] yHex = {};

/*
              // HEXAGON
        Hexagon hexagon = new RegularHexagon();
        hexagon.paint(Color.magenta, hexagon, g);
*/


/*
              //QUADRILATERAL
        int[] xQuad = {400, 300, 500, 600};
        int[] yQuad = {100, 200, 200, 100};
        Quadrilateral quad = new Quadrilateral(xQuad, yQuad);
        quad.paint(Color.cyan, quad, g);
*/

/*
                //REGULAR PENTAGON
        RegularPentagon regPent = new RegularPentagon();
        regPent.paint(Color.BLUE, regPent, g);
*/


/*
                //PENTAGON
        //WEIRD COORDINATES
        int[] xPent = {100, 200, 400, 600, 700};
        int[] yPent = {200, 400, 100, 400, 400};
        Pentagon pentagon = new Pentagon(xPent, yPent);
        pentagon.paint(Color.red ,pentagon, g);
*/

/*

              //TRIANGLE
        int[] x = {400, 300, 500};
        int[] y = {100, 200, 200};
        Triangle triangle = new Triangle(x, y);
        triangle.paint(Color.pink, triangle, g);
*/

        //triangle.translate(200, 300);



    }  
}
