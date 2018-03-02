
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Explorer extends Application {

	private AnchorPane ap;
	private Map oceanMap;
	private final int dimensions = 10;
	private final int islandCount = 6;
	private final int scaleFactor = 50;
	private Ship ship;
	private Scene scene;
	private ImageView shipImageView;

	/*
	private void drawGrid()
	{
		for(int x = 0; x < dimensions; x++)
		{
			for(int y = 0; y < dimensions; y++)
			{
				Rectangle rect = new Rectangle(x*scalefactor, y*scalefactor, scalefactor, scalefactor);
				rect.setStroke(Color.BLACK);
				int[][] map = oceanMap.getMap();
				if(map[x][y] ) //== true
				{
					Image island = new Image(new File("images\\island.jpg").toURI().toString(), 50, 50, true, true);
					ImageView islandImageView = new ImageView(island);
					islandImageView.setX(x * scalefactor);//edit to set island point in OceanMap
					islandImageView.setY(y * scalefactor);//edit to set island point in OceanMap
					ap.getChildren().add(islandImageView);
				}
				else
					rect.setFill(Color.PALETURQUOISE);
				ap.getChildren().add(rect);
			}
		}
	}
	*/
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
			switch (event.getCode())
			{
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
			}
			shipImageView.setX(ship.getPlayerShipLocation().x * scaleFactor);
			shipImageView.setY(ship.getPlayerShipLocation().y * scaleFactor);
		});
	}

	// we can definitely reduce these methods to a single method. Lets keep that in mind while we code
	private void loadShipImage()
	{
		Image shipImage = new Image(new File("images\\ship.png").toURI().toString(), 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getPlayerShipLocation().x * scaleFactor);
		shipImageView.setY(ship.getPlayerShipLocation().y * scaleFactor);
		ap.getChildren().add(shipImageView);
	}

	private void loadPirateImage(String url, PirateShip pirate)
	{
		Image shipImage = new Image(url, 50, 50, true, true);
		ImageView ImageView = new ImageView(shipImage);
		ImageView.setX(pirate.getPirateShipLocation().x * scaleFactor);
		ImageView.setY(pirate.getPirateShipLocation().y * scaleFactor);
		ap.getChildren().add(ImageView);
	}


	@Override
	public void start(Stage oceanStage) throws Exception {
		ap = new AnchorPane();
		oceanMap = Map.initiate(dimensions, islandCount);
		drawGrid();
		oceanMap.placeIslands(ap);

		ship = new Ship(oceanMap, ap);
		PirateShip pirate1 = new PirateShip(ship, oceanMap);
		PirateShip pirate2 = new PirateShip(ship, oceanMap);

		loadShipImage(); //ship
		loadPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate1); //pirate image 1
		loadPirateImage(new File("images\\pirateShip.png").toURI().toString(), pirate2); //pirate image 2
		
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
		scene = new Scene(ap, 500, 500);
		oceanStage.setTitle("Chrissy Columbus");
		oceanStage.setScene(scene);
		oceanStage.show();
		weighAnchor();//if you comment this out you can see the map all the other work. The event handler is the only problem.
	}
}