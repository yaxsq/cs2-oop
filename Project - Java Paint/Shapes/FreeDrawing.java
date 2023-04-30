package Shapes;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class FreeDrawing extends Shape implements Serializable {

    private ArrayList<Circle> circles;
    private int stroke;
    private Color lineColor;

    public FreeDrawing(ArrayList<Circle> circles, Color color, int stroke) {
        this.circles = (ArrayList<Circle>) circles.clone();
        setStroke(stroke);
        lineColor = new Color(color.getRed(), color.getBlue(), color.getGreen());          //deep copying
    }

    public FreeDrawing() {}

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < circles.size(); i++) {
            g.setColor(lineColor);
            g.fillOval(circles.get(i).getX(), circles.get(i).getY(), stroke, stroke);
        }
    }

    @Override
    public String shapeToString() {
        return circles + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

    public void setStroke(int stroke) {             //set to public bc of shape having a public setter for file saving
        if (stroke < 2) {
            this.stroke = 2;
            return;
        }
        this.stroke = stroke;
    }

    //setters and getters for filesaving

    @Override
    public int getStroke() {
        return stroke;
    }

    public void setCircles(ArrayList<Circle> circles) {
        this.circles = circles;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

}
