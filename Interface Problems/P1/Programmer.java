package P1;

public class Programmer extends Employee implements Promotable{

    Programmer(String name, int salary) {
        super(name, salary);
    }

    @Override
    protected int calculateBonus() {
        return 5000;
    }

    @Override
    public void promote(double newSalary) {
        return ;
    }
}
