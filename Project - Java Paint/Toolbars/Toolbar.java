package Toolbars;

import Buttons.Button;
import Listeners.keyListener;
import Listeners.mouseListener;
import Shapes.Rectangle;
import Singletons.GradientWindow;
import Singletons.Tooltip;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

import static Singletons.Tooltip.getButton;
import static Singletons.Tooltip.visible;

public abstract class Toolbar extends Rectangle implements mouseListener, keyListener, ActionListener, Serializable {

    protected ArrayList<Button> buttons;

    public Toolbar(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white, Color.lightGray, 2);
    }

    public Toolbar() {}

    public void paint(Graphics g, ImageObserver ob) {
        super.paint(g);
    }           //Changed to draw from paint   //1 changed to paint and added paint in rect

    @Override
    public void onPress(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).IsClicked(x, y);
            if (buttons.get(i).getPressed()) {
                buttons.get(i).getListener().onPress(x, y);
            }
        }
    }

    @Override
    public void onRelease(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).IsClicked(x, y);
            if (buttons.get(i).getPressed()) {
                buttons.get(i).getListener().onRelease(x, y);
            }
        }
    }

    @Override
    public void onClick(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).IsClicked(x, y);
            if (buttons.get(i).getPressed()) {
                buttons.get(i).getListener().onClick(x, y);
                buttons.get(i).setPressed(false);
            }
        }
    }

    public void onMove(int x, int y) {

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).tooltipListener != null && buttons.get(i).hover(x, y)) {         //if hovering and listener is initiated
                Tooltip.getInstance().setText(buttons.get(i).tooltipListener.onHover());            //setting text to show
                Tooltip.getInstance().updatePoint(x, y);                                            //updating to latest coords
                Tooltip.getInstance().setButton(buttons.get(i));                                    //saving the button the mouse is hovering on
                visible = true;                                                                     //visibility true
            } else if (getButton() != null && !getButton().hover(x, y)) {                       //if button is not null and the mouse is not hovering
                visible = false;                                                                    //visibility false and text reset
                Tooltip.getInstance().setText(null);
            }
        }

        GradientWindow.getInstance().hover(x, y);
    }

    /**
     * @param key used for key inputs (shortcuts)
     *            <p>
     *            f for filemenu
     *            e for editmenu
     *            w for stroke
     *            c for adding colors
     */
    @Override
    public void onKeyRelease(char key) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getKeyListener() != null) {
                buttons.get(i).getKeyListener().onKeyRelease(key);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    protected abstract void initializeImages();


}
