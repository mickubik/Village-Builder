/*
 * 
 * For Choices we need to add nextDialogue(Dialogue) for each choice.
 * Need to set up nextDialogue for choices.
 * Also at a later point could try and make more things private
 * 
 * 
 * For dialogue use setNextDialogueWithId(id[]])
 * This will create a new Dialogue with only an id
 * Therefore next step is initialzeDialogue(id[])
 * 		initializeDialogue could instead use addX addY...
 * In our class we have a variable id[] which keeps track of the current id
 * At each choice stage we increment id[] spot ie id[0]->id[1]
 * Then when a choice tree finishes we set id[currentSpot] = 0 and currentSpot--
 * Then continue with assignId(id[currentSpot]++)
 * 
 */
package general;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

class MainWindow extends JFrame {
	private int mainWindowWidth = 800;
	private int mainWindowHeight = 600;
	private int imageAreaWidth = 800;
	private int imageAreaHeight = 3*mainWindowHeight/4;
	private int choiceAreaWidth = 3*imageAreaWidth/4;
	private int choiceAreaHeight = imageAreaHeight/10;
	private Dimension textAreaMinSize = new Dimension(mainWindowWidth, mainWindowHeight/4);
	private Dimension choiceAreaMinSize = new Dimension(choiceAreaWidth, choiceAreaHeight);
	private Dimension imageAreaMinSize = new Dimension(imageAreaWidth, imageAreaHeight);
	private Dimension choiceSpacing = new Dimension(0, choiceAreaHeight/2);
	private static JTextArea textArea = new JTextArea("test");
	private JPanel gamePanel = new JPanel();
	private JPanel image = new JPanel();
	

	private MainWindow() {
		design();
	}
	
	private void design(){
		setTitle("Village Builder");
		setSize(mainWindowWidth, mainWindowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(0,0);	    
		setVisible(true);
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));    		    
		image.setMinimumSize(imageAreaMinSize);
		image.setMaximumSize(imageAreaMinSize);	
		image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));
		image.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		gamePanel.setBackground(Color.CYAN );	        
		gamePanel.add(image);
		gamePanel.add(createTextArea());
		
		setContentPane(gamePanel);
	}

	private JTextArea createChoiceArea(int yLoc, String text){
		JTextArea choiceArea = new JTextArea("choice");
		choiceArea.setMinimumSize(choiceAreaMinSize);
		choiceArea.setMaximumSize(choiceAreaMinSize);
		choiceArea.setEditable(false);
		choiceArea.setText(text);
		
		choiceArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		choiceArea.setLayout(new BoxLayout(choiceArea, BoxLayout.Y_AXIS));

		choiceArea.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				image.removeAll();
				setTextArea(Game.advanceDialogue().display());
				if (Game.getCurrentDialogue().hasChoices()){
					displayChoices(Game.getCurrentDialogue());
				}
			}
		});
		return choiceArea;		      
	}

	private JTextArea createTextArea(){
		textArea.setMinimumSize(textAreaMinSize);
		textArea.setMaximumSize(textAreaMinSize);
		textArea.setEditable(false);
		textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
		textArea.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		textArea.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){				
				if (Game.getCurrentDialogue().hasChoices()){
					return;
				}
				setTextArea(Game.advanceDialogue().display());
				System.out.println(Game.getCurrentDialogue().display());
				System.out.println("hasChoices: " + Game.getCurrentDialogue().hasChoices());
				if (Game.getCurrentDialogue().hasChoices()){
					displayChoices(Game.getCurrentDialogue());
				}
			}
		});
		return textArea;		      
	}
	
	public static void setTextArea(String newText){
		textArea.setText(newText);
	}

	public void displayChoices(Dialogue dialogueWithChoices){
		int numberOfChoices = dialogueWithChoices.numberOfChoices();
		if (numberOfChoices % 2 == 0){
			int startPoint = imageAreaHeight/2  - choiceAreaHeight/2 * numberOfChoices;
			int currentPoint = startPoint;
			Choice currentChoice = dialogueWithChoices.firstChoice;
			System.out.println("currentChoice.text: " + currentChoice);
			image.add(Box.createRigidArea(choiceSpacing));
			image.add(createChoiceArea(currentPoint, currentChoice.text));
			while (currentChoice.nextChoice != null){
				currentChoice = currentChoice.nextChoice;
				image.add(Box.createRigidArea(choiceSpacing));
				image.add(createChoiceArea(currentPoint, currentChoice.text));
				
			}

//			for (int i=0; i<numberOfChoices; i++){
//				image.add(Box.createRigidArea(choiceSpacing));
//				image.add(createChoiceArea(currentPoint, dialogueWithChoices.getChoices()[i]));				
//			}
		}
	}

	public static void main(String[] args) {
		JFrame mainWindow = new MainWindow();
		Game.initializeGameDialogue();
		setTextArea(Game.getCurrentDialogue().display());
	}
}
