package Toolbars;

import Buttons.*;
import Buttons.Button;
import DrawingPanel.DrawingPanel;
import Listeners.keyListener;
import Listeners.mouseListener;
import Singletons.GradientWindow;
import Singletons.Grid;
import Listeners.tooltipListener;
import Singletons.Tooltip;
import SwingTimerEx.SwingTimerEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import static Singletons.Tooltip.visible;

public class TopBar extends Toolbar implements mouseListener, ActionListener, Serializable {

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
    private static boolean fileMenu;
    private static boolean openWindow;
    private static boolean editMenu;
    private static boolean strokeMenu;

    private File[] files;                           //array for files for open window
    private ArrayList<FileButton> fileButtons;      //array for buttons for the files in openwindow

    private Button[] menuButtons;                   //array used to store buttons for dropdown menus
    private Button[] fileMenuButtons;
    private Button[] editMenuButtons;
    private Button[] strokeMenuButtons;

    public static Color currentColor = Color.pink;
    public static Color gradientSelection;
    public static boolean addCustomColor;

    public static int stroke = 3;                                       //Default value set to 3
    public static Color strokeColor;

    private Button shapeChosen;
    public static String currentShape;

    public TopBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        initializeImages();
        buttons = new ArrayList<>();

        /**
         * created all buttons in the toolbar manually
         * set up their mouse and key listeners
         */

        buttons.add(new ToggleButton(5, 15, 60, 40, "File", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(0).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(f) File menu";
            }
        });
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
                    fileMenuButtons = new Button[3];

                    Button newButton = new MenuButton(0, 60, 79, 30, "New", buttonDep.getImage(), buttonPre.getImage());
                    fileMenuButtons[0] = newButton;
                    fileMenuButtons[0].setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 'n') {
                                fileMenuButtons[0].getListener().onClick(fileMenuButtons[0].x + 1, fileMenuButtons[0].y + 1);
                            }
                        }
                    });
                    fileMenuButtons[0].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("New");
                            SwingTimerEx.refresh();

                            fileMenuButtons[0].updateIcon();
                            fileMenu = false;
                            buttons.get(0).updateIcon(fileMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });

                    Button openButton = new MenuButton(0, 91, 79, 30, "Open", buttonDep.getImage(), buttonPre.getImage());
                    fileMenuButtons[1] = openButton;
                    openButton.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
//                            System.out.println("OPEN");
                            fileMenuButtons[1].updateIcon();

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
                    fileMenuButtons[2] = saveButton;
                    saveButton.setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 's') {
                                saveButton.getListener().onClick(saveButton.x + 1, saveButton.y + 1);
                            }
                        }
                    });
                    saveButton.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("SAVING");

                            SwingTimerEx.save();
//                            SwingTimerEx.saveStacks();       //saves the current stack

                            fileMenuButtons[2].updateIcon();
                            fileMenu = false;
                            buttons.get(0).updateIcon(fileMenu);
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
        buttons.get(1).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(e) Edit menu";
            }
        });
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
                    editMenuButtons = new Button[2];

                    Button undo = new MenuButton(70, 60, 79, 30, "Undo", buttonDep.getImage(), buttonPre.getImage());
                    editMenuButtons[0] = undo;
                    undo.setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 'z') {
                                editMenuButtons[0].mouseListener.onClick(editMenuButtons[0].x + 1, editMenuButtons[0].y + 1);
                            }
                        }
                    });
                    undo.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            System.out.println("UNDO");
                            DrawingPanel.undo();

                            editMenuButtons[0].updateIcon();
                            editMenu = false;
                            buttons.get(1).updateIcon(editMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {
                        }

                        @Override
                        public void onRelease(int x, int y) {
                        }
                    });

                    Button redo = new MenuButton(70, 91, 79, 30, "Redo", buttonDep.getImage(), buttonPre.getImage());
                    editMenuButtons[1] = redo;
                    redo.setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == 'y') {
                                editMenuButtons[1].mouseListener.onClick(editMenuButtons[1].x + 1, editMenuButtons[1].y + 1);
                            }
                        }
                    });
                    redo.setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
//                            System.out.println("REDO");
                            DrawingPanel.redo();

                            editMenuButtons[1].updateIcon();
                            editMenu = false;
                            buttons.get(1).updateIcon(editMenu);
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
        buttons.get(2).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(1) Right Angle Triangle";
            }
        });
        buttons.get(2).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("right selected");
                shapeChosen = buttons.get(2);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
        buttons.get(2).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '1') {
                    buttons.get(2).mouseListener.onClick(buttons.get(2).x + 1, buttons.get(2).y + 1);
                }
            }
        });

        buttons.add(new ToggleButton(180, 5, 25, 25, equilateralIcon.getImage(), equilateralIconDepressed.getImage()));
        buttons.get(3).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(2) Equilateral Triangle";
            }
        });
        buttons.get(3).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '2') {
                    buttons.get(3).mouseListener.onClick(buttons.get(3).x + 1, buttons.get(3).y + 1);
                }
            }
        });
        buttons.get(3).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("equil selected");
                shapeChosen = buttons.get(3);
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
        buttons.get(4).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(3) Rectangle";
            }
        });
        buttons.get(4).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("rect selected");
                shapeChosen = buttons.get(4);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
        buttons.get(4).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '3') {
                    buttons.get(4).mouseListener.onClick(buttons.get(4).x + 1, buttons.get(4).y + 1);
                }
            }
        });

        buttons.add(new ToggleButton(150, 40, 25, 25, circleIcon.getImage(), circleIconDepressed.getImage()));
        buttons.get(5).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(3) Circle";
            }
        });
        buttons.get(5).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("circle selected");
                shapeChosen = buttons.get(5);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
        buttons.get(5).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '4') {
                    buttons.get(5).mouseListener.onClick(buttons.get(5).x + 1, buttons.get(5).y + 1);
                }
            }
        });

        buttons.add(new ToggleButton(180, 40, 25, 25, hexIcon.getImage(), hexIconDepressed.getImage()));
        buttons.get(6).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(5) Hexagon";
            }
        });
        buttons.get(6).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("hex selected");
                shapeChosen = buttons.get(6);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
        buttons.get(6).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '5') {
                    buttons.get(6).mouseListener.onClick(buttons.get(6).x + 1, buttons.get(6).y + 1);
                }
            }
        });

        buttons.add(new ToggleButton(210, 40, 25, 25, pentIcon.getImage(), pentIconDepressed.getImage()));
        buttons.get(7).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(6) Pentagram";
            }
        });
        buttons.get(7).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("pent selected");
                shapeChosen = buttons.get(7);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });
        buttons.get(7).setKeyListener(new keyListener() {
            @Override
            public void onKeyRelease(char key) {
                if (key == '6') {
                    buttons.get(7).mouseListener.onClick(buttons.get(7).x + 1, buttons.get(7).y + 1);
                }
            }
        });


        //Creating buttons for color selection in the toolbar
        //Creating the buttons, adding them to the arraylist and setting up their listeners
        Color colorButtonStroke = new Color(0, 0, 0);
        buttons.add(new ColorButton(400, 5, 20, 20, Color.red, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(8));
        setColorButtonMouseListener(buttons.get(8));

        buttons.add(new ColorButton(425, 5, 20, 20, Color.pink, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(9));
        setColorButtonMouseListener(buttons.get(9));

        buttons.add(new ColorButton(450, 5, 20, 20, Color.orange, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(10));
        setColorButtonMouseListener(buttons.get(10));

        buttons.add(new ColorButton(475, 5, 20, 20, Color.yellow, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(11));
        setColorButtonMouseListener(buttons.get(11));

        buttons.add(new ColorButton(400, 30, 20, 20, Color.green, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(12));
        setColorButtonMouseListener(buttons.get(12));

        buttons.add(new ColorButton(425, 30, 20, 20, Color.blue, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(13));
        setColorButtonMouseListener(buttons.get(13));

        buttons.add(new ColorButton(450, 30, 20, 20, Color.black, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(14));
        setColorButtonMouseListener(buttons.get(14));

        buttons.add(new ColorButton(475, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(15));
        setColorButtonMouseListener(buttons.get(15));

        //Buttons for custom colors, default color is white
        buttons.add(new ColorButton(500, 5, 20, 20, Color.white, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(16));
        setColorButtonMouseListener(buttons.get(16));

        buttons.add(new ColorButton(525, 5, 20, 20, Color.white, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(17));
        setColorButtonMouseListener(buttons.get(17));

        buttons.add(new ColorButton(500, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(18));
        setColorButtonMouseListener(buttons.get(18));

        //this last button get overwritten if a new color is added from the gradient and all other spots are occupied
        buttons.add(new ColorButton(525, 30, 20, 20, Color.white, colorButtonStroke));
        setColorButtonTooltipListener(buttons.get(19));
        setColorButtonMouseListener(buttons.get(19));


        buttons.add(new ToggleButton(560, 5, 45, 45, "Add", editColorsIcon.getImage(), editColorsIcon.getImage()));
        buttons.get(20).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(c) Add custom colors";
            }
        });
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
            public void onClick(int x, int y) {             // change this to only calling gradient winvdow visible
                System.out.println("EDITING");

                if (GradientWindow.visible) {                           //switching bw true and false
                    GradientWindow.visible = false;
                } else {
                    GradientWindow.visible = true;
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

        buttons.get(21).textColor = currentColor;

        buttons.add(new ColorButton(350, 5, 45, 60, "Color 2", Color.black, colorButtonStroke));
        buttons.get(22).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "Set stroke color by choosing a color \nand pressing this button";
            }
        });
        buttons.get(22).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                buttons.get(22).textColor = currentColor;
                strokeColor = buttons.get(22).textColor;

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
        buttons.get(23).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(w) Set stroke value";
            }
        });
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
                    strokeMenuButtons = new Button[2];


                    strokeMenuButtons[0] = new ActiveButton(250, 65, 60, 30, "-", buttonDep.getImage(), buttonPre.getImage());
                    strokeMenuButtons[0].setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == ',') {
                                strokeMenuButtons[0].getListener().onClick(strokeMenuButtons[0].x + 1, strokeMenuButtons[0].y + 1);
                            }
                        }
                    });
                    strokeMenuButtons[0].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {

                            if (stroke >= 1) {
                                stroke--;
                            } else {
                                System.out.println("Stroke set to the minimum.");
                            }

                            strokeMenuButtons[0].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                            buttons.get(23).updateIcon(strokeMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    strokeMenuButtons[1] = new ActiveButton(250, 95, 60, 30, "+", buttonDep.getImage(), buttonPre.getImage());
                    strokeMenuButtons[1].setKeyListener(new keyListener() {
                        @Override
                        public void onKeyRelease(char key) {
                            if (key == '.') {
                                strokeMenuButtons[1].getListener().onClick(strokeMenuButtons[1].x + 1, strokeMenuButtons[1].y + 1);
                            }
                        }
                    });
                    strokeMenuButtons[1].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke++;
                            strokeMenuButtons[1].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                            buttons.get(23).updateIcon(strokeMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    /*
                    menuButtons[2] = new ActiveButton(250, 125, 60, 30, "9", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[2].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke = 9;
                            menuButtons[2].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                            buttons.get(23).updateIcon(strokeMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    menuButtons[3] = new ActiveButton(250, 155, 60, 30, "13", buttonDep.getImage(), buttonPre.getImage());
                    menuButtons[3].setListener(new mouseListener() {
                        @Override
                        public void onClick(int x, int y) {
                            stroke = 13;
                            menuButtons[3].updateIcon();
                            System.out.println("Stroke set to " + stroke);
                            strokeMenu = false;
                            buttons.get(23).updateIcon(strokeMenu);
                        }

                        @Override
                        public void onPress(int x, int y) {

                        }

                        @Override
                        public void onRelease(int x, int y) {

                        }
                    });

                    */

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
        buttons.get(24).setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return "(7) Doodle mode";
            }
        });
        buttons.get(24).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Free drawing mode");
                shapeChosen = buttons.get(24);
                updateShapeIcons();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        //Grid button
        buttons.add(new ActiveButton(760, 10, 70, 35, "Grid", buttonDep.getImage(), buttonPre.getImage()));
        buttons.get(25).setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                Grid grid = Grid.getGrid();

                grid.onClick(x, y);
                buttons.get(25).updateIcon();
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        });

        buttons.get(0).getListener().onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
        buttons.get(0).getListener().onClick(buttons.get(0).x + 1, buttons.get(0).y + 1);
        buttons.get(1).getListener().onClick(buttons.get(1).x + 1, buttons.get(1).y + 1);
        buttons.get(1).getListener().onClick(buttons.get(1).x + 1, buttons.get(1).y + 1);
        buttons.get(23).getListener().onClick(buttons.get(23).x + 1, buttons.get(23).y + 1);
        buttons.get(23).getListener().onClick(buttons.get(23).x + 1, buttons.get(23).y + 1);
    }

    public TopBar() {}

    public void paint(Graphics g, ImageObserver ob) {
        super.paint(g, ob);                                                 //Painting toolbar/rectangle        //CHANGED added ob
        Grid grid = Grid.getGrid();
        grid.paint(g);

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
                strokeMenu = false;

                for (int i = 0; i < fileMenuButtons.length; i++) {
                    fileMenuButtons[i].paint(g, ob);
                }
            }

            if (editMenu) {
                fileMenu = false;
                strokeMenu = false;
//                openWindow = false;

                for (int i = 0; i < editMenuButtons.length; i++) {
                    editMenuButtons[i].paint(g, ob);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("OPEN ONE MENU AT A TIME");      //update icon when clicked omsewhere else fix
        }

        if (openWindow) {
            fileMenu = false;
            editMenu = false;
            strokeMenu = false;

            g.setColor(Color.lightGray);        //Color set to lightgray

            int height = 600;
            if (fileButtons != null) {                                          //setting up the window's height
                if ((fileButtons.size() * 35) > 600) {
                    height = 600;
                } else {
                    height = fileButtons.size() * 35;
                }
            }

            try {
                if (fileButtons != null && fileButtons.size() > 0) {                    //draws window and buttons if arraylist not empty
                    g.fillRect(80, 80, 300, height + 10);       //drawing rectangle for window and adjusting height
                    for (int i = 0; i < fileButtons.size(); i++) {
                        fileButtons.get(i).paint(g, ob);
                    }
                } else {
                    System.out.println("NO FILES IN THE DIRECTORY.");                   //prints message if no files found
                    buttons.get(0).updateIcon(fileMenu);
                    openWindow = false;
                }
            } catch (NullPointerException ex) {
                System.out.println("NO FILES IN THE FOLDER");
                return;
            }

        }

        if (strokeMenu) {           //if stroke menu is true, other menus are closed and stroke menu's buttons are painted
            fileMenu = false;
            editMenu = false;
            openWindow = false;

            g.setColor(Color.lightGray);
            g.fillRect(250, 65, 60, 60);

            for (int i = 0; i < strokeMenuButtons.length; i++) {
                strokeMenuButtons[i].paint(g, ob);
            }

        }


        if (visible) {
            Tooltip.getInstance().paint(g);                     // fix  this gets drawn before other elements so appears to be under them
        }

//        GradientWindow.getInstance().paint(g, ob);
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

        if (GradientWindow.visible) {
            GradientWindow.getInstance().onPress(x, y);
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

        if (fileMenuButtons != null && fileMenu) {
            for (int i = 0; i < fileMenuButtons.length; i++) {
                fileMenuButtons[i].IsClicked(x, y);
                if (fileMenuButtons[i].getPressed()) {
                    fileMenuButtons[i].getListener().onClick(x, y);
                    fileMenuButtons[i].setPressed(false);
                }
            }
        }

        if (editMenuButtons != null && editMenu) {
            for (int i = 0; i < editMenuButtons.length; i++) {
                editMenuButtons[i].IsClicked(x, y);
                if (editMenuButtons[i].getPressed()) {
                    editMenuButtons[i].getListener().onClick(x, y);
                    editMenuButtons[i].setPressed(false);
                }
            }
        }

        if (fileButtons != null && openWindow) {
            for (int i = 0; i < fileButtons.size(); i++) {
                fileButtons.get(i).IsClicked(x, y);
                if (fileButtons.get(i).getPressed()) {
                    fileButtons.get(i).getListener().onClick(x, y);
                    fileButtons.get(i).setPressed(false);
                }
            }
        }

        if (strokeMenuButtons != null && strokeMenu) {
            for (int i = 0; i < strokeMenuButtons.length; i++) {
                strokeMenuButtons[i].IsClicked(x, y);
                if (strokeMenuButtons[i].getPressed()) {
                    strokeMenuButtons[i].getListener().onClick(x, y);
                    strokeMenuButtons[i].setPressed(false);
                }
            }
        }

        if (GradientWindow.visible) {
            GradientWindow.getInstance().onClick(x, y);
        }

    }

    public void onKeyRelease(char key) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getKeyListener() != null) {
                buttons.get(i).getKeyListener().onKeyRelease(key);
            }
        }

        for (int i = 0; i < fileMenuButtons.length; i++) {
            if (fileMenuButtons[i].getKeyListener() != null) {
                fileMenuButtons[i].getKeyListener().onKeyRelease(key);
            }
        }

        for (int i = 0; i < editMenuButtons.length; i++) {
            if (editMenuButtons[i].getKeyListener() != null) {
                editMenuButtons[i].getKeyListener().onKeyRelease(key);
            }
        }

        for (int i = 0; i < strokeMenuButtons.length; i++) {
            if (strokeMenuButtons[i].getListener() != null) {
                strokeMenuButtons[i].getKeyListener().onKeyRelease(key);
            }
        }
    }

    /**
     * creates the window after pressing open
     */
    private void createOpenWindow() {
        int x = 85;
        int y = 85;

        fileButtons = new ArrayList<>();                             //resetting arraylist of buttons and files array
        files = null;

        File file = new File("src/FILES/");
        files = file.listFiles();                                    //files array becomes populated with files in the directory defined above

        if (files != null || files.length <= 0) {
            for (int i = 0; i < files.length; i++) {
                file = files[i];
                fileButtons.add(new Buttons.FileButton(x, y, 280, 30, file.getName(), buttonDep.getImage(), buttonPre.getImage(), file));
                setFileButtonListener(fileButtons.get(fileButtons.size() - 1));
                y += 35;

            }
        } else if (files[0] == null) {
            openWindow = false;
            fileButtons = null;
            System.out.println("NO FILES FOUND");
        }

    }

    /**
     * initialises all images used in the class
     */
    protected void initializeImages() {
        buttonDep = new ImageIcon("src/otherIcons/square_depressed.png");
        buttonPre = new ImageIcon("src/otherIcons/square_pressed.png");

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

        editColorsIcon = new ImageIcon("src/otherIcons/colorsIcon.png");
    }

    Button getCurrentShape() {
        return shapeChosen;
    }

    /**
     * loops through the buttons arraylist and sets all images of shapes/free drawing to depressed except the one selected
     */
    void updateShapeIcons() {
        Button button = getCurrentShape();
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
    void setColorButtonMouseListener(Button button) {
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
//                SwingTimerEx.loadStack(((FileButton)button).getFile().getName());           //loads the stack from the file stored
                SwingTimerEx.load(((FileButton)button).getFile().getName());           //loads the stack from the file stored

                System.out.println(button.text + " loaded!");
                System.out.println(((FileButton)button).getFile().getPath() + " setFieLBtuttonsLIstener called");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        updateShape();                                                      //sets the current shape to the one selected
        updateShapeIcons();

        if (addCustomColor) {                                               //if flag is true, method is called and color is added
            addCustomColor(gradientSelection);
            addCustomColor = false;
        }

    }

    private void updateShape() {
        if (getCurrentShape() == null) {
            currentShape = null;
            return;
        } else if (getCurrentShape().equals(buttons.get(2))) {
            currentShape = "righttriangle";
            return;
        } else if (getCurrentShape().equals(buttons.get(3))) {
            currentShape = "equiltriangle";
            return;
        } else if (getCurrentShape().equals(buttons.get(4))) {
            currentShape = "rectangle";
            return;
        } else if (getCurrentShape().equals(buttons.get(5))) {
            currentShape = "circle";
            return;
        } else if (getCurrentShape().equals(buttons.get(6))) {
            currentShape = "hexagon";
            return;
        } else if (getCurrentShape().equals(buttons.get(7))) {
            currentShape = "pentagram";
            return;
        } else if (getCurrentShape().equals(buttons.get(24))) {
            currentShape = "free";
            return;
        }
    }

    public static void setAllMenusFalse() {
        fileMenu = false;
        editMenu = false;
        openWindow = false;
        strokeMenu = false;
    }

    public static void setColorButtonTooltipListener(Button cb) {
        cb.setTooltipListener(new tooltipListener() {
            @Override
            public String onHover() {
                return cb.textColor.getRed() + ", " + cb.textColor.getGreen() + ", " + cb.textColor.getBlue();
            }
        });
    }

    //setters and getters for filesaving


    public void setButtonDep(ImageIcon buttonDep) {
        this.buttonDep = buttonDep;
    }

    public void setButtonPre(ImageIcon buttonPre) {
        this.buttonPre = buttonPre;
    }

    public void setStroke(int stroke) {                                 //static - potential conflict
        TopBar.stroke = stroke;
    }

    public void setStrokeColor(Color strokeColor) {                     //static - potential conflict
        TopBar.strokeColor = strokeColor;
    }

    public static void setCurrentShape(String currentShape) {
        TopBar.currentShape = currentShape;
    }

    public static void setAddCustomColor(boolean addCustomColor) {
        TopBar.addCustomColor = addCustomColor;
    }

    public void setCircleIcon(ImageIcon circleIcon) {
        this.circleIcon = circleIcon;
    }

    public void setCircleIconDepressed(ImageIcon circleIconDepressed) {
        this.circleIconDepressed = circleIconDepressed;
    }

    public static void setCurrentColor(Color currentColor) {
        TopBar.currentColor = currentColor;
    }

    public void setEditColorsIcon(ImageIcon editColorsIcon) {
        this.editColorsIcon = editColorsIcon;
    }

    public static void setEditMenu(boolean editMenu) {
        TopBar.editMenu = editMenu;
    }

    public void setEditMenuButtons(Button[] editMenuButtons) {
        this.editMenuButtons = editMenuButtons;
    }

    public void setEquilateralIcon(ImageIcon equilateralIcon) {
        this.equilateralIcon = equilateralIcon;
    }

    public void setEquilateralIconDepressed(ImageIcon equilateralIconDepressed) {
        this.equilateralIconDepressed = equilateralIconDepressed;
    }

    public void setFileButtons(ArrayList<FileButton> fileButtons) {
        this.fileButtons = fileButtons;
    }

    public static void setFileMenu(boolean fileMenu) {
        TopBar.fileMenu = fileMenu;
    }

    public void setFileMenuButtons(Button[] fileMenuButtons) {
        this.fileMenuButtons = fileMenuButtons;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public static void setGradientSelection(Color gradientSelection) {
        TopBar.gradientSelection = gradientSelection;
    }

    public void setHexIcon(ImageIcon hexIcon) {
        this.hexIcon = hexIcon;
    }

    public void setHexIconDepressed(ImageIcon hexIconDepressed) {
        this.hexIconDepressed = hexIconDepressed;
    }

    public void setMenuButtons(Button[] menuButtons) {
        this.menuButtons = menuButtons;
    }

    public static void setOpenWindow(boolean openWindow) {
        TopBar.openWindow = openWindow;
    }

    public void setPentIcon(ImageIcon pentIcon) {
        this.pentIcon = pentIcon;
    }

    public void setPentIconDepressed(ImageIcon pentIconDepressed) {
        this.pentIconDepressed = pentIconDepressed;
    }

    public void setRectIcon(ImageIcon rectIcon) {
        this.rectIcon = rectIcon;
    }

    public void setRectIconDepressed(ImageIcon rectIconDepressed) {
        this.rectIconDepressed = rectIconDepressed;
    }

    public void setRightAngleIcon(ImageIcon rightAngleIcon) {
        this.rightAngleIcon = rightAngleIcon;
    }

    public void setRightAngleIconDepressed(ImageIcon rightAngleIconDepressed) {
        this.rightAngleIconDepressed = rightAngleIconDepressed;
    }

    public void setShapeChosen(Button shapeChosen) {
        this.shapeChosen = shapeChosen;
    }

    public static void setStrokeMenu(boolean strokeMenu) {
        TopBar.strokeMenu = strokeMenu;
    }

    public void setStrokeMenuButtons(Button[] strokeMenuButtons) {
        this.strokeMenuButtons = strokeMenuButtons;
    }

    @Override
    protected void setColor(Color color) {
        super.setColor(color);
    }

    @Override
    public void setBottomRight(Point bottomRight) {
        super.setBottomRight(bottomRight);
    }

    @Override
    public void setButton_color(Color button_color) {
        super.setButton_color(button_color);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    @Override
    public void setLocation(Point topLeft, Point bottomRight) {
        super.setLocation(topLeft, bottomRight);
    }

    @Override
    public void setStroke_color(Color stroke_color) {
        super.setStroke_color(stroke_color);
    }

    @Override
    public void setTopLeft(Point topLeft) {
        super.setTopLeft(topLeft);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    public ImageIcon getButtonPre() {
        return buttonPre;
    }

    public ImageIcon getButtonDep() {
        return buttonDep;
    }

    public ImageIcon getCircleIcon() {
        return circleIcon;
    }

    public int getStroke() {                        //static - potential conflict
        return stroke;
    }

    public Color getStrokeColor() {                 //static - potential conflict
        return strokeColor;
    }

    public ArrayList<FileButton> getFileButtons() {
        return fileButtons;
    }

    public Button getShapeChosen() {
        return shapeChosen;
    }

    public Button[] getEditMenuButtons() {
        return editMenuButtons;
    }

    public Button[] getFileMenuButtons() {
        return fileMenuButtons;
    }

    public Button[] getMenuButtons() {
        return menuButtons;
    }

    public Button[] getStrokeMenuButtons() {
        return strokeMenuButtons;
    }

    public static Color getCurrentColor() {
        return currentColor;
    }

    public static Color getGradientSelection() {
        return gradientSelection;
    }

    public ImageIcon getEquilateralIcon() {
        return equilateralIcon;
    }

    public File[] getFiles() {
        return files;
    }

    public ImageIcon getCircleIconDepressed() {
        return circleIconDepressed;
    }

    public ImageIcon getEditColorsIcon() {
        return editColorsIcon;
    }

    public ImageIcon getEquilateralIconDepressed() {
        return equilateralIconDepressed;
    }

    public ImageIcon getHexIcon() {
        return hexIcon;
    }

    public ImageIcon getHexIconDepressed() {
        return hexIconDepressed;
    }

    public ImageIcon getPentIcon() {
        return pentIcon;
    }

    public ImageIcon getPentIconDepressed() {
        return pentIconDepressed;
    }

    public ImageIcon getRectIcon() {
        return rectIcon;
    }

    public ImageIcon getRectIconDepressed() {
        return rectIconDepressed;
    }

    public ImageIcon getRightAngleIcon() {
        return rightAngleIcon;
    }

    public ImageIcon getRightAngleIconDepressed() {
        return rightAngleIconDepressed;
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public int[] getXCoords(Point[] arr) {
        return super.getXCoords(arr);
    }

    @Override
    public int[] getYCoords(Point[] arr) {
        return super.getYCoords(arr);
    }

    @Override
    public Color getButton_color() {
        return super.getButton_color();
    }

    @Override
    public Color getStroke_color() {
        return super.getStroke_color();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public Point getBottomRight() {
        return super.getBottomRight();
    }

    @Override
    public Point getTopLeft() {
        return super.getTopLeft();
    }
}
