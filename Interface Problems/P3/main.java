package P3;

import java.util.ArrayList;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) {

        Animal amp = new Amphibian() {
            @Override
            public void laysEggs() {

            }

            @Override
            public void waterBreathing() {

            }
        };
        Animal bird = new Birds() {
            @Override
            public void laysEggs() {
                super.laysEggs();
            }

            @Override
            public void flies() {
                super.flies();
            }
        };
        Animal mammal = new Mammal() {
            @Override
            public void givesMilk() {
                super.givesMilk();
            }
        };
        Animal bat = new Bat();
        Animal platypus = new Platypus();
        Animal chimera = new Chimera();

//        ArrayList<Animal> animals = new ArrayList<>();
        LinkedList<Animal> animalsLL = new LinkedList<>();

        animalsLL.add(amp);
        animalsLL.add(bird);
        animalsLL.add(mammal);
        animalsLL.add(bat);
        animalsLL.add(platypus);
        animalsLL.add(chimera);

        System.out.println("DONE " + animalsLL);

    }

}
