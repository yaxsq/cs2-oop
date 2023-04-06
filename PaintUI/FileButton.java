import java.awt.*;

public class FileButton extends  ActiveButton{

    public FileButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, text, i_depressed, i_pressed);
    }

    void setDefaultListener () {
        this.mouseListener = new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println(text + " loaded!");
            }

            @Override
            public void onPress(int x, int y) {

            }

            @Override
            public void onRelease(int x, int y) {

            }
        };
    }
}
