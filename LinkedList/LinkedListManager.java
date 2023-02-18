public class LinkedListManager {
    public static void main(String[] args) {

        LinkedList l = new LinkedList();


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
//        l.atIndex(0, 5);

  //      l.removeAll(1);
        l.printList();
        //PROBLEM 2.2
        /*
        System.out.println("LIST BEFORE removeAt");
        l.printList();
        System.out.println();

        l.removeAt(7);
        l.removeAt(2);

        System.out.println("LIST AFTER removeAt");

        /*
        //PROBLEM 2.3
        l.removeAll(2);
         */

        //PROBLEM 2.4
        //l.printList();

/*
        //PROBLEM 3
        l.addString("abcd");
        l.printList();

        //PROBLEM 4
        String str = l.getString();
        System.out.println("String is " + str);
*/
    }
}
