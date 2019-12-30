/* FirstGUI class which contains level selection as well as info on previous games played for each level
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstGUI extends JFrame implements ActionListener{
	protected JFrame Frame;
	public int[] Wins; //gets wins and losses arrays from BasicStartGUI
	public int[] Losses;
	
	//labels which display number of wins and losses for each level
	protected JLabel wins1; 
	protected JLabel wins2;
	protected JLabel wins3;
	protected JLabel wins4;
	protected JLabel wins5;
	
	protected JLabel losses1; 
	protected JLabel losses2; 
	protected JLabel losses3; 
	protected JLabel losses4;
	protected JLabel losses5;	
	public FirstGUI(int allWins[], int allLosses[]) {		
		this.setTitle("Play Lob Pong");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		this.Wins = allWins; 
		this.Losses = allLosses;		
		JPanel topPanel = new JPanel(); 
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
		add(topPanel, BorderLayout.NORTH);
		
		JPanel buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
			
		JLabel commandLabel2 = new JLabel("Select Difficulty Level");
		buttonRow.add(commandLabel2);
		
		buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
		
		//These buttons+labels display all the wins/losses for all other levels
		
		JButton level1 = new JButton("Level 1"); 
		level1.addActionListener(this);
		buttonRow.add(level1);
		
		
		wins1 = new JLabel("Wins: " + Wins[0] + "     "); 
		buttonRow.add(wins1);
		
		losses1 = new JLabel("Losses: " + Losses[0] + "     "); 
		buttonRow.add(losses1);
		
		buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
		
		
		JButton level2 = new JButton("Level 2");
		level2.addActionListener(this);
		buttonRow.add(level2);
		
		wins2 = new JLabel("Wins: " + Wins[1] + "     ");
		buttonRow.add(wins2);
		
		losses2 = new JLabel("Losses: " + Losses[1] + "     ");
		buttonRow.add(losses2);
		
		buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);

		
		JButton level3 = new JButton("Level 3");
		level3.addActionListener(this);
		buttonRow.add(level3);
		
		wins3 = new JLabel("Wins: " + Wins[2] + "     ");
		buttonRow.add(wins3);
		
		losses3 = new JLabel("Losses: " + Losses[2] + "     ");
		buttonRow.add(losses3);
		
		buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
		
		
		JButton level4 = new JButton("Level 4");
		level4.addActionListener(this);
		buttonRow.add(level4);
		
		wins4 = new JLabel("Wins: " + Wins[3] + "     ");
		buttonRow.add(wins4);
		
		losses4 = new JLabel("Losses: " + Losses[3] + "     ");
		buttonRow.add(losses4);
		
		buttonRow = new JPanel();
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
		
		
		JButton level5 = new JButton("Level 5");
		level5.addActionListener(this);
		buttonRow.add(level5);
		
		wins5 = new JLabel("Wins: " + Wins[4] + "     ");
		buttonRow.add(wins5);
		
		losses5 = new JLabel("Losses: " + Losses[4] + "     ");
		buttonRow.add(losses5);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		
		String cmd = e.getActionCommand();
		//closes itself when level button is clicked
		if (cmd.equals("Level 1")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); 
			Frame = new MainGUI(1, this.Wins, this.Losses);
		}else if (cmd.equals("Level 2")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); 
			Frame = new MainGUI(2, this.Wins, this.Losses);
		}else if (cmd.equals("Level 3")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); 
			Frame = new MainGUI(3, this.Wins, this.Losses);
		}else if (cmd.equals("Level 4")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Frame = new MainGUI(4, this.Wins, this.Losses); 
		}else if (cmd.equals("Level 5")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Frame = new MainGUI(5, this.Wins, this.Losses);
		}
		Frame.setVisible(true);
	}
}

