import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PirateShip implements Observer, NSMoving, EWMoving {

	private Map map;
	private Point currentLocation;
	private AnchorPane anchorPane;
	
	PirateShip(Observable o, Map om)
	{
		o.addObserver(this);
		map = om;
		currentLocation = om.initPirate();
	}

	public void loadImage()
	{
		Image shipImage = new Image(new File("images\\pirateShip.png").toURI().toString(), 50, 50, true, true);
		ImageView shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x * 50); //scale factor
		shipImageView.setY(currentLocation.y * 50);

		//anchorPane is never assigned. could cause problems
		anchorPane.getChildren().add(shipImageView);
	}
	
	@Override
	public void update(Observable o, Object args)
	{
		if (o instanceof Ship)
		{
			Ship ship = (Ship)o;
			Point destiny = ship.currentLocation;
			if(destiny.getY() < currentLocation.getY())
				if(!map.seaMap[currentLocation.x][currentLocation.y-1]) //== false
					goNorth();
			if(destiny.getY() > currentLocation.getY())
				if(!map.seaMap[currentLocation.x][currentLocation.y+1])
					goSouth();
			if(destiny.getX() > currentLocation.getX())
				if(!map.seaMap[currentLocation.x+1][currentLocation.y])
					goEast();
			if(destiny.getX() < currentLocation.getX())
				if(!map.seaMap[currentLocation.x-1][currentLocation.y])
					goWest();
		}
	}

	public void goNorth()
	{
		Point myPoint = getLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()+1);
		currentLocation = myPoint;
		loadImage();
	}

	public void goSouth()
	{
		Point myPoint = getLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()-1);
		currentLocation = myPoint;
		loadImage();
	}
	
	public void goEast()
	{
		Point myPoint = getLocation();
		myPoint.setLocation(myPoint.getX()+1, myPoint.getY());
		currentLocation = myPoint;
		loadImage();
	}
	
	public void goWest()
	{
		Point myPoint = getLocation();
		myPoint.setLocation(myPoint.getX()-1, myPoint.getY());
		currentLocation = myPoint;
		loadImage();
	}
	
	public Point getLocation()
	{
		return currentLocation;
	}
}