/**
 * 
 */
package boardGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Cathal
 *
 */
class DiceTest {
	
	
	// test data
	
	private int validRollLow, validRollHigh, invalidRollLow, invalidRollHigh,
				validTotalLow, validTotalHigh, invalidTotalLow, invalidTotalHigh;
	
	Dice testDice;

	
	@BeforeEach
	void setUp() throws Exception {
		
		invalidRollLow = 0;
		validRollLow = 1;
		validRollHigh = 6;
		invalidRollHigh = 7;
		
		invalidTotalLow = 1;
		validTotalLow = 2;
		validTotalHigh = 12;
		invalidTotalHigh = 13;
		
		testDice = new Dice();
		
	}

	@Test
	void testGetSetDiceOneValid() {
		testDice.setDiceOne(validRollLow);
		assertEquals(validRollLow, testDice.getDiceOne());
		
		testDice.setDiceOne(validRollHigh);
		assertEquals(validRollHigh, testDice.getDiceOne());
	}


	@Test
	void testGetSetDiceTwoValid() {
		testDice.setDiceTwo(validRollLow);
		assertEquals(validRollLow, testDice.getDiceTwo());
		
		testDice.setDiceTwo(validRollHigh);
		assertEquals(validRollHigh, testDice.getDiceTwo());
	}
	
	
	@Test
	void testGetSetDiceOneInvalid() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testDice.setDiceOne(invalidRollLow);
			testDice.setDiceOne(invalidRollHigh);
			
		});
		
		assertEquals("Invalid Dice", ex.getMessage());
	}
	
	@Test
	void testGetSetDiceTwoInvalid() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testDice.setDiceTwo(invalidRollLow);
			testDice.setDiceTwo(invalidRollHigh);
			
		});
		
		assertEquals("Invalid Dice", ex.getMessage());
	}

	
	@Test
	void testGetSetDiceTotal() {
		testDice.setDiceTotal(validTotalLow);
		assertEquals(validTotalLow, testDice.getDiceTotal());
		
		testDice.setDiceTotal(validTotalHigh);
		assertEquals(validTotalHigh, testDice.getDiceTotal());
	}

	
	@Test
	void testInvalidGetSetDiceTotal() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testDice.setDiceTotal(invalidTotalLow);
			testDice.setDiceTotal(invalidTotalHigh);
			
		});
		
		assertEquals("Invalid Dice Total", ex.getMessage());
	}

	
	@Test
	void testRollDice() {
		
		testDice.rollDice();
		
		assertTrue(testDice.getDiceOne() >= 1 && testDice.getDiceOne() <= 6);
		assertTrue(testDice.getDiceTwo() >= 1 && testDice.getDiceTwo() <= 6);
		
		assertTrue(testDice.getDiceTotal() >= 2 && testDice.getDiceTotal() <= 12);
	}

}
