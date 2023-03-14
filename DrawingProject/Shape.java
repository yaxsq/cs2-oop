import java.awt.*;

abstract class Shape {

    protected Color color;

    /**
     * @param g Graphics
     *          draws the shape it is called for
     */
    abstract void draw(Graphics g);

    /**
     * @return the attributes of the shape in a string
     */
    abstract String shapeToString();


     //setLocation not an abstract function because parameters need to be defined in the superclass and the parameters have to be different for each shape

    protected Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

}