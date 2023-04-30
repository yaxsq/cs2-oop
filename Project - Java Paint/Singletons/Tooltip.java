package Singletons;

import Buttons.Button;
import Buttons.ColorButton;
import Shapes.Rectangle;

import java.awt.*;
import java.io.Serializable;

public class Tooltip implements Serializable {

    private static Tooltip instance = new Tooltip();
    public static boolean visible;
    private static Point point;
    private Point endPoint;
    private int fontSize;
    private static String text;
    private static Color textColor;
    private static Buttons.Button button;

    private Tooltip() {
        fontSize = 12;
        textColor = Color.black;
    }

    public static Tooltip getInstance() {
        return instance;
    }

    public void paint(Graphics g) {
        if (visible) {
//            System.out.println("INSIDE");

            Font font = new Font(text, Font.PLAIN, fontSize);                                        //setting up font
            g.setColor(textColor);
            g.setFont(font);

            FontMetrics fontMetrics = g.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);
            int textHeight = fontMetrics.getAscent() - fontMetrics.getDescent();

            Point startPoint = new Point(point.x + 10, point.y + 15);
            endPoint = new Point(startPoint.x + textWidth + 6, startPoint.y + textHeight + 8);            //setting up second point for rectangle's dimensions
            Rectangle tipBox = new Rectangle(startPoint, endPoint, Color.YELLOW, Color.CYAN);                   //box for tooltip
            tipBox.setStroke(2);                                                                    //setting custom stroke to avoid conflict with global stroke used for shapes
            tipBox.draw(g);

            g.setColor(textColor);
            g.drawString(text, startPoint.x + 2, startPoint.y + textHeight + 4);                          //drawing the text itself

        } else {
            text = null;
            System.out.println("CLEAnING");
            g.clearRect(point.x + 10, point.y + 20 + 10, 50, 20);
        }
    }

    public void updatePoint(int x, int y) {
        point = new Point(x, y);
    }

    public void setButton(Button button1) {
        button = button1;
    }

    public static Button getButton() {
        return button;
    }

    public void setText(String text) {
        this.text = text;
    }

}
