public abstract class Animal {

    protected String name;
    protected int age;
    protected boolean dead;

    /**
     * @param name animal's name
     * @param age animal's age
     *            CONSTRUCTOR
     */
    Animal(String name, int age) {
        this.name = name;
        this.age = age;
        dead = false;
    }

    //Abstract eat method
    abstract void eat(Animal food);
    abstract void eat();

    /*                              //Original eat method
    void eat() {
        if (!dead) {
            System.out.println(this.name + " is eating.");
            return;
        }
        alreadyDead();
    }
     */


    /**
     * Animal sleeps if not dead
     */
    void sleep() {
        if (!dead) {
            System.out.println(this.name + " is sleeping.");
            return;
        }
        alreadyDead();
    }

    /**
     * Animal dies if not dead
     */
    void die() {
        if (!dead) {
            System.out.println(this.name + " died.");
            dead = true;
            return;
        }
        System.out.println(this.name + " already died.");
    }

    /**
     * prints a message about animal's death if dea already
     */
    void alreadyDead() {
        System.out.println(this.name + " is dead.");
    }

    /*
    String getName() {
        return this.name;
    }
     */
}
