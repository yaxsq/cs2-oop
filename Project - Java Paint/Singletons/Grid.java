package Singletons;

import java.awt.*;
import java.io.Serializable;

import Board.Board;
import DrawingPanel.DrawingPanel;

public class Grid implements Serializable {

    private static Grid instance = new Grid();
    private static boolean visible = false;
    private static int value = 2;
    private static Color color = Color.gray;

    private static int x, y, width, height;

    private Grid() {
        x = 0;
        y = 70;
        width = Board.B_WIDTH - 150;
        height = Board.B_HEIGHT - 80;
    }

    public static Grid getGrid() {
        return instance;
    }

    public static void paint(Graphics g) {
        if (visible) {
            g.setColor(color);

            for (int i = DrawingPanel.getY(); i < DrawingPanel.getHeight() + DrawingPanel.getY(); i += value) {
                for (int j = DrawingPanel.getX(); j < DrawingPanel.getWidth() + DrawingPanel.getX(); j += value) {
                    g.drawRect(j, i, value, value);
//                    System.out.println(i + " " + j);
                }
            }

        }

    }

    public static void onClick(int x, int y) {
        if (visible) {
            if (value < 64) {
                value = value * 2;
            } else {
                value = 2;
                visible = false;
            }
        } else {
            visible = true;
        }
        System.out.println("GRID IS " + visible + " " + value);
    }

}
