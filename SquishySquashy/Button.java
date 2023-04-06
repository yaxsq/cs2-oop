import java.awt.Image;

public class Button 
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
//	protected Image image_depressed;
	protected Image squished;
	protected Image current_image;
	protected boolean pressed;
	
	public Button(int x, int y, int width, int height, Image i_depressed, Image i_pressed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
//		image_depressed = i_depressed;
		squished = i_pressed;
		current_image = i_depressed;
	}

	public Button(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	public Image GetImage() 
	{
		return current_image;
	}
	
	public Boolean IsPressed()
	{
		return pressed;
	}
	
	public void SetPressed(boolean pressed)
	{
		this.pressed = pressed;
	}
	
	public boolean IsClicked(int x, int y)
	{
		if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
		{
			pressed = true;
			current_image = squished;
			return true;
		}
		return false;
	}
}
