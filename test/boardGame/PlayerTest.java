/**
 * 
 */
package boardGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author heidiwilson
 *
 */
class PlayerTest {
	
	// test data
	String nameValid1, nameValid2, nameInvalidEmpty, nameInvalidNull, nameInvalidNotAlpha;
	
	int balanceValidLower, balanceValidMiddle, balanceValidUpper;
	
	int positionValidLower, positionValidMiddle, positionValidUpper, 
	positionInvalidLower, positionInvalidUpper;
	
	Player testPlayer;
	
	StreetSquare square1, square2;
	
	int square1SellPrice, square2SellPrice;
	
	int buildRenewableCampsiteCost, buildSustainabilityCampsiteCost;



	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		nameValid1 = "Arnold";
		nameValid2 = "Heidi";
		nameInvalidEmpty = "";
		nameInvalidNull = null;
		nameInvalidNotAlpha = "5";
		
		balanceValidLower = -50;
		balanceValidMiddle = 100;
		balanceValidUpper = 500;
		
		positionValidLower = 0;
		positionValidMiddle = 6;
		positionValidUpper = 11;
		positionInvalidLower = -1;
		positionInvalidUpper = 12;
		
		testPlayer = new Player(nameValid1, balanceValidMiddle, positionValidMiddle);
		
		square1 = new RenewableEnergySquare(nameValid1, balanceValidLower);
		square2 = new SustainabilitySquare(nameValid2, balanceValidUpper);

		square1SellPrice = 250;
		square2SellPrice = 375;
		
		buildRenewableCampsiteCost = 750;
		buildSustainabilityCampsiteCost = 1125;

	}

	@Test
	void testPlayerConstructor() {
		//test constructor with args
		testPlayer = new Player(nameValid2, balanceValidLower, positionValidLower);
		
		//verify object actually created
		assertNotNull(testPlayer);
		
		assertEquals(nameValid2, testPlayer.getName());
		assertEquals(balanceValidLower, testPlayer.getBalance());
		assertEquals(positionValidLower, testPlayer.getPosition());

	}
	
	@Test
	void testNegativePlayerConstructor() {

		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer = new Player(nameInvalidNull, balanceValidLower, positionValidLower);
			testPlayer = new Player(nameValid1, balanceValidLower, positionInvalidLower);
		});

	}

	@Test
	void testGetSetNameValid() {

		testPlayer.setName(nameValid2);
		assertEquals(nameValid2, testPlayer.getName());
	}
	
	@Test
	void testGetSetNameInvalid() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testPlayer.setName(nameInvalidEmpty);
			testPlayer.setName(nameInvalidNull);
			testPlayer.setName(nameInvalidNotAlpha);
		});
		
		assertEquals("Invalid Name", ex.getMessage());
	}

	@Test
	void testGetSetBalanceValid() {
		
		testPlayer.setBalance(balanceValidLower);
		assertEquals(balanceValidLower, testPlayer.getBalance());
		
		testPlayer.setBalance(balanceValidMiddle);
		assertEquals(balanceValidMiddle, testPlayer.getBalance());
		
		testPlayer.setBalance(balanceValidUpper);
		assertEquals(balanceValidUpper, testPlayer.getBalance());
	}

	@Test
	void testGetSetPositionValid() {
	
		testPlayer.setPosition(positionValidLower);
		assertEquals(positionValidLower, testPlayer.getPosition());
		
		testPlayer.setPosition(positionValidMiddle);
		assertEquals(positionValidMiddle, testPlayer.getPosition());
		
		testPlayer.setPosition(positionValidUpper);
		assertEquals(positionValidUpper, testPlayer.getPosition());
	}

	@Test
	void testGetSetPositionInvalid() {
	
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testPlayer.setPosition(positionInvalidLower);
			testPlayer.setPosition(positionInvalidUpper);
		});
		
		assertEquals("Invalid Position", ex.getMessage());
	}

	@Test
	void testGetSetOwnedSquares() {
		
		testPlayer.addOwnedSquare(square1);
		testPlayer.addOwnedSquare(square2);
		
		Set<StreetSquare> ownedSquares = testPlayer.getOwnedSquares();
        assertEquals(2, ownedSquares.size());
        assertTrue(ownedSquares.contains(square1));
        assertTrue(ownedSquares.contains(square2));
		
	}

	@Test
	void testAddOwnedSquare() {
		
		testPlayer.addOwnedSquare(square1);
		assertTrue(testPlayer.getOwnedSquares().contains(square1));
		
		testPlayer.addOwnedSquare(square2);
		assertTrue(testPlayer.getOwnedSquares().contains(square2));
		
	}

	@Test
	void testRemoveOwnedSquare() {
		
		testPlayer.addOwnedSquare(square1);
		testPlayer.addOwnedSquare(square2);
		
		testPlayer.removeOwnedSquare(square2);
		assertFalse(testPlayer.getOwnedSquares().contains(square2));
		
		testPlayer.removeOwnedSquare(square1);
		assertFalse(testPlayer.getOwnedSquares().contains(square1));
		
		assertEquals(0,testPlayer.getOwnedSquares().size());
	}
	
	@Test
	void testAddEcos() {
		testPlayer.setBalance(100);
		testPlayer.addEcos(80);
		assertEquals(180, testPlayer.getBalance());
		
		testPlayer.setBalance(-50);
		testPlayer.addEcos(80);
		assertEquals(30, testPlayer.getBalance());
	}
	
	@Test
	void testDeductEcos() {
		testPlayer.setBalance(100);
		testPlayer.deductEcos(50);
		assertEquals(50, testPlayer.getBalance());
		
		testPlayer.setBalance(40);
		testPlayer.deductEcos(80);
		assertEquals(-40, testPlayer.getBalance());
		
	}
	
	@Test
	void testOwnsAllSquareOfType() {
		testPlayer.setBalance(balanceValidUpper);
		testPlayer.addOwnedSquare(square1);
		testPlayer.sellProperty(square1, square1SellPrice);
		
		assertFalse (testPlayer.getOwnedSquares().contains(square1));
		assertFalse (square1.isOwned());
		assertEquals(testPlayer.getBalance(), (balanceValidUpper + square1SellPrice));
		
		testPlayer.setBalance(balanceValidMiddle);
		testPlayer.addOwnedSquare(square2);
		testPlayer.sellProperty(square2, square2SellPrice);
		
		assertFalse (testPlayer.getOwnedSquares().contains(square2));
		assertFalse (square2.isOwned());
		assertEquals(testPlayer.getBalance(), (balanceValidMiddle + square2SellPrice));

	}

}
