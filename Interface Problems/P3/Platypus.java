package P3;

public class Platypus extends Mammal implements EggLayer{
    @Override
    public void givesMilk() {
        System.out.println("platylus gives milk");
    }

    @Override
    public void laysEggs() {
        System.out.println("platypus lays eggs");
    }
}
