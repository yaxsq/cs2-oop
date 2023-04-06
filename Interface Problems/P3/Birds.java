package P3;

public abstract class Birds implements EggLayer, Flyer{

    @Override
    public void laysEggs() {
        System.out.println("bird lays eggs");
    }

    @Override
    public void flies() {
        System.out.println("bird flies");
    }
}
