package P3;

public class Chimera implements EggLayer, MilkProvider, Flyer, WaterBreather{

    @Override
    public void laysEggs() {
        System.out.println("chimera lays eggs");
    }

    @Override
    public void flies() {
        System.out.println("chimera files");
    }

    @Override
    public void givesMilk() {
        System.out.println("chimera gives milk");
    }

    @Override
    public void waterBreathing() {
        System.out.println("chimera breathes underwater");
    }
}
