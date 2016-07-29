/*Project by: Ryan Lim,	Kyaw Tun, and Marion Fiona Gallagher.
A drawing program that lets the user draw in three colors,
erase things, and clear the screen. Exciting things, all!*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class DrawGUI extends JPanel {
//Initializing the buttons for the program
	private JRadioButton pen_1, pen_2, pen_3, pen_4, pen_5, eraser;
	private JButton clearButton;
//Initializing the color variable the program will use for the background and changing the color of the pens
	private static Color currentColor = Color.PINK;
	private static Color randomColor = Color.CYAN;
	private static boolean isRandomSelected = false;
//Defining the colors that the pens can be
	private final static Color PURPLE = new Color(204, 0, 204);
	private final static Color FOREST = new Color(0, 153, 0);
	private final static Color SUNNYD = new Color(255, 179, 0);
//Defining the diameter of the pen
	private final static int DIAMETER = 12;
//Initializing the boolean to tell us if the user can draw or not
	protected static boolean canDraw;
//Initializing the ArrayList of ColoredPoint objects that will allow the program to keep track of where and what color the pens were 
	private ArrayList<ColoredPoint> myPoints;

	public DrawGUI() {
		setBackground(Color.PINK);
		myPoints = new ArrayList<ColoredPoint>();
		
		JPanel drawPanel = new JPanel();
		ButtonGroup myGroup = new ButtonGroup();
		//Making the pen objects, and giving them listeners and text
		pen_1 = new JRadioButton("Purple");
		drawPanel.add(pen_1);
		pen_1.addActionListener(new ToolListener());
		pen_2 = new JRadioButton("Forest Green");
		drawPanel.add(pen_2);
		pen_2.addActionListener(new ToolListener());
		pen_3 = new JRadioButton("Sunny-D");
		drawPanel.add(pen_3);
		pen_3.addActionListener(new ToolListener());
		pen_4 = new JRadioButton("A random color");
		drawPanel.add(pen_4);
		pen_4.addActionListener(new ToolListener());
		pen_5 = new JRadioButton("???");
		drawPanel.add(pen_5);
		pen_5.addActionListener(new ToolListener());
		//The eraser isn't functionally different from the other pens--it's just the same color as the background
		eraser = new JRadioButton("Eraser");
		drawPanel.add(eraser);
		eraser.addActionListener(new ToolListener());
		//Making the button to clear the screen of dots
		clearButton = new JButton("Clear Drawing");
		drawPanel.add(clearButton);
		clearButton.addActionListener(new ToolListener());
		//Adding all the pens (and the eraser) to a ButtonGroup so that the user can't select more than one pen
		myGroup.add(pen_1);
		myGroup.add(pen_2);
		myGroup.add(pen_3);
		myGroup.add(pen_4);
		myGroup.add(pen_5);
		myGroup.add(eraser);
		
		this.add(drawPanel);

		this.addMouseMotionListener(new CanvasListener());
		this.addMouseListener(new CanvasListener());

	}
	
	//getter to retrive what color of pen the user has selected
	public static Color getCurrentColor() {
		if (isRandomSelected == true) {
			currentColor = getRandomColor();
		} return currentColor;
	}
	//getter to retrieve a random color
	public static Color getRandomColor() {
		Random generator = new Random();
		int redInt = 0;
		int greenInt = 0;
		int blueInt = 0;
		
		redInt = generator.nextInt(0 + 255) + 0;
		greenInt = generator.nextInt(0 + 255) + 0;
		blueInt = generator.nextInt(0 + 255) + 0;
		
		randomColor = new Color(redInt, greenInt, blueInt);
		
		return randomColor;
	}

	//method to draw the ovals where and with what color myPoints says to
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		for(ColoredPoint point : myPoints) {
			int x = (int) point.getXCoord();
			int y = (int) point.getYCoord();
			//setColor must be inside the for loop because otherwise when the user switches the pen everything will turn the same color
			pen.setColor(point.getPointColor());
			pen.fillOval(x, y, DIAMETER, DIAMETER);
		}
	}
	//class to listen to the canvas in terms of the mouse
	private class CanvasListener extends MouseAdapter {
		@Override
		//this method tracks when the mouse is clicked, and to switch canIDraw on and off accordingly
		public void mouseClicked (MouseEvent event){
			if (canDraw == true) {
				canDraw = false;
			} else if (canDraw == false) {
				canDraw = true;
			}
		}
		//this method tracks when the mouse moves, and when it does, add the point it moves over (and the current color) to myPoints
		@Override
		public void mouseMoved(MouseEvent event) {
			if(canDraw) {
				ColoredPoint point = new ColoredPoint(event.getPoint(), getCurrentColor());
				myPoints.add(point);
			}
			repaint();
		}
	}
	//the class to tell the program what to do if the user hits any of the buttons
	private class ToolListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//the logic that tells the program what to do when the user interacts with stuff
			if (pen_1.isSelected()) {
				currentColor = PURPLE;
			} else if (pen_2.isSelected()) {
				currentColor = FOREST;
			} else if (pen_3.isSelected()) {
				currentColor = SUNNYD;
			} else if (pen_4.isSelected()) {
				currentColor = getRandomColor();
			} else if (eraser.isSelected()) {
				currentColor = Color.PINK;
			} //because it's a special snowflake, the fifth pen gets some logic of its own
			if (pen_5.isSelected()) {
				isRandomSelected = true;
			} else {
				isRandomSelected = false;
			}
			//this is seperate because clearing the canvas isn't related which other pens are selected
			if (arg0.getSource() == clearButton) {
				myPoints.clear();
			}
		}
	}	
}
