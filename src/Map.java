import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.File;
import java.util.Random;

/*
0: Untouched spaces (movable locations)
1: Non-harming space (island)
2: Harming space (enemy)
 */
public class Map
{
	private int[][] seaMap;
	private int dimensions;
	private final int islandCount;
	private static Map instance; //singleton to make sure there is always only one map

	public static Map initiate(int dimensions, int islandCount)
	{
		if(instance == null)
			instance = new Map(dimensions, islandCount);

		return instance;
	}

	private Map(int dimensions, int islands)
	{
		//any untouched blocks are set to 0
		seaMap = new int[dimensions][dimensions];
		islandCount = islands;
		this.dimensions = dimensions;
	}
	
	public void placeIslands(AnchorPane ap)
	{
		int islandsToPlace = islandCount;
		while(islandsToPlace > 0)
		{
			Random randy1 = new Random();
		    int x = randy1.nextInt(dimensions);
			int y = randy1.nextInt(dimensions);
			if(seaMap[x][y] == 0) // untouched location
			{
				seaMap[x][y] = 1; //set it to a Non-harming space
				placeIslandImage(x, y, ap);
				islandsToPlace--;
			}
		}
	}

	private void placeIslandImage(int x, int y, AnchorPane ap)
	{
		Image islandImage = new Image(new File("images\\island.jpg").toURI().toString(), 50, 50, true, true);
		ImageView island = new ImageView(islandImage);
		island.setX(x * 50); //scale factor
		island.setY(y * 50);
		island.setScaleX(.95); //I felt the sizes looked a little too much
		island.setScaleY(.95);
		ap.getChildren().add(island);
	}


	public Point initShip()
	{
		Random randy1 = new Random();
		Point newy = new Point(randy1.nextInt(dimensions), randy1.nextInt(dimensions));

		while(checkLocation(newy.x, newy.y) != 0) //if the location is already taken
			newy = new Point(randy1.nextInt(dimensions), randy1.nextInt(dimensions)); //continue creating new locations until a good space is found

		return newy;
	}
	
	public Point initPirate()
	{
		Random randy1 = new Random();
		Point newy = new Point(randy1.nextInt(dimensions), randy1.nextInt(dimensions));

		while(checkLocation(newy.x, newy.y) != 0) //^^
			newy = new Point(randy1.nextInt(dimensions), randy1.nextInt(dimensions));

		seaMap[newy.x][newy.y] = 2; //set them to a damaging enemy
		return newy;
	}
	
	public int[][] getMap() { return seaMap; }
	
	//int getDimensions() { return dimensions; } not necessary because you have the dimensions as a local variable
	
	public int checkLocation(int x, int y) { return seaMap[x][y]; }
}
