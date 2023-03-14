import java.util.ArrayList;

class Queue {

    ArrayList<Shape> shapes;

    Queue() {
        shapes = new ArrayList<Shape>();                                    //Initializing arrayList
    }

    /**
     * @param shape shape to be added to the queue
     */
    void enqueue(Shape shape) {
        shapes.add(shape);
    }

    /**
     * @return shape at the front of the queue
     */
    Shape dequeue() {
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
    Shape get(int i) {
        return shapes.get(i);
    }

    /**
     * @return size of the queue (arrayList)
     */
    int queueSize() {
        return shapes.size();
    }

    /**
     * Empties the queue
     */
    void emptyQueue() {
        shapes.clear();
    }

}
