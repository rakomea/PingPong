/* This class stores values related to the ball
 */

import java.awt.Color;
import javax.swing.JComponent;
public class Ball extends JComponent{

	public Color color;
	private int startX;
	private int startY;
	public int XVelocity;
	
	public Ball(Color color, int startX, int startY, int initialXVelocity) {
		this.color = color;
		this.startX = startX;
		this.startY = startY;
		this.XVelocity = initialXVelocity;
	}
	public int getStartX() {
		return this.startX;
	}
	public int getStartY() {
		return this.startY;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int determineYVelocity() {
		return 10;
	}
}
