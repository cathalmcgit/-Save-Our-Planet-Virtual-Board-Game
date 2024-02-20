package boardGame;

public class GoSquare extends Square{

	private final int bonus = 100;
	
    public GoSquare(String squareName, int squarePosition) {
    	super(squareName, squarePosition);
    }
    
    public int getBonus() {
        return bonus;
    }
	
	@Override
	protected void displaySquareMessage() {
		String goMsg = String.format("You have landed on the Go square and receive a bonus of %d Ecos!", this.bonus);
		String horizontalLine = "+" + "-".repeat(goMsg.length() + 2) + "+";
		
		System.out.println();
		System.out.println(horizontalLine);
		System.out.println("| " + goMsg + " |");
		System.out.println(horizontalLine);
		System.out.println();
	}

}
