import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author uakhan
 * This class creates a circle
 */
class Circle extends Shape {
    private int size;
    private Point topLeft;
    private Point bottomRight;

    /**
     * @param topLeft     the higher point
     * @param bottomRight the lower point
     * @param C           color
     *                    CONSTRUCTOR
     */
    Circle(Point topLeft, Point bottomRight, Color C) {
        setLocation(topLeft, bottomRight);
        setSize(topLeft, bottomRight);
        setColor(C);
    }

    /**
     * @param topLeft topLeft point
     * @param size    size
     * @param c       color
     *                Used only when reading shape from file
     */
    Circle(Point topLeft, int size, Color c) {
        this.topLeft = topLeft;
        setSize(size);
        setColor(c);
    }

    /**
     * @param topLeft     the higher point
     * @param bottomRight the lower point
     *                    uses the length formula between two points to calculate the size by using the coordinates input
     *                    sets size to a default size of 10 if it is lower than 10
     */
    void setSize(Point topLeft, Point bottomRight) {

        //LENGTH FORMULA ( (X2-X1)^2 + (Y2-Y1)^2 )^1/2
        this.size = (int) (Math.sqrt(Math.pow((bottomRight.x - topLeft.x), 2) + Math.pow((bottomRight.y - topLeft.y), 2)));

        if (this.size < 10)
            size = 10;
    }

    /**
     * @param size size in int (precealculated)
     *             Used when reading shape from file only
     */
    void setSize(int size) {
        this.size = size;
    }

    /**
     * @param topLeft     a point for the circle
     * @param bottomRight a point for the circle
     *                    swaps the points depending on their size
     */
    void setLocation(Point topLeft, Point bottomRight) {
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

        topLeft = new Point(bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);

    }

    /**
     * @return returns the size of the circle
     */
    int getSize() {
        return size;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(topLeft.x, topLeft.y, getSize(), getSize());
    }

    @Override
    String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + getSize() + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }


}