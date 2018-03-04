import java.awt.Point;
import java.util.Observable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class Ship extends Observable implements NSMoving, EWMoving
{
	private Point currentLocation;
	private Observable o;
	private Map map;

	Ship(AnchorPane ap)
	{
		map = Map.getInstance();
		currentLocation = map.initShip();
		o = new Observable();
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

	//TODO: We could likely turn these into a single method
	public void goNorth()
	{
		if(currentLocation.y > 0)
			if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);

		o.notifyObservers(currentLocation);
	}

	public void goSouth()
	{
		if(currentLocation.y < Explorer.getDimensions() - 1)
			if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);

		o.notifyObservers(currentLocation);
	}

	public void goEast()
	{
		if(currentLocation.x < Explorer.getDimensions() - 1)
			if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);

		o.notifyObservers(currentLocation);
	}

	public void goWest()
	{
		if(currentLocation.x > 0)
			if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);

		o.notifyObservers(currentLocation);
	}

	public Point getLocation() { return currentLocation; }
}