public class Lion extends Carnivore{

    Lion(String name, int age) {
        super(name, age);
    }

    void eat(Animal food) {
        if (!dead) {
//            if (food instanceof Herbivore) {
                System.out.println(this.name + " is eating " + food.name);
//            } else {
//                System.out.println(this.name + " can not eat " + food.name);
//            }
        }
        else {
            alreadyDead();
        }
    }

}
