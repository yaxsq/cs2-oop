public class Herbivore extends Animal{

    Herbivore(String name, int age) {
        super(name, age);
    }

    @Override
    void eat(Animal food) {
//        System.out.println(this.name + " is eating plants.");
        System.out.println(this.name + " can not eat " + food.name);

    }

    void eat() {
        if (!dead) {
            System.out.println(this.name + " is eating plants.");
            return;
        }
        alreadyDead();
    }

}
