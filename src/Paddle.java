/* This class stores values related to the paddle
 */

import java.awt.Color;
import javax.swing.JComponent;

public class Paddle extends JComponent{
	
	private int paddleWidth;
	public Color color;
	
	public Paddle(int width, Color color) {
		this.paddleWidth = width;
		this.color = color;
	}
	
	public void setPaddleWidth(int width) {
		this.paddleWidth = width;
	}
	public int getPaddleWidth() {
		return this.paddleWidth;
	}
	
	private int xPos = 500-this.getPaddleWidth();
	
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	public int getX() {
		return this.xPos;
	}
}
