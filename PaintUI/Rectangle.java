import java.awt.*;

class Rectangle {
    protected Point topLeft;
    protected int width;
    protected int height;
    protected int stroke;
    private Color button_color;
    private Color stroke_color;

    Rectangle() {       }

    Rectangle(int x, int y, int width, int height) {
        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        button_color = Color.white;
        stroke_color = Color.gray;
        stroke = 2;
    }

    Rectangle(Point topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        button_color = Color.white;
        stroke_color = Color.gray;
        stroke = 2;
    }


    //Takes in x and y
    Rectangle(int x, int y, int width, int height, Color button_color, Color stroke_color, int stroke) {
        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.button_color = button_color;
        this.stroke_color = stroke_color;
        this.stroke = stroke;
    }


    /*
    //Takes in one point, same x y
    public Rectangle(Point p, int width, int height, Color button_color, Color stroke_color, int stroke)
    {
        this(p.x, p.y, width, height, button_color, stroke_color, stroke);
    }
     */

    void paint(Graphics g) {
        g.setColor(stroke_color);
//        g.fillRect(topLeft.x - width/2 - stroke , topLeft.y - height/2 - stroke, width + stroke*2, height + stroke*2);
        g.fillRect(topLeft.x, topLeft.y, width, height);
        g.setColor(button_color);
        g.fillRect(topLeft.x + stroke, topLeft.y + stroke, width - (stroke * 2), height - (stroke * 2));
    }
}
