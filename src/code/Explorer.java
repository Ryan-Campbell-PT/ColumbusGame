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
	public Ship ship; //Junit needs this too
	private Snake snake;
	private Eel eel;
	private Scene scene;
	private ImageView shipImageView;
	private ImageView snakeImageView;
	private ImageView eelImageView;
	private FollowPirateShip followPirateShip;
	private LostPirateShip lostPirateShip;
	private ArrayList<IPirateShip> pirateList;

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
			shipImageView.setX(ship.getCurrentLocation().x * getScaleFactor());
			shipImageView.setY(ship.getCurrentLocation().y * getScaleFactor());
			// here we can implement the arraylist. Simple for loop and what not
			followPirateShip.getImageView().setX(followPirateShip.getCurrentLocation().x * getScaleFactor());
			followPirateShip.getImageView().setY(followPirateShip.getCurrentLocation().y * getScaleFactor());

			snakeImageView.setX(snake.getCurrentLocation().x*getScaleFactor());
			snakeImageView.setY(snake.getCurrentLocation().y*getScaleFactor());
			
			eelImageView.setX(eel.getCurrentLocation().x*getScaleFactor());
			eelImageView.setY(eel.getCurrentLocation().y*getScaleFactor());
			//fancy little for each loop right here
			for(IPirateShip ship : pirateList)
			{
				ship.getImageView().setX(ship.getCurrentLocation().x * getScaleFactor());
				ship.getImageView().setY(ship.getCurrentLocation().y * getScaleFactor());
			}
		});
	}

	// we can definitely reduce these methods to a single method. Lets keep that in mind while we code
	private void loadShipImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), getScaleFactor(), getScaleFactor(), true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getCurrentLocation().x * getScaleFactor());
		shipImageView.setY(ship.getCurrentLocation().y * getScaleFactor());
		getAp().getChildren().add(shipImageView);
	}

	private ImageView createPirateImage(String url, IPirateShip pirate)
	{
		Image shipImage = new Image(url, getScaleFactor(), getScaleFactor(), true, true);
		ImageView imageView = new ImageView(shipImage);
		imageView.setX(pirate.getCurrentLocation().x * getScaleFactor());
		imageView.setY(pirate.getCurrentLocation().y * getScaleFactor());
		getAp().getChildren().add(imageView);
		return imageView;
	}

	private ImageView createSnakeImage(Snake snake)
	{
		Image snakeImage = new Image(new File("images\\snakeboi.jpg").toURI().toString(), getScaleFactor(), getScaleFactor(), true, true);
		snakeImageView = new ImageView(snakeImage);
		snakeImageView.setX(snake.getCurrentLocation().x * getScaleFactor());
		snakeImageView.setY(snake.getCurrentLocation().y * getScaleFactor());
		getAp().getChildren().add(snakeImageView);
		return snakeImageView;
	}
	
	private ImageView createEelImage(Eel eel)
	{
		Image eelImage = new Image(new File("images\\eelboi.jpg").toURI().toString(), getScaleFactor(), getScaleFactor(), true, true);
		eelImageView = new ImageView(eelImage);
		eelImageView.setX(eel.getCurrentLocation().x * getScaleFactor());
		eelImageView.setY(eel.getCurrentLocation().y * getScaleFactor());
		getAp().getChildren().add(eelImageView);
		return eelImageView;
	}

	@Override
	public void start(Stage oceanStage)
    {
        //TODO: we could probably turn this all into a single method. Can be something we do towards the end
        //create all the necessary objects
        ap = new AnchorPane();
		ship = Ship.getInstance();
		eel = new Eel();
		snake = new Snake();
		scene = new Scene(getAp(), getDimensions() * getScaleFactor(), getDimensions() * getScaleFactor());
		WhirlpoolFactory whirlpoolFactory = new WhirlpoolFactory(ship);
		followPirateShip = new FollowPirateShip();
		lostPirateShip = new LostPirateShip();
		pirateList = new ArrayList<>();

		//draw the map
		drawGrid();
		Map.getInstance().placeIslands();
		Map.getInstance().placeTreasure();

		//load in the images
		loadShipImage();
		createSnakeImage(snake);
		createEelImage(eel);
		followPirateShip.setImageView(createPirateImage(new File("images\\pirateShip.png").toURI().toString(), followPirateShip)); //pirate image 1
		lostPirateShip.setImageView(createPirateImage(new File("images\\pirateShip.png").toURI().toString(), lostPirateShip)); //pirate image 2
		pirateList.add(followPirateShip);
		pirateList.add(lostPirateShip);

		//add the observers
		ship.addObserver(whirlpoolFactory);
		ship.addObserver(lostPirateShip);
		ship.addObserver(followPirateShip);
		ship.addObserver(snake);
		ship.addObserver(eel);

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