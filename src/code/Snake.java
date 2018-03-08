package code;

import java.awt.Point;
import java.util.Random;

public class Snake implements NSMoving
{
	private Map map;
	private Point currentLocation;
	private int randy, moveTime;
	private boolean n, s;
	
	Snake()
	{
		randy = new Random().nextInt(10);
		moveTime= 0;
		n = true;
		s = false;
	}
	
	@Override
	public Point getCurrentLocation() {return currentLocation;}

	@Override
	public void goNorth()
	{
		if(n == true && moveTime == randy)
		{
			if(currentLocation.y > 0)
			{
				if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0)
				{
					currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
					moveTime=0;
				}
			}
			else
			{
				n = false;
				s = true;
				goSouth();
			}
		}
	}

	@Override
	public void goSouth()
	{
		if(s == true && moveTime == randy)
		{
			if(currentLocation.y < Explorer.getDimensions() - 1)
			{
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				{
					currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
					moveTime=0;
				}
			}
			else
			{
				s = false;
				n = true;
				goNorth();	
			}
		}
	}
}
