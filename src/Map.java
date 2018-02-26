import java.awt.Point;
import java.util.Random;

public class Map
{
	boolean[][] seaMap;
	private int dimensions;
	private final int islandCount;

	Map(int dimensions, int islands)
	{
		seaMap = new boolean[dimensions][dimensions];
		islandCount = islands;
		this.dimensions = dimensions;
	}
	
	public void placeIslands()
	{
		int islandsToPlace = islandCount;
		while(islandsToPlace >0)
		{
			Random randy1 = new Random();
		    int x = randy1.nextInt(getDimensions());
			int y = randy1.nextInt(getDimensions());
			if(!seaMap[x][y]) //!= true
			{
				seaMap[x][y] = true;
				islandsToPlace--;
			}
		}
	}
	
	public Point initShip()
	{
		Random randy1 = new Random();
		int x;
		int y;
		x = randy1.nextInt(getDimensions());
		y = randy1.nextInt(getDimensions());
		Point newy = new Point(x, y);
		while(seaMap[x][y]) //true
		{
			x = randy1.nextInt(getDimensions());
			y = randy1.nextInt(getDimensions());
			newy = new Point(x, y);
		}
		return newy;
	}
	
	public Point initPirate()
	{
		Random randy1 = new Random();
		int x;
		int y;
		x = randy1.nextInt(getDimensions());
		y = randy1.nextInt(getDimensions());
		Point newy = new Point(x, y);
		while(seaMap[x][y]) //== true
		{
			x = randy1.nextInt(getDimensions());
			y = randy1.nextInt(getDimensions());
			newy = new Point(x, y);
		}
		return newy;
	}
	
	public boolean[][] getMap()
	{
		return seaMap;
	}
	
	int getDimensions()
	{
		return dimensions;
	}
	
	public boolean isOcean(int x, int y)
	{
		boolean osh = false;
		while (!seaMap[x][y]) //== false
			osh = true;
		return osh;
	}
}
