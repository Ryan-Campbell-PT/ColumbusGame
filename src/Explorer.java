
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

public class Explorer extends Application {

	Pane ap;
	Map oceanMap;
	final int dimensions = 10;
	final int islandCount = 6;
	final int scalefactor = 50;
	ImageView shipImageView;
	ImageView islandImageView;
	ImageView pirate1ImageView;
	ImageView pirate2ImageView;
	Ship ship;
	Scene scene;
	PirateShip pirate1;
	PirateShip pirate2;
	
	public void drawGrid()
	{
		for(int x = 0; x < dimensions; x++)
		{ 
			for(int y = 0; y < dimensions; y++)
			{ 
				Rectangle rect = new Rectangle(x*scalefactor, y*scalefactor, scalefactor, scalefactor);
				rect.setStroke(Color.BLACK);
				boolean[][] map = oceanMap.seaMap;
				if(map[x][y] == true)
				{
					//rect.setFill(Color.GREEN);
					Image island = new Image("images\\island.jpg", 50, 50, true, true);
					islandImageView = new ImageView(island);
					islandImageView.setX(x*scalefactor);//edit to set island point in OceanMap
					islandImageView.setY(y*scalefactor);//edit to set island point in OceanMap
					ap.getChildren().add(islandImageView);
				}
				else
					rect.setFill(Color.PALETURQUOISE);
				ap.getChildren().add(rect);
			}
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void weighAnchor()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()//Eclipse hates this line for some reason
		{
			public void handle(KeyEvent ke)
			{
				switch(ke.getCode())
				{
				default:
					break;
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
				}
				shipImageView.setX(ship.getShipLocation().x*scalefactor);
				shipImageView.setY(ship.getShipLocation().y*scalefactor);
			}
		});
	}
	
	public void loadShipImage()
	{
		Image shipImage = new Image("images\\ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x*scalefactor);
		shipImageView.setY(ship.getShipLocation().y*scalefactor);
		ap.getChildren().add(shipImageView);
	}
	
	public void loadPirate1Image()
	{
		Image shipImage = new Image("images\\pirateShip.png", 50, 50, true, true);
		pirate1ImageView = new ImageView(shipImage);
		pirate1ImageView.setX(pirate1.getShipLocation().x*scalefactor);
		pirate1ImageView.setY(pirate1.getShipLocation().y*scalefactor);
		ap.getChildren().add(pirate1ImageView);
	}
	
	public void loadPirate2Image()
	{
		Image shipImage = new Image("images\\pirateShip.png", 50, 50, true, true);
		pirate2ImageView = new ImageView(shipImage);
		pirate2ImageView.setX(pirate2.getShipLocation().x*scalefactor);
		pirate2ImageView.setY(pirate2.getShipLocation().y*scalefactor);
		ap.getChildren().add(pirate2ImageView);
	}

	@Override
	public void start(Stage oceanStage) throws Exception {
		ap = new AnchorPane();
		oceanMap = new Map(dimensions, islandCount);
		oceanMap.getMap();
		oceanMap.placeIslands();		
		drawGrid();
		
		ship = new Ship(oceanMap);
		loadShipImage();
		
		pirate1 = new PirateShip(ship, oceanMap);
		loadPirate1Image();
		pirate2 = new PirateShip(ship, oceanMap);
		loadPirate2Image();
		
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
		Scene scene = new Scene(ap, 500, 500);
		oceanStage.setTitle("Chrissy Columbus");
		oceanStage.setScene(scene);
		oceanStage.show();
		weighAnchor();//if you comment this out you can see the map all the other work. The event handler is the only problem.
	}	
}