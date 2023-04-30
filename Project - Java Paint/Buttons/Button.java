package Buttons;

import Listeners.*;
import Shapes.*;
import Shapes.Rectangle;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public abstract class Button implements Serializable {
    public int x;
    public int y;
    protected int width;
    protected int height;
    public String text;
    protected int fontSize = 12;
    protected Image image_depressed;
    protected Image image_pressed;
    public Image current_image;
    protected boolean pressed;
    public Color textColor; //changed
    public mouseListener mouseListener;
    public tooltipListener tooltipListener;
    protected keyListener keyListener;

    /**
     * @param x           x coord
     * @param y           y coord
     * @param width       width
     * @param height      height
     * @param i_depressed depressed icon
     * @param i_pressed   pressed icon
     */
    public Button(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image_depressed = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
        image_pressed = i_pressed.getScaledInstance(width, height, Image.SCALE_FAST);
        current_image = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
    }

    public Button(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        image_depressed = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
        image_pressed = i_pressed.getScaledInstance(width, height, Image.SCALE_FAST);
        current_image = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);

    }

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public Button() {
    }

    public void paint(Graphics g, ImageObserver ob) {
        if (image_depressed != null && image_pressed != null) {
            g.drawImage(current_image, x, y, ob);
        }

        if (text != null && (image_depressed == null && image_pressed == null)) {
            g.drawRect(x, y, width, height);
            g.setColor(textColor);
            drawText(text, textColor, g, fontSize);
        }

        if (this.text != null) {
            g.setColor(textColor);
            drawText(text, textColor, g, fontSize);
        }
    }

    //protected abstract void setUpListener();

    /**
     * @param x x coord
     * @param y y coord
     * @return boolean for click status
     * if the click is within the dimension, true is returned and current image is updated
     */
    public boolean IsClicked(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            pressed = true;
            current_image = image_pressed;
            textColor = Color.white;
            return true;
        }
        return false;
    }

    public boolean hover(int x, int y) {
        if (x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
            return true;
        }
        return false;
    }

    public void setListener(mouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public void setKeyListener(keyListener keyListener) {
        this.keyListener = keyListener;
    }

    public mouseListener getListener() {
        return mouseListener;
    }

    public tooltipListener getTooltipListener () {
        return this.tooltipListener;
    }

    public void setTooltipListener(Listeners.tooltipListener tooltipListener) {
        this.tooltipListener = tooltipListener;
    }

    public keyListener getKeyListener() {
        return this.keyListener;
    }

    public Image getImage() {
        return current_image;
    }

    public void setImage(Image image) {
        current_image = image;
    }

    public Boolean getPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void drawText(String text, Color textColor, Graphics g, int fontSize) {
        Font font = new Font(text, Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(textColor);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getAscent() - fontMetrics.getDescent();
        g.drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2));
    }

    public void updateIcon() {
        if (this instanceof ActiveButton) {
//            System.out.println("------UPDATING ICON");
            if (getPressed()) {
                setImage(image_depressed);
            } else {
                setPressed(true);
                setImage(image_pressed);
            }

            textColor = Color.black;
        }
    }

    public void updateIcon(boolean fileMenu) {
        if (this instanceof ToggleButton) {
//            System.out.println("------UPDATING TOGGLE");
            if (!fileMenu) {
                setImage(image_depressed);
                textColor = Color.BLACK;
            }
        }
    }

    public void drawFileMenu(Graphics g, ImageObserver ob) {
//            System.out.println("FILE MENUUUUUUUUUUUUUUUUUUU");
        Rectangle fileBox = new Rectangle(0, 55, 80, 150, Color.white, Color.gray, 1);
//            fileBox.paint(g);

        Button newButton = new ActiveButton(0, 60, 79, 30, "New", image_depressed, image_pressed);
        newButton.paint(g, ob);
        newButton.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("new");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        Button openButton = new ActiveButton(0, 91, 79, 30, "Open", image_depressed, image_pressed);
        openButton.paint(g, ob);
        openButton.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("open");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        Button saveButton = new ActiveButton(0, 121, 79, 39, "Save", image_depressed, image_pressed);
        saveButton.paint(g, ob);
        saveButton.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("save");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        Button tempButton = new ToggleButton(100, 100, 100, 100, "TEXT", image_depressed, image_pressed);
        tempButton.paint(g, ob);
        tempButton.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("TEMP");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
    }

//    void drawFileMenu()

    public void drawEditMenu(Graphics g, ImageObserver ob) {
        Button undo = new ActiveButton(70, 60, 79, 30, "Undo", image_depressed, image_pressed);
        undo.paint(g, ob);
        undo.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("clicked undo");
            }

            @Override
            public void onPress(int x, int y) {
                System.out.println("pressed undo");
            }

            @Override
            public void onRelease(int x, int y) {
                System.out.println("released undo");
            }
        });

        Button redo = new ActiveButton(70, 91, 79, 30, "Redo", image_depressed, image_pressed);
        redo.paint(g, ob);
    }

    public  void setDepressedImage() {
        setImage(image_depressed);
        textColor = Color.black;
    }

    //setters getters for filesaving

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setImage_depressed(Image image_depressed) {
        this.image_depressed = image_depressed;
    }

    public void setImage_pressed(Image image_pressed) {
        this.image_pressed = image_pressed;
    }

    public void setCurrent_image(Image current_image) {
        this.current_image = current_image;
    }

    public void setMouseListener(Listeners.mouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Image getCurrent_image() {
        return current_image;
    }

    public Image getImage_depressed() {
        return image_depressed;
    }

    public Image getImage_pressed() {
        return image_pressed;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Listeners.mouseListener getMouseListener() {
        return mouseListener;
    }

    public String getText() {
        return text;
    }

}