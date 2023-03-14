import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;


class DrawingFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Drawing Program");
        frame.setDefaultCloseOperation(3);

        DrawingPanel panel = new DrawingPanel();

        frame.add(panel);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {                   //File has to read shapes
                System.out.println("DRAW ANYTHING (as long as it is a circle, rectangle or triangle)");

                try {
                    File file = new File("shapes.txt");

                    if (file.exists() && file.canRead()) {

                        Scanner in = new Scanner(file);
                        String[] lineSplit;                             //Array to store separated elements
                        int separatorCount = 0;

                        Stack shapeStack = new Stack();

                        if (getFileLineAmount(file) != 0) {                             //If file is populated

                            for (int i = 0; i < getFileLineAmount(file); i++) {         //Loops through all lines
                                String line = in.nextLine();                            //Extracts a line
                                separatorCount = 0;

                                for (int j = 0; j < line.length(); j++) {
                                    if (line.charAt(j) == ',') {                        //Gets the separator count for the line
                                        separatorCount++;
                                    }
                                }

                                lineSplit = line.split(",");                       //Splitting and storing in array

                                if (separatorCount == 5) {                              //If circle
                                    Point topLeft = new Point(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]));
                                    int size = Integer.parseInt(lineSplit[2]);
                                    int red = Integer.parseInt(lineSplit[3]);
                                    int green = Integer.parseInt(lineSplit[4]);
                                    int blue = Integer.parseInt(lineSplit[5]);

                                    //variables extracted and circle created and pushed
                                    shapeStack.push(new Circle(topLeft, size, new Color(red, green, blue)));
                                }

                                if (separatorCount == 6) {                              //If rectangle
                                    Point topLeft = new Point(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]));
                                    Point bottomRight = new Point(Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]));
                                    int red = Integer.parseInt(lineSplit[4]);
                                    int green = Integer.parseInt(lineSplit[5]);
                                    int blue = Integer.parseInt(lineSplit[6]);

                                    //variables extracted and rectangle created and pushed
                                    shapeStack.push(new Rectangle(topLeft, bottomRight, new Color(red, green, blue)));
                                }

                                if (separatorCount == 8) {                              //If triangle
                                    Point[] points = new Point[3];
                                    points[0] = new Point(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[3]));
                                    points[1] = new Point(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[4]));
                                    points[2] = new Point(Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[5]));

                                    int red = Integer.parseInt(lineSplit[6]);
                                    int green = Integer.parseInt(lineSplit[7]);
                                    int blue = Integer.parseInt(lineSplit[8]);

                                    //variables extracted and triangle created and pushed
                                    shapeStack.push(new Triangle(points, new Color(red, green, blue)));
                                }

                            }
                        }

                        in.close();
                        panel.setShapeStack(shapeStack);            //shapeStack passed into panel
                    }

                } catch (Exception ex) {
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Shutting down");

                try {
                    File file = new File("shapes.txt");
                    PrintWriter printWriter = new PrintWriter(file);

                    Stack shapeStack = panel.getShapeStack();                       //Gets the shapeStack from DrawingPanel
                    for (int i = 0; i < shapeStack.getStackSize(); i++) {
                        printWriter.println(shapeStack.get(i).shapeToString());     //Writes the attributes of all shapes to a line in the file
                    }

                    printWriter.close();
                } catch (Exception ex) {
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * @param file file to be read from
     * @return the amount of lines in the file
     * @throws FileNotFoundException
     */
    private static int getFileLineAmount(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        int lines = 0;

        while (in.hasNextLine()) {
            in.nextLine();
            lines++;
        }

        return lines;
    }

}