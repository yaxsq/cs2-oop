import java.awt.*;

class Rectangle extends Shape {

    Point topLeft;
    Point bottomRight;

    /**
     * @param topLeft     topleft point of the rectangle
     * @param bottomRight bottomleft point of the rectangle
     * @param color       color of the rectangle
     *                    calls the setlocation and setcolor methods and stores the parameters in private variables
     */
    Rectangle(Point topLeft, Point bottomRight, Color color) {
        setLocation(topLeft, bottomRight);
        super.setColor(color);
    }

//    @Override

    /**
     * @param topLeft     the higher point (starting point)
     * @param bottomRight the lower point (ending point)
     *                    sets the location by swapping the variables appropriately if needed
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
    }

    @Override
    void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillRect(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
    }

    @Override
    String shapeToString() {
        return topLeft.x + "," + topLeft.y + "," + bottomRight.x + "," + bottomRight.y + "," + getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue();
    }


}
