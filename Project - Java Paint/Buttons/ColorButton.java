package Buttons;

import Listeners.*;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public class ColorButton extends ToggleButton implements Serializable {

    private mouseListenerColorGradient mouseListenerColorGradient;
    private Color stroke;
    private boolean selected;

    public ColorButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    public ColorButton(int x, int y, int width, int height, Color color, Color stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textColor = color;
        this.stroke = stroke;
    }

    public ColorButton(int x, int y, int width, int height, String text, Color color, Color stroke) {
        this(x, y, width, height, color, stroke);
        this.text = text;
    }

    public ColorButton() {}

    public void setDefaultGradientListener() {
        this.mouseListener = new mouseListener() {
            @Override
            public void onClick(int x, int y) {
//                System.out.println(textColor.getRed() + " " + textColor.getBlue() + " " + textColor.getGreen() + " SELECTED");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        };
    }

    public void setDefaultMouseListenerColorGradient() {
        this.mouseListenerColorGradient = new mouseListenerColorGradient() {
            @Override
            public Color onClick(int x, int y) {
                System.out.println(textColor.getRed() + " " + textColor.getBlue() + " " + textColor.getGreen() + " SELECTED");
                return textColor;
            }
        };
    }

    public void setDefaultColorButtonTooltipListener() {
        this.setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return textColor.getRed() + " " + textColor.getGreen() + " " + textColor.getBlue();

            }
        });
    }

    public boolean IsClicked(int x, int y) {
        if (x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
            pressed = true;
            return true;
        }
        return false;
    }

    void resetColor(Color color) {
        this.textColor = color;
    }

    Color getColor() {
        return this.textColor;
    }

    public void paint(Graphics g, ImageObserver ob) {
        g.setColor(stroke);
        g.fillRect(x, y, width, height);
//        g.drawRect(x - 1, y - 1, width + 1, height + 1);
        g.setColor(textColor);
        g.fillRect(x + 1, y + 1, width - 2, height - 2);

        if (text != null) {
            super.drawText(text, textColor.darker(), g, 12);
        }
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean getSelected() {
        return this.selected;
    }

    public Listeners.mouseListenerColorGradient getMouseListenerColorGradient() {
        return this.mouseListenerColorGradient;
    }

    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    @Override
    protected void setUpListener() {
        super.setUpListener();
    }

    //setters and getters for filesaving

    public void setMouseListenerColorGradient(Listeners.mouseListenerColorGradient mouseListenerColorGradient) {
        this.mouseListenerColorGradient = mouseListenerColorGradient;
    }

    public Color getStroke() {
        return stroke;
    }

    @Override
    public Listeners.mouseListener getMouseListener() {
        return super.getMouseListener();
    }


}
