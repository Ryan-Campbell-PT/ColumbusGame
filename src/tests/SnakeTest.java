package tests;

import static org.junit.jupiter.api.Assertions.*;

import code.Snake;
import org.junit.jupiter.api.Test;

class SnakeTest {

	@Test
	void RandomMoveTest() //tests if he movesrandomly based on the random ints called randy being equal to moveTime
	{
		Snake snake = new Snake(true);

		int testX = snake.getCurrentLocation().x;
		int testY = snake.getCurrentLocation().y;

		snake.goEast(true);
		assertTrue(snake.getCurrentLocation().x == testX - 1 && snake.getCurrentLocation().y == testY);

		testX = snake.getCurrentLocation().x;
		testY = snake.getCurrentLocation().y;

		snake.goNorth(false);
		assertTrue(snake.getCurrentLocation().x == testX && snake.getCurrentLocation().y == testY - 1);

		testX = snake.getCurrentLocation().x;
		testY = snake.getCurrentLocation().y;

		snake.goSouth(false);
		assertTrue(snake.getCurrentLocation().x == testX && snake.getCurrentLocation().y == testY + 1);

		testX = snake.getCurrentLocation().x;
		testY = snake.getCurrentLocation().y;

		snake.goWest(false);
		assertTrue(snake.getCurrentLocation().x == testX + 1 && snake.getCurrentLocation().y == testY);

	}

}
