package P2;

public abstract class Car implements Vehicle{

    String name;
    boolean running;

    Car(String name) {
        this.name = name;
        running = false;
    }

    void startEngine() {
        running = true;
    }

}
