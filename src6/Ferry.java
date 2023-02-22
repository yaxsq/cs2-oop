import java.util.ArrayList;

public class Ferry {

    private ArrayList<Truck> trucks;
    private int tail = -1;
    private int head = 0;

    public Ferry () {
        trucks = new ArrayList<Truck>(50);
    }

    public void enqueue (Truck truck) {
        if ( (tail-head) == 50 ) {
            System.out.println("ferry full");
        }
        trucks.set(++tail, truck);
    }

    public void dequeue () {
        if (head != tail) {
            System.out.println("DEQUEUING ");
            trucks.remove(head);
            head++;
        }
        else {
            System.out.printf("EMPTY FERRY");
        }
    }

}
