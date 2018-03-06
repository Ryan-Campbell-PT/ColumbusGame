import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer, NSMoving, EWMoving
{
	private Map map;
	private Point currentLocation;

	PirateShip(Ship ship)
	{
		ship.addObserver(this);
		map = Map.getInstance();
		currentLocation = map.initPirate();
	}

	@Override
	public void update(Observable o, Object args)
	{
		if(o instanceof Ship)
		{
			Ship ship = (Ship)o;

			//program locks itself to only cpompleting the first action it was told to do
			if(ship.getLocation().x > currentLocation.x)//if for horizontal movement. permits diagona
				//for some reason, segmenting this single method in more brackets makes the whole thing work.
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
					this.goEast();
			
			else if(ship.getLocation().x < currentLocation.x)
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
					this.goWest();
			
			if(ship.getLocation().y < currentLocation.y)//an if for all vertical movement. permits diagonal
				if (map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
					this.goNorth();
					
			else if(ship.getLocation().y > currentLocation.y)
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
					this.goSouth();
		}
	}

	@Override
	public void goWest()
	{
		map.setPoint(currentLocation.x, currentLocation.y, 0); //set the point its currently at to untouched
		map.setPoint(currentLocation.x - 1, currentLocation.y, 2); //set the going to point to an enemy location
		currentLocation.setLocation(currentLocation.x - 1, currentLocation.y); //update the GUI location of the ship
	}

	@Override
	public void goEast()
	{
		map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
		map.setPoint(currentLocation.x + 1, currentLocation.y, 2);
		currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
	}

	@Override
	public void goNorth()
	{
		map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
		map.setPoint(currentLocation.x, currentLocation.y - 1, 2);
		currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
		System.out.println("Location set north");
	}

	@Override
	public void goSouth()
	{
		map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
		map.setPoint(currentLocation.x, currentLocation.y + 1, 2);
		currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
		
	}
	
	public Point getLocation() {return currentLocation;}
}