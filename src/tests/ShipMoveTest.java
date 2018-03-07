package tests;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.junit.Test;

import code.Explorer;
import code.Map;
//import org.junit.jupiter.api.Test;
import code.Ship;

public class ShipMoveTest {
	
	//Ship ship;
	Explorer e;
	Map m;
	@Test
	public void testMoveNorth()//make sure it moved up
	{
		e = new Explorer();
		m = new Map();
		e.start();
		Ship ship = new Ship();
		Point startloc = ship.getLocation();
		ship.goNorth();
		assertTrue(ship.getLocation().y == startloc.y-1);
	}
	
	/*public void testMoveSouth()//make sure it moved down
	{
		ship = new Ship();
		Point startloc = ship.getLocation();
		ship.goSouth();
		assertTrue(ship.getLocation().y == startloc.y+1);
	}
	
	public void testMoveEast()//make sure it moved right
	{
		ship = new Ship();
		Point startloc = ship.getLocation();
		ship.goEast();
		assertTrue(ship.getLocation().x == startloc.x+1);
	}
	
	public void testMoveWest()//make sure it moved left
	{
		ship = new Ship();
		Point startloc = ship.getLocation();
		ship.goWest();
		assertTrue(ship.getLocation().x == startloc.x-1);
	}*/
}
