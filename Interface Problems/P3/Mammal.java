package P3;

public abstract class Mammal implements MilkProvider {

    @Override
    public void givesMilk() {
        System.out.println("mammal gives milk");
    }
}
