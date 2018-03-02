
import java.awt.Point;
import java.io.File;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Ship extends Observable{

	private Point currentLocation;
	private AnchorPane ap;
	private Observable o;
	private Map map;

	Ship(Map map, AnchorPane ap)
	{
		this.map = map;
		currentLocation = map.initShip();
		this.ap = ap;
		o = new Observable();
	}
	
	private void loadShipImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), 50, 50, true, true);
		ImageView shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x * 50); //scale factor
		shipImageView.setY(currentLocation.y * 50);
		ap.getChildren().add(shipImageView);
	}
	
	public void goNorth()
	{
		if(currentLocation.y > 0)
			if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);

		o.notifyObservers(currentLocation);
	}
	
	public void goSouth()
	{
		if(currentLocation.y < 9)
			if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);

		o.notifyObservers(currentLocation);
	}
	
	public void goEast()
	{
		if(currentLocation.x < 9)
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
	
	public Point getPlayerShipLocation()
	{
		return currentLocation;
	}
	
	public Point setShipLocation(int x, int y)
	{
		Point myPoint  = new Point();
		myPoint.x = x;
		myPoint.y = y;
		currentLocation = myPoint;
		return currentLocation;
	}
}