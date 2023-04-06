import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

public class TopBar extends Toolbar implements mouseListener {

    //Images
    private ImageIcon rightAngleIcon;
    private ImageIcon equilateralIcon;
    private ImageIcon rectIcon;
    private ImageIcon circleIcon;
    private ImageIcon hexIcon;
    private ImageIcon pentIcon;
    private ImageIcon rightAngleIconDepressed;
    private ImageIcon equilateralIconDepressed;
    private ImageIcon rectIconDepressed;
    private ImageIcon circleIconDepressed;
    private ImageIcon hexIconDepressed;
    private ImageIcon pentIconDepressed;

    private ImageIcon buttonDep;
    private ImageIcon buttonPre;

    private ImageIcon editColorsIcon;

    //boolean for menus
    private boolean fileMenu;
    private boolean openWindow;
    private boolean editMenu;
    private boolean addColors;
    private boolean strokeMenu;

    private File[] files;                           //array for files for open window
    private ArrayList<FileButton> fileButtons;      //array for buttons for the files in openwindow

    private Button[] menuButtons;                   //array used to store buttons for dropdown menus

    private Button[] colorWindowButtons;
    private ArrayList<ColorButton> colorGradient;

    private Color currentColor;
    private Color gradientSelection;

    private Color color1;
    private Color color2;

    private int stroke;

    private Button currentShape;


    TopBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        initializeImages();
        buttons = new ArrayList<>();

        /**
         * created all buttons in the toolbar manually
         * set up their mouse and key listeners
         */

        buttons.add(new ToggleButton(5, 15, 60, 40, "File", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(0).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'f') {
                    buttons.get(0).mouseListener.onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
                }
            }
        });
        buttons.get(0).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
//                System.out.println("CLICK FILE ");

                if (fileMenu) {
                    fileMenu = false;
                    menuButtons = null;
                } else {
                    fileMenu = true;
                    menuButtons = new Button[3];

                    Button newButton = new MenuButton(0, 60, 79, 30, "New", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[0] = newButton;
                    menuButtons[0].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("New");
                            menuButtons[0].updateIcon();
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });

                    Button openButton = new MenuButton(0, 91, 79, 30, "Open", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[1] = openButton;
                    openButton.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
//                            System.out.println("OPEN");
                            menuButtons[1].updateIcon();

                            createOpenWindow();

                            if (openWindow) {
                                openWindow = false;
                            } else {
                                openWindow = true;
                            }
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });

                    Button saveButton = new MenuButton(0, 121, 79, 30, "Save", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[2] = saveButton;
                    saveButton.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("SAVING");
                            menuButtons[2].updateIcon();
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                }

                buttons.get(0).updateIcon(fileMenu);

            }

            @Override
            public void onPress(int x, int y) {
            }

            @Override
            public void onRelease(int x, int y) {
            }
        });

        buttons.add(new ToggleButton(70, 15, 60, 40, "Edit", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(1).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'e') {
                    buttons.get(1).mouseListener.onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
                }
            }
        });
        buttons.get(1).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
//                System.out.println("EDIT");

                if (editMenu) {
                    editMenu = false;
                    menuButtons = null;
                } else {
                    editMenu = true;
                    menuButtons = new Button[2];

                    Button undo = new MenuButton(70, 60, 79, 30, "Undo", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[0] = undo;
                    undo.setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 'z') {
                                menuButtons[0].mouseListener.onClick(menuButtons[0].x + 1, menuButtons[0].y + 1);
                            }
                        }
                    });
                    undo.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("UNDO");
                            menuButtons[0].updateIcon();
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });

                    Button redo = new MenuButton(70, 91, 79, 30, "Redo", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[1] = redo;
                    redo.setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 'y') {
                                menuButtons[1].mouseListener.onClick(menuButtons[1].x + 1, menuButtons[1].y + 1);
                            }
                        }
                    });
                    redo.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("REDO");
                            menuButtons[1].updateIcon();
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });
                }

                buttons.get(1).updateIcon(editMenu);
            }

            @Override
            public void onPress(int x, int y) {
            }

            @Override
            public void onRelease(int x, int y) {
            }
        });

        buttons.add(new ToggleButton(150, 5, 25, 25, rightAngleIcon.getImage(), rightAngleIconDepressed.getImage()));
        buttons.get(2).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("right selected");
                currentShape = buttons.get(2);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(180, 5, 25, 25, equilateralIcon.getImage(), equilateralIconDepressed.getImage()));
        buttons.get(3).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("equil selected");
                currentShape = buttons.get(3);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(210, 5, 25, 25, rectIcon.getImage(), rectIconDepressed.getImage()));
        buttons.get(4).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("rect selected");
                currentShape = buttons.get(4);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(150, 40, 25, 25, circleIcon.getImage(), circleIconDepressed.getImage()));
        buttons.get(5).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("circle selected");
                currentShape = buttons.get(5);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(180, 40, 25, 25, hexIcon.getImage(), hexIconDepressed.getImage()));
        buttons.get(6).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("hex selected");
                currentShape = buttons.get(6);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(210, 40, 25, 25, pentIcon.getImage(), pentIconDepressed.getImage()));
        buttons.get(7).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("pent selected");
                currentShape = buttons.get(7);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });


        //Creating buttons for color selection in the toolbar
        //Creating the buttons, adding them to the arraylist and setting up their listeners
        Color colorButtonStroke = new Color(0, 0, 0);
        buttons.add(new ColorButton(400, 5, 20, 20, Color.red, colorButtonStroke));
        setColorButtonListener(buttons.get(8));

        buttons.add(new ColorButton(425, 5, 20, 20, Color.pink, colorButtonStroke));
        setColorButtonListener(buttons.get(9));

        buttons.add(new ColorButton(450, 5, 20, 20, Color.orange, colorButtonStroke));
        setColorButtonListener(buttons.get(10));

        buttons.add(new ColorButton(475, 5, 20, 20, Color.yellow, colorButtonStroke));
        setColorButtonListener(buttons.get(11));

        buttons.add(new ColorButton(400, 30, 20, 20, Color.green, colorButtonStroke));
        setColorButtonListener(buttons.get(12));

        buttons.add(new ColorButton(425, 30, 20, 20, Color.blue, colorButtonStroke));
        setColorButtonListener(buttons.get(13));

        buttons.add(new ColorButton(450, 30, 20, 20, Color.black, colorButtonStroke));
        setColorButtonListener(buttons.get(14));

        buttons.add(new ColorButton(475, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonListener(buttons.get(15));

        //Buttons for custom colors, default color is white
        buttons.add(new ColorButton(500, 5, 20, 20, Color.white, colorButtonStroke));
        setColorButtonListener(buttons.get(16));

        buttons.add(new ColorButton(525, 5, 20, 20, Color.white, colorButtonStroke));
        setColorButtonListener(buttons.get(17));

        buttons.add(new ColorButton(500, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonListener(buttons.get(18));

        //this last button get overwritten if a new color is added from the gradient and all other spots are occupied
        buttons.add(new ColorButton(525, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonListener(buttons.get(19));


        buttons.add(new ToggleButton(560, 5, 45, 45, "Add", editColorsIcon.getImage(), editColorsIcon.getImage()));
        buttons.get(20).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'c') {
                    buttons.get(20).mouseListener.onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
                }
            }
        });
        buttons.get(20).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("EDITING");

                if (addColors) {
                    addColors = false;
                    colorWindowButtons = null;
                } else {
                    addColors = true;
                    colorWindowButtons = new Button[3];

                    colorWindowButtons[0] = new MenuButton(85, 85, 210, 30, "Add to Custom Colors", buttonDep.getImage(), buttonPre.getImage());
                    colorWindowButtons[0].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
//                            System.out.println("ADD COLORS");

                            if (gradientSelection != null) {
                                addCustomColor(gradientSelection);
                            }

                            colorWindowButtons[0].updateIcon();

                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    colorWindowButtons[1] = new MenuButton(300, 85, 40, 30, "Exit", buttonDep.getImage(), buttonPre.getImage());
                    colorWindowButtons[1].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
//                            System.out.println("EXIT");
                            colorWindowButtons[1].updateIcon();
                            addColors = false;
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    Color tempColor;
                    if (gradientSelection != null) {
                        tempColor = gradientSelection;
                    } else {
                        tempColor = Color.white;
                    }

                    colorWindowButtons[2] = new ColorButton(90, 325, 40, 30, tempColor, Color.black);

                    colorGradient = new ArrayList<>();
                    createGradient();
                }
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ColorButton(300, 5, 45, 60, "Color 1", Color.white, colorButtonStroke));
        buttons.get(21).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
//                buttons.get(21).textColor = currentColor;

            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ColorButton(350, 5, 45, 60, "Color 2", Color.black, colorButtonStroke));
        buttons.get(22).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                buttons.get(22).textColor = currentColor;
                System.out.println("To set up stroke color, select a color from the grid and press Color 2");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.add(new ToggleButton(245, 5, 50, 60, "Stroke", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(23).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == 'w') {
                    buttons.get(23).mouseListener.onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
                }
            }
        });
        buttons.get(23).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                if (strokeMenu) {
                    strokeMenu = false;
                    menuButtons = null;
                } else {
                    strokeMenu = true;
                    menuButtons = new Button[3];

                    menuButtons[0] = new ActiveButton(250, 65, 60, 30, "3", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[0].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke = 3;
                            menuButtons[0].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    menuButtons[1] = new ActiveButton(250, 95, 60, 30, "6", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[1].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke = 6;
                            menuButtons[0].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    menuButtons[2] = new ActiveButton(250, 125, 60, 30, "9", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[2].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke = 9;
                            menuButtons[0].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });
                }

                buttons.get(23).updateIcon(strokeMenu);
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        //Acts as a shape button
        buttons.add(new ToggleButton(620, 10, 130, 35, "Free Drawing", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(24).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Free drawing mode");
                currentShape = buttons.get(24);
                updateShapeIcons();
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
        super.paint(g);                                                 //Painting toolbar/rectangle

        g.setColor(Color.gray);
        g.drawLine(140, 0, 140, 69);                    //Separating line

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g, ob);                                //Painting all buttons
        }

        g.setColor(Color.gray);
        g.drawLine(240, 0, 240, 69);                    //Separating line

        try {
            if (fileMenu) {
                editMenu = false;
//                openWindow = false;
                addColors = false;
                strokeMenu = false;

                for (int i = 0; i < menuButtons.length; i++) {
                    menuButtons[i].paint(g, ob);
                }
            }

            if (editMenu) {
                fileMenu = false;
                addColors = false;
                strokeMenu = false;
//                openWindow = false;

                for (int i = 0; i < menuButtons.length; i++) {
                    menuButtons[i].paint(g, ob);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("OPEN ONE MENU AT A TIME");      //update icon when clicked omsewhere else fix
        }

        if (openWindow) {
            fileMenu = false;
            editMenu = false;
            addColors = false;
            strokeMenu = false;

            g.setColor(Color.lightGray);        //Color set to lightgray

            /*
            if (files != null) {
                int height;
                if (files.length * 30 > 600) {
                    height = 600;
                } else {
                    height = files.length * 30;
                }
            }
             */

            int height = 600;
            if (fileButtons != null) {
                if ((fileButtons.size() * 35) > 600) {
                    height = 600;
                } else {
                    height = fileButtons.size() * 35;
                }
            }

//            Rectangle openWindow = new Rectangle(80, 80, 300, height, Color.lightGray, Color.gray, 2);
//            openWindow.paint(g);

            g.fillRect(80, 80, 300, height + 10);       //fix height

            try {
                for (int i = 0; i < fileButtons.size(); i++) {
                    fileButtons.get(i).paint(g, ob);
                }
            } catch (NullPointerException ex) {
                System.out.println("NO FILES IN THE FOLDER");
            }


        }

        if (addColors) {            //if addcolors is true, the window is painted along with the gradient
            fileMenu = false;
            editMenu = false;
            openWindow = false;
            strokeMenu = false;

            g.setColor(Color.lightGray);
            g.fillRect(80, 80, 265, 300);       //drawing a rectangle

            for (int i = 0; i < colorWindowButtons.length; i++) {
                colorWindowButtons[i].paint(g, ob);                 //buttons in the color window
            }

            for (int i = 0; i < colorGradient.size(); i++) {
                colorGradient.get(i).paint(g, ob);                  //gradient color buttons
            }

            if (gradientSelection != null) {                        //if a color is selected, r g b values are updated in the window
                g.setColor(Color.BLACK);
                g.drawString("Red:    " + gradientSelection.getRed(), 150, 320);
                g.drawString("Green:  " + gradientSelection.getGreen(), 150, 340);
                g.drawString("Blue:   " + gradientSelection.getBlue(), 150, 360);
            } else {                                                 //default r g b shown in case no selection has been made yet
                g.drawString("Red:    " + 255, 150, 320);
                g.drawString("Green:  " + 255, 150, 340);
                g.drawString("Blue:   " + 255, 150, 360);
            }
        }

        if (strokeMenu) {           //if stroke menu is true, other menus are closed and stroke menu's buttons are painted
            fileMenu = false;
            editMenu = false;
            openWindow = false;
            addColors = false;

            g.setColor(Color.lightGray);
            g.fillRect(250, 65, 60, 90);

            for (int i = 0; i < menuButtons.length; i++) {
                menuButtons[i].paint(g, ob);
            }

        }

    }

    /**
     * @param x
     * @param y doesnt loop through all buttons because that is not needed
     */
    public void onPress(int x, int y) {
        super.onPress(x, y);
        if (menuButtons != null) {
            for (int i = 0; i < menuButtons.length; i++) {
                menuButtons[i].IsClicked(x, y);
                if (menuButtons[i].getPressed()) {
                    menuButtons[i].getListener().onPress(x, y);
                }
            }
        }
        if (colorWindowButtons != null) {
            for (int i = 0; i < colorWindowButtons.length; i++) {
                colorWindowButtons[i].IsClicked(x, y);
                if (colorWindowButtons[i].getPressed()) {
                    colorWindowButtons[i].getListener().onPress(x, y);
                }
            }
        }
    }

    /**
     * @param x
     * @param y doesnt loop through all buttons because that is not needed
     */
    public void onRelease(int x, int y) {
        super.onRelease(x, y);
        if (menuButtons != null) {
            for (int i = 0; i < menuButtons.length; i++) {
                menuButtons[i].IsClicked(x, y);
                if (menuButtons[i].getPressed()) {
                    menuButtons[i].getListener().onRelease(x, y);
                }
            }
        }
    }

    public void onClick(int x, int y) {
        super.onClick(x, y);

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
                    gradientSelection = cb.textColor;
                    colorWindowButtons[2].textColor = gradientSelection;
                    cb.setPressed(false);
                }
            }
        }

        if (fileButtons != null) {
            for (int i = 0; i < fileButtons.size(); i++) {
                fileButtons.get(i).IsClicked(x, y);
                if (fileButtons.get(i).getPressed()) {
                    fileButtons.get(i).getListener().onClick(x, y);
                    fileButtons.get(i).setPressed(false);
                }
            }
        }

    }

    /**
     * sets up an array of color buttons
     */
    private void createGradient() {
        int xClr;
        int yClr;

        int r;
        int g;
        int b;
        int jump = 7;

        for (yClr = 120; yClr < 300; yClr++) {

            xClr = 90;
            r = 255;
            b = 0;

            for (g = 0; g < 256; g = g + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }

            g = 255;
            b = 0;

            for (r = 255; r > 0; r = r - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }

            r = 0;
            g = 255;

            for (b = 0; b < 256; b = b + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }

            r = 0;
            b = 255;

            for (g = 255; g > 0; g = g - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }

            g = 0;
            b = 255;

            for (r = 0; r < 256; r = r + jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }

            r = 255;
            g = 0;

            for (b = 255; b > 0; b = b - jump) {
                ColorButton cb = new ColorButton(xClr++, yClr, 1, 1, new Color(r, g, b), null);
                cb.setDefaultGradientListener();
                colorGradient.add(cb);
            }
        }

    }

    /**
     * creates the window after pressing open
     */
    private void createOpenWindow() {
        int x = 85;
        int y = 85;

        fileButtons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {                   //Manually creating files
            FileButton fb = new FileButton(x, y, 280, 30, "FILE " + i, buttonDep.getImage(), buttonPre.getImage());
            setFileButtonListener(fb);
            fileButtons.add(fb);

            y += 35;
        }

        /*
        File file = new File("src/shapes");
        files = file.listFiles();

        int x = 85;
        int y = 85;

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                file = files[i];
                fileButtons.add(new FileButton(x, y, 280, 30, file.getName(), buttonDep.getImage(), buttonPre.getImage()));
                y += 35;
            }
        } else {
            System.out.println("NO FILES FOUND");
        }
         */

    }

    /**
     * initialises all images used in the class
     */
    protected void initializeImages() {
        buttonDep = new ImageIcon("src/square_depressed.png");
        buttonPre = new ImageIcon("src/square_pressed.png");

        rightAngleIcon = new ImageIcon("src/shapeIcons/rightAngleIcon.png");
        equilateralIcon = new ImageIcon("src/shapeIcons/equilateralIcon.png");
        rectIcon = new ImageIcon("src/shapeIcons/rectIcon.png");
        circleIcon = new ImageIcon("src/shapeIcons/circleIcon.png");
        hexIcon = new ImageIcon("src/shapeIcons/hexIcon.png");
        pentIcon = new ImageIcon("src/shapeIcons/pentIcon.png");

        rightAngleIconDepressed = new ImageIcon("src/shapeIcons/depressed/rightAngleIcon.png");
        equilateralIconDepressed = new ImageIcon("src/shapeIcons/depressed/equilateralIcon.png");
        rectIconDepressed = new ImageIcon("src/shapeIcons/depressed/rectIcon.png");
        circleIconDepressed = new ImageIcon("src/shapeIcons/depressed/circleIcon.png");
        hexIconDepressed = new ImageIcon("src/shapeIcons/depressed/hexIcon.png");
        pentIconDepressed = new ImageIcon("src/shapeIcons/depressed/pentIcon.png");

        editColorsIcon = new ImageIcon("src/colorsIcon.png");
    }


    Button getCurrentShape() {
        return currentShape;
    }

    /**
     * loops through the buttons arraylist and sets all images of shapes/free drawing to depressed except the one selected
     */
    void updateShapeIcons() {
        Button button = getCurrentShape();
        System.out.println(button.current_image);
        for (int i = 2; i < 8; i++) {
            if (!buttons.get(i).equals(button) && button instanceof ToggleButton) {
                buttons.get(i).setDepressedImage();
            }
        }
        if (!buttons.get(24).equals(button) && button instanceof ToggleButton) {
            buttons.get(24).setDepressedImage();
        }
    }

    /**
     * @param button colorbutton of type button
     *               <p>
     *               sets up the listener for a color button
     *               the listener sets the currentColor variable to the color of the parameter button and outputs a message
     */
    void setColorButtonListener(Button button) {
        button.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                currentColor = button.textColor;
                buttons.get(21).textColor = currentColor;
                System.out.println("Current color is " + currentColor.getRed() + " " + currentColor.getGreen() + " " + currentColor.getBlue());
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
    }

    /**
     * @param color color to be added to the toolbar
     *              looks for empty spots in the 4 spots reserved for custom colors
     *              if all spots are taken, the last spot is overwritten
     */
    void addCustomColor(Color color) {
        Color defaultColor = new Color(255, 255, 255);

        for (int i = 16; i <= 19; i++) {
            if (buttons.get(i).textColor.equals(defaultColor)) {
                buttons.get(i).textColor = color;
                return;
            }
        }
        System.out.println("Ran out of custom spaces - Overwriting last space");
        buttons.get(19).textColor = color;
        return;
    }

    void setFileButtonListener(Button button) {
        button.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println(button.text + " loaded!");
                button.updateIcon();
                buttons.get(0).updateIcon(fileMenu);
                openWindow = false;
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
    }

}
