package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.jupiter.api.Test;
import code.PirateShip;
import code.Ship;

class PirateMoveTest {

	PirateShip pirate;
	Ship ship;
	@Test
	public void testFollow()//test that their distance from eachother changed after 2 moves
	{
		ship = new Ship();
		pirate  = new PirateShip(ship);
		Point shipstart = ship.getLocation();
		Point startloc = pirate.getLocation();
		ship.goNorth();
		ship.goSouth();
		int xdif = shipstart.x-startloc.x;
		int ydif = shipstart.y - startloc.y;
		assertTrue((ship.getLocation().y-pirate.getLocation().y)!=ydif 
				||(ship.getLocation().x-pirate.getLocation().x)!=xdif);
	}

}
