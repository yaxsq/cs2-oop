import java.awt.*;
import java.awt.image.ImageObserver;

public class ColorButton extends ToggleButton {

    Color stroke;
    boolean selected;

    public ColorButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    ColorButton(int x, int y, int width, int height, Color color, Color stroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textColor = color;
        this.stroke = stroke;
    }

    ColorButton(int x, int y, int width, int height, String text, Color color, Color stroke) {
        this(x, y, width, height, color, stroke);
        this.text = text;
    }

    void setDefaultGradientListener() {
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

    Color getColor () {
        return this.textColor;
    }

    void paint(Graphics g, ImageObserver ob) {
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

    boolean getSelected () {
        return this.selected;
    }

}
