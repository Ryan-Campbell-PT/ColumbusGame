import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;
import java.util.Random;

/*
0: Untouched spaces (movable locations)
1: Non-harming space (island)
2: Harming space (enemy)
3: Treasure (winning condition)
4: Whirlpool (teleport mechanic)
*/
public class Map
{
	private int[][] seaMap;
	private static Map instance; //singleton to make sure there is always only one map

	public static Map getInstance()
	{
		if(instance == null)
			instance = new Map();

		return instance;
	}

	private Map()
	{
		//any untouched blocks are set to 0
		seaMap = new int[Explorer.getDimensions()][Explorer.getDimensions()];
	}
	
	public void placeIslands()
	{
		int islandsToPlace = Explorer.getIslandCount();
		while(islandsToPlace > 0)
		{
			Random randy1 = new Random();
		    int x = randy1.nextInt(Explorer.getDimensions());
			int y = randy1.nextInt(Explorer.getDimensions());
			if(seaMap[x][y] == 0) // untouched location
			{
				seaMap[x][y] = 1; //set it to a Non-harming space
				placeImage(x, y, new File("images\\island.jpg").toURI().toString());
				islandsToPlace--;
			}
		}
	}

	//create a single treasure that leads to a win condition
	//TODO: Will need to design a splash screen or something to indicate winning. May not want to have it random
	public void placeTreasure()
	{
		Random rand = new Random();
		int x = rand.nextInt(Explorer.getDimensions());
		int y = rand.nextInt(Explorer.getDimensions());
		if(seaMap[x][y] == 0) // untouched location
		{
			seaMap[x][y] = 3; //set it to the treasure spot
			placeImage(x, y, new File("images\\treasureChest.png").toURI().toString());
		}
	}

	private void placeImage(int x, int y, String imageLocation)
	{
		//I felt the sizes looked a little large, so i reduced the width and height slightly
		Image image = new Image(imageLocation, Explorer.getScaleFactor() * .95, Explorer.getScaleFactor() * .95, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setX(x * Explorer.getScaleFactor()); //scale factor
		imageView.setY(y * Explorer.getScaleFactor());
		Explorer.getAp().getChildren().add(imageView);
	}

	//I definitly feel these two methods could be reduced or changed to look better
	public Point initShip()
	{
		Random randy1 = new Random();
		Point newy = new Point(randy1.nextInt(Explorer.getDimensions()), randy1.nextInt(Explorer.getDimensions()));

		while(checkLocation(newy.x, newy.y) != 0) //if the location is already taken
			newy = new Point(randy1.nextInt(Explorer.getDimensions()), randy1.nextInt(Explorer.getDimensions())); //continue creating new locations until a good space is found

		return newy;
	}
	
	public Point initPirate()
	{
		Random randy1 = new Random();
		Point newy = new Point(randy1.nextInt(Explorer.getDimensions()), randy1.nextInt(Explorer.getDimensions()));

		while(checkLocation(newy.x, newy.y) != 0) //^^
			newy = new Point(randy1.nextInt(Explorer.getDimensions()), randy1.nextInt(Explorer.getDimensions()));

		setPoint(newy.x, newy.y, 2); //set them to a damaging enemy
		return newy;
	}

	public void setPoint(int x, int y, int status) { seaMap[x][y] = status; }

	public int checkLocation(int x, int y) { return seaMap[x][y]; }}
