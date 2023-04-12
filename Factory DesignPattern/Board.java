import CityFactory.CityFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel
        implements ActionListener, MouseInputListener {

    private final int B_WIDTH = 1280;
    private final int B_HEIGHT = 720;
    private final int DELAY = 25;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;

    private ArrayList<CityButton> cityButtons;
    private CityButton currentCB;
    private CityButton prevCB;

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

    public Board() throws FileNotFoundException {

        initBoard();
    }

    private void InitializeAssets() throws FileNotFoundException {
        CityFactory cf = new CityFactory();
        cityButtons = new ArrayList<>();

        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));
        cityButtons.add(new CityButton(cf.getCity("Sindh")));


        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));
        cityButtons.add(new CityButton(cf.getCity("Punjab")));


        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));
        cityButtons.add(new CityButton(cf.getCity("Balochistan")));

        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));
        cityButtons.add(new CityButton(cf.getCity("Khyber Pakhtunkhwa")));


    }

    private void initBoard() throws FileNotFoundException {

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

        for (int i = 0; i < cityButtons.size(); i++) {
            cityButtons.get(i).paint(g, this);
        }

        if (prevCB != null && currentCB != null) {
            g.drawLine(prevCB.getLocation().x, prevCB.getLocation().y, currentCB.getLocation().x, currentCB.getLocation().y);
        }

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
    }

    public void IsClicked(int x, int y) {
        for (int i = 0; i < cityButtons.size(); i++) {
            cityButtons.get(i).IsClicked(x, y);
            if (cityButtons.get(i).getPressed()) {
                if (prevCB == null) {
                    prevCB = cityButtons.get(i);                            // fix line drawing
                } else {
                    prevCB = currentCB;
                    currentCB = cityButtons.get(i);
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        IsClicked(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        mousePressed = false;
        //start_drawing = false;
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
        // TODO Auto-generated method stub

    }
}