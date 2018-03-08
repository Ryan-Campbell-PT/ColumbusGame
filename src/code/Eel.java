package code;

import java.awt.Point;
import java.util.*;

public class Eel implements EWMoving
{
	private Map map;
	private Point currentLocation;
	private int randy, moveTime;
	private boolean e, w;
	
	private Eel()
	{
		randy = new Random().nextInt(10);
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
					currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);
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
					currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
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
}
