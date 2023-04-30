package Shapes;

import Shapes.Shape;
import Toolbars.TopBar;

import java.awt.*;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {

    public Point topLeft;           //changed to public need to make getter
    protected int width;
    protected int height;
    protected Color stroke_color;
    protected Color button_color;
    protected int stroke;

    /**
     * @param topLeft     topleft point of the rectangle
     * @param bottomRight bottomleft point of the rectangle
     * @param color       color of the rectangle
     *                    calls the setlocation and setcolor methods and stores the parameters in private variables
     */
    public Rectangle(Point topLeft, Point bottomRight, Color color, Color strokeColor) {
        setLocation(topLeft, bottomRight);
        super.setColor(color);
        this.strokeColor = strokeColor;
        this.stroke = TopBar.stroke;                                    //explicitly called topbar.stroke to avoid conflict with rectangle's stroke
    }

    public Rectangle() {
    }

    public Rectangle(int x, int y, int width, int height) {
        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        button_color = Color.white;
        stroke_color = Color.gray;
        stroke = 2;
    }

    public Rectangle(Point topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        button_color = Color.white;
        stroke_color = Color.gray;
        stroke = 2;
    }


    //Takes in x and y
    public Rectangle(int x, int y, int width, int height, Color button_color, Color stroke_color, int stroke) {
        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.button_color = button_color;
        this.stroke_color = stroke_color;
        this.stroke = stroke;
    }

    //Takes in one point, same x y
    public Rectangle(Point p, int width, int height, Color button_color, Color stroke_color, int stroke) {
        this(p.x, p.y, width, height, button_color, stroke_color, stroke);
    }

    /**
     * @param topLeft     the higher point (starting point)
     * @param bottomRight the lower point (ending point)
     *                    sets the location by swapping the variables appropriately if needed
     */
    public void setLocation(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;

        if (topLeft.x > bottomRight.x) {
            int temp = topLeft.x;
            topLeft.x = bottomRight.x;
            bottomRight.x = temp;
        }
        if (topLeft.y > bottomRight.y) {
            int temp = topLeft.y;
            topLeft.y = bottomRight.y;
            bottomRight.y = temp;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (strokeColor == null) {
            g.setColor(super.getColor());
            g.fillRect(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
        } else {
            g.setColor(strokeColor);
            g.fillRect(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
            g.setColor(super.getColor());
            g.fillRect(topLeft.x + stroke, topLeft.y + stroke, (bottomRight.x - topLeft.x) - stroke*2, (bottomRight.y - topLeft.y) - stroke*2);
        }
    }

    public void paint(Graphics g) {
        g.setColor(stroke_color);
//        g.fillRect(topLeft.x - width/2 - stroke , topLeft.y - height/2 - stroke, width + stroke*2, height + stroke*2);
        g.fillRect(topLeft.x, topLeft.y, width, height);
        g.setColor(button_color);
        g.fillRect(topLeft.x + stroke, topLeft.y + stroke, width - (stroke * 2), height - (stroke * 2));
    }

    @Override
    public String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    //setters and getters for filesaving

    @Override
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        super.setStrokeColor(strokeColor);
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public void setButton_color(Color button_color) {
        this.button_color = button_color;
    }

    @Override
    public Point getTopLeft() {
        return topLeft;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getStroke() {
        return stroke;
    }

    @Override
    public Color getStrokeColor() {
        return super.getStrokeColor();
    }

    public Color getStroke_color() {
        return stroke_color;
    }

    public Color getButton_color() {
        return button_color;
    }
}