package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import code.WhirlpoolFactory;

class WhirlpoolTest {

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
