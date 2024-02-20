/**
 * 
 */
package boardGame;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Aidan
 * A class used to represent information about a Player
 */
public class Player {
	
	// constants for validation
	private static final int NAME_LOWER_LIMIT = 1;
	private static final int POSITION_LOWER_LIMIT = 0;
	private static final int POSITION_UPPER_LIMIT = 12;
	
	private String name;
	private int balance;
	private int position;
	private Set<StreetSquare> ownedSquares;
	
	/**
	 * @param name
	 * @param balance
	 * @param position
	 */
	public Player(String name, int balance, int position) {
		this.setName(name);
		this.setBalance(balance);
		this.setPosition(position);
		this.ownedSquares = new HashSet<>();
	}
	
	/**
	 * @returns the name of the player
	 */
    public String getName() {
        return name;
    }

    /**
	 * Sets the name of a player
	 * @param name
	 * @throws IllegalArgumentException if an invalid name is entered
	 */
	public void setName(String name) throws IllegalArgumentException {
		
		if (name != null && name.length() >= NAME_LOWER_LIMIT) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Invalid Name");
		}
	}
    
    /*
     * @returns the balance of the player
     */
    public int getBalance() {
    	return balance;
    }
    
    /**
	 * Sets the balance of a player
	 * @param balance
	 * option to set balance less than zero allowed, as this will lead
	 * to the player's elimination from the game
	 */
    public void setBalance(int balance) {
    	this.balance = balance;
    	}
    
    
    /*
     * @returns the position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
	 * Sets the position of the player
	 * @param position
	 * @throws IllegalArgumentException if invalid data is entered
	 */
    public void setPosition(int position) throws IllegalArgumentException {
    	if (position >= POSITION_LOWER_LIMIT && position <= POSITION_UPPER_LIMIT) {
    		 this.position = position;
		} else {
			throw new IllegalArgumentException("Invalid Position");
		}
       
    }
	
    /*
     * 
     */
    public Set<StreetSquare> getOwnedSquares() {
        return ownedSquares;
    }
    
    /*
     * 
     */
    public void addOwnedSquare(StreetSquare square) {
        ownedSquares.add(square);
    }
    
    /*
     * 
     */
    public void removeOwnedSquare(StreetSquare square) {
        ownedSquares.remove(square);
    }

	@Override
	public String toString() {
		return "Player [name=" + name + ", balance=" + balance + ", position=" + position + ", ownedSquares="
				+ ownedSquares + "]";
	}
    
	/*
	 * 
	 */
	public void deductEcos(int buyCost) {
		int newBalance = this.balance - buyCost;
		setBalance(newBalance);
	}

	public void addEcos(int payment) {
		int newBalance = this.balance + payment;
		setBalance(newBalance);
	}

	public void sellProperty(StreetSquare property, int sellPrice) {
		// TODO Auto-generated method stub
		ownedSquares.remove(property);
        property.setOwner(null);
        addEcos(sellPrice);
	}

	public void buildCampsite(StreetSquare property) {
		if(property.getOwner() == this && property.getNumberOfMinorDevs() < 3) {
			this.deductEcos(property.getBuildCampsiteCost());
			property.addCampsite();
		}
	}
	
	public void buildGlampsite(StreetSquare property) {
		if(property.getOwner() == this && property.getNumberOfMinorDevs() == 3 && !property.hasGlampsite) {
			this.deductEcos(property.getBuildGlampsiteCost());
			property.setHasGlampsite(true);
		}
	}
	
	public boolean ownsAllSquaresOfType(Class<? extends StreetSquare> squareType) {
	    int count = 0;
	    for (Square square : ownedSquares) {
	        if (squareType.isInstance(square)) {
	            count++;
	        }
	    }

	    if (squareType == RecyclingSquare.class) {
	        return count == 2;
	    } else if (squareType == CarbonOffsetSquare.class) {
	        return count == 3;
	    } else if (squareType == RenewableEnergySquare.class) {
	        return count == 3;
	    } else if (squareType == SustainabilitySquare.class) {
	        return count == 2;
	    } else {
	        return false;
	    }
	}


}
