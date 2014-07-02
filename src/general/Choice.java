package general;

public class Choice {
	String text;
	private Dialogue nextDialogue = null;
	public Choice nextChoice = null;
	
	public Choice(String text) {
		this.text = text;
	}	
	public void setNextChoice(Choice nextChoice) {
		this.nextChoice = nextChoice;
	}	
	public void setNextDialogue(Dialogue nextDialogue) {
		this.nextDialogue = nextDialogue;
	}
	public Dialogue setAndGetNextDialogue(Dialogue nextDialogue) {
		this.nextDialogue = nextDialogue;
		return nextDialogue;
	}
	public Choice getNextChoice() {
		return nextChoice;
	}	
}

