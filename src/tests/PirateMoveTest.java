package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.jupiter.api.Test;
import code.FollowPirateShip;
import code.Ship;

class PirateMoveTest {

	FollowPirateShip pirate;
	Ship ship;
	@Test
	public void testFollow()//test that their distance from eachother changed after 2 moves
	{
		ship = Ship.getInstance();
		pirate = new FollowPirateShip();
		Point shipstart = ship.getCurrentLocation();
		Point startloc = pirate.getCurrentLocation();
		ship.goNorth();
		ship.goSouth();
		int xdif = shipstart.x-startloc.x;
		int ydif = shipstart.y - startloc.y;
		assertTrue((ship.getCurrentLocation().y-pirate.getCurrentLocation().y)!=ydif 
				||(ship.getCurrentLocation().x-pirate.getCurrentLocation().x)!=xdif);
	}

}
