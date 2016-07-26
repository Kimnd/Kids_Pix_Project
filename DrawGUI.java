package Project6;

//Project by: Ryan Lim,	Ethan <INSERT NAME HERE>
//, and Marion Fiona Gallagher.
//A drawing program that lets the user draw in three colors,
//erase things, and clear the screen. Exciting things, all!
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class DrawGUI extends JFrame{

private JRadioButton pen_1, pen_2, pen_3, eraser;
private JButton clearButton;
private static boolean canDraw;

public DrawGUI() {
	super("Welcome to Drawing!");
	setSize(600, 600);
	
	Container canvas = getContentPane();
	canvas.setBackground(Color.PINK);
	canvas.addMouseListener(new CanvasListener());
	
	JPanel mainPanel = new JPanel();
	mainPanel.setBackground(Color.GRAY);
	canvas.setLayout(new BorderLayout());
	canvas.add(mainPanel, BorderLayout.PAGE_END);
	
	JPanel drawPanel = new JPanel();
	pen_1 = new JRadioButton("Color option 1!");
	drawPanel.add(pen_1);
	pen_1.addActionListener(new ToolListener());
	pen_2 = new JRadioButton("Color option 2!");
	drawPanel.add(pen_2);
	pen_2.addActionListener(new ToolListener());
	pen_3 = new JRadioButton("Color option 3!");
	drawPanel.add(pen_3);
	pen_3.addActionListener(new ToolListener());
	eraser = new JRadioButton("Eraser");
	drawPanel.add(eraser);
	eraser.addActionListener(new ToolListener());
	clearButton = new JButton("Clear Drawing");
	drawPanel.add(clearButton);
	clearButton.addActionListener(new ToolListener());
	
	mainPanel.add(drawPanel);
	canDraw = false;
}

//getters and setters for canIDraw, the variable that specifies whether or not a user can draw on the canvas
public static boolean getCanDraw () {
	return canDraw;
}

public static void setCanDraw (boolean canIDraw) {
	canDraw = canIDraw;
}


private class CanvasListener extends MouseAdapter {
	@Override
	public void mouseClicked (MouseEvent event){
		if (canDraw == true) {
			canDraw = false;
		} else if (canDraw == false) {
			canDraw = true;
		}
		System.out.println(canDraw);
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
			DrawGUI frame = new DrawGUI();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
	});
	
}
}