import java.awt.Point;
import java.io.File;
import java.util.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Ship extends Observable //implements NSMoving, EWMoving{
{
	private Point currentLocation;
	private AnchorPane ap;
	private Observable o;
	private Map map;

	Ship(AnchorPane ap)
	{
		map = Map.getInstance();
		currentLocation = map.initShip();
		this.ap = ap;
		o = new Observable();
	}

	public void loadImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), 50, 50, true, true);
		ImageView shipImageView = new ImageView(shipImage);
		shipImageView.setX(currentLocation.x * 50); //scale factor
		shipImageView.setY(currentLocation.y * 50);
		ap.getChildren().add(shipImageView);
	}

	public void goDirection(KeyCode event)
	{
		switch(event)
		{
			case RIGHT:
				goEast();
				break;
			case LEFT:
				goWest();
				break;
			case UP:
				goNorth();
				break;
			case DOWN:
				goSouth();
				break;
			default:
				break;
		}
	}

	//TODO: We could likely turn these into a single method
	private void goNorth()
	{
		if(currentLocation.y > 0)
			if(map.checkLocation(currentLocation.x, currentLocation.y - 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);

		o.notifyObservers(currentLocation);
	}

	private void goSouth()
	{
		if(currentLocation.y < 9)
			if(map.checkLocation(currentLocation.x, currentLocation.y + 1) == 0)
				currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);

		o.notifyObservers(currentLocation);
	}

	private void goEast()
	{
		if(currentLocation.x < 9)
			if(map.checkLocation(currentLocation.x + 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);

		o.notifyObservers(currentLocation);
	}

	private void goWest()
	{
		if(currentLocation.x > 0)
			if(map.checkLocation(currentLocation.x - 1, currentLocation.y) == 0)
				currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);

		o.notifyObservers(currentLocation);
	}

	public Point getPlayerShipLocation() { return currentLocation; }
}