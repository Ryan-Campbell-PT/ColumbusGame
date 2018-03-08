package code;

import java.awt.Point;
import java.util.*;

import code.Map;

public class Eel implements Observer, EWMoving
{
	private Map map;
	private Point currentLocation;
	private int randy, moveTime;
	private boolean e, w;
	
	public Eel()
	{
		map = Map.getInstance();
		currentLocation = createLocation();
		randy = new Random().nextInt(3);
		moveTime= 0;//I'll most likely end up using a timer
		e = true;
		w = false;
	}
	
	@Override
	public Point getCurrentLocation() {return currentLocation;}

	@Override
	public void goWest()
	{
		if(w == true && moveTime == randy)
		{
			if(currentLocation.x > 0)
			{
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
				{
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
					map.setPoint(getCurrentLocation().x - 1, getCurrentLocation().y, 2);
					getCurrentLocation().setLocation(currentLocation.x - 1, currentLocation.y);
					moveTime=0;
				}
			}
			else
			{
				w = false;
				e = true;
				goEast();
			}
		}
	}

	@Override
	public void goEast()
	{
		if(e == true && moveTime == randy)
		{
			if(currentLocation.x < Explorer.getDimensions() - 1)
			{
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
				{
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
					map.setPoint(getCurrentLocation().x + 1, getCurrentLocation().y, 2);
					getCurrentLocation().setLocation(getCurrentLocation().x + 1, getCurrentLocation().y);
					moveTime=0;
				}
			}
			else
			{
				e = false;
				w = true;
				goWest();
			}
			
		}
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

	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Ship)
		{
			if(moveTime<randy)
				moveTime++;
			else
			{
				if(e==true)
					this.goEast();
			
				else if(w==true)
					this.goWest();
			}
		}
	}
}