package general;

public class EventDialogue {
	private Dialogue[] gameDialogue;
	private Dialogue currentDialogue;
	private String eventName;  //TODO add to title bar
	private EventDialogue nextEventDialogue;

	public EventDialogue(String eventName, Dialogue[] gameDialogue) {
		this.gameDialogue = gameDialogue;
		this.eventName = eventName;
	}

	public Dialogue getCurrentDialogue(){
		if (currentDialogue==null){
			currentDialogue = gameDialogue[0];
		}
		return this.currentDialogue;	
	}
	public void setNextEventDialogue(EventDialogue nextEventDialogue) {
		this.nextEventDialogue = nextEventDialogue;
		return;
	}
	
	public Dialogue advanceDialogue(){
		System.out.println("currentDialogue.getNextDialogue(): " + currentDialogue.getNextDialogue());
		if (currentDialogue.getNextDialogue() == null){
			return null;
		}
		currentDialogue = currentDialogue.getNextDialogue();
		return currentDialogue;		
	}

	public EventDialogue getNextEvent() {
		return this.nextEventDialogue;
	}

	public Dialogue getNextDialogue() {
		return currentDialogue.getNextDialogue();
	}
}


