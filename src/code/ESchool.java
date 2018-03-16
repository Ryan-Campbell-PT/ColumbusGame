package code;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

public class ESchool implements Observer
{
	ArrayList<Eel> eelList;
	State currentState;
	State eastState;
	State westState;

	ESchool()
	{
		eelList = new ArrayList<>();
		eastState = new EastState();
		westState = new WestState();
		currentState = westState;
		createSwarm();
		Ship.getInstance().addObserver(this);
	}

	public ArrayList<Eel> getEelList() { return eelList; }

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

		while (true)
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

		if(westCount == getNumEels())
			currentState = westState;
		else if (eastCount == getNumEels())
			currentState = eastState;

		if(westCount == eastCount) {} //open on both sides, dont change anything
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


	private interface State
	{
		void moveEels();
	}

	private class EastState implements State
	{
		@Override
		public void moveEels()
		{
			for (Eel e : eelList)
				e.goEast();
		}
	}

	private class WestState implements State
	{

		@Override
		public void moveEels()
		{
			for(Eel e : eelList)
				e.goWest();
		}
	}
}