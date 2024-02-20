package boardGame;
/**
 * @author heidiwilson
 *
 */
public class Dice {
	
	private int diceOne;
	private int diceTwo;
	private int diceTotal;
	
	public static final int MIN_DICE_VALUE = 1;
	public static final int MAX_DICE_VALUE = 6;
	public static final int MIN_TOTAL_DICE_VALUE = 2;
	public static final int MAX_TOTAL_DICE_VALUE = 12;
	
	/*
	 * Default Constructor
	 */
	public Dice() {
		
		
	}

	public int getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(int diceOne) throws IllegalArgumentException {
		
		if (diceOne >= MIN_DICE_VALUE && diceOne <= MAX_DICE_VALUE ) {
			this.diceOne = diceOne;
		} else {
			throw new IllegalArgumentException("Invalid Dice");
		}
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(int diceTwo) throws IllegalArgumentException {
		
		if (diceTwo >= MIN_DICE_VALUE && diceTwo <= MAX_DICE_VALUE ) {
			this.diceTwo = diceTwo;
		} else {
			throw new IllegalArgumentException("Invalid Dice");
		}
	}

	public int getDiceTotal() {
		return diceTotal;
	}

	public void setDiceTotal(int diceTotal) throws IllegalArgumentException {
		
		if (diceTotal >= MIN_TOTAL_DICE_VALUE && diceTotal <= MAX_TOTAL_DICE_VALUE ) {
			this.diceTotal = diceTotal;
		} else {
			throw new IllegalArgumentException("Invalid Dice Total");
		}
		
	}
	
	public void rollDice() {
		
		this.setDiceOne((int) (MAX_DICE_VALUE * Math.random() + 1));
		this.setDiceTwo((int) (MAX_DICE_VALUE * Math.random() + 1));
		
		diceTotal = diceOne + diceTwo;
	
		System.out.println("You have rolled a " + diceOne + " on the first dice and a " + diceTwo + " on the second dice. " +"\nMove " + diceTotal + " squares!\n");
		
	}


}
