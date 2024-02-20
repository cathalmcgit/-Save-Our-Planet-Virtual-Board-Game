/**
 * 
 */
package boardGame;

/**
 * @author Aidan
 *
 */
public abstract class Square {
	
	//constants for validation
	private static final int NAME_LOWER_LIMIT = 1;
	private static final int POSITION_LOWER_LIMIT = 0;
	private static final int POSITION_UPPER_LIMIT = 12;
	
	// Variables
	protected String squareName;
	protected int squarePosition;
	protected Player owner;
	
	// Constructor
	public Square(String squareName, int squarePosition) {
	    this.setSquareName(squareName);
	    this.squarePosition = squarePosition;
	}
	
	// Getters setters
	
	/*
	 * @returns the Square's name
	 */
	public String getSquareName() {
		return squareName;
	}
	
	
	/**
	 * Sets the name of a square
	 * @param name
	 * @throws IllegalArgumentException if an invalid name is entered
	 */
	public void setSquareName(String squarename) throws IllegalArgumentException {
		
		if (squarename != null && squarename.length() >= NAME_LOWER_LIMIT) {
			this.squareName = squarename;
		} else {
			throw new IllegalArgumentException("Invalid Name");
		}
	}

	/*
	 * @returns the Square's position
	 */
	public int getSquarePosition() {
		return squarePosition;
	}
	
	
	/**
	 * @param squarePosition the squarePosition to set
	 */
	public void setSquarePosition(int squarePosition) {
	
	if (squarePosition >= POSITION_LOWER_LIMIT && squarePosition <= POSITION_UPPER_LIMIT) {
		this.squarePosition = squarePosition;
	} else {
		throw new IllegalArgumentException("Invalid Position");
	}
	}
	
	/*
	 * @returns the Player which is the Square's owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of a Square
	 * @param owner
	 * null allowed in order that squares can have no owner
	 */
	public void setOwner(Player owner) {
			this.owner = owner;
		
	}

	
	// Methods that child classes will need to provide:
	
	protected abstract void displaySquareMessage();
	
}
