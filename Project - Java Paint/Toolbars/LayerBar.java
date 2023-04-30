package Toolbars;

import Board.Board;
import Buttons.ActiveButton;
import Buttons.LayerButton;
import DataStructures.Queue;
import DataStructures.Stack;
import DrawingPanel.DrawingPanel;
import Listeners.keyListener;
import Listeners.mouseListener;
import Listeners.tooltipListener;
import Singletons.Tooltip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static Singletons.Tooltip.visible;

public class LayerBar extends Toolbar {

    private static ImageIcon buttonDep;
    private static ImageIcon buttonPre;

    private static LinkedList<LayerButton> layerButtons;
    private int dY = 50;
    private static int layersCount = 0;
    private static int dYstatic = 50;

    public LayerBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        initializeImages();

        buttons = new ArrayList<>();
        layerButtons = new LinkedList<>();

        //Creating buttons for layerbar
        buttons.add(new ActiveButton(topLeft.x + 5, topLeft.y + 30, 50, 25, "Add", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(0).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(a) Adds a new layer";
            }
        });
        buttons.get(0).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'a') {
                    buttons.get(0).getListener().onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
                }
            }
        });
        buttons.get(0).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Adding layer");

                int start = topLeft.y + 45;                     //initial point for making layer buttons

                if (layersCount != 10) {
                    layerButtons.add(new LayerButton(topLeft.x + 5, start + dY, 140, 25, "Layer " + (layerButtons.size() + 1), buttonDep.getImage(), buttonPre.getImage(), new Stack(), new Queue()));
//                    layerButtons.get(layerButtons.size() - 1).setUpLayerButtonListener();
                    setUpLayerButtonListener(layerButtons.get(layerButtons.size() - 1));

                    layersCount++;
                    dY += 50;                                   //variable to make sure Y coordinate changes for new layer
                } else {
                    System.out.println("Layer slots full.");
                }

                updateLayerButtonLocation();
                buttons.get(0).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ActiveButton(topLeft.x + 60, topLeft.y + 30, 80, 25, "Remove", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(1).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(d) Removes the selected layer";
            }
        });
        buttons.get(1).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'd') {
                    buttons.get(1).getListener().onClick(buttons.get(1).x + 1, buttons.get(1).y + 1);
                }
            }
        });
        buttons.get(1).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("REMOVE");

                LayerButton lb = getCurrentLayer();                         //lb is current layer selected

                if (layerButtons.size() == 1) {
                    System.out.println("The last layer can not be removed.");
                    buttons.get(1).updateIcon();
                    return;
                }

                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(lb)) {
                        layerButtons.remove(i);                             //removing selected button from array
                        layersCount--;
                    }
                }

                updateLayerButtonLocation();
                buttons.get(1).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ActiveButton(topLeft.x + 5, topLeft.y + 60, 55, 25, "Raise", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(2).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                LayerButton lowerLB = getCurrentLayer();                        //current button
                LayerButton upperLB;                                            //button to replace with

                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(lowerLB) && i != 0) {
                        upperLB = layerButtons.get(i - 1);

                        int tempVar;

                        //replacing y coordinate values
                        tempVar = lowerLB.y;
                        lowerLB.y = upperLB.y;
                        upperLB.y = tempVar;

//                        Buttons.LayerButton tempButton = lowerLB;

                        //replacing button positions in the array because that affects the painting
                        layerButtons.set(i, upperLB);
                        layerButtons.set(i - 1, lowerLB);

                        //making sure the button remains selected
                        lowerLB.setPressed(true);

                        break;
                    }
                }

                buttons.get(2).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ActiveButton(topLeft.x + 60, topLeft.y + 60, 55, 25, "Lower", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(3).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                LayerButton upperLB = getCurrentLayer();                            //button chosen
                LayerButton lowerLB;                                                //button to be replaced with

                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(upperLB) && i < layerButtons.size() - 1) {
                        lowerLB = layerButtons.get(i + 1);

                        int tempVar;

                        //replacing y coordinate values
                        tempVar = lowerLB.y;
                        lowerLB.y = upperLB.y;
                        upperLB.y = tempVar;

                        //swapping positions in the array
                        layerButtons.set(i + 1, upperLB);
                        layerButtons.set(i, lowerLB);

                        //making sure the current button is selected
                        upperLB.setPressed(true);

                        break;
                    }
                }

                buttons.get(3).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        if (layerButtons.size() < 1 && layerButtons != null) {
            buttons.get(0).getListener().onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
            layerButtons.get(0).getListener().onClick(layerButtons.get(0).x + 1, layerButtons.get(0).y + 1);
        }
    }

    public void paint(Graphics g, ImageObserver ob) {
        super.paint(g);
        g.setColor(Color.gray);                                                           //drawing layerbar box
        g.drawRect(topLeft.x, topLeft.y, width, height);

        g.setColor(Color.YELLOW);                                                         //drawing layer heading
        g.fillRect(topLeft.x + 2, topLeft.y, width - 4, 18);
        g.setColor(Color.gray);
        g.drawLine(topLeft.x, topLeft.y + 18, topLeft.x + width, topLeft.y + 18);
        drawText(g, "LAYERS", 13, Color.BLACK, topLeft.x, topLeft.y + 3);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g, ob);                                //Painting all layerBar buttons
        }

        for (int i = 0; i < layerButtons.size(); i++) {
            layerButtons.get(i).paint(g, ob);                           //painting all layer buttons
        }

        if (visible) {
            Tooltip.getInstance().paint(g);
        }

//        DrawingPanel.setUpDrawingGraphics(g);                               //draws the current layer
    }

    public static void paintAllShapes(Graphics g) {
        try {
            for (int i = layerButtons.size() - 1; i >= 0; i--) {
                if (layerButtons.get(i).getShapeStack() != null) {
                    for (int j = 0; j < layerButtons.get(i).getShapeStack().getStackSize(); j++) {
                        layerButtons.get(i).getShapeStack().get(j).draw(g);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    protected void drawText(Graphics g, String text, int fontSize, Color textColor, int x, int y) {
        Font font = new Font(text, Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(textColor);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getAscent() - fontMetrics.getDescent();
        g.drawString(text, x + (width / 2) - (textWidth / 2), y + textHeight);
    }

    protected void initializeImages() {
        buttonDep = new ImageIcon("src/otherIcons/square_depressed.png");
        buttonPre = new ImageIcon("src/otherIcons/square_pressed.png");
    }

    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (layerButtons != null) {                                     //if layer buttons not null
            for (int i = 0; i < layerButtons.size(); i++) {               //loops through all layerbuttons
                LayerButton lb = layerButtons.get(i);
                lb.IsClicked(x, y);
                if (lb.getPressed()) {                                          //if button is pressed
                    for (int j = 0; j < layerButtons.size(); j++) {
                        if (!layerButtons.get(j).equals(lb)) {
                            layerButtons.get(j).setPressed(false);          //sets pressed to false for all buttons which are not pressed
                            layerButtons.get(j).setDepressedImage();        //updating imageicon
                        }
                    }
                    lb.getListener().onClick(x, y);                         //calling onClick for the layer button clicked
                }

            }
        }
    }

    private static LayerButton getCurrentLayer() {
        for (int i = 0; i < layerButtons.size(); i++) {
            LayerButton lb = layerButtons.get(i);
            if (lb.getPressed() || lb.getSelected()) {
                lb.textColor = Color.black;
//                lb.setPressed(false);
                return lb;
            }
        }
        layerButtons.get(0).getListener().onClick(layerButtons.get(0).x + 1, layerButtons.get(0).y + 1);
//        setCurrentLayer(layerButtons.get(0));
        return null;
    }

    private static void setCurrentLayer(LayerButton lb) {
        layerChanged();
        lb.setPressed(true);
        lb.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        updateLayerShapeStack();
        updateLayerButtonLocation();

        for (int i = 0; i < layerButtons.size(); i++) {
            if (layerButtons.get(i).getShapeStack() != null) {
                if (getCurrentLayer() != null) {
//                    System.out.println("AP-LB STACK " + i + " " + layerButtons.get(i).getShapes().getStackSize() + " " + getCurrentLayer().text);
                } else {
                    System.out.println("Select a layer to draw on.");
                }
            }
        }
    }

    private void updateLayerButtonLocation() {
        if (layerButtons != null) {
            int start = topLeft.y + 95;
            dY = 0;

            for (int i = 0; i < layerButtons.size(); i++) {
                layerButtons.get(i).setY(start + dY);
                dY += 50;
            }
        }

    }

    /**
     * @param lb       the button currently selected
     * @param selected the value to set other buttons' selected value to
     */
    private static void setOtherLayerSelected(LayerButton lb, boolean selected) {
        for (int i = 0; i < layerButtons.size(); i++) {
            if (!layerButtons.get(i).equals(lb)) {
                layerButtons.get(i).setSelected(selected);
            }
        }
    }

    private void setUpLayerButtonListener(LayerButton lb) {
        lb.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                lb.setSelected(true);
                setOtherLayerSelected(lb, false);
                setCurrentLayer(lb);

                System.out.println(lb.text + " chosen.");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
    }

    private static void layerChanged() {
        if (getCurrentLayer() != null) {
            DrawingPanel.setShapeStack(getCurrentLayer().getShapeStack());
            DrawingPanel.setShapeQueue(getCurrentLayer().getRedoQueue());
            DrawingPanel.setShape(null);
        }

        for (int i = 0; i < layerButtons.size(); i++) {
            System.out.println("LayerBar/layerChanged: STACK " + i + " SIZE " + layerButtons.get(i).getShapeStack().getStackSize() + " Current " + getCurrentLayer().text);
        }

    }

    //setters and getters for filesaving

    public void setLayerButtons(LinkedList<LayerButton> layerButtons) {
        this.layerButtons = layerButtons;
    }

    public void setLayersCount(int layersCount) {
        this.layersCount = layersCount;
    }

    public void setButtonDep(ImageIcon buttonDep) {
        this.buttonDep = buttonDep;
    }

    public void setButtonPre(ImageIcon buttonPre) {
        this.buttonPre = buttonPre;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public LinkedList<LayerButton> getLayerButtons() {
        return layerButtons;
    }

    public int getLayersCount() {
        return layersCount;
    }

    public ImageIcon getButtonDep() {
        return buttonDep;
    }

    public ImageIcon getButtonPre() {
        return buttonPre;
    }

    public int getdY() {
        return dY;
    }

    public static void setFirstStack(Stack stack) {
        layerButtons.get(0).setShapeStack(stack);
    }

    public static ArrayList<Stack> getAllLayerStacks() {
        ArrayList<Stack> stacks = new ArrayList<>();

        for (int i = 0; i < layerButtons.size(); i++) {
            stacks.add(layerButtons.get(i).getShapeStack());
        }
        return stacks;
    }

    public static void setAllLayerStacks(ArrayList<Stack> stacks) {
        for (int i = 0; i < stacks.size(); i++) {
//            buttons.get(0).getListener().onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);       //button has to be static which causes conflicts

            int start = 70 + 45;                     //initial point for making layer buttons

            if (layersCount != 10) {
                layerButtons.add(new LayerButton((Board.B_WIDTH - 150) + 5, start + dYstatic, 140, 25, "Layer " + (layerButtons.size() + 1), buttonDep.getImage(), buttonPre.getImage(), new Stack(), new Queue()));
//                    layerButtons.get(layerButtons.size() - 1).setUpLayerButtonListener();
                layerButtons.get(layerButtons.size() - 1).setListener(new mouseListener() {
                    @Override
                    public void onClick(int x, int y) {

                    }

                    @Override
                    public void onPress(int x, int y) {

                    }

                    @Override
                    public void onRelease(int x, int y) {

                    }
                });

                dYstatic += 50;                                   //variable to make sure Y coordinate changes for new layer
                layersCount++;
            } else {
                System.out.println("Layer slots full.");
            }

            layerButtons.get(layerButtons.size()-1).setShapeStack(stacks.get(i));
        }
    }


}
