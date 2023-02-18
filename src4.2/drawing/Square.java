package drawing;

import java.awt.Color;

public class Square extends Rectangle {
	public Square(int x, int y, int side, Color button_color, Color stroke_color, int stroke)
	{
		super(x, y, side, side, button_color, stroke_color, stroke);
	}
	
	public Square(Point center, int side, Color button_color, Color stroke_color, int stroke)
	{
		super(center, side, side, button_color, stroke_color, stroke);
	}
}
