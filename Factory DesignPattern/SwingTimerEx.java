import java.awt.EventQueue;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx() throws FileNotFoundException {

        initUI();
    }

    private void initUI() throws FileNotFoundException {

        add(new Board());

        setResizable(true);
        pack();

        setTitle("Java Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = null;
            try {
                ex = new SwingTimerEx();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ex.setVisible(true);
        });
    }
}