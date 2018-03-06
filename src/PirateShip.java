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
			System.out.println("Updating Pirate");
			
			if(((Ship)o).getLocation().y < currentLocation.y)
				if (map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
				{
					System.out.println("Going North");
					this.goNorth();
					System.out.println("Went North");
				}
					
			else if(((Ship)o).getLocation().y > currentLocation.y)
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				{
					System.out.println("Going South");
					this.goSouth();
				}

			else if(((Ship)o).getLocation().x > currentLocation.x)
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
				{
					
					this.goEast();
				}

			else if(((Ship)o).getLocation().x < currentLocation.x)
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
				{
					System.out.println("Going West");
					this.goWest();
				}
		}
	}

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