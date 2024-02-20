package boardGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquaresTest {

// test data
    
    String squareNameValid, squareNameInvalid, squarenameInvalidNull;
    Field RecyclingField, Carbon_OffsetField;
    int positionValidLower, positionValidMiddle, positionValidUpper, positionInvalidUpper, positionInvalidLower;
    int buyingPriceValidLower, buyingPriceValidMiddle, buyingPriceValidUpper, buyingPriceInvalid;
    int landingCostValid, landingCostInvalid;
    boolean ownedValid = true;
    
    GoSquare testGoSquare;
    RecyclingSquare testRecyclingSquare;
    CarbonOffsetSquare testCarbonSquare;
    RenewableEnergySquare testRenewableSquare;
    SustainabilitySquare testSustainabilitySquare;
    
    Player testPlayer;
	
	@BeforeEach
	void setUp() throws Exception {
		
		squareNameValid = "Vegan Valley";
		squareNameInvalid = "";
		squarenameInvalidNull = null;
		RecyclingField = Field.Recycling;
		Carbon_OffsetField = Field.Carbon_Offset;
		positionValidLower = 1;
		positionValidMiddle = 6;
		positionValidUpper = 11;
		positionInvalidLower = -6;
		positionInvalidUpper = 14;
		buyingPriceValidLower = 100;
		buyingPriceValidMiddle = 800;
		buyingPriceValidUpper = 1500;
		buyingPriceInvalid = -1;
		landingCostValid = 200;
		landingCostInvalid = -1;
		ownedValid = true;
		
		testRecyclingSquare = new RecyclingSquare(squareNameValid, buyingPriceInvalid);
		testGoSquare = new GoSquare(squareNameValid, buyingPriceInvalid);
		
		testPlayer = new Player("Malcolm", 175, 6);
		
	}
	
	@Test
	void testRecyclingSquareConstructor() {
		
		testRecyclingSquare = new RecyclingSquare(squareNameValid, buyingPriceValidLower);
		
		assertNotNull(testRecyclingSquare);
		
		assertEquals(squareNameValid, testRecyclingSquare.getSquareName());
		assertEquals(RecyclingField, testRecyclingSquare.getFieldName());
		assertEquals(false, testRecyclingSquare.isOwned());
		assertEquals(false, testRecyclingSquare.hasCampsite);
		assertEquals(false, testRecyclingSquare.hasGlampsite);
		
	}
	
	@Test
	void testInvalidRecyclingSquareConstructor() {

		assertThrows(IllegalArgumentException.class, () -> {
			testRecyclingSquare = new RecyclingSquare(squareNameInvalid, buyingPriceValidLower);
			testRecyclingSquare = new RecyclingSquare(squareNameValid, buyingPriceInvalid);
		});

	}

	
	@Test
	void testGetSetSquareNameValid() {
		
		testRecyclingSquare.setSquareName(squareNameValid);
		assertEquals(squareNameValid, testRecyclingSquare.getSquareName());
	}
	
	
	@Test
	void testGetSetSquareNameInvalid() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testRecyclingSquare.setSquareName(squareNameInvalid);
			testRecyclingSquare.setSquareName(squarenameInvalidNull);
			
		});
		
		assertEquals("Invalid Name", ex.getMessage());
	}
	
	
	@Test
	void testIsOwned() {
		testRecyclingSquare.setOwned(true);
	    assertTrue(testRecyclingSquare.isOwned());
	    
	    testRecyclingSquare.setOwned(false);
	    assertFalse(testRecyclingSquare.isOwned());
	}


	@Test
	void testSetHasCampsite() {
		testRecyclingSquare.setHasCampsite(true);
	    assertTrue(testRecyclingSquare.hasCampsite());
	    
	    testRecyclingSquare.setHasCampsite(false);
	    assertFalse(testRecyclingSquare.hasCampsite());
	}

	@Test
	void testSetHasGlampsite() {
		testRecyclingSquare.setHasGlampsite(true);
	    assertTrue(testRecyclingSquare.hasGlampsite());
	    
	    testRecyclingSquare.setHasGlampsite(false);
	    assertFalse(testRecyclingSquare.hasGlampsite());
	}

	@Test
	void testGetSetFieldNameValid() {
		
		testRecyclingSquare.setFieldName(Field.Carbon_Offset);
		assertEquals(Field.Carbon_Offset, testRecyclingSquare.getFieldName());
		
		testRecyclingSquare.setFieldName(Field.Sustainability);
		assertEquals(Field.Sustainability, testRecyclingSquare.getFieldName());
	}

	
	void testSetFieldNullException() {
		assertThrows(IllegalArgumentException.class, ()->{
			testRecyclingSquare.setFieldName(null);
		});
	}
	
	@Test
	void testGetSetPositionValid() {
		
		testRecyclingSquare.setSquarePosition(positionValidLower);
		assertEquals(positionValidLower, testRecyclingSquare.getSquarePosition());
		
		testRecyclingSquare.setSquarePosition(positionValidMiddle);
		assertEquals(positionValidMiddle, testRecyclingSquare.getSquarePosition());
		
		testRecyclingSquare.setSquarePosition(positionValidUpper);
		assertEquals(positionValidUpper, testRecyclingSquare.getSquarePosition());
		
	}

	@Test
	void testGetSetPositionInvalid() {
		
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testRecyclingSquare.setSquarePosition(positionInvalidLower);
			testRecyclingSquare.setSquarePosition(positionInvalidUpper);
		});
		assertEquals("Invalid Position", ex.getMessage());
	}
	
	@Test
	void testGetSetOwner() {
		testRecyclingSquare.setOwner(testPlayer);
		assertEquals(testPlayer, testRecyclingSquare.getOwner());
	}
	
	@Test
	void testGetSetHasCampsite() {
		assertFalse(testRecyclingSquare.hasCampsite);
		
		testRecyclingSquare.setHasCampsite(true);
		
		assertTrue(testRecyclingSquare.hasCampsite);

	}

	@Test
	void testGetSetHasGlampsite() {
		assertFalse(testRecyclingSquare.hasGlampsite);
		
		testRecyclingSquare.setHasGlampsite(true);
		
		assertTrue(testRecyclingSquare.hasGlampsite);

	}


}
