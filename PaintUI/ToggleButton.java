import java.awt.*;
import java.awt.image.ImageObserver;

public class ToggleButton extends Button {

    public ToggleButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
//        this.setUpListener();
    }

    public ToggleButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, text, i_depressed, i_pressed);
//        this.setUpListener();
    }

    public ToggleButton () {    }

    protected void setUpListener() {        /*
        super.setListener(new Listener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("CLICKED TOGGLE");

            }

            @Override
            public void onPress(int x, int y) {
                System.out.println("PRESSED TOGGLE");
            }

            @Override
            public void onRelease(int x, int y) {
                System.out.println("REALESED TOGGLE");
                if (current_image.equals(image_pressed)) {      //////////
                    current_image = image_depressed;
                }
                setPressed(false);
            }
        });     */
    }

}
