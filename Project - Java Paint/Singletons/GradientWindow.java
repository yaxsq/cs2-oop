package Singletons;

import Buttons.Button;
import Buttons.ColorButton;
import Buttons.MenuButton;
import Shapes.Rectangle;
import Listeners.mouseListener;
import Listeners.tooltipListener;
import Toolbars.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

public class GradientWindow extends Rectangle implements mouseListener, Serializable {

    private static GradientWindow instance = new GradientWindow();
    private Button[] colorWindowButtons;
    public static boolean visible = false;
    private ArrayList<ColorButton> colorGradient;
    private Color gradientSelection;
    public static Color tempColor;
    private ImageIcon buttonDepressed;
    private ImageIcon buttonPressed;
    private tooltipListener tooltipListener;

    private GradientWindow() {
        super(80, 80, 265, 300, Color.lightGray, Color.darkGray, 3);

        colorGradient = new ArrayList<>();
        initializeImages();
        createGradient();
        setColorWindowButtons();
    }

    public static GradientWindow getInstance() {
        return instance;
    }

    public void paint(Graphics g, ImageObserver ob) {
        if (visible && colorWindowButtons != null && colorGradient != null) {
            super.paint(g);                                         //painting window

            for (int i = 0; i < colorWindowButtons.length; i++) {
                colorWindowButtons[i].paint(g, ob);                 //buttons in the color window
            }

            for (int i = 0; i < colorGradient.size(); i++) {
                colorGradient.get(i).paint(g, ob);                  //gradient color buttons
            }

            if (gradientSelection != null) {                        //if a color is selected, r g b values are updated in the window
                g.setColor(Color.BLACK);
                g.drawString("Red:    " + gradientSelection.getRed(), 150, 320);
                g.drawString("Green:  " + gradientSelection.getGreen(), 150, 340);
                g.drawString("Blue:   " + gradientSelection.getBlue(), 150, 360);
            } else {                                                 //default r g b shown in case no selection has been made yet
                g.drawString("Red:    " + 255, 150, 320);
                g.drawString("Green:  " + 255, 150, 340);
                g.drawString("Blue:   " + 255, 150, 360);
            }
        }
    }

    public void setVisible(boolean visible1) {
        visible = visible1;
        if (visible1) {
            TopBar.setAllMenusFalse();
        }
    }

    @Override
    public void onClick(int x, int y) {
        if (colorWindowButtons != null) {
            for (int i = 0; i < colorWindowButtons.length; i++) {
                colorWindowButtons[i].IsClicked(x, y);
                if (colorWindowButtons[i].getPressed()) {
                    colorWindowButtons[i].getListener().onClick(x, y);
                    colorWindowButtons[i].setPressed(false);
                }
            }
        }

        if (colorGradient != null) {
            for (int i = 0; i < colorGradient.size(); i++) {
                ColorButton cb = colorGradient.get(i);
                cb.IsClicked(x, y);
                if (cb.getPressed()) {
                    cb.getMouseListenerColorGradient().onClick(x, y);
                    gradientSelection = cb.textColor;
                    colorWindowButtons[2].textColor = gradientSelection;
                    cb.setPressed(false);
                }
            }
        }
    }

    @Override
    public void onPress(int x, int y) {
        if (colorWindowButtons != null) {
            for (int i = 0; i < colorWindowButtons.length; i++) {
                colorWindowButtons[i].IsClicked(x, y);
                if (colorWindowButtons[i].getPressed()) {
                    colorWindowButtons[i].getListener().onPress(x, y);
                }
            }
        }
    }

    @Override
    public void onRelease(int x, int y) {

    }

    /**
     * sets up an array of color buttons
     */
    private void createGradient() {
        int xClr;
        int yClr;

        int r;
        int g;
        int b;
        int jump = 7;

        for (yClr = 120; yClr < 300; yClr++) {

            xClr = 90;
            r = 255;
            b = 0;

            for (g = 0; g < 256; g = g + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }

            g = 255;
            b = 0;

            for (r = 255; r > 0; r = r - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }

            r = 0;
            g = 255;

            for (b = 0; b < 256; b = b + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }

            r = 0;
            b = 255;

            for (g = 255; g > 0; g = g - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }

            g = 0;
            b = 255;

            for (r = 0; r < 256; r = r + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }

            r = 255;
            g = 0;

            for (b = 255; b > 0; b = b - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultMouseListenerColorGradient();
                cb.setDefaultColorButtonTooltipListener();
                colorGradient.add(cb);
            }
        }

        for (int i = 0; i < colorGradient.size(); i++) {
            TopBar.setColorButtonTooltipListener(colorGradient.get(i));
        }
    }

    private void setColorWindowButtons() {
        colorWindowButtons = new Button[3];

        colorWindowButtons[0] = new MenuButton(85, 85, 210, 30, "Add to Custom Colors", buttonDepressed.getImage(), buttonPressed.getImage());
        colorWindowButtons[0].setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                if (gradientSelection != null) {
                    System.out.println("ADDING");
                    TopBar.gradientSelection = gradientSelection;
                    TopBar.addCustomColor = true;
                }

                colorWindowButtons[0].updateIcon();

            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        colorWindowButtons[1] = new MenuButton(300, 85, 40, 30, "Exit", buttonDepressed.getImage(), buttonPressed.getImage());
        colorWindowButtons[1].setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
//                            System.out.println("EXIT");
                colorWindowButtons[1].updateIcon();
                setVisible(false);
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        colorWindowButtons[2] = new ColorButton(90, 325, 40, 30, tempColor, Color.black);

    }

    private void initializeImages() {
        buttonDepressed = new ImageIcon("src/otherIcons/square_depressed.png");
        buttonPressed = new ImageIcon("src/otherIcons/square_pressed.png");
    }

    public void hover(int x, int y) {
        for (int i = 0; i < colorGradient.size(); i++) {
            if (colorGradient.get(i).hover(x, y)) {
                colorGradient.get(i).getTooltipListener().onHover();
            }
        }
    }
}
