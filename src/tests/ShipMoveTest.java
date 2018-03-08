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
<<<<<<< HEAD
		e = new Explorer();
		//m = new Map();
		Ship ship = e.ship;
		e.start(oceanStage);
=======
		ship = Ship.getInstance();
>>>>>>> branch 'master' of https://github.com/Ryan-Campbell-PT/ColumbusGame.git
		Point startloc = ship.getLocation();
		ship.goNorth();
		assertTrue(ship.getLocation().y == startloc.y-1);
	}
	
	/*public void testMoveSouth()//make sure it moved down
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
	}*/
}
