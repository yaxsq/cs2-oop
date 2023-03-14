import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.SwingUtilities;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private final int DEFAULT_WIDTH = 600;
    private final int DEFAULT_HEIGHT = 600;

    //    private int x1, y1;// x2, y2;
    private char key;
    private Graphics g;
    private Shape shape;
    private Color color;
    private Point topLeft;
    private Point bottomRight;
    private Point movingPoint;
    private Random random = new Random();
    private Stack shapeStack;
    private Queue shapeQueue;
    private Point[] trianglePoints = new Point[3];
    private int tpCount = 0;

    // CONSTRUCTOR
    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        setFocusable(true);

        //initializing stack and queue
        shapeStack = new Stack();
        shapeQueue = new Queue();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void setUpDrawingGraphics() {
        g = getGraphics();

        //Iterating through the stack and drawing every shape
        try {
            for (int i = 0; i < shapeStack.getStackSize(); i++) {
                shapeStack.get(i).draw(g);
            }
        } catch (Exception ex) {
//            System.out.println("Out of shapes");
        }

        keyPressMessage();
    }

    @Override
    public void mouseClicked(MouseEvent e) {        //used for triangle
//        System.out.println("CLICKED  ");

        if (SwingUtilities.isLeftMouseButton(e) && key == '3') {
//            System.out.println("--------------------MC--------LC");
            trianglePoints[tpCount++] = new Point(e.getX(), e.getY());              //the point is stored in an array
//            System.out.println("POINTS " + trianglePoints[tpCount - 1].x + " " + trianglePoints[tpCount - 1].y + "\n");

            keyPressMessage();

            if (tpCount == 3) {                                             //if the array is full
                tpCount = 0;                                                    //counter is reset
//                System.out.println("\nEMPTIED\n");

                shape = new Triangle(trianglePoints, color);                    //triangle created
                shapeStack.push(shape);                                         //triangle pushed into stack
            }
        }

        setUpDrawingGraphics();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {                                  //If LMB
//            System.out.println("------------------------------LC");
            topLeft = new Point(e.getX(), e.getY());                                //Coordinates saved
//            System.out.println("TOPLEFT " + topLeft.x + " " + topLeft.y);

            color = randColor();                                                //Color generated for the shape being drawn

            //Purging queue if a new point is created (new shape is made) and the queue is not empty
            if (shapeQueue.queueSize() != 0) {
                shapeQueue.emptyQueue();
            }

        } else if (SwingUtilities.isRightMouseButton(e)) {                          //If RMB
//            System.out.println("right pressed");

            try {
//                System.out.println("\nSTACK BEFORE " + shapeStack.stackSize());
                Shape redo = shapeStack.pop();                             //Popping shape
                shapeQueue.enqueue(redo);                                  //Enqueueing the popped shape
//                System.out.println("STACK AFTER " + shapeStack.stackSize());
            } catch (Exception ex) {                                       //Exception in case the stack is empty
                System.out.println("OUT OF SHAPES");
            }

            //Clearing the canvas of any shapes drawn previously and drawing the remaining shapes again
            cleanFrame();

            //resetting the coordinates for the next shape to be made
            topLeft = null;
            bottomRight = null;

            setUpDrawingGraphics();

        } else if (SwingUtilities.isMiddleMouseButton(e)) {                          //If MMB then redoes a single shape

//            for (int i = 0; i < shapeQueue.queueSize(); i++) {
            shape = shapeQueue.dequeue();                                   //Dequeued a shape
            shapeStack.push(shape);                                         //Pushed a shape
//            }

            topLeft = null;
            bottomRight = null;

            setUpDrawingGraphics();                                         //Draws the frame again without the shape
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        bottomRight = new Point(e.getX(), e.getY());            //Saving the coordinates of where the mouse was released
//        System.out.println("RELEASE " + bottomRight.x + " " + bottomRight.y);

        try {
            if (!(topLeft.x == bottomRight.x && topLeft.y == bottomRight.y)) {      //If the mouse has been dragged
                if (key == '1') {                                                      //If the choice is circle

                    shape = new Circle(topLeft, bottomRight, color);                   //new circle created
                    shapeStack.push(shape);                                            //Pushed into stack

                    keyPressMessage();

                } else if (key == '2') {                                            //If the choice is rectangle

                    shape = new Rectangle(topLeft, bottomRight, color);               //New rectangle created
                    shapeStack.push(shape);                                           //Pushed into stack

                    keyPressMessage();

                }
            }
        } catch (Exception ex) {
        }

        setUpDrawingGraphics();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cleanFrame();                                                       //Cleaning frame to avoid ghosting
        setUpDrawingGraphics();

        if (key == '1') {                                                   //if circle
            bottomRight = new Point(e.getX(), e.getY());                    //saves the last coordinate

            //Making sure topleft is topleft (smaller point) and bottomright is bottomright (larger point) and swapping if not
            try {
                if (topLeft.x > bottomRight.x && topLeft != null) {
                    int temp = topLeft.x;
                    topLeft.x = bottomRight.x;
                    bottomRight.x = temp;
                }
                if (topLeft.y > bottomRight.y) {
                    int temp = topLeft.y;
                    topLeft.y = bottomRight.y;
                    bottomRight.y = temp;
                }
            } catch (NullPointerException ex) {
            }
            shape = new Circle(topLeft, bottomRight, color);                //circle created temporarily
            shape.draw(g);                                                  //drawn but not pushed into stack

        } else if (key == '2') {                                            //if rectangle
            bottomRight = new Point(e.getX(), e.getY());                    //saves the last coordinate

            //Making sure topleft is topleft (smaller point) and bottomright is bottomright (larger point) and swapping if not
            try {
                if (topLeft.x > bottomRight.x && topLeft != null) {
                    int temp = topLeft.x;
                    topLeft.x = bottomRight.x;
                    bottomRight.x = temp;
                }
                if (topLeft.y > bottomRight.y) {
                    int temp = topLeft.y;
                    topLeft.y = bottomRight.y;
                    bottomRight.y = temp;
                }
            } catch (NullPointerException ex) {
            }
            shape = new Rectangle(topLeft, bottomRight, color);             //rectangle created temporarily
            shape.draw(g);                                                  //drawn but not pushed into stack

        } /* else if (key == '3') {                                            //if triangle
            bottomRight = new Point(e.getX(), e.getY());                    //saves the last coordinate

            g.drawLine(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
        }
        */
        else if (key == '4') {                                                  //SECRET LINE  MODE
            bottomRight = new Point(e.getX(), e.getY());
//            g.fillOval(bottomRight.x, bottomRight.y, 5, 5);
            shapeStack.push(new Circle(bottomRight, 5, randColor()) );
        }

//        setUpDrawingGraphics();                     //temporary shapes are drawn every time the mouse is dragged
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (key == '3' && tpCount != 0) {                           //If triangle is selected and the point array for triangle is not empty
            movingPoint = new Point(e.getX(), e.getY());
            cleanFrame();
            //Drawing a dynamic line from the last point stored in the trianglePoints array to the point where the mouse is
            g.drawLine(trianglePoints[tpCount - 1].x, trianglePoints[tpCount - 1].y, movingPoint.x, movingPoint.y);

            if (tpCount == 2) {                                     //If two coordinates exist
                //Drawing a static line between the first two points stored
                g.drawLine(trianglePoints[0].x, trianglePoints[0].y, trianglePoints[1].x, trianglePoints[1].y);
            }
        }

        setUpDrawingGraphics();
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("TYPED");
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("key pressed " + e.getKeyChar());
        key = e.getKeyChar();
        keyPressMessage();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @param arr point array
     * @return same array with all values set to null
     */

    //Not used in the program
    private Point[] cleanArray(Point[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        return arr;
    }


    /**
     * @returna randomly generated color
     */
    private Color randColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }


    /**
     * @param key the char pressed
     * @return name of the shape according to the key pressed
     */
    private String getShapeName(char key) {
        switch (key) {
            case '1':
                return "Circle";
            case '2':
                return "Rectangle";
            case '3':
                return "Triangle";
            case '4':
                return "Line **SECRET MODE**";
            default:
                return "Nothing";
        }
    }


    /**
     * Outputs a message at the bottom left of the frame to indicate the current shape being drawn
     */
    private void keyPressMessage() {
        g.setColor(Color.black);
        g.clearRect(5, DEFAULT_HEIGHT - 15, 170, 15);
        g.drawString("Pressed " + key + " drawing " + getShapeName(key), 5, DEFAULT_HEIGHT - 5);
    }

    /**
     * calls clearRect to clean the frame
     */
    private void cleanFrame() {
        g.clearRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    Stack getShapeStack() {
        return shapeStack;
    }

    void setShapeStack(Stack shapeStack) {
        this.shapeStack = shapeStack;
        setUpDrawingGraphics();
    }
}