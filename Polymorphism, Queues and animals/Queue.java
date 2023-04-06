import java.util.ArrayList;

public class Queue {

    ArrayList<Animal> animals;

    Queue() {
        animals = new ArrayList<>();
    }

    void enqueue(Animal animal) {

        if (animal instanceof Herbivore) {
            animals.add(animal);
            return;
        }

        if (!containsCarnivore() && animal instanceof Carnivore) {
            animals.add(animal);
        } else {
            System.out.println("Carnivore exists already.");
        }

    }

    Animal getFront() {
        return animals.get(0);
    }
    Animal dequeue() {
        Animal animal = animals.get(0);
        animals.remove(0);
        return animal;
    }

    boolean containsCarnivore() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) instanceof Carnivore) {
                return true;
            }
        }
        return false;
    }

    Animal getAnimal (int i) {
        return animals.get(i);
    }

    Animal getCarnivore() {
        Animal carnivore;

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) instanceof Carnivore) {
                return animals.get(i);
            }
        }
        return null;
    }

    Animal extractCarnivore() {
        Animal carnivore;

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) instanceof Carnivore) {
                carnivore = animals.get(i);
                animals.remove(i);
                return carnivore;
            }
        }
        return null;
    }

    Animal removeAt(int i) {
        Animal animal = animals.get(i);
        animals.remove(i);
        return animal;
    }

    void printQueue() {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(i + "  " + animals.get(i).name);
        }
    }

    int getSize() {
        return animals.size();
    }

}
