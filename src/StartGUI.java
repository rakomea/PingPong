/* Has a play/start button
 * passes arrays for wins and losses from Main class to FirstGUI class
 * This frame updates FirstGUI class after the user wins or losses 
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGUI extends JPanel implements ActionListener{
	
	private int[] Wins; //passed wins array
	private int[] Losses; //passed losses array
	protected JFrame Frame;
	
	public StartGUI(int allWins[], int allLosses[]){
		this.Wins = allWins; 
		this.Losses = allLosses;
		
		JPanel topPanel = new JPanel(); 
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
		add(topPanel, BorderLayout.NORTH);
		
		JPanel buttonRow = new JPanel(); 
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));
		topPanel.add(buttonRow);
		
		
		JButton startButton = new JButton("Play Lob Pong!"); 
		startButton.addActionListener(this);
		buttonRow.add(startButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		String cmd = e.getActionCommand();
		if (cmd.equals("Play Lob Pong!")) {
			
			Frame = new FirstGUI(this.Wins, this.Losses);
			Frame.setVisible(true);
		}
	}
}

