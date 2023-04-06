import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener, MouseInputListener {

    final int B_WIDTH = 1280;
    final int B_HEIGHT = 720;
    private final int DELAY = 25;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;

    private boolean start_drawing = false;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Window window;

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            char keyChar = e.getKeyChar();
            keyPressed = false;

            window.onKeyRelease(keyChar);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressed = true;
            key = e.getKeyCode();
        }
    }

    /**
     * Constructor  initialises board
     */     //Initialize buttons if ob null
    public Board() {
        initBoard();
    }

    private void initBoard() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new TAdapter());

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void InitializeAssets() {
        window = new Window(B_HEIGHT, B_WIDTH);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        window.paint(g, this);

        if (keyPressed) {
            drawNotification("key ", g);
        }
        if (mousePressed) {
            drawNotification("mouse ", g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        window.onClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        window.onPress(e.getX(), e.getY());

        mousePressed = true;
        start_drawing = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        window.onRelease(e.getX(), e.getY());

        mousePressed = false;
        //start_drawing = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void mouseDragged(MouseEvent e) {    }

    @Override
    public void mouseMoved(MouseEvent e) {    }

    private void drawNotification(String text, Graphics g) {
        g.setColor(Color.RED);
        g.drawString(text + key + " pressed", 20, 220);
    }

    @Override
    public void actionPerformed(ActionEvent e) {    //Runs all the time
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }


}