package DataStructures;
import Shapes.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Queue implements Serializable {

    ArrayList<Shape> shapes;

    public Queue() {
        shapes = new ArrayList<Shape>();                                    //Initializing arrayList
    }

    /**
     * @param shape shape to be added to the queue
     */
    public void enqueue(Shape shape) {
        shapes.add(shape);
    }

    /**
     * @return shape at the front of the queue
     */
    public Shape dequeue() {
        if (shapes.size() > 0) {
            Shape firstShape = shapes.get(0);
            shapes.remove(0);
            return firstShape;
        }
        return null;
    }

    /**
     * @param i index of the arrayList
     * @return shape at index i in the arraylist
     * NEVER USED
     */
    public Shape get(int i) {
        return shapes.get(i);
    }

    /**
     * @return size of the queue (arrayList)
     */
    public int queueSize() {
        return shapes.size();
    }

    /**
     * Empties the queue
     */
    public void emptyQueue() {
        shapes.clear();
    }

    //setters and getters for filesaving

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

}
