package code;
import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import javafx.scene.input.KeyCode;

public class Ship extends Observable implements NSMoving, EWMoving
{
	private Point currentLocation;
	private Map map;
	private static Ship instance;

	public static Ship getInstance() //the players ship makes most sense to have as singleton
	{
		if(instance == null)
			instance = new Ship();

		return instance;
	}

	private Ship() //needs to be public for the Junit tests
	{
		map = Map.getInstance();
		currentLocation = createLocation();
	}

	public void goDirection(KeyCode event)
	{
		switch(event)
		{
			case RIGHT:
				goEast();
				break;
			case LEFT:
				goWest();
				break;
			case UP:
				goNorth();
				break;
			case DOWN:
				goSouth();
				break;
			default:
				break;
		}
	}

	//this is necessary for whatever reason
	@Override
	public void notifyObservers()
	{
		setChanged();
		super.notifyObservers();
	}

	//TODO: We could likely turn these into a single method
	public void goNorth()
	{
		if(currentLocation.y > 0)
			if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
		this.notifyObservers();
	}

	public void goSouth()
	{
		if(currentLocation.y < Explorer.getDimensions() - 1)
			if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);

		this.notifyObservers();
	}

	public void goEast()
	{
		if(currentLocation.x < Explorer.getDimensions() - 1)
			if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);

		this.notifyObservers();
	}

	public void goWest()
	{
		if(currentLocation.x > 0)
			if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);

		this.notifyObservers();
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

	public Point getLocation() { return currentLocation; }
}