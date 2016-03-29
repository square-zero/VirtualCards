/*
 * Queue for a virtual deck of 52 cards
 */

import static java.lang.System.out;
import static java.lang.System.err;

public class CardQueue {
	/*
	 *  CLASS VARIABLES
	 */
	private Card[] deck;
	private int cardsInDeck;
	private int maxCardsInDeck;
	private int numOfSuits;
	private boolean[] suitsInDeck; // hearts -> diamonds -> spades -> clubs
	private int minVal;
	private int maxVal;
	
	/*
	 *  QUEUE VARIABLES
	 */
	private int head;
	private int tail;
	
	/*
	 *  CLASS CONSTRUCTORS
	 */
	
	// create a deck of cards with custom parameters
	// allows creating sub-sets of a 52-card deck
	// assumes minVal < maxVal, values will be switched
	public CardQueue(	int minVal,
						int maxVal, 
						boolean hasHearts,
						boolean hasDiamonds,
						boolean hasSpades,
						boolean hasClubs	) {
		// store value range
		// first, check that values are within acceptable range
		if (minVal < 1 || minVal > 13) {
			if (minVal < 1) {
				minVal = 1;
			} else {
				minVal = 13;
			}
		}
		if (maxVal < 1 || maxVal > 13) {
			if (maxVal > 13) {
				maxVal = 13;
			} else {
				maxVal = 1;
			}
		}
		
		// next, assign minVal/maxVal
		// this.minVal = min(minVal, maxVal)
		// this.maxVal = max(minVal, maxVal)
		if (minVal > maxVal) {
			this.minVal = maxVal;
			this.maxVal = minVal;
		} else {
			this.minVal = minVal;
			this.maxVal = maxVal;
		}
		
		// count number of cards in deck
		suitsInDeck = new boolean[4];
		suitsInDeck[0] = hasHearts;
		suitsInDeck[1] = hasDiamonds;
		suitsInDeck[2] = hasSpades;
		suitsInDeck[3] = hasClubs;

		for (int i = 0; i < suitsInDeck.length; i++) {
			if (suitsInDeck[i]) {
				numOfSuits++;
			}
		}
		maxCardsInDeck = numOfSuits*(maxVal - minVal + 1);
		
		deck = new Card[maxCardsInDeck];
		
		for (int i = 0; i < deck.length; i++) {
			deck[i] = new Card(i+1);
			cardsInDeck++;
		}
		
		head = 0;
		tail = 52;
	}
	
	// create a standard deck with 52-cards
	public CardQueue() {
		this(1, 13, true, true, true, true);
	}
	
	/*
	 *  CLASS METHODS
	 */
	
	// draws the top card from the deck
	// prints to console
	// (DE-QUEUE)
	public Card drawCard() throws ArrayIndexOutOfBoundsException {
		if (head == tail) {
			throw new ArrayIndexOutOfBoundsException();
		}
		head++;
		cardsInDeck--;
		return deck[head - 1];
	}
	
	
	// Re-shuffles the remaining cards without replacing dealt cards
	public void shuffleRemainingDeck() {
		// not implemented yet
	}
	
	// Re-shuffles the cards, including any that have been dealt
	public void shuffleDeck() {
		head = 0;
		tail = 52;
		cardsInDeck = 52;
		
		Card temp;
		int toShuffle;
		
		for (int i = 0; i < deck.length; i++) {
			temp = deck[i];
			toShuffle = (int) (i + Math.floor(Math.random()*(52-i)));			
			deck[i] = deck[toShuffle];
			deck[toShuffle] = temp;
		}
	}
	
	private static void printCard(Card card) {
		out.println(card.makeString());
	}
	
	// Prints the number of remaining cards to the terminal
	public void countRemainingCards() {
		out.println("There are " + cardsInDeck + " cards remaining in the deck.");
	}
	
	// Returns all drawn cards to deck in the order they appeared
	public void resetHead() {
		head = 0;
		cardsInDeck = 52;
	}
}
