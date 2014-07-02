package general;

public class Dialogue {
	private String text;
	public Choice firstChoice;
	public Choice currentChoice;
	private Dialogue nextDialogue = null;
	private int id[];
	
	
	public Dialogue(int id[]) {
		this.id = id;
	}
	public void addText(String text) {
		this.text = text;
	}
	public void addChoice(Choice choice) {
		if (firstChoice==null){
			firstChoice = choice;
			currentChoice = choice;
		}else{
			currentChoice.setNextChoice(choice);
			currentChoice = choice;
		}
	}
	public String display () {
		return text;
	}
	public Choice getCurrentChoice() {
		return currentChoice;
	}
	public boolean hasChoices() {
		return (firstChoice == null) ? false : true;
	}
	public int numberOfChoices() {
		if (firstChoice == null){
			return 0;
		}
		int count = 1;
		currentChoice = firstChoice;
		while (currentChoice.getNextChoice() != null){
			currentChoice = currentChoice.getNextChoice();
			count++;			
		}
		return count;
	}
	public void setNextDialogue(Dialogue nextDialogue) {
		this.nextDialogue = nextDialogue;
		return;
	}
	public Dialogue getNextDialogue() {
		return nextDialogue;
	}
	public Dialogue setAndGetNextDialogue(Dialogue nextDialogue) {
		this.nextDialogue = nextDialogue;
		return nextDialogue;
	}
//	public void setChoices(String[] choices) {
//		this.choices = choices;
//	}
}
