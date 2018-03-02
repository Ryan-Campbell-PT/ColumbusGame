import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PirateShip implements Observer//, NSMoving, EWMoving {
{
	private Map map;
	private Point currentLocation;
	private AnchorPane anchorPane;
	//private Ship playerShip;

	PirateShip(Observable o)
	{
		o.addObserver(this);
		map = Map.getInstance();
		currentLocation = map.initPirate();
	}

	@Override
	public void update(Observable o, Object args)
	{
		if (o instanceof Ship)
		{
			//TODO: some of these directions may be incorrect. Check them to be sure
			Ship playerShip = (Ship)o;
			if(playerShip.getPlayerShipLocation().getY() < currentLocation.getY())
				if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
					followNorth();
			else if(playerShip.getPlayerShipLocation().getY() > currentLocation.getY())
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
					followSouth();
			else if(playerShip.getPlayerShipLocation().getX() > currentLocation.getX())
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
					followEast();
			else if(playerShip.getPlayerShipLocation().getX() < currentLocation.getX())
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
					followWest();
		}
	}

	//TODO: These functions could definitely be turned into a single function, may want to do in the future
	private void followNorth()
	{
		//set the current location to 0 (to indicate its no longer being used)
		map.setPoint(currentLocation.x, currentLocation.y, 0);
		//set the going to location to enemy
		map.setPoint(currentLocation.x, currentLocation.y - 1, 2);
		//change the ships location
		currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
	}

	private void followSouth()
	{
		//^^
		map.setPoint(currentLocation.x, currentLocation.y, 0);
		map.setPoint(currentLocation.x, currentLocation.y + 1, 2);
		currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
	}
	
	private void followEast()
	{
		//^^
		map.setPoint(currentLocation.x, currentLocation.y, 0);
		map.setPoint(currentLocation.x + 1, currentLocation.y, 2);
		currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
	}
	
	private void followWest()
	{
		//^^
		map.setPoint(currentLocation.x, currentLocation.y, 0);
		map.setPoint(currentLocation.x - 1, currentLocation.y, 2);
		currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);
	}
	
	public Point getPirateShipLocation()
	{
		return currentLocation;
	}
}