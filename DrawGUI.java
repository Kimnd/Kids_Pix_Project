

//Project by: Ryan Lim,	Ethan <INSERT NAME HERE>
//, and Marion Fiona Gallagher.
//A drawing program that lets the user draw in three colors,
//erase things, and clear the screen. Exciting things, all!
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class DrawGUI extends JPanel{

private JRadioButton pen_1, pen_2, pen_3, eraser;
private JButton clearButton;
private static boolean canDraw;
private final static int DIAMETER = 10;
private ArrayList<Point> myPoints;
public DrawGUI() {
	
	//super("Welcome to Drawing!");
	//setSize(600, 600);
	
	//Container canvas = getContentPane();
	setBackground(Color.pink);
	myPoints = new ArrayList<Point>();

	
	//JPanel mainPanel = new JPanel();
	//mainPanel.setBackground(Color.GRAY);
	//canvas.setLayout(new BorderLayout());
	//canvas.add(mainPanel, BorderLayout.PAGE_END);
	
	JPanel drawPanel = new JPanel();
	ButtonGroup myGroup = new ButtonGroup();
	
	pen_1 = new JRadioButton("Color option 1!");
	drawPanel.add(pen_1);
	pen_1.addActionListener(new ToolListener());
	pen_2 = new JRadioButton("Color option 2!");
	drawPanel.add(pen_2);
	pen_2.addActionListener(new ToolListener());
	pen_3 = new JRadioButton("Color option 3!");
	drawPanel.add(pen_3);
	pen_3.addActionListener(new ToolListener());
	
	myGroup.add(pen_1);
	myGroup.add(pen_2);
	myGroup.add(pen_3);
	

	eraser = new JRadioButton("Eraser");
	drawPanel.add(eraser);
	eraser.addActionListener(new ToolListener());
	clearButton = new JButton("Clear Drawing");
	drawPanel.add(clearButton);
	clearButton.addActionListener(new ToolListener());
	
	this.add(drawPanel);
	
	this.addMouseMotionListener(new CanvasListener());
	this.addMouseListener(new CanvasListener());
	canDraw = false;
}

//getters and setters for canIDraw, the variable that specifies whether or not a user can draw on the canvas
public static boolean getCanDraw () {
	return canDraw;
}

public static void setCanDraw (boolean canIDraw) {
	canDraw = canIDraw;
}

@Override
public void paintComponent(Graphics pen) {
	super.paintComponent(pen);

		for(Point point : myPoints) {
			int x = (int) point.getX();
			int y = (int) point.getY();
			
				pen.setColor(Color.blue);
				pen.fillOval(x, y, DIAMETER, DIAMETER);
			
			
	}
	
}
private class CanvasListener extends MouseAdapter {
	@Override
	public void mouseClicked (MouseEvent event){
		if (canDraw == true) {
			canDraw = false;
			
		} else if (canDraw == false) {
			canDraw = true;
			repaint();;
		}
		System.out.println(canDraw);
		
		
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		 
			if(canDraw)
			{
				Point point = event.getPoint();
				myPoints.add(point);
			}
			
			repaint();
	}
}



private class ToolListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	
}

public static void main(String args[]) {
	System.out.println("running");
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			JFrame frame = new JFrame("Welcome to Drawing!");
			frame.setSize(600, 600);
			// create an object of your class
			DrawGUI panel = new DrawGUI();
			frame.getContentPane().add(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
	});
	
}
}