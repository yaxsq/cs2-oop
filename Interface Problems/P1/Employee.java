package P1;

public abstract class Employee {

    String name;
    int salary;

    protected abstract int calculateBonus();

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

}
