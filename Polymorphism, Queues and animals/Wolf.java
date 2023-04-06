public class Wolf extends Carnivore {

    Wolf(String name, int age) {
        super(name, age);
    }

    void eat(Animal food) {
        if (!dead) {
            System.out.println(this.name + " is eating " + food.name);
        }
        else {
            alreadyDead();
        }
    }
}
