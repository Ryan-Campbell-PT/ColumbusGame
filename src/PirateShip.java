import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PirateShip implements Observer {

	Ship ship;
	Map om;
	Point currentLocation;
	ImageView shipImageView;
	int scalefactor = 50;
	AnchorPane ache;
	
	public PirateShip(Observable o, Map om)
	{
		o.addObserver(this);
		currentLocation = om.initPirate();
	}

	public void loadPirateImage()
	{
		Image shipImage = new Image("images\\pirateShip.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x*scalefactor);
		shipImageView.setY(currentLocation.y*scalefactor);
		ache.getChildren().add(shipImageView);
	}
	
	@Override
	public void update(Observable o, Object args)
	{
		if (o instanceof Ship)
		{
			Ship ship = (Ship)o;
			Point destiny = ship.currentLocation;
			if(destiny.getY() < currentLocation.getY())
				if(om.seaMap[currentLocation.x][currentLocation.y-1] == false)
					followNorth();
			if(destiny.getY() > currentLocation.getY())
				if(om.seaMap[currentLocation.x][currentLocation.y+1] == false)
					followSouth();
			if(destiny.getX() > currentLocation.getX())
				if(om.seaMap[currentLocation.x+1][currentLocation.y] == false)
					followEast();
			if(destiny.getX() < currentLocation.getX())
				if(om.seaMap[currentLocation.x-1][currentLocation.y] == false)
					followWest();
		}
	}

	/*public void follow(Point shipspot)
	{
		Point mypoint = new Point();
		ship = new Ship(om);
		if(shipspot.getX() < )
	}*/

	public void followNorth()
	{
		Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()+1);
		currentLocation = myPoint;
		loadPirateImage();
	}

	public void followSouth()
	{
		Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()-1);
		currentLocation = myPoint;
		loadPirateImage();
	}
	
	public void followEast()
	{
		Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX()+1, myPoint.getY());
		currentLocation = myPoint;
		loadPirateImage();
	}
	
	public void followWest()
	{
		Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX()-1, myPoint.getY());
		currentLocation = myPoint;
		loadPirateImage();
	}
	
	public Point getShipLocation()
	{
		return currentLocation;
	}
}