/* Main class
 * actual game frame
 * game will start as soon as frame is initialized
 * Timers are modified to work for ball and paddle at different time intervals
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.Timer;


public class MainGUI extends JFrame implements KeyListener{
	 protected Paddle paddle;
	 protected Ball ball;
	 private final HashSet<Integer> pressed = new HashSet<Integer>(); //to account for user holding down movement keys
	 private Timer ballTimer; 
	 private boolean isRight; 
	 protected int yVelocity; 
	 protected boolean rightOrLeft = true; 
	 protected int bugHandler; //to handle errors
	 protected int scoreCounter = 0; 
	 protected int toWinTime; 
	 protected int lives; 
	 protected int level; 	 
	 public int[] wins = new int[5]; 
	 public int[] losses = new int[5];	 
	 protected Color ballColor; 
	 protected Color backgroundColor; 
	 protected int paddleWidth; 
	 protected int ballSpeed; 
	 public int timeCounter;	 
	 protected boolean gameOn = true; 
	
	public MainGUI(int level, int wins[], int losses[]) {		
		this.wins = wins;
		this.losses = losses;		
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false); 		
		this.level = level;
		if (level == 1) { 
			this.setTitle("Level 1");
			lives = 4;
			toWinTime = 30;
			backgroundColor = Color.LIGHT_GRAY;
			paddleWidth = 80;
			ballColor = Color.ORANGE;
			ballSpeed = 10; 
		}else if (level == 2) {
			this.setTitle("Level 2");
			lives = 3;
			toWinTime = 35;
			backgroundColor = Color.DARK_GRAY;
			paddleWidth = 60;
			ballColor = Color.RED;
			ballSpeed = 15; 
		}else if (level == 3) {
			lives = 3;
			this.setTitle("Level 3");
			toWinTime = 40;
			backgroundColor = Color.CYAN;
			paddleWidth = 50;
			ballColor = Color.YELLOW;
			ballSpeed = 17; 
		}else if (level == 4) {
			lives = 2;
			this.setTitle("Level 4");
			toWinTime = 45;
			backgroundColor = Color.DARK_GRAY;
			paddleWidth = 40;
			ballColor = Color.GREEN;
			ballSpeed = 20; 
		}else if (level == 5) {
			lives = 2;
			this.setTitle("Level 5");
			toWinTime = 50;
			backgroundColor = Color.WHITE;
			paddleWidth = 30;
			ballColor = Color.BLUE;
			ballSpeed = 23; 
		}
		ball = new Ball(ballColor, 300, 20, ballSpeed); //creates new ball
		this.add(ball);
		
		paddle = new Paddle(paddleWidth, Color.BLACK); //creates new paddle
		this.add(paddle);
		addKeyListener(this); //listens for left and right keys 
	    this.setFocusable(true);
	    
	    ballTimer = new Timer(100, new TimerCallback());
		ballTimer.start();
	    
	    new Timer(5, new ActionListener(){ //timer for paddle
            public void actionPerformed(ActionEvent e){
                if(pressed.contains(KeyEvent.VK_RIGHT)) { //right key held down
                		isRight = true;
                		redrawPaddle(isRight);
                } else if(pressed.contains(KeyEvent.VK_LEFT)) { //left key held down
                		isRight = false;
                		redrawPaddle(isRight);
                }
            }
        }).start();
	    
	    new Timer(1000, new ActionListener(){ //count-down timer
            public void actionPerformed(ActionEvent e){
                timeCounter += 1;
            }
        }).start(); 
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		String keyOutput = Character.toString(e.getKeyChar());
		if (keyOutput.equals("e") && gameOn == false) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //closes itself when e button is clicked
		}
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		pressed.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) { 
		pressed.remove(e.getKeyCode());
	}
	public void redrawPaddle(boolean isRight) { 
		Graphics g = this.getGraphics();
		g.setColor(paddle.color);
		if (gameOn) {
			if (isRight) {
				paddle.setX(paddle.getX()+1);
				g.fillRect(paddle.getX(), 700, paddle.getPaddleWidth(), 10);
			}else {
				paddle.setX(paddle.getX()-1);
				g.fillRect(paddle.getX(), 700, paddle.getPaddleWidth(), 10);
			}
		}
	}
	protected class TimerCallback implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {			
			if (gameOn) {
				drawBackground();				
				if (timeCounter == toWinTime) {
					levelWon();
				}				
				bugHandler += 1; //handles bugs
				if (ball.getStartY() >= 690 && bugHandler >= 7 && ball.getStartX()+10 >= paddle.getX() && ball.getStartX()+5 <= paddle.getX()+paddle.getPaddleWidth()) {
					bugHandler = 0; //bugs
					scoreCounter += 1;
					yVelocity = yVelocity*-1; //invert y-velocity when it hits the paddle
				}
				if (ball.getStartY() >= 750) {
					lives -=1; 
					if (lives <= 0) {
						gameOver(); 
					}else {
						yVelocity = 0;
						ball.setStartX(400);
						ball.setStartY(50);
					}
				}	
				yVelocity += 1; 				
				if (ball.getStartX() <= 0) { //for x-component of velocity
					rightOrLeft = true;					
				}else if(ball.getStartX() >= 990) {
					rightOrLeft = false;
				}				
				if (rightOrLeft) {
					ball.setStartX(ball.getStartX()+ball.XVelocity);
					ball.setStartY(ball.getStartY()+yVelocity);
				}else{
					ball.setStartX(ball.getStartX()-ball.XVelocity);
					ball.setStartY(ball.getStartY()+yVelocity);
				}
			}
		}
	}
	public void drawBackground() { //handles all graphics
		try {
			Graphics g = this.getGraphics();
			g.setColor(this.backgroundColor);
			g.fillRect(0, 0, 1000, 800);
			g.setColor(Color.BLACK);
			g.fillRect(paddle.getX()-5, 700, paddle.getPaddleWidth()+10, 10);
			
			g.setColor(ball.color);
			g.fillOval(ball.getStartX(),ball.getStartY(),10,10);
			
			g.setColor(this.backgroundColor);
			g.fillRect(0, 710, 1000, 90);
			
			g.setColor(Color.BLACK);
			g.drawString("Score: " + scoreCounter, 25, 60);
			g.setColor(Color.BLACK);
			g.drawString("Time Left: " + (toWinTime-timeCounter) , 100, 60);
			
			g.setColor(Color.BLACK);
			g.drawString("Lives Left: " + lives , 210, 60);
		}catch(NullPointerException e){
			
		}
	}
	public void gameOver() { 
		try {
			gameOn = false;
			losses[this.level - 1] += 1; 
			Graphics g = this.getGraphics();
			g.setColor(Color.RED);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("GAME OVER" , 420, 300); 
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("Press 'E' key exit to level select" , 420, 340);
			ballTimer.stop(); 			
			try { //rewrites the new values in out.txt 
			    FileWriter fstream = new FileWriter("out.txt");
			        BufferedWriter out = new BufferedWriter(fstream);
			    out.write(wins[0] + "," + wins[1] + "," + wins[2] + "," +wins[3] + "," + wins[4] + "," + losses[0]+ "," + losses[1]+ "," + losses[2]+ "," + losses[3]+ "," + losses[4]);
			    out.close();
			    }catch (Exception e){
			 
			    }
			
		}catch (NullPointerException e) {
			
		}
	}
	public void levelWon() {
		try {
			gameOn = false; 
			wins[this.level - 1] += 1; 
			Graphics g = this.getGraphics();
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("You Won!" , 420, 300);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("Press 'E' key to exit to level select" , 420, 340);
			ballTimer.stop(); 			
			try { //rewrites the new values in out.txt 			
			    FileWriter fstream = new FileWriter("out.txt");
			        BufferedWriter out = new BufferedWriter(fstream);
			    out.write(wins[0] + "," + wins[1] + "," + wins[2] + "," +wins[3] + "," + wins[4] + "," + losses[0]+ "," + losses[1]+ "," + losses[2]+ "," + losses[3]+ "," + losses[4]);
			    out.close();
			    }catch (Exception e){
			      System.err.println("Error: " + e.getMessage());
			    }			
		}catch (NullPointerException e) {
			
		}
	}
}

