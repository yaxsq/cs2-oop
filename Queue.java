import java.util.ArrayList;

public class Queue {

    private ArrayList<Truck> trucks = new ArrayList<Truck>(5);
    private Truck[] arr = new Truck[5];
    private int tail = -1;
    private int head = 0;
    private int size = 0;

    public Queue(int size) {
        trucks = new ArrayList<Truck>(size);
    }

    public void enqueue (Truck truck) {
        trucks.add(truck);
        tail++;
    }

    public Truck dequeue () {
        Truck truck = trucks.get(tail);
        trucks.remove(tail--);
        return truck;
    }

    public boolean isFull () {
        if (tail == size-1) {
            return true;
        }
        return false;
    }

    public Truck peek () {
        Truck truck = trucks.get(tail);
        return truck;
    }






}


