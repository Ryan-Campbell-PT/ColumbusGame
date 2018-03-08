package code;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Snake implements Observer, NSMoving
{
	private Map map;
	private Point currentLocation;
	private int randy, moveTime;
	private boolean n, s;
	
	public Snake()
	{
		map = Map.getInstance();
		currentLocation = createLocation();
		randy = new Random().nextInt(3);
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
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y-1, 2);
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
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
					map.setPoint(getCurrentLocation().x, getCurrentLocation().y+1, 2);
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
				if(s==true)
					this.goSouth();
			
				else if(n==true)
					this.goNorth();
			}
		}		
	}
}
