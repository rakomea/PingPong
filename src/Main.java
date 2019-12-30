/* Name: Richard Owusu Akomea 
  NetID: rakomea 
  Project 4
  Lab section: Monday/Wednesday 12:30 - 1:45
  I did not collaborate with anyone on this assignment.
  Main class
 Game is run from this class
 Initializes the main JFrame and adds the main StartGUI to it
 Gets stored values from out.txt file, which stores win and loss values for each level
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	//array which stores wins and losses values from out.txt
	protected static int wins[] = new int[5]; 
	protected static int losses[] = new int[5]; 
	
	public static void main(String[] args) {
				
		//accessing file which stores wins and losses below
		 String fileName = "out.txt"; //used by fileReader
		   String line = null;//default line value

	       try {
	           // FileReader reads text files and is wrapped in BufferedReader.
	           FileReader fileReader = new FileReader(fileName);
	           BufferedReader bufferedReader = new BufferedReader(fileReader);

	           while((line = bufferedReader.readLine()) != null) { 
	        	   	   String Lines = line; 
	        	   	   String comma = "[,]"; 
	        	   	   String[] tokens = Lines.split(comma); //split Lines by commas
	        	   	   for (int i = 0; i < 5; i++) { //fill wins and losses with corresponding values from tokens array
	        	   	   wins[i] = Integer.parseInt(tokens[i]);
	        	   	   losses[i] = Integer.parseInt(tokens[i+5]);
	        	   	   }
	           }   
	           bufferedReader.close();         
	       }
	       catch(FileNotFoundException ex) { 
	           System.out.println( 
	               "Unable to open file '" + 
	               fileName + "'");                
	       }
	       catch(IOException ex) { 
	           System.out.println(
	               "Error reading file '" 
	               + fileName + "'");                  
	       }
	   	JFrame frame = new JFrame();
		frame.setTitle("Project 4");
		frame.setSize(200, 200);
		frame.setResizable(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartGUI startgui = new StartGUI(wins, losses);
		frame.add(startgui);
		frame.setVisible(true);
	  }
	}