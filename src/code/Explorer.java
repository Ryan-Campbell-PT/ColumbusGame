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
	private Scene scene;

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
		//TODO: for WHATEVER fucking reason, this makes it work. Play with this
		Ship ship = Ship.getInstance(); //get the ship
		PirateShipFactory pirateShipFactory = PirateShipFactory.getInstance(); //create any ships
		pirateShipFactory.addPirateShip(new FollowPirateShip());
		pirateShipFactory.addPirateShip(new LostPirateShip());
		WhirlpoolFactory whirlpoolFactory = new WhirlpoolFactory(); //no need to do anything with the whirlpool, it handles itself
		Eel eel = new Eel(); //misc creations
		Snake snake = new Snake();

		scene.setOnKeyPressed((KeyEvent event)->
		{
			//ship will handle which location to go
			ship.goDirection(event.getCode());
			ship.getImageView().setX(ship.getCurrentLocation().x * getScaleFactor());
			ship.getImageView().setY(ship.getCurrentLocation().y * getScaleFactor());

			snake.getImageView().setX(snake.getCurrentLocation().x*getScaleFactor());
			snake.getImageView().setY(snake.getCurrentLocation().y*getScaleFactor());

			eel.getImageView().setX(eel.getCurrentLocation().x*getScaleFactor());
			eel.getImageView().setY(eel.getCurrentLocation().y*getScaleFactor());

			//fancy little for each loop right here
			for(IPirateShip ships : PirateShipFactory.getPirateList())
			{
				ships.getImageView().setX(ships.getCurrentLocation().x * getScaleFactor());
				ships.getImageView().setY(ships.getCurrentLocation().y * getScaleFactor());
			}
		});
	}

	@Override
	public void start(Stage oceanStage)
    {
        //create all the necessary objects
        ap = new AnchorPane();
		scene = new Scene(getAp(), getDimensions() * getScaleFactor(), getDimensions() * getScaleFactor());

		//draw the map
		drawGrid();
		Map.getInstance().placeIslands();
		Map.getInstance().placeTreasure();

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