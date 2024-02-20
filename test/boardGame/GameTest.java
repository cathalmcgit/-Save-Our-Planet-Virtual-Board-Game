package boardGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	
	//test data
		List<Square> testGameboard;
		int gameBoardSize;


	@BeforeEach
	void setUp() throws Exception {
		
		testGameboard = Game.createGameBoard();
		gameBoardSize = 12;

	}
	
	@Test
	void testCreateGameboard() {
		testGameboard = Game.createGameBoard();
		
		if (testGameboard.size() == gameBoardSize) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

}
