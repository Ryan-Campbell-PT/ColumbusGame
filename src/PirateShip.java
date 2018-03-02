import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PirateShip implements Observer {

	private Map map;
	private Point currentLocation;
	private AnchorPane anchorPane;
	private Ship playerShip;

	PirateShip(Observable o, Map om)
	{
		o.addObserver(this);
		map = om;
		currentLocation = om.initPirate();
	}

	private void loadPirateImage()
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
			//TODO: some of these directions may be incorrect. Check them to be sure
			playerShip = (Ship)o;
			if(playerShip.getPlayerShipLocation().getY() < currentLocation.getY())
				if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0) //open space
					followNorth();
			else if(playerShip.getPlayerShipLocation().getY() > currentLocation.getY())
				if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
					followSouth();
			else if(playerShip.getPlayerShipLocation().getX() > currentLocation.getX())
				if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
					followEast();
			else if(playerShip.getPlayerShipLocation().getX() < currentLocation.getX())
				if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
					followWest();
		}
	}

	//TODO: These may not work just yet
	//TODO: Check the location on the map if its not taken, set the pirateShips current location on the map to
	//to 0 (to indicate nothing is there, then set the newly moved location to 2 (to indicate enemy)
	private void followNorth()
	{
		/*Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()+1);
		currentLocation = myPoint;
		loadPirateImage();
		*/
		currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
	}

	private void followSouth()
	{
	/*	Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX(), myPoint.getY()-1);
		currentLocation = myPoint;
		loadPirateImage();
*/
		currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
	}
	
	private void followEast()
	{
	/*	Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX()+1, myPoint.getY());
		currentLocation = myPoint;
		loadPirateImage();
*/
		currentLocation.setLocation(currentLocation.x + 1, currentLocation.y + 1);
	}
	
	private void followWest()
	{
		/*Point myPoint = getShipLocation();
		myPoint.setLocation(myPoint.getX()-1, myPoint.getY());
		currentLocation = myPoint;
		loadPirateImage();
*/
		currentLocation.setLocation(currentLocation.x - 1, currentLocation.y + 1);
	}
	
	public Point getPirateShipLocation()
	{
		return currentLocation;
	}
}