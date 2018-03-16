package code;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

/**
 * ESchool is the class that controls how the eels move together
 * Eels are meant to move as a group, so we made it a composite design
 * We also used the state pattern to decide which direction to move throughout the game
 */
public class ESchool implements Observer
{
	static ArrayList<Eel> eelList;
	EelState currentState;
	EelState eastState;
	EelState westState;

	ESchool()
	{
		eelList = new ArrayList<>();
		eastState = new EastState();
		westState = new WestState();
		currentState = westState;
		createSwarm();
		Ship.getInstance().addObserver(this);
	}

	public static ArrayList<Eel> getEelList() { return eelList; }

	private void createSwarm()
	{
		Random rand = new Random();
		int x = rand.nextInt(Explorer.getDimensions());
		int y = rand.nextInt(Explorer.getDimensions());

		while (Map.getInstance().checkLocation(x, y) != 0) {
			x = rand.nextInt(Explorer.getDimensions());
			y = rand.nextInt(Explorer.getDimensions());
		}

		eelList.add(new Eel(x, y)); //this will be the eel that they all are made around

		int iterX = eelList.get(0).getCurrentLocation().x;
		int iterY = eelList.get(0).getCurrentLocation().y;
		int counter = 1;

		while (counter < getNumEels())
		{
			if (Map.getInstance().checkLocation(iterX + 1, iterY) == 0) {
				eelList.add(new Eel(iterX + 1, iterY));
				counter++;

				if(counter >= getNumEels())
					break;
			}
			if (Map.getInstance().checkLocation(iterX - 1, iterY) == 0) {
				eelList.add(new Eel(iterX - 1, iterY));
				counter++;

				if(counter >= getNumEels())
					break;
			}
			if (Map.getInstance().checkLocation(iterX, iterY + 1) == 0) {
				eelList.add(new Eel(iterX, iterY + 1));
				counter++;

				if(counter >= getNumEels())
					break;
			}
			if (Map.getInstance().checkLocation(iterX, iterY + 1) == 0) {
				eelList.add(new Eel(iterX + 1, iterY + 1));
				counter++;

				if(counter >= getNumEels())
					break;
			}

			iterX++;
			iterY++;
		}

	}


	/*
	Each ship movement, this checks the eels locations and decides whether the state should be moved to
	West or East
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		int westCount = 0;
		int eastCount = 0;
		for (Eel e : eelList)
		{
			if (e.canGoEast()) eastCount++;
			if (e.canGoWest()) westCount++;
		}


		if(westCount == eastCount) {} //open on both sides, continue with current state
		else if(westCount == getNumEels())
			currentState = westState;
		else if(eastCount == getNumEels())
			currentState = eastState;

		currentState.moveEels();
	}


	private int getNumEels()
	{
		return 4;
	}
	}