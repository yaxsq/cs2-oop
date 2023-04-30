package SwingTimerEx;

import Board.Board;
import DataStructures.Queue;
import DataStructures.Stack;
import DrawingPanel.DrawingPanel;
import Toolbars.LayerBar;
import Toolbars.Toolbar;
import Window.Window;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class SwingTimerEx extends JFrame {

    private static Board board;

    public SwingTimerEx() {
        initUI();
    }

    private void initUI() {

        board = new Board();
        add(board);

        setResizable(false);
        pack();

        setTitle("Java Paint");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });

    }

    /**
     * stores only the current stack in an xml file
     * NOT USED
     */
    public static void saveStacks() {
        try {
            String fileName = System.currentTimeMillis() + "_save.xml";
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/FILES/" + fileName)));
            encoder.writeObject(DrawingPanel.getShapeStack());
//            encoder.writeObject(DrawingPanel.getShapeQueue());
//            encoder.writeObject(Window.getLayerBar());
            encoder.close();

            System.out.println(fileName + " saved!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION - SAVE");
        }
    }

    /**
     * @param fileName
     * loads the single stack stored in the file
     * NOT USED
     */
    public static void loadStack(String fileName) {
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("src/FILES/" + fileName)));
            Stack tempStack = (Stack) decoder.readObject();
            DrawingPanel.setShapeStack(tempStack);
            LayerBar.setFirstStack(tempStack);
//            DrawingPanel.setShapeQueue((Queue) decoder.readObject());
//            Window.setLayerBar((LayerBar) decoder.readObject());
            decoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION - LOAD");
        }
    }

    /**
     * called when file/new is pressed
     */
    public static void refresh() {
        board = new Board();
    }

    /**
     * saves the state of the current layerbutton/stacks in an xml file
     */

    public static void save() {
        try {
            String fileName = System.currentTimeMillis() + "_save.xml";
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/FILES/" + fileName)));
            encoder.writeObject(LayerBar.getAllLayerStacks());
            encoder.close();

            System.out.println(fileName + " saved!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION - SAVE");
        }
    }


    /**
     * @param fileName name of the file being loaded from
     *                 reads the stacks stored in the file and sets it to the current layerbuttons/stacks
     */

    public static void load(String fileName) {
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("src/FILES/" + fileName)));
            LayerBar.setAllLayerStacks( (ArrayList<Stack>) decoder.readObject());
            decoder.close();
        } catch (FileNotFoundException e) {
            System.out.println("NOT SAVED ----");
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION - LOAD");
        }
    }


}