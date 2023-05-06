import CustomExceptions.MoreThanThreeDigitsException;
import CustomExceptions.NegativeInputException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        p1();
//        p2();
//        p3();
//        p4();
//        p5();
    }

    private static void p1() {
        System.out.println("      PROBLEM 1");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter first number: ");
        String x = in.nextLine();
        System.out.print("Enter second number: ");
        String y = in.nextLine();

        try {
            int n1 = Integer.parseInt(x);
        } catch (NumberFormatException ex) {
            multiplyString(Integer.parseInt(y), x);
            System.exit(0);
        }

        try {
            int n2 = Integer.parseInt(y);
        } catch (NumberFormatException ex) {
            multiplyString(Integer.parseInt(x), y);
            System.exit(0);
        }

        System.out.println("Result: " + (Integer.parseInt(x) * Integer.parseInt(y)));
    }

    private static void p2() {
        System.out.println("      PROBLEM 2");
        Scanner in = new Scanner(System.in);
        int[] arr = new int[5];

        System.out.print("Input index: ");
        int index = in.nextInt();                                   //taking in index and value
        System.out.print("Input value: ");
        int value = in.nextInt();

        try {
            arr[index] = value;                                     //trying to store the value
        } catch (ArrayIndexOutOfBoundsException ex) {
            int[] newArr = new int[index + 1];                        //new array created according to the index input

            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];                                 //copying old values into the new array
            }
            newArr[index] = value;                                  //setting value at the index input
            arr = newArr;                                            //setting the new array's reference to the original array

        }
        printArray(arr);
    }

    private static void p3() {
        System.out.println("      PROBLEM 3");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter first number: ");
        String x = in.nextLine();                               //taking in the numbers
        System.out.print("Enter second number: ");
        String y = in.nextLine();

        try {                                                   //trying normal subtraction
            System.out.println("Result " + (Integer.parseInt(x) - Integer.parseInt(y)));
        } catch (NumberFormatException ex) {
            try {
                int n1 = Integer.parseInt(x);                   //checking if x is the string
            } catch (NumberFormatException e) {
                try {                                           //trying to print substring
                    System.out.println(x.substring(0, x.length() - Integer.parseInt(y)));
                } catch (StringIndexOutOfBoundsException exc) {
                    System.out.println("Not Possible");         //number too large
                }
                System.exit(0);
            }

            try {
                int n2 = Integer.parseInt(y);                   //checking if y is the string
            } catch (NumberFormatException e) {
                try {                                           //trying ti print substring
                    System.out.println(y.substring(0, y.length() - Integer.parseInt(x)));
                } catch (StringIndexOutOfBoundsException exc) {
                    System.out.println("Not possible");         //number too large
                }
                System.exit(0);
            }
        }
    }

    private static void p4() {
        System.out.println("      PROBLEM 4");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a three digit number: ");
        int x = in.nextInt();                                   //taking in the three-digit number
        int tempX = 0;

        try {
            try {
                tempX = x;
                if (tempX < 0) {
                    throw new NegativeInputException();
                }
            } catch (NegativeInputException ex) {
                ex.printStackTrace();
                System.exit(0);
            }

            int i = 0;

            if (x >= 0) {
                while (tempX > 0) {
                    tempX = tempX / 10;                             //trying to determine the number of digits
                    i++;
                }

                if (i > 3) {
                    throw new MoreThanThreeDigitsException();       //exception thrown if digits > 3
                } else {
                    int sum = 0;

                    while (x > 0) {
                        sum += x % 10;                                //sum calculated and printed if no exception called
                        x = x / 10;
                    }
                    System.out.println("Sum: " + sum);
                }
            } else {
                System.out.println("Invalid input.");
            }
        } catch (MoreThanThreeDigitsException ex) {
            ex.printStackTrace();
        }
    }

    private static void p5() {
        System.out.println("      PROBLEM 5");
        Scanner in = new Scanner(System.in);
//        double x = in.nextDouble();
//        double y = in.nextDouble();
        System.out.println("Enter numerator: ");
        int x = in.nextInt();                           //taking in the numbers
        System.out.println("Enter denominator: ");
        int y = in.nextInt();

        try {
            double result = x / y;                        //dividing here to catch an exception
        } catch (ArithmeticException ex) {
            System.out.println("Invalid input - Can not divide by zero.");

            while (y == 0) {
                System.out.println("Enter the divisor again: ");            //taking in y again until it is not zero
                y = in.nextInt();
            }
        }

        System.out.println("Result : " + (x / y));        //final result with y != 0

    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void multiplyString(int n, String text) {
        for (int i = 0; i < n; i++) {
            System.out.print(text);
        }
        System.out.println();
    }

}