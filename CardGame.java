
/*
 * basic script for creating a shuffled deck of cards,
 * outputs the results to the terminal
 */

import static java.lang.System.out;
import static java.lang.System.err;

public class CardGame {
	
	/*
	 *  CLASS VARIABLES
	 */
	
	static CardQueue cardQ;
	static int[] cardsUnique;
	static Card tempCard;
	static int tempHash;
	
	/*
	 *  MAIN METHOD
	 *  
	 *  Creates a standard, unshuffled arrangement of all 52 cards,
	 *  then shuffles the deck using a Fisher-Yates shuffle and
	 *  prints the resulting deck to the terminal.
	 *  
	 *  Finally, a hash-table is used to verify that the shuffle did
	 *  not accidentally duplicate or remove a card.
	 */
	
	public static void main(String[] args) {
		cardsUnique = new int[52];
		
		// Create a new deck of cards
		out.println("Generating new deck:");
		cardQ = new CardQueue();
		for (int i = 0; i < 52; i++) {
			out.print("Card " + (i+1) + ": \t");
			tempCard = cardQ.drawCard();
			out.println(tempCard.makeString());
		}
		out.println();
		
		// Shuffle the deck of cards
		out.println("Shuffling deck:");
		cardQ.shuffleDeck();
		for (int i = 0; i < 52; i++) {
			out.print("Card " + (i+1) + ": \t");
			tempCard = cardQ.drawCard();
			out.println(tempCard.makeString());
			tempHash = tempCard.hash();
			cardsUnique[tempHash] += 1;
		}
		out.println();
		
		cardQ.shuffleDeck();
		cardQ.countRemainingCards();
		
		cardQ.drawCard();
		cardQ.countRemainingCards();
		
		// Double-check that all cards remain in deck after shuffle
		boolean areCardsUnique = true;
		for (int i = 0; i < cardsUnique.length; i++) {
			// out.println("Array index " + i + ", array value " + cardsUnique[i]);
			if (cardsUnique[i] != 1) {
				areCardsUnique = false;
			}
		}
		if (areCardsUnique) {
			out.println("Valid shuffle. No cards missing.");
		} else {
			out.println("Invalid shuffle. Some cards missing or duplicates.");
		}
	}
}
