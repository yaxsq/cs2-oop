package Window;

import DrawingPanel.DrawingPanel;
import Shapes.Rectangle;
import Toolbars.LayerBar;
import Toolbars.Toolbar;
import Toolbars.TopBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

import static Board.Board.B_HEIGHT;
import static Board.Board.B_WIDTH;

public class Window extends Rectangle implements ActionListener, Serializable {

    private ArrayList<Toolbar> toolbars;
    private DrawingPanel drawingPanel;
    private static Toolbar layers;

    /**
     *
     */
    public Window() {
        toolbars = new ArrayList<>();

        Toolbar topBar = new TopBar(0, 0, B_WIDTH, 70);
        toolbars.add(topBar);

        layers = new LayerBar(B_WIDTH-150, 70, 150, B_HEIGHT-80);
        toolbars.add(layers);

        drawingPanel = new DrawingPanel(0, 70, B_WIDTH-150, B_HEIGHT-80);
    }

    /**
     * @param g
     * @param ob
     *
     * draws a border around the window and paints the toolbars
     */
    public void paint(Graphics g, ImageObserver ob){
        g.setColor(Color.red);
        g.drawRect(0, 0, B_WIDTH-1, B_HEIGHT-1);
        g.setColor(Color.blue);
        g.drawRect(1, 1, B_WIDTH-3, B_HEIGHT-3);                          //Border

        drawingPanel.paint(g, ob);
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).paint(g, ob);
        }
    }

    //Methods passing the mouse coordintes

    public void onClick(MouseEvent e, int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onClick(x, y);
        }

        if (drawingPanel.IsClicked(e)) {
            drawingPanel.onClick(e);
        }
    }

    public void onPress(MouseEvent e, int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onPress(x, y);
        }

        if (drawingPanel.IsClicked(e)) {
            drawingPanel.onPress(e);
        }
    }

    public void onRelease(MouseEvent e, int x, int y) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onRelease(x, y);
        }

        if (drawingPanel.IsClicked(e)) {
            drawingPanel.onRelease(e);
        }
    }                                       //CAN REPLACE MOUSELISTENER WITH MOUSEEVENTLISTENER
                                            //FIX CALL DRAWINGPANEL FUNCTIONS ONLY WHEN BUTTON NOT PRESSED IN TOPBAR
    public void onDrag(MouseEvent e) {
        if (drawingPanel.IsClicked(e)) {
            drawingPanel.onDrag(e);
        }
    }

    public void onMove(MouseEvent e) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onMove(e.getX(), e.getY());
        }
        if (drawingPanel.IsClicked(e)) {
            drawingPanel.onMove(e);
        }
    }

    /**
     * @param key key from listener
     *            call functions responding to key inputs of all toolbars
     */
    public void onKeyRelease(char key) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).onKeyRelease(key);
        }

        drawingPanel.onKeyRelease(key);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < toolbars.size(); i++) {
            toolbars.get(i).actionPerformed(e);
        }

        drawingPanel.actionPerformed(e);
    }

    //setters and getters for filesaving

    public static void setLayerBar(LayerBar lb) {
        layers = lb;
    }

    public static Toolbar getLayerBar() {
        return layers;
    }

    public void setToolbars(ArrayList<Toolbar> toolbars) {
        this.toolbars = toolbars;
    }

    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public int getB_HEIGHT() {
        return B_HEIGHT;
    }

    public int getB_WIDTH() {
        return B_WIDTH;
    }

    public ArrayList<Toolbar> getToolbars() {
        return toolbars;
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

}
