package DrawingPanel;

import Board.Board;
import DataStructures.Queue;
import DataStructures.Stack;
import Listeners.mouseEventListener;
import Listeners.keyListener;
import Shapes.*;
import Shapes.Rectangle;
import Shapes.Shape;
import Singletons.GradientWindow;
import Toolbars.LayerBar;
import Toolbars.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

public class DrawingPanel implements mouseEventListener, keyListener, ActionListener, Serializable {

    private static int x, y, width, height;

    private static Stack shapeStack;
    private static Queue shapeQueue;

    private char key;

    private static String currentShape;                               //shape for getting the currently chosen shape
    private static Shape shape;                                                    //shape for making a shape object and pushing in stack
    private Color color;
    private int currentStroke;
    private Color strokeColor;

    private static Point topLeft;
    private static Point bottomRight;
    private Point movingPoint;

    private ArrayList<Circle> line;
    private Color lineColor;

    public DrawingPanel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //initializing stack and queue          // not needed since these are initialized by layerBar
//        shapeStack = new Stack();
//        shapeQueue = new Queue();
    }

    public DrawingPanel() {
    }

    /**
     * @param g
     * @param ob draws the shape being created only
     *           also draws the gradient window if active
     */
    public void paint(Graphics g, ImageObserver ob) {
        g.setColor(Color.magenta);
        g.drawRect(x, y, width, height);

//        setUpDrawingGraphics(g);
        LayerBar.paintAllShapes(g);

        try {
            if (Board.mousePressed) {
                shape.draw(g);                                             //temp shape is drawn only when mouse is pressed
            } else {
                shape = null;
            }
        } catch (Exception ex) {

        }

        if (line != null) {
            shape = null;
            for (int i = 0; i < line.size(); i++) {
                line.get(i).draw(g);
            }
        }

        GradientWindow.getInstance().paint(g, ob);
    }

    public boolean IsClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    /**
     * @param g NOT USED
     *          draws shapes from the current stack
     */
    public static void setUpDrawingGraphics(Graphics g) {
//        g = getGraphics();

        //Iterating through the stack and drawing every shape
        if (shapeStack != null) {
            try {
                for (int i = 0; i < shapeStack.getStackSize(); i++) {
                    shapeStack.get(i).draw(g);
                }
            } catch (Exception ex) {
                System.out.println("Out of shapes");
                return;
            }
        }

//        keyPressMessage();
    }

    @Override
    public void onKeyRelease(char key) {
        this.key = key;
//        System.out.println("DP KEY " + key + " THIS " + this.key);
    }

    @Override
    public void onClick(MouseEvent e) {
//        System.out.println("DP CLICK");

        /*          //code for drawing custom triangle / not used
        if (SwingUtilities.isLeftMouseButton(e) && key == '3') {
            trianglePoints[tpCount++] = new Point(e.getX(), e.getY());              //the point is stored in an array

//            keyPressMessage();

            if (tpCount == 3) {                                             //if the array is full
                tpCount = 0;                                                    //counter is reset

                shape = new Triangle(trianglePoints, color);                    //triangle created
                shapeStack.push(shape);                                         //triangle pushed into stack
            }
        }
         */

    }

    @Override
    public void onPress(MouseEvent e) {
//        System.out.println("DP PRESS");
        line = null;

        if (key != '0' && color != null) {                          //If shape and color are selected

            if (SwingUtilities.isLeftMouseButton(e)) {                                  //If LMB
                topLeft = new Point(e.getX(), e.getY());                                //Coordinates saved
//                System.out.println("TOPLEFT SET");

                //Purging queue if a new point is created (new shape is made) and the queue is not empty
                if (shapeQueue != null) {
                    if (shapeQueue.queueSize() != 0) {
                        shapeQueue.emptyQueue();
                    }
                }

                if (key == '6') {
                    shape = new Pentagram(topLeft, color, strokeColor);
                }

                if (key == '7') {                                   // free drawing starts *if selected
//                    System.out.println("HERE");
                    line = new ArrayList<>();
                    lineColor = color;

                    line.add(new Circle(topLeft, currentStroke, lineColor));
                }

            } else if (SwingUtilities.isRightMouseButton(e)) {                          //If RMB
//                System.out.println("RMBRMBRMB");
                undo();

            } else if (SwingUtilities.isMiddleMouseButton(e)) {                          //If MMB then redoes a single shape
//                System.out.println("MMB MMB MMB ");
                redo();
            }
        }
    }


    @Override
    public void onRelease(MouseEvent e) {
//        System.out.println("DP RELEASE");

        if (SwingUtilities.isLeftMouseButton(e)) {
            bottomRight = new Point(e.getX(), e.getY());

            if (!(topLeft.x == bottomRight.x && topLeft.y == bottomRight.y)) {      //If the mouse has been dragged

                if (key == '1') {
                    shape = new RightAngleTriangle(topLeft, bottomRight, color, strokeColor);
                    shapeStack.push(shape);
                } else if (key == '2') {
                    shape = new EquilateralTriangle(topLeft, bottomRight, color, strokeColor);
                    shapeStack.push(shape);
                } else if (key == '3') {
                    shape = new Rectangle(topLeft, bottomRight, color, strokeColor);
                    shapeStack.push(shape);
                } else if (key == '4') {
                    shape = new Circle(topLeft, bottomRight, color, strokeColor);
                    shapeStack.push(shape);
                } else if (key == '5') {
                    shape = new Hexagon(topLeft, bottomRight, color, strokeColor);
                    shapeStack.push(shape);
                } else if (key == '6') {
                    shapeStack.push(shape);             //Pentagram
                } else if (key == '7') {
                    line.add(new Circle(bottomRight, currentStroke, lineColor));

                    shape = new FreeDrawing(line, lineColor, currentStroke);
                    shapeStack.push(shape);

                    line = null;
                    lineColor = null;
                }
//                System.out.println("DP  SHAPE STACK SIZE IS " + shapeStack.getStackSize());
            }
        }
    }

    @Override
    public void onDrag(MouseEvent e) {
//        System.out.println("DP DRAG");
        bottomRight = new Point(e.getX(), e.getY());                    //saves the last coordinate

        if (key == '1') {
            shape = new RightAngleTriangle(topLeft, bottomRight, color, strokeColor);
        } else if (key == '2') {
            shape = new EquilateralTriangle(topLeft, bottomRight, color, strokeColor);
        } else if (key == '3') {
            shape = new Rectangle(topLeft, bottomRight, color, strokeColor);
        } else if (key == '4') {
            shape = new Circle(topLeft, bottomRight, color, strokeColor);
        } else if (key == '5') {
            shape = new Hexagon(topLeft, bottomRight, color, strokeColor);
        } else if (key == '6') {
            ((Pentagram) shape).changePoints(bottomRight);
        } else if (key == '7') {
            line.add(new Circle(bottomRight, currentStroke, lineColor));
        }
    }

    @Override
    public void onMove(MouseEvent e) {
//        System.out.println("DP MOVE");
    }

    public static void setShapeStack(Stack shapeStack1) {
        shapeStack = shapeStack1;
    }

    public static Stack getShapeStack() {
        return shapeStack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (TopBar.currentColor != null) {
//            if (key != '7') {
            this.color = TopBar.currentColor;
            this.lineColor = TopBar.currentColor;
//            } else {                                                      /// PONETIAL SOLUTION FOR LINEVOLORS
//                this.lineColor = TopBar.currentColor;
//            }
        } else {
            this.color = Color.WHITE;
        }

        if (TopBar.strokeColor == null) {
            TopBar.strokeColor = Color.BLACK;
        }

        strokeColor = TopBar.strokeColor;
        currentStroke = TopBar.stroke;
        currentShape = TopBar.currentShape;
        setKey(currentShape);

    }

    private void setKey(String currentShape) {

        if (currentShape != null) {
            switch (currentShape) {
                case "righttriangle":
                    key = '1';
                    TopBar.currentShape = "righttriangle";
                    break;
                case "equiltriangle":
                    key = '2';
                    break;
                case "rectangle":
                    key = '3';
                    break;
                case "circle":
                    key = '4';
                    break;
                case "hexagon":
                    key = '5';
                    break;
                case "pentagram":
                    key = '6';
                    break;
                case "free":
                    key = '7';
                    break;
                default:
                    key = '0';
                    break;
            }
        }
    }


    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void undo() {

        try {
            Shape redo = shapeStack.pop();                             //Popping shape
            shapeQueue.enqueue(redo);                                  //Enqueueing the popped shape
        } catch (Exception ex) {                                       //Exception in case the stack is empty
            System.out.println("OUT OF SHAPES");
        }

        //resetting the coordinates for the next shape to be made
        topLeft = null;
        bottomRight = null;
    }

    public static void redo() {
        if (shapeQueue.queueSize() > 0) {
            Shape redo = shapeQueue.dequeue();                                   //Dequeued a shape
            shapeStack.push(redo);                                               //Pushed a shape

            topLeft = null;
            bottomRight = null;
        } else {
            System.out.println("Redo Queue empty.");
        }
    }

    public static void resetShapeStacknQueue() {
        shapeStack = null;
        shapeQueue = null;
    }

    //setters and getters for filesaving

    public void setKey(char key) {
        this.key = key;
    }

    public static void setWidth(int width) {
        DrawingPanel.width = width;
    }

    public static void setHeight(int height) {
        DrawingPanel.height = height;
    }

    public static void setY(int y) {
        DrawingPanel.y = y;
    }

    public static void setBottomRight(Point bottomRight) {
        DrawingPanel.bottomRight = bottomRight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void setCurrentShape(String currentShape) {
        DrawingPanel.currentShape = currentShape;
    }

    public void setCurrentStroke(int currentStroke) {
        this.currentStroke = currentStroke;
    }

    public void setLine(ArrayList<Circle> line) {
        this.line = line;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setMovingPoint(Point movingPoint) {
        this.movingPoint = movingPoint;
    }

    public static void setShape(Shape shape1) {
        shape = shape1;
    }

    public static void setShapeQueue(Queue shapeQueue) {
        DrawingPanel.shapeQueue = shapeQueue;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public static void setTopLeft(Point topLeft) {
        DrawingPanel.topLeft = topLeft;
    }

    public static void setX(int x) {
        DrawingPanel.x = x;
    }

    public static Queue getShapeQueue() {
        return shapeQueue;
    }

    public Shape getShape() {
        return shape;
    }

    public ArrayList<Circle> getLine() {
        return line;
    }

    public char getKey() {
        return key;
    }

    public Color getColor() {
        return color;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public int getCurrentStroke() {
        return currentStroke;
    }

    public static Point getBottomRight() {
        return bottomRight;
    }

    public Point getMovingPoint() {
        return movingPoint;
    }

    public static Point getTopLeft() {
        return topLeft;
    }

    public static String getCurrentShape() {
        return currentShape;
    }

}