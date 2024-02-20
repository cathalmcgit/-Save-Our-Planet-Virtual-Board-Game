package boardGame;

public abstract class StreetSquare extends Square{
	
	protected boolean owned;
	protected boolean hasCampsite;
	protected int numberOfCampsites;
	protected boolean hasGlampsite;
	private Field fieldName;
	String minorDevName;
	String majorDevName;
	
	public StreetSquare(String squareName, int squarePosition) {
		super(squareName, squarePosition);
	}
	
	// Getters setters
	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public boolean getHasCampsite() {
        return hasCampsite;
    }

    public void setHasCampsite(boolean hasCampsite) {
        this.hasCampsite = hasCampsite;
    }
    
    public int getNumberOfMinorDevs() {
        return numberOfCampsites;
    }

    public void addCampsite() {
        this.numberOfCampsites += 1;
    }

    public boolean getHasGlampsite() {
        return hasGlampsite;
    }

    public void setHasGlampsite(boolean hasGlampsite) {
        this.hasGlampsite = hasGlampsite;
    }
    
    
    
	
	/**
	 * @return the fieldName
	 */
	public Field getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(Field fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the minorDevName
	 */
	public String getMinorDevName() {
		return minorDevName;
	}

	/**
	 * @param minorDevName the minorDevName to set
	 */
	public void setMinorDevName(String minorDevName) {
		this.minorDevName = minorDevName;
	}

	/**
	 * @return the majorDevName
	 */
	public String getMajorDevName() {
		return majorDevName;
	}

	/**
	 * @param majorDevName the majorDevName to set
	 */
	public void setMajorDevName(String majorDevName) {
		this.majorDevName = majorDevName;
	}

	// To be implemented by child classes
	protected abstract int getBuyCost();
	protected abstract int getBuildCampsiteCost();
	protected abstract int getBuildGlampsiteCost();
	protected abstract int getLandPrice();
	protected abstract int getLandPriceWithCampsite();
	protected abstract int getLandPriceWithGlampsite();
	
}
