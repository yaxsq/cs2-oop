package Buttons;

import Listeners.*;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class FileButton extends ActiveButton implements Serializable {

    File file;

    public FileButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed, File file) {
        super(x, y, width, height, text, i_depressed, i_pressed);
        this.file = file;
    }

    public FileButton () {}

    public File getFile() {
        return this.file;
    }


    //setters and getters for filesaving


    public void setFile(File file) {
        this.file = file;
    }
}
