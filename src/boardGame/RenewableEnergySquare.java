package boardGame;

public class RenewableEnergySquare extends StreetSquare {
	// Set prices
	private final int buyCost = 500;
	private final int buildCampsiteCost = 750;
	private final int buildGlampsiteCost = 1000;
	private final int priceToLand = 200;
	private final int priceToLandWithCampsite = 600;
	private final int priceToLandWithGlampsite = 850;
	private final String minorDevName = "Wind Farm";
	private final String majorDevName = "Solar Power Plant";
	
	// Constructor
    public RenewableEnergySquare(String squareName, int squarePosition) {
        super(squareName, squarePosition);
        this.setFieldName(Field.Renewable_Energy);
        this.owned = false;
        this.hasCampsite = false;
        this.hasGlampsite = false;
        this.setMinorDevName(minorDevName);
        this.setMajorDevName(majorDevName);
    }
    
	// Getters setters
	public int getBuyCost() {
		return this.buyCost;
	}
	public int getBuildCampsiteCost() {
		return this.buildCampsiteCost;
	}
	public int getBuildGlampsiteCost() {
		return this.buildGlampsiteCost;
	}
	public int getLandPrice() {
		return this.priceToLand;
	}
	public int getLandPriceWithCampsite() {
		return this.priceToLandWithCampsite;
	}
	public int getLandPriceWithGlampsite() {
		return this.priceToLandWithGlampsite;
	}
	
	public boolean isOwned() {
        return this.owned;
    }
    public void setOwned(boolean owned) {
        this.owned = owned;
    }
    public boolean hasCampsite() {
        return this.hasCampsite;
    }
    public void setHasCampsite(boolean hasCampsite) {
        this.hasCampsite = hasCampsite;
    }
    public boolean hasGlampsite() {
        return this.hasGlampsite;
    }
    public void setHasGlampsite(boolean hasGlampsite) {
        this.hasGlampsite = hasGlampsite;
        
    }

	@Override
	public void displaySquareMessage() {
	    String availability;
	    int landingPrice;
	    
	    if (hasGlampsite) {
	    	landingPrice = priceToLandWithGlampsite;
        } else if (hasCampsite) {
        	landingPrice = priceToLandWithCampsite;
        } else {
        	landingPrice = priceToLand;
        }

	    /*
	    if (this.owned == false) {
	        availability = "available";
	        System.out.println(String.format("You are on: %s", this.squareName));
	        System.out.println(String.format("Field: %s", this.getFieldName()));
	        System.out.println(String.format("This square is %s", availability));
	        System.out.println(String.format("This square costs %d ecos to buy", this.buyCost));
	    } else {
	        availability = "taken";
	        System.out.println(String.format("You are on: %s", this.squareName));
	        System.out.println(String.format("Field: %s", this.getFieldName()));
	        System.out.println(String.format("This square is %s", availability));
	        System.out.println(String.format("You must pay %d ecos to land on this square", landingPrice));
	    }
	    System.out.println();
	    */
	    
	    if (this.owned == false) {
	        availability = "available";
	    } else {
	        availability = "taken";
	    }

	    String squareInfo = String.format("You are on: %s", this.squareName);
	    String fieldInfo = String.format("Field: %s", this.getFieldName());
	    String availabilityInfo = String.format("This square is %s", availability);
	    String costInfo;

	    if (this.owned == false) {
	        costInfo = String.format("This square costs %d ecos to buy", this.buyCost);
	    } else {
	        costInfo = String.format("You must pay %d ecos to land on this square", landingPrice);
	    }

	    int maxLength = Math.max(Math.max(squareInfo.length(), fieldInfo.length()), Math.max(availabilityInfo.length(), costInfo.length()));

	    String horizontalLine = "+" + "-".repeat(maxLength + 2) + "+";

	    System.out.println(horizontalLine);
	    System.out.println("| " + squareInfo + " ".repeat(maxLength - squareInfo.length()) + " |");
	    System.out.println("| " + fieldInfo + " ".repeat(maxLength - fieldInfo.length()) + " |");
	    System.out.println("| " + availabilityInfo + " ".repeat(maxLength - availabilityInfo.length()) + " |");
	    System.out.println("| " + costInfo + " ".repeat(maxLength - costInfo.length()) + " |");
	    System.out.println(horizontalLine);
	    System.out.println();

	}
}
