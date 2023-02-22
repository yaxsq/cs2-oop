import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MAIN {

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("cars.txt");
        Scanner in = new Scanner(file);

        Car[] cars = new Car[4];
        String[] stringSplit;
        String[] car = new String[fileLineCount(file)];
        String line = " ";
        int carArrayCounter = 0;

        //Extracting data from file and storing in car array
        while (in.hasNextLine()) {

            //extracing line and splitting
            line = in.nextLine();
            stringSplit = line.split(":");

            //if not the title, gets details in line
            if (stringSplit.length == 2) {
                line = stringSplit[1].strip();
            }

            //if (!line.equalsIgnoreCase(""))
                car[carArrayCounter++] = line;
            //System.out.println(line);
        }

        System.out.println("LOOP");
        line = "";
        int carArrCount = 0;

        for (int i = 0; i < car.length; i++) {
            if ( car[i].equalsIgnoreCase("")) {
                line = line.substring(0, line.length()-1);
                System.out.println(line);;
                cars[carArrCount++] = new Car(line);
                line = "";
            }
            else {
                line += car[i] + "'";
            }

        }

        System.out.println("LINE COUNT " + fileLineCount(file));

        //printArr(car);)

        Truck truck1 = new Truck();
        Truck truck2 = new Truck();
        for (int i = 0; i < 32; i++) {
            truck1.load(cars[2]);
            truck1.peek();
            if (truck1.isFull()) {
                System.out.println("TRUCK 2");
                truck2.load(cars[1]);
                truck2.peek();
            }
        }

        Ferry ferry1 = new Ferry();
        ferry1.enqueue(truck1);
        ferry1.enqueue(truck2);

        /*
        for (int i = 0; i < cars.length; i++) {
            System.out.println("NAME " + cars[i].getName() );
            System.out.println("END  " + cars[i].getEnd() );
        }\
         */



        /*
        Truck truck1 = new Truck();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[1]);
        truck1.peek();
        truck1.load(cars[2]);
        truck1.peek();
        truck1.load(cars[3]);
        truck1.peek();
        truck1.load(cars[2]);
        truck1.peek();
        truck1.load(cars[3]);
        truck1.peek();

        System.out.println("UNLOAD");

        truck1.unload();
        truck1.peek();
        truck1.unload();
        truck1.peek();
        truck1.unload();
        truck1.peek();
        truck1.unload();
        truck1.peek();
        */

        in.close();

        /*

        l.append(2);
        l.append(0);
        l.append(1);
        l.append(2);
        l.append(3);

       // l.addTo(-2, 4);
        //l.addTo(3, 33);
        //l.addTo(77, 66);
        l.addTo(1, 555);

        //at index +1     negative

        //PROBLEM 2.1
        l.atIndex(0, 5);

        l.removeAll(1);
        l.printList();
        //PROBLEM 2.2

        System.out.println("LIST BEFORE removeAt");
        l.printList();
        System.out.println();

        l.removeAt(7);
        l.removeAt(2);

        System.out.println("LIST AFTER removeAt");


        //PROBLEM 2.3
        l.removeAll(2);


        //PROBLEM 2.4
        //l.printList();


        //PROBLEM 3
        l.addString("abcd");
        l.printList();

        //PROBLEM 4
        String str = l.getString();
        System.out.println("String is " + str);

        */

    }

    public static int fileLineCount(File file) throws FileNotFoundException {
        int count = 0;
        Scanner in = new Scanner(file);

        while (in.hasNextLine()) {
            in.nextLine();
            count++;
        }

        return count;
    }

    public static void printArr(String[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /*
    public void printCarNames () {
        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i].getName() );
        }
    }
    */
}
