package drawing;
import java.awt.*;

public class Rectangle
{
	private Point center ;
	private int width;
	private int height;
	private int stroke;
	private Color button_color;
	private Color stroke_color;
	private String txt;
	private Graphics g;

	public Rectangle()
	{

	}

	public Rectangle(int x, int y, int width, int height, Color button_color, Color stroke_color, int stroke, String txt, Graphics g)
	{
		center = new Point(x, y);
		this.width = width;
		this.height = height;
		this.button_color = button_color;
		this.stroke_color = stroke_color;
		this.stroke = stroke;
		this.txt = txt;
		this.g = g;

	}

	public Rectangle(Point p, int width, int height, Color button_color, Color stroke_color, int stroke, String txt, Graphics g)
	{
		this(p.x, p.y, width, height, button_color, stroke_color, stroke, txt, g);
	}

	public void paint(Graphics g)
	{
		g.setColor(stroke_color);
		g.fillRect(center.x - width/2 - stroke , center.y - height/2 - stroke, width + stroke*2, height + stroke*2);
		g.setColor(button_color);
		g.fillRect(center.x - width/2 , center.y - height/2, width, height);

		drawMidStr();
	}

	private void drawMidStr() {

		Font font = new Font(txt, 2, (height+width)/30);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(txt, center.x-( font.getSize() ) , center.y);

	}
}
