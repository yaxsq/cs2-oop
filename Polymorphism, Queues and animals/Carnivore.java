public class Carnivore extends Animal{

    Carnivore(String name, int age) {
        super(name, age);
    }

    @Override
    void eat(Animal food) {
        if (!dead) {
            System.out.println(this.name + " is eating " + food.name);
            return;
        }
        alreadyDead();
    }

    @Override
    void eat() {
        //DOES NOT DO ANYTHING
    }

}
