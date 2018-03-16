package code;
import java.awt.Point;
import java.io.File;
import java.util.*;
import code.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Eel implements EWMoving
{
	Point currentLocation;
	ImageView imageView;
	boolean east, west;

	Eel(int x, int y)
	{
		currentLocation = new Point(x, y);
		imageView = createImageView();
		Map.getInstance().setPoint(x, y, 3);

		if(Map.getInstance().checkLocation(x + 1, y) == 0 ||
				Map.getInstance().checkLocation(x + 1, y) == 3)
			east = true;
		else
			east = false;

		if(Map.getInstance().checkLocation(x - 1, y) == 0 || //empty
				Map.getInstance().checkLocation(x - 1, y) == 3) //or another eel
			west = true;
		else
			west = false;
	}

	@Override
	public Point getCurrentLocation() { return currentLocation; }

	public ImageView getImageView() { return imageView; }

	@Override
	public ImageView createImageView()
	{
		Image snakeImage = new Image(new File("images\\eelboi.jpg").toURI().toString(), Explorer.getScaleFactor(), Explorer.getScaleFactor(), true, true);
		ImageView imageView = new ImageView(snakeImage);
		imageView.setX(this.getCurrentLocation().x * Explorer.getScaleFactor());
		imageView.setY(this.getCurrentLocation().y * Explorer.getScaleFactor());
		Explorer.getAp().getChildren().add(imageView);
		return imageView;
	}

	@Override
	public void goWest()
	{
		Map.getInstance().setPoint(currentLocation.x, currentLocation.y, 0);
		Map.getInstance().setPoint(currentLocation.x - 1, currentLocation.y, 3);
		currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);

		if(Map.getInstance().checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
			west = true;
		else
			west = false;
	}

	@Override
	public void goEast()
	{
		Map.getInstance().setPoint(currentLocation.x, currentLocation.y, 0);
		Map.getInstance().setPoint(currentLocation.x + 1, currentLocation.y, 3);
		currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);

		if(Map.getInstance().checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
			east = true;
		else
			east = false;
	}

	boolean canGoEast() { return east; }
	boolean canGoWest() { return west; }
}