package manager;
import java.awt.*;  
import javax.swing.JFrame;

import drawing.DrawingCanvas;  
  
public class DrawingManager {  
      
	    
        public static void main(String[] args) 
        {  
	        DrawingCanvas canvas=new DrawingCanvas();  
	        JFrame frame=new JFrame();  
	        frame.add(canvas);  
	        frame.setSize(800,600);  
	        //f.setLayout(null);  
	        frame.setVisible(true);
			frame.setTitle("Shazain");

	    }  
  
}  