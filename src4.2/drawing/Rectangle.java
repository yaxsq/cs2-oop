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
	
	public Rectangle()
	{
		
	}
	
	public Rectangle(int x, int y, int width, int height, Color button_color, Color stroke_color, int stroke)
	{
		center = new Point(x, y);
		this.width = width;
		this.height = height;
		this.button_color = button_color;
		this.stroke_color = stroke_color;
		this.stroke = stroke;
	}
	
	public Rectangle(Point p, int width, int height, Color button_color, Color stroke_color, int stroke)
	{
		this(p.x, p.y, width, height, button_color, stroke_color, stroke);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(stroke_color);
		g.fillRect(center.x - width/2 - stroke , center.y - height/2 - stroke, width + stroke*2, height + stroke*2);
		g.setColor(button_color);
		g.fillRect(center.x - width/2 , center.y - height/2, width, height); 
	}
}
