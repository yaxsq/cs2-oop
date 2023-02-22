public class Truck {

    private Car[] cars;
    private int basePointer = 0;
    private int topPointer = -1;

    public Truck () {
        cars = new Car[20];
    }

    public void load (Car car) {
        if (topPointer < 19) {
            cars[++topPointer] = car;
            return;
        }
        System.out.println("FULL");
    }

    public void unload() {
        if (topPointer > basePointer) {
            topPointer--;
        }
    }

    public void peek() {
        System.out.println(cars[topPointer].getName());
    }

    public boolean isFull () {
        if (topPointer == 19) {
            return true;
        }
        return false;
    }
}
