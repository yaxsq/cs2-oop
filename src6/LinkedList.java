class LinkedList {
    Node head;


    /**
     * @param index the index in the list to be inserted into
     * @param data the value to be added
     */
    public void addTo(int index, int data) {
        int i = 0;
        Node current = head;
/*
        if (index < 0) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;

        }   */

        //Runs if index is less than zero or the list has only one element
        if (index <= 0 || head.next == null) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;

            //If the index after the first element
        } else if (index > 0) {

            //for loop runs until it reaches the second last current
            for (i = 0; current.next != null; i++) {
                //prev is the previous node
                Node prev = current;
                current = current.next;

                //if the value is in the middle of the list
                if (i >= index - 1) {
                    Node newNode = new Node(data);
                    prev.next = newNode;
                    newNode.next = current;
                    break;
                }

                //if the value is at the end
                if (current.next == null) {
                    Node newNode = new Node(data);
                    current.next = newNode;
                    break;
                }
            }
            /*
            for (i = 0; current.next != null; i++) {
                current = current.next;
                if (i == index) {
                    Node newNode = new Node(data);
                    newNode.next = current.next;
                    current.next = newNode;
                } && i == index-1

                current = current.next;
                prev = current;
                    Node newNode = new Node(data);

                    prev.next = newNode;
                    newNode.next = prev.next.next;
                    current = newNode;
            }
            while (index != i-1) {

                Node prev = current;
                Node newNode = new Node(data);
                newNode.next = current;
                prev.next = newNode;

                i++;
                break;
            }       */
        }/*
        else {
            while (current.next!=null) {
                current = current.next;
            }
            Node newNode = new Node(data);
            current.next = newNode;
        }*/

    }

    /**
     * @param index the index of the value to be removed
     */
    public void removeAt(int index) {
        Node current = head;
        Node prev;
        // try catch to be safe if the index does not exist
        try {

            for (int i = 0; current != null; i++) {

                //removes the first variable
                if (index == i && current.next == head.next) {
                    head = head.next;
                }
                //removes the variable if it is not the first one
                //loop stops before the index to be able to work with the previous and current nodes
                else if (i == index - 1) {
                    prev = current;
                    prev.next = current.next.next;
//                current = current.next.next;
                }

                prev = current;
                current = current.next;
            }
        } catch (Exception e) {
            System.out.println("Element does not exist.");
        }

    }

    public void removeAll(int data) {
        Node current = head;

        while (current != null) {

            if (current.data == data)
                remove(current.data);

            current = current.next;
        }

    }


    /**
     * @param str string to be added
     *            <p>
     *            traverses through all characters of the string and adds each character to the list
     */
    public void addString(String str) {
        for (int i = 0; i < str.length(); i++) {
            append(str.charAt(i));
        }
    }

    /**
     * @return the string which is a combination of all characters' ASCII values stored in the linked list
     * <p>
     * goes through the linked list and converts each integer to its ascii value
     * adds the character to the string
     * the string is then returned
     */

    public String getString() {
        Node current = head;
        String str = "";
        char ch;

        while (current.next != null) {
            str += (char) current.data;
            current = current.next;
        }
        return str;
    }

    public void printList() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public void append(int data) {

        if (head == null) {
            head = new Node(data);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
    }

    public void remove(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            //System.out.println("Removed " + head.data);
            head = head.next;
            System.out.println(" removed ");
            return;

        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

}


/*
        while (i < index) {
            current = current.next;
            i++;
        }

        if (i == index) {
            remove(current.data);
        }
*/