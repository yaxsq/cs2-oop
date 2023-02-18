package drawing;

import java.awt.*;
import java.awt.font.TextAttribute;

public class DrawingCanvas extends Canvas {
    public void paint(Graphics g) {
        setBackground(Color.WHITE);

        int frameX = 800;
        int frameY = 600;
        int rectLenX = 300;
        int rectLenY = 100;

        Rectangle rect = new Rectangle(frameX / 2, frameY / 2, rectLenX, rectLenX, Color.YELLOW, Color.lightGray, 3, "IT WORKS!", g) ;
        rect.paint(g);

        /*
        Font font = new Font("txt", 2, (rectLenX+rectLenY)/20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("txt", frameX/2, frameY/2);
*/
        /*
        g.drawString("Hello",40,40);
        g.fillRect(50, 50, 40, 30);  
        g.drawOval(30,130,50, 60);  
        setForeground(Color.RED);  
        g.fillOval(130,130,50, 60);  
        g.drawArc(30, 200, 40,50,90,60);  
        g.fillArc(30, 130, 40,50,180,40);  
        
        Rectangle rectangle = new Rectangle(400, 300, 100, 20, Color.GREEN, Color.BLUE, 3);
        rectangle.paint(g);
        
        Square square = new Square(600, 400, 100, Color.YELLOW, Color.BLUE, 3);
        square.paint(g);
          */
    }
}
