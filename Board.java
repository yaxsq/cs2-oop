import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel
        implements ActionListener, MouseInputListener {

    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 25;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;

    private boolean start_drawing = false;

    private ImageIcon[] images;
    private ImageIcon squished;
    private ArrayList<Ant> ants;
    private ArrayList<Ant> deadAnts;
    private int score;
    private Point mouseMoved;
    private ArrayList<Point> mouseMoving;


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            keyPressed = false;

            if (key == KeyEvent.VK_SPACE) {

            }

        }

        @Override
        public void keyPressed(KeyEvent e) {

            keyPressed = true;
            key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

            }

        }
    }

    public Board() {
        initBoard();
    }

    private void InitializeAssets() {
        ants = new ArrayList<>();
        deadAnts = new ArrayList<>();
        mouseMoving = new ArrayList<>();
        score = 0;

        //Loading images
        for (int i = 0; i < images.length; i++) {
            if (i <= 9) {
                images[i] = new ImageIcon("src/ant/tile00" + i + ".png");
            } else {
                images[i] = new ImageIcon("src/ant/tile0" + i + ".png");
            }
        }

        //separate image for squished
        squished = new ImageIcon("src/ant/tile023.png");

    }

    private void initBoard() {

        images = new ImageIcon[22];

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Drawing score string
        g.setColor(Color.RED);
        g.drawString("SCORE: " + score, 10, 10);

        //Drawing ants that are alive
        for (int i = 0; i < ants.size(); i++) {
            ants.get(i).paint(g, this);
        }

        //Drawing ants that are dead
        for (int i = 0; i < deadAnts.size(); i++) {
            deadAnts.get(i).paint(g, this);
        }

//        System.out.println("ANTS " + ants.size());
//        System.out.println("DEAD " + deadAnts.size());

        if (keyPressed) {
            drawNotification("key ", g);
        }

        if (mousePressed) {
            drawNotification("mouse ", g);
        }

    }

    private void drawNotification(String text, Graphics g) {
        g.setColor(Color.RED);
        g.drawString(text + key + " pressed", 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();

        //Probability for generating ants
        double probability = Math.random();

        if (probability > 0.75) {            //Generating normal ant
            ants.add(new Ant((int) (Math.random() * B_WIDTH), B_HEIGHT, images, squished.getImage()));
        }
        if (probability > 0.95) {           //Generating wavy ant
            ants.add(new WavyAnt((int) (Math.random() * B_WIDTH), B_HEIGHT, images, squished.getImage()));
        }

        for (int i = 0; i < ants.size(); i++) {
            Ant ant = ants.get(i);

            if (ant.getAlive()) {           //Updating images if alive
                ant.current_image = images[(int) (Math.random() * (images.length - 1))].getImage();

                if (mouseMoving.size() > 1) {

                    if (mouseMoved.x > (ant.getX() - 10) || mouseMoved.x < (ant.getX() + ant.width + 10)) {
                        ant.addX((mouseMoving.get(1).x - mouseMoving.get(0).x) );
//                        ant.addX(100);
                    }

                    if (mouseMoved.y > (ant.getY() - 20) || mouseMoved.y < (ant.getY() + ant.height + 20)) {
                        ant.addY((mouseMoving.get(1).y - mouseMoving.get(0).y) );
//                        ant.addY(100);
                    }
                }

                ant.moveAnt();              //Updating coords

                if (ant.getY() < 2) {
                    ants.remove(ant);       //Removing ant from arraylist if it has reached the top
                }

            } else {
                score++;                            //Increasing score if the current ant is not alive
                deadAnts.add(ant);          //Adding the recently deceased ant to the deadants array
                ants.remove(ant);           //Removing the recently deceased ant from the main array
            }
        }

    }

    public void IsClicked(int x, int y) {
        for (int i = 0; i < ants.size(); i++) {
            ants.get(i).IsClicked(x, y);            //calling IsClicked for all ants
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        IsClicked(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMoved = new Point(e.getX(), e.getY());
        mouseMoving.add(mouseMoved);

        if (mouseMoving.size() == 3) {
            mouseMoving.remove(0);
        }
    }

    private int dX() {
        int dX;

        Point p1 = mouseMoving.get(0);
        Point p2 = mouseMoving.get(1);

        dX = p2.x - p1.x;
        return dX;
    }

    private int dY() {
        int dY;

        Point p1 = mouseMoving.get(0);
        Point p2 = mouseMoving.get(1);

        dY = p2.y - p1.y;
        return dY;
    }

}