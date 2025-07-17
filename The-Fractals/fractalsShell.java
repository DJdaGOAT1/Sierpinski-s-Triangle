// package fractals;

//Name of the File: fractalsShell.java
//Name: Devansh Joshi
//Purpose of this file: This file uses the concepts of recursion as a means to apply to a digital canvas in order to create new shapes


import java.awt.*;
public class fractalsShell {
	public static void main(String[] args) {
		//This is the code used to create the image you see on the panel.
		
		DrawingPanel panel = new DrawingPanel(600,600); //The Canvas
		panel.setBackground(Color.BLACK);//Setting background color of the drawing panel to Black
		Graphics g = panel.getGraphics();//Create the paintbrush
		
		//Everything else in main is for adding aspects to the canvas
		
		g.setColor(Color.ORANGE); //Setting the paintbrush to the color red
		g.drawString("Sierpinski Triangle", 20, 50); //create a RED string onto the canvas
		
		//Now I need to assign the points of my equilateral triangle.
		Point bottomLeft = new Point(50, 250);
		Point topMiddle = new Point(150, 50);
		Point bottomRight = new Point(250, 250);
		int level = 6;
		drawTriangle(g, bottomLeft, topMiddle, bottomRight);
		recur(level, g, bottomLeft, topMiddle, bottomRight);
		
		g.setColor(Color.ORANGE); //Setting the paintbrush to the color red
		g.drawString("Cantor Set", 20, 350); //create a RED string onto the canvas
		
        int cantorStartX = 50; // Adjust the starting X-coordinate
        int cantorEndX = 250;  // Adjust the ending X-coordinate
        int cantorY = 365;     // Adjust the Y-coordinate
        int cantorLevel = 6;   // Adjust the level as needed
        cantorSet(cantorLevel, g, new Point(cantorStartX, cantorY), new Point(cantorEndX, cantorY));

		
		

		
	}

	public static void drawTriangle(Graphics g, Point p1, Point p2, Point p3)
	{
		/* 
			Since the Graphics class doesn't contain a drawTriangle method,
			I decided to create my own using the Polygon class, which will
			basically trace the shape starting from the first point to the
			next point and so on and so on. It will then close the shape by
			tracing from the last point to the first point.
		*/

		//If you want to create a custom shape, edit this function to fit the
			//your design.
		Polygon p = new Polygon();
		p.addPoint(p1.x, p1.y);
		p.addPoint(p2.x, p2.y);
		p.addPoint(p3.x, p3.y);
		g.drawPolygon(p);
		//or you can use:   g.fillPolygon(p);
		
	}
	
	public static Point midpoint(Point p1, Point p2)
	{
		//This method should give us the midpoint of 2 coordinates
		  	//Use this site as a guide: https://www.mathsisfun.com/algebra/line-midpoint.html
		
		Point mid = new Point();
		mid.x = (p1.x + p2.x) / 2; // mid.x is the x-coordinate of the median which is the combined x-values of the points, p1 and p2, divided by 2;
		mid.y = (p1.y + p2.y) / 2; // mid.y is the y-coordinate of the median which is the combined y-values of the points, p1 and p2, divided by 2;
		
		return mid;
	}

	public static void recur(int level, Graphics g, Point p1, Point p2, Point p3)
	{
		if(level < 1) return;
		else {
			//finding midpoint for each of the three sides of a triangle
			Point mid1to2 = midpoint(p1, p2);
			Point mid2to3 = midpoint(p2, p3);
			Point mid1to3 = midpoint(p1, p3);
			
			
			g.setColor(Color.GREEN);//Setting the paintbrush to the color blue
			drawTriangle(g, mid1to2, mid2to3, p2); // drawing the top-middle triangle
			drawTriangle(g, mid1to2, mid1to3, p1); // drawing the bottom-left triangle
			drawTriangle(g, mid1to3, mid2to3, p3); // drawing the bottom-right triangle
			
			

			
			recur(level - 1, g, mid1to2, mid2to3, p2); // calling the method itself to access the top-middle triangle created
			recur(level - 1, g, mid1to2, mid1to3, p1); // calling the method itself to access the bottom-left triangle created
			recur(level - 1, g, mid1to3, mid2to3, p3); // calling the method itself to access the bottom-right triangle created
			
		}
	}
	
	public static void cantorSet(int level, Graphics g, Point p1, Point p2) {
		if(level < 1) return;
		else {
			int length = p2.x - p1.x; // finding the length (essentially the distance between the x-coordinates) from p1 to p2
			g.setColor(Color.WHITE); //Setting paintbrush to color white
			g.drawLine(p1.x, p1.y, p2.x, p2.y); // draw the central line segment
			int space = length / 3; // the gap size is ⅓ the size of the length of the central line segment
			
			// Calculate starting points for both line segments
			Point leftStart = new Point(p1.x, p1.y + 20); 
			Point rightStart = new Point(p1.x + 2 * space, p2.y + 20); 

			// Calculate ending points for both line segments
			Point leftEnd = new Point(p1.x + space, p2.y + 20); 
			Point rightEnd = new Point(p2.x, p1.y + 20); 

			cantorSet(level - 1, g, leftStart, leftEnd); // drawing left line segments recursively
			cantorSet(level - 1, g, rightStart, rightEnd); // drawing right line segments recursively
			
		}
	}
}