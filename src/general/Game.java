package general;

public class Game {
	private static EventDialogue currentEvent;
	private static int id0 = 0;
	private static int id1 = 0;
	private static int id2 = 0;
	private static int id3 = 0;
	private static int id4 = 0;
	private static int currentId = 0;
	private static int[] dialogueId = {id0, id1, id2, id3, id4};

	public static void initializeGameDialogue(){		
		Dialogue[] eventDialogue = new Dialogue[4];
		int i = 0;
		eventDialogue[i] = new Dialogue(dialogueId);
		eventDialogue[i].addText("Welcome to Village Builder");
		dialogueId[currentId]++;
		eventDialogue[i+1] = eventDialogue[i].setAndGetNextDialogue(new Dialogue(dialogueId));
		i++;
				
		eventDialogue[i].addText("The Eternal Empire has been expanding its borders of late.");		
		dialogueId[currentId]++;
		eventDialogue[i+1] = eventDialogue[i].setAndGetNextDialogue(new Dialogue(dialogueId));
		i++;
		
		eventDialogue[i].addText("You will have many choices to make. Will you be up to it?");
		eventDialogue[i].addChoice(new Choice("Yes"));
		eventDialogue[i].addChoice(new Choice("We will see"));
		dialogueId[currentId]++;
		eventDialogue[i+1] = eventDialogue[i].setAndGetNextDialogue(new Dialogue(dialogueId));
		i++;
		
		eventDialogue[i].addText("Interesting");	
		
		EventDialogue introDialogue = new EventDialogue("Intro", eventDialogue);
		currentEvent = introDialogue;
		
		EventDialogue intro2Dialogue = new EventDialogue("Intro2", eventDialogue);
		introDialogue.setNextEventDialogue(intro2Dialogue);
		
//		Dialogue[] eventDialogue = new Dialogue[4];
//		int i = 0;
//		eventDialogue[i] = 
//				new Dialogue("Welcome to Village Builder");
//		i++;
//		eventDialogue[i] = eventDialogue[i-1].setAndGetNextDialogue(
//				new Dialogue("The Eternal Empire has been expanding its borders of late."));		
//		i++;
//		String[] choices = {"Yes", "We will see"};
//		eventDialogue[i] = eventDialogue[i-1].setAndGetNextDialogue(
//				new Dialogue("You will have many choices to make. Will you be up to it?", choices));
//		i++;
//		eventDialogue[i] = eventDialogue[i-1].setAndGetNextDialogue(
//				new Dialogue("Interesting"));	
//		
//		EventDialogue introDialogue = new EventDialogue("Intro", eventDialogue);
//		currentEvent = introDialogue;
//		
//		EventDialogue intro2Dialogue = new EventDialogue("Intro2", eventDialogue);
//		introDialogue.setNextEventDialogue(intro2Dialogue);
	}


	public static Dialogue getCurrentDialogue() {
		return currentEvent.getCurrentDialogue();
	}
	
	public static Dialogue advanceDialogue() {
		if (currentEvent.getNextDialogue() == null){
			currentEvent = currentEvent.getNextEvent();	
			return currentEvent.getCurrentDialogue();
		}
		return currentEvent.advanceDialogue();
	}


}
