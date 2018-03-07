package tests;

import static org.junit.Assert.*;
import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import code.Explorer;
import code.Ship;
import javafx.stage.Stage;
import java.awt.Point;

public class ShipMoveTest {
	
	//Ship ship;
	Explorer e;
	//Map m;
	Stage oceanStage;
	@Test
	public void testMoveNorth()//make sure it moved up
	{
		e = new Explorer();
		//m = new Map();
		Ship ship = e.ship;
		e.start(oceanStage);
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
