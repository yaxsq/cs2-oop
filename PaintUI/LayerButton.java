import java.awt.*;

public class LayerButton extends ToggleButton{
    boolean selected;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param i_depressed
     * @param i_pressed
     *
     * buttons used for layers only
     */
    public LayerButton(int x, int y, int width, int height, String text, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, text, i_depressed, i_pressed);
        selected = false;
    }

    public boolean IsClicked(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            pressed = true;
            current_image = image_pressed;
            textColor = Color.white;
            return true;
        }
        return false;
    }

    /**
     * sets up a default listener for layer buttons
     */
    public void setUpLayerButtonListener() {
        super.setListener(new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println(text + " chosen.");

                selected = true;

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
