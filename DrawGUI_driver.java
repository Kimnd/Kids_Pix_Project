import java.awt.EventQueue;
import javax.swing.JFrame;

public class DrawGUI_driver {
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//make a JFrame object to hold the DrawGUI and set its size and what-not
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
