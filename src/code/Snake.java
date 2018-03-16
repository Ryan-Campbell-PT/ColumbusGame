package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Snake implements Observer, NSMoving
{
	private Map map;
	private Point currentLocation;
	private int randy, moveTime;
	private boolean n, s;
	private ImageView imageView;

	Snake()
	{
		map = Map.getInstance();
		currentLocation = createLocation();
		randy = new Random().nextInt(3);
		moveTime= 0;
		n = true;
		s = false;
		imageView = createImageView();
		Ship.getInstance().addObserver(this);
	}

	@Override
	public ImageView createImageView()
	{
		Image snakeImage = new Image(new File("images\\snakeboi.jpg").toURI().toString(), Explorer.getScaleFactor(), Explorer.getScaleFactor(), true, true);
		ImageView imageView = new ImageView(snakeImage);
		imageView.setX(this.getCurrentLocation().x * Explorer.getScaleFactor());
		imageView.setY(this.getCurrentLocation().y * Explorer.getScaleFactor());
		Explorer.getAp().getChildren().add(imageView);
		return imageView;
	}
	
	@Override
	public Point getCurrentLocation() {return currentLocation;}

	@Override
	public void goNorth()
	{
		if(n && moveTime == randy)
		{
			if(currentLocation.y > 0)
			{
				if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0 || map.checkLocation(currentLocation.x, currentLocation.y - 1) ==5)
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
		if(s && moveTime == randy)
		{
			if(currentLocation.y < Explorer.getDimensions() - 1)
			{
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0 || map.checkLocation(currentLocation.x, currentLocation.y + 1) == 5)
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
				if(s)
					this.goSouth();
				else if(n)
					this.goNorth();
			}
		}		
	}

	public ImageView getImageView() { return imageView; }

	//test specific methods
	public Snake(boolean irrelevant)
	{
		currentLocation = new Point(new Random().nextInt(15), new Random().nextInt(15));
	}

	public void goSouth(boolean irrelevant)
	{
		currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
	}

	public void goNorth(boolean irrelevant)
	{
		currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
	}
	public void goEast(boolean irrelevant)
	{
		currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
	}

	public void goWest(boolean irrelevant)
	{
		currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);
	}
	//end test methods
}
