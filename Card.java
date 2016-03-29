/*
 * Class for an individual card in a standard deck
 * of 52 unique playing cards of 13 values and four suits
 */

import static java.lang.System.out;
import static java.lang.System.err;

public class Card {
	
	/*
	 *  CLASS VARIABLES
	 */
	
	private int value;		// face-value of card. 1-A, 11-J, 12-Q, 13-K
	private boolean[] type;	/*
									 *	0,0 == hearts
									 *	0,1 == diamonds
									 *	1,0 == spades
									 *	1,1 == clubs
									 */
	
	// Flags that are assigned True if either
	// - the value specified is too high (if using int in constructor) OR
	// - the suit assigned does not exist (if using String in constructor)
	private boolean incorrectType;
	private boolean incorrectValue;

	/*
	 * 	CLASS CONSTRUCTORS
	 */
	
	// Construct a new card by specifying its face value
	// and a two-bit representation of its suit (defined above)
	public Card(int value, boolean isBlack, boolean notHeartShaped) {
		this.value = value;
		type[0] = isBlack;
		type[1] = notHeartShaped;
		// if specified value is not within acceptable range
		// flag value as incorrect
		if (value < 1 || value > 13) {
			incorrectValue = true;
		}
	}
	
	// create a card by specifying the suit as a string
	// booleans initialize to false.
	// redundant initializations commented for posterity
	public Card(int value, String str) {
		this.value = value;
		if (value < 1 || value > 13) {
			incorrectValue = false;
		}
		type = new boolean[2];
		if (str.equalsIgnoreCase("Hearts")) {
			// type[0] = false;
			// type[1] = false;
		} else if (str.equalsIgnoreCase("Diamonds")) {
			// type[0] = false;
			type[1] = true;
		} else if (str.equalsIgnoreCase("Spades")) {
			type[0] = true;
			// type[1] = false;
		} else if (str.equalsIgnoreCase("Clubs")) {
			type[0] = true;
			type[1] = true;
		} else {
			// if invalid type specified,
			// card does not exist within deck
			incorrectType = true;
		}
	}
	
	// constructor to create a card using a value from 1-52
	public Card(int value) {
		if (value > 52 || value < 0) {
			incorrectValue = true;
		}
		this.value = ((value - 1) % 13) + 1;
		int suit = (value - 1) / 13;
		
		//out.println("" + suit);
		
		type = new boolean[2];
		if (suit == 0) {
			// hearts
			// type[0] = false;
			// type[1] = false;			
		} else if (suit == 1) {
			// diamonds
			// type[0] = false;
			type[1] = true;			
		} else if (suit == 2) {
			// spades
			type[0] = true;
			// type[1] = false;			
		} else if (suit == 3) {
			// clubs
			type[0] = true;
			type[1] = true;
		} else {
			incorrectType = true;
		}
	}
	
	/*
	 *  CLASS METHODS
	 */
	
	// super basic hash function 
	// mainly used for verification of shuffles
	// outputs 0-51 
	public int hash() {
		int hash = value - 1;
		if (!type[0] && !type[1]) {
			hash += 0;
		} else if (!type[0] && type[1]) {
			hash += 13;
		} else if (type[0] && type[1]) {
			hash += 26;
		} else {
			hash += 39;
		}
		
		return hash;
	}
	
	// Returns a string-representation of the card
	// containing: the face-value and the suit
	public String makeString() {
		String str = "";
		String vStr = "";
		
		// special names for certain cards
		if (value == 11) {
			vStr = "Jack";
		} else if (value == 12) {
			vStr = "Queen";
		} else if (value == 13) {
			vStr = "King";
		} else if (value == 1) {
			vStr = "Ace";
		} else {
			vStr = "" + value;
		}
		
		str += "" + vStr + "\t";
		
		// suits
		if (type[0] && type[1]) {
			str += "Clubs";
		} else if (type[0] || type[1]) {
			if (type[1]) {
				str += "Diamonds";
			} else {
				str += "Spades";
			}
		} else {
			str += "Hearts";
		}
		
		return str;
	}
}
