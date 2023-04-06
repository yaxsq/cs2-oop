public class main {

    public static void main(String[] args) {

        Animal l1 = new Lion("Lion1", 2);
        Animal l2 = new Lion("Lion2", 3);

        Animal w1 = new Wolf("Wolf1", 2);
        Animal w2 = new Wolf("Wolf3", 3);

        Animal c1 = new Cow("Cow1", 3);
        Animal c2 = new Cow("Cow2", 4);

        Animal g1 = new Goat("Goat1", 4);
        Animal g2 = new Goat("Goat2", 4);

        /*
        l1.eat(c1);
        l1.eat(l2);
        System.out.println();
        c1.eat();
        c2.eat(c1);
        c2.eat(l1);
        System.out.println();

        l1.sleep();
        l1.die();
        l1.sleep();
        l1.eat(l2);
        l1.eat(c2);
        l1.sleep();
        l1.die();
        */

        //Creating a queue
        Queue queue = new Queue();


        queue.enqueue(l1);
//        queue.enqueue(l2);      //second carnivore
//        queue.enqueue(w1);      //third carnivore
        queue.enqueue(c1);
        queue.enqueue(c2);
        queue.enqueue(g1);
        queue.enqueue(g2);
//

        System.out.println();
        queue.printQueue();

        //Getting the carnivore out of the list
        Animal carnivore = queue.extractCarnivore();

        if (carnivore != null) {
            System.out.println("\nCARNIVORE NAME  " + carnivore.name + "\n");
        }

        //queue.printQueue();

        //iteration counter
        int i = 0;

        while (queue.getSize() > 0) {
            for (int j = 0; j < queue.getSize(); j++) {
                Animal animal = queue.getAnimal(j);

                if (Math.random() >= 0.5) {
                    carnivore.eat(animal);
                    queue.removeAt(j);

                } else if (Math.random() <= 0.3) {
                    queue.enqueue(new Herbivore(animal.name + " OFSP 1", 0));
                    queue.enqueue(new Herbivore(animal.name + " OFSP 2", 0));
                    System.out.println(animal.name + "'s offspring produced.");
                }

                queue.printQueue();
                System.out.println();

                i++;
            }
        }

        System.out.println("It took " + i + " iterations for the queue to empty.");


    }

}
