package code;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class PirateShip implements Observer, NSMoving, EWMoving
{
	private Map map;
	private Point currentLocation;

	public PirateShip() //needs to be public for Junit tests
	{
		map = Map.getInstance();
		currentLocation = createLocation();
	}

	@Override
	public void update(Observable o, Object args)
	{
		if(o instanceof Ship)
		{
			Ship ship = (Ship)o;

			//program locks itself to only completely the first action it was told to do
			if(ship.getLocation().x > currentLocation.x)//if for horizontal movement. permits diagonal
				//for some reason, segmenting this single method in more brackets makes the whole thing work.
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
					this.goEast();
			
			if(ship.getLocation().x < currentLocation.x)
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
					this.goWest();
			
			if(ship.getLocation().y < currentLocation.y)//an if for all vertical movement. permits diagonal
				if (map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
					this.goNorth();
					
			if(ship.getLocation().y > currentLocation.y)
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
	}

	@Override
	public void goSouth()
	{
		map.setPoint(currentLocation.x, currentLocation.y, 0); //^^
		map.setPoint(currentLocation.x, currentLocation.y + 1, 2);
		currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
		
	}

	private Point createLocation()
	{
		Random rand = new Random();
		int x = rand.nextInt(Explorer.getDimensions());
		int y = rand.nextInt(Explorer.getDimensions());

		while(Map.getInstance().checkLocation(x, y) != 0)
		{
			x = rand.nextInt(Explorer.getDimensions());
			y = rand.nextInt(Explorer.getDimensions());
		}

		return new Point(x, y);
	}


	public Point getLocation() {return currentLocation;}
}