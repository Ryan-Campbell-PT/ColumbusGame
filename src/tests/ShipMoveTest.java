package tests;

//import static org.junit.Assert.*;
//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import code.Explorer;
import code.Ship;
import javafx.stage.Stage;
import java.awt.Point;

public class ShipMoveTest {
	
	Ship ship;
	Explorer e;
	//Map m;
	Stage oceanStage;
	@Test
	public void testMoveNorth()//make sure it moved up
	{
		e = new Explorer();
		//m = new Map();
		ship = Ship.getInstance();
		e.start(oceanStage);
		Point startloc = ship.getCurrentLocation();
		ship.goNorth();
		assertTrue(ship.getCurrentLocation().y == startloc.y-1);
	}
	
	public void testMoveSouth()//make sure it moved down
	{
		ship = Ship.getInstance();
		Point startloc = ship.getCurrentLocation();
		ship.goSouth();
		assertTrue(ship.getCurrentLocation().y == startloc.y+1);
	}
	
	public void testMoveEast()//make sure it moved right
	{
		ship = Ship.getInstance();
		Point startloc = ship.getCurrentLocation();
		ship.goEast();
		assertTrue(ship.getCurrentLocation().x == startloc.x+1);
	}
	
	public void testMoveWest()//make sure it moved left
	{
		ship = Ship.getInstance();
		Point startloc = ship.getCurrentLocation();
		ship.goWest();
		assertTrue(ship.getCurrentLocation().x == startloc.x-1);
	}
}
