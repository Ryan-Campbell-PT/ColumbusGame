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

public class Explorer extends Application {

	//I made these static variables so we can have one instance of all necessary statistics
	//and not need to pass them into functions and classes throughout the project
	private static AnchorPane ap;
	private final static int dimensions = 15;
	private final static int scaleFactor = 50;
	private Ship ship;
	private Scene scene;
	private ImageView shipImageView;

	private void drawGrid()
	{
		for(int i = 0; i < dimensions; i++)
			for(int j = 0; j < dimensions; j++)
				drawRectangle(i, j, Color.PALETURQUOISE);
	}

	private void drawRectangle(int x, int y, Color color)
	{
		Rectangle rect = new Rectangle(x * scaleFactor, y * scaleFactor, scaleFactor, scaleFactor);
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
			shipImageView.setX(ship.getLocation().x * scaleFactor);
			shipImageView.setY(ship.getLocation().y * scaleFactor);
		});
	}

	// we can definitely reduce these methods to a single method. Lets keep that in mind while we code
	private void loadShipImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getLocation().x * scaleFactor);
		shipImageView.setY(ship.getLocation().y * scaleFactor);
		ap.getChildren().add(shipImageView);
	}

	private void loadPirateImage(String url, PirateShip pirate)
	{
		Image shipImage = new Image(url, 50, 50, true, true);
		ImageView ImageView = new ImageView(shipImage);
		ImageView.setX(pirate.getLocation().x * scaleFactor);
		ImageView.setY(pirate.getLocation().y * scaleFactor);
		ap.getChildren().add(ImageView);
	}


	@Override
	public void start(Stage oceanStage) throws Exception
    {
        //TODO: we could probably turn this all into a single method. Can be something we do towards the end
        //map creation
        ap = new AnchorPane();
		Map.initiate(getDimensions(), getIslandCount(), getAp());
		drawGrid();
		Map.getInstance().placeIslands();
		Map.getInstance().placeTreasure();

		ship = new Ship();
		PirateShip pirate1 = new PirateShip(ship);
		PirateShip pirate2 = new PirateShip(ship);

		loadShipImage(); //ship
		loadPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate1); //pirate image 1
		loadPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate2); //pirate image 2
		
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
		scene = new Scene(ap, 750, 750);
		oceanStage.setTitle("Chrissy Columbus");
		oceanStage.setScene(scene);
		oceanStage.show();
		weighAnchor();//if you comment this out you can see the map all the other work. The event handler is the only problem.
	}


	public static AnchorPane getAp() { return ap; }
	public static int getDimensions() { return dimensions; }
	public static int getIslandCount() { return dimensions / 2; }
	public static int getScaleFactor() { return scaleFactor; }
}