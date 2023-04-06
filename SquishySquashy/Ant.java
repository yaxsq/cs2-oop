import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Ant extends Button {

    protected ImageIcon[] moving;
    boolean alive;
    protected int move = 5;

    public Ant(int x, int y, ImageIcon[] moving, Image squished) {
        super(x, y);
        width = 61;
        height = 74;
        this.moving = moving;
        this.squished = squished;
        alive = true;
        current_image = moving[0].getImage();
    }

    public boolean IsClicked(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            pressed = true;
            current_image = squished;
            alive = false;
            return true;
        }
        return false;
    }

    void moveAnt() {
        if (alive) {
            this.y -= move;         //increasing height
        }
        else {
            move = 0;               //move is 0 if dead
        }
    }

    boolean getAlive() {
        return alive;
    }

    void paint (Graphics g, ImageObserver ob) {
        g.drawImage(current_image, x, y, ob);
    }

    int getY () {
        return y;
    }

    int getX () {
        return x;
    }

    void addX (int x) {
        this.x += x;
    }

    void addY (int y) {
        this.y += y;
    }

}