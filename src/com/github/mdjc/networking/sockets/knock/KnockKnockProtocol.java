package com.github.mdjc.networking.sockets.knock;

import java.util.Random;

public class KnockKnockProtocol {

	private static enum State {
		WAITING, SENT_KNOCK_KNOCK, SENT_CLUE, SENT_ANSWER
	}

	private static final String[] clues = { "Amos", "Nana", "Etch", "Mikey" };
	private static final String[] answers = { "A mosquito", "Nana your bussiness", "Bless you!",
			"Mikey doesn't fit in the keyhole" };
	private static final String SAY_WHOS_THERE = "When I say Knock Knock you have to say: Who's there?. Knock! Knock!";
	private static final Random rand = new Random(); 

	public static final String THE_END = "the end.";

	private State currentState = State.WAITING;
	private int currentJoke = 0;

	public String processInput(String input) {
		String output = null;

		switch (currentState) {
		case WAITING:
			output = "Knock! Knock!";
			currentState = State.SENT_KNOCK_KNOCK;
			break;
		case SENT_KNOCK_KNOCK:
			if (input.equalsIgnoreCase("Who's there?")) {
				currentJoke = rand.nextInt(clues.length);
				output = clues[currentJoke];
				currentState = State.SENT_CLUE;
			} else {
				output = SAY_WHOS_THERE;
			}
			break;
		case SENT_CLUE:
			if (input.equalsIgnoreCase(clues[currentJoke] + " who?")) {
				output = answers[currentJoke];
				currentState = State.SENT_ANSWER;
			} else {
				output = "You have to ask: " + clues[currentJoke] + " who?";
			}
			break;
		case SENT_ANSWER:
			output = THE_END;
			break;
		}

		return output;
	}

}
