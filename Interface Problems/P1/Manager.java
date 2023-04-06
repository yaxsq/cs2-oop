package P1;

public class Manager extends Employee implements Promotable{

    Manager(String name, int salary) {
        super(name, salary);
    }

    @Override
    protected int calculateBonus() {
        return 10000;
    }

    @Override
    public void promote(double salary) {
        return;
    }
}
