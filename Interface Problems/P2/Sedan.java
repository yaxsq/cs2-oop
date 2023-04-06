package P2;

public class Sedan extends Car implements Convertible{

    Sedan(String name) {
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

    @Override
    public void openRoof() {
        System.out.println("roof open");
    }

}
