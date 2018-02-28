import java.awt.Point;
import java.io.File;
import java.util.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Ship extends Observable implements NSMoving, EWMoving{

	Point currentLocation;
	private AnchorPane ap;
	private Observable o;

	//TODO: ship images dont disappear as moving
	//TODO: Vertical movement is reversed
	Ship(Map map, AnchorPane ap)
	{
		currentLocation = map.initShip();
		this.ap = ap;
	}
	
	public void loadImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), 50, 50, true, true);
		ImageView shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x * 50); //scale factor
		shipImageView.setY(currentLocation.y * 50);
		ap.getChildren().add(shipImageView);
	}
	
	public void goNorth()
	{
		if(currentLocation.y >= 1)
		{
			Point myPoint = getLocation();
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
		loadImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goSouth()
	{
		if(currentLocation.y <= 9)
		{
			Point myPoint = getLocation();
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
		loadImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goEast()
	{
		if(currentLocation.x <= 9)
		{
			Point myPoint = getLocation();
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
		loadImage();
		o.notifyObservers(currentLocation);
	}
	
	public void goWest()
	{
		if(currentLocation.x >= 1)
		{
			Point myPoint = getLocation();
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
		loadImage();
		o.notifyObservers(currentLocation);
	}
	
	public Point getLocation()
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