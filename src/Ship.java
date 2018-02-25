
import java.awt.Point;
import java.util.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Ship extends Observable{

	Map oceanMap;
	Point currentLocation;
	ImageView shipImageView;
	AnchorPane ap;
	int scalefactor = 50;
	Observable o;
	//OceanExplorer oe;
	
	public Ship(Map map)
	{
		//randle = new Random();
		//map.shipLocation.setLocation(map.shipLocation.x = randle.nextInt(10), map.shipLocation.y = randle.nextInt(10));
		currentLocation = map.initShip();
	}
	
	public void loadShipImage()
	{
		Image shipImage = new Image("images\\ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x*scalefactor);
		shipImageView.setY(currentLocation.y*scalefactor);
		ap.getChildren().add(shipImageView);
	}
	
	public void goNorth()
	{
		if(currentLocation.y >= 1)
		{
			Point myPoint = getShipLocation();
			try
			{
				if(myPoint.y == 0) throw new NullPointerException(); 
					myPoint.setLocation(myPoint.getX(), myPoint.getY()+1);
					currentLocation = myPoint;
			}
			catch (NullPointerException e)
			{
				System.out.println("can not go up!");
			}
		}
		loadShipImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goSouth()
	{
		if(currentLocation.y <= 9)
		{
			Point myPoint = getShipLocation();
			try
			{
				if(myPoint.y == 9) throw new NullPointerException(); 
					myPoint.setLocation(myPoint.getX(), myPoint.getY()-1);
					currentLocation = myPoint;
			}
			catch (NullPointerException e)
			{
				System.out.println("can not go down!");
			}
		}
		loadShipImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goEast()
	{
		if(currentLocation.x <= 9)
		{
			Point myPoint = getShipLocation();
			try
			{
				if(myPoint.y == 0) throw new NullPointerException();
				{
					myPoint.setLocation(myPoint.getX()+1, myPoint.getY());
					currentLocation = myPoint;
				}
			}
			catch (NullPointerException e)
			{
				System.out.println("can not go right!");
			}
		}
		loadShipImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goWest()
	{
		if(currentLocation.x >= 1)
		{
			Point myPoint = getShipLocation();
			try
			{
				if(myPoint.x == 0) throw new NullPointerException(); 
					myPoint.setLocation(myPoint.getX() -1, myPoint.getY());
					currentLocation = myPoint;
			}
			catch (NullPointerException e)
			{
				System.out.println("can not go left!");
			}
		}
		loadShipImage();
		o.notifyObservers(currentLocation);
	}
	
	public Point getShipLocation()
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