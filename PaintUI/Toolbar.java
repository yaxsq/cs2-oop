import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class Toolbar extends Rectangle implements mouseListener, keyListener {

    ArrayList<Button> buttons;

    Toolbar(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white, Color.lightGray, 2);
    }

    void paint(Graphics g, ImageObserver ob) {
        super.paint(g);
    }

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
        /*
        if (menuButtons != null) {
            for (int i = 0; i < menuButtons.length; i++) {
                menuButtons[i].IsClicked(x, y);
                if (menuButtons[i].getPressed()) {
                    menuButtons[i].getListener().onClick(x, y);
                    menuButtons[i].setPressed(false);
                }
            }
        }
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
                    cb.getListener().onClick(x, y);
                    cb.setPressed(false);
                }

                /*
                colorGradient.get(i).IsClicked(x, y);
                if (colorGradient.get(i).getPressed()) {
                    colorGradient.get(i).getListener().onClick(x, y);
                    colorGradient.get(i).setPressed(false);
                }

                 */

    }

    /**
     * @param key
     *
     * used for key inputs (shortcuts)
     *
     * f for filemenu
     * e for editmenu
     * w for stroke
     * c for adding colors
     */
    @Override
    public void onKeyRelease(char key) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getKeyListener() != null) {
                buttons.get(i).getKeyListener().onKeyRelease(key);
            }
        }
    }

    protected abstract void initializeImages();


}
