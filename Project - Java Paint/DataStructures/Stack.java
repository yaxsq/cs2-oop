package DataStructures;
import Shapes.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Stack implements Serializable {

    ArrayList<Shape> shapes;

    public Stack() {
        shapes = new ArrayList<Shape>();                    //Initializing arrayList
    }

    /**
     * @return the last entered shape
     */
    public Shape pop() {
        Shape last = shapes.get(shapes.size() - 1);         //Saving the last element
        shapes.remove(shapes.size() - 1);             //Removing the last element
        return last;
    }

    /**
     * @param shape shape to be pushed
     */
    public void push(Shape shape) {
        shapes.add(shape);                                  //Adding a shape (at the end)
    }

    /**
     * @return size of arrayList
     */
    public int getStackSize() {
        shapes.trimToSize();                                //Trims empty spaces and returns
        return shapes.size();
    }

    /**
     * @param i index in the arrayList
     * @return shape at index i
     */
    public Shape get(int i) {
        return shapes.get(i);
    }

    //setters and getters for filesaving

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
