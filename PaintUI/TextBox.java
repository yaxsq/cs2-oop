import java.awt.*;

public class TextBox {

    private int x;
    private int y;
    private String text;
    private int fontSize;
    private Color textColor;

    TextBox (int x, int y, String text, int fontSize, Color textColor) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.fontSize = fontSize;
        this.textColor = textColor;

    }

    void paint(Graphics g) {
        Font font = new Font(text, Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(textColor);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getAscent() - fontMetrics.getDescent();
//        g.drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2) );
        g.drawString(text, x, y);
    }

}
