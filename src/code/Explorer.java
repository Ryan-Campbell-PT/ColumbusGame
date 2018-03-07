package code;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class Explorer extends Application {

	//I made these static variables so we can have one instance of all necessary statistics
	//and not need to pass them into functions and classes throughout the project
	private static AnchorPane ap;
	private Ship ship;
	private PirateShip pirate1;
	private PirateShip pirate2;
	private Scene scene;
	private ImageView shipImageView;
	private ArrayList<PirateShip> pirates;
	//only one pirate moves. if we iterate this way it may work.
	//it could also consolidate a big chunck of code here.

	private void drawGrid()
	{
		for(int i = 0; i < getDimensions(); i++)
			for(int j = 0; j < getDimensions(); j++)
				drawRectangle(i, j, Color.PALETURQUOISE);
	}

	private void drawRectangle(int x, int y, Color color)
	{
		Rectangle rect = new Rectangle(x * getScaleFactor(), y * getScaleFactor(), getScaleFactor(), getScaleFactor());
		rect.setStroke(Color.BLACK);
		rect.setFill(color);
		ap.getChildren().add(rect);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
	
	private void weighAnchor()
	{
		//this is essentially the same code as before, except it doesnt have
		//the handle() function
		scene.setOnKeyPressed((KeyEvent event)->
		{
			//ship will handle which location to go
			ship.goDirection(event.getCode());
			shipImageView.setX(ship.getLocation().x * getScaleFactor());
			shipImageView.setY(ship.getLocation().y * getScaleFactor());
			// here we can implement the arraylist. Simple for loop and what not

			//fancy little for each loop right here
			for(PirateShip ship : pirates)
			{
				ship.getImageView().setX(ship.getLocation().x * getScaleFactor());
				ship.getImageView().setY(ship.getLocation().y * getScaleFactor());
			}
		});
	}

	// we can definitely reduce these methods to a single method. Lets keep that in mind while we code
	private void loadShipImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), getScaleFactor(), getScaleFactor(), true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getLocation().x * getScaleFactor());
		shipImageView.setY(ship.getLocation().y * getScaleFactor());
		getAp().getChildren().add(shipImageView);
	}

	private ImageView createPirateImage(String url, PirateShip pirate)
	{
		Image shipImage = new Image(url, getScaleFactor(), getScaleFactor(), true, true);
		ImageView imageView = new ImageView(shipImage);
		imageView.setX(pirate.getLocation().x * getScaleFactor());
		imageView.setY(pirate.getLocation().y * getScaleFactor());
		getAp().getChildren().add(imageView);

		return imageView;
	}

	@Override
	public void start(Stage oceanStage)
    {
        //TODO: we could probably turn this all into a single method. Can be something we do towards the end
        //create all the necessary objects
        ap = new AnchorPane();
		ship = Ship.getInstance();
		pirate1 = new PirateShip();
		pirate2 = new PirateShip();
		pirates = new ArrayList<>();
		scene = new Scene(getAp(), getDimensions() * getScaleFactor(), getDimensions() * getScaleFactor());
		WhirlpoolFactory factory = new WhirlpoolFactory(ship);

		//draw the map
		drawGrid();
		Map.getInstance().placeIslands();
		Map.getInstance().placeTreasure();

		//load in the images
		loadShipImage(); //ship
		pirate1.addImageView(createPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate1)); //pirate image 1
		pirate2.addImageView(createPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate2)); //pirate image 2

		//add the observers
		ship.addObserver(factory);
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);

		pirates.add(pirate1);
		pirates.add(pirate2);

		//finish the misc stuff
		oceanStage.setTitle("Chrissy Columbus");
		oceanStage.setScene(scene);
		oceanStage.show();
		weighAnchor();
	}


	public static AnchorPane getAp() { return ap; }
	public static int getDimensions() { return 15; } //dimensions without a variable
	public static int getIslandCount() { return getDimensions() / 2; } //island count without a variable
	public static int getScaleFactor() { return 50; }
}