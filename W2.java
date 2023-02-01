import java.util.Scanner;

class Book {

    public String title;
    public String author;
    public String isbn;
    public int pages;

    public Book(String title, String author, String isbn, int pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }

    public void summary() {
        System.out.println("Title " + this.title);
        System.out.println("AUTHOR " + this.author);
        System.out.println("ISBM " + isbn);
        System.out.println("pages " + pages);
    }

}

class Car {

    String make;
    String model;
    int year;
    String color;
    int wheels;
    int doors;

    public Car(String make, String model, int year, String color, int wheels, int doors) {

        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.wheels = wheels;
        this.doors = doors;

    }

    public void deets() {
        System.out.println("DEETS");
        System.out.println(make);
        System.out.println(model);
        System.out.println(year);
        System.out.println(color);
        System.out.println(wheels);
        System.out.println(doors);

    }


}

class Person {

    String name;
    int age;
    Pet pet;

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    public void getDetails() {
        System.out.println("PERSON DEETS");
        System.out.println(name);
        System.out.println(age);
        System.out.println(pet.name);
    }

}

class Pet {

    String name;
    String breed;
    int age;

    public Pet(String name, String breed, int age) {

        this.name = name;
        this.breed = breed;
        this.age = age;

    }

    public void getDetails() {
        System.out.println("PERSON DEETS");
        System.out.println(name);
        System.out.println(breed);
        System.out.println(age);
    }

}

class Persons {

    String pername;
    String petname;

    public Persons(String pername, String petname) {
        this.pername = pername;
        this.petname = petname;
    }

    public void getDeets() {
        System.out.println("DEETS");
        System.out.println(pername);
        System.out.println(petname);
    }

}


public class Main {

    public static void main(String[] args) {

        Persons[] arr = new Persons[3];

        String[] human = new String[5];
        human[0] = "aaa";
        human[1] = "bbb";
        human[2] = "ccc";
        human[3] = "ddd";
        human[4] = "eee";

        String[] pet = new String[5];
        pet[0] = "apet";
        pet[1] = "bpet";
        pet[2] = "cpet";
        pet[3] = "dpet";
        pet[4] = "epet";


        for (int i = 0; i < arr.length; i++) {
            Persons banda = new Persons( human[(int)(Math.random()*5)], pet[(int)(Math.random()*5)] );
            arr[i] = banda;
            arr[i].getDeets();
        }


        /*
        Pet dog = new Pet("DOGGO", "GOLDEN", 21);
        Person weirdo = new Person("Naam", 22, dog);

        System.out.println("PERSON DEETSSSS MAIN");
        weirdo.getDetails();

        System.out.println();
        System.out.println("PET DEETS MAIN");
        dog.getDetails();

         */

        /*

        Scanner in = new Scanner(System.in);

        Car[] arr = new Car[3];

        String make;
        String model;
        int year;
        String color;
        int wheels;
        int doors;

        for (int i = 0; i < 3; i++) {

            System.out.println("ENTER");
            make = in.next();
            model = in.next();
            year = in.nextInt();
            color = in.next();
            wheels = in.nextInt();
            doors = in.nextInt();

            Car obx = new Car(make, model, year, color, wheels, doors);
            arr[i] = obx;

            obx.deets();
        }

        System.out.println(arr[0].model);
        System.out.println(arr[1].model);
        System.out.println(arr[2].model);

         */

        /*
        Book harrypotter = new Book("Harry Potter", "JK", "4444-2-2", 420);

        System.out.println("OUTPUT XXXXx");
        harrypotter.summary();

         */

    }
}
