import java.awt.Color;
import java.awt.Point;

public class ColoredPoint {
	private Point pointCoord;
	private Color pointColor;
	
	public ColoredPoint(Point pointCoord, Color pointColor) {
		this.pointCoord = pointCoord;
		this.pointColor= pointColor;
	}
	
	public Point getPointCoord() {
		return pointCoord;
	}
	public double getXCoord() {
		return pointCoord.getX();
	}
	public double getYCoord() {
		return pointCoord.getY();
	}
	public Color getPointColor() {
		return pointColor;
	}
//	public ColoredPoint getColoredPoint() {
		
//	}
	
	public void setPointCoord(Point pointCoord) {
		this.pointCoord = pointCoord;		
	}
	public void setPointColor(Color pointColor) {
		this.pointColor = pointColor;
	}
	
	public String toString() {
		return "Coordinates of point: " + getPointCoord() + "\nColor of point: " + getPointColor();
	}
}
