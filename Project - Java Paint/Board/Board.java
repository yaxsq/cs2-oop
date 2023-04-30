package Board;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import Window.Window;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener, MouseInputListener, Serializable {

    public static final int B_WIDTH = 1280;
    public static final int B_HEIGHT = 720;
    private final int DELAY = 1;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    public static boolean mousePressed = false;

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
        window = new Window();

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
        window.onClick(e, e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        window.onPress(e, e.getX(), e.getY());

        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        window.onRelease(e, e.getX(), e.getY());

        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void mouseDragged(MouseEvent e) {
        window.onDrag(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        window.onMove(e);
    }

    private void drawNotification(String text, Graphics g) {
        g.setColor(Color.RED);
        g.drawString(text + key + " pressed", 20, 220);
    }

    @Override
    public void actionPerformed(ActionEvent e) {    //Runs all the time
        Toolkit.getDefaultToolkit().sync();
        repaint();

        window.actionPerformed(e);
    }

    //setters and getters for filesaving

    public void setKey(int key) {
        this.key = key;
    }

    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public static void setMousePressed(boolean mousePressed) {
        Board.mousePressed = mousePressed;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public int getDELAY() {
        return DELAY;
    }

    public int getKey() {
        return key;
    }

    public Timer getTimer() {
        return timer;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    protected Graphics getComponentGraphics(Graphics g) {
        return super.getComponentGraphics(g);
    }

    public boolean getKeyPressed( ) {
        return keyPressed;
    }

    public boolean getMousePressed() {
        return mousePressed;
    }
}