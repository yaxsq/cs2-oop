import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Window extends Rectangle{

    private int B_WIDTH;
    private int B_HEIGHT;
    private ArrayList<Toolbar> toolbars;

    /**
     * @param B_HEIGHT board height
     * @param B_WIDTH board width
     */
    Window(int B_HEIGHT, int B_WIDTH) {
        this.B_HEIGHT = B_HEIGHT;
        this.B_WIDTH = B_WIDTH;

        toolbars = new ArrayList<>();

        Toolbar topBar = new TopBar(0, 0, B_WIDTH, 70);
        toolbars.add(topBar);

        Toolbar layers = new LayerBar(B_WIDTH-150, 70, 150, B_HEIGHT-80);
        toolbars.add(layers);
    }

    /**
     * @param g
     * @param ob
     *
     * draws a border around the window and paints the toolbars
     */
    void paint(Graphics g, ImageObserver ob){
        g.setColor(Color.red);
        g.drawRect(0, 0, B_WIDTH-1, B_HEIGHT-1);
        g.setColor(Color.blue);
        g.drawRect(1, 1, B_WIDTH-3, B_HEIGHT-3);

        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).paint(g, ob);
        }
    }

    //Methods passing the mouse coordintes

    void onClick(int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onClick(x, y);
        }
    }

    void onPress(int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onPress(x, y);
        }
    }

    void onRelease(int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onRelease(x, y);
        }
    }

    /**
     * @param key key from listener
     *            call functions responding to key inputs of all toolbars
     */
    void onKeyRelease(char key) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onKeyRelease(key);
        }
    }

}
