package code;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class PirateShip extends IPirateShip
{
	//TODO: Implement pirate ship factory

	public PirateShip() //needs to be public for Junit tests
	{
		setCurrentLocation(createLocation());
	}

	@Override
	public void update(Observable o, Object args)
	{
		if(o instanceof Ship)
		{
			Ship ship = (Ship)o;

			//program locks itself to only completely the first action it was told to do
			if(ship.getCurrentLocation().x > getCurrentLocation().x)//if for horizontal movement. permits diagonal
				//for some reason, segmenting this single method in more brackets makes the whole thing work.
				if(getMap().checkLocation(getCurrentLocation().x + 1, getCurrentLocation().y) == 0)
					this.goEast();
			
			if(ship.getCurrentLocation().x < getCurrentLocation().x)
				if(getMap().checkLocation(getCurrentLocation().x - 1, getCurrentLocation().y) == 0)
					this.goWest();
			
			if(ship.getCurrentLocation().y < getCurrentLocation().y)//an if for all vertical movement. permits diagonal
				if (getMap().checkLocation(getCurrentLocation().x, getCurrentLocation().y - 1) == 0) //open space
					this.goNorth();
					
			if(ship.getCurrentLocation().y > getCurrentLocation().y)
				if(getMap().checkLocation(getCurrentLocation().x, getCurrentLocation().y + 1) == 0)
					this.goSouth();
		}
	}

	@Override
	public void goWest()
	{
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //set the point its currently at to untouched
		getMap().setPoint(getCurrentLocation().x - 1, getCurrentLocation().y, 2); //set the going to point to an enemy location
		getCurrentLocation().setLocation(getCurrentLocation().x - 1, getCurrentLocation().y); //update the GUI location of the ship
	}

	@Override
	public void goEast()
	{
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
		getMap().setPoint(getCurrentLocation().x + 1, getCurrentLocation().y, 2);
		getCurrentLocation().setLocation(getCurrentLocation().x + 1, getCurrentLocation().y);
	}

	@Override
	public void goNorth()
	{
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y - 1, 2);
		getCurrentLocation().setLocation(getCurrentLocation().x, getCurrentLocation().y - 1);
	}

	@Override
	public void goSouth()
	{
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
		getMap().setPoint(getCurrentLocation().x, getCurrentLocation().y + 1, 2);
		getCurrentLocation().setLocation(getCurrentLocation().x, getCurrentLocation().y + 1);
		
	}
}