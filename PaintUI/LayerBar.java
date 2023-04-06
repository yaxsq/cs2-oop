import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

public class LayerBar extends Toolbar {

    ImageIcon buttonDep;
    ImageIcon buttonPre;

    LinkedList<Shape> shapes;
    LinkedList<LinkedList> layers;
    ArrayList<LayerButton> layerButtons;
    int dY = 50;
    int layersCount;

    LayerBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        initializeImages();

        buttons = new ArrayList<>();
        shapes = new LinkedList<>();
        layers = new LinkedList<>();
        layerButtons = new ArrayList<>();

        //Creating buttons for layerbar
        buttons.add(new ActiveButton(topLeft.x + 5, topLeft.y + 30, 50, 25, "Add", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(0).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Adding layer");
                buttons.get(0).updateIcon();

                int start = topLeft.y + 45;                     //inital point for making layer buttons
                layersCount = 0;

                if (layersCount != 10) {
                    layers.add(new LinkedList());               //adding layers and layerbuttons
                    layerButtons.add(new LayerButton(topLeft.x + 5, start + dY, 140, 25, "Layer " + (layerButtons.size() + 1), buttonDep.getImage(), buttonPre.getImage()));
                    layerButtons.get(layerButtons.size() - 1).setUpLayerButtonListener();

                    layersCount++;
                    dY += 50;                                   //variable to make sure Y coordinate changes for new layer
                } else {
                    System.out.println("Layer slots full.");
                }

            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ActiveButton(topLeft.x + 60, topLeft.y + 30, 80, 25, "Remove", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(1).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("REMOVE");

                int removeIndex = 0;
                LayerButton lb = getCurrentLayer();                         //lb is current layer selected
                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(lb)) {
                        layerButtons.remove(i);                             //removing selected button from array
                        removeIndex = i;
//                        layersCount--;
                    }
                }

//                dY -= 50 * removeIndex;

                buttons.get(1).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ActiveButton(topLeft.x + 5, topLeft.y + 60, 55, 25, "Raise", buttonDep.getImage(), buttonPre.getImage() ));
        buttons.get(2).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                LayerButton lowerLB = getCurrentLayer();                        //current button
                LayerButton upperLB;                                            //button to replace with

                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(lowerLB) && i != 0) {
                        upperLB = layerButtons.get(i-1);

                        int tempVar;

                        //replacing y coordinate values
                        tempVar = lowerLB.y;
                        lowerLB.y = upperLB.y;
                        upperLB.y = tempVar;

//                        LayerButton tempButton = lowerLB;

                        //replacing button positions in the array because that affects the painting
                        layerButtons.set(i, upperLB);
                        layerButtons.set(i-1, lowerLB);

                        //making sure the button remains selected
                        lowerLB.setPressed(true);

                        upperLB = null;
                        lowerLB = null;

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

        buttons.add(new ActiveButton(topLeft.x + 60, topLeft.y + 60, 55, 25, "Lower", buttonDep.getImage(), buttonPre.getImage() ));
        buttons.get(3).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                LayerButton upperLB = getCurrentLayer();                            //button chosen
                LayerButton lowerLB;                                                //button to be replaced with

                for (int i = 0; i < layerButtons.size(); i++) {
                    if (layerButtons.get(i).equals(upperLB) && i < layerButtons.size()-1 ) {
                        lowerLB = layerButtons.get(i+1);

                        int tempVar;

                        //replacing y coordinate values
                        tempVar = lowerLB.y;
                        lowerLB.y = upperLB.y;
                        upperLB.y = tempVar;

                        //swapping positions in the array
                        layerButtons.set(i+1, upperLB);
                        layerButtons.set(i, lowerLB);

                        //making sure the current button is selected
                        upperLB.setPressed(true);

                        upperLB = null;
                        lowerLB = null;

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

    }

    void paint(Graphics g, ImageObserver ob) {
        g.setColor(Color.gray);
        g.drawRect(topLeft.x, topLeft.y, width, height);
        drawText(g, "LAYERS", 13, Color.BLACK, topLeft.x, topLeft.y + 3);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g, ob);                                //Painting all layerBar buttons
        }

        for (int i = 0; i < layerButtons.size(); i++) {
            layerButtons.get(i).paint(g, ob);                           //painting all layer buttons
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
        buttonDep = new ImageIcon("src/square_depressed.png");
        buttonPre = new ImageIcon("src/square_pressed.png");
    }

    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (layerButtons != null) {
            for (int i = 0; i < layerButtons.size(); i++) {
                LayerButton lb = layerButtons.get(i);
                lb.IsClicked(x, y);
                if (lb.getPressed()) {
                    for (int j = 0; j < layerButtons.size(); j++) {
                        if (!layerButtons.get(j).equals(lb)) {
                            layerButtons.get(j).setPressed(false);          //sets pressed to false for all buttons which are not pressed
                            layerButtons.get(j).setDepressedImage();
                        }
                    }
                    lb.getListener().onClick(x, y);                         //calling onClick for the layer button clicked
                }

            }
        }
    }

    LayerButton getCurrentLayer() {
        for (int i = 0; i < layerButtons.size(); i++) {
            LayerButton lb = layerButtons.get(i);
            if (lb.getPressed()) {
//                System.out.println(lb.text + "  ");
                lb.textColor = Color.black;
                lb.setPressed(false);
                return lb;
            }
        }
        return null;
    }
}
