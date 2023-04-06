package P2;

public class SUV extends Car{

    SUV(String name) {
        super(name);
    }

    @Override
    public int accelerate(int speed) {
        return 0;
    }

    @Override
    public int decelerate(int speed) {
        return 0;
    }
}
