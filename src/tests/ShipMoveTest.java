package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.jupiter.api.Test;
import code.Ship;

class ShipMoveTest {
	
	Ship ship;
	
	@Test
	public void testMoveNorth()//make sure it moved up
	{
		ship = Ship.getInstance();
		Point startloc = ship.getLocation();
		ship.goNorth();
		assertTrue(ship.getLocation().y == startloc.y-1);
	}
	
	public void testMoveSouth()//make sure it moved down
	{
		ship = Ship.getInstance();
		Point startloc = ship.getLocation();
		ship.goSouth();
		assertTrue(ship.getLocation().y == startloc.y+1);
	}
	
	public void testMoveEast()//make sure it moved right
	{
		ship = Ship.getInstance();
		Point startloc = ship.getLocation();
		ship.goEast();
		assertTrue(ship.getLocation().x == startloc.x+1);
	}
	
	public void testMoveWest()//make sure it moved left
	{
		ship = Ship.getInstance();
		Point startloc = ship.getLocation();
		ship.goWest();
		assertTrue(ship.getLocation().x == startloc.x-1);
	}
}
