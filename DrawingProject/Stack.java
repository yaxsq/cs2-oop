import java.util.ArrayList;

class Stack {

    ArrayList<Shape> shapes;

    Stack() {
        shapes = new ArrayList<Shape>();                    //Initializing arrayList
    }

    /**
     * @return the last entered shape
     */
    Shape pop() {
        Shape last = shapes.get(shapes.size() - 1);         //Saving the last element
        shapes.remove(shapes.size() - 1);             //Removing the last element
        return last;
    }

    /**
     * @param shape shape to be pushed
     */
    void push(Shape shape) {
        shapes.add(shape);                                  //Adding a shape (at the end)
    }

    /**
     * @return size of arrayList
     */
    int getStackSize() {
        shapes.trimToSize();                                //Trims empty spaces and returns
        return shapes.size();
    }

    /**
     * @param i index in the arrayList
     * @return shape at index i
     */
    Shape get(int i) {
        return shapes.get(i);
    }

}
