import java.awt.*;
import java.awt.image.ImageObserver;

public class ActiveButton extends Button {

    public ActiveButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
//        this.setUpListener();
    }

    public ActiveButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, text, i_depressed, i_pressed);
//        this.setUpListener();
    }


    protected void setUpListener() {        /*
        super.setListener(new Listener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("CLICKCED ACTIVE");
            }

            @Override
            public void onPress(int x, int y) {
                System.out.println("PRESSED ACTIVE");
                ActiveButton.super.setPressed(true);
            }

            @Override
            public void onRelease(int x, int y) {
                System.out.println("REALESED ACTIVE");
                ActiveButton.super.setPressed(false);

            }
        });         */
    }

}
