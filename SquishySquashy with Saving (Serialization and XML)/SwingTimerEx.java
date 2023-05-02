import java.awt.EventQueue;
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

        setTitle("Java Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }

}