package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;

import code.Map;
import code.WhirlpoolFactory;
import org.junit.jupiter.api.Test;
import code.FollowPirateShip;
import code.Ship;

class PirateMoveTest {

	@Test
	void checkWhirlpoolCreation()
	{
		WhirlpoolFactory factory = new WhirlpoolFactory(true);

		factory.createWhirlpool();
		assertTrue(factory.getActiveList().size() == 1);
		assertTrue(factory.getInactiveList().size() == 0);

		factory.removeWhirlpool();

		assertTrue(factory.getActiveList().size() == 0);
		assertTrue(factory.getInactiveList().size() == 1);
	}
}