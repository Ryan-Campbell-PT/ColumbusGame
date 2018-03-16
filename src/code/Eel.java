package code;
import java.awt.Point;
import java.io.File;
import java.util.*;
import code.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Eel implements EWMoving
{
	private Map map;
	private Point currentLocation;
	//private int randy, moveTime;
	public boolean e, w;
	private ImageView imageView;

	//TODO: Eel will implement the composite pattern, having a random number determine how many eels go in a direction and hog real estate
	public Eel()
	{
		map = Map.getInstance();
		currentLocation = createLocation();
		Map.getInstance().setPoint(currentLocation.x, currentLocation.y, 2);
		//randy = new Random().nextInt(3);
		//moveTime= 0;//I'll most likely end up using a timer
		e = true;
		w = false;
		imageView = createImageView();
	}

	@Override
	public ImageView createImageView()
	{
		Image eelImage = new Image(new File("images\\eelboi.jpg").toURI().toString(), Explorer.getScaleFactor(), Explorer.getScaleFactor(), true, true);
		ImageView eelImageView = new ImageView(eelImage);
		eelImageView.setX(this.getCurrentLocation().x * Explorer.getScaleFactor());
		eelImageView.setY(this.getCurrentLocation().y * Explorer.getScaleFactor());
		Explorer.getAp().getChildren().add(eelImageView);
		return eelImageView;
	}

	@Override
	public Point getCurrentLocation() {return currentLocation;}

	@Override
	public void goWest()
	{
		map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
		map.setPoint(getCurrentLocation().x - 1, getCurrentLocation().y, 2);
		getCurrentLocation().setLocation(currentLocation.x - 1, currentLocation.y);
	}

	@Override
	public void goEast()
	{
		map.setPoint(getCurrentLocation().x, getCurrentLocation().y, 0); //^^
		map.setPoint(getCurrentLocation().x + 1, getCurrentLocation().y, 2);
		getCurrentLocation().setLocation(getCurrentLocation().x + 1, getCurrentLocation().y);
	}
	
	public Point createLocation()
	{
		Random random = new Random();
		int x = random.nextInt(Explorer.getDimensions());
		int y = random.nextInt(Explorer.getDimensions());

		while(Map.getInstance().checkLocation(x, y) != 0)
		{
			x = random.nextInt(Explorer.getDimensions());
			y = random.nextInt(Explorer.getDimensions());
		}

		return new Point(x, y);
	}

	/*@Override
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
	}*/

	public ImageView getImageView() { return imageView; }
}