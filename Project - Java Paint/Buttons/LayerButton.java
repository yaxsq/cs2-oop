package Buttons;

import DataStructures.Queue;
import DataStructures.Stack;
import Listeners.*;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public class LayerButton extends ToggleButton implements Serializable {

    private Stack shapes;
    private Queue redoQueue;
    private boolean selected;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param i_depressed
     * @param i_pressed   buttons used for layers only
     */
    public LayerButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed, Stack shapes, Queue redoQueue) {
        super(x, y, width, height, text, i_depressed, i_pressed);
        this.shapes = shapes;
        this.redoQueue = redoQueue;
        selected = false;
    }

    public LayerButton() {}

    public boolean IsClicked(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            pressed = true;
            selected = true;                    /////
            current_image = image_pressed;
            textColor = Color.white;
            return true;
        }
        return false;
    }

    /**
     * sets up a default listener for layer buttons
     */
    public void setUpLayerButtonListener() {
        super.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println(text + " chosen.");

                selected = true;
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
    }

    public void setShapeStack(Stack shapes) {
        this.shapes = shapes;
    }

    public Stack getShapeStack() {
        return shapes;
    }

    public void setRedoQueue(Queue redoQueue) {
        this.redoQueue = redoQueue;
    }

    public Queue getRedoQueue() {
        return redoQueue;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void paint(int x, int y, Graphics g, ImageObserver ob) {
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

    public void setY(int y) {
        this.y = y;
    }

}
